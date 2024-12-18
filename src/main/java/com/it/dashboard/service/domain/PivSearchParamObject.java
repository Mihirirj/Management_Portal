package com.it.dashboard.service.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.it.dashboard.util.common.KeyValueObject;

@SuppressWarnings("serial")
public class PivSearchParamObject implements Serializable {
	
	
	
	private String idNo;
	private String customerName;
	private BigDecimal pivAmount;
	private String costCenter;
	private String referenceNo;
	private Date pivDate;
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
	
	public BigDecimal getPivAmount() {
		return pivAmount;
	}
	public void setPivAmount(BigDecimal pivAmount) {
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
	
	
	
	
	
	
	
	
	
	

}
