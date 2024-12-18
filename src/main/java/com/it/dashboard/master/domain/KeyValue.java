package com.it.dashboard.master.domain;

import java.io.Serializable;

public class KeyValue implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String key;
	public String value;
	
	public KeyValue()
	{
		
	}
	
	public KeyValue(String key, String value)
	{
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
