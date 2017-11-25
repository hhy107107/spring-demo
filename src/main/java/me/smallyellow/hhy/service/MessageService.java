package me.smallyellow.hhy.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.smallyellow.base.boot.web.bean.Page;
import me.smallyellow.base.boot.web.exception.WebException;
import me.smallyellow.hhy.mapper.MessageMapper;
import me.smallyellow.hhy.model.Message;
import me.smallyellow.hhy.websocket.bean.TextMessageBean;
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
	public List<Message> listMessage(Long userId, Long toId, Long fromId, Long lastId, Integer size) throws WebException {
		if(userId.equals(toId) || userId.equals(fromId)){
			List<Message> list = messageMapper.selectMessage(toId, fromId, lastId, size);
			return list;
		} else {
			throw new WebException("没有权限查看该聊天记录");
		}
	}
	
	/**
	 * 插入一条聊天记录
	 * @param message
	 * @throws WebException
	 */
	public Long insertMessage(Message message) throws WebException{
		int count = messageMapper.insert(message);
		if(count <= 0) {
			throw new WebException("插入失败，请检查");
		}
		return message.getId();
	}
	
	/**
	 * 插入一条聊天记录
	 * @param message
	 * @throws WebException
	 */
	public Long insertMessage(TextMessageBean message) throws WebException{
		Message msg = new Message();
		msg.setNotNull(null, message.getTo(), message.getFrom(), message.getType().getValue(), new Date(), message.getStatus());
		msg.setMessage(message.getMessage());
		int count = messageMapper.insert(msg);
		if(count <= 0) {
			throw new WebException("插入失败，请检查");
		}
		return msg.getId();
	}
	
	/**
	 * 修改消息状态
	 * @param id
	 * @param status
	 * @throws WebException
	 */
	public void updateMessageStatus(Long id, Short status) throws WebException{
		Message msg = new Message();
		msg.setNotNull(id, null, null, null, null, status);
		messageMapper.updateByPrimaryKeySelective(msg);
	}
}
