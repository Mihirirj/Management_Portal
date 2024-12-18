package com.it.dashboard.master.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the APP_SUBTYPE_CHARGE database table.
 * 
 */
@Embeddable
public class AppSubtypeChargePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private long phase;

	@Column(name="CONNECTION_TYPE")
	private long connectionType;

	@Column(name="APPLICATION_SUB_TYPE")
	private String applicationSubType;

	@Column(name="\"YEAR\"")
	private String year;

	public AppSubtypeChargePK() {
	}
	public long getPhase() {
		return this.phase;
	}
	public void setPhase(long phase) {
		this.phase = phase;
	}
	public long getConnectionType() {
		return this.connectionType;
	}
	public void setConnectionType(long connectionType) {
		this.connectionType = connectionType;
	}
	public String getApplicationSubType() {
		return this.applicationSubType;
	}
	public void setApplicationSubType(String applicationSubType) {
		this.applicationSubType = applicationSubType;
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
		if (!(other instanceof AppSubtypeChargePK)) {
			return false;
		}
		AppSubtypeChargePK castOther = (AppSubtypeChargePK)other;
		return 
			(this.phase == castOther.phase)
			&& (this.connectionType == castOther.connectionType)
			&& this.applicationSubType.equals(castOther.applicationSubType)
			&& this.year.equals(castOther.year);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.phase ^ (this.phase >>> 32)));
		hash = hash * prime + ((int) (this.connectionType ^ (this.connectionType >>> 32)));
		hash = hash * prime + this.applicationSubType.hashCode();
		hash = hash * prime + this.year.hashCode();
		
		return hash;
	}
}