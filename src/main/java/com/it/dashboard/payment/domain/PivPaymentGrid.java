package com.it.dashboard.payment.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

@SuppressWarnings("serial")
public class PivPaymentGrid implements Serializable {
	private String deptId;
	private String pivNo;
	private BigDecimal pivAmount;
	private BigDecimal payAmount;
	private String bankName;
	private String branchName;
	private Date chequeDate;
	private String chequeNo;
	private String paymentMode;
	private long seqNo;
	private String paidAmount;
	
	public PivPaymentGrid(){
		
	}
	
	public PivPaymentGrid(String deptId, String pivNo, BigDecimal pivAmount,BigDecimal payAmount,String bankName, String branchName,Date chequeDate,String chequeNo)
	{
		this.deptId = deptId;
		this.pivNo = pivNo;
		this.pivAmount = pivAmount;
		this.payAmount = payAmount;
		this.bankName = bankName;
		this.branchName = branchName;
		this.chequeDate = chequeDate;
		this.chequeNo = chequeNo;
	}
	
	public PivPaymentGrid(long seqNo, String paidAmount,String paymentMode,String bankName, String branchName, Date chequeDate,String chequeNo )
	{
		this.seqNo = seqNo;
		this.paidAmount = paidAmount;
		this.paymentMode = paymentMode;
		this.bankName = bankName;
		this.branchName = branchName;
		this.chequeDate = chequeDate;
		this.chequeNo = chequeNo;
	}
	
	public PivPaymentGrid(long seqNo, String paidAmount,String paymentMode, Date chequeDate,String chequeNo )
	{
		this.seqNo = seqNo;
		this.paidAmount = paidAmount;
		this.paymentMode = paymentMode;
		this.chequeDate = chequeDate;
		this.chequeNo = chequeNo;
	}
	
	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getPivNo() {
		return pivNo;
	}

	public void setPivNo(String pivNo) {
		this.pivNo = pivNo;
	}

	

	public BigDecimal getPivAmount() {
		return pivAmount;
	}

	public void setPivAmount(BigDecimal pivAmount) {
		this.pivAmount = pivAmount;
	}

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public Date getChequeDate() {
		return chequeDate;
	}

	public void setChequeDate(Date chequeDate) {
		this.chequeDate = chequeDate;
	}

	public String getChequeNo() {
		return chequeNo;
	}

	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}

	@Override
	public String toString() {
		return "PivAmountGrid [deptId=" + deptId + ", pivNo=" + pivNo + ", pivAmount=" + pivAmount +", payAmount=" + payAmount+", bankName=" + bankName+", branchName=" + branchName+", chequeDate=" + chequeDate+", chequeNo=" + chequeNo+"]";
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public long getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(long seqNo) {
		this.seqNo = seqNo;
	}

	public String getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(String paidAmount) {
		this.paidAmount = paidAmount;
	}

	

	

	

	
	
	
	
}
