package com.it.dashboard.service.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

@SuppressWarnings("serial")
public class CAPayUpdateRequest implements Serializable  {

	private String PivNo;
	private String PivPaymentDate;
	private String PaymentLocation;
	public String getPivNo() {
		return PivNo;
	}
	public void setPivNo(String pivNo) {
		PivNo = pivNo;
	}
	public String getPivPaymentDate() {
		return PivPaymentDate;
	}
	public void setPivPaymentDate(String pivPaymentDate) {
		PivPaymentDate = pivPaymentDate;
	}
	public String getPaymentLocation() {
		return PaymentLocation;
	}
	public void setPaymentLocation(String paymentLocation) {
		PaymentLocation = paymentLocation;
	}
	
	
	
	
	
	

}
