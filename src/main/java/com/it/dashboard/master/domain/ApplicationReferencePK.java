package com.it.dashboard.master.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the APPLICATION_REFERENCE database table.
 * 
 */
@Embeddable
public class ApplicationReferencePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="APPLICATION_ID", insertable=false, updatable=false)
	private String applicationId;

	@Column(name="DEPT_ID", insertable=false, updatable=false)
	private String deptId;

	public ApplicationReferencePK() {
	}
	public String getApplicationId() {
		return this.applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
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
		if (!(other instanceof ApplicationReferencePK)) {
			return false;
		}
		ApplicationReferencePK castOther = (ApplicationReferencePK)other;
		return 
			this.applicationId.equals(castOther.applicationId)
			&& this.deptId.equals(castOther.deptId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.applicationId.hashCode();
		hash = hash * prime + this.deptId.hashCode();
		
		return hash;
	}
}