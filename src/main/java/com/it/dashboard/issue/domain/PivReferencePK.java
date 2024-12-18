package com.it.dashboard.issue.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the PIV_REFERENCE database table.
 * 
 */
@Embeddable
public class PivReferencePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="DEPT_ID", unique=true, nullable=false, length=6)
	private String deptId;

	@Column(name="PIV_NO", unique=true, nullable=false, length=24)
	private String pivNo;

	public PivReferencePK() {
	}
	public String getDeptId() {
		return this.deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getPivNo() {
		return this.pivNo;
	}
	public void setPivNo(String pivNo) {
		this.pivNo = pivNo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PivReferencePK)) {
			return false;
		}
		PivReferencePK castOther = (PivReferencePK)other;
		return 
			this.deptId.equals(castOther.deptId)
			&& this.pivNo.equals(castOther.pivNo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.deptId.hashCode();
		hash = hash * prime + this.pivNo.hashCode();
		
		return hash;
	}
}