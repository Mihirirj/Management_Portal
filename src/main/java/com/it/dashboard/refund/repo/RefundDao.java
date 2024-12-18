package com.it.dashboard.refund.repo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.it.dashboard.admin.domain.PivHistory;
import com.it.dashboard.issue.domain.PivDetail;
import com.it.dashboard.refund.domain.PivRefund;
import com.it.dashboard.refund.domain.PivRefundPK;



public interface RefundDao {
	public List<String> isRefundableAccCode(String appName);
	public int isRefundablePiv(String deptId,String pivNo, String appName);
	public void insertRefundDetails(PivRefund pivRefund, String PivNo,PivDetail pivDetail, String appName);
	public List<PivRefund> getRefundPivByStatus(String deptId, String status, String appName);
	//public void updateRefund(PivRefund pivRefund);
	public void updateRefundDetails(String pivNo,String deptId,String status,Timestamp approvalDate,String approvalUser, String appName);
	public void updateRefundIssueDetails(String pivNo,String deptId,String status,Date refundDate,String refundUser,String docNo, String appName);
	public BigDecimal getRequsetAmount(String pivNo, String appName);
	public List<String> getCostCentersForLoggedUser(String loggedUserDeptId, String appName);
	public List<String> getAuthorizedActivity(String userRole,String deptId, String appName);
	public String getDeptIdforPIV(String pivNo, String appName);
	List<PivRefund> getRefundPivByStatus(List<String> deptList, String status, String appName);
	void insertRefundDetails(PivRefund pivRefund, PivDetail pivDetail,
			PivHistory pivHistory, String appName);
	void update(PivRefund pivRefund, String appName);
	void updateRefundDetails(PivRefund pivRefund, PivDetail pivDetail,
			PivHistory pivHistory, String appName);
	PivRefund findById(PivRefundPK id, String appName);
	PivRefund findById(String deptId, String pivNo, String appName);
	
	
}
