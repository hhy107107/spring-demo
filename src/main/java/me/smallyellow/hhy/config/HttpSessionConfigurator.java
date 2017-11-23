package me.smallyellow.hhy.config;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

/**
 * 端点配置(试图将HttpSession 放到 ServerEndpointConfig中，但这个配置没起到作用....
 * 改为通过sessionId获取session
 * @author hhy
 * 2017年11月22日下午1:45:13
 */
public class HttpSessionConfigurator extends ServerEndpointConfig.Configurator{
	
	@Override  
	public void modifyHandshake(ServerEndpointConfig sec,  
	                                HandshakeRequest request, HandshakeResponse response) {  
	    // 获取当前Http连接的session  
		HttpSession httpSession = (HttpSession) request.getHttpSession();  
		// 将http session信息注入websocket session  
		sec.getUserProperties().put(HttpSession.class.getName(), httpSession);  
	}  
}
