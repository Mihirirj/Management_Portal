package com.it.dashboard.master.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the GLACCTM database table.
 * 
 */
@Entity
@NamedQuery(name="Glacctm.findAll", query="SELECT g FROM Glacctm g")
public class Glacctm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="AC_CD")
	private String acCd;

	@Column(name="AC_NM")
	private String acNm;

	@Column(name="AC_TYPE")
	private BigDecimal acType;

	@Column(name="BS_PNL")
	private String bsPnl;

	@Column(name="ENT_BY")
	private String entBy;

	@Temporal(TemporalType.DATE)
	@Column(name="ENT_DT")
	private Date entDt;

	@Lob
	private String filter;

	@Column(name="IS_REFUND")
	private String isRefund;

	@Column(name="IS_TAX")
	private String isTax;

	@Column(name="LOG_ID")
	private BigDecimal logId;

	@Column(name="LVL_NO")
	private BigDecimal lvlNo;

	@Column(name="MODI_BY")
	private String modiBy;

	@Temporal(TemporalType.DATE)
	@Column(name="MODI_DT")
	private Date modiDt;

	@Column(name="PRENT_AC")
	private String prentAc;

	@Column(name="SORT_SEQ")
	private BigDecimal sortSeq;

	private BigDecimal status;

	public Glacctm() {
	}

	public String getAcCd() {
		return this.acCd;
	}

	public void setAcCd(String acCd) {
		this.acCd = acCd;
	}

	public String getAcNm() {
		return this.acNm;
	}

	public void setAcNm(String acNm) {
		this.acNm = acNm;
	}

	public BigDecimal getAcType() {
		return this.acType;
	}

	public void setAcType(BigDecimal acType) {
		this.acType = acType;
	}

	public String getBsPnl() {
		return this.bsPnl;
	}

	public void setBsPnl(String bsPnl) {
		this.bsPnl = bsPnl;
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

	public String getIsRefund() {
		return this.isRefund;
	}

	public void setIsRefund(String isRefund) {
		this.isRefund = isRefund;
	}

	public String getIsTax() {
		return this.isTax;
	}

	public void setIsTax(String isTax) {
		this.isTax = isTax;
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

	public String getPrentAc() {
		return this.prentAc;
	}

	public void setPrentAc(String prentAc) {
		this.prentAc = prentAc;
	}

	public BigDecimal getSortSeq() {
		return this.sortSeq;
	}

	public void setSortSeq(BigDecimal sortSeq) {
		this.sortSeq = sortSeq;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

}