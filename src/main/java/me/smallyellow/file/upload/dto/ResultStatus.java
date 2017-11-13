package me.smallyellow.file.upload.dto;

/**
 * 常量类
 * 操作状态默认值
 * @author hhy
 * 2017年11月13日上午10:25:07
 */
public class ResultStatus {
	public static final Short ERROR = -1; //操作失败，默认值
	public static final Short SUCCESS = 1; //操作成功
	public static final Short EXCEPTION = -2; //操作失败，报异常
	
	public static final Short FileNotFoundException = -3; //文件不存在
	public static final Short IOException = -4; //文件流错误
	
	public static String getMessage(Short status) {
		switch (status) {
		case -1:
			return "错误";
		case -2:
			return "操作失败";
		case -3:
			return "文件不存在";
		case -4:
			return "文件流错误";
		}
		return null;
	}

}
