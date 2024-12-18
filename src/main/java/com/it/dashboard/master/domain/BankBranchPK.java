package com.it.dashboard.master.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the BANK_BRANCH database table.
 * 
 */
@Embeddable
public class BankBranchPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="BRANCH_CODE")
	private String branchCode;

	@Column(name="BANK_CODE", insertable=false, updatable=false)
	private String bankCode;

	public BankBranchPK() {
	}
	public String getBranchCode() {
		return this.branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getBankCode() {
		return this.bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof BankBranchPK)) {
			return false;
		}
		BankBranchPK castOther = (BankBranchPK)other;
		return 
			this.branchCode.equals(castOther.branchCode)
			&& this.bankCode.equals(castOther.bankCode);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.branchCode.hashCode();
		hash = hash * prime + this.bankCode.hashCode();
		
		return hash;
	}
}