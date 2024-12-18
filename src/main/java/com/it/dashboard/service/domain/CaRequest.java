package com.it.dashboard.service.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the CA_REQUEST database table.
 * 
 */
@Entity
@Table(name="CA_REQUEST")
@NamedQuery(name="CaRequest.findAll", query="SELECT c FROM CaRequest c")
public class CaRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="REQUEST_ID")
	private String requestId;

	@Column(name="APPLICATION_NO")
	private String applicationNo;

	@Column(name="CA_APPLICATION_NO")
	private String caApplicationNo;

	@Column(name="REQUEST_DATE")
	private Timestamp requestDate;

	@Column(name="RESPONSE_CODE")
	private String responseCode;

	@Column(name="RESPONSE_MSG")
	private String responseMsg;

	public CaRequest() {
	}

	public String getRequestId() {
		return this.requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getApplicationNo() {
		return this.applicationNo;
	}

	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}

	public String getCaApplicationNo() {
		return this.caApplicationNo;
	}

	public void setCaApplicationNo(String caApplicationNo) {
		this.caApplicationNo = caApplicationNo;
	}

	public Timestamp getRequestDate() {
		return this.requestDate;
	}

	public void setRequestDate(Timestamp requestDate) {
		this.requestDate = requestDate;
	}

	public String getResponseCode() {
		return this.responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMsg() {
		return this.responseMsg;
	}

	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}

}