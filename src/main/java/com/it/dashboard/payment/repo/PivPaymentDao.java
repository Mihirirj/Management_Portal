package com.it.dashboard.payment.repo;


import java.util.List;

import com.it.dashboard.issue.domain.PivApplicant;
import com.it.dashboard.issue.domain.PivDetail;
import com.it.dashboard.payment.domain.PaymentMethodObject;
import com.it.dashboard.payment.domain.PivBankTran;
import com.it.dashboard.payment.domain.PivPayment;
import com.it.dashboard.payment.domain.PivPaymentGrid;

public interface PivPaymentDao {

	PivPayment findByPivCheque(String pivNo, String chequeNo, String appName);

	PivPayment findById(String deptId, String pivNo, long seqNo, String appName);

	List<PivPayment> getChequePaymentPived(String deptId, String paymentMode,
			String status, String appName);

	List<PivPayment> getPivToStatusAndPayMode(List<String> deptList,
			String paymentMode, String status, String chequeNo, String appName);

	List<PivPaymentGrid> getPivPaymentsToChequeNo1(String chequeNo, String appName);

	List<PivPaymentGrid> getPivPaymentsToPIV(String pivNo, String appName);

	List<PivPayment> getPivPaymentToPIV(String pivNo, String appName);

	List<String> getPivNoToChequeNo(String chequeNo, String status, String appName);

	List<String> getChequeNoToStatus(String status, String appName);

	void deletePaymentToPivNo(String pivNo, String appName);

	void update(PivPayment pivPayment, String appName);

	void register(PivPayment pivPayment, String appName);

	void remove(PivPayment pivPayment, String appName);

	long getNextPayemntSeqNo(String pivNo, String deptId, String appName);

	double getPaidAmount(String pivNo, String appName);

	long insertBankTrans(PivBankTran pivBankTran, String appName);

	void updateBankTrans(PivBankTran pivBankTran, String appName);

	PivBankTran findBankTranById(String transId, String pivCheckNo,
			String activity, String appName);

	boolean isChequePayment(String deptId, String pivNo, String appName);

	double getChequeTotal(String chequeNo, List<String> deptList, String status, String appName);

	double getFreshChequeTotal(String chequeNo, List<String> deptList, String appName);

	List<PivPayment> getReturnPivListByStatus(String deptId, String status, String appName);

	List<PivPayment> getReturnPivList(List<String> deptList, String status, String appName);
	
	/*public List<PivApplicant> getChequePayemtPiv(String deptId) ;

	List<PivPaymentGrid> getPivPaymentsToChequeNo(String chequeNo);

	PivPayment findByPivCheque(String pivNo, String chequeNo);

	void update(PivPayment pivPayment);

	List<String> getChequeNoToStatus(String status);

	List<String> getPivNoToChequeNo(String chequeNo, String status);

	void register(PivPayment pivPayment);

	List<PivPaymentGrid> getPivPaymentsToPIV(String pivNo);

	long getNextPayemntSeqNo(String pivNo, String deptId);

	void remove(PivPayment pivPayment);

	PivPayment findById(String deptId, String pivNo, long serialNo);

	long insertBankTrans(PivBankTran pivBankTran);

	void updateBankTrans(PivBankTran pivBankTran);

	PivBankTran findBankTranById(String transId, String pivCheckNo,	String activity);

	List<PivPayment> getPivPaymentToPIV(String pivNo);

	boolean isChequePayment(String deptId, String pivNo);

	List<PivPayment> getReturnPivListByStatus(String deptId, String status);
	
	void chequeStatusUpdate(String pivNo,String deptId, String chequeNo, String status, String loggedUser);
	
	List<PivPayment> getChequePaymentPived(String deptId, String paymentMode, String status);
	
	List<PivPaymentGrid> getPivPaymentsToChequeNo1(String chequeNo);
	
	void deletePaymentToPivNo(String pivNo);

	List<PivPayment> getPivToStatusAndPayMode(List<String> deptList,String paymentMode, String status, String chequeNo);

	void confirmPayment(PivDetail pivDetail,List<PaymentMethodObject> pivPaymentList);

	double getChequeTotal(String chequeNo, List<String> costCenterList, String status);

	double getFreshChequeTotal(String chequeNo, List<String> costCenterList);

	void updateChequePivStatus(String chequeNo, String status, String loggedUser,List<String> deptList);

	List<PivPayment> getReturnPivList(List<String> deptList, String status);

	double getPaidAmount(String pivNo);

	List<PivBankTran> getBankPaymentList(String bankRefNo);

	PivDetail getSetoffFromPIV(String pivNo);

	void setoffPiv(PivDetail pivDetail, PivDetail pivDetailSetoffFrom,String loggedUser);

	String isJobExist(String refNo);*/

	
	
}




