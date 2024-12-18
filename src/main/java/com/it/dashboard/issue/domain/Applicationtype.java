package com.it.dashboard.issue.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the APPLICATIONTYPES database table.
 * 
 */
@Entity
@Table(name="APPLICATIONTYPES")
@NamedQuery(name="Applicationtype.findAll", query="SELECT a FROM Applicationtype a")
public class Applicationtype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String apptype;

	private String description;

	@Column(name="SYS_TYPE")
	private String sysType;

	public Applicationtype() {
	}

	public String getApptype() {
		return this.apptype;
	}

	public void setApptype(String apptype) {
		this.apptype = apptype;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSysType() {
		return this.sysType;
	}

	public void setSysType(String sysType) {
		this.sysType = sysType;
	}

}