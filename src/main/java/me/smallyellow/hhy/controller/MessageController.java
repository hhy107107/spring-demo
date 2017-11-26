package me.smallyellow.hhy.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import me.smallyellow.base.boot.web.bean.AjaxResult;
import me.smallyellow.base.boot.web.bean.Page;
import me.smallyellow.base.boot.web.exception.WebException;
import me.smallyellow.hhy.constant.CommonConst;
import me.smallyellow.hhy.model.Message;
import me.smallyellow.hhy.model.UserInfo;
import me.smallyellow.hhy.service.MessageService;

/**
 * 消息控制层
 * @author hhy
 * 2017年11月24日
 */
@Controller
public class MessageController {

	@Autowired
	private MessageService messageService;
	
	/**
	 * 获取聊天消息
	 * @param request
	 * @param to 发送给谁的消息
	 * @param from 谁发送的消息
	 * @param lastId 最近一条消息编号[0表示获取最新消息
	 * @param size 消息条数[可选]
	 * @return
	 */
	@RequestMapping(value = "/message/chatList", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult getList(HttpServletRequest request,
			@RequestParam("to") Long to,
			@RequestParam("from") Long from,
			@RequestParam(value = "lastId", required = false) Long lastId,
			@RequestParam(value = "size", required = false) Integer size) {
		AjaxResult result = new AjaxResult();
		try{
			UserInfo user = (UserInfo) request.getSession().getAttribute(CommonConst.USER);
			List<Message> list = messageService.listMessage(user.getId(), to, from, lastId, size);
			result.setResult(list);
			result.setCode(AjaxResult.SUCCESS);
		} catch (WebException e) {
			e.printStackTrace();
		}
		return result;
	}
}
