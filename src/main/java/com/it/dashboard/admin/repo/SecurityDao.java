package com.it.dashboard.admin.repo;

import java.util.List;

import com.it.dashboard.admin.domain.Sauserm;


//import com.mis.security.domain.LoginDetail;
public interface SecurityDao {
	String getPassword(String userName, String appName);
	List<String> getAuthorizedCostCenters(String userName, String appName);
	Boolean validateLogin(String userName, String password, String appName) ;
	String getCostCenter(String userName, String appName);
	String getAuthorizedLevel(String userName, String appName);
	List<String> getUserList(String deptId, String userLevel, String appName);
	void updateUserLevel(String deptId,String userId, String userLevel, String appName);
	//void createAutoIdLoginDetail(LoginDetail loginDetail, String webAppName);
	//void createLoginDetail(LoginDetail loginDetail, String webAppName);
	String getNextLogId(String logIdPrefix, String appName);
	Sauserm getSauserm(String userName, String appName);
	
	

}
