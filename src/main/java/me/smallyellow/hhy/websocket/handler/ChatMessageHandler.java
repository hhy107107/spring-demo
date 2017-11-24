package me.smallyellow.hhy.websocket.handler;

import java.util.Date;

import javax.websocket.MessageHandler;

import org.springframework.beans.factory.annotation.Autowired;

import me.smallyellow.base.boot.web.exception.WebException;
import me.smallyellow.base.core.utils.JSONUtils;
import me.smallyellow.hhy.model.Message;
import me.smallyellow.hhy.service.MessageService;
import me.smallyellow.hhy.websocket.ChatEndPoint;
import me.smallyellow.hhy.websocket.bean.TextMessageBean;

/**
 * 收到消息处理
 * @author hhy
 * 2017年11月16日下午8:25:30
 */
public class ChatMessageHandler implements MessageHandler.Whole<String>{
	
	private MessageService messageService;
	private ChatEndPoint chatEndPoint;
	
	public ChatMessageHandler(MessageService messageService, ChatEndPoint chatEndPoint){
		this.messageService = messageService;
		this.chatEndPoint = chatEndPoint;
	}
	
	@Override
	public void onMessage(String messageStr) {
		System.out.println("收到了消息：" + messageStr);
		TextMessageBean message = JSONUtils.toBean(messageStr, TextMessageBean.class);
		Long toUser = null; //消息发送给谁
		if (message != null) {
			//收到普通文本消息
			//保存聊天记录
			Message msg = new Message();
			msg.setNotNull(null, message.getTo(), message.getFrom(), message.getType().getValue(), new Date());
			msg.setMessage(message.getMessage());
			toUser = message.getTo();
			try{
				messageService.insertMessage(msg);
			} catch (WebException e){
				e.printStackTrace();
				System.out.println("保存消息失败" + msg.toString());
			}
		} else {
			//未知消息
		}
		//收到消息后，如果消息有toUser，就将消息发送toUser
		if (toUser != null) {
			chatEndPoint.sendMessage(toUser, messageStr);
		}
	}

}
