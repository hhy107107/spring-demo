package me.smallyellow.hhy.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import me.smallyellow.base.boot.web.bean.AjaxResult;
import me.smallyellow.base.boot.web.exception.WebException;
import me.smallyellow.hhy.constant.CommonConst;
import me.smallyellow.hhy.model.NoteType;
import me.smallyellow.hhy.model.UserInfo;
import me.smallyellow.hhy.service.NoteTypeService;

/**
 * 笔记类型控制层
 * @author hhy
 * 2017年11月26日上午10:52:45
 */
@Controller
public class NoteTypeController {
	
	@Autowired
	private NoteTypeService noteTypeService;

	/**
	 * 获取笔记列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/note/typeList", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult typeList(HttpServletRequest request){
		AjaxResult result = new AjaxResult();
		UserInfo user = (UserInfo) request.getSession().getAttribute(CommonConst.USER);
		List<NoteType> list = noteTypeService.selectNoteTypeList(user.getId());
		result.setCode(AjaxResult.SUCCESS);
		result.setResult(list);
		return result;
	}
	
	/**
	 * 增加类型
	 * @param request
	 * @param name
	 * @param description
	 * @return
	 */
	@RequestMapping(value = "/note/typeAdd", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult typeAdd(HttpServletRequest request,
			@RequestParam("name") String name,
			@RequestParam(value = "description", required = false) String description){
		AjaxResult result = new AjaxResult();
		UserInfo user = (UserInfo) request.getSession().getAttribute(CommonConst.USER);
		NoteType noteType = new NoteType();
		noteType.setNotNull(null, name, user.getId());
		noteType.setDescription(description);
		try {
			noteTypeService.insertNoteType(noteType);
			result.setCode(AjaxResult.SUCCESS);
		} catch (WebException e) {
			result.setCode(AjaxResult.ERROR);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value = "/note/typeDel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult typeDel(HttpServletRequest request,
			@RequestParam("id") Long id){
		AjaxResult result = new AjaxResult();
		UserInfo user = (UserInfo) request.getSession().getAttribute(CommonConst.USER);
		NoteType noteType = new NoteType();
		noteType.setNotNull(null, name, user.getId());
		noteType.setDescription(description);
		try {
			noteTypeService.insertNoteType(noteType);
			result.setCode(AjaxResult.SUCCESS);
		} catch (WebException e) {
			result.setCode(AjaxResult.ERROR);
			result.setMessage(e.getMessage());
		}
		return result;
	}
}
