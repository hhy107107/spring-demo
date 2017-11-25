package me.smallyellow.hhy.websocket.bean;

/**
 * 通知消息
 * @author hhy
 * 2017年11月25日上午11:27:08
 */
public class NoticeMessageBean extends MessageBean{

	private static final long serialVersionUID = -7987346079067246584L;
	
	private String tag; //通知标记 属于哪类通知
	
	private int event; //通知事件

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public int getEvent() {
		return event;
	}

	public void setEvent(int event) {
		this.event = event;
	}
	
}
