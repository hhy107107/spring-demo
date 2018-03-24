/*
 * 文件名称：FriendsGroup.java
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
 @Table(name="friends_group")
public class FriendsGroup extends BaseLongEntity {

	private static final long serialVersionUID = 1L;
	/**构造函数**/
	public FriendsGroup() {}
	public FriendsGroup(Long id,Long onwerUser,String name) {
		this.id=id;
		this.onwerUser=onwerUser;
		this.name=name;
	}
	public FriendsGroup setNotNull(Long id,Long onwerUser) {
		this.id=id;
		this.onwerUser=onwerUser;
		return this;
	}
	/**属性**/
	@Column(name = "onwer_user")
	private Long onwerUser;// 所有者ID（非空） 
	@Column(name = "name")
	private String name;// 分组名字

	/**属性Get、Set函数**/
	public Long getOnwerUser() {
		return onwerUser;
	}
	public void setOnwerUser(Long onwerUser) {
		this.onwerUser = onwerUser;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((onwerUser == null) ? 0 : onwerUser.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FriendsGroup other = (FriendsGroup) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (onwerUser == null) {
			if (other.onwerUser != null)
				return false;
		} else if (!onwerUser.equals(other.onwerUser))
			return false;
		return true;
	}

}