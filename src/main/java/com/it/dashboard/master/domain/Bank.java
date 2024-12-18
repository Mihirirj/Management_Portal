package com.it.dashboard.master.domain;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the BANK database table.
 * 
 */
@Entity
@NamedQuery(name="Bank.findAll", query="SELECT b FROM Bank b")
public class Bank implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="BANK_CODE")
	private String bankCode;

	@Column(name="BANK_NAME")
	private String bankName;

	@Column(name="IS_BANK")
	private String isBank;

	@Column(name="SORT_KEY")
	private BigDecimal sortKey;

	//bi-directional many-to-one association to BankBranch
	/*@OneToMany(mappedBy="bank")
	@JsonIgnore 
	private List<BankBranch> bankBranches;*/

	public Bank() {
	}

	public String getBankCode() {
		return this.bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getIsBank() {
		return this.isBank;
	}

	public void setIsBank(String isBank) {
		this.isBank = isBank;
	}

	public BigDecimal getSortKey() {
		return this.sortKey;
	}

	public void setSortKey(BigDecimal sortKey) {
		this.sortKey = sortKey;
	}
/*
	public List<BankBranch> getBankBranches() {
		return this.bankBranches;
	}

	public void setBankBranches(List<BankBranch> bankBranches) {
		this.bankBranches = bankBranches;
	}

	public BankBranch addBankBranch(BankBranch bankBranch) {
		getBankBranches().add(bankBranch);
		bankBranch.setBank(this);

		return bankBranch;
	}

	public BankBranch removeBankBranch(BankBranch bankBranch) {
		getBankBranches().remove(bankBranch);
		bankBranch.setBank(null);

		return bankBranch;
	}*/

}