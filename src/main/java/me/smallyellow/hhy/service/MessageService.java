package me.smallyellow.hhy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.smallyellow.base.boot.web.bean.Page;
import me.smallyellow.base.boot.web.exception.WebException;
import me.smallyellow.hhy.mapper.MessageMapper;
import me.smallyellow.hhy.model.Message;
import tk.mybatis.mapper.entity.Example;

/**
 * 聊天消息
 * @author hhy
 * 2017年11月23日下午4:19:06
 */
@Service
public class MessageService {

	@Autowired
	private MessageMapper messageMapper;
	
	/**
	 * 获取聊天消息
	 * @param to 发送给谁的消息
	 * @param from 谁发送的消息
	 * @param lastId 最近一条消息编号[0表示获取最新消息
	 * @param size 消息条数[可选]
	 * @return
	 */
	public Page<Message> listMessage(Long userId, Long toId, Long fromId, Long lastId, Integer size) {
		List<Message> list = messageMapper.selectAll();
		Page<Message> page = new Page<>();
		page.setList(list);
		return page;
	}
	
	/**
	 * 插入一条聊天记录
	 * @param message
	 * @throws WebException
	 */
	public void insertMessage(Message message) throws WebException{
		int count = messageMapper.insert(message);
		if(count <= 0) {
			throw new WebException("插入失败，请检查");
		}
	}
}
