package com.it.dashboard.master.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the PIV_TYPE_AUTH database table.
 * 
 */
@Embeddable
public class PivTypeAuthPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="DEPT_ID")
	private String deptId;

	@Column(name="TITLE_CD", insertable=false, updatable=false)
	private String titleCd;

	public PivTypeAuthPK() {
	}
	public String getDeptId() {
		return this.deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getTitleCd() {
		return this.titleCd;
	}
	public void setTitleCd(String titleCd) {
		this.titleCd = titleCd;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PivTypeAuthPK)) {
			return false;
		}
		PivTypeAuthPK castOther = (PivTypeAuthPK)other;
		return 
			this.deptId.equals(castOther.deptId)
			&& this.titleCd.equals(castOther.titleCd);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.deptId.hashCode();
		hash = hash * prime + this.titleCd.hashCode();
		
		return hash;
	}
}