package com.it.dashboard.refund.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the PIV_REFUND database table.
 * 
 */
@Embeddable
public class PivRefundPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="DEPT_ID", insertable=false, updatable=false)
	private String deptId;

	@Column(name="PIV_NO", insertable=false, updatable=false)
	private String pivNo;

	public PivRefundPK() {
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
		if (!(other instanceof PivRefundPK)) {
			return false;
		}
		PivRefundPK castOther = (PivRefundPK)other;
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