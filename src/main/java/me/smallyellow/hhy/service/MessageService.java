package me.smallyellow.hhy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.smallyellow.base.boot.web.exception.WebException;
import me.smallyellow.hhy.mapper.MessageMapper;
import me.smallyellow.hhy.model.Message;

/**
 * 聊天消息
 * @author hhy
 * 2017年11月23日下午4:19:06
 */
@Service
public class MessageService {

	@Autowired
	private MessageMapper messageMapper;
	
	public List<Message> listMessage() {
		List<Message> list = messageMapper.selectAll();
		return list;
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
