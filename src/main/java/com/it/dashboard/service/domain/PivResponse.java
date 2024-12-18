package com.it.dashboard.service.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

@SuppressWarnings("serial")
public class PivResponse implements Serializable  {

	private String applicationNo;
	private String responseCode;
	private String responseMsg;
	private PivObject pivObject;
	
	public String getApplicationNo() {
		return applicationNo;
	}
	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseMsg() {
		return responseMsg;
	}
	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}
	public PivObject getPivObject() {
		return pivObject;
	}
	public void setPivObject(PivObject pivObject) {
		this.pivObject = pivObject;
	}
	
	
	
	
	
	
	
	

}
