package com.simple.mvc.controller.handle;

import com.simple.mvc.domain.ServiceResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.HashMap;
import java.util.Map;

@RestController
@ControllerAdvice(annotations = RestController.class)
public class ServiceSuccessHandle implements ResponseBodyAdvice<Object> {

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		Class<?> type = returnType.getMethod().getReturnType();
		return !ServiceResponse.class.isAssignableFrom(type) && !byte[].class.isAssignableFrom(type);
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
		Map<String, Object> map = new HashMap<>();
		map.put("status", 200);
		map.put("msg", "success");
		map.put("data", body);

		return map;
	}
}
