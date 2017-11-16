package me.smallyellow.hhy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * ws 配置
 * @author hhy
 * 2017年11月15日下午5:07:09
 */
@Configuration
@EnableWebSocketMessageBroker  //注解用于开启使用STOMP协议来传输基于代理（MessageBroker）的消息; 这时候控制器（controller）开始支持@MessageMapping,就像是使用@requestMapping一样
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	@Override
	public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
		//将/endpointSang路径注册为STOMP 端点
		//端点的作用——客户端在订阅或发布消息 到目的地址前，要连接该端点
		//用户发送请求/smallyellow/endpointSang与 STOMP server 进行连接，之后再转发到 订阅url
		stompEndpointRegistry.addEndpoint("/endpointSang").withSockJS();	
	}
	
	@Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
		//消息代理将会处理前缀为 "/topic" and "/queue" 消息。
        registry.enableSimpleBroker("/topic","/queue");
        //发送应用程序的消息将会带有 "/app" 前缀
        // registry.setApplicationDestinationPrefixes("/app");
        // 应用程序以 /app 为前缀，而 代理目的地以 /topic或/queue 为前缀.
        // js.url = "/xx/app/hello" -> @MessageMapping("/hello") 注释的方法.
    }

}
