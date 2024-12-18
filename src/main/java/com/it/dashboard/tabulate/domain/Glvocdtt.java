package com.it.dashboard.tabulate.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the GLVOCDTT database table.
 * 
 */
@Entity
@Table(name="GLVOCDTT")
public class Glvocdtt implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private GlvocdttPK id;

	@Column(name="AUTO_GEN", precision=1)
	private BigDecimal autoGen;

	@Column(name="CR_AMT", precision=22)
	private BigDecimal crAmt;

	@Column(name="DR_AMT", precision=22)
	private BigDecimal drAmt;

	@Column(name="GL_CD", length=27)
	private String glCd;

	@Column(length=2000)
	private String nar;

	@Column(name="SUB_AC", length=20)
	private String subAc;

	public Glvocdtt() {
	}

	public GlvocdttPK getId() {
		return this.id;
	}

	public void setId(GlvocdttPK id) {
		this.id = id;
	}

	public BigDecimal getAutoGen() {
		return this.autoGen;
	}

	public void setAutoGen(BigDecimal autoGen) {
		this.autoGen = autoGen;
	}

	public BigDecimal getCrAmt() {
		return this.crAmt;
	}

	public void setCrAmt(BigDecimal crAmt) {
		this.crAmt = crAmt;
	}

	public BigDecimal getDrAmt() {
		return this.drAmt;
	}

	public void setDrAmt(BigDecimal drAmt) {
		this.drAmt = drAmt;
	}

	public String getGlCd() {
		return this.glCd;
	}

	public void setGlCd(String glCd) {
		this.glCd = glCd;
	}

	public String getNar() {
		return this.nar;
	}

	public void setNar(String nar) {
		this.nar = nar;
	}

	public String getSubAc() {
		return this.subAc;
	}

	public void setSubAc(String subAc) {
		this.subAc = subAc;
	}

}