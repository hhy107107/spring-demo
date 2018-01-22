package me.smallyellow.hhy.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import me.smallyellow.base.boot.web.bean.AjaxResult;
import me.smallyellow.base.boot.web.exception.WebException;
import me.smallyellow.base.core.utils.validate.ValidateCode;
import me.smallyellow.hhy.model.UserInfo;
import me.smallyellow.hhy.service.UserService;

/**
 * 基础功能
 * @author hhy
 * 2017年12月7日下午2:14:48
 */
@Controller
public class CommonController {
	
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
			@RequestParam("password") String password,
			@RequestParam("code") String code){
		AjaxResult result = new AjaxResult();
		try{
			HttpSession session = request.getSession();  
	        String sessionCode = (String) session.getAttribute("code");
			if (code.toUpperCase().equals(sessionCode)) {
				UserInfo user = userService.login(username, password);
				request.getSession().setAttribute("user", user);
				result.setCode(AjaxResult.SUCCESS);
				result.setResult(user);
			} else {
				throw new WebException("验证码错误");
			}
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
	 * 获取验证码
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/common/getCode", method = RequestMethod.GET)
	@ResponseBody
	public void getCode(HttpServletRequest request, HttpServletResponse response){
        // 设置响应的类型格式为图片格式  
        response.setContentType("image/jpeg");  
        //禁止图像缓存。  
        response.setHeader("Pragma", "no-cache");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setDateHeader("Expires", 0);  

        HttpSession session = request.getSession();  

        ValidateCode vCode = new ValidateCode(120,40,4,100);  
        session.setAttribute("code", vCode.getCode());  
        try {
			vCode.write(response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}
}
