package com.it.dashboard.master.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.validator.constraints.NotEmpty;

import com.it.dashboard.admin.domain.PivActivityGrid;
import com.it.dashboard.issue.domain.PivAmountGrid;
import com.it.dashboard.issue.domain.PivApplicant;
import com.it.dashboard.issue.domain.PivApproval;
import com.it.dashboard.issue.domain.PivDetail;
import com.it.dashboard.master.domain.PivActivity;
import com.it.dashboard.admin.domain.PivHistory;
import com.it.dashboard.master.domain.Bank;
import com.it.dashboard.master.domain.BankBranch;
import com.it.dashboard.master.domain.KeyValue;
import com.it.dashboard.payment.domain.PivPayment;
import com.it.dashboard.payment.domain.PivPaymentGrid;
import com.it.dashboard.refund.domain.PivRefund;


@SuppressWarnings("serial")
public class MasterModel implements Serializable {
	private String deptId;
	private PivTypeAuth pivTypeAuth;
	private List<PivTypeAuth> pivTypeAuthList;
	private List<Gltitlm> pivTypeList;
	private Map<String,String> deptIdMap;
	
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public PivTypeAuth getPivTypeAuth() {
		return pivTypeAuth;
	}
	public void setPivTypeAuth(PivTypeAuth pivTypeAuth) {
		this.pivTypeAuth = pivTypeAuth;
	}
	public List<PivTypeAuth> getPivTypeAuthList() {
		return pivTypeAuthList;
	}
	public void setPivTypeAuthList(List<PivTypeAuth> pivTypeAuthList) {
		this.pivTypeAuthList = pivTypeAuthList;
	}
	public List<Gltitlm> getPivTypeList() {
		return pivTypeList;
	}
	public void setPivTypeList(List<Gltitlm> pivTypeList) {
		this.pivTypeList = pivTypeList;
	}
	public Map<String, String> getDeptIdMap() {
		return deptIdMap;
	}
	public void setDeptIdMap(Map<String, String> deptIdMap) {
		this.deptIdMap = deptIdMap;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	

}
