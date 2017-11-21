package me.smallyellow.hhy.websocket;

import java.io.IOException;

import javax.websocket.CloseReason;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.web.socket.server.standard.SpringConfigurator;

/**
 * 定义一个端点
 * @author hhy
 * 2017年11月21日下午7:18:51
 */
@ServerEndpoint(value="/chat", configurator = SpringConfigurator.class)
public class ChatEndPoint extends Endpoint{

	@Override
	public void onOpen(Session session, EndpointConfig arg1) {
		System.out.println("被打开了..");
		session.addMessageHandler(new ChatMessageHandler());
		try {
			session.getBasicRemote().sendText("我在跟你说话");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onClose(Session session, CloseReason closeReason) {
		super.onClose(session, closeReason);
		System.out.println("被关闭了..");
	}

	@Override
	public void onError(Session session, Throwable throwable) {
		super.onError(session, throwable);
		System.out.println("发生了错误..");
	}
}
