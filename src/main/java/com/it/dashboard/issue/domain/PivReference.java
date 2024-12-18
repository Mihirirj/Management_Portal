package com.it.dashboard.issue.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the PIV_REFERENCE database table.
 * 
 */
@Entity
@Table(name="PIV_REFERENCE")
public class PivReference implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PivDetailPK id;

	@Temporal(TemporalType.DATE)
	@Column(name="ADD_DATE")
	private Date addDate;

	@Column(name="ADD_TIME", length=11)
	private String addTime;

	@Column(name="ADD_USER", length=10)
	private String addUser;

	@Column(name="REF_PIV_NO", nullable=false, length=24)
	private String refPivNo;

	@Column(name="REF_REASON", nullable=false, length=150)
	private String refReason;

	@Temporal(TemporalType.DATE)
	@Column(name="UPD_DATE")
	private Date updDate;

	@Column(name="UPD_TIME", length=11)
	private String updTime;

	@Column(name="UPD_USER", length=10)
	private String updUser;

	//bi-directional one-to-one association to PivDetail
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="DEPT_ID", referencedColumnName="DEPT_ID", nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="PIV_NO", referencedColumnName="PIV_NO", nullable=false, insertable=false, updatable=false)
		})
	private PivDetail pivDetail;

	public PivReference() {
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

	public String getRefPivNo() {
		return this.refPivNo;
	}

	public void setRefPivNo(String refPivNo) {
		this.refPivNo = refPivNo;
	}

	public String getRefReason() {
		return this.refReason;
	}

	public void setRefReason(String refReason) {
		this.refReason = refReason;
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

}