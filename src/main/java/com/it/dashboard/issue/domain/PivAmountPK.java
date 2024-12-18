package com.it.dashboard.issue.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the PIV_AMOUNT database table.
 * 
 */
@Embeddable
public class PivAmountPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="DEPT_ID", insertable=false, updatable=false)
	private String deptId;

	@Column(name="PIV_NO", insertable=false, updatable=false)
	private String pivNo;

	@Column(name="ACCOUNT_CODE")
	private String accountCode;

	public PivAmountPK() {
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
	public String getAccountCode() {
		return this.accountCode;
	}
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PivAmountPK)) {
			return false;
		}
		PivAmountPK castOther = (PivAmountPK)other;
		return 
			this.deptId.equals(castOther.deptId)
			&& this.pivNo.equals(castOther.pivNo)
			&& this.accountCode.equals(castOther.accountCode);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.deptId.hashCode();
		hash = hash * prime + this.pivNo.hashCode();
		hash = hash * prime + this.accountCode.hashCode();
		
		return hash;
	}
}