/*
 * 文件名称：NoteUserGrade.java
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
 @Table(name="note_user_grade")
public class NoteUserGrade extends BaseLongEntity {

	private static final long serialVersionUID = 1L;
	/**构造函数**/
	public NoteUserGrade() {}
	public NoteUserGrade(Long id,Long userId,Long gradeId) {
		this.id=id;
		this.userId=userId;
		this.gradeId=gradeId;
	}
	public NoteUserGrade setNotNull(Long id,Long userId,Long gradeId) {
		this.id=id;
		this.userId=userId;
		this.gradeId=gradeId;
		return this;
	}
	/**属性**/
	@Column(name = "user_id")
	private Long userId;// 用户id（非空） 
	@Column(name = "grade_id")
	private Long gradeId;// 等级id（非空） 

	/**属性Get、Set函数**/
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getGradeId() {
		return gradeId;
	}
	public void setGradeId(Long gradeId) {
		this.gradeId = gradeId;
	}

}