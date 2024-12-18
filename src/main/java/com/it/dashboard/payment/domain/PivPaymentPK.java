package com.it.dashboard.payment.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the PIV_PAYMENT database table.
 * 
 */
@Embeddable
public class PivPaymentPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="DEPT_ID", insertable=false, updatable=false)
	private String deptId;

	@Column(name="PIV_NO", insertable=false, updatable=false)
	private String pivNo;

	@Column(name="SEQ_NO")
	private long seqNo;

	public PivPaymentPK() {
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
	public long getSeqNo() {
		return this.seqNo;
	}
	public void setSeqNo(long seqNo) {
		this.seqNo = seqNo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PivPaymentPK)) {
			return false;
		}
		PivPaymentPK castOther = (PivPaymentPK)other;
		return 
			this.deptId.equals(castOther.deptId)
			&& this.pivNo.equals(castOther.pivNo)
			&& (this.seqNo == castOther.seqNo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.deptId.hashCode();
		hash = hash * prime + this.pivNo.hashCode();
		hash = hash * prime + ((int) (this.seqNo ^ (this.seqNo >>> 32)));
		
		return hash;
	}
}