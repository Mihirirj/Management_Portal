package com.it.dashboard.admin.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CONFIG database table.
 * 
 */
@Entity
@NamedQuery(name="Config.findAll", query="SELECT c FROM Config c")
public class Config implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"KEY\"")
	private String key;

	@Column(name="\"VALUE\"")
	private String value;

	public Config() {
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}