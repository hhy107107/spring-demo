/*
 * 文件名称：UserFriends.java
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
 @Table(name="user_friends")
public class UserFriends extends BaseLongEntity {

	private static final long serialVersionUID = 1L;
	/**构造函数**/
	public UserFriends() {}
	public UserFriends(Long id,Long userId,Long friendId,Long groupId) {
		this.id=id;
		this.userId=userId;
		this.friendId=friendId;
		this.groupId=groupId;
	}
	public UserFriends setNotNull(Long id,Long userId,Long friendId,Long groupId) {
		this.id=id;
		this.userId=userId;
		this.friendId=friendId;
		this.groupId=groupId;
		return this;
	}
	/**属性**/
	@Column(name = "user_id")
	private Long userId;// 用户id（非空） 
	@Column(name = "friend_id")
	private Long friendId;// 好友用户id（非空） 
	@Column(name = "group_id")
	private Long groupId;// 分组id（非空） 

	/**属性Get、Set函数**/
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getFriendId() {
		return friendId;
	}
	public void setFriendId(Long friendId) {
		this.friendId = friendId;
	}
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((friendId == null) ? 0 : friendId.hashCode());
		result = prime * result + ((groupId == null) ? 0 : groupId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		UserFriends other = (UserFriends) obj;
		if (friendId == null) {
			if (other.friendId != null)
				return false;
		} else if (!friendId.equals(other.friendId))
			return false;
		if (groupId == null) {
			if (other.groupId != null)
				return false;
		} else if (!groupId.equals(other.groupId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

}