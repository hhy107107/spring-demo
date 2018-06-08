package me.smallyellow.hhy.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import me.smallyellow.base.boot.web.bean.AjaxResult;
import me.smallyellow.base.boot.web.bean.Page;
import me.smallyellow.hhy.constant.CommonConst;
import me.smallyellow.hhy.model.Note;
import me.smallyellow.hhy.model.UserInfo;
import me.smallyellow.hhy.model.dto.NoteDTO;
import me.smallyellow.hhy.service.NoteService;

/**
 * 文件控制层
 * @author hhy
 * 2017年11月27日上午10:00:50
 */
@Controller
public class FileController {
	
	@RequestMapping(value = "/file/uploadFolder", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult uploadFolder(HttpServletRequest request) {
		AjaxResult result = new AjaxResult();
		try {
			UserInfo user = (UserInfo) request.getSession().getAttribute(CommonConst.USER);
			MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
			MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
			//MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);
			List<MultipartFile> files = multipartRequest.getFiles("fileFolder"); 
			for (MultipartFile multipartFile : files) {
				CommonsMultipartFile f = (CommonsMultipartFile)multipartFile;
				String name = f.getFileItem().getName();
				System.out.println(name);
			}
			result.setCode(AjaxResult.SUCCESS);
			result.setResult(files);
		} catch (Exception e){
			e.printStackTrace();
			result.setCode(AjaxResult.ERROR);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
}
