package com.it.dashboard.issue.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.validator.constraints.NotEmpty;

import com.it.dashboard.admin.domain.PivActivityGrid;
import com.it.dashboard.master.domain.PivActivity;
import com.it.dashboard.admin.domain.PivHistory;
import com.it.dashboard.master.domain.Bank;
import com.it.dashboard.master.domain.BankBranch;
import com.it.dashboard.payment.domain.PivPayment;
import com.it.dashboard.refund.domain.PivRefund;


@SuppressWarnings("serial")
public class ChequePivModel implements Serializable {
	private PivDetail pivDetail;
	private PivApplicant pivApplicant;
	private PivPayment pivPayment;
	Map<String,String> pivTypeList;
	Map<String,String> bankCodeList;
	private List<PivAmountGrid> amountList;
	private List<PivHistory> historyList;
	//private List<Bank> bankList;
	//private List<String> bblist;
	private Map<String,String> bankBranchList;
	private PivHistory pivHistory;
	private String printCurrency;
	private Bank Bank;
	private BankBranch bankBranch;
	Map<String,String> bankList;
	private String pivTotal;
	
	private String mode;
	private List<String> pivNoList;
	
	@NotEmpty
	private String message;
	private String messageType;
	private PivApproval pivApproval;
	private PivActivity pivActivity;
	private List<PivActivityGrid> activityGridList;
	
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
	
	
	
	public Map<String, String> getBankBranchList() {
		return bankBranchList;
	}
	public void setBankBranchList(Map<String, String> bankBranchList) {
		this.bankBranchList = bankBranchList;
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
	public Map<String, String> getBankList() {
		return bankList;
	}
	public void setBankList(Map<String, String> bankList) {
		this.bankList = bankList;
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
	
	

}
