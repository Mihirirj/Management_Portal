package com.it.dashboard.payment.domain;

import java.io.Serializable;

import java.util.List;



@SuppressWarnings("serial")
public class PaymentObject implements Serializable  {

	private String agentCode;
	private String agentBranchCode;
	private String paidDeptId;
	private String pivNo;
	//@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy HH:mm:ss")
	private String paidDate;
	private String transDate;
	private String transId;
	private double paidTotalAmount;
	//private PaymentMethodObject[] paymentMethodObjArray;
	private List<PaymentMethodObject> paymentMethodObjArray;
	
	/*public PaymentMethodObject[] getPaymentMethodObjArray() {
		return paymentMethodObjArray;
	}
	public void setPaymentMethodObjArray(PaymentMethodObject[] paymentMethodObjArray) {
		this.paymentMethodObjArray = paymentMethodObjArray;
	}*/
	private String[] string ;
	
	public String getAgentCode() {
		return agentCode;
	}
	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
	public String getAgentBranchCode() {
		return agentBranchCode;
	}
	public void setAgentBranchCode(String agentBranchCode) {
		this.agentBranchCode = agentBranchCode;
	}
	public String getPivNo() {
		return pivNo;
	}
	public void setPivNo(String pivNo) {
		this.pivNo = pivNo;
	}
	public String getPaidDate() {
		return paidDate;
	}
	public void setPaidDate(String paidDate) {
		this.paidDate = paidDate;
	}
	public String getTransId() {
		return transId;
	}
	public void setTransId(String transId) {
		this.transId = transId;
	}
	public double getPaidTotalAmount() {
		return paidTotalAmount;
	}
	public void setPaidTotalAmount(double paidTotalAmount) {
		this.paidTotalAmount = paidTotalAmount;
	}
	public List<PaymentMethodObject> getPaymentMethodObjArray() {
		return paymentMethodObjArray;
	}
	public void setPaymentMethodObjArray(
			List<PaymentMethodObject> paymentMethodObjArray) {
		this.paymentMethodObjArray = paymentMethodObjArray;
	}
	public String[] getString() {
		return string;
	}
	public void setString(String[] string) {
		this.string = string;
	}
	public String getTransDate() {
		return transDate;
	}
	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}
	public String getPaidDeptId() {
		return paidDeptId;
	}
	public void setPaidDeptId(String paidDeptId) {
		this.paidDeptId = paidDeptId;
	}
	
	
	
	
	
}
