package com.it.dashboard.admin.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SAUSRDPM database table.
 * 
 */
@Entity
@NamedQuery(name="Sausrdpm.findAll", query="SELECT s FROM Sausrdpm s")
public class Sausrdpm implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SausrdpmPK id;

	@Column(name="COMP_ID")
	private String compId;

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

	@Column(name="GROUP_ID")
	private String groupId;

	@Column(name="LOG_ID")
	private BigDecimal logId;

	@Column(name="MODI_BY")
	private String modiBy;

	@Temporal(TemporalType.DATE)
	@Column(name="MODI_DT")
	private Date modiDt;

	private BigDecimal status;

	public Sausrdpm() {
	}

	public SausrdpmPK getId() {
		return this.id;
	}

	public void setId(SausrdpmPK id) {
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