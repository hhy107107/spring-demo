/*
 * 文件名称：NoteGrade.java
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
 @Table(name="note_grade")
public class NoteGrade extends BaseLongEntity {

	private static final long serialVersionUID = 1L;
	/**构造函数**/
	public NoteGrade() {}
	public NoteGrade(Long id,String name,Short grade,Integer num) {
		this.id=id;
		this.name=name;
		this.grade=grade;
		this.num=num;
	}
	public NoteGrade setNotNull(Long id,String name,Short grade) {
		this.id=id;
		this.name=name;
		this.grade=grade;
		return this;
	}
	/**属性**/
	@Column(name = "name")
	private String name;// 写作等级名称（非空） 
	@Column(name = "grade")
	private Short grade;// 写作等级（非空） 
	@Column(name = "num")
	private Integer num;// 升级条件（笔记数量）

	/**属性Get、Set函数**/
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Short getGrade() {
		return grade;
	}
	public void setGrade(Short grade) {
		this.grade = grade;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}

}