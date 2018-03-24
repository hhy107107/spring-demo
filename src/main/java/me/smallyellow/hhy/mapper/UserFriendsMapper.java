/*
 * 文件名称：UserFriendsMapper.java
 */
package me.smallyellow.hhy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import me.smallyellow.base.boot.mybatis.bean.MyMapper;
import me.smallyellow.hhy.model.UserFriends;
import me.smallyellow.hhy.model.dto.UserFriendsDTO;

/**
 * 名称：
 * 模块描述：数据库表对应dao操作类
 * 作者：系统自动生成
 */
public interface UserFriendsMapper extends MyMapper<UserFriends> {

	/**
	 * 获取好友列表
	 * @param userId 用户id
	 * @return
	 */
	List<UserFriendsDTO> listFriendsWithGroup(@Param("userId") Long userId);
	
}