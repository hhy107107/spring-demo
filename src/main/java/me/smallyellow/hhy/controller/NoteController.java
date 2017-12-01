package me.smallyellow.hhy.controller;

import java.util.Date;
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
import me.smallyellow.base.boot.web.bean.Page;
import me.smallyellow.hhy.constant.CommonConst;
import me.smallyellow.hhy.model.Note;
import me.smallyellow.hhy.model.UserInfo;
import me.smallyellow.hhy.model.dto.NoteDTO;
import me.smallyellow.hhy.service.NoteService;

/**
 * 文章控制层
 * @author hhy
 * 2017年11月27日上午10:00:50
 */
@Controller
public class NoteController {
	
	@Autowired
	private NoteService noteService;
	
	/**
	 * 文章列表
	 * @param request
	 * @param pageNo
	 * @param pageSize
	 * @param typeId
	 * @return
	 */
	@RequestMapping(value = "/note/listNote", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult listNote(HttpServletRequest request,
			@RequestParam("pageNo") Integer pageNo,
			@RequestParam("pageSize") Integer pageSize,
			@RequestParam(value = "typeId", required = false) Long typeId) {
		AjaxResult result = new AjaxResult();
		try {
			UserInfo user = (UserInfo) request.getSession().getAttribute(CommonConst.USER);
			Page<Note> list = noteService.selectNoteList(user.getId(), typeId, pageNo, pageSize);
			result.setCode(AjaxResult.SUCCESS);
			result.setResult(list);
		} catch (Exception e){
			e.printStackTrace();
			result.setCode(AjaxResult.ERROR);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 文章详情
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/note/noteDetail", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult noteDetail(HttpServletRequest request,
			@RequestParam("id") Long id) {
		AjaxResult result = new AjaxResult();
		try {
			UserInfo user = (UserInfo) request.getSession().getAttribute(CommonConst.USER);
			NoteDTO list = noteService.selectNoteDetail(user.getId(), id);
			result.setCode(AjaxResult.SUCCESS);
			result.setResult(list);
		} catch (Exception e){
			e.printStackTrace();
			result.setCode(AjaxResult.ERROR);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 添加笔记
	 * @param request
	 * @param typeId
	 * @param title
	 * @return
	 */
	@RequestMapping(value = "/note/addNote", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult addNote(HttpServletRequest request,
			@RequestParam("typeId") Long typeId,
			@RequestParam("title") String title,
			@RequestParam("isMarkdown") Short isMarkdown,
			@RequestParam(value = "content", required = false) String content) {
		AjaxResult result = new AjaxResult();
		try {
			UserInfo user = (UserInfo) request.getSession().getAttribute(CommonConst.USER);
			Note note = new Note();
			note.setNotNull(null, typeId, user.getName(), user.getId(), title, new Date(), isMarkdown);
			note.setContent(content);
			noteService.insertNote(note);
			result.setCode(AjaxResult.SUCCESS);
		} catch (Exception e){
			e.printStackTrace();
			result.setCode(AjaxResult.ERROR);
			result.setMessage(e.getMessage());
		}
		return result;
	}
}
