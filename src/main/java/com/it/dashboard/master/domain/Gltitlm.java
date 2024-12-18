package com.it.dashboard.master.domain;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.it.dashboard.issue.domain.PivDetail;

import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the GLTITLM database table.
 * 
 */
@Entity
@NamedQuery(name="Gltitlm.findAll", query="SELECT g FROM Gltitlm g")
public class Gltitlm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TITLE_CD")
	private String titleCd;

	@Column(name="PIV_CD")
	private String pivCd;

	private BigDecimal status;

	@Column(name="SYS_TYPE")
	private String sysType;

	@Column(name="TITLE_NM")
	private String titleNm;

	@Column(name="\"TYPE\"")
	private String type;

	//bi-directional many-to-one association to PivTypeAuth
	/*@OneToMany(mappedBy="gltitlm")
	@JsonIgnore 
	private List<PivTypeAuth> pivTypeAuths;*/
	
	//bi-directional many-to-one association to PivDetail
	/*@OneToMany(mappedBy="gltitlm")
	@JsonIgnore 
	private List<PivDetail> pivDetails;*/

	public Gltitlm() {
	}

	public String getTitleCd() {
		return this.titleCd;
	}

	public void setTitleCd(String titleCd) {
		this.titleCd = titleCd;
	}

	public String getPivCd() {
		return this.pivCd;
	}

	public void setPivCd(String pivCd) {
		this.pivCd = pivCd;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public String getSysType() {
		return this.sysType;
	}

	public void setSysType(String sysType) {
		this.sysType = sysType;
	}

	public String getTitleNm() {
		return this.titleNm;
	}

	public void setTitleNm(String titleNm) {
		this.titleNm = titleNm;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/*public List<PivTypeAuth> getPivTypeAuths() {
		return this.pivTypeAuths;
	}

	public void setPivTypeAuths(List<PivTypeAuth> pivTypeAuths) {
		this.pivTypeAuths = pivTypeAuths;
	}

	public PivTypeAuth addPivTypeAuth(PivTypeAuth pivTypeAuth) {
		getPivTypeAuths().add(pivTypeAuth);
		pivTypeAuth.setGltitlm(this);

		return pivTypeAuth;
	}

	public PivTypeAuth removePivTypeAuth(PivTypeAuth pivTypeAuth) {
		getPivTypeAuths().remove(pivTypeAuth);
		pivTypeAuth.setGltitlm(null);

		return pivTypeAuth;
	}*/

	/*
	public List<PivDetail> getPivDetails() {
		return pivDetails;
	}

	public void setPivDetails(List<PivDetail> pivDetails) {
		this.pivDetails = pivDetails;
	}
*/
}