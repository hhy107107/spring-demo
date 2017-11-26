/*
 * 文件名称：NoteType.java
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
 @Table(name="note_type")
public class NoteType extends BaseLongEntity {

	private static final long serialVersionUID = 1L;
	/**构造函数**/
	public NoteType() {}
	public NoteType(Long id,String name,String description,Long userId) {
		this.id=id;
		this.name=name;
		this.description=description;
		this.userId=userId;
	}
	public NoteType setNotNull(Long id,String name,Long userId) {
		this.id=id;
		this.name=name;
		this.userId=userId;
		return this;
	}
	/**属性**/
	@Column(name = "name")
	private String name;// 类型名称（非空） 
	@Column(name = "description")
	private String description;// 类型描述
	@Column(name = "user_id")
	private Long userId;// （非空） 

	/**属性Get、Set函数**/
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}

}