package com.it.dashboard.admin.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

@SuppressWarnings("serial")
public class PivDashboardGrid implements Serializable {
	private String pivNo;
	private String applicantName;
	private String applicantId;
	private Date pivDate;
	private BigDecimal pivAmount;
	private String status;
	private Date paidTime;
	
	public PivDashboardGrid(){
		
	}
	
	public PivDashboardGrid(String pivNo, String applicantName, String applicantId, String status, Date pivDate, BigDecimal pivAmount)
	{
		this.pivNo=pivNo;
		this.applicantName=applicantName;
		this.applicantId=applicantId;
		this.pivDate=pivDate;
		this.pivAmount=pivAmount;
		this.status=status;
	}
	
	public PivDashboardGrid(String pivNo, String applicantName, String applicantId, String status, 
			Date pivDate, BigDecimal pivAmount, Date paidTime)
	{
		this.pivNo=pivNo;
		this.applicantName=applicantName;
		this.applicantId=applicantId;
		this.pivDate=pivDate;
		this.pivAmount=pivAmount;
		this.status=status;
		this.paidTime = paidTime;
	}
	
	
	
	
	public String getPivNo() {
		return pivNo;
	}

	public void setPivNo(String pivNo) {
		this.pivNo = pivNo;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public String getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(String applicantId) {
		this.applicantId = applicantId;
	}

	public Date getPivDate() {
		return pivDate;
	}

	public void setPivDate(Date pivDate) {
		this.pivDate = pivDate;
	}

	public BigDecimal getPivAmount() {
		return pivAmount;
	}

	public void setPivAmount(BigDecimal pivAmount) {
		this.pivAmount = pivAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getPaidTime() {
		return paidTime;
	}

	public void setPaidTime(Date paidTime) {
		this.paidTime = paidTime;
	}

	
	/*@Override
	public String toString() {
		return "PivAmountGrid [acCd=" + acCd + ", acNm=" + acNm + ", amount=" + amount +", isTax=" + isTax+", taxValue=" + taxValue+"]";
	}*/

	
	
	
	
	
}
