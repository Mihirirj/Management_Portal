package com.it.dashboard.service.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

@SuppressWarnings("serial")
public class ApplicationObject implements Serializable  {

	private String caApplicationNo;
	private String applicationDate;
	private String firstName;
	private String lastName;
	private String idType;
	private String id;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String telephone;
	private String mobile;
	private String email;
	private String serviceAddressLine1;
	private String serviceAddressLine2;
	private String serviceAddressLine3;
	private String csc;
	private String applicationType;
	private String existingAccountNo;
	private String neighborAccountNo;
	private String phase;
	private String amperage;
	private String connectioType;
	private String customerVatNo;
	private String pivReferenceType;
	private String areaId;
	private String cscId;
	
	
	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getAddressLine3() {
		return addressLine3;
	}

	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getServiceAddressLine1() {
		return serviceAddressLine1;
	}

	public void setServiceAddressLine1(String serviceAddressLine1) {
		this.serviceAddressLine1 = serviceAddressLine1;
	}

	public String getServiceAddressLine2() {
		return serviceAddressLine2;
	}

	public void setServiceAddressLine2(String serviceAddressLine2) {
		this.serviceAddressLine2 = serviceAddressLine2;
	}

	public String getServiceAddressLine3() {
		return serviceAddressLine3;
	}

	public void setServiceAddressLine3(String serviceAddressLine3) {
		this.serviceAddressLine3 = serviceAddressLine3;
	}

	public String getCsc() {
		return csc;
	}

	public void setCsc(String csc) {
		this.csc = csc;
	}

	public String getApplicationType() {
		return applicationType;
	}

	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}

	public String getNeighborAccountNo() {
		return neighborAccountNo;
	}

	public void setNeighborAccountNo(String neighborAccountNo) {
		this.neighborAccountNo = neighborAccountNo;
	}

	
	

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public String getConnectioType() {
		return connectioType;
	}

	public void setConnectioType(String connectioType) {
		this.connectioType = connectioType;
	}

	public String getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
	}

	public String getExistingAccountNo() {
		return existingAccountNo;
	}

	public void setExistingAccountNo(String existingAccountNo) {
		this.existingAccountNo = existingAccountNo;
	}

	public String getCaApplicationNo() {
		return caApplicationNo;
	}

	public void setCaApplicationNo(String caApplicationNo) {
		this.caApplicationNo = caApplicationNo;
	}

	

	public String getAmperage() {
		return amperage;
	}

	public void setAmperage(String amperage) {
		this.amperage = amperage;
	}

	public String getCustomerVatNo() {
		return customerVatNo;
	}

	public void setCustomerVatNo(String customerVatNo) {
		this.customerVatNo = customerVatNo;
	}

	public String getPivReferenceType() {
		return pivReferenceType;
	}

	public void setPivReferenceType(String pivReferenceType) {
		this.pivReferenceType = pivReferenceType;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getCscId() {
		return cscId;
	}

	public void setCscId(String cscId) {
		this.cscId = cscId;
	}
	

}
