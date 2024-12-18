package com.it.dashboard.issue.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the PIV_APPLICANT database table.
 * 
 */
@Embeddable
public class PivApplicantPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="PIV_NO", insertable=false, updatable=false)
	private String pivNo;

	@Column(name="DEPT_ID", insertable=false, updatable=false)
	private String deptId;

	public PivApplicantPK() {
	}
	public String getPivNo() {
		return this.pivNo;
	}
	public void setPivNo(String pivNo) {
		this.pivNo = pivNo;
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
		if (!(other instanceof PivApplicantPK)) {
			return false;
		}
		PivApplicantPK castOther = (PivApplicantPK)other;
		return 
			this.pivNo.equals(castOther.pivNo)
			&& this.deptId.equals(castOther.deptId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.pivNo.hashCode();
		hash = hash * prime + this.deptId.hashCode();
		
		return hash;
	}
}