package com.it.dashboard.payment.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the PIV_BANK_TRANS database table.
 * 
 */
@Embeddable
public class PivBankTranPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="PIV_CHECK_NO", unique=true, nullable=false, length=14)
	private String pivCheckNo;

	@Column(name="TRANS_ID", unique=true, nullable=false, length=14)
	private String transId;

	@Column(unique=true, nullable=false, length=7)
	private String activity;

	public PivBankTranPK() {
	}
	public String getPivCheckNo() {
		return this.pivCheckNo;
	}
	public void setPivCheckNo(String pivCheckNo) {
		this.pivCheckNo = pivCheckNo;
	}
	public String getTransId() {
		return this.transId;
	}
	public void setTransId(String transId) {
		this.transId = transId;
	}
	public String getActivity() {
		return this.activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PivBankTranPK)) {
			return false;
		}
		PivBankTranPK castOther = (PivBankTranPK)other;
		return 
			this.pivCheckNo.equals(castOther.pivCheckNo)
			&& this.transId.equals(castOther.transId)
			&& this.activity.equals(castOther.activity);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.pivCheckNo.hashCode();
		hash = hash * prime + this.transId.hashCode();
		hash = hash * prime + this.activity.hashCode();
		
		return hash;
	}
}