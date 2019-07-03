package com.example.websocket.simpledemo.controller;

import com.example.websocket.simpledemo.websocket.ReverseWebSocketEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class HelloController {

	@Autowired
	private ReverseWebSocketEndpoint webSocketEndpoint;

	@GetMapping("/hello")
	public String hello() throws IOException {
		webSocketEndpoint.handleMessage(null, "12345", "12345");
		return "SUCCESS";
	}
}
