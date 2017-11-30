package me.smallyellow.hhy.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import me.smallyellow.base.boot.web.bean.AjaxResult;
import me.smallyellow.base.boot.web.bean.VueStaticInfo;
import me.smallyellow.base.boot.web.bean.VueStaticUtils;
import me.smallyellow.hhy.constant.CommonConst;
import me.smallyellow.hhy.model.App;
import me.smallyellow.hhy.model.UserInfo;
import me.smallyellow.hhy.service.TestService;
import me.smallyellow.hhy.service.UserService;

@Controller
public class HomeController {
	

	@Autowired 
	private TestService testService;
	
	@Autowired
	private UserService userService;
	
	private final static VueStaticInfo vue = new VueStaticInfo();
	
	
	
	/**
	 * 首页
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(HttpServletRequest request, HttpServletResponse response, Model model){
		if(!vue.isInit()){
			//VueStaticUtils.init(webPathProperties.getStaticUrl()+"/smallyellow/init.json", vue);
			VueStaticUtils.init("http://127.0.0.1:9999/static/init.json", vue);
		}
		model.addAttribute("vue", vue);
		return "index";
	}
	
	/**
	 * 获取当前登录用户信息
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/initData", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult initData(HttpServletRequest request, HttpServletResponse response, Model model){
		AjaxResult result = new AjaxResult();
		UserInfo user = (UserInfo) request.getSession().getAttribute(CommonConst.USER);
		if (user != null) {
			UserInfo info = userService.getUser(user.getId());
			result.setCode(AjaxResult.SUCCESS);
			result.setResult(info);
		} else {
			result.setCode(AjaxResult.UN_LOGIN);
		}
		return result;
	}
	
	@RequestMapping(value = "/ws", method = RequestMethod.GET)
	public String ws(HttpServletRequest request, HttpServletResponse response, Model model){
		return "ws2";
	}

	
	@RequestMapping(value="/test", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult test(HttpServletRequest request, Model model){ 
		AjaxResult result = new AjaxResult();
		List<UserInfo> list = testService.test();
		result.setCode(AjaxResult.SUCCESS);
		result.setResult(list);
		return result;
	}
	
	@RequestMapping(value="/test2", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult test2(HttpServletRequest request, Model model){ 
		AjaxResult result = new AjaxResult();
		List<App> list = testService.test2();
		result.setCode(AjaxResult.SUCCESS);
		result.setResult(list);
		return result;
	}
}
