package com.it.dashboard.issue.domain;

import java.util.List;

public class ContactForm {
	
	private List<Contact> contacts;
	private Student student;

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
}
