package com.simple.security.controller;

import com.simple.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class HelloController {

	@GetMapping("/hello/{name}")
	public String hello(@PathVariable("name") String name, HttpSession session){
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
		String username = userDetails.getUsername();

		return "Hello：" + username;
	}

	@Autowired
	private UserService userService;

	@GetMapping("/register")
	public String register(@RequestParam("userName") String userName, @RequestParam("password")String password){
		return userService.register(userName, password);
	}

}
