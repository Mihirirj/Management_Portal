package com.it.dashboard.service.domain;

import java.io.Serializable;

import org.exolab.castor.types.DateTime;



public class CAPaymentUpdateRequest implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String PivNo;
	private DateTime PivPaymentDate;
	private String PaymentLocation;
	public String getPivNo() {
		return PivNo;
	}
	public void setPivNo(String pivNo) {
		PivNo = pivNo;
	}
	
	
	public DateTime getPivPaymentDate() {
		return PivPaymentDate;
	}
	public void setPivPaymentDate(DateTime pivPaymentDate) {
		PivPaymentDate = pivPaymentDate;
	}
	public String getPaymentLocation() {
		return PaymentLocation;
	}
	public void setPaymentLocation(String paymentLocation) {
		PaymentLocation = paymentLocation;
	}
	
	
	
	
	
	

}
