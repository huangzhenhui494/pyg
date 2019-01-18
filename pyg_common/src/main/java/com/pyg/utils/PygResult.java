package com.pyg.utils;

import java.io.Serializable;

/**
 * @author huangzhenhui
 * @date 2019-01-04 20:11
 */
public class PygResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean success;
	private String message;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public PygResult(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}
	
	
}
