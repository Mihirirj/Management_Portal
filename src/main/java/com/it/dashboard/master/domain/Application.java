package com.it.dashboard.master.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the APPLICATIONS database table.
 * 
 */
@Entity
@Table(name="APPLICATIONS")
@NamedQuery(name="Application.findAll", query="SELECT a FROM Application a")
public class Application implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ApplicationPK id;

	@Temporal(TemporalType.DATE)
	@Column(name="ADD_DATE")
	private Date addDate;

	@Column(name="ADD_TIME")
	private String addTime;

	@Column(name="ADD_USER")
	private String addUser;

	@Column(name="ALLOCATED_BY")
	private String allocatedBy;

	@Temporal(TemporalType.DATE)
	@Column(name="ALLOCATED_DATE")
	private Date allocatedDate;

	@Column(name="ALLOCATED_TIME")
	private String allocatedTime;

	@Column(name="ALLOCATED_TO")
	private String allocatedTo;

	@Column(name="APPLICABLE_STD_YEAR")
	private String applicableStdYear;

	@Column(name="APPLICATION_NO")
	private String applicationNo;

	@Column(name="APPLICATION_SUB_TYPE")
	private String applicationSubType;

	@Column(name="APPLICATION_TYPE")
	private String applicationType;

	@Column(name="CONFIRMED_BY")
	private String confirmedBy;

	@Temporal(TemporalType.DATE)
	@Column(name="CONFIRMED_DATE")
	private Date confirmedDate;

	@Column(name="CONFIRMED_TIME")
	private String confirmedTime;

	@Column(name="CONTACT_ADDRESS")
	private String contactAddress;

	@Column(name="CONTACT_EMAIL")
	private String contactEmail;

	@Column(name="CONTACT_ID_NO")
	private String contactIdNo;

	@Column(name="CONTACT_MOBILE")
	private String contactMobile;

	@Column(name="CONTACT_NAME")
	private String contactName;

	@Column(name="CONTACT_TELEPHONE")
	private String contactTelephone;

	private String description;

	@Column(name="DISCONNECTED_WITHIN")
	private BigDecimal disconnectedWithin;

	private BigDecimal duration;

	@Column(name="DURATION_IN_DAYS")
	private BigDecimal durationInDays;

	@Column(name="DURATION_TYPE")
	private String durationType;

	@Column(name="EXIST_TARIFF")
	private String existTariff;

	@Column(name="FINALIZED_WITHIN")
	private BigDecimal finalizedWithin;

	@Temporal(TemporalType.DATE)
	@Column(name="FROM_DATE")
	private Date fromDate;

	@Column(name="ID_NO")
	private String idNo;

	@Column(name="IS_LOAN_APP")
	private String isLoanApp;

	@Column(name="IS_PIV1_NEEDED")
	private String isPiv1Needed;

	@Column(name="IS_SEQUENCE_CHANGE")
	private String isSequenceChange;

	@Column(name="IS_TARIFF_CHANGE")
	private String isTariffChange;

	@Column(name="IS_VISITNG_NEEDED")
	private String isVisitngNeeded;

	@Column(name="LINKED_WITH")
	private String linkedWith;

	@Column(name="PREPARED_BY")
	private String preparedBy;

	@Column(name="SAMURDHI_MEMBER")
	private String samurdhiMember;

	private String status;

	@Temporal(TemporalType.DATE)
	@Column(name="SUBMIT_DATE")
	private Date submitDate;

	@Temporal(TemporalType.DATE)
	@Column(name="TO_DATE")
	private Date toDate;

	@Temporal(TemporalType.DATE)
	@Column(name="UPD_DATE")
	private Date updDate;

	@Column(name="UPD_TIME")
	private String updTime;

	@Column(name="UPD_USER")
	private String updUser;

	public Application() {
	}

	public ApplicationPK getId() {
		return this.id;
	}

	public void setId(ApplicationPK id) {
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

	public String getAllocatedBy() {
		return this.allocatedBy;
	}

	public void setAllocatedBy(String allocatedBy) {
		this.allocatedBy = allocatedBy;
	}

	public Date getAllocatedDate() {
		return this.allocatedDate;
	}

	public void setAllocatedDate(Date allocatedDate) {
		this.allocatedDate = allocatedDate;
	}

	public String getAllocatedTime() {
		return this.allocatedTime;
	}

	public void setAllocatedTime(String allocatedTime) {
		this.allocatedTime = allocatedTime;
	}

	public String getAllocatedTo() {
		return this.allocatedTo;
	}

	public void setAllocatedTo(String allocatedTo) {
		this.allocatedTo = allocatedTo;
	}

	public String getApplicableStdYear() {
		return this.applicableStdYear;
	}

	public void setApplicableStdYear(String applicableStdYear) {
		this.applicableStdYear = applicableStdYear;
	}

	public String getApplicationNo() {
		return this.applicationNo;
	}

	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}

	public String getApplicationSubType() {
		return this.applicationSubType;
	}

	public void setApplicationSubType(String applicationSubType) {
		this.applicationSubType = applicationSubType;
	}

	public String getApplicationType() {
		return this.applicationType;
	}

	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
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

	public String getContactAddress() {
		return this.contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public String getContactEmail() {
		return this.contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getContactIdNo() {
		return this.contactIdNo;
	}

	public void setContactIdNo(String contactIdNo) {
		this.contactIdNo = contactIdNo;
	}

	public String getContactMobile() {
		return this.contactMobile;
	}

	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}

	public String getContactName() {
		return this.contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactTelephone() {
		return this.contactTelephone;
	}

	public void setContactTelephone(String contactTelephone) {
		this.contactTelephone = contactTelephone;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getDisconnectedWithin() {
		return this.disconnectedWithin;
	}

	public void setDisconnectedWithin(BigDecimal disconnectedWithin) {
		this.disconnectedWithin = disconnectedWithin;
	}

	public BigDecimal getDuration() {
		return this.duration;
	}

	public void setDuration(BigDecimal duration) {
		this.duration = duration;
	}

	public BigDecimal getDurationInDays() {
		return this.durationInDays;
	}

	public void setDurationInDays(BigDecimal durationInDays) {
		this.durationInDays = durationInDays;
	}

	public String getDurationType() {
		return this.durationType;
	}

	public void setDurationType(String durationType) {
		this.durationType = durationType;
	}

	public String getExistTariff() {
		return this.existTariff;
	}

	public void setExistTariff(String existTariff) {
		this.existTariff = existTariff;
	}

	public BigDecimal getFinalizedWithin() {
		return this.finalizedWithin;
	}

	public void setFinalizedWithin(BigDecimal finalizedWithin) {
		this.finalizedWithin = finalizedWithin;
	}

	public Date getFromDate() {
		return this.fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public String getIdNo() {
		return this.idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getIsLoanApp() {
		return this.isLoanApp;
	}

	public void setIsLoanApp(String isLoanApp) {
		this.isLoanApp = isLoanApp;
	}

	public String getIsPiv1Needed() {
		return this.isPiv1Needed;
	}

	public void setIsPiv1Needed(String isPiv1Needed) {
		this.isPiv1Needed = isPiv1Needed;
	}

	public String getIsSequenceChange() {
		return this.isSequenceChange;
	}

	public void setIsSequenceChange(String isSequenceChange) {
		this.isSequenceChange = isSequenceChange;
	}

	public String getIsTariffChange() {
		return this.isTariffChange;
	}

	public void setIsTariffChange(String isTariffChange) {
		this.isTariffChange = isTariffChange;
	}

	public String getIsVisitngNeeded() {
		return this.isVisitngNeeded;
	}

	public void setIsVisitngNeeded(String isVisitngNeeded) {
		this.isVisitngNeeded = isVisitngNeeded;
	}

	public String getLinkedWith() {
		return this.linkedWith;
	}

	public void setLinkedWith(String linkedWith) {
		this.linkedWith = linkedWith;
	}

	public String getPreparedBy() {
		return this.preparedBy;
	}

	public void setPreparedBy(String preparedBy) {
		this.preparedBy = preparedBy;
	}

	public String getSamurdhiMember() {
		return this.samurdhiMember;
	}

	public void setSamurdhiMember(String samurdhiMember) {
		this.samurdhiMember = samurdhiMember;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getSubmitDate() {
		return this.submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	public Date getToDate() {
		return this.toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
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