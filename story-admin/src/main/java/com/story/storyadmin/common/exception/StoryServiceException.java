package com.story.storyadmin.common.exception;

/**
 * 统一Rest API异常类 继承Exception受检查异常
 * 
 * @author 
 *
 */
public class StoryServiceException extends Exception {

	private static final long serialVersionUID = -7795831940099270963L;

	/**
	 * 构造函数
	 */
	public StoryServiceException() {
		super();
	}

	/**
	 * 构造函数 重载
	 * 
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public StoryServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * 构造函数
	 * 
	 * @param message
	 * @param cause
	 */
	public StoryServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 构造函数
	 * 
	 * @param message
	 */
	public StoryServiceException(String message) {
		super(message);
	}

	/**
	 * 构造函数
	 * 
	 * @param cause
	 */
	public StoryServiceException(Throwable cause) {
		super(cause);
	}

}
