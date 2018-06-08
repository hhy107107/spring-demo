package me.smallyellow.hhy.im.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tio.core.Aio;
import org.tio.im.common.packets.ChatBody;
import org.tio.im.common.packets.Command;
import org.tio.im.common.packets.Group;
import org.tio.im.common.packets.User;
import org.tio.im.common.tcp.TcpPacket;
import org.tio.im.common.ws.WsPacket;

import me.smallyellow.base.boot.web.exception.WebException;
import me.smallyellow.base.core.utils.CollectionUtils;
import me.smallyellow.hhy.mapper.FriendsGroupMapper;
import me.smallyellow.hhy.mapper.UserFriendsMapper;
import me.smallyellow.hhy.mapper.UserInfoMapper;
import me.smallyellow.hhy.model.FriendsGroup;
import me.smallyellow.hhy.model.UserFriends;
import me.smallyellow.hhy.model.UserInfo;
import me.smallyellow.hhy.model.dto.FriendGroupDTO;
import me.smallyellow.hhy.model.dto.UserFriendsDTO;

@Service
public class FriendsService {

	@Autowired
	private UserFriendsMapper userFriendsMapper;
	
	@Autowired
	private FriendsGroupMapper friendsGroupMapper;
	
	@Autowired
	private UserInfoMapper userInfoMapper;

	
	/**
	 * 好友列表
	 * @param userId
	 * @return
	 * @throws WebException
	 */
	public List<Group> listFriends(Long userId) throws WebException {
		List<UserFriendsDTO> list = userFriendsMapper.listFriendsWithGroup(userId);
		if (CollectionUtils.isNotEmpty(list) && list.size() > 1 || (list.size() == 1 && list.get(0) != null)) {
			List<Group> groupList = new ArrayList<>();
			for (UserFriendsDTO dto : list) {
				User user = null;
				if (dto.getFriendId() != null) {
					user = new User();
					user.setId(dto.getUsername());
					user.setNick(dto.getNick());
					user.setAvatar(dto.getUserface());
				}
				Group group = new Group();
				group.setGroup_id(dto.getGroupId().toString());
				group.setName(dto.getName());
				if (!groupList.contains(group)) {
					if (dto.getFriendId() != null) {
						List<User> friends = new ArrayList<>();
						friends.add(user);
						group.setUsers(friends);;
					}
					groupList.add(group);
				} else {
					if (dto.getFriendId() != null) {
						List<User> fList = groupList.get(groupList.indexOf(group)).getUsers();
						if (CollectionUtils.isNotEmpty(fList)) {
							fList.add(user);
						} else {
							fList = new ArrayList<>();
							fList.add(user);
							groupList.get(groupList.indexOf(group)).setUsers(fList);
						}
					}
				}
				
			}
			return groupList;
		} else {
			FriendsGroup group = new FriendsGroup();
			group.setNotNull(null, userId, (short) 0);
			group.setName("我的好友");
			int result = friendsGroupMapper.insert(group);
			if (result > 0) {
				listFriends(userId);
			}
		}
		
		return null;
	}

	/**
	 * 请求好友
	 * @param username
	 * @param frienduname
	 */
	public boolean requestFriend(String username, String frienduname) {
		UserInfo user = getUserByUsername(username);
		UserInfo friendUser = getUserByUsername(frienduname);
		FriendsGroup friendsGroup = new FriendsGroup();
		friendsGroup.setOnwerUser(friendUser.getId());
		friendsGroup.setType((short) 0);
		FriendsGroup fg = friendsGroupMapper.selectOne(friendsGroup);
		
		FriendsGroup userGroup = new FriendsGroup();
		userGroup.setOnwerUser(user.getId());
		userGroup.setType((short) 0);
		FriendsGroup ug = friendsGroupMapper.selectOne(userGroup);
		
		UserFriends uf = new UserFriends();
		uf.setNotNull(null, user.getId(), friendUser.getId(), ug.getId());
		int cound = userFriendsMapper.insert(uf);
		
		UserFriends ff = new UserFriends();
		ff.setNotNull(null, friendUser.getId(), user.getId(), fg.getId());
		cound += userFriendsMapper.insert(ff);
		if (cound == 2) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 根据用户名获取用户
	 * @param username
	 * @return
	 */
	private UserInfo getUserByUsername(String username) {
		UserInfo record = new UserInfo();
		record.setUsername(username);
		return userInfoMapper.selectOne(record);
	}
}
