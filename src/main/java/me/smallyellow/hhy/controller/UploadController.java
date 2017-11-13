package me.smallyellow.hhy.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import me.smallyellow.base.boot.web.WebPathProperties;
import me.smallyellow.base.boot.web.bean.AjaxResult;
import me.smallyellow.file.upload.UploadUtils;
import me.smallyellow.file.upload.dto.UploadResult;
import me.smallyellow.hhy.constant.UploadClassifyConst;

/**
 * 文件上传
 * @author hhy
 * 2017年11月13日上午10:52:19
 */
@Controller
public class UploadController {
	
	@Autowired
	private WebPathProperties webPathProperties;
	
	@RequestMapping(value="/upload", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult test(HttpServletRequest request, Model model,
			@RequestParam(value = "file") MultipartFile file){ 
		AjaxResult result = new AjaxResult();
		try {
			UploadResult fileResult = UploadUtils.uploadFile(file.getInputStream(), webPathProperties.getDownloadUrl(),
					webPathProperties.getFileDir(), UploadClassifyConst.TEST, file.getOriginalFilename());
			System.out.println("结果："+fileResult.getStatus()+" "+fileResult.getFilePath()+" "+fileResult.getFileUrl());
			result.setCode(fileResult.getStatus());
			result.setResult(fileResult);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}