package me.smallyellow.hhy.websocket.bean;

/**
 * 文本消息
 * @author hhy
 * 2017年11月23日下午3:50:56
 */
public class TextMessageBean extends MessageBean{

	private static final long serialVersionUID = 8752425988784699189L;

	private String message; //消息内容

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "TextMessageBean [message=" + message + ", to=" + to + ", from=" + from + ", type=" + type + "]";
	}
	
}
