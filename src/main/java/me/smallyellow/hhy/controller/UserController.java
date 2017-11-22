package me.smallyellow.hhy.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import me.smallyellow.base.boot.web.bean.AjaxResult;
import me.smallyellow.base.boot.web.exception.WebException;
import me.smallyellow.hhy.model.UserInfo;
import me.smallyellow.hhy.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 登录
	 * @param request
	 * @param response
	 * @param model
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/user/login", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult index(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam("username") String username,
			@RequestParam("password") String password){
		AjaxResult result = new AjaxResult();
		try{
			UserInfo user = userService.login(username, password);
			request.getSession().setAttribute("user", user);
			result.setCode(AjaxResult.SUCCESS);
			result.setResult(user);
		} catch (WebException e){
			result.setCode(AjaxResult.ERROR);
			result.setMessage(e.getMessage());
		}
		return result;
	}
}
