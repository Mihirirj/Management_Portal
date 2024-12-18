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
public class PivDetailObject implements Serializable {
	
	
	
	
	private String pivNo;
	private double pivTotal;
	private String costCenter;
	private String amountInWords;
	private String pivReceiptNo;
	private String referenceNo;
	private Date pivDate;
	private String currencyCode;
	private Date expiryDate;
	private String returnCode;
	private String returnValue;
	
	private PivApplicantObject pivApplicantObject;
	private List<PivAmountGrid> pivAmountList;
	
	public PivDetailObject()
	{
		
	}
	
	public String getPivNo() {
		return pivNo;
	}
	public void setPivNo(String pivNo) {
		this.pivNo = pivNo;
	}
	public double getPivTotal() {
		return pivTotal;
	}
	public void setPivTotal(double pivTotal) {
		this.pivTotal = pivTotal;
	}
	public String getCostCenter() {
		return costCenter;
	}
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}
	public String getAmountInWords() {
		return amountInWords;
	}
	public void setAmountInWords(String amountInWords) {
		this.amountInWords = amountInWords;
	}
	public String getPivReceiptNo() {
		return pivReceiptNo;
	}
	public void setPivReceiptNo(String pivReceiptNo) {
		this.pivReceiptNo = pivReceiptNo;
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
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public PivApplicantObject getPivApplicantObject() {
		return pivApplicantObject;
	}
	public void setPivApplicantObject(PivApplicantObject pivApplicantObject) {
		this.pivApplicantObject = pivApplicantObject;
	}
	public List<PivAmountGrid> getPivAmountList() {
		return pivAmountList;
	}
	public void setPivAmountList(List<PivAmountGrid> pivAmountList) {
		this.pivAmountList = pivAmountList;
	}
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public String getReturnValue() {
		return returnValue;
	}
	public void setReturnValue(String returnValue) {
		this.returnValue = returnValue;
	}
	
	
	
	
	

}
