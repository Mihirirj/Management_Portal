package com.it.dashboard.master.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SPSTDRAT database table.
 * 
 */
@Embeddable
public class SpstdratPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="RATE_CODE")
	private String rateCode;

	@Column(name="\"YEAR\"")
	private String year;

	public SpstdratPK() {
	}
	public String getRateCode() {
		return this.rateCode;
	}
	public void setRateCode(String rateCode) {
		this.rateCode = rateCode;
	}
	public String getYear() {
		return this.year;
	}
	public void setYear(String year) {
		this.year = year;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SpstdratPK)) {
			return false;
		}
		SpstdratPK castOther = (SpstdratPK)other;
		return 
			this.rateCode.equals(castOther.rateCode)
			&& this.year.equals(castOther.year);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.rateCode.hashCode();
		hash = hash * prime + this.year.hashCode();
		
		return hash;
	}
}