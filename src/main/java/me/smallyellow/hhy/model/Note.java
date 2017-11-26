/*
 * 文件名称：Note.java
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
 @Table(name="note")
public class Note extends BaseLongEntity {

	private static final long serialVersionUID = 1L;
	/**构造函数**/
	public Note() {}
	public Note(Long id,Long typeId,String author,Long userId,String content,String title,Date createTime,Date updateTime,Integer heart,Short status) {
		this.id=id;
		this.typeId=typeId;
		this.author=author;
		this.userId=userId;
		this.content=content;
		this.title=title;
		this.createTime=createTime;
		this.updateTime=updateTime;
		this.heart=heart;
		this.status=status;
	}
	public Note setNotNull(Long id,Long typeId,String author,Long userId,String title,Date createTime) {
		this.id=id;
		this.typeId=typeId;
		this.author=author;
		this.userId=userId;
		this.title=title;
		this.createTime=createTime;
		return this;
	}
	/**属性**/
	@Column(name = "type_id")
	private Long typeId;// 类型id（非空） 
	@Column(name = "author")
	private String author;// 作者名称（非空） 
	@Column(name = "user_id")
	private Long userId;// 作者id（非空） 
	@Column(name = "content")
	private String content;// 内容
	@Column(name = "title")
	private String title;// 标题（非空） 
	@Column(name = "create_time")
	private Date createTime;// 创建时间（非空） 
	@Column(name = "update_time")
	private Date updateTime;// 更新时间
	@Column(name = "heart")
	private Integer heart;// 收获了多少喜欢
	@Column(name = "status")
	private Short status;// 状态 1-正常 2-草稿 3-回收站

	/**属性Get、Set函数**/
	public Long getTypeId() {
		return typeId;
	}
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getHeart() {
		return heart;
	}
	public void setHeart(Integer heart) {
		this.heart = heart;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}

}