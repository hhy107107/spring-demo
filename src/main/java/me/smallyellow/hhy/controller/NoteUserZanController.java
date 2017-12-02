package me.smallyellow.hhy.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import me.smallyellow.base.boot.web.bean.AjaxResult;
import me.smallyellow.hhy.constant.CommonConst;
import me.smallyellow.hhy.model.NoteUserZan;
import me.smallyellow.hhy.model.UserInfo;
import me.smallyellow.hhy.service.NoteUserZanService;

@Controller
public class NoteUserZanController {

	@Autowired
	private NoteUserZanService noteUserZanService;
	
	/**
	 * 获取赞
	 * @param request
	 * @param noteId 笔记id
	 * @return
	 */
	@RequestMapping(value="/note/getZan", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult getZan(HttpServletRequest request,
			@RequestParam("noteId") Long noteId) {
		AjaxResult result = new AjaxResult();
		UserInfo user = (UserInfo) request.getSession().getAttribute(CommonConst.USER);
		try {
			NoteUserZan zan = noteUserZanService.getNoteZanByNoteIdAndUserId(noteId, user.getId());
			result.setCode(AjaxResult.SUCCESS);
			result.setResult(zan);
		} catch(Exception e) {
			result.setCode(AjaxResult.ERROR);
			result.setMessage("服务器错误");
		}
		return result;
	}
	
	/**
	 * 修改赞
	 * @param request
	 * @param noteId 笔记id
	 * @param zan 1-赞  2-不赞
	 * @return
	 */
	@RequestMapping(value="/note/changeZan", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult getZan(HttpServletRequest request,
			@RequestParam("noteId") Long noteId,
			@RequestParam("zan") Short zan) {
		AjaxResult result = new AjaxResult();
		UserInfo user = (UserInfo) request.getSession().getAttribute(CommonConst.USER);
		try {
			noteUserZanService.updateNoteZan(noteId, user.getId(), zan);
			result.setCode(AjaxResult.SUCCESS);
		} catch(Exception e) {
			result.setCode(AjaxResult.ERROR);
			result.setMessage("服务器错误");
		}
		return result;
	}
}
