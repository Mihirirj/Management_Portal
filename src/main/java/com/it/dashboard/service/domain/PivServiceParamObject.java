package com.it.dashboard.service.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.it.dashboard.util.common.KeyValueObject;

@SuppressWarnings("serial")
public class PivServiceParamObject implements Serializable {
	
	
	
	private String agentCode;
	private Date fromDate;
	private Date toDate;
	public String getAgentCode() {
		return agentCode;
	}
	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	
	
}
