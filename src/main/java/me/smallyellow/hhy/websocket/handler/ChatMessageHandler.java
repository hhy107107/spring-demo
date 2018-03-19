package me.smallyellow.hhy.websocket.handler;

import java.io.IOException;

import javax.websocket.MessageHandler;

import me.smallyellow.base.boot.web.exception.WebException;
import me.smallyellow.base.core.utils.JSONUtils;
import me.smallyellow.hhy.service.MessageService;
import me.smallyellow.hhy.websocket.ChatEndPoint;
import me.smallyellow.hhy.websocket.bean.MessageBean;
import me.smallyellow.hhy.websocket.bean.NoticeMessageBean;
import me.smallyellow.hhy.websocket.bean.TextMessageBean;
import me.smallyellow.hhy.websocket.bean.WSConst;

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
		message.setStatus(WSConst.Code.INIT);
		//收到普通文本消息
		if (message != null) {
			//保存聊天记录
			Long messageId = null;
			try{
				messageId = messageService.insertMessage(message);
			} catch (WebException e){
				e.printStackTrace();
				message.setStatus(WSConst.Code.FAILURE);
				System.out.println("保存消息失败" + message.toString());
			}
			//收到消息后，如果是聊天消息,并且消息成功保存到数据库,就将消息发送toUser,并告知fromUser该消息的发送状态
			if (message.getType().equals(MessageBean.TYPE.CHAT) 
					&& message.getStatus().equals(WSConst.Code.INIT)) {
				try {
					chatEndPoint.sendMessage(message.getTo(), messageStr);
					message.setStatus(WSConst.Code.SEND); //发送成功
				} catch (WebException e) {
					e.printStackTrace();
					//暂不支持离线消息
					message.setStatus(WSConst.Code.OUT_LINE);
				} catch (IOException e) {
					e.printStackTrace();
					//用户在线，但消息发送失败
					message.setStatus(WSConst.Code.FAILURE);
				} finally {
					//修改消息状态
					messageService.updateMessageStatus(messageId, message.getStatus());
					//将消息状态告知前台
					try {
						NoticeMessageBean noticeMessage = new NoticeMessageBean();
						noticeMessage.setType(MessageBean.TYPE.PUSH); //标记为推送
						noticeMessage.setTag(WSConst.Tag.CHAT); //推送的tag 是chat
						noticeMessage.setEvent(message.getStatus()); //将消息的状态推送给前台
						chatEndPoint.sendMessage(message.getFrom(), JSONUtils.toJSON(noticeMessage));
					} catch (WebException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

}
