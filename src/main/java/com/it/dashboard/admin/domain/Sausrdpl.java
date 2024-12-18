package com.it.dashboard.admin.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SAUSRDPL database table.
 * 
 */
@Entity
@NamedQuery(name="Sausrdpl.findAll", query="SELECT s FROM Sausrdpl s")
public class Sausrdpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SausrdplPK id;

	@Column(name="COMP_ID")
	private String compId;

	@Column(name="CONF_BY")
	private String confBy;

	@Temporal(TemporalType.DATE)
	@Column(name="CONF_DT")
	private Date confDt;

	@Column(name="DEPT_ID")
	private String deptId;

	@Column(name="ENT_BY")
	private String entBy;

	@Temporal(TemporalType.DATE)
	@Column(name="ENT_DT")
	private Date entDt;

	@Column(name="GROUP_ID")
	private String groupId;

	@Temporal(TemporalType.DATE)
	@Column(name="LOG_DATE")
	private Date logDate;

	@Temporal(TemporalType.DATE)
	@Column(name="LOG_TIME")
	private Date logTime;

	@Column(name="MODI_BY")
	private String modiBy;

	@Temporal(TemporalType.DATE)
	@Column(name="MODI_DT")
	private Date modiDt;

	private String oprn;

	private BigDecimal status;

	@Column(name="USER_ID")
	private String userId;

	public Sausrdpl() {
	}

	public SausrdplPK getId() {
		return this.id;
	}

	public void setId(SausrdplPK id) {
		this.id = id;
	}

	public String getCompId() {
		return this.compId;
	}

	public void setCompId(String compId) {
		this.compId = compId;
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

	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
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

	public String getGroupId() {
		return this.groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public Date getLogDate() {
		return this.logDate;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

	public Date getLogTime() {
		return this.logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
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

	public String getOprn() {
		return this.oprn;
	}

	public void setOprn(String oprn) {
		this.oprn = oprn;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}