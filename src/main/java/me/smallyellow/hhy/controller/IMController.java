package me.smallyellow.hhy.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import me.smallyellow.base.boot.web.bean.AjaxResult;
import me.smallyellow.base.boot.web.exception.WebException;
import me.smallyellow.hhy.constant.CommonConst;
import me.smallyellow.hhy.im.service.FriendsService;
import me.smallyellow.hhy.model.UserInfo;

@Controller
public class IMController {
	
	@Autowired
	private FriendsService friendsService;
	
	@RequestMapping(value = "/im/addFriend", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult changeUser(HttpServletRequest request,
			@RequestParam("frienduname") String frienduname){
		AjaxResult result = new AjaxResult();
		try{
			UserInfo user = (UserInfo) request.getSession().getAttribute(CommonConst.USER);
			//friendsService.requestFriend(frienduname);
			result.setCode(AjaxResult.SUCCESS);
		} catch (WebException e){
			e.printStackTrace();
			result.setCode(AjaxResult.ERROR);
			result.setMessage(e.getMessage());
		}
		return result;
	}
}
