package me.smallyellow.hhy.websocket.bean;

import java.io.Serializable;

/**
 * 消息对象
 * @author hhy
 * 2017年11月23日下午3:51:57
 */
abstract public class MessageBean implements Serializable{
	public enum TYPE {
		CHAT((short)1),
		PUSH((short)2);
		private final Short value;
	    //构造方法必须是private或者默认
	    private TYPE(Short value) {
	        this.value = value;
	    }

	    public TYPE valueOf(Short value) {
	        switch (value) {
	        case 1:
	            return TYPE.CHAT;
	        case 2:
	            return TYPE.PUSH;
	        default:
	            return null;
	        }
	    }

		public Short getValue() {
			return value;
		}
	}
	private static final long serialVersionUID = 8752425988784699189L;
	
	protected Long to; //消息发送给谁
	protected Long from; //消息来自谁
	protected TYPE type; //消息类型
	protected Short status; //消息状态
	
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	public Long getTo() {
		return to;
	}
	public void setTo(Long to) {
		this.to = to;
	}
	public Long getFrom() {
		return from;
	}
	public void setFrom(Long from) {
		this.from = from;
	}
	public TYPE getType() {
		return type;
	}
	public void setType(TYPE type) {
		this.type = type;
	}
	
}
