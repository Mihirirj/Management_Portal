package com.it.dashboard.refund.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.it.dashboard.issue.domain.PivDetail;
import com.it.dashboard.issue.domain.PivDetailPK;

/**
 * The persistent class for the PIV_REFUND database table.
 * 
 */
@Entity
@Table(name="PIV_REFUND")
//@NamedQuery(name="PivRefund.findAll", query="SELECT p FROM PivRefund p")
public class PivRefund implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PivDetailPK id;

	@Column(name="APPROVAL_DATE")
	private Timestamp approvalDate;

	@Column(name="APPROVAL_USER")
	private String approvalUser;

	@Column(name="DOC_NO")
	private String docNo;
	
	@Temporal(TemporalType.DATE)
	@Column(name="REFUND_DATE", nullable=true)
	private Date refundDate;

	@Column(name="REFUND_REQUEST_AMOUNT", nullable=false, precision=20, scale=2)
	private BigDecimal refundRequestAmount;

	@Column(name="REFUND_USER")
	private String refundUser;

	@Column(name="REFUNDABLE_AMOUNT")
	private BigDecimal refundableAmount;

	@Column(name="REQUEST_DATE")
	private Timestamp requestDate;

	@Column(name="REQUEST_USER")
	private String requestUser;

	@Column(name="SEQ_NO", nullable=false, precision=2)
	private BigDecimal seqNo;

	private String status;

	//bi-directional one-to-one association to PivDetail
	@JsonIgnore
	@OneToOne
	@JoinColumns({
		@JoinColumn(name="DEPT_ID", referencedColumnName="DEPT_ID", nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="PIV_NO", referencedColumnName="PIV_NO", nullable=false, insertable=false, updatable=false)
		})
	private PivDetail pivDetail;

	public PivRefund() {
	}

	public PivDetailPK getId() {
		return this.id;
	}

	public void setId(PivDetailPK id) {
		this.id = id;
	}

	public Timestamp getApprovalDate() {
		return this.approvalDate;
	}

	public void setApprovalDate(Timestamp approvalDate) {
		this.approvalDate = approvalDate;
	}

	public String getApprovalUser() {
		return this.approvalUser;
	}

	public void setApprovalUser(String approvalUser) {
		this.approvalUser = approvalUser;
	}

	public String getDocNo() {
		return this.docNo;
	}

	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}
	
	public Date getRefundDate() {
		return this.refundDate;
	}

	public void setRefundDate(Date refundDate) {
		this.refundDate = refundDate;
	}

	public BigDecimal getRefundRequestAmount() {
		return this.refundRequestAmount;
	}

	public void setRefundRequestAmount(BigDecimal refundRequestAmount) {
		this.refundRequestAmount = refundRequestAmount;
	}

	public String getRefundUser() {
		return this.refundUser;
	}

	public void setRefundUser(String refundUser) {
		this.refundUser = refundUser;
	}

	public BigDecimal getRefundableAmount() {
		return this.refundableAmount;
	}

	public void setRefundableAmount(BigDecimal refundableAmount) {
		this.refundableAmount = refundableAmount;

	}

	public Timestamp getRequestDate() {
		return this.requestDate;
	}

	public void setRequestDate(Timestamp requestDate) {
		this.requestDate = requestDate;
	}

	public String getRequestUser() {
		return this.requestUser;
	}

	public void setRequestUser(String requestUser) {
		this.requestUser = requestUser;
	}

	public BigDecimal getSeqNo() {
		return this.seqNo;
	}

	public void setSeqNo(BigDecimal seqNo) {
		this.seqNo = seqNo;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public PivDetail getPivDetail() {
		return this.pivDetail;
	}

	public void setPivDetail(PivDetail pivDetail) {
		this.pivDetail = pivDetail;
	}
}