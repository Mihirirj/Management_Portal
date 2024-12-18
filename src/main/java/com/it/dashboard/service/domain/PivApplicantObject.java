package com.it.dashboard.service.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.validator.constraints.NotEmpty;

import com.it.dashboard.admin.domain.PivActivityGrid;
import com.it.dashboard.issue.domain.PivAmountGrid;
import com.it.dashboard.issue.domain.PivApplicant;
import com.it.dashboard.issue.domain.PivApproval;
import com.it.dashboard.issue.domain.PivDetail;
import com.it.dashboard.master.domain.PivActivity;
import com.it.dashboard.admin.domain.PivHistory;
import com.it.dashboard.master.domain.Bank;
import com.it.dashboard.master.domain.BankBranch;
import com.it.dashboard.payment.domain.PivPayment;
import com.it.dashboard.refund.domain.PivRefund;


@SuppressWarnings("serial")
public class PivApplicantObject implements Serializable {
	
	
	
	
	private String idNo;
	private String epfNo;
	private String name;
	private String address;
	private String telephoneNo;
	private String vatRegNo;
	private String email;
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getEpfNo() {
		return epfNo;
	}
	public void setEpfNo(String epfNo) {
		this.epfNo = epfNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelephoneNo() {
		return telephoneNo;
	}
	public void setTelephoneNo(String telephoneNo) {
		this.telephoneNo = telephoneNo;
	}
	public String getVatRegNo() {
		return vatRegNo;
	}
	public void setVatRegNo(String vatRegNo) {
		this.vatRegNo = vatRegNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
	
	
	

}
