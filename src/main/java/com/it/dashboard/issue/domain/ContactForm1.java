package com.it.dashboard.issue.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class ContactForm1 implements Serializable {
	private PivDetail pivDetail;
	private PivApplicant pivApplicant;
	//private ArrayList<PivAmountGrid> pivAmountList;
	Map<String,String> pivTypeList;
	//private List<PivAmountGrid> pivAmountList;
	private List<Contact1> contacts;
	
	public Map<String, String> getPivTypeList() {
		return pivTypeList;
	}
	public void setPivTypeList(Map<String, String> pivTypeList) {
		this.pivTypeList = pivTypeList;
	}
	public PivDetail getPivDetail() {
		return pivDetail;
	}
	public void setPivDetail(PivDetail pivDetail) {
		this.pivDetail = pivDetail;
	}
	public PivApplicant getPivApplicant() {
		return pivApplicant;
	}
	public void setPivApplicant(PivApplicant pivApplicant) {
		this.pivApplicant = pivApplicant;
	}
	
	public List<Contact1> getContacts() {
		return contacts;
	}
	public void setContacts(List<Contact1> contacts) {
		this.contacts = contacts;
	}
	
	
	
	


	


	
}
