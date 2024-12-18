package com.it.dashboard.admin.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SAUSRDPL database table.
 * 
 */
@Embeddable
public class SausrdplPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="LOG_ID")
	private long logId;

	@Column(name="SEQ1_NO")
	private long seq1No;

	public SausrdplPK() {
	}
	public long getLogId() {
		return this.logId;
	}
	public void setLogId(long logId) {
		this.logId = logId;
	}
	public long getSeq1No() {
		return this.seq1No;
	}
	public void setSeq1No(long seq1No) {
		this.seq1No = seq1No;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SausrdplPK)) {
			return false;
		}
		SausrdplPK castOther = (SausrdplPK)other;
		return 
			(this.logId == castOther.logId)
			&& (this.seq1No == castOther.seq1No);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.logId ^ (this.logId >>> 32)));
		hash = hash * prime + ((int) (this.seq1No ^ (this.seq1No >>> 32)));
		
		return hash;
	}
}