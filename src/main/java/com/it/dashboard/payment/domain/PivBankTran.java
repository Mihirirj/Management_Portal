package com.it.dashboard.payment.domain;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the PIV_BANK_TRANS database table.
 * 
 */
@Entity
@Table(name="PIV_BANK_TRANS")
@NamedQuery(name="PivBankTran.findAll", query="SELECT p FROM PivBankTran p")
public class PivBankTran implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SeqGenTrans")
	@SequenceGenerator(name = "SeqGenTrans", sequenceName = "PIV_BANK_TRANS_SEQ", allocationSize = 1)
	@Column(name="TRANS_LOG_ID")
	private long transLogId;

	private String activity;

	@Column(name="ADD_DATE")
	private Timestamp addDate;

	@Column(name="ADD_USER")
	private String addUser;

	private BigDecimal amount;

	@Column(name="BANK_CODE")
	private String bankCode;

	@Column(name="BRANCH_CODE")
	private String branchCode;

	@Column(name="PAYMENT_DATE")
	private Timestamp paymentDate;

	@Column(name="PIV_CHECK_NO")
	private String pivCheckNo;

	@Column(name="RETURN_CODE")
	private String returnCode;

	@Column(name="RETURN_MSG")
	private String returnMsg;

	private String status1;

	private String status2;

	@Column(name="TRANS_DATE")
	private Timestamp transDate;

	@Column(name="TRANS_ID")
	private String transId;

	@Temporal(TemporalType.DATE)
	@Column(name="UPDT_TIME")
	private Date updtTime;

	public PivBankTran() {
	}

	public long getTransLogId() {
		return this.transLogId;
	}

	public void setTransLogId(long transLogId) {
		this.transLogId = transLogId;
	}

	public String getActivity() {
		return this.activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public Timestamp getAddDate() {
		return this.addDate;
	}

	public void setAddDate(Timestamp addDate) {
		this.addDate = addDate;
	}

	public String getAddUser() {
		return this.addUser;
	}

	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getBankCode() {
		return this.bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBranchCode() {
		return this.branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public Timestamp getPaymentDate() {
		return this.paymentDate;
	}

	public void setPaymentDate(Timestamp paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getPivCheckNo() {
		return this.pivCheckNo;
	}

	public void setPivCheckNo(String pivCheckNo) {
		this.pivCheckNo = pivCheckNo;
	}

	public String getReturnCode() {
		return this.returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMsg() {
		return this.returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	public String getStatus1() {
		return this.status1;
	}

	public void setStatus1(String status1) {
		this.status1 = status1;
	}

	public String getStatus2() {
		return this.status2;
	}

	public void setStatus2(String status2) {
		this.status2 = status2;
	}

	public Timestamp getTransDate() {
		return this.transDate;
	}

	public void setTransDate(Timestamp transDate) {
		this.transDate = transDate;
	}

	public String getTransId() {
		return this.transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}

	public Date getUpdtTime() {
		return this.updtTime;
	}

	public void setUpdtTime(Date updtTime) {
		this.updtTime = updtTime;
	}

}