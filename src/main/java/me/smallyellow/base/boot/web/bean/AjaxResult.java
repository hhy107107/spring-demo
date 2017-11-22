package me.smallyellow.base.boot.web.bean;

import java.io.Serializable;

/**
 * ajax操作返回信息
 */
public class AjaxResult implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final int UN_AUTH = -3;// 没有权限
	public static final int UN_LOGIN = -2;// 未登录
	public static final int ERROR = -1;// 返回异常
	public static final int FAILURE = 0;// 调用失败
	public static final int SUCCESS = 1;// 调用成功
	
	private int code;// 返回结果类型 ERROR、SUCCESS、FAILURE
	private String message;// 返回消息
	private Object result;// 返回数据

	public AjaxResult() {
		this.code = FAILURE;
	}
	
	public AjaxResult(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}