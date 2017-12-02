/*
 * 文件名称：NoteUserZan.java
 */
package me.smallyellow.hhy.model;

import javax.persistence.Table;
import javax.persistence.Column;
import me.smallyellow.base.boot.mybatis.bean.BaseLongEntity;


/**
 * 名称：
 * 模块描述：数据库表对应实体类
 * 作者：系统自动生成
 */
 @Table(name="note_user_zan")
public class NoteUserZan extends BaseLongEntity {

	private static final long serialVersionUID = 1L;
	/**构造函数**/
	public NoteUserZan() {}
	public NoteUserZan(Long id,Long noteId,Long userId,Short zan) {
		this.id=id;
		this.noteId=noteId;
		this.userId=userId;
		this.zan=zan;
	}
	public NoteUserZan setNotNull(Long id,Long noteId,Long userId,Short zan) {
		this.id=id;
		this.noteId=noteId;
		this.userId=userId;
		this.zan=zan;
		return this;
	}
	/**属性**/
	@Column(name = "note_id")
	private Long noteId;// 笔记id（非空） 
	@Column(name = "user_id")
	private Long userId;// 用户id（非空） 
	@Column(name = "zan")
	private Short zan;// 是否赞 1-赞 2-没赞（非空） 

	/**属性Get、Set函数**/
	public Long getNoteId() {
		return noteId;
	}
	public void setNoteId(Long noteId) {
		this.noteId = noteId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Short getZan() {
		return zan;
	}
	public void setZan(Short zan) {
		this.zan = zan;
	}

}