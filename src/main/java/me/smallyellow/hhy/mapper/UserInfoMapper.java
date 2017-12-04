/*
 * 文件名称：UserInfoMapper.java
 */
package me.smallyellow.hhy.mapper;

import org.apache.ibatis.annotations.Param;

import me.smallyellow.base.boot.mybatis.bean.MyMapper;
import me.smallyellow.hhy.model.UserInfo;
import me.smallyellow.hhy.model.dto.UserInfoDTO;

/**
 * 名称：
 * 模块描述：数据库表对应dao操作类
 * 作者：系统自动生成
 */
public interface UserInfoMapper extends MyMapper<UserInfo> {
	
	/**
	 * 获取用户信息，包括博客等级
	 * @param id
	 * @return
	 */
	UserInfoDTO selectUserInfoWithGrade(@Param("id") Long id);

	/**
	 * 查找用户是否存在
	 * @param username
	 * @param email
	 * @return
	 */
	UserInfo selectUserByusernameOrEmail(@Param("username") String username, @Param("email") String email);
}