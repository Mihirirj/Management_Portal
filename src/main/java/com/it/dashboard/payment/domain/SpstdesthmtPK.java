package com.it.dashboard.payment.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SPSTDESTHMT database table.
 * 
 */
@Embeddable
public class SpstdesthmtPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="APP_NO")
	private String appNo;

	@Column(name="STD_NO")
	private String stdNo;

	@Column(name="DEPT_ID")
	private String deptId;

	public SpstdesthmtPK() {
	}
	public String getAppNo() {
		return this.appNo;
	}
	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}
	public String getStdNo() {
		return this.stdNo;
	}
	public void setStdNo(String stdNo) {
		this.stdNo = stdNo;
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
		if (!(other instanceof SpstdesthmtPK)) {
			return false;
		}
		SpstdesthmtPK castOther = (SpstdesthmtPK)other;
		return 
			this.appNo.equals(castOther.appNo)
			&& this.stdNo.equals(castOther.stdNo)
			&& this.deptId.equals(castOther.deptId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.appNo.hashCode();
		hash = hash * prime + this.stdNo.hashCode();
		hash = hash * prime + this.deptId.hashCode();
		
		return hash;
	}
}