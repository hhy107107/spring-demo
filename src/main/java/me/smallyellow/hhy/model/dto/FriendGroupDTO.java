package me.smallyellow.hhy.model.dto;

import java.util.List;

import me.smallyellow.hhy.model.FriendsGroup;

/**
 * 分组，包括好友
 * @author smallYellow
 *
 */
public class FriendGroupDTO extends FriendsGroup{

	private static final long serialVersionUID = 8371115232921651239L;

	private List<UserFriendsDTO> friends;

	public List<UserFriendsDTO> getFriends() {
		return friends;
	}

	public void setFriends(List<UserFriendsDTO> friends) {
		this.friends = friends;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
}
