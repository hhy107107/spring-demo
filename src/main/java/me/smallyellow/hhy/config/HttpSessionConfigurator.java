package me.smallyellow.hhy.config;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

import org.springframework.web.socket.server.standard.SpringConfigurator;

/**
 * 端点配置
 * @author hhy
 * 2017年11月22日下午1:45:13
 */
public class HttpSessionConfigurator extends SpringConfigurator{
	
	@Override  
	public void modifyHandshake(ServerEndpointConfig sec,  
	                                HandshakeRequest request, HandshakeResponse response) {  
	    // 获取当前Http连接的session  
		HttpSession httpSession = (HttpSession) request.getHttpSession();  
		// 将http session信息注入websocket session  
		sec.getUserProperties().put(HttpSession.class.getName(), httpSession);  
	}  
}
