package test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimaps;
import com.test.domin.A;
import com.test.domin.B;
import com.test.domin.C;
import com.test.domin.D;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainTest {

	public static void main(String[] args) throws Exception {
		D d = new D(1L, "D1", 1l);
		C c = new C(1L, "C1", 1L, Lists.newArrayList(d));
		B b = new B(1L, "B1", 1L, Lists.newArrayList(c));
		A a = new A(1L, "A1", Lists.newArrayList(b));


		List<A> aList = new ArrayList<>();
		aList.add(a);

		System.out.println(JSON.toJSONString(aList, SerializerFeature.WriteNullStringAsEmpty));


		String json  = "[{\"id\":1,\"name\":\"A1\",\"bList\":[{\"aId\":1,\"cList\":[{\"bId\":11,\"dList\":[{\"cId\":111,\"id\":1111,\"name\":\"D1111\"},{\"cId\":111,\"id\":1112,\"name\":\"D1112\"}]," +
				"\"id\":111,\"name\":\"C111\"},{\"bId\":11,\"dList\":[{\"cId\":112,\"id\":1123,\"name\":\"D1123\"},{\"cId\":112,\"id\":1124,\"name\":\"D1124\"}],\"id\":112,\"name\":\"C112\"}]," +
				"\"id\":11,\"name\":\"B11\"},{\"aId\":1,\"cList\":[{\"bId\":12,\"dList\":[{\"cId\":123,\"id\":1235,\"name\":\"D1235\"},{\"cId\":123,\"id\":1236,\"name\":\"D1236\"}],\"id\":123," +
				"\"name\":\"C123\"},{\"bId\":12,\"dList\":[{\"cId\":124,\"id\":1247,\"name\":\"D1247\"},{\"cId\":124,\"id\":1248,\"name\":\"D1248\"}],\"id\":124,\"name\":\"C124\"}],\"id\":12," +
				"\"name\":\"B12\"}]},{\"id\":2,\"name\":\"A2\",\"bList\":[{\"aId\":2,\"cList\":[{\"bId\":23,\"dList\":[{\"cId\":235,\"id\":2359,\"name\":\"D2359\"},{\"cId\":235,\"id\":23510," +
				"\"name\":\"D23510\"}],\"id\":235,\"name\":\"C235\"},{\"bId\":23,\"dList\":[{\"cId\":236,\"id\":23611,\"name\":\"D23611\"},{\"cId\":236,\"id\":23612,\"name\":\"D23612\"}],\"id\":236," +
				"\"name\":\"C236\"}],\"id\":23,\"name\":\"B23\"},{\"aId\":2,\"cList\":[{\"bId\":12,\"dList\":[{\"cId\":123,\"id\":1235,\"name\":\"D1235\"},{\"cId\":123,\"id\":1236," +
				"\"name\":\"D1236\"}],\"id\":123,\"name\":\"C123\"},{\"bId\":12,\"dList\":[{\"cId\":124,\"id\":1247,\"name\":\"D1247\"},{\"cId\":124,\"id\":1248,\"name\":\"D1248\"}],\"id\":124," +
				"\"name\":\"C124\"}],\"id\":12,\"name\":\"B12\"}]}]";

//		A a1 = JSON.parseObject(json, A.class);
		List<A> aList1 = JSONArray.parseArray(json, A.class);

		List<B> bLists = aList1.stream().flatMap(p -> p.getBList().stream()).collect(Collectors.toList());
		List<C> cLists = bLists.stream().flatMap(p -> p.getCList().stream()).collect(Collectors.toList());
		List<D> dLists = cLists.stream().flatMap(p -> p.getDList().stream()).collect(Collectors.toList());

		System.out.println(aList1);

		for (A a1 : aList1) {
			System.out.println(a1);
		}

		System.out.println("----------------------------------\r\n");

		for (B b1 : bLists) {
			System.out.println(b1);
		}

		System.out.println("----------------------------------\r\n");

		for (C c1 : cLists) {
			System.out.println(c1);
		}

		System.out.println("----------------------------------\r\n");

		for (D d1 : dLists) {
			System.out.println(d1);
		}

		System.out.println("----------------------------------\r\n");

		ImmutableListMultimap<Long, B> multimap = Multimaps.index(bLists, t -> t.getAId());
		for (Map.Entry<Long, B> entry : multimap.entries()) {
			System.out.println(entry.getKey() + "::" +entry.getValue());
		}

		System.out.println("----------------------------------\r\n");

	}

}
