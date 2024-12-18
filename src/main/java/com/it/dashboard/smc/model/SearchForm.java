package com.it.dashboard.smc.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;




public class SearchForm {



    @Size(min = 18, max = 18, message = "Application No must be exactly 18 characters long")
    private String applicationNo;

    @Size(min = 18, max = 18, message = "Estimation No must be exactly 18 characters long")
    private String estimationNo;

    @Size(min = 18, max = 18, message = "Job No must be exactly 18 characters long")
    private String jobNo;

    @Pattern(regexp = "^\\d{10}$|^\\d{12}$", message = "ID Number must be either 10 or 12 digits long")
    private String idNo;

    @Size(min = 10, max = 10, message = "Account No must be exactly 10 characters long")
    private String accountNo;

	public String getApplicationNo() {
		return applicationNo;
	}

	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}

	public String getEstimationNo() {
		return estimationNo;
	}

	public void setEstimationNo(String estimationNo) {
		this.estimationNo = estimationNo;
	}

	public String getJobNo() {
		return jobNo;
	}

	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}


	

}
