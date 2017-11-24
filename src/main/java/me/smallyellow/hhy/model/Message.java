/*
 * 文件名称：Message.java
 */
package me.smallyellow.hhy.model;

import javax.persistence.Table;
import javax.persistence.Column;
import me.smallyellow.base.boot.mybatis.bean.BaseLongEntity;

import java.util.Date;

/**
 * 名称：
 * 模块描述：数据库表对应实体类
 * 作者：系统自动生成
 */
 @Table(name="message")
public class Message extends BaseLongEntity {

	private static final long serialVersionUID = 1L;
	/**构造函数**/
	public Message() {}
	public Message(Long id,Long userTo,Long userFrom,Short type,String message,Date sendTime) {
		this.id=id;
		this.userTo=userTo;
		this.userFrom=userFrom;
		this.type=type;
		this.message=message;
		this.sendTime=sendTime;
	}
	public Message setNotNull(Long id,Long userTo,Long userFrom,Short type,Date sendTime) {
		this.id=id;
		this.userTo=userTo;
		this.userFrom=userFrom;
		this.type=type;
		this.sendTime=sendTime;
		return this;
	}
	/**属性**/
	@Column(name = "user_to")
	private Long userTo;// 消息发送给谁（非空） 
	@Column(name = "user_from")
	private Long userFrom;// 消息来自哪里（非空） 
	@Column(name = "type")
	private Short type;// 类型（非空） 
	@Column(name = "message")
	private String message;// 消息内容
	@Column(name = "send_time")
	private Date sendTime;// 发送时间（非空） 

	/**属性Get、Set函数**/
	public Long getUserTo() {
		return userTo;
	}
	public void setUserTo(Long userTo) {
		this.userTo = userTo;
	}
	public Long getUserFrom() {
		return userFrom;
	}
	public void setUserFrom(Long userFrom) {
		this.userFrom = userFrom;
	}
	public Short getType() {
		return type;
	}
	public void setType(Short type) {
		this.type = type;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

}