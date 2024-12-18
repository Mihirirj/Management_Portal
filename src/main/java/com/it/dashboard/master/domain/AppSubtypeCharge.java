package com.it.dashboard.master.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the APP_SUBTYPE_CHARGE database table.
 * 
 */
@Entity
@Table(name="APP_SUBTYPE_CHARGE")
@NamedQuery(name="AppSubtypeCharge.findAll", query="SELECT a FROM AppSubtypeCharge a")
public class AppSubtypeCharge implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AppSubtypeChargePK id;

	@Column(name="APPLICATION_FEE")
	private BigDecimal applicationFee;

	@Column(name="FIXED_COST")
	private BigDecimal fixedCost;

	@Temporal(TemporalType.DATE)
	@Column(name="UPD_DATE")
	private Date updDate;

	@Column(name="UPD_USER")
	private String updUser;

	public AppSubtypeCharge() {
	}

	public AppSubtypeChargePK getId() {
		return this.id;
	}

	public void setId(AppSubtypeChargePK id) {
		this.id = id;
	}

	public BigDecimal getApplicationFee() {
		return this.applicationFee;
	}

	public void setApplicationFee(BigDecimal applicationFee) {
		this.applicationFee = applicationFee;
	}

	public BigDecimal getFixedCost() {
		return this.fixedCost;
	}

	public void setFixedCost(BigDecimal fixedCost) {
		this.fixedCost = fixedCost;
	}

	public Date getUpdDate() {
		return this.updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	public String getUpdUser() {
		return this.updUser;
	}

	public void setUpdUser(String updUser) {
		this.updUser = updUser;
	}

}