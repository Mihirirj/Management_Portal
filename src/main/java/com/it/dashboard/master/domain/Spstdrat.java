package com.it.dashboard.master.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SPSTDRAT database table.
 * 
 */
@Entity
@NamedQuery(name="Spstdrat.findAll", query="SELECT s FROM Spstdrat s")
public class Spstdrat implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SpstdratPK id;

	private String description;

	private BigDecimal rate;

	public Spstdrat() {
	}

	public SpstdratPK getId() {
		return this.id;
	}

	public void setId(SpstdratPK id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getRate() {
		return this.rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

}