package com.it.dashboard.issue.controller;

import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

public class RegistrationBean 
{
	@NotEmpty
	@Size(min=1,max=5)
	String firstName;
	
	@NotEmpty
	@Size(min=1,max=5)
	String lastName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}