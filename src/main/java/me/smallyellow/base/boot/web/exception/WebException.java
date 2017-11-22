package me.smallyellow.base.boot.web.exception;

/**
 * 异常
 * @author hhy
 * 2017年11月22日下午1:27:55
 */
public class WebException extends RuntimeException{
	private static final long serialVersionUID = 7704885346643981734L;
	
	public WebException(String message) {
		super(message);
	}
	
}
