package com.it.dashboard.service.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.it.dashboard.util.common.KeyValueObject;

@SuppressWarnings("serial")
public class StampDutyObject implements Serializable {
	
	
	
	private Date paidDate;
	private String txCount;
	private String pivTotal;
	private String stampDuty;
	
	
	public StampDutyObject(Date paidDate,String txCount, String pivTotal,
			String stampDuty )
	{
		this.stampDuty = stampDuty;
		this.paidDate = paidDate;
		this.txCount = txCount;
		this.pivTotal = pivTotal;
		this.stampDuty = stampDuty;
	}
	
	public Date getPaidDate() {
		return paidDate;
	}
	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}

	public String getTxCount() {
		return txCount;
	}

	public void setTxCount(String txCount) {
		this.txCount = txCount;
	}

	public String getPivTotal() {
		return pivTotal;
	}

	public void setPivTotal(String pivTotal) {
		this.pivTotal = pivTotal;
	}

	public String getStampDuty() {
		return stampDuty;
	}

	public void setStampDuty(String stampDuty) {
		this.stampDuty = stampDuty;
	}
	
	
	
	
}
