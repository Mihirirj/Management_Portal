package com.it.dashboard.service.domain;

import java.io.Serializable;


@SuppressWarnings("serial")
public class PivRequest implements Serializable  {

	private String requestId;
	private String requestDate;
	private String callingFrom;
	private ApplicationObject applicationObject;
	
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}
	public String getCallingFrom() {
		return callingFrom;
	}
	public void setCallingFrom(String callingFrom) {
		this.callingFrom = callingFrom;
	}
	public ApplicationObject getApplicationObject() {
		return applicationObject;
	}
	public void setApplicationObject(ApplicationObject applicationObject) {
		this.applicationObject = applicationObject;
	}
	
	
	

	
	

}
