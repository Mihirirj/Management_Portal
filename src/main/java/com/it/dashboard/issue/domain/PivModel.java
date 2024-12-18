package com.it.dashboard.issue.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.validator.constraints.NotEmpty;

import com.it.dashboard.admin.domain.PivActivityGrid;
import com.it.dashboard.master.domain.PivActivity;
import com.it.dashboard.admin.domain.PivHistory;
import com.it.dashboard.master.domain.Bank;
import com.it.dashboard.master.domain.BankBranch;
import com.it.dashboard.master.domain.KeyValue;
import com.it.dashboard.payment.domain.PivBankTran;
import com.it.dashboard.payment.domain.PivPayment;
import com.it.dashboard.payment.domain.PivPaymentGrid;
import com.it.dashboard.refund.domain.PivRefund;


@SuppressWarnings("serial")
public class PivModel implements Serializable {
	private PivDetail pivDetail;
	private PivApplicant pivApplicant;
	private PivPayment pivPayment;
	private PivHistory pivHistory;
	private PivApproval pivApproval;
	private PivActivity pivActivity;
	
	Map<String,String> pivTypeList;
	Map<String,String> bankCodeList;
	private List<PivAmountGrid> amountList;
	private List<PivHistory> historyList;
	Map<String,String> deptIdList;
	private String statusDesc;
	
	public String getPivNoFind() {
		return pivNoFind;
	}
	public void setPivNoFind(String pivNoFind) {
		this.pivNoFind = pivNoFind;
	}
	private String printCurrency;
	private String pivNoFind;
	//private List<KeyValue> bankList;
	//private List<KeyValue> bankBranchList;
	//private List<KeyValue> agentList;
	Map<String,String> agentBranchList;
	Map<String,String> agentList;
	Map<String,String> bankList;
	Map<String,String> bankBranchList;
	
	private Bank Bank;
	private BankBranch bankBranch;
	private String pivTotal;
	
	private String mode;
	private String isMultipleDeptId;
	
	private List<String> pivNoList;
	
	@NotEmpty
	private String message;
	private String messageType;
	
	private List<PivActivityGrid> activityGridList;
	
	private String paymentMode;
	private BigDecimal paidAmount;
	private Date chequeDate;
	private String chequeBank;
	private String chequeBranch;
	private String chequeNo;
	private List<PivPaymentGrid> chequePivLst;
	
	private List<String> chequeNoLst;
	//private List<PivPaymentGrid> paymentList;
	private List<PivPayment> paymentList;
	private Date issuedFrom;
	private Date issuedTo;
	private Date paidFrom;
	private Date paidTo;
	/******** edit by pasindu ********/
	private String returnCheque;
	private String returnChequeNo;
	/*********************************/
	//private String total;
	
	public List<PivActivityGrid> getActivityGridList() {
		return activityGridList;
	}
	public void setActivityGridList(List<PivActivityGrid> activityGridList) {
		this.activityGridList = activityGridList;
	}
	public PivApproval getPivApproval() {
		return pivApproval;
	}
	public void setPivApproval(PivApproval pivApproval) {
		this.pivApproval = pivApproval;
	}
	
	public PivActivity getPivActivity() {
		return pivActivity;
	}
	public void setPivActivity(PivActivity pivActivity) {
		this.pivActivity = pivActivity;
	}
	public BankBranch getBankBranch() {
		return bankBranch;
	}
	public void setBankBranch(BankBranch bankBranch) {
		this.bankBranch = bankBranch;
	}
	
	
	
	
	public Bank getBank() {
		return Bank;
	}
	public void setBank(Bank bank) {
		Bank = bank;
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	public Map<String, String> getBankCodelist() {
		return selectbankcodeList;
	}
	public void setBankCodelist(Map<String, String> selectbankcodeList) {
		this.selectbankcodeList = selectbankcodeList;
	}
	Map<String,String> selectbankcodeList;
	
	public Map<String, String> getBankCodeList() {
		return bankCodeList;
	}
	public void setBankCodeList(Map<String, String> bankCodeList) {
		this.bankCodeList = bankCodeList;
	}
	///////////////////////////////////////////////////////////////////////////////////////////
	public Map<String, String> getSelectPivList() {
		return selectPivList;
	}
	public void setSelectPivList(Map<String, String> selectPivList) {
		this.selectPivList = selectPivList;
	}
	Map<String,String> selectPivList;
	//private String pivDetail;
	private PivRefund pivRefund;
	
	public Map<String, String> getPivTypeList() {
		return pivTypeList;
	}
	public void setPivTypeList(Map<String, String> pivTypeList) {
		this.pivTypeList = pivTypeList;
	}
	public PivHistory getPivHistory() {
		return pivHistory;
	}
	public void setPivHistory(PivHistory pivHistory) {
		this.pivHistory = pivHistory;
	}
	
	public PivDetail getPivDetail() {
		return pivDetail;
	}
	public void setPivDetail(PivDetail pivDetail) {
		this.pivDetail = pivDetail;
	}
	public PivApplicant getPivApplicant() {
		return pivApplicant;
	}
	public void setPivApplicant(PivApplicant pivApplicant) {
		this.pivApplicant = pivApplicant;
	}
	public List<PivAmountGrid> getAmountList() {
		return amountList;
	}
	public void setAmountList(List<PivAmountGrid> amountList) {
		this.amountList = amountList;
	}
	public List<PivHistory> getHistoryList(){
		return historyList;
	}
	public void setHistoryList(List<PivHistory> historyList){
		this.historyList=historyList;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public List<String> getPivNoList() {
		return pivNoList;
	}
	public void setPivNoList(List<String> pivNoList) {
		this.pivNoList = pivNoList;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	
	/*public List<PivAmountGrid> getContacts() {
		return contacts;
	}
	public void setContacts(List<PivAmountGrid> contacts) {
		this.contacts = contacts;
	}*/
	
	public PivRefund getPivRefund() {	//NA
		return pivRefund;
	}
	public void setPivRefund(PivRefund pivRefund) {	//NA
		this.pivRefund = pivRefund;
	}

	public String getPrintCurrency() {
		return printCurrency;
	}
	public void setPrintCurrency(String printCurrency) {
		this.printCurrency = printCurrency;
	}
	public PivPayment getPivPayment() {
		return pivPayment;
	}
	public void setPivPayment(PivPayment pivPayment) {
		this.pivPayment = pivPayment;
	}
	
	/*public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}*/
	public String getPivTotal() {
		return pivTotal;
	}
	public void setPivTotal(String pivTotal) {
		this.pivTotal = pivTotal;
	}
	public String getChequeNo() {
		return chequeNo;
	}
	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}
	public List<PivPaymentGrid> getChequePivLst() {
		return chequePivLst;
	}
	public void setChequePivLst(List<PivPaymentGrid> chequePivLst) {
		this.chequePivLst = chequePivLst;
	}
	public List<String> getChequeNoLst() {
		return chequeNoLst;
	}
	public void setChequeNoLst(List<String> chequeNoLst) {
		this.chequeNoLst = chequeNoLst;
	}
	
	public Map<String, String> getBankBranchList() {
		return bankBranchList;
	}
	public void setBankBranchList(Map<String, String> bankBranchList) {
		this.bankBranchList = bankBranchList;
	}
	public Map<String, String> getBankList() {
		return bankList;
	}
	public void setBankList(Map<String, String> bankList) {
		this.bankList = bankList;
	}
	
	public Map<String, String> getAgentBranchList() {
		return agentBranchList;
	}
	public void setAgentBranchList(Map<String, String> agentBranchList) {
		this.agentBranchList = agentBranchList;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	
	public BigDecimal getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(BigDecimal paidAmount) {
		this.paidAmount = paidAmount;
		if(this.paidAmount!=null)
		{
			this.paidAmount = this.paidAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
	}
	public Date getChequeDate() {
		return chequeDate;
	}
	public void setChequeDate(Date chequeDate) {
		this.chequeDate = chequeDate;
	}
	public String getChequeBank() {
		return chequeBank;
	}
	public void setChequeBank(String chequeBank) {
		this.chequeBank = chequeBank;
	}
	public String getChequeBranch() {
		return chequeBranch;
	}
	public void setChequeBranch(String chequeBranch) {
		this.chequeBranch = chequeBranch;
	}
	/*public List<PivPaymentGrid> getPaymentList() {
		return paymentList;
	}
	public void setPaymentList(List<PivPaymentGrid> paymentList) {
		this.paymentList = paymentList;
	}*/
	
	public Map<String, String> getAgentList() {
		return agentList;
	}
	public List<PivPayment> getPaymentList() {
		return paymentList;
	}
	public void setPaymentList(List<PivPayment> paymentList) {
		this.paymentList = paymentList;
	}
	public void setAgentList(Map<String, String> agentList) {
		this.agentList = agentList;
	}
	public Date getIssuedFrom() {
		return issuedFrom;
	}
	public void setIssuedFrom(Date issuedFrom) {
		this.issuedFrom = issuedFrom;
	}
	public Date getIssuedTo() {
		return issuedTo;
	}
	public void setIssuedTo(Date issuedTo) {
		this.issuedTo = issuedTo;
	}
	public Date getPaidFrom() {
		return paidFrom;
	}
	public void setPaidFrom(Date paidFrom) {
		this.paidFrom = paidFrom;
	}
	public Date getPaidTo() {
		return paidTo;
	}
	public void setPaidTo(Date paidTo) {
		this.paidTo = paidTo;
	}
	public String getReturnCheque() {
		return returnCheque;
	}
	public void setReturnCheque(String returnCheque) {
		this.returnCheque = returnCheque;
	}
	public String getReturnChequeNo() {
		return returnChequeNo;
	}
	public void setReturnChequeNo(String returnChequeNo) {
		this.returnChequeNo = returnChequeNo;
	}
	public Map<String, String> getDeptIdList() {
		return deptIdList;
	}
	public void setDeptIdList(Map<String, String> deptIdList) {
		this.deptIdList = deptIdList;
	}
	public String getIsMultipleDeptId() {
		return isMultipleDeptId;
	}
	public void setIsMultipleDeptId(String isMultipleDeptId) {
		this.isMultipleDeptId = isMultipleDeptId;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	
	
	
	

}
