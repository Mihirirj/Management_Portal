package com.it.dashboard.master.domain;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the GLACGRPM database table.
 * 
 */
@Entity
@NamedQuery(name="Glacgrpm.findAll", query="SELECT g FROM Glacgrpm g")
public class Glacgrpm implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private GlacgrpmPK id;

	
	
	@Column(name="SORT_KEY")
	private BigDecimal sortKey;

	//bi-directional many-to-one association to Bank
	/*@ManyToOne
	@JoinColumn(name="AC_CD", insertable=false, updatable=false)
	private Glacctm glacctm;*/
		
	public Glacgrpm() {
	}

	public GlacgrpmPK getId() {
		return this.id;
	}

	public void setId(GlacgrpmPK id) {
		this.id = id;
	}

	public BigDecimal getSortKey() {
		return this.sortKey;
	}

	public void setSortKey(BigDecimal sortKey) {
		this.sortKey = sortKey;
	}

	

	/*
	public Glacctm getGlacctm() {
		return glacctm;
	}

	public void setGlacctm(Glacctm glacctm) {
		this.glacctm = glacctm;
	}*/
	
	

}