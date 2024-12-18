package com.it.dashboard.master.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.it.dashboard.payment.domain.PivPayment;


/**
 * The persistent class for the BANK_BRANCH database table.
 * 
 */
@Entity
@Table(name="BANK_BRANCH")
@NamedQuery(name="BankBranch.findAll", query="SELECT b FROM BankBranch b")
public class BankBranch implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BankBranchPK id;

	@Column(name="BRANCH_ADDRESS")
	private String branchAddress;

	@Column(name="BRANCH_NAME")
	private String branchName;

	//bi-directional many-to-one association to Bank
	@ManyToOne
	@JoinColumn(name="BANK_CODE", insertable=false, updatable=false)
	private Bank bank;
	
	//bi-directional many-to-one association to PivApplicant
	/*@OneToMany(mappedBy="bankBranch",cascade = CascadeType.ALL)
	@JsonIgnore 
	private List<PivPayment> pivPayments;*/

	public BankBranch() {
	}

	public BankBranchPK getId() {
		return this.id;
	}

	public void setId(BankBranchPK id) {
		this.id = id;
	}

	public String getBranchAddress() {
		return this.branchAddress;
	}

	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}

	public String getBranchName() {
		return this.branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public Bank getBank() {
		return this.bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}
/*
	public List<PivPayment> getPivPayments() {
		return pivPayments;
	}

	public void setPivPayments(List<PivPayment> pivPayments) {
		this.pivPayments = pivPayments;
	}
*/
}