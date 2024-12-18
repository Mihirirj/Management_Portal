package com.it.dashboard.issue.repo;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Date;

import com.it.dashboard.issue.domain.PivApplicant;
import com.it.dashboard.issue.domain.PivDetail;
import com.it.dashboard.payment.domain.PivPayment;
import com.it.dashboard.payment.domain.PivPaymentGrid;




public interface PivDetailDao {

	public PivDetail findByReferenceNo(String referenceNo, String appName);

    public List<PivDetail> findAllOrderedReferenceNo(String appName);
    
    public void register(PivDetail pivDetail, String appName);
    
    public void register(PivDetail pivDetail, String nextPivNo, String appName);
    
    public void update(PivDetail pivDetail, String appName);
    
    public void remove(String accountCode, String appName);

    public PivDetail findByPivNo(String pivNo, String appName);

    public String getNextPivNo(String pivNoPrefix, String appName);
	
	

	
    
    public List<PivDetail> getPivListByStatus(String deptId, String status, String appName);
    
    public List<String> getPivNoListByStatus(String deptId, String status, String sysName, String appName);
    
    public List<PivDetail> getAuthorisedPivDetailListByStatus(String userRole, String deptId,String status, String appName);
    
    public List<String> getAuthorisedPivNoListByStatus(String userRole, String deptId,String status, String appName);
    
    public void updateStatus(String pivNo, String deptId,String status, String appName);


    public void updatePaiddate(String pivNo, String deptId,Date paidDate,String paidAgent,String paidBranch, String appName);

	PivDetail findByBankCheckNo(String bankCheckNo, String appName);
	/////sandun///////////////
	//void chequeStatusUpdate1(String pivNo, String chequeNo, String status);
	List<PivDetail> getChequePaymententPived(String deptId, String status, String appName);
	
	List<PivPaymentGrid> getPivPaymentsToChequeNo1(String chequeNo, String appName);

	/******** edit by pasindu ********/
	PivDetail findReferenceNoOrBankCheckNoInPiv(String pivNo, String status, String appName);
	/*********************************/

	List<String> getApprovedPivNoListByUser(String user, String deptId, String appName);

	List<String> getPivNoListByStatus(List deptIdList, String status,
			String sysName, String appName);

	List<String> getAuthorisedPivNoListByStatus(String userRole,
			List deptIdList, String status, String appName);

	List<String> getApprovedPivNoListByUser(String user, List<String> deptIdList, String appName);

	boolean isPivExistByReferenceNo(String refNo, String appName);

	//PivDetail findByCAReferenceNo(String referenceNo);

	//PivDetail findByCAReferenceNo(String referenceNo, String deptId);

	PivDetail findByCAReferenceNo(BigDecimal referenceNo, String deptId, String appName);

	Object[] findByCAApplicationNo(BigDecimal referenceNo, String deptId, String appName);

	PivDetail findByBankCheckNo(String bankCheckNo, String status, String appName);

	

	
    
    

}
