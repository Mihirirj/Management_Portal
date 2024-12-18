package com.it.dashboard.smc.dao;
import com.it.dashboard.master.domain.Applicant;
import com.it.dashboard.master.domain.Application;







import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.List;









//Chart imports
import javax.persistence.Query;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.it.dashboard.master.domain.TableRowValue;
import com.it.dashboard.smc.model.SearchModel;


public interface EstimateDao {
	public int pendingEstimateCountByDivision(String division, String appName);
	public int getAccountNotOpened(String division, String appName);
    public int pivPaidNotEnergisedcount(String division, String appName);
    public int PendingSolarEatimatesCountByDivision(String division, String appName);
	
	List<TableRowValue> pendingEstimateDetailByDivision(String division,
			String appName);

	List<TableRowValue> pendingEstimateDetailByProvince(String province,
			String appName);

 // Method for retrieving chart data
    List<Object[]> getDataForLoanApplications(String appName, String startDate, String endDate);

    List<Object[]> NewSolarAccounts(String appName, String startDate, String endDate);
    
    List<Object[]> getDataForNewAccounts(String appName, String startDate, String endDate);
    
    // New method to retrieve Search Data 
	List<SearchModel> getSearchDataByIdNo(String idNo);
    List<SearchModel> getSearchDataByAccountNo(String accountNo);
    List<SearchModel>getSearchDataByApplicationNo(String applicationNo);
    List<SearchModel>getSearchDataByJobNo(String applicationNo);
    List<SearchModel>getSearchDataByEstimationNo(String estimationNo);
//    List<SearchModel> getSearchDataByAccountNo(String accountNo);
    
    List<Applicant> getDataById(String idNo);



}
	
