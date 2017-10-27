package me.smallyellow.base.core.bean;

/**
 * 关于异常的工具类.
 * 参考了guava的Throwables。
 */
public class UtilsException extends RuntimeException {

	private static final long serialVersionUID = 622783926923852753L;

	public UtilsException(Throwable e) {
		super(e.getMessage(), e);
	}
	
	public UtilsException(String message) {
		super(message);
	}
	
	public UtilsException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
}
