package com.example.websocket.simpledemo.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * ReverseWebSocketEndpoint
 *
 * @author zifangsky
 * @date 2018/9/30
 * @since 1.0.0
 */
@Component
@ServerEndpoint("/reverse/{sid}")
public class ReverseWebSocketEndpoint {

	@OnMessage
	public void handleMessage(Session session, String message, @PathParam("sid") String sid) throws IOException {
		System.out.println("------------------------" + sid + "______" + this);
		session.getBasicRemote().sendText("Reversed: " + new StringBuilder(message).reverse());
	}

}
