package com.simple.mvc.controller;

import com.simple.mvc.dto.HelloDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@PostMapping("/hello")
	public String hello(@RequestBody HelloDto helloDto){
		System.out.println(helloDto);
		return helloDto.toString();
	}

}
