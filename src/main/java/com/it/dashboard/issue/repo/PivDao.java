package com.it.dashboard.issue.repo;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.it.dashboard.admin.domain.PivDashboardGrid;
import com.it.dashboard.admin.domain.PivHistory;
import com.it.dashboard.issue.domain.PivAmount;
import com.it.dashboard.issue.domain.PivAmountGrid;
import com.it.dashboard.issue.domain.PivApplicant;
import com.it.dashboard.issue.domain.PivDetail;
import com.it.dashboard.master.domain.Applicant;
import com.it.dashboard.master.domain.Application;
import com.it.dashboard.master.domain.WiringLandDetail;
import com.it.dashboard.payment.domain.PivPayment;
import com.it.dashboard.service.domain.PivDetailObject;
import com.it.dashboard.service.domain.PivSearchParamObject;
import com.it.dashboard.service.domain.PivSearchResultObject;
import com.it.dashboard.service.domain.PivServiceRequest;
import com.it.dashboard.service.domain.StampDutyObject;



public interface PivDao {
	public String insertPiv(PivDetail pivDetail, PivApplicant pivApplicant, ArrayList<PivAmount> pivAmountList, String appName);
	
	public void updatePiv(PivDetail pivDetail, PivApplicant pivApplicant, ArrayList<PivAmount> pivAmountList, String appName);

	List<PivAmountGrid> getAcctCodesByPivNo(String pivNo, String appName);

	//two methods to get issued piv details(pr)
	
		public List<PivApplicant> findNewPivDetails(String deptId, String appName);
		public int findNewPivCount(String deptId, String appName);
		
	//two methods to get paid piv details(pr)
		
		//public List<PivDashboardGrid> findPaidPivDetails(String deptId, String status);
		
		
		//method to count today total revenue
		public BigDecimal findTotalRevenue(String deptId, String status, String appName);
		
		//methods to get to be Approved and to be validated piv list and count
		public List<PivApplicant> getAuthorisedPivDetailList(String userRole,String deptId, String status, String appName);
		public int getAuthorisedPivDetailCount(String userRole,String deptId, String status, String appName) ;
		////////
		public void savePivHistory(String pivNo,String deptId,String status, String appName);

		//void updatePivStatus(PivDetail pivDetail);

		void updatePivStatus(PivDetail pivDetail, PivHistory pivHistory, String appName);

		void updatePivPayment(PivDetail pivDetail, PivPayment pivPayment, String appName);

		void removePivPayment(PivDetail pivDetail, PivPayment pivPayment, String appName);

		List<PivAmountGrid> getAcctCodesByPivNoWithoutZero(String pivNo, String appName);

		List<PivDashboardGrid> getAuthorisedPivList(String userRole,
				String deptId, String status, String appName);

		List<PivDashboardGrid> getTodayPivList(String deptId, String appName);

		String insertPiv(PivDetail pivDetail, PivApplicant pivApplicant,
				ArrayList<PivAmount> pivAmountList, PivHistory pivHistory, String returnChequeNo, String appName);

		List<PivSearchResultObject> serachPiv(PivSearchParamObject paramObj, String appName);

		List<PivDashboardGrid> findPaidPivDetails(String deptId, String appName);

		int findPaidPivCount(String deptId, String appName);

		List<StampDutyObject> getStampDutyList(String compId, Date fromDate,
				Date toDate, String appName);

		void cancelPayment(PivDetail pivDetail, String appName);

		


		

		String[] generatePivThruService(String appIdPrefix,
				Applicant applicant, Application application,
				WiringLandDetail wiringLandDetail, PivDetail pivDetail,
				PivApplicant pivApplicant, ArrayList<PivAmount> pivAmountList, String appName)
				throws Exception;

		void addPivServiceReq(PivServiceRequest pivSerReq, String appName);

		
		
		//List<PivAmountGrid> getAcctCodesByPivNo(String pivNo, String pivType);

}
