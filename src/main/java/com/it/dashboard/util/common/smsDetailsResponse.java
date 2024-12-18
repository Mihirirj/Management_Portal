package com.it.dashboard.util.common;

import java.io.Serializable;

public class smsDetailsResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Exception ex;
	private boolean success ;
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public Exception getEx() {
		return ex;
	}
	public void setEx(Exception ex) {
		this.ex = ex;
	}
	
	
	
	

}
