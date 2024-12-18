package com.it.dashboard.smc.model;

import java.util.Date;

public class SearchModel {
    private String idNo;
    private String estimateNo;
    private String accountNo;
    private String applicationNo;
    private String description2;
    private String description;
    private String projectNo;
    private String consumerName;
    private String consumerAddress;
    private Date submitDate;
    private String smcStatus;

    public SearchModel(String idNo, String estimateNo, String accountNo, String applicationNo, 
                       String description2, String description, String projectNo, 
                       String consumerName, String consumerAddress, Date submitDate, String smcStatus) {
        this.idNo = idNo;
        this.estimateNo = estimateNo;
        this.accountNo = accountNo;
        this.applicationNo = applicationNo;
        this.description2 = description2;
        this.description = description;
        this.projectNo = projectNo;
        this.consumerName = consumerName;
        this.consumerAddress = consumerAddress;
        this.submitDate = submitDate;
        this.smcStatus = smcStatus;
    }

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getEstimateNo() {
		return estimateNo;
	}

	public void setEstimateNo(String estimateNo) {
		this.estimateNo = estimateNo;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getApplicationNo() {
		return applicationNo;
	}

	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}

	public String getDescription2() {
		return description2;
	}

	public void setDescription2(String description2) {
		this.description2 = description2;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	public String getConsumerName() {
		return consumerName;
	}

	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}

	public String getConsumerAddress() {
		return consumerAddress;
	}

	public void setConsumerAddress(String consumerAddress) {
		this.consumerAddress = consumerAddress;
	}

	public Date getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	public String getSmcStatus() {
		return smcStatus;
	}

	public void setSmcStatus(String smcStatus) {
		this.smcStatus = smcStatus;
	}

	

	
}