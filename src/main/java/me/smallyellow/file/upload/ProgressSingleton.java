package me.smallyellow.file.upload;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.socket.TextMessage;

import me.smallyellow.hhy.websocket.HHYWebSocketHandler;

/**
 * 文件上传进度
 * @author hhy
 * 2017年11月17日上午9:13:19
 */
public class ProgressSingleton {
	private static HHYWebSocketHandler handler = new HHYWebSocketHandler();
	private static ConcurrentHashMap<Object, Object> table = new ConcurrentHashMap<>();
	
	public static void put(Object key, Object value){
		table.put(key, value);
		TextMessage message = new TextMessage(table.get(key).toString());
		handler.sendMessageToUsers(message);
	}
	
	public static Object get(Object key){
		return table.get(key);
	}
	
	public static Object remove(Object key){
		return table.remove(key);
	}
}
