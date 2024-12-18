package com.it.dashboard.issue.domain;

import java.io.Serializable;
import java.math.BigDecimal;

@SuppressWarnings("serial")
public class Contact1 implements Serializable {
	private String acCd;
	private String acNm;
	private BigDecimal amount;
	
	public Contact1(){
		
	}
	public Contact1(String acCd, String acNm, BigDecimal amount)
	{
		this.acCd = acCd;
		this.acNm = acNm;
		this.amount = amount;
	}
	
	public String getAcCd() {
		return acCd;
	}


	public void setAcCd(String acCd) {
		this.acCd = acCd;
	}


	public String getAcNm() {
		return acNm;
	}


	public void setAcNm(String acNm) {
		this.acNm = acNm;
	}


	public BigDecimal getAmount() {
		return amount;
	}


	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}


	@Override
	public String toString() {
		return "Contact1 [acCd=" + acCd + ", acNm=" + acNm + ", amount=" + amount +"]";
	}
}
