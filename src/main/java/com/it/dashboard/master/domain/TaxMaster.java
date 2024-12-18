package com.it.dashboard.master.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TAX_MASTER database table.
 * 
 */
@Entity
@Table(name="TAX_MASTER")
@NamedQuery(name="TaxMaster.findAll", query="SELECT t FROM TaxMaster t")
public class TaxMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TAX_CODE")
	private String taxCode;

	@Column(name="AC_CD")
	private String acCd;

	@Column(name="APPROVAL_DATE")
	private Timestamp approvalDate;

	@Column(name="APPROVED_USER")
	private String approvedUser;

	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name="EFFECT_FROM")
	private Date effectFrom;

	@Temporal(TemporalType.DATE)
	@Column(name="EFFECT_TO")
	private Date effectTo;

	@Column(name="ENTRY_DATE")
	private Timestamp entryDate;

	@Column(name="ENTRY_USER")
	private String entryUser;

	private String status;

	@Column(name="TAX_VAL")
	private BigDecimal taxVal;

	public TaxMaster() {
	}

	public String getTaxCode() {
		return this.taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	public String getAcCd() {
		return this.acCd;
	}

	public void setAcCd(String acCd) {
		this.acCd = acCd;
	}

	public Timestamp getApprovalDate() {
		return this.approvalDate;
	}

	public void setApprovalDate(Timestamp approvalDate) {
		this.approvalDate = approvalDate;
	}

	public String getApprovedUser() {
		return this.approvedUser;
	}

	public void setApprovedUser(String approvedUser) {
		this.approvedUser = approvedUser;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEffectFrom() {
		return this.effectFrom;
	}

	public void setEffectFrom(Date effectFrom) {
		this.effectFrom = effectFrom;
	}

	public Date getEffectTo() {
		return this.effectTo;
	}

	public void setEffectTo(Date effectTo) {
		this.effectTo = effectTo;
	}

	public Timestamp getEntryDate() {
		return this.entryDate;
	}

	public void setEntryDate(Timestamp entryDate) {
		this.entryDate = entryDate;
	}

	public String getEntryUser() {
		return this.entryUser;
	}

	public void setEntryUser(String entryUser) {
		this.entryUser = entryUser;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getTaxVal() {
		return this.taxVal;
	}

	public void setTaxVal(BigDecimal taxVal) {
		this.taxVal = taxVal;
	}

}