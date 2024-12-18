package com.it.dashboard.admin.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SAUSERM database table.
 * 
 */
@Entity
@NamedQuery(name="Sauserm.findAll", query="SELECT s FROM Sauserm s")
public class Sauserm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="USER_ID")
	private String userId;

	@Column(name="ACESS_AUTH")
	private BigDecimal acessAuth;

	@Temporal(TemporalType.DATE)
	@Column(name="CHANGE_DT")
	private Date changeDt;

	@Column(name="CHANGE_FRQ")
	private BigDecimal changeFrq;

	@Column(name="CONF_BY")
	private String confBy;

	@Temporal(TemporalType.DATE)
	@Column(name="CONF_DT")
	private Date confDt;

	@Column(name="ENT_BY")
	private String entBy;

	@Temporal(TemporalType.DATE)
	@Column(name="ENT_DT")
	private Date entDt;

	@Column(name="EXP_STATUS")
	private String expStatus;

	@Temporal(TemporalType.DATE)
	@Column(name="EXPIRY_DT")
	private Date expiryDt;

	@Column(name="JOB_TITLE")
	private String jobTitle;

	@Column(name="LOG_ID")
	private BigDecimal logId;

	@Column(name="LOG_STATUS")
	private String logStatus;

	@Column(name="LOGIN_PRD")
	private BigDecimal loginPrd;

	@Column(name="MODI_BY")
	private String modiBy;

	@Temporal(TemporalType.DATE)
	@Column(name="MODI_DT")
	private Date modiDt;

	@Column(name="OLD_PSWRD")
	private String oldPswrd;

	private String pswrd;

	@Column(name="PSWRD_HINT")
	private String pswrdHint;

	@Column(name="RPT_USER")
	private String rptUser;

	private BigDecimal status;

	@Column(name="TRXN_AUTH")
	private BigDecimal trxnAuth;

	@Column(name="USER_LEVEL")
	private String userLevel;

	@Column(name="USER_NM")
	private String userNm;

	@Column(name="VALID_PRD")
	private BigDecimal validPrd;

	@Column(name="WARN_PRD")
	private BigDecimal warnPrd;

	public Sauserm() {
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public BigDecimal getAcessAuth() {
		return this.acessAuth;
	}

	public void setAcessAuth(BigDecimal acessAuth) {
		this.acessAuth = acessAuth;
	}

	public Date getChangeDt() {
		return this.changeDt;
	}

	public void setChangeDt(Date changeDt) {
		this.changeDt = changeDt;
	}

	public BigDecimal getChangeFrq() {
		return this.changeFrq;
	}

	public void setChangeFrq(BigDecimal changeFrq) {
		this.changeFrq = changeFrq;
	}

	public String getConfBy() {
		return this.confBy;
	}

	public void setConfBy(String confBy) {
		this.confBy = confBy;
	}

	public Date getConfDt() {
		return this.confDt;
	}

	public void setConfDt(Date confDt) {
		this.confDt = confDt;
	}

	public String getEntBy() {
		return this.entBy;
	}

	public void setEntBy(String entBy) {
		this.entBy = entBy;
	}

	public Date getEntDt() {
		return this.entDt;
	}

	public void setEntDt(Date entDt) {
		this.entDt = entDt;
	}

	public String getExpStatus() {
		return this.expStatus;
	}

	public void setExpStatus(String expStatus) {
		this.expStatus = expStatus;
	}

	public Date getExpiryDt() {
		return this.expiryDt;
	}

	public void setExpiryDt(Date expiryDt) {
		this.expiryDt = expiryDt;
	}

	public String getJobTitle() {
		return this.jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public BigDecimal getLogId() {
		return this.logId;
	}

	public void setLogId(BigDecimal logId) {
		this.logId = logId;
	}

	public String getLogStatus() {
		return this.logStatus;
	}

	public void setLogStatus(String logStatus) {
		this.logStatus = logStatus;
	}

	public BigDecimal getLoginPrd() {
		return this.loginPrd;
	}

	public void setLoginPrd(BigDecimal loginPrd) {
		this.loginPrd = loginPrd;
	}

	public String getModiBy() {
		return this.modiBy;
	}

	public void setModiBy(String modiBy) {
		this.modiBy = modiBy;
	}

	public Date getModiDt() {
		return this.modiDt;
	}

	public void setModiDt(Date modiDt) {
		this.modiDt = modiDt;
	}

	public String getOldPswrd() {
		return this.oldPswrd;
	}

	public void setOldPswrd(String oldPswrd) {
		this.oldPswrd = oldPswrd;
	}

	public String getPswrd() {
		return this.pswrd;
	}

	public void setPswrd(String pswrd) {
		this.pswrd = pswrd;
	}

	public String getPswrdHint() {
		return this.pswrdHint;
	}

	public void setPswrdHint(String pswrdHint) {
		this.pswrdHint = pswrdHint;
	}

	public String getRptUser() {
		return this.rptUser;
	}

	public void setRptUser(String rptUser) {
		this.rptUser = rptUser;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public BigDecimal getTrxnAuth() {
		return this.trxnAuth;
	}

	public void setTrxnAuth(BigDecimal trxnAuth) {
		this.trxnAuth = trxnAuth;
	}

	public String getUserLevel() {
		return this.userLevel;
	}

	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}

	public String getUserNm() {
		return this.userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public BigDecimal getValidPrd() {
		return this.validPrd;
	}

	public void setValidPrd(BigDecimal validPrd) {
		this.validPrd = validPrd;
	}

	public BigDecimal getWarnPrd() {
		return this.warnPrd;
	}

	public void setWarnPrd(BigDecimal warnPrd) {
		this.warnPrd = warnPrd;
	}

}