package me.smallyellow.hhy.controller;

import java.io.IOException;

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
import me.smallyellow.base.core.lang.DateUtils;
import me.smallyellow.hhy.constant.CommonConst;
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
	@RequestMapping(value = "/common/login", method = RequestMethod.GET)
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
			e.printStackTrace();
			result.setCode(AjaxResult.ERROR);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 退出登录
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/common/logout", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult index(HttpServletRequest request, HttpServletResponse response, Model model){
		AjaxResult result = new AjaxResult();
		try{
			request.getSession().setAttribute("user", null);
			result.setCode(AjaxResult.SUCCESS);
		} catch (WebException e){
			e.printStackTrace();
			result.setCode(AjaxResult.ERROR);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 注册
	 * @param request
	 * @param response
	 * @param model
	 * @param username
	 * @param password
	 * @param email
	 * @return
	 */
	@RequestMapping(value = "/common/register", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult register(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam("email") String email){
		AjaxResult result = new AjaxResult();
		try{
			UserInfo user = new UserInfo();
			user.setNotNull(null, username, password, username, "1", email, (short)4, (short)2);
			userService.insertUser(user);
			result.setCode(AjaxResult.SUCCESS);
		} catch (WebException e){
			e.printStackTrace();
			result.setCode(AjaxResult.ERROR);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 激活用户
	 * @param request
	 * @param response
	 * @param model
	 * @param accessToken
	 * @return
	 */
	@RequestMapping(value = "/common/activate", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult activate(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam("accessToken") String accessToken){
		AjaxResult result = new AjaxResult();
		try{
			userService.activateUser(accessToken);
			try {
				response.sendRedirect("http://localhost:8080/");
			} catch (IOException e) {
				e.printStackTrace();
			}
			result.setCode(AjaxResult.SUCCESS);
		} catch (WebException e){
			e.printStackTrace();
			result.setCode(AjaxResult.ERROR);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 修改用户
	 * @param request
	 * @param name 昵称
	 * @param address 地址
	 * @param birthday 生日 yyyy-MM-dd
	 * @param sex 性别 1-男 2-女
	 * @param reason 来小黄平台的原因 1-崇拜小黄 2-崇拜统子 3-想了解bjhj 4-被小黄无形之中的气质所吸引
	 * @param signature 个性签名
	 * @param userface 头像路径
	 * @return
	 */
	@RequestMapping(value = "/user/changeUser", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult changeUser(HttpServletRequest request,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "address", required = false) String address,
			@RequestParam(value = "birthday", required = false) String birthday,
			@RequestParam(value = "sex", required = false) Short sex,
			@RequestParam(value = "reason", required = false) Short reason,
			@RequestParam(value = "signature", required = false) String signature,
			@RequestParam(value = "userface", required = false) String userface){
		AjaxResult result = new AjaxResult();
		try{
			UserInfo user = (UserInfo) request.getSession().getAttribute(CommonConst.USER);
			UserInfo info = new UserInfo();
			info.setNotNull(user.getId(), null, null, name, null, null, reason, null);
			info.setAddress(address);
			info.setBirthday(DateUtils.stringToDate(birthday, 1));
			info.setSex(sex);
			info.setSignature(signature);
			info.setUserface(userface);
			userService.updateUser(info);
			request.getSession().setAttribute("user", info);
			result.setCode(AjaxResult.SUCCESS);
		} catch (WebException e){
			e.printStackTrace();
			result.setCode(AjaxResult.ERROR);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 修改用户密码
	 * @param request
	 * @param newPwd
	 * @param oldPwd
	 * @return
	 */
	@RequestMapping(value = "/user/changeUserPwd", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult changeUserPwd(HttpServletRequest request,
			@RequestParam(value = "newPwd", required = false) String newPwd,
			@RequestParam(value = "oldPwd", required = false) String oldPwd){
		AjaxResult result = new AjaxResult();
		try{
			UserInfo user = (UserInfo) request.getSession().getAttribute(CommonConst.USER);
			UserInfo info = new UserInfo();
			info.setNotNull(user.getId(), null, newPwd, null, null, null, null, null);
			userService.updateUserPwd(info, oldPwd);
			result.setCode(AjaxResult.SUCCESS);
		} catch (WebException e){
			e.printStackTrace();
			result.setCode(AjaxResult.ERROR);
			result.setMessage(e.getMessage());
		}
		return result;
	}
}
