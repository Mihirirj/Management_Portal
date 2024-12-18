package com.it.dashboard.tabulate.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the GLVOCHTT database table.
 * 
 */
@Entity
@Table(name="GLVOCHTT")
public class Glvochtt implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private GlvochttPK id;

	@Temporal(TemporalType.DATE)
	@Column(name="ACCT_DT")
	private Date acctDt;

	@Column(length=1)
	private String active;

	@Column(name="APPR_BY", length=12)
	private String apprBy;

	@Temporal(TemporalType.DATE)
	@Column(name="APPR_DT")
	private Date apprDt;

	@Temporal(TemporalType.DATE)
	@Column(name="APR_REJ_DT_1")
	private Date aprRejDt1;

	@Temporal(TemporalType.DATE)
	@Column(name="APR_REJ_DT_2")
	private Date aprRejDt2;

	@Column(name="APR_REJ_UID_1", length=12)
	private String aprRejUid1;

	@Column(name="APR_REJ_UID_2", length=12)
	private String aprRejUid2;

	@Column(name="BANK_AC", precision=3)
	private BigDecimal bankAc;

	@Column(name="BANK_CD", length=10)
	private String bankCd;

	@Column(name="CHQ_NO", precision=12)
	private BigDecimal chqNo;

	@Column(name="CNTRY_CD", length=5)
	private String cntryCd;

	@Column(name="CONF_BY", length=12)
	private String confBy;

	@Temporal(TemporalType.DATE)
	@Column(name="CONF_DT")
	private Date confDt;

	@Column(name="CUR_CD", length=4)
	private String curCd;

	@Temporal(TemporalType.DATE)
	@Column(name="DOC_DT")
	private Date docDt;

	@Column(name="DOC_REF", length=20)
	private String docRef;

	@Column(name="DR_CR", length=2)
	private String drCr;

	@Column(name="ENT_BY", length=12)
	private String entBy;

	@Temporal(TemporalType.DATE)
	@Column(name="ENT_DT")
	private Date entDt;

	@Column(name="EX_RT", precision=22)
	private BigDecimal exRt;

	@Column(name="FCY_VAL", precision=22)
	private BigDecimal fcyVal;

	@Column(precision=2)
	private BigDecimal freq;

	@Column(name="LCY_VAL", precision=22)
	private BigDecimal lcyVal;

	@Column(name="MODI_BY", length=12)
	private String modiBy;

	@Temporal(TemporalType.DATE)
	@Column(name="MODI_DT")
	private Date modiDt;

	@Column(name="REF_1", length=30)
	private String ref1;

	@Column(name="REF_2", length=30)
	private String ref2;

	@Column(name="REF_3", length=30)
	private String ref3;

	@Column(name="REF_4", length=30)
	private String ref4;

	@Column(length=2000)
	private String remarks;

	@Temporal(TemporalType.DATE)
	@Column(name="SENT_DT1")
	private Date sentDt1;

	@Temporal(TemporalType.DATE)
	@Column(name="SENT_DT2")
	private Date sentDt2;

	@Column(precision=2)
	private BigDecimal status;

	@Column(name="SUP_CD", length=10)
	private String supCd;

	@Column(name="TRF_DEPT", length=6)
	private String trfDept;

	@Column(name="TRF_TYPE", length=3)
	private String trfType;

	@Column(name="TRX_TYPE", length=30)
	private String trxType;

	@Column(name="TRX_VAL", precision=22)
	private BigDecimal trxVal;

	public Glvochtt() {
	}

	public GlvochttPK getId() {
		return this.id;
	}

	public void setId(GlvochttPK id) {
		this.id = id;
	}

	public Date getAcctDt() {
		return this.acctDt;
	}

	public void setAcctDt(Date acctDt) {
		this.acctDt = acctDt;
	}

	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getApprBy() {
		return this.apprBy;
	}

	public void setApprBy(String apprBy) {
		this.apprBy = apprBy;
	}

	public Date getApprDt() {
		return this.apprDt;
	}

	public void setApprDt(Date apprDt) {
		this.apprDt = apprDt;
	}

	public Date getAprRejDt1() {
		return this.aprRejDt1;
	}

	public void setAprRejDt1(Date aprRejDt1) {
		this.aprRejDt1 = aprRejDt1;
	}

	public Date getAprRejDt2() {
		return this.aprRejDt2;
	}

	public void setAprRejDt2(Date aprRejDt2) {
		this.aprRejDt2 = aprRejDt2;
	}

	public String getAprRejUid1() {
		return this.aprRejUid1;
	}

	public void setAprRejUid1(String aprRejUid1) {
		this.aprRejUid1 = aprRejUid1;
	}

	public String getAprRejUid2() {
		return this.aprRejUid2;
	}

	public void setAprRejUid2(String aprRejUid2) {
		this.aprRejUid2 = aprRejUid2;
	}

	public BigDecimal getBankAc() {
		return this.bankAc;
	}

	public void setBankAc(BigDecimal bankAc) {
		this.bankAc = bankAc;
	}

	public String getBankCd() {
		return this.bankCd;
	}

	public void setBankCd(String bankCd) {
		this.bankCd = bankCd;
	}

	public BigDecimal getChqNo() {
		return this.chqNo;
	}

	public void setChqNo(BigDecimal chqNo) {
		this.chqNo = chqNo;
	}

	public String getCntryCd() {
		return this.cntryCd;
	}

	public void setCntryCd(String cntryCd) {
		this.cntryCd = cntryCd;
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

	public String getCurCd() {
		return this.curCd;
	}

	public void setCurCd(String curCd) {
		this.curCd = curCd;
	}

	public Date getDocDt() {
		return this.docDt;
	}

	public void setDocDt(Date docDt) {
		this.docDt = docDt;
	}

	public String getDocRef() {
		return this.docRef;
	}

	public void setDocRef(String docRef) {
		this.docRef = docRef;
	}

	public String getDrCr() {
		return this.drCr;
	}

	public void setDrCr(String drCr) {
		this.drCr = drCr;
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

	public BigDecimal getExRt() {
		return this.exRt;
	}

	public void setExRt(BigDecimal exRt) {
		this.exRt = exRt;
	}

	public BigDecimal getFcyVal() {
		return this.fcyVal;
	}

	public void setFcyVal(BigDecimal fcyVal) {
		this.fcyVal = fcyVal;
	}

	public BigDecimal getFreq() {
		return this.freq;
	}

	public void setFreq(BigDecimal freq) {
		this.freq = freq;
	}

	public BigDecimal getLcyVal() {
		return this.lcyVal;
	}

	public void setLcyVal(BigDecimal lcyVal) {
		this.lcyVal = lcyVal;
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

	public String getRef1() {
		return this.ref1;
	}

	public void setRef1(String ref1) {
		this.ref1 = ref1;
	}

	public String getRef2() {
		return this.ref2;
	}

	public void setRef2(String ref2) {
		this.ref2 = ref2;
	}

	public String getRef3() {
		return this.ref3;
	}

	public void setRef3(String ref3) {
		this.ref3 = ref3;
	}

	public String getRef4() {
		return this.ref4;
	}

	public void setRef4(String ref4) {
		this.ref4 = ref4;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getSentDt1() {
		return this.sentDt1;
	}

	public void setSentDt1(Date sentDt1) {
		this.sentDt1 = sentDt1;
	}

	public Date getSentDt2() {
		return this.sentDt2;
	}

	public void setSentDt2(Date sentDt2) {
		this.sentDt2 = sentDt2;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public String getSupCd() {
		return this.supCd;
	}

	public void setSupCd(String supCd) {
		this.supCd = supCd;
	}

	public String getTrfDept() {
		return this.trfDept;
	}

	public void setTrfDept(String trfDept) {
		this.trfDept = trfDept;
	}

	public String getTrfType() {
		return this.trfType;
	}

	public void setTrfType(String trfType) {
		this.trfType = trfType;
	}

	public String getTrxType() {
		return this.trxType;
	}

	public void setTrxType(String trxType) {
		this.trxType = trxType;
	}

	public BigDecimal getTrxVal() {
		return this.trxVal;
	}

	public void setTrxVal(BigDecimal trxVal) {
		this.trxVal = trxVal;
	}

}