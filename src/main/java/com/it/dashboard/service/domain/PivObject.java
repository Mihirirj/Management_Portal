package com.it.dashboard.service.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

@SuppressWarnings("serial")
public class PivObject implements Serializable  {

	private String pivNo;
	private String bankRefNo;
	private String pivAmount;
	private String pivCreatedDate;
	private String pivType;
	
	public String getPivNo() {
		return pivNo;
	}
	public void setPivNo(String pivNo) {
		this.pivNo = pivNo;
	}
	public String getBankRefNo() {
		return bankRefNo;
	}
	public void setBankRefNo(String bankRefNo) {
		this.bankRefNo = bankRefNo;
	}
	public String getPivAmount() {
		return pivAmount;
	}
	public void setPivAmount(String pivAmount) {
		this.pivAmount = pivAmount;
	}
	public String getPivCreatedDate() {
		return pivCreatedDate;
	}
	public void setPivCreatedDate(String pivCreatedDate) {
		this.pivCreatedDate = pivCreatedDate;
	}
	public String getPivType() {
		return pivType;
	}
	public void setPivType(String pivType) {
		this.pivType = pivType;
	}
	
	
	
	
	
	
	
	
	

}
