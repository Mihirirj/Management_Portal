package com.it.dashboard.clientService.domain;

import java.util.List;

public class PivResponse {

	public List<PIVPayment> payment;

	public List<PIVPayment> getPayment() {
		return payment;
	}

	public void setPayment(List<PIVPayment> payment) {
		this.payment = payment;
	}
	
	
}