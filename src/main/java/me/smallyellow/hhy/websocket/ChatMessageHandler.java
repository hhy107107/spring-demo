package me.smallyellow.hhy.websocket;

import javax.websocket.MessageHandler;

/**
 * 
 * @author hhy
 * 2017年11月16日下午8:25:30
 */
public class ChatMessageHandler implements MessageHandler.Whole<String>{
	
	@Override
	public void onMessage(String message) {
		System.out.println("收到了消息：" + message);
	}

}
