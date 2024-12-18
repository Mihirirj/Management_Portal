package com.it.dashboard.master.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the APPLICATION_REFERENCE database table.
 * 
 */
@Entity
@Table(name="APPLICATION_REFERENCE")
@NamedQuery(name="ApplicationReference.findAll", query="SELECT a FROM ApplicationReference a")
public class ApplicationReference implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ApplicationReferencePK id;

	@Column(name="APPLICATION_NO")
	private String applicationNo;

	@Column(name="CONSRUCTOR_ID")
	private String consructorId;

	@Column(name="ID_NO")
	private String idNo;

	@Column(name="POSTED_BY")
	private String postedBy;

	@Temporal(TemporalType.DATE)
	@Column(name="POSTED_DATE")
	private Date postedDate;

	@Column(name="POSTED_TIME")
	private String postedTime;

	private String projectno;

	private String status;

	public ApplicationReference() {
	}

	public ApplicationReferencePK getId() {
		return this.id;
	}

	public void setId(ApplicationReferencePK id) {
		this.id = id;
	}

	public String getApplicationNo() {
		return this.applicationNo;
	}

	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}

	public String getConsructorId() {
		return this.consructorId;
	}

	public void setConsructorId(String consructorId) {
		this.consructorId = consructorId;
	}

	public String getIdNo() {
		return this.idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getPostedBy() {
		return this.postedBy;
	}

	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}

	public Date getPostedDate() {
		return this.postedDate;
	}

	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}

	public String getPostedTime() {
		return this.postedTime;
	}

	public void setPostedTime(String postedTime) {
		this.postedTime = postedTime;
	}

	public String getProjectno() {
		return this.projectno;
	}

	public void setProjectno(String projectno) {
		this.projectno = projectno;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}