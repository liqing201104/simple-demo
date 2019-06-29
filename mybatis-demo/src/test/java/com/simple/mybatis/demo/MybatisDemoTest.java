package com.simple.mybatis.demo;

import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.simple.mybatis.demo.domain.SysRole;
import com.simple.mybatis.demo.domain.UserInfo;
import com.simple.mybatis.demo.mapper.SysRoleMapper;
import com.simple.mybatis.demo.mapper.UserInfoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MybatisDemoApplication.class)
public class MybatisDemoTest {
	
	@Autowired
	private UserInfoMapper userMapper;

	@Autowired
	private SysRoleMapper roleMapper;

	@Test
	public void testInsert(){


		UserInfo userInfo = new UserInfo("zhangsan", "12345");
		Integer insert = userMapper.insert(userInfo);
		System.out.println(insert + "__________" + userInfo.getId());

		UserInfo userInfo2 = new UserInfo("zhangsan", "12345");
		Integer insert2 = userMapper.insert2(userInfo2);
		System.out.println(insert2 + "__________" + userInfo2.getId());

		SysRole sysRole = new SysRole("admin");
		Integer insert1 = roleMapper.insert(sysRole);
		System.out.println(insert1 + "__________" + sysRole.getRoleId());

		SysRole sysRole3 = new SysRole("admin");
		Integer insert3 = roleMapper.insert2(sysRole3);
		System.out.println(insert3 + "__________" + sysRole3.getRoleId());

	}
			
	
}
