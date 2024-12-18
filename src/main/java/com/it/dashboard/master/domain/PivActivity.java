package com.it.dashboard.master.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the PIV_ACTIVITY database table.
 * 
 */
@Entity
@Table(name="PIV_ACTIVITY")
@NamedQuery(name="PivActivity.findAll", query="SELECT p FROM PivActivity p")
public class PivActivity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ACTIVITY_CODE")
	private String activityCode;

	@Column(name="DESCRIPTION_1")
	private String description1;

	@Column(name="DESCRIPTION_2")
	private String description2;

	@Column(name="SORT_KEY")
	private BigDecimal sortKey;

	
		
	public PivActivity() {
	}

	public String getActivityCode() {
		return this.activityCode;
	}

	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}

	public String getDescription1() {
		return this.description1;
	}

	public void setDescription1(String description1) {
		this.description1 = description1;
	}

	public String getDescription2() {
		return this.description2;
	}

	public void setDescription2(String description2) {
		this.description2 = description2;
	}

	public BigDecimal getSortKey() {
		return this.sortKey;
	}

	public void setSortKey(BigDecimal sortKey) {
		this.sortKey = sortKey;
	}

}