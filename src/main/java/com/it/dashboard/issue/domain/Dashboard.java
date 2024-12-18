package com.it.dashboard.issue.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the DASHBOARD database table.
 * 
 */
@Entity
@NamedQuery(name="Dashboard.findAll", query="SELECT d FROM Dashboard d")
public class Dashboard implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="APPLICATION_ID")
	private String applicationId;

	@Column(name="ACC_OPENED_BY")
	private String accOpenedBy;

	@Column(name="ACC_OPENED_DATE")
	private Timestamp accOpenedDate;

	@Column(name="ACCOUNT_NO")
	private String accountNo;

	@Column(name="APP_SUBMITTED_BY")
	private String appSubmittedBy;

	@Column(name="APP_SUBMITTED_DATE")
	private Timestamp appSubmittedDate;

	@Column(name="APPLICATION_SUB_TYPE")
	private String applicationSubType;

	@Column(name="APPLICATION_TYPE")
	private String applicationType;

	@Column(name="APPOINTMENT_BY")
	private String appointmentBy;

	@Column(name="APPOINTMENT_DATE")
	private Timestamp appointmentDate;

	@Column(name="BILL_CREATED_BY")
	private String billCreatedBy;

	@Column(name="BILL_CREATED_DATE")
	private Timestamp billCreatedDate;

	@Column(name="CONNECTION_TYPE")
	private BigDecimal connectionType;

	@Column(name="CONSUMER_ADDRESS")
	private String consumerAddress;

	@Column(name="CONSUMER_NAME")
	private String consumerName;

	@Column(name="CONTRACTOR_ID")
	private String contractorId;

	@Column(name="CONTRACTOR_NAME")
	private String contractorName;

	@Column(name="DEPT_ID")
	private String deptId;

	@Column(name="DETAILED_COST")
	private BigDecimal detailedCost;

	@Column(name="ESTIMATE_APPROVED_BY")
	private String estimateApprovedBy;

	@Column(name="ESTIMATE_APPROVED_DATE")
	private Timestamp estimateApprovedDate;

	@Column(name="ESTIMATE_NO")
	private String estimateNo;

	@Column(name="ESTIMATE_NO_CHAR")
	private String estimateNoChar;

	@Column(name="ESTIMATED_BY")
	private String estimatedBy;

	@Column(name="ESTIMATED_DATE")
	private Timestamp estimatedDate;

	@Column(name="EXISTING_ACC_NO")
	private String existingAccNo;

	@Column(name="HARD_CLOSED_BY")
	private String hardClosedBy;

	@Column(name="HARD_CLOSED_DATE")
	private Timestamp hardClosedDate;

	@Column(name="ID_NO")
	private String idNo;

	@Column(name="JOB_ALLOCATED_BY")
	private String jobAllocatedBy;

	@Column(name="JOB_ALLOCATED_DATE")
	private Timestamp jobAllocatedDate;

	@Column(name="JOB_APPROVED_BY")
	private String jobApprovedBy;

	@Column(name="JOB_APPROVED_DATE")
	private Timestamp jobApprovedDate;

	@Column(name="JOB_CREATED_BY")
	private String jobCreatedBy;

	@Column(name="JOB_CREATED_DATE")
	private Timestamp jobCreatedDate;

	@Column(name="JOB_DEPT_ID")
	private String jobDeptId;

	@Column(name="JOB_EXPORTED_BY")
	private String jobExportedBy;

	@Column(name="JOB_EXPORTED_DATE")
	private Timestamp jobExportedDate;

	@Column(name="JOB_FINISHED_BY")
	private String jobFinishedBy;

	@Column(name="JOB_FINISHED_DATE")
	private Timestamp jobFinishedDate;

	@Column(name="JOB_NO")
	private String jobNo;

	@Column(name="LOAN_TYPE")
	private String loanType;

	@Column(name="LOCATION_VISITED_BY")
	private String locationVisitedBy;

	@Column(name="LOCATION_VISITED_DATE")
	private Timestamp locationVisitedDate;

	@Column(name="ORIGINATED_BY")
	private String originatedBy;

	private BigDecimal phase;

	@Column(name="PIV_RIP_AMOUNT")
	private BigDecimal pivRipAmount;

	@Column(name="PIV_RIP_COMFIRMED_BY")
	private String pivRipComfirmedBy;

	@Column(name="PIV_RIP_COMFIRMED_DATE")
	private Timestamp pivRipComfirmedDate;

	@Column(name="PIV_RIP_ISSUED_BY")
	private String pivRipIssuedBy;

	@Column(name="PIV_RIP_ISSUED_DATE")
	private Timestamp pivRipIssuedDate;

	@Column(name="PIV_RIP_NO")
	private String pivRipNo;

	@Column(name="PIV_RIP_PAID_DATE")
	private Timestamp pivRipPaidDate;

	@Column(name="PIV1_AMOUNT")
	private BigDecimal piv1Amount;

	@Column(name="PIV1_COMFIRMED_BY")
	private String piv1ComfirmedBy;

	@Column(name="PIV1_COMFIRMED_DATE")
	private Timestamp piv1ComfirmedDate;

	@Column(name="PIV1_ISSUED_BY")
	private String piv1IssuedBy;

	@Column(name="PIV1_ISSUED_DATE")
	private Timestamp piv1IssuedDate;

	@Column(name="PIV1_NO")
	private String piv1No;

	@Column(name="PIV1_PAID_DATE")
	private Timestamp piv1PaidDate;

	@Column(name="PIV2_AMOUNT")
	private BigDecimal piv2Amount;

	@Column(name="PIV2_COMFIRMED_BY")
	private String piv2ComfirmedBy;

	@Column(name="PIV2_COMFIRMED_DATE")
	private Timestamp piv2ComfirmedDate;

	@Column(name="PIV2_ELN_AMOUNT")
	private BigDecimal piv2ElnAmount;

	@Column(name="PIV2_ELN_COMFIRMED_BY")
	private String piv2ElnComfirmedBy;

	@Column(name="PIV2_ELN_COMFIRMED_DATE")
	private Timestamp piv2ElnComfirmedDate;

	@Column(name="PIV2_ELN_ISSUED_BY")
	private String piv2ElnIssuedBy;

	@Column(name="PIV2_ELN_ISSUED_DATE")
	private Timestamp piv2ElnIssuedDate;

	@Column(name="PIV2_ELN_NO")
	private String piv2ElnNo;

	@Column(name="PIV2_ELN_PAID_DATE")
	private Timestamp piv2ElnPaidDate;

	@Column(name="PIV2_ESD_AMOUNT")
	private BigDecimal piv2EsdAmount;

	@Column(name="PIV2_ESD_COMFIRMED_BY")
	private String piv2EsdComfirmedBy;

	@Column(name="PIV2_ESD_COMFIRMED_DATE")
	private Timestamp piv2EsdComfirmedDate;

	@Column(name="PIV2_ESD_ISSUED_BY")
	private String piv2EsdIssuedBy;

	@Column(name="PIV2_ESD_ISSUED_DATE")
	private Timestamp piv2EsdIssuedDate;

	@Column(name="PIV2_ESD_NO")
	private String piv2EsdNo;

	@Column(name="PIV2_ESD_PAID_DATE")
	private Timestamp piv2EsdPaidDate;

	@Column(name="PIV2_ISSUED_BY")
	private String piv2IssuedBy;

	@Column(name="PIV2_ISSUED_DATE")
	private Timestamp piv2IssuedDate;

	@Column(name="PIV2_NO")
	private String piv2No;

	@Column(name="PIV2_PAID_DATE")
	private Timestamp piv2PaidDate;

	@Column(name="PIV3_AMOUNT")
	private BigDecimal piv3Amount;

	@Column(name="PIV3_COMFIRMED_BY")
	private String piv3ComfirmedBy;

	@Column(name="PIV3_COMFIRMED_DATE")
	private Timestamp piv3ComfirmedDate;

	@Column(name="PIV3_ISSUED_BY")
	private String piv3IssuedBy;

	@Column(name="PIV3_ISSUED_DATE")
	private Timestamp piv3IssuedDate;

	@Column(name="PIV3_NO")
	private String piv3No;

	@Column(name="PIV3_PAID_DATE")
	private Timestamp piv3PaidDate;

	@Column(name="SMC_STATUS")
	private String smcStatus;

	@Column(name="SOFT_CLOSED_BY")
	private String softClosedBy;

	@Column(name="SOFT_CLOSED_DATE")
	private Timestamp softClosedDate;

	private String status;

	@Column(name="STATUS_CHANGED_BY")
	private String statusChangedBy;

	@Column(name="STATUS_CHANGED_DATE")
	private Timestamp statusChangedDate;

	@Column(name="STATUS_CHANGED_REASON")
	private String statusChangedReason;

	@Column(name="STD_COST")
	private BigDecimal stdCost;

	@Column(name="TARIFF_CAT_CODE")
	private String tariffCatCode;

	@Column(name="TARIFF_CODE")
	private String tariffCode;

	public Dashboard() {
	}

	public String getApplicationId() {
		return this.applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getAccOpenedBy() {
		return this.accOpenedBy;
	}

	public void setAccOpenedBy(String accOpenedBy) {
		this.accOpenedBy = accOpenedBy;
	}

	public Timestamp getAccOpenedDate() {
		return this.accOpenedDate;
	}

	public void setAccOpenedDate(Timestamp accOpenedDate) {
		this.accOpenedDate = accOpenedDate;
	}

	public String getAccountNo() {
		return this.accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAppSubmittedBy() {
		return this.appSubmittedBy;
	}

	public void setAppSubmittedBy(String appSubmittedBy) {
		this.appSubmittedBy = appSubmittedBy;
	}

	public Timestamp getAppSubmittedDate() {
		return this.appSubmittedDate;
	}

	public void setAppSubmittedDate(Timestamp appSubmittedDate) {
		this.appSubmittedDate = appSubmittedDate;
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

	public String getAppointmentBy() {
		return this.appointmentBy;
	}

	public void setAppointmentBy(String appointmentBy) {
		this.appointmentBy = appointmentBy;
	}

	public Timestamp getAppointmentDate() {
		return this.appointmentDate;
	}

	public void setAppointmentDate(Timestamp appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getBillCreatedBy() {
		return this.billCreatedBy;
	}

	public void setBillCreatedBy(String billCreatedBy) {
		this.billCreatedBy = billCreatedBy;
	}

	public Timestamp getBillCreatedDate() {
		return this.billCreatedDate;
	}

	public void setBillCreatedDate(Timestamp billCreatedDate) {
		this.billCreatedDate = billCreatedDate;
	}

	public BigDecimal getConnectionType() {
		return this.connectionType;
	}

	public void setConnectionType(BigDecimal connectionType) {
		this.connectionType = connectionType;
	}

	public String getConsumerAddress() {
		return this.consumerAddress;
	}

	public void setConsumerAddress(String consumerAddress) {
		this.consumerAddress = consumerAddress;
	}

	public String getConsumerName() {
		return this.consumerName;
	}

	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}

	public String getContractorId() {
		return this.contractorId;
	}

	public void setContractorId(String contractorId) {
		this.contractorId = contractorId;
	}

	public String getContractorName() {
		return this.contractorName;
	}

	public void setContractorName(String contractorName) {
		this.contractorName = contractorName;
	}

	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public BigDecimal getDetailedCost() {
		return this.detailedCost;
	}

	public void setDetailedCost(BigDecimal detailedCost) {
		this.detailedCost = detailedCost;
	}

	public String getEstimateApprovedBy() {
		return this.estimateApprovedBy;
	}

	public void setEstimateApprovedBy(String estimateApprovedBy) {
		this.estimateApprovedBy = estimateApprovedBy;
	}

	public Timestamp getEstimateApprovedDate() {
		return this.estimateApprovedDate;
	}

	public void setEstimateApprovedDate(Timestamp estimateApprovedDate) {
		this.estimateApprovedDate = estimateApprovedDate;
	}

	public String getEstimateNo() {
		return this.estimateNo;
	}

	public void setEstimateNo(String estimateNo) {
		this.estimateNo = estimateNo;
	}

	public String getEstimateNoChar() {
		return this.estimateNoChar;
	}

	public void setEstimateNoChar(String estimateNoChar) {
		this.estimateNoChar = estimateNoChar;
	}

	public String getEstimatedBy() {
		return this.estimatedBy;
	}

	public void setEstimatedBy(String estimatedBy) {
		this.estimatedBy = estimatedBy;
	}

	public Timestamp getEstimatedDate() {
		return this.estimatedDate;
	}

	public void setEstimatedDate(Timestamp estimatedDate) {
		this.estimatedDate = estimatedDate;
	}

	public String getExistingAccNo() {
		return this.existingAccNo;
	}

	public void setExistingAccNo(String existingAccNo) {
		this.existingAccNo = existingAccNo;
	}

	public String getHardClosedBy() {
		return this.hardClosedBy;
	}

	public void setHardClosedBy(String hardClosedBy) {
		this.hardClosedBy = hardClosedBy;
	}

	public Timestamp getHardClosedDate() {
		return this.hardClosedDate;
	}

	public void setHardClosedDate(Timestamp hardClosedDate) {
		this.hardClosedDate = hardClosedDate;
	}

	public String getIdNo() {
		return this.idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getJobAllocatedBy() {
		return this.jobAllocatedBy;
	}

	public void setJobAllocatedBy(String jobAllocatedBy) {
		this.jobAllocatedBy = jobAllocatedBy;
	}

	public Timestamp getJobAllocatedDate() {
		return this.jobAllocatedDate;
	}

	public void setJobAllocatedDate(Timestamp jobAllocatedDate) {
		this.jobAllocatedDate = jobAllocatedDate;
	}

	public String getJobApprovedBy() {
		return this.jobApprovedBy;
	}

	public void setJobApprovedBy(String jobApprovedBy) {
		this.jobApprovedBy = jobApprovedBy;
	}

	public Timestamp getJobApprovedDate() {
		return this.jobApprovedDate;
	}

	public void setJobApprovedDate(Timestamp jobApprovedDate) {
		this.jobApprovedDate = jobApprovedDate;
	}

	public String getJobCreatedBy() {
		return this.jobCreatedBy;
	}

	public void setJobCreatedBy(String jobCreatedBy) {
		this.jobCreatedBy = jobCreatedBy;
	}

	public Timestamp getJobCreatedDate() {
		return this.jobCreatedDate;
	}

	public void setJobCreatedDate(Timestamp jobCreatedDate) {
		this.jobCreatedDate = jobCreatedDate;
	}

	public String getJobDeptId() {
		return this.jobDeptId;
	}

	public void setJobDeptId(String jobDeptId) {
		this.jobDeptId = jobDeptId;
	}

	public String getJobExportedBy() {
		return this.jobExportedBy;
	}

	public void setJobExportedBy(String jobExportedBy) {
		this.jobExportedBy = jobExportedBy;
	}

	public Timestamp getJobExportedDate() {
		return this.jobExportedDate;
	}

	public void setJobExportedDate(Timestamp jobExportedDate) {
		this.jobExportedDate = jobExportedDate;
	}

	public String getJobFinishedBy() {
		return this.jobFinishedBy;
	}

	public void setJobFinishedBy(String jobFinishedBy) {
		this.jobFinishedBy = jobFinishedBy;
	}

	public Timestamp getJobFinishedDate() {
		return this.jobFinishedDate;
	}

	public void setJobFinishedDate(Timestamp jobFinishedDate) {
		this.jobFinishedDate = jobFinishedDate;
	}

	public String getJobNo() {
		return this.jobNo;
	}

	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}

	public String getLoanType() {
		return this.loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public String getLocationVisitedBy() {
		return this.locationVisitedBy;
	}

	public void setLocationVisitedBy(String locationVisitedBy) {
		this.locationVisitedBy = locationVisitedBy;
	}

	public Timestamp getLocationVisitedDate() {
		return this.locationVisitedDate;
	}

	public void setLocationVisitedDate(Timestamp locationVisitedDate) {
		this.locationVisitedDate = locationVisitedDate;
	}

	public String getOriginatedBy() {
		return this.originatedBy;
	}

	public void setOriginatedBy(String originatedBy) {
		this.originatedBy = originatedBy;
	}

	public BigDecimal getPhase() {
		return this.phase;
	}

	public void setPhase(BigDecimal phase) {
		this.phase = phase;
	}

	public BigDecimal getPivRipAmount() {
		return this.pivRipAmount;
	}

	public void setPivRipAmount(BigDecimal pivRipAmount) {
		this.pivRipAmount = pivRipAmount;
	}

	public String getPivRipComfirmedBy() {
		return this.pivRipComfirmedBy;
	}

	public void setPivRipComfirmedBy(String pivRipComfirmedBy) {
		this.pivRipComfirmedBy = pivRipComfirmedBy;
	}

	public Timestamp getPivRipComfirmedDate() {
		return this.pivRipComfirmedDate;
	}

	public void setPivRipComfirmedDate(Timestamp pivRipComfirmedDate) {
		this.pivRipComfirmedDate = pivRipComfirmedDate;
	}

	public String getPivRipIssuedBy() {
		return this.pivRipIssuedBy;
	}

	public void setPivRipIssuedBy(String pivRipIssuedBy) {
		this.pivRipIssuedBy = pivRipIssuedBy;
	}

	public Timestamp getPivRipIssuedDate() {
		return this.pivRipIssuedDate;
	}

	public void setPivRipIssuedDate(Timestamp pivRipIssuedDate) {
		this.pivRipIssuedDate = pivRipIssuedDate;
	}

	public String getPivRipNo() {
		return this.pivRipNo;
	}

	public void setPivRipNo(String pivRipNo) {
		this.pivRipNo = pivRipNo;
	}

	public Timestamp getPivRipPaidDate() {
		return this.pivRipPaidDate;
	}

	public void setPivRipPaidDate(Timestamp pivRipPaidDate) {
		this.pivRipPaidDate = pivRipPaidDate;
	}

	public BigDecimal getPiv1Amount() {
		return this.piv1Amount;
	}

	public void setPiv1Amount(BigDecimal piv1Amount) {
		this.piv1Amount = piv1Amount;
	}

	public String getPiv1ComfirmedBy() {
		return this.piv1ComfirmedBy;
	}

	public void setPiv1ComfirmedBy(String piv1ComfirmedBy) {
		this.piv1ComfirmedBy = piv1ComfirmedBy;
	}

	public Timestamp getPiv1ComfirmedDate() {
		return this.piv1ComfirmedDate;
	}

	public void setPiv1ComfirmedDate(Timestamp piv1ComfirmedDate) {
		this.piv1ComfirmedDate = piv1ComfirmedDate;
	}

	public String getPiv1IssuedBy() {
		return this.piv1IssuedBy;
	}

	public void setPiv1IssuedBy(String piv1IssuedBy) {
		this.piv1IssuedBy = piv1IssuedBy;
	}

	public Timestamp getPiv1IssuedDate() {
		return this.piv1IssuedDate;
	}

	public void setPiv1IssuedDate(Timestamp piv1IssuedDate) {
		this.piv1IssuedDate = piv1IssuedDate;
	}

	public String getPiv1No() {
		return this.piv1No;
	}

	public void setPiv1No(String piv1No) {
		this.piv1No = piv1No;
	}

	public Timestamp getPiv1PaidDate() {
		return this.piv1PaidDate;
	}

	public void setPiv1PaidDate(Timestamp piv1PaidDate) {
		this.piv1PaidDate = piv1PaidDate;
	}

	public BigDecimal getPiv2Amount() {
		return this.piv2Amount;
	}

	public void setPiv2Amount(BigDecimal piv2Amount) {
		this.piv2Amount = piv2Amount;
	}

	public String getPiv2ComfirmedBy() {
		return this.piv2ComfirmedBy;
	}

	public void setPiv2ComfirmedBy(String piv2ComfirmedBy) {
		this.piv2ComfirmedBy = piv2ComfirmedBy;
	}

	public Timestamp getPiv2ComfirmedDate() {
		return this.piv2ComfirmedDate;
	}

	public void setPiv2ComfirmedDate(Timestamp piv2ComfirmedDate) {
		this.piv2ComfirmedDate = piv2ComfirmedDate;
	}

	public BigDecimal getPiv2ElnAmount() {
		return this.piv2ElnAmount;
	}

	public void setPiv2ElnAmount(BigDecimal piv2ElnAmount) {
		this.piv2ElnAmount = piv2ElnAmount;
	}

	public String getPiv2ElnComfirmedBy() {
		return this.piv2ElnComfirmedBy;
	}

	public void setPiv2ElnComfirmedBy(String piv2ElnComfirmedBy) {
		this.piv2ElnComfirmedBy = piv2ElnComfirmedBy;
	}

	public Timestamp getPiv2ElnComfirmedDate() {
		return this.piv2ElnComfirmedDate;
	}

	public void setPiv2ElnComfirmedDate(Timestamp piv2ElnComfirmedDate) {
		this.piv2ElnComfirmedDate = piv2ElnComfirmedDate;
	}

	public String getPiv2ElnIssuedBy() {
		return this.piv2ElnIssuedBy;
	}

	public void setPiv2ElnIssuedBy(String piv2ElnIssuedBy) {
		this.piv2ElnIssuedBy = piv2ElnIssuedBy;
	}

	public Timestamp getPiv2ElnIssuedDate() {
		return this.piv2ElnIssuedDate;
	}

	public void setPiv2ElnIssuedDate(Timestamp piv2ElnIssuedDate) {
		this.piv2ElnIssuedDate = piv2ElnIssuedDate;
	}

	public String getPiv2ElnNo() {
		return this.piv2ElnNo;
	}

	public void setPiv2ElnNo(String piv2ElnNo) {
		this.piv2ElnNo = piv2ElnNo;
	}

	public Timestamp getPiv2ElnPaidDate() {
		return this.piv2ElnPaidDate;
	}

	public void setPiv2ElnPaidDate(Timestamp piv2ElnPaidDate) {
		this.piv2ElnPaidDate = piv2ElnPaidDate;
	}

	public BigDecimal getPiv2EsdAmount() {
		return this.piv2EsdAmount;
	}

	public void setPiv2EsdAmount(BigDecimal piv2EsdAmount) {
		this.piv2EsdAmount = piv2EsdAmount;
	}

	public String getPiv2EsdComfirmedBy() {
		return this.piv2EsdComfirmedBy;
	}

	public void setPiv2EsdComfirmedBy(String piv2EsdComfirmedBy) {
		this.piv2EsdComfirmedBy = piv2EsdComfirmedBy;
	}

	public Timestamp getPiv2EsdComfirmedDate() {
		return this.piv2EsdComfirmedDate;
	}

	public void setPiv2EsdComfirmedDate(Timestamp piv2EsdComfirmedDate) {
		this.piv2EsdComfirmedDate = piv2EsdComfirmedDate;
	}

	public String getPiv2EsdIssuedBy() {
		return this.piv2EsdIssuedBy;
	}

	public void setPiv2EsdIssuedBy(String piv2EsdIssuedBy) {
		this.piv2EsdIssuedBy = piv2EsdIssuedBy;
	}

	public Timestamp getPiv2EsdIssuedDate() {
		return this.piv2EsdIssuedDate;
	}

	public void setPiv2EsdIssuedDate(Timestamp piv2EsdIssuedDate) {
		this.piv2EsdIssuedDate = piv2EsdIssuedDate;
	}

	public String getPiv2EsdNo() {
		return this.piv2EsdNo;
	}

	public void setPiv2EsdNo(String piv2EsdNo) {
		this.piv2EsdNo = piv2EsdNo;
	}

	public Timestamp getPiv2EsdPaidDate() {
		return this.piv2EsdPaidDate;
	}

	public void setPiv2EsdPaidDate(Timestamp piv2EsdPaidDate) {
		this.piv2EsdPaidDate = piv2EsdPaidDate;
	}

	public String getPiv2IssuedBy() {
		return this.piv2IssuedBy;
	}

	public void setPiv2IssuedBy(String piv2IssuedBy) {
		this.piv2IssuedBy = piv2IssuedBy;
	}

	public Timestamp getPiv2IssuedDate() {
		return this.piv2IssuedDate;
	}

	public void setPiv2IssuedDate(Timestamp piv2IssuedDate) {
		this.piv2IssuedDate = piv2IssuedDate;
	}

	public String getPiv2No() {
		return this.piv2No;
	}

	public void setPiv2No(String piv2No) {
		this.piv2No = piv2No;
	}

	public Timestamp getPiv2PaidDate() {
		return this.piv2PaidDate;
	}

	public void setPiv2PaidDate(Timestamp piv2PaidDate) {
		this.piv2PaidDate = piv2PaidDate;
	}

	public BigDecimal getPiv3Amount() {
		return this.piv3Amount;
	}

	public void setPiv3Amount(BigDecimal piv3Amount) {
		this.piv3Amount = piv3Amount;
	}

	public String getPiv3ComfirmedBy() {
		return this.piv3ComfirmedBy;
	}

	public void setPiv3ComfirmedBy(String piv3ComfirmedBy) {
		this.piv3ComfirmedBy = piv3ComfirmedBy;
	}

	public Timestamp getPiv3ComfirmedDate() {
		return this.piv3ComfirmedDate;
	}

	public void setPiv3ComfirmedDate(Timestamp piv3ComfirmedDate) {
		this.piv3ComfirmedDate = piv3ComfirmedDate;
	}

	public String getPiv3IssuedBy() {
		return this.piv3IssuedBy;
	}

	public void setPiv3IssuedBy(String piv3IssuedBy) {
		this.piv3IssuedBy = piv3IssuedBy;
	}

	public Timestamp getPiv3IssuedDate() {
		return this.piv3IssuedDate;
	}

	public void setPiv3IssuedDate(Timestamp piv3IssuedDate) {
		this.piv3IssuedDate = piv3IssuedDate;
	}

	public String getPiv3No() {
		return this.piv3No;
	}

	public void setPiv3No(String piv3No) {
		this.piv3No = piv3No;
	}

	public Timestamp getPiv3PaidDate() {
		return this.piv3PaidDate;
	}

	public void setPiv3PaidDate(Timestamp piv3PaidDate) {
		this.piv3PaidDate = piv3PaidDate;
	}

	public String getSmcStatus() {
		return this.smcStatus;
	}

	public void setSmcStatus(String smcStatus) {
		this.smcStatus = smcStatus;
	}

	public String getSoftClosedBy() {
		return this.softClosedBy;
	}

	public void setSoftClosedBy(String softClosedBy) {
		this.softClosedBy = softClosedBy;
	}

	public Timestamp getSoftClosedDate() {
		return this.softClosedDate;
	}

	public void setSoftClosedDate(Timestamp softClosedDate) {
		this.softClosedDate = softClosedDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusChangedBy() {
		return this.statusChangedBy;
	}

	public void setStatusChangedBy(String statusChangedBy) {
		this.statusChangedBy = statusChangedBy;
	}

	public Timestamp getStatusChangedDate() {
		return this.statusChangedDate;
	}

	public void setStatusChangedDate(Timestamp statusChangedDate) {
		this.statusChangedDate = statusChangedDate;
	}

	public String getStatusChangedReason() {
		return this.statusChangedReason;
	}

	public void setStatusChangedReason(String statusChangedReason) {
		this.statusChangedReason = statusChangedReason;
	}

	public BigDecimal getStdCost() {
		return this.stdCost;
	}

	public void setStdCost(BigDecimal stdCost) {
		this.stdCost = stdCost;
	}

	public String getTariffCatCode() {
		return this.tariffCatCode;
	}

	public void setTariffCatCode(String tariffCatCode) {
		this.tariffCatCode = tariffCatCode;
	}

	public String getTariffCode() {
		return this.tariffCode;
	}

	public void setTariffCode(String tariffCode) {
		this.tariffCode = tariffCode;
	}

}