package com.it.dashboard.issue.domain;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the PIV_DETAIL database table.
 * 
 */
@Entity
@Table(name="PIV_DETAIL")
@NamedQuery(name="PivDetail.findAll", query="SELECT p FROM PivDetail p")
public class PivDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PivDetailPK id;

	@Temporal(TemporalType.DATE)
	@Column(name="ADD_DATE")
	private Date addDate;

	@Column(name="ADD_TIME")
	private String addTime;

	@Column(name="ADD_USER")
	private String addUser;

	@Column(name="AMOUNT_IN_WORDS")
	private String amountInWords;

	/*@Column(name="APP_REFERENCE_NO")
	private String appReferenceNo;*/

	@Column(name="APPROVED_BY")
	private String approvedBy;

	@Column(name="APPROVED_DATE")
	private Timestamp approvedDate;

	@Column(name="BANK_CHECK_NO")
	private String bankCheckNo;

	/*@Column(name="BANK_CODE")
	private String bankCode;*/

	@Temporal(TemporalType.DATE)
	@Column(name="BANK_PAID_DATE")
	private Date bankPaidDate;

	@Temporal(TemporalType.DATE)
	@Column(name="BANK_PAID_TIME")
	private Date bankPaidTime;

	@Column(name="BANK_STATUS")
	private String bankStatus;

	@Column(name="BRANCH_CODE")
	private String branchCode;
	
	@Column(name="CA_APPLICATION_NO")
	private BigDecimal caApplicationNo;
	
	@Temporal(TemporalType.DATE)
	@Column(name="CANCEL_DATE")
	private Date cancelDate;

	@Column(name="CANCEL_TIME")
	private String cancelTime;

	@Column(name="CANCEL_USER")
	private String cancelUser;

	/*@Column(name="CASH_IN_TRANSIT")
	private BigDecimal cashInTransit;*/

	@Column(name="CEB_BRANCH_NAME")
	private String cebBranchName;
/*
	@Column(name="CHEQUE_BANK_CODE")
	private String chequeBankCode;

	@Column(name="CHEQUE_BRANCH_CODE")
	private String chequeBranchCode;

	@Temporal(TemporalType.DATE)
	@Column(name="CHEQUE_DATE")
	private Date chequeDate;

	@Column(name="CHEQUE_NO")
	private BigDecimal chequeNo;*/

	@Column(name="CONFIRMED_BY")
	private String confirmedBy;

	@Temporal(TemporalType.DATE)
	@Column(name="CONFIRMED_DATE")
	private Date confirmedDate;

	@Column(name="CONFIRMED_TIME")
	private String confirmedTime;

	@Column(name="CURRENCY_CODE")
	private String currencyCode;

	private String description;

	/*@Column(name="ELECTRICITY_DEBTORS")
	private BigDecimal electricityDebtors;*/

	@Column(name="ENTERED_TIME")
	private Timestamp enteredTime;

	/*@Column(name="EST_REFERENCE_NO")
	private String estReferenceNo;*/

	@Temporal(TemporalType.DATE)
	@Column(name="EXPIRY_DATE")
	private Date expiryDate;

	/*@Column(name="FOR_DISHONORED_CHEQUE")
	private BigDecimal forDishonoredCheque;*/

	@Column(name="GRAND_TOTAL")
	private BigDecimal grandTotal;

	@Column(name="ID_NO")
	private String idNo;

	@Column(name="ISSUED_DATE")
	private Timestamp issuedDate;

	/*@Column(name="LOAN_AMOUNT")
	private BigDecimal loanAmount;

	@Column(name="LOAN_REFERENCE")
	private String loanReference;

	@Column(name="MISCELLANEOUS_DEPOSIT")
	private BigDecimal miscellaneousDeposit;

	@Column(name="MISCELLANEOUS_INCOME")
	private BigDecimal miscellaneousIncome;

	@Column(name="NBT_AMOUNT")
	private BigDecimal nbtAmount;*/

	@Column(name="OTHER_PIV_REF")
	private String otherPivRef;

	@Column(name="PAID_AGENT")
	private String paidAgent;

	@Column(name="PAID_AMOUNT")
	private BigDecimal paidAmount;

	@Column(name="PAID_DEPT_ID")
	private String paidDeptId;
	
	@Column(name="PAID_BRANCH")
	private String paidBranch;

	@Temporal(TemporalType.DATE)
	@Column(name="PAID_DATE")
	private Date paidDate;

	@Column(name="PAID_TIME")
	private Timestamp paidTime;

	@Column(name="PAYMENT_MODE")
	private String paymentMode;

	@Column(name="PIV_AMOUNT")
	private BigDecimal pivAmount;

	@Temporal(TemporalType.DATE)
	@Column(name="PIV_DATE")
	private Date pivDate;

	@Column(name="PIV_RECEIPT_NO")
	private String pivReceiptNo;

	@Column(name="PIV_SEQ_NO")
	private BigDecimal pivSeqNo;

	/*@Column(name="POS_A")
	private String posA;

	@Column(name="POS_CENTER")
	private String posCenter;*/

	@Column(name="PREPARED_BY")
	private String preparedBy;

	@Column(name="PREPARED_DATE")
	private Timestamp preparedDate;

	/*@Column(name="PROCESSING_FEE")
	private BigDecimal processingFee;*/

	@Column(name="REFERENCE_NO")
	private String referenceNo;

	@Column(name="REFERENCE_TYPE")
	private String referenceType;

	@Column(name="REJECTED_BY")
	private String rejectedBy;

	@Column(name="REJECTED_DATE")
	private Timestamp rejectedDate;

	@Column(name="REJECTED_REASON")
	private String rejectedReason;
/*
	@Column(name="REVISE_REFERENCE_NO")
	private String reviseReferenceNo;

	/*@Column(name="SECURITY_DEPOSIT")
	private BigDecimal securityDeposit;

	@Column(name="SER_CONN_OR_ELEC_SCH")
	private BigDecimal serConnOrElecSch;*/

	private String status;

	@Column(name="SUB_TOTAL")
	private BigDecimal subTotal;

	@Column(name="SYS_TYPE")
	private String sysType;
	
	@Column(name="SETOFF_FROM")
	private String setoffFrom;
	
	@Column(name="SETOFF_TO")
	private String setoffTo;

	/*@Column(name="TENDER_DEPOSIT")
	private BigDecimal tenderDeposit;*/

	@Column(name="TITLE_CD")
	private String titleCd;

	@Temporal(TemporalType.DATE)
	@Column(name="UPD_DATE")
	private Date updDate;

	@Column(name="UPD_TIME")
	private String updTime;

	@Column(name="UPD_USER")
	private String updUser;

	//private BigDecimal vat;

	//bi-directional many-to-one association to PivApplicant
	/*@OneToMany(mappedBy="pivDetail")
	@JsonIgnore 
	private List<PivApplicant> pivApplicants;*/
	
	/*@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="TITLE_CD", insertable=true, updatable=true)
	private Gltitlm gltitlm;*/
	
	//bi-directional many-to-one association to PivActivity
	/*@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="STATUS", insertable=true, updatable=true)
	private PivActivity pivActivity;*/

	public PivDetail() {
	}

	public PivDetailPK getId() {
		return this.id;
	}

	public void setId(PivDetailPK id) {
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

	public String getAmountInWords() {
		return this.amountInWords;
	}

	public void setAmountInWords(String amountInWords) {
		this.amountInWords = amountInWords;
	}

	
	public String getApprovedBy() {
		return this.approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public Timestamp getApprovedDate() {
		return this.approvedDate;
	}

	public void setApprovedDate(Timestamp approvedDate) {
		this.approvedDate = approvedDate;
	}

	public String getBankCheckNo() {
		return this.bankCheckNo;
	}

	public void setBankCheckNo(String bankCheckNo) {
		this.bankCheckNo = bankCheckNo;
	}

	

	public Date getBankPaidDate() {
		return this.bankPaidDate;
	}

	public void setBankPaidDate(Date bankPaidDate) {
		this.bankPaidDate = bankPaidDate;
	}

	public Date getBankPaidTime() {
		return this.bankPaidTime;
	}

	public void setBankPaidTime(Date bankPaidTime) {
		this.bankPaidTime = bankPaidTime;
	}

	public String getBankStatus() {
		return this.bankStatus;
	}

	public void setBankStatus(String bankStatus) {
		this.bankStatus = bankStatus;
	}

	public String getBranchCode() {
		return this.branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public Date getCancelDate() {
		return this.cancelDate;
	}

	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
	}

	public String getCancelTime() {
		return this.cancelTime;
	}

	public void setCancelTime(String cancelTime) {
		this.cancelTime = cancelTime;
	}

	public String getCancelUser() {
		return this.cancelUser;
	}

	public void setCancelUser(String cancelUser) {
		this.cancelUser = cancelUser;
	}

	

	

	

	
	public String getConfirmedBy() {
		return this.confirmedBy;
	}

	public void setConfirmedBy(String confirmedBy) {
		this.confirmedBy = confirmedBy;
	}

	public Date getConfirmedDate() {
		return this.confirmedDate;
	}

	public void setConfirmedDate(Date confirmedDate) {
		this.confirmedDate = confirmedDate;
	}

	public String getConfirmedTime() {
		return this.confirmedTime;
	}

	public void setConfirmedTime(String confirmedTime) {
		this.confirmedTime = confirmedTime;
	}

	public String getCurrencyCode() {
		return this.currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public Timestamp getEnteredTime() {
		return this.enteredTime;
	}

	public void setEnteredTime(Timestamp enteredTime) {
		this.enteredTime = enteredTime;
	}
/*
	public String getEstReferenceNo() {
		return this.estReferenceNo;
	}

	public void setEstReferenceNo(String estReferenceNo) {
		this.estReferenceNo = estReferenceNo;
	}
*/
	public Date getExpiryDate() {
		return this.expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	

	public BigDecimal getGrandTotal() {
		return this.grandTotal;
	}

	public void setGrandTotal(BigDecimal grandTotal) {
		this.grandTotal = grandTotal;
	}

	public String getIdNo() {
		return this.idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public Timestamp getIssuedDate() {
		return this.issuedDate;
	}

	public void setIssuedDate(Timestamp issuedDate) {
		this.issuedDate = issuedDate;
	}

	

	

	

	public String getOtherPivRef() {
		return this.otherPivRef;
	}

	public void setOtherPivRef(String otherPivRef) {
		this.otherPivRef = otherPivRef;
	}

	public String getPaidAgent() {
		return this.paidAgent;
	}

	public void setPaidAgent(String paidAgent) {
		this.paidAgent = paidAgent;
	}

	public BigDecimal getPaidAmount() {
		return this.paidAmount;
	}

	public void setPaidAmount(BigDecimal paidAmount) {
		this.paidAmount = paidAmount;
	}

	public String getPaidBranch() {
		return this.paidBranch;
	}

	public void setPaidBranch(String paidBranch) {
		this.paidBranch = paidBranch;
	}

	public Date getPaidDate() {
		return this.paidDate;
	}

	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}

	public Timestamp getPaidTime() {
		return this.paidTime;
	}

	public void setPaidTime(Timestamp paidTime) {
		this.paidTime = paidTime;
	}

	public String getPaymentMode() {
		return this.paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public BigDecimal getPivAmount() {
		return this.pivAmount;
	}

	public void setPivAmount(BigDecimal pivAmount) {
		this.pivAmount = pivAmount;
	}

	public Date getPivDate() {
		return this.pivDate;
	}

	public void setPivDate(Date pivDate) {
		this.pivDate = pivDate;
	}

	public String getPivReceiptNo() {
		return this.pivReceiptNo;
	}

	public void setPivReceiptNo(String pivReceiptNo) {
		this.pivReceiptNo = pivReceiptNo;
	}

	public BigDecimal getPivSeqNo() {
		return this.pivSeqNo;
	}

	public void setPivSeqNo(BigDecimal pivSeqNo) {
		this.pivSeqNo = pivSeqNo;
	}

	

	public String getPreparedBy() {
		return this.preparedBy;
	}

	public void setPreparedBy(String preparedBy) {
		this.preparedBy = preparedBy;
	}

	public Timestamp getPreparedDate() {
		return this.preparedDate;
	}

	public void setPreparedDate(Timestamp preparedDate) {
		this.preparedDate = preparedDate;
	}

	

	public String getReferenceNo() {
		return this.referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	public String getReferenceType() {
		return this.referenceType;
	}

	public void setReferenceType(String referenceType) {
		this.referenceType = referenceType;
	}

	public String getRejectedBy() {
		return this.rejectedBy;
	}

	public void setRejectedBy(String rejectedBy) {
		this.rejectedBy = rejectedBy;
	}

	public Timestamp getRejectedDate() {
		return this.rejectedDate;
	}

	public void setRejectedDate(Timestamp rejectedDate) {
		this.rejectedDate = rejectedDate;
	}

	public String getRejectedReason() {
		return this.rejectedReason;
	}

	public void setRejectedReason(String rejectedReason) {
		this.rejectedReason = rejectedReason;
	}
/*
	public String getReviseReferenceNo() {
		return this.reviseReferenceNo;
	}

	public void setReviseReferenceNo(String reviseReferenceNo) {
		this.reviseReferenceNo = reviseReferenceNo;
	}

	*/

	

	public BigDecimal getSubTotal() {
		return this.subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public String getSysType() {
		return this.sysType;
	}

	public void setSysType(String sysType) {
		this.sysType = sysType;
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
/*
	public BigDecimal getVat() {
		return this.vat;
	}

	public void setVat(BigDecimal vat) {
		this.vat = vat;
	}
*/
	/*
	public List<PivApplicant> getPivApplicants() {
		return this.pivApplicants;
	}

	public void setPivApplicants(List<PivApplicant> pivApplicants) {
		this.pivApplicants = pivApplicants;
	}

	public PivApplicant addPivApplicant(PivApplicant pivApplicant) {
		getPivApplicants().add(pivApplicant);
		pivApplicant.setPivDetail(this);

		return pivApplicant;
	}

	public PivApplicant removePivApplicant(PivApplicant pivApplicant) {
		getPivApplicants().remove(pivApplicant);
		pivApplicant.setPivDetail(null);

		return pivApplicant;
	}
	*/
/*
	public Gltitlm getGltitlm() {
		return gltitlm;
	}

	public void setGltitlm(Gltitlm gltitlm) {
		this.gltitlm = gltitlm;
	}

	public PivActivity getPivActivity() {
		return pivActivity;
	}

	public void setPivActivity(PivActivity pivActivity) {
		this.pivActivity = pivActivity;
	}*/

	public String getPaidDeptId() {
		return paidDeptId;
	}

	public void setPaidDeptId(String paidDeptId) {
		this.paidDeptId = paidDeptId;
	}

	public String getSetoffFrom() {
		return setoffFrom;
	}

	public void setSetoffFrom(String setoffFrom) {
		this.setoffFrom = setoffFrom;
	}

	public String getSetoffTo() {
		return setoffTo;
	}

	public void setSetoffTo(String setoffTo) {
		this.setoffTo = setoffTo;
	}

	public BigDecimal getCaApplicationNo() {
		return caApplicationNo;
	}

	public void setCaApplicationNo(BigDecimal caApplicationNo) {
		this.caApplicationNo = caApplicationNo;
	}

	public String getCebBranchName() {
		return cebBranchName;
	}

	public void setCebBranchName(String cebBranchName) {
		this.cebBranchName = cebBranchName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTitleCd() {
		return titleCd;
	}

	public void setTitleCd(String titleCd) {
		this.titleCd = titleCd;
	}

	

}