package com.it.dashboard.master.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the APPLICANT database table.
 * 
 */
@Entity
@NamedQuery(name="Applicant.findAll", query="SELECT a FROM Applicant a")
public class Applicant implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_NO")
	private String idNo;

	@Temporal(TemporalType.DATE)
	@Column(name="ADD_DATE")
	private Date addDate;

	@Column(name="ADD_TIME")
	private String addTime;

	@Column(name="ADD_USER")
	private String addUser;

	@Column(name="CEB_EMPLOYEE")
	private String cebEmployee;

	private String city;

	@Column(name="COMPANY_NAME")
	private String companyName;

	@Column(name="DEPT_ID")
	private String deptId;

	private String email;

	@Column(name="ENTITLED_4_LOAN")
	private String entitled4Loan;

	@Column(name="FIRST_NAME")
	private String firstName;

	@Column(name="FULL_NAME")
	private String fullName;

	@Column(name="ID_TYPE")
	private String idType;

	@Column(name="LAST_NAME")
	private String lastName;

	@Column(name="LOAN_AMOUNT")
	private BigDecimal loanAmount;

	@Column(name="LOAN_REFERENCE")
	private String loanReference;

	@Column(name="MEMBER_OF_SAMURDHI")
	private String memberOfSamurdhi;

	@Column(name="MOBILE_NO")
	private String mobileNo;

	@Column(name="NO_OF_SHARES")
	private BigDecimal noOfShares;

	@Column(name="PERSONAL_CORPORATE")
	private String personalCorporate;

	@Column(name="POSTAL_CODE")
	private String postalCode;

	@Column(name="PREFERRED_LANGUAGE")
	private String preferredLanguage;

	@Column(name="SAMURDHI_ID")
	private String samurdhiId;

	@Column(name="SHARE_PRICE")
	private BigDecimal sharePrice;

	private String status;

	@Column(name="STREET_ADDRESS")
	private String streetAddress;

	private String suburb;

	@Column(name="TELEPHONE_NO")
	private String telephoneNo;

	@Temporal(TemporalType.DATE)
	@Column(name="UPD_DATE")
	private Date updDate;

	@Column(name="UPD_TIME")
	private String updTime;

	@Column(name="UPD_USER")
	private String updUser;

	

	public Applicant() {
	}

	public String getIdNo() {
		return this.idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
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

	public String getCebEmployee() {
		return this.cebEmployee;
	}

	public void setCebEmployee(String cebEmployee) {
		this.cebEmployee = cebEmployee;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEntitled4Loan() {
		return this.entitled4Loan;
	}

	public void setEntitled4Loan(String entitled4Loan) {
		this.entitled4Loan = entitled4Loan;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getIdType() {
		return this.idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public BigDecimal getLoanAmount() {
		return this.loanAmount;
	}

	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getLoanReference() {
		return this.loanReference;
	}

	public void setLoanReference(String loanReference) {
		this.loanReference = loanReference;
	}

	public String getMemberOfSamurdhi() {
		return this.memberOfSamurdhi;
	}

	public void setMemberOfSamurdhi(String memberOfSamurdhi) {
		this.memberOfSamurdhi = memberOfSamurdhi;
	}

	public String getMobileNo() {
		return this.mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public BigDecimal getNoOfShares() {
		return this.noOfShares;
	}

	public void setNoOfShares(BigDecimal noOfShares) {
		this.noOfShares = noOfShares;
	}

	public String getPersonalCorporate() {
		return this.personalCorporate;
	}

	public void setPersonalCorporate(String personalCorporate) {
		this.personalCorporate = personalCorporate;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPreferredLanguage() {
		return this.preferredLanguage;
	}

	public void setPreferredLanguage(String preferredLanguage) {
		this.preferredLanguage = preferredLanguage;
	}

	public String getSamurdhiId() {
		return this.samurdhiId;
	}

	public void setSamurdhiId(String samurdhiId) {
		this.samurdhiId = samurdhiId;
	}

	public BigDecimal getSharePrice() {
		return this.sharePrice;
	}

	public void setSharePrice(BigDecimal sharePrice) {
		this.sharePrice = sharePrice;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStreetAddress() {
		return this.streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getSuburb() {
		return this.suburb;
	}

	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}

	public String getTelephoneNo() {
		return this.telephoneNo;
	}

	public void setTelephoneNo(String telephoneNo) {
		this.telephoneNo = telephoneNo;
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

	

}