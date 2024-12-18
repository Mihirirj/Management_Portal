package com.it.dashboard.issue.domain;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;


/**
 * The persistent class for the PIV_APPLICANT database table.
 * 
 */
@Entity
@Table(name="PIV_APPLICANT")
@NamedQuery(name="PivApplicant.findAll", query="SELECT p FROM PivApplicant p")
public class PivApplicant implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PivApplicantPK id;

	@Column(name="ADD_DATE")
	private Timestamp addDate;

	@Column(name="ADD_USER")
	private String addUser;

	private String address;

	@Column(name="COLLECT_PERSON_ID")
	private String collectPersonId;

	@Column(name="COLLECT_PERSON_NAME")
	private String collectPersonName;

	private String email;

	@Column(name="EPF_NO")
	private String epfNo;

	@Column(name="ID_NO")
	private String idNo;

	@Column(name="ID_TYPE")
	private String idType;

	private String name;

	@Column(name="PIV_APP_NO")
	private String pivAppNo;

	@Column(name="TELEPHONE_NO")
	private String telephoneNo;

	@Column(name="UPD_DATE")
	private Timestamp updDate;

	@Column(name="UPD_USER")
	private String updUser;

	@Column(name="VAT_REG_NO")
	private String vatRegNo;

	//bi-directional many-to-one association to PivDetail
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumns({
		@JoinColumn(name="DEPT_ID", referencedColumnName="DEPT_ID", insertable=false, updatable=false),
		@JoinColumn(name="PIV_NO", referencedColumnName="PIV_NO", insertable=false, updatable=false)
		})
	private PivDetail pivDetail;

	
	
	
	public PivApplicant() {
	}

	public PivApplicantPK getId() {
		return this.id;
	}

	public void setId(PivApplicantPK id) {
		this.id = id;
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

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCollectPersonId() {
		return this.collectPersonId;
	}

	public void setCollectPersonId(String collectPersonId) {
		this.collectPersonId = collectPersonId;
	}

	public String getCollectPersonName() {
		return this.collectPersonName;
	}

	public void setCollectPersonName(String collectPersonName) {
		this.collectPersonName = collectPersonName;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEpfNo() {
		return this.epfNo;
	}

	public void setEpfNo(String epfNo) {
		this.epfNo = epfNo;
	}

	public String getIdNo() {
		return this.idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getIdType() {
		return this.idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPivAppNo() {
		return this.pivAppNo;
	}

	public void setPivAppNo(String pivAppNo) {
		this.pivAppNo = pivAppNo;
	}

	public String getTelephoneNo() {
		return this.telephoneNo;
	}

	public void setTelephoneNo(String telephoneNo) {
		this.telephoneNo = telephoneNo;
	}

	public Timestamp getUpdDate() {
		return this.updDate;
	}

	public void setUpdDate(Timestamp updDate) {
		this.updDate = updDate;
	}

	public String getUpdUser() {
		return this.updUser;
	}

	public void setUpdUser(String updUser) {
		this.updUser = updUser;
	}

	public String getVatRegNo() {
		return this.vatRegNo;
	}

	public void setVatRegNo(String vatRegNo) {
		this.vatRegNo = vatRegNo;
	}

	public PivDetail getPivDetail() {
		return this.pivDetail;
	}

	public void setPivDetail(PivDetail pivDetail) {
		this.pivDetail = pivDetail;
	}

}