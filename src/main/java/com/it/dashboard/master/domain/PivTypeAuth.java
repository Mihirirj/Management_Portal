package com.it.dashboard.master.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;


/**
 * The persistent class for the PIV_TYPE_AUTH database table.
 * 
 */
@Entity
@Table(name="PIV_TYPE_AUTH")
@NamedQuery(name="PivTypeAuth.findAll", query="SELECT p FROM PivTypeAuth p")
public class PivTypeAuth implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PivTypeAuthPK id;

	@Column(name="ADD_DATE")
	private Timestamp addDate;

	@Column(name="ADD_USER")
	private String addUser;
	
	
	
	//bi-directional many-to-one association to Gltitlm
	@ManyToOne
	@JoinColumn(name="TITLE_CD", insertable=false, updatable=false)
	private Gltitlm gltitlm;

	public PivTypeAuth() {
	}

	public PivTypeAuthPK getId() {
		return this.id;
	}

	public void setId(PivTypeAuthPK id) {
		this.id = id;
	}

	public Gltitlm getGltitlm() {
		return this.gltitlm;
	}

	public void setGltitlm(Gltitlm gltitlm) {
		this.gltitlm = gltitlm;
	}

	
	public Timestamp getAddDate() {
		return addDate;
	}

	

	public void setAddDate(Timestamp addDate) {
		this.addDate = addDate;
	}

	public String getAddUser() {
		return addUser;
	}

	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}

}