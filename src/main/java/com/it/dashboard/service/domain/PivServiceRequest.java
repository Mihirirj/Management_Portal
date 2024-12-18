package com.it.dashboard.service.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the PIV_SERVICE_REQUEST database table.
 * 
 */
@Entity
@Table(name="PIV_SERVICE_REQUEST")
@NamedQuery(name="PivServiceRequest.findAll", query="SELECT p FROM PivServiceRequest p")
public class PivServiceRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PIV_SERVICE_REQ_GENERATOR")
    @SequenceGenerator(sequenceName = "PIV_SERVICE_REQ_SEQ", allocationSize = 1, name = "PIV_SERVICE_REQ_GENERATOR")
	@Column(name="SERIAL_NO")
	private long serialNo;

	@Column(name="CALLING_FROM")
	private String callingFrom;

	@Column(name="PIV_NO")
	private String pivNo;

	@Column(name="REQUEST_ID")
	private String requestId;

	@Column(name="REQUEST_TIME")
	private Timestamp requestTime;

	@Column(name="RESPONSE_CODE")
	private String responseCode;

	@Column(name="RESPONSE_MSG")
	private String responseMsg;

	@Column(name="RESPONSE_TIME")
	private Timestamp responseTime;

	@Column(name="SERVICE_TYPE")
	private String serviceType;

	@Column(name="THIRD_PARTY_REF_NO")
	private String thirdPartyRefNo;

	public PivServiceRequest() {
	}

	public long getSerialNo() {
		return this.serialNo;
	}

	public void setSerialNo(long serialNo) {
		this.serialNo = serialNo;
	}

	public String getCallingFrom() {
		return this.callingFrom;
	}

	public void setCallingFrom(String callingFrom) {
		this.callingFrom = callingFrom;
	}

	public String getPivNo() {
		return this.pivNo;
	}

	public void setPivNo(String pivNo) {
		this.pivNo = pivNo;
	}

	public String getRequestId() {
		return this.requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public Timestamp getRequestTime() {
		return this.requestTime;
	}

	public void setRequestTime(Timestamp requestTime) {
		this.requestTime = requestTime;
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

	public Timestamp getResponseTime() {
		return this.responseTime;
	}

	public void setResponseTime(Timestamp responseTime) {
		this.responseTime = responseTime;
	}

	public String getServiceType() {
		return this.serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getThirdPartyRefNo() {
		return this.thirdPartyRefNo;
	}

	public void setThirdPartyRefNo(String thirdPartyRefNo) {
		this.thirdPartyRefNo = thirdPartyRefNo;
	}

}