package com.simple.mvc.controller;

import com.simple.mvc.dto.HelloDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class HelloController {

	@PostMapping("/hello")
	public String hello(@RequestBody HelloDto helloDto){
		System.out.println(helloDto);
		return helloDto.toString();
	}

	@GetMapping("/helloDto")
	public HelloDto helloDto(){
		HelloDto helloDto = new HelloDto();
		helloDto.setHelloDate(new Date());
		helloDto.setHelloWorld("hello word");
		helloDto.setId(12);
		return helloDto;
	}

}
