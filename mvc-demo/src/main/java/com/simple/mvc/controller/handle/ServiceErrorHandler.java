package com.simple.mvc.controller.handle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@ControllerAdvice(annotations = RestController.class) // 对于Interceptor（拦截器）层的异常，Spring框架层的异常，就无能为力了
public class ServiceErrorHandler implements ErrorController {

	@Override
	public String getErrorPath() {
		return null;
	}
}