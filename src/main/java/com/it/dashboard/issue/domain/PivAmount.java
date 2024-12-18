package com.it.dashboard.issue.domain;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the PIV_AMOUNT database table.
 * 
 */
@Entity
@Table(name="PIV_AMOUNT")
@NamedQuery(name="PivAmount.findAll", query="SELECT p FROM PivAmount p")
public class PivAmount implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PivAmountPK id;

	@Column(name="ADD_DATE")
	private Timestamp addDate;

	@Column(name="ADD_USER")
	private String addUser;

	private BigDecimal amount;
	
	@Column(name="DISPLAY_AMOUNT")
	private BigDecimal displayAmount;
	
	@Column(name="TAX_PERCENT")
	private BigDecimal taxPercent;
	
	@Column(name="SORT_KEY")
	private BigDecimal sortKey;

	@Column(name="UPD_DATE")
	private Timestamp updDate;

	@Column(name="UPD_USER")
	private String updUser;

	public PivAmount() {
	}

	public PivAmountPK getId() {
		return this.id;
	}

	public void setId(PivAmountPK id) {
		this.id = id;
	}

	public Timestamp getAddDate() {
		return this.addDate;
	}

	public void setAddDate(Timestamp addDate) {
		this.addDate = addDate;
	}

	public String getAddUser() {
		return this.addUser;
	}

	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Timestamp getUpdDate() {
		return this.updDate;
	}

	public void setUpdDate(Timestamp updDate) {
		this.updDate = updDate;
	}

	public String getUpdUser() {
		return this.updUser;
	}

	public void setUpdUser(String updUser) {
		this.updUser = updUser;
	}

	public BigDecimal getTaxPercent() {
		return taxPercent;
	}

	public void setTaxPercent(BigDecimal taxPercent) {
		this.taxPercent = taxPercent;
	}

	public BigDecimal getSortKey() {
		return sortKey;
	}

	public void setSortKey(BigDecimal sortKey) {
		this.sortKey = sortKey;
	}

	public BigDecimal getDisplayAmount() {
		return displayAmount;
	}

	public void setDisplayAmount(BigDecimal displayAmount) {
		this.displayAmount = displayAmount;
	}

}