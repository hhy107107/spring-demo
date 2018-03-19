/*
 * 文件名称：MessageMapper.java
 */
package me.smallyellow.hhy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import me.smallyellow.base.boot.mybatis.bean.MyMapper;
import me.smallyellow.hhy.model.Message;

/**
 * 名称：
 * 模块描述：数据库表对应dao操作类
 * 作者：系统自动生成
 */
public interface MessageMapper extends MyMapper<Message> {
	
	/**
	 * 查询两个用户的聊天记录
	 * @param toId 给谁发的
	 * @param fromId 谁接收的
	 * @param lastId 最近的聊天记录编号
	 * @return
	 */
	public List<Message> selectMessage(@Param("userOne") Long userOne, @Param("userTwo") Long userTwo, 
			@Param("lastId") Long lastId, @Param("size") Integer size);
	
}