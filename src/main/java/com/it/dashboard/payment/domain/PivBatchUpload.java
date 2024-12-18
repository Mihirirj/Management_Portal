package com.it.dashboard.payment.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the PIV_BATCH_UPLOAD database table.
 * 
 */
@Entity
@Table(name="PIV_BATCH_UPLOAD")
public class PivBatchUpload implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PivBatchUploadPK id;

	@Temporal(TemporalType.DATE)
	@Column(name="ADD_DATE")
	private Date addDate;

	@Column(name="ADD_TIME", length=11)
	private String addTime;

	@Column(name="ADD_USER", length=10)
	private String addUser;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_FROM", nullable=false)
	private Date dateFrom;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_TO", nullable=false)
	private Date dateTo;

	@Column(name="NO_OF_RECORDS", nullable=false, precision=4)
	private BigDecimal noOfRecords;

	@Column(name="TOTAL_AMOUNT", nullable=false, precision=12, scale=2)
	private BigDecimal totalAmount;

	@Temporal(TemporalType.DATE)
	@Column(name="UPD_DATE")
	private Date updDate;

	@Column(name="UPD_TIME", length=11)
	private String updTime;

	@Column(name="UPD_USER", length=10)
	private String updUser;

	@Temporal(TemporalType.DATE)
	@Column(name="UPLOAD_DATE", nullable=false)
	private Date uploadDate;

	@Column(name="UPLOAD_USER", nullable=false, length=10)
	private String uploadUser;

	public PivBatchUpload() {
	}

	public PivBatchUploadPK getId() {
		return this.id;
	}

	public void setId(PivBatchUploadPK id) {
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

	public Date getDateFrom() {
		return this.dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return this.dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public BigDecimal getNoOfRecords() {
		return this.noOfRecords;
	}

	public void setNoOfRecords(BigDecimal noOfRecords) {
		this.noOfRecords = noOfRecords;
	}

	public BigDecimal getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
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

	public Date getUploadDate() {
		return this.uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getUploadUser() {
		return this.uploadUser;
	}

	public void setUploadUser(String uploadUser) {
		this.uploadUser = uploadUser;
	}

}