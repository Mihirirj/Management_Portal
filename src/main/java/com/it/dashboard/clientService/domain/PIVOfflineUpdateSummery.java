package com.it.dashboard.clientService.domain;

import java.util.List;

public class PIVOfflineUpdateSummery {

	public String agentCode;
    public String agentBranchCode;
    public String paidDeptId;
    public String pivNo;     
    public double paidTotalAmount;
    public String paidDate;

    public List<PymntOfflineMthdObject> paymentMethodObjArray;

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

	public String getPaidDeptId() {
		return paidDeptId;
	}

	public void setPaidDeptId(String paidDeptId) {
		this.paidDeptId = paidDeptId;
	}

	public String getPivNo() {
		return pivNo;
	}

	public void setPivNo(String pivNo) {
		this.pivNo = pivNo;
	}

	public double getPaidTotalAmount() {
		return paidTotalAmount;
	}

	public void setPaidTotalAmount(double paidTotalAmount) {
		this.paidTotalAmount = paidTotalAmount;
	}

	public String getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(String paidDate) {
		this.paidDate = paidDate;
	}

	public List<PymntOfflineMthdObject> getPaymentMethodObjArray() {
		return paymentMethodObjArray;
	}

	public void setPaymentMethodObjArray(
			List<PymntOfflineMthdObject> paymentMethodObjArray) {
		this.paymentMethodObjArray = paymentMethodObjArray;
	}
     
     
}