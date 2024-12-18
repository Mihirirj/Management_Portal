package com.it.dashboard.master.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the GLDEPTM database table.
 * 
 */
@Entity
@NamedQuery(name="Gldeptm.findAll", query="SELECT g FROM Gldeptm g")
public class Gldeptm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="DEPT_ID")
	private String deptId;

	@Column(name="COMP_ID")
	private String compId;

	@Column(name="CS_PS")
	private String csPs;

	@Column(name="DEPT_NM")
	private String deptNm;

	@Column(name="ENT_BY")
	private String entBy;

	@Temporal(TemporalType.DATE)
	@Column(name="ENT_DT")
	private Date entDt;

	@Lob
	private String filter;

	@Column(name="LOG_ID")
	private BigDecimal logId;

	@Column(name="MODI_BY")
	private String modiBy;

	@Temporal(TemporalType.DATE)
	@Column(name="MODI_DT")
	private Date modiDt;

	private BigDecimal status;

	public Gldeptm() {
	}

	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getCompId() {
		return this.compId;
	}

	public void setCompId(String compId) {
		this.compId = compId;
	}

	public String getCsPs() {
		return this.csPs;
	}

	public void setCsPs(String csPs) {
		this.csPs = csPs;
	}

	public String getDeptNm() {
		return this.deptNm;
	}

	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
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

	public String getFilter() {
		return this.filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public BigDecimal getLogId() {
		return this.logId;
	}

	public void setLogId(BigDecimal logId) {
		this.logId = logId;
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

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

}