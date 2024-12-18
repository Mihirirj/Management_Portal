package com.it.dashboard.payment.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the PIV_BATCH_UPLOAD database table.
 * 
 */
@Embeddable
public class PivBatchUploadPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="FILE_NAME", unique=true, nullable=false, length=14)
	private String fileName;

	@Column(name="\"YEAR\"", unique=true, nullable=false, precision=4)
	private long year;

	public PivBatchUploadPK() {
	}
	public String getFileName() {
		return this.fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public long getYear() {
		return this.year;
	}
	public void setYear(long year) {
		this.year = year;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PivBatchUploadPK)) {
			return false;
		}
		PivBatchUploadPK castOther = (PivBatchUploadPK)other;
		return 
			this.fileName.equals(castOther.fileName)
			&& (this.year == castOther.year);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.fileName.hashCode();
		hash = hash * prime + ((int) (this.year ^ (this.year >>> 32)));
		
		return hash;
	}
}