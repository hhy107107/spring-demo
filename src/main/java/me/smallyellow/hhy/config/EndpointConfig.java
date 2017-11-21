package me.smallyellow.hhy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
import org.springframework.web.socket.server.standard.ServerEndpointRegistration;

import me.smallyellow.hhy.websocket.ChatEndPoint;

/**
 * 使用spring boot 必须要加这个配置
 * @author hhy
 * 2017年11月21日下午6:37:04
 */
@Configuration  
public class EndpointConfig {

	@Bean  
	public ServerEndpointRegistration echoEndpoint() {  
		return new ServerEndpointRegistration("/chat", ChatEndPoint.class);  
	}  
	
	//该bean将用底层的WebSocket容器注册任何的被@ServerEndpoint注解的beans。当部署到一个单独的servlet容器时，该角色将被一个servlet容器初始化方法履行，ServerEndpointExporter bean也就不是必需的了。
	@Bean  
	public ServerEndpointExporter endpointExporter() {  
		return new ServerEndpointExporter();  
	}  
	   
}
