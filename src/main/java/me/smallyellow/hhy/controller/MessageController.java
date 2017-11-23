package me.smallyellow.hhy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import me.smallyellow.base.boot.web.bean.AjaxResult;
import me.smallyellow.base.boot.web.exception.WebException;
import me.smallyellow.hhy.model.Message;
import me.smallyellow.hhy.service.MessageService;

@Controller
public class MessageController {

	@Autowired
	private MessageService messageService;
	
	@RequestMapping(value = "/message/listTest", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult getList() {
		AjaxResult result = new AjaxResult();
		try{
			List<Message> list = messageService.listMessage();
			result.setResult(list);
			result.setCode(AjaxResult.SUCCESS);
		} catch (WebException e) {
			e.printStackTrace();
		}
		return result;
	}
}
