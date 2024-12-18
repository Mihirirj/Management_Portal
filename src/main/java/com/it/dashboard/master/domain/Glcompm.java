package com.it.dashboard.master.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the GLCOMPM database table.
 * 
 */
@Entity
@NamedQuery(name="Glcompm.findAll", query="SELECT g FROM Glcompm g")
public class Glcompm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="COMP_ID")
	private String compId;

	@Column(name="ADD_1")
	private String add1;

	@Column(name="ADD_2")
	private String add2;

	@Column(name="ADD_3")
	private String add3;

	@Column(name="ADD_4")
	private String add4;

	@Column(name="COMP_NM")
	private String compNm;

	@Column(name="E_MAIL")
	private String eMail;

	@Column(name="ENT_BY")
	private String entBy;

	@Temporal(TemporalType.DATE)
	@Column(name="ENT_DT")
	private Date entDt;

	@Column(name="FAX_1")
	private String fax1;

	@Column(name="FAX_2")
	private String fax2;

	@Lob
	private String filter;

	@Column(name="GRP_COMP")
	private String grpComp;

	@Column(name="LOG_ID")
	private BigDecimal logId;

	@Column(name="LVL_NO")
	private BigDecimal lvlNo;

	@Column(name="MODI_BY")
	private String modiBy;

	@Temporal(TemporalType.DATE)
	@Column(name="MODI_DT")
	private Date modiDt;

	@Column(name="PARENT_ID")
	private String parentId;

	private BigDecimal status;

	@Column(name="TEL_1")
	private String tel1;

	@Column(name="TEL_2")
	private String tel2;

	public Glcompm() {
	}

	public String getCompId() {
		return this.compId;
	}

	public void setCompId(String compId) {
		this.compId = compId;
	}

	public String getAdd1() {
		return this.add1;
	}

	public void setAdd1(String add1) {
		this.add1 = add1;
	}

	public String getAdd2() {
		return this.add2;
	}

	public void setAdd2(String add2) {
		this.add2 = add2;
	}

	public String getAdd3() {
		return this.add3;
	}

	public void setAdd3(String add3) {
		this.add3 = add3;
	}

	public String getAdd4() {
		return this.add4;
	}

	public void setAdd4(String add4) {
		this.add4 = add4;
	}

	public String getCompNm() {
		return this.compNm;
	}

	public void setCompNm(String compNm) {
		this.compNm = compNm;
	}

	public String getEMail() {
		return this.eMail;
	}

	public void setEMail(String eMail) {
		this.eMail = eMail;
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

	public String getFax1() {
		return this.fax1;
	}

	public void setFax1(String fax1) {
		this.fax1 = fax1;
	}

	public String getFax2() {
		return this.fax2;
	}

	public void setFax2(String fax2) {
		this.fax2 = fax2;
	}

	public String getFilter() {
		return this.filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public String getGrpComp() {
		return this.grpComp;
	}

	public void setGrpComp(String grpComp) {
		this.grpComp = grpComp;
	}

	public BigDecimal getLogId() {
		return this.logId;
	}

	public void setLogId(BigDecimal logId) {
		this.logId = logId;
	}

	public BigDecimal getLvlNo() {
		return this.lvlNo;
	}

	public void setLvlNo(BigDecimal lvlNo) {
		this.lvlNo = lvlNo;
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

	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public String getTel1() {
		return this.tel1;
	}

	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}

	public String getTel2() {
		return this.tel2;
	}

	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}

}