package com.it.dashboard.master.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class TableRowValue implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String code;
	public String name;
	public BigDecimal count1;
	
	public TableRowValue()
	{
		
	}
	
	public TableRowValue(String code, BigDecimal count1)
	{
		this.code = code;
		this.count1 = count1;
	}
	
	public TableRowValue(String code, String name, BigDecimal count1)
	{
		this.code = code;
		this.name = name;
		this.count1 = count1;
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getCount1() {
		return count1;
	}

	public void setCount1(BigDecimal count1) {
		this.count1 = count1;
	}

	
	
	
}
