package com.it.dashboard.issue.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the PIV_APPROVAL database table.
 * 
 */
@Embeddable
public class PivApprovalPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="DEPT_ID")
	private String deptId;

	private String activity;

	@Column(name="USER_ROLE")
	private String userRole;

	public PivApprovalPK() {
	}
	public String getDeptId() {
		return this.deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getActivity() {
		return this.activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public String getUserRole() {
		return this.userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PivApprovalPK)) {
			return false;
		}
		PivApprovalPK castOther = (PivApprovalPK)other;
		return 
			this.deptId.equals(castOther.deptId)
			&& this.activity.equals(castOther.activity)
			&& this.userRole.equals(castOther.userRole);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.deptId.hashCode();
		hash = hash * prime + this.activity.hashCode();
		hash = hash * prime + this.userRole.hashCode();
		
		return hash;
	}
}