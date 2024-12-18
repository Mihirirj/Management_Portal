package com.it.dashboard.clientService.domain;

public class PymntOfflineMthdObject {

	 public String pivNo; 
     public double paidAmount; 
     public String paymentMethod; 
     public String chequeNo; 
     public String creditCardNum;       
     public String chequeBank; 
     public String chequeBankBranch; 
     public String tranID;
	
     
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
	public String getCreditCardNum() {
		return creditCardNum;
	}
	public void setCreditCardNum(String creditCardNum) {
		this.creditCardNum = creditCardNum;
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
	public String getTranID() {
		return tranID;
	}
	public void setTranID(String tranID) {
		this.tranID = tranID;
	} 
     
     
}