package com.it.dashboard.issue.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the APPLICATIONSUBTYPES database table.
 * 
 */
@Entity
@Table(name="APPLICATIONSUBTYPES")
@NamedQuery(name="Applicationsubtype.findAll", query="SELECT a FROM Applicationsubtype a")
public class Applicationsubtype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String appsubtype;

	private String description;

	public Applicationsubtype() {
	}

	public String getAppsubtype() {
		return this.appsubtype;
	}

	public void setAppsubtype(String appsubtype) {
		this.appsubtype = appsubtype;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}