package com.hong.webSocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.hong.bean.User;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * WebSocket握手拦截器
 */
public class WebSocketHandshakeInterceptor implements HandshakeInterceptor {
	private final static Logger LOGGER = LoggerFactory.getLogger(WebSocketHandshakeInterceptor.class);

	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse serverHttpResponse,
			WebSocketHandler webSocketHandler, Map<String, Object> attributes) throws Exception {
		if (request instanceof ServletServerHttpRequest) {
			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
			HttpSession session = servletRequest.getServletRequest().getSession(true);
			session.setAttribute("test", "测试添加session属性共享");
			String userName = null;
			if (session != null) { // --3hhws
				// 使用userName区分WebSocketHandler，以便定向发送消息
				System.out.println("before Handshake");
				User user = (User) session.getAttribute("user");
				if (user != null)
					userName = user.getUsername();
				else
					userName = "user:" + session.getId();
				if (attributes.get(userName) == null) {
					if (session.getAttribute("orderId") != null)
						attributes.put("orderId", session.getAttribute("orderId"));
					attributes.put("WEBSOCKET_USERNAME", userName);
				}
			}
		}
		return true;
	}

	public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse,
			WebSocketHandler webSocketHandler, Exception e) {
		System.out.println("After Handshake");
	}
}
