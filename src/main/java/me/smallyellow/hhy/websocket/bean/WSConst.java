package me.smallyellow.hhy.websocket.bean;

/**
 * 常量/返回码 -32768~32768
 * @author hhy
 * 2017年11月25日下午2:25:17
 */
public class WSConst {
	public static class Code {
		public static final Short INIT = 0;// 服务器知道了你的发送请求，但还未进行任何操作
		public static final Short RECEIVED = 1;// 接收成功
		public static final Short OUT_LINE = 2;//发送成功，用户离线
		public static final Short FAILURE = 3;// 未知错误，发送失败
		public static final Short SEND = 4;//发送成功 
	}
	public static class Tag {
		public static final String CHAT = "chat";// 通知类型 聊天相关
	}
}
