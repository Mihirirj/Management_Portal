package com.it.dashboard.payment.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PaymentMethodObject implements Serializable  {

	private String pivNo;
	private double paidAmount;
	private String paymentMethod;
	private String chequeNo;
	private String chequeDate;
	private String chequeBank;
	private String chequeBankBranch;
	
	public String getPivNo() {
		return pivNo;
	}
	public void setPivNo(String pivNo) {
		this.pivNo = pivNo;
	}
	public double getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getChequeNo() {
		return chequeNo;
	}
	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}
	public String getChequeDate() {
		return chequeDate;
	}
	public void setChequeDate(String chequeDate) {
		this.chequeDate = chequeDate;
	}
	public String getChequeBank() {
		return chequeBank;
	}
	public void setChequeBank(String chequeBank) {
		this.chequeBank = chequeBank;
	}
	public String getChequeBankBranch() {
		return chequeBankBranch;
	}
	public void setChequeBankBranch(String chequeBankBranch) {
		this.chequeBankBranch = chequeBankBranch;
	}
	
	
	
	
	
	
	
}
