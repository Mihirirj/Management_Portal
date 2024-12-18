package com.it.dashboard.payment.domain;

import java.io.Serializable;

import javax.persistence.*;

import com.it.dashboard.issue.domain.PivDetail;
import com.it.dashboard.issue.domain.PivDetailPK;
import com.it.dashboard.master.domain.Agent;
import com.it.dashboard.master.domain.Bank;
import com.it.dashboard.master.domain.BankBranch;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the PIV_PAYMENT database table.
 * 
 */
@Entity
@Table(name="PIV_PAYMENT")
public class PivPayment implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PivPaymentPK id;

	@Temporal(TemporalType.DATE)
	@Column(name="ADD_DATE")
	private Date addDate;

	@Column(name="ADD_TIME", length=11)
	private String addTime;

	@Column(name="ADD_USER", length=10)
	private String addUser;

	@Column(name="CHEQUE_BANK_BRANCH", length=4)
	private String chequeBankBranch;

	@Column(name="CHEQUE_BANK_CODE", length=4)
	private String chequeBankCode;

	@Temporal(TemporalType.DATE)
	@Column(name="CHEQUE_DATE")
	private Date chequeDate;

	@Column(name="CHEQUE_NO", length=10)
	private String chequeNo;

	@Column(name="PAID_AMOUNT", nullable=false, precision=20, scale=2)
	private BigDecimal paidAmount;

	@Column(name="PAYMENT_MODE", nullable=false, length=1)
	private String paymentMode;

	@Column(length=150)
	private String remark;
	
	@Column(length=1)
	private String status;

	

	@Temporal(TemporalType.DATE)
	@Column(name="UPD_DATE")
	private Date updDate;

	@Column(name="UPD_TIME", length=11)
	private String updTime;

	@Column(name="UPD_USER", length=10)
	private String updUser;

	//bi-directional one-to-one association to PivDetail
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumns({
		@JoinColumn(name="DEPT_ID", referencedColumnName="DEPT_ID", nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="PIV_NO", referencedColumnName="PIV_NO", nullable=false, insertable=false, updatable=false)
		})
	private PivDetail pivDetail;

	//bi-directional many-to-one association to Agent
	/*@ManyToOne
	@JoinColumn(name="CHEQUE_BANK_CODE")
	private Bank bank;
	
	//bi-directional many-to-one association to Agent
	@ManyToOne
	@JoinColumn(name="CHEQUE_BANK_BRANCH")
	private BankBranch bankBranch;*/
		
	/*@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumns({
		@JoinColumn(name="CHEQUE_BANK_CODE", referencedColumnName="BANK_CODE", nullable=true, insertable=true, updatable=true),
		@JoinColumn(name="CHEQUE_BANK_BRANCH", referencedColumnName="BRANCH_CODE", nullable=true, insertable=true, updatable=true)
		})
	private BankBranch bankBranch;*/
	
	public PivPayment() {
	}

	

	public PivPaymentPK getId() {
		return id;
	}



	public void setId(PivPaymentPK id) {
		this.id = id;
	}



	public Date getAddDate() {
		return this.addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public String getAddTime() {
		return this.addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getAddUser() {
		return this.addUser;
	}

	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}

	public String getChequeBankBranch() {
		return this.chequeBankBranch;
	}

	public void setChequeBankBranch(String chequeBankBranch) {
		this.chequeBankBranch = chequeBankBranch;
	}

	public String getChequeBankCode() {
		return this.chequeBankCode;
	}

	public void setChequeBankCode(String chequeBankCode) {
		this.chequeBankCode = chequeBankCode;
	}

	/*public Bank getBank() {
		return bank;
	}



	public void setBank(Bank bank) {
		this.bank = bank;
	}*/



	public Date getChequeDate() {
		return this.chequeDate;
	}

	public void setChequeDate(Date chequeDate) {
		this.chequeDate = chequeDate;
	}

	public String getChequeNo() {
		return this.chequeNo;
	}

	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}

	public BigDecimal getPaidAmount() {
		return this.paidAmount;
	}

	public void setPaidAmount(BigDecimal paidAmount) {
		this.paidAmount = paidAmount;
	}

	public String getPaymentMode() {
		return this.paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	

	public Date getUpdDate() {
		return this.updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	public String getUpdTime() {
		return this.updTime;
	}

	public void setUpdTime(String updTime) {
		this.updTime = updTime;
	}

	public String getUpdUser() {
		return this.updUser;
	}

	public void setUpdUser(String updUser) {
		this.updUser = updUser;
	}

	public PivDetail getPivDetail() {
		return this.pivDetail;
	}

	public void setPivDetail(PivDetail pivDetail) {
		this.pivDetail = pivDetail;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}



	/*public BankBranch getBankBranch() {
		return bankBranch;
	}



	public void setBankBranch(BankBranch bankBranch) {
		this.bankBranch = bankBranch;
	}
*/
}