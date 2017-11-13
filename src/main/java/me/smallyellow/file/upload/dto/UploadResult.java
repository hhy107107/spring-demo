package me.smallyellow.file.upload.dto;

import java.io.Serializable;

/**
 * 上传状态
 * @author hhy
 * 2017年11月13日上午9:35:18
 */
public class UploadResult implements Serializable{
	private static final long serialVersionUID = -7604481165728916079L;
	
	private Short status = ResultStatus.ERROR; //状态
	private String message = ResultStatus.getMessage(status);
	private String fileUrl; //文件访问地址
	private String filePath; //文件相对路径
	
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "UploadResult [status=" + status + ", message=" + message + ", fileUrl=" + fileUrl + ", filePath="
				+ filePath + "]";
	}
	
}
