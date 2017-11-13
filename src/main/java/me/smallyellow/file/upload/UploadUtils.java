package me.smallyellow.file.upload;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import me.smallyellow.base.core.lang.DateUtils;
import me.smallyellow.base.core.lang.FileUtis;
import me.smallyellow.file.upload.dto.ResultStatus;
import me.smallyellow.file.upload.dto.UploadResult;

public class UploadUtils {
	
	/**
	 * 上传文件
	 * @param inputStream 文件流
	 * @param fileDir 文件保存子路径
	 * @param classify 文件分类名
	 * @param fileName 文件名
	 * @return
	 */
	public static UploadResult uploadFile(InputStream inputStream, String fileUrl, String fileDir, String classify, String fileName){
		UploadResult result = new UploadResult();
		String ext = fileName.substring(fileName.lastIndexOf(".")); //文件后缀
		String subPath = classify + File.separator + DateUtils.dateToString(new Date(), 1);
		String realUploadPath = fileDir + subPath;
		try {
			String saveFileName = FileUtis.saveFile(inputStream, realUploadPath, null, ext);
			String filePath = subPath + File.separator + saveFileName;
			result.setStatus(ResultStatus.SUCCESS);
			result.setFilePath(filePath);
			result.setFileUrl(fileUrl + File.separator + filePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			result.setMessage(ResultStatus.getMessage(ResultStatus.FileNotFoundException));
		} catch (IOException e) {
			e.printStackTrace();
			result.setMessage(ResultStatus.getMessage(ResultStatus.IOException));
		}
		return result;
	}
}
