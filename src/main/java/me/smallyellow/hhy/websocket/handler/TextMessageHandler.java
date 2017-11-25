package me.smallyellow.hhy.websocket.handler;

import java.util.Date;

import javax.websocket.MessageHandler;

import org.springframework.beans.factory.annotation.Autowired;

import me.smallyellow.base.boot.web.exception.WebException;
import me.smallyellow.hhy.model.Message;
import me.smallyellow.hhy.service.MessageService;
import me.smallyellow.hhy.websocket.bean.TextMessageBean;

/**
 * 普通文本消息  无用，请看{@link ChatMessageHandler}
 * @author hhy
 * 2017年11月23日下午3:59:31
 */
public class TextMessageHandler implements MessageHandler.Whole<TextMessageBean>{
	
	@Autowired
	private MessageService messageService;
	
	@Override
	public void onMessage(TextMessageBean message) {
		System.out.println("收到了消息：" + message);
		//保存聊天记录
		Message msg = new Message();
		msg.setNotNull(null, message.getTo(), message.getFrom(), message.getType().getValue(), new Date(), msg.getStatus());
		try{
			messageService.insertMessage(msg);
		} catch (WebException e){
			e.printStackTrace();
			System.out.println("保存消息失败" + msg.toString());
		}
	}

}
