package com.it.dashboard.tabulate.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the GLVOCHTT database table.
 * 
 */
@Embeddable
public class GlvochttPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="DOC_NO", unique=true, nullable=false, length=20)
	private String docNo;

	@Column(name="BATCH_ID", unique=true, nullable=false, length=10)
	private String batchId;

	@Column(name="DOC_PF", unique=true, nullable=false, length=20)
	private String docPf;

	@Column(name="DEPT_ID", unique=true, nullable=false, length=6)
	private String deptId;

	public GlvochttPK() {
	}
	public String getDocNo() {
		return this.docNo;
	}
	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}
	public String getBatchId() {
		return this.batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	public String getDocPf() {
		return this.docPf;
	}
	public void setDocPf(String docPf) {
		this.docPf = docPf;
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
		if (!(other instanceof GlvochttPK)) {
			return false;
		}
		GlvochttPK castOther = (GlvochttPK)other;
		return 
			this.docNo.equals(castOther.docNo)
			&& this.batchId.equals(castOther.batchId)
			&& this.docPf.equals(castOther.docPf)
			&& this.deptId.equals(castOther.deptId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.docNo.hashCode();
		hash = hash * prime + this.batchId.hashCode();
		hash = hash * prime + this.docPf.hashCode();
		hash = hash * prime + this.deptId.hashCode();
		
		return hash;
	}
}