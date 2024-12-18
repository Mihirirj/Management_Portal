package com.it.dashboard.issue.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

@SuppressWarnings("serial")
public class PivAmountGrid implements Serializable {
	private String acCd;
	private String acNm;
	//@NumberFormat(style=Style.CURRENCY )
	//private BigDecimal amount;
	private String amount;
	private String isTax;
	private String isRefund;
	private double taxValue;
	private BigDecimal sortKey;
	public PivAmountGrid(){
		
	}
	
	public PivAmountGrid(String acCd, String acNm, String amount)
	{
		this.acCd = acCd;
		this.acNm = acNm;
		this.amount = amount;
	}
	
	
	public PivAmountGrid(String acCd, String acNm, String isTax, String isRefund)
	{
		this.acCd = acCd;
		this.acNm = acNm;
		this.isTax = isTax;
		this.isRefund = isRefund;
	}
	
	public PivAmountGrid(String acCd, String acNm, String isTax, String isRefund, BigDecimal sortKey)
	{
		this.acCd = acCd;
		this.acNm = acNm;
		this.isTax = isTax;
		this.isRefund = isRefund;
		this.sortKey = sortKey;
	}
	
	public PivAmountGrid(String acCd, String acNm, String amount, BigDecimal taxPercent, BigDecimal sortKey)
	{
		this.acCd = acCd;
		this.acNm = acNm;
		this.amount = amount;
		if(taxPercent!=null)
			this.taxValue = taxPercent.doubleValue();
		else
			this.taxValue = 0;
		this.sortKey = sortKey;
	}
	
	public PivAmountGrid(String acCd, String acNm)
	{
		this.acCd = acCd;
		this.acNm = acNm;
		//this.amount = new BigDecimal(0);
		this.amount = "0.00";
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


	/*public BigDecimal getAmount() {
		return amount;
	}


	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}*/


	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getIsTax() {
		return isTax;
	}

	public void setIsTax(String isTax) {
		this.isTax = isTax;
	}

	public String getIsRefund() {
		return isRefund;
	}

	public void setIsRefund(String isRefund) {
		this.isRefund = isRefund;
	}

	@Override
	public String toString() {
		return "PivAmountGrid [acCd=" + acCd + ", acNm=" + acNm + ", amount=" + amount +", isTax=" + isTax+", taxValue=" + taxValue+"]";
	}

	public double getTaxValue() {
		return taxValue;
	}

	public void setTaxValue(double taxValue) {
		this.taxValue = taxValue;
	}

	public BigDecimal getSortKey() {
		return sortKey;
	}

	public void setSortKey(BigDecimal sortKey) {
		this.sortKey = sortKey;
	}

	
	
	
	
}
