package com.it.dashboard.master.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the GLACGRPM database table.
 * 
 */
@Embeddable
public class GlacgrpmPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="AC_CD")
	private String acCd;

	@Column(name="TITLE_CD")
	private String titleCd;

	@Column(name="DEPT_ID")
	private String deptId;

	public GlacgrpmPK() {
	}
	public String getAcCd() {
		return this.acCd;
	}
	public void setAcCd(String acCd) {
		this.acCd = acCd;
	}
	public String getTitleCd() {
		return this.titleCd;
	}
	public void setTitleCd(String titleCd) {
		this.titleCd = titleCd;
	}
	public String getDeptId() {
		return this.deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof GlacgrpmPK)) {
			return false;
		}
		GlacgrpmPK castOther = (GlacgrpmPK)other;
		return 
			this.acCd.equals(castOther.acCd)
			&& this.titleCd.equals(castOther.titleCd)
			&& this.deptId.equals(castOther.deptId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.acCd.hashCode();
		hash = hash * prime + this.titleCd.hashCode();
		hash = hash * prime + this.deptId.hashCode();
		
		return hash;
	}
}