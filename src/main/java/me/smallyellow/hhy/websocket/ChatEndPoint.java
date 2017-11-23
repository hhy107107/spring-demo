package me.smallyellow.hhy.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.websocket.CloseReason;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.tomcat.websocket.WsSession;

import me.smallyellow.base.boot.web.exception.WebException;
import me.smallyellow.base.boot.web.session.XHSessionContext;
import me.smallyellow.base.core.utils.CollectionUtils;
import me.smallyellow.hhy.config.HttpSessionConfigurator;
import me.smallyellow.hhy.constant.CommonConst;
import me.smallyellow.hhy.model.UserInfo;
import me.smallyellow.hhy.websocket.handler.ChatMessageHandler;
import me.smallyellow.hhy.websocket.handler.TextMessageHandler;

/**
 * 定义一个端点
 * @author hhy
 * 2017年11月21日下午7:18:51
 */
@ServerEndpoint(value="/chat", configurator = HttpSessionConfigurator.class)
public class ChatEndPoint extends Endpoint{

	private static final Map<Long, Session> sessionMap; //当前连接的用户
	
	static{
		sessionMap = new HashMap<>();
	}
	
	@Override
	public void onOpen(Session session, EndpointConfig config) {
		System.out.println("被打开了..");
		WsSession wsSesstion = (WsSession)session;
		session.addMessageHandler(new ChatMessageHandler());
		session.addMessageHandler(new TextMessageHandler());
		HttpSession httpSession = XHSessionContext.getSession(wsSesstion.getHttpSessionId());
		/*HttpSession httpSession = (HttpSession) config.getUserProperties().
				get(HttpSession.class.getName());*/
		UserInfo userInfo = (UserInfo) httpSession.getAttribute(CommonConst.USER);
		if(userInfo != null) {
			sessionMap.put(userInfo.getId(), session);
		} else {
			throw new WebException("未登录");
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
	
	/**
	 * 给指定用户发送消息
	 * @param userId 指定用户id
	 * @param message 消息内容
	 */
	public void sendMessage(Integer userId, String message) {
		Session session = sessionMap.get(userId);
		if(session != null) {
			//可以发送消息
			try {
				session.getBasicRemote().sendText(message);
				System.out.println("发送了消息：" + message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			throw new WebException("暂不支持离线消息");
		}
	}
	
	/**
	 * 向所有用户发送消息
	 * @param message 消息内容
	 */
	public void sendMessage(String message) {
		if(CollectionUtils.isNotEmpty(sessionMap)){
			for (Map.Entry<Long, Session> entry : sessionMap.entrySet()) {
				Session session = entry.getValue();
				try {
					session.getBasicRemote().sendText(message);
					System.out.println("发送了消息：" + message);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			throw new WebException("当前没有用户在线");
		}
	}
}
