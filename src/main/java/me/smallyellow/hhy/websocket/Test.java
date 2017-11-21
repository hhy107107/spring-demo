package me.smallyellow.hhy.websocket;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.smallyellow.hhy.config.HttpSessionConfigurator;

@ServerEndpoint(value="/endpointSang", configurator = HttpSessionConfigurator.class)
public class Test {
	private Logger logger = LoggerFactory.getLogger(Test.class);

    
	//连接时执行
    @OnOpen
    public void onOpen(Session session) throws IOException{
        logger.debug("新连接：{}");
    }

    //关闭时执行
    @OnClose
    public void onClose(){
        logger.debug("连接：{} 关闭");
    }

    //收到消息时执行
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        logger.debug("收到用户{}的消息{}",message);
        session.getBasicRemote().sendText("收到消息 "); //回复用户
    }

    //连接错误时执行
    @OnError
    public void onError(Session session, Throwable error){
        logger.debug("用户id为：{}的连接发送错误");
        error.printStackTrace();
    }


}
