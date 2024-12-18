package com.it.dashboard.issue.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the PIV_APPROVAL database table.
 * 
 */
@Entity
@Table(name="PIV_APPROVAL")
@NamedQuery(name="PivApproval.findAll", query="SELECT p FROM PivApproval p")
public class PivApproval implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PivApprovalPK id;

	@Column(name="ADD_DATE")
	private Timestamp addDate;

	@Column(name="ADD_USER")
	private String addUser;

	private String description;

	
	public PivApproval() {
	}

	public PivApprovalPK getId() {
		return this.id;
	}

	public void setId(PivApprovalPK id) {
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

}