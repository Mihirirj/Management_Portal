package com.it.dashboard.master.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the PCESTHTT database table.
 * 
 */
@Entity
@NamedQuery(name="Pcesthtt.findAll", query="SELECT p FROM Pcesthtt p")
public class Pcesthtt implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PcesthttPK id;

	@Column(name="ACTUAL_UNITS")
	private BigDecimal actualUnits;

	@Column(name="ALLOC_PAID")
	private BigDecimal allocPaid;

	@Column(name="ALLOC_SETTLE")
	private BigDecimal allocSettle;

	@Temporal(TemporalType.DATE)
	@Column(name="APR_DT1")
	private Date aprDt1;

	@Temporal(TemporalType.DATE)
	@Column(name="APR_DT2")
	private Date aprDt2;

	@Temporal(TemporalType.DATE)
	@Column(name="APR_DT3")
	private Date aprDt3;

	@Temporal(TemporalType.DATE)
	@Column(name="APR_DT4")
	private Date aprDt4;

	@Temporal(TemporalType.DATE)
	@Column(name="APR_DT5")
	private Date aprDt5;

	@Column(name="APR_UID1")
	private String aprUid1;

	@Column(name="APR_UID2")
	private String aprUid2;

	@Column(name="APR_UID3")
	private String aprUid3;

	@Column(name="APR_UID4")
	private String aprUid4;

	@Column(name="APR_UID5")
	private String aprUid5;

	@Column(name="CAT_CD")
	private String catCd;

	@Column(name="CLIENT_NM")
	private String clientNm;

	@Column(name="CONF_BY")
	private String confBy;

	@Temporal(TemporalType.DATE)
	@Column(name="CONF_DT")
	private Date confDt;

	@Column(name="CONT_NO")
	private String contNo;

	private String controlled;

	@Column(name="CUST_CONTRIB")
	private BigDecimal custContrib;

	private String descr;

	@Column(name="ENT_BY")
	private String entBy;

	@Temporal(TemporalType.DATE)
	@Column(name="ENT_DT")
	private Date entDt;

	@Column(name="EST_TYPE")
	private String estType;

	@Column(name="ESTIMATED_YEAR")
	private String estimatedYear;

	private String estimatedyear;

	@Temporal(TemporalType.DATE)
	@Column(name="ETIMATE_DT")
	private Date etimateDt;

	@Column(name="FUND_CONTRIB")
	private BigDecimal fundContrib;

	@Column(name="FUND_COST")
	private BigDecimal fundCost;

	@Column(name="FUND_ID")
	private String fundId;

	@Column(name="FUND_SOURCE")
	private String fundSource;

	@Column(name="LABEL_1")
	private String label1;

	@Column(name="LABEL_10")
	private String label10;

	@Column(name="LABEL_2")
	private String label2;

	@Column(name="LABEL_3")
	private String label3;

	@Column(name="LABEL_4")
	private String label4;

	@Column(name="LABEL_5")
	private String label5;

	@Column(name="LABEL_6")
	private String label6;

	@Column(name="LABEL_7")
	private String label7;

	@Column(name="LABEL_8")
	private String label8;

	@Column(name="LABEL_9")
	private String label9;

	@Column(name="LB_RATE_YEAR")
	private String lbRateYear;

	@Column(name="LOG_ID")
	private BigDecimal logId;

	@Column(name="NORM_DEFAULT")
	private BigDecimal normDefault;

	@Column(name="OMS_REF_NO")
	private String omsRefNo;

	@Column(name="PAID_AMT")
	private BigDecimal paidAmt;

	@Column(name="PART_PCNT")
	private BigDecimal partPcnt;

	@Column(name="PARTIAL_AMT")
	private BigDecimal partialAmt;

	@Column(name="PARTIAL_PMT")
	private String partialPmt;

	private BigDecimal priority;

	@Temporal(TemporalType.DATE)
	@Column(name="PRJ_ASS_DT")
	private Date prjAssDt;

	@Column(name="PROJECT_NO")
	private String projectNo;

	@Temporal(TemporalType.DATE)
	@Column(name="REJCT_DT")
	private Date rejctDt;

	@Column(name="REJCT_UID")
	private String rejctUid;

	@Column(name="REJECT_REASON")
	private String rejectReason;

	@Column(name="REV_REASON")
	private String revReason;

	@Temporal(TemporalType.DATE)
	@Column(name="REVISE_DT")
	private Date reviseDt;

	@Column(name="REVISE_EST")
	private BigDecimal reviseEst;

	@Column(name="REVISE_UID")
	private String reviseUid;

	@Column(name="SETTLED_AMT")
	private BigDecimal settledAmt;

	private BigDecimal status;

	@Column(name="STD_COST")
	private BigDecimal stdCost;

	@Column(name="SUB_CONT")
	private String subCont;

	@Column(name="SUP_CD")
	private String supCd;

	@Column(name="TAX_AMT")
	private BigDecimal taxAmt;

	@Column(name="TAX_PCNT")
	private BigDecimal taxPcnt;

	@Column(name="TMPL_ID")
	private String tmplId;

	public Pcesthtt() {
	}

	public PcesthttPK getId() {
		return this.id;
	}

	public void setId(PcesthttPK id) {
		this.id = id;
	}

	public BigDecimal getActualUnits() {
		return this.actualUnits;
	}

	public void setActualUnits(BigDecimal actualUnits) {
		this.actualUnits = actualUnits;
	}

	public BigDecimal getAllocPaid() {
		return this.allocPaid;
	}

	public void setAllocPaid(BigDecimal allocPaid) {
		this.allocPaid = allocPaid;
	}

	public BigDecimal getAllocSettle() {
		return this.allocSettle;
	}

	public void setAllocSettle(BigDecimal allocSettle) {
		this.allocSettle = allocSettle;
	}

	public Date getAprDt1() {
		return this.aprDt1;
	}

	public void setAprDt1(Date aprDt1) {
		this.aprDt1 = aprDt1;
	}

	public Date getAprDt2() {
		return this.aprDt2;
	}

	public void setAprDt2(Date aprDt2) {
		this.aprDt2 = aprDt2;
	}

	public Date getAprDt3() {
		return this.aprDt3;
	}

	public void setAprDt3(Date aprDt3) {
		this.aprDt3 = aprDt3;
	}

	public Date getAprDt4() {
		return this.aprDt4;
	}

	public void setAprDt4(Date aprDt4) {
		this.aprDt4 = aprDt4;
	}

	public Date getAprDt5() {
		return this.aprDt5;
	}

	public void setAprDt5(Date aprDt5) {
		this.aprDt5 = aprDt5;
	}

	public String getAprUid1() {
		return this.aprUid1;
	}

	public void setAprUid1(String aprUid1) {
		this.aprUid1 = aprUid1;
	}

	public String getAprUid2() {
		return this.aprUid2;
	}

	public void setAprUid2(String aprUid2) {
		this.aprUid2 = aprUid2;
	}

	public String getAprUid3() {
		return this.aprUid3;
	}

	public void setAprUid3(String aprUid3) {
		this.aprUid3 = aprUid3;
	}

	public String getAprUid4() {
		return this.aprUid4;
	}

	public void setAprUid4(String aprUid4) {
		this.aprUid4 = aprUid4;
	}

	public String getAprUid5() {
		return this.aprUid5;
	}

	public void setAprUid5(String aprUid5) {
		this.aprUid5 = aprUid5;
	}

	public String getCatCd() {
		return this.catCd;
	}

	public void setCatCd(String catCd) {
		this.catCd = catCd;
	}

	public String getClientNm() {
		return this.clientNm;
	}

	public void setClientNm(String clientNm) {
		this.clientNm = clientNm;
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

	public String getContNo() {
		return this.contNo;
	}

	public void setContNo(String contNo) {
		this.contNo = contNo;
	}

	public String getControlled() {
		return this.controlled;
	}

	public void setControlled(String controlled) {
		this.controlled = controlled;
	}

	public BigDecimal getCustContrib() {
		return this.custContrib;
	}

	public void setCustContrib(BigDecimal custContrib) {
		this.custContrib = custContrib;
	}

	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
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

	public String getEstType() {
		return this.estType;
	}

	public void setEstType(String estType) {
		this.estType = estType;
	}

	public String getEstimatedYear() {
		return this.estimatedYear;
	}

	public void setEstimatedYear(String estimatedYear) {
		this.estimatedYear = estimatedYear;
	}

	public String getEstimatedyear() {
		return this.estimatedyear;
	}

	public void setEstimatedyear(String estimatedyear) {
		this.estimatedyear = estimatedyear;
	}

	public Date getEtimateDt() {
		return this.etimateDt;
	}

	public void setEtimateDt(Date etimateDt) {
		this.etimateDt = etimateDt;
	}

	public BigDecimal getFundContrib() {
		return this.fundContrib;
	}

	public void setFundContrib(BigDecimal fundContrib) {
		this.fundContrib = fundContrib;
	}

	public BigDecimal getFundCost() {
		return this.fundCost;
	}

	public void setFundCost(BigDecimal fundCost) {
		this.fundCost = fundCost;
	}

	public String getFundId() {
		return this.fundId;
	}

	public void setFundId(String fundId) {
		this.fundId = fundId;
	}

	public String getFundSource() {
		return this.fundSource;
	}

	public void setFundSource(String fundSource) {
		this.fundSource = fundSource;
	}

	public String getLabel1() {
		return this.label1;
	}

	public void setLabel1(String label1) {
		this.label1 = label1;
	}

	public String getLabel10() {
		return this.label10;
	}

	public void setLabel10(String label10) {
		this.label10 = label10;
	}

	public String getLabel2() {
		return this.label2;
	}

	public void setLabel2(String label2) {
		this.label2 = label2;
	}

	public String getLabel3() {
		return this.label3;
	}

	public void setLabel3(String label3) {
		this.label3 = label3;
	}

	public String getLabel4() {
		return this.label4;
	}

	public void setLabel4(String label4) {
		this.label4 = label4;
	}

	public String getLabel5() {
		return this.label5;
	}

	public void setLabel5(String label5) {
		this.label5 = label5;
	}

	public String getLabel6() {
		return this.label6;
	}

	public void setLabel6(String label6) {
		this.label6 = label6;
	}

	public String getLabel7() {
		return this.label7;
	}

	public void setLabel7(String label7) {
		this.label7 = label7;
	}

	public String getLabel8() {
		return this.label8;
	}

	public void setLabel8(String label8) {
		this.label8 = label8;
	}

	public String getLabel9() {
		return this.label9;
	}

	public void setLabel9(String label9) {
		this.label9 = label9;
	}

	public String getLbRateYear() {
		return this.lbRateYear;
	}

	public void setLbRateYear(String lbRateYear) {
		this.lbRateYear = lbRateYear;
	}

	public BigDecimal getLogId() {
		return this.logId;
	}

	public void setLogId(BigDecimal logId) {
		this.logId = logId;
	}

	public BigDecimal getNormDefault() {
		return this.normDefault;
	}

	public void setNormDefault(BigDecimal normDefault) {
		this.normDefault = normDefault;
	}

	public String getOmsRefNo() {
		return this.omsRefNo;
	}

	public void setOmsRefNo(String omsRefNo) {
		this.omsRefNo = omsRefNo;
	}

	public BigDecimal getPaidAmt() {
		return this.paidAmt;
	}

	public void setPaidAmt(BigDecimal paidAmt) {
		this.paidAmt = paidAmt;
	}

	public BigDecimal getPartPcnt() {
		return this.partPcnt;
	}

	public void setPartPcnt(BigDecimal partPcnt) {
		this.partPcnt = partPcnt;
	}

	public BigDecimal getPartialAmt() {
		return this.partialAmt;
	}

	public void setPartialAmt(BigDecimal partialAmt) {
		this.partialAmt = partialAmt;
	}

	public String getPartialPmt() {
		return this.partialPmt;
	}

	public void setPartialPmt(String partialPmt) {
		this.partialPmt = partialPmt;
	}

	public BigDecimal getPriority() {
		return this.priority;
	}

	public void setPriority(BigDecimal priority) {
		this.priority = priority;
	}

	public Date getPrjAssDt() {
		return this.prjAssDt;
	}

	public void setPrjAssDt(Date prjAssDt) {
		this.prjAssDt = prjAssDt;
	}

	public String getProjectNo() {
		return this.projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	public Date getRejctDt() {
		return this.rejctDt;
	}

	public void setRejctDt(Date rejctDt) {
		this.rejctDt = rejctDt;
	}

	public String getRejctUid() {
		return this.rejctUid;
	}

	public void setRejctUid(String rejctUid) {
		this.rejctUid = rejctUid;
	}

	public String getRejectReason() {
		return this.rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	public String getRevReason() {
		return this.revReason;
	}

	public void setRevReason(String revReason) {
		this.revReason = revReason;
	}

	public Date getReviseDt() {
		return this.reviseDt;
	}

	public void setReviseDt(Date reviseDt) {
		this.reviseDt = reviseDt;
	}

	public BigDecimal getReviseEst() {
		return this.reviseEst;
	}

	public void setReviseEst(BigDecimal reviseEst) {
		this.reviseEst = reviseEst;
	}

	public String getReviseUid() {
		return this.reviseUid;
	}

	public void setReviseUid(String reviseUid) {
		this.reviseUid = reviseUid;
	}

	public BigDecimal getSettledAmt() {
		return this.settledAmt;
	}

	public void setSettledAmt(BigDecimal settledAmt) {
		this.settledAmt = settledAmt;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public BigDecimal getStdCost() {
		return this.stdCost;
	}

	public void setStdCost(BigDecimal stdCost) {
		this.stdCost = stdCost;
	}

	public String getSubCont() {
		return this.subCont;
	}

	public void setSubCont(String subCont) {
		this.subCont = subCont;
	}

	public String getSupCd() {
		return this.supCd;
	}

	public void setSupCd(String supCd) {
		this.supCd = supCd;
	}

	public BigDecimal getTaxAmt() {
		return this.taxAmt;
	}

	public void setTaxAmt(BigDecimal taxAmt) {
		this.taxAmt = taxAmt;
	}

	public BigDecimal getTaxPcnt() {
		return this.taxPcnt;
	}

	public void setTaxPcnt(BigDecimal taxPcnt) {
		this.taxPcnt = taxPcnt;
	}

	public String getTmplId() {
		return this.tmplId;
	}

	public void setTmplId(String tmplId) {
		this.tmplId = tmplId;
	}

}