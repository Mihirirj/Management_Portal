package com.it.dashboard.issue.domain;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the SMC_STATUS database table.
 * 
 */
@Entity
@Table(name="SMC_STATUS")
@NamedQuery(name="SmcStatus.findAll", query="SELECT s FROM SmcStatus s")
public class SmcStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="STATUS_DESCRIPTION")
	private String statusDescription;
	
	@Id
	@Column(name="STATUS_NO")
	private String statusNo;

	@Column(name="STATUS_TYPE")
	private String statusType;

	public SmcStatus() {
	}

	public String getStatusDescription() {
		return this.statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	public String getStatusNo() {
		return this.statusNo;
	}

	public void setStatusNo(String statusNo) {
		this.statusNo = statusNo;
	}

	public String getStatusType() {
		return this.statusType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

}