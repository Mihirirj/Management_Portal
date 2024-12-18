package com.it.dashboard.admin.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PIV_CONFIG database table.
 * 
 */
@Entity
@Table(name="PIV_CONFIG")
@NamedQuery(name="PivConfig.findAll", query="SELECT p FROM PivConfig p")
public class PivConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String code;

	@Column(name="CODE_VALUE")
	private String codeValue;

	public PivConfig() {
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodeValue() {
		return this.codeValue;
	}

	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}

}