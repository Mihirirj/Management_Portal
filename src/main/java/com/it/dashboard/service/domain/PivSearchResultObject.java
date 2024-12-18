package com.it.dashboard.service.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.it.dashboard.util.common.KeyValueObject;

@SuppressWarnings("serial")
public class PivSearchResultObject implements Serializable {
	
	
	
	private String idNo;
	private String customerName;
	private String pivAmount;
	private String costCenter;
	private String referenceNo;
	private Date pivDate;
	private String pivNo;
	private String address;
	
	public PivSearchResultObject(){}
	
	public PivSearchResultObject(String costCenter,String pivNo, String idNo,
								String customerName,String address, String referenceNo,
								String pivAmount, Date pivDate  )
	{
		this.idNo = idNo;
		this.customerName = customerName;
		this.pivAmount = pivAmount;
		this.costCenter = costCenter;
		this.referenceNo = referenceNo;
		this.pivDate = pivDate;
		this.pivNo = pivNo;
		this.address = address;
		
	}
	
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	
	public String getPivAmount() {
		return pivAmount;
	}
	public void setPivAmount(String pivAmount) {
		this.pivAmount = pivAmount;
	}
	public String getCostCenter() {
		return costCenter;
	}
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}
	public String getReferenceNo() {
		return referenceNo;
	}
	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}
	public Date getPivDate() {
		return pivDate;
	}
	public void setPivDate(Date pivDate) {
		this.pivDate = pivDate;
	}
	public String getPivNo() {
		return pivNo;
	}
	public void setPivNo(String pivNo) {
		this.pivNo = pivNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
	
	
	
	
	
	
	

}
