package com.zl.common.exception;

import java.io.Serializable;

/**
 * 自定义异常
 * @author coolz
 *
 */
public class ZlException extends Throwable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ZlException() {
		super();
	}

	public ZlException(String message) {
		super(message);
	}

	public ZlException(String message, Throwable cause) {
		super(message, cause);
	}

	public ZlException(Throwable cause) {
		super(cause);
	}

	protected ZlException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
