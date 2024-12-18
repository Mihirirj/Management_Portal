package com.it.dashboard.master.repo;

import com.it.dashboard.master.domain.Applicant;
import com.it.dashboard.master.domain.Application;
import com.it.dashboard.master.domain.ApplicationReference;
import com.it.dashboard.master.domain.WiringLandDetail;

public interface ApplicationDao 
{

	void update(Application application, String appName);

	Application findByAppNo(String appNo, String appName);

	void registerApplicant(Applicant applicant, String appName);

	void registerApplication(Application application, String appName);

	String getNextAppId(String appIdPrefix, String appName);

	//String createApplicationAutoId(Application application, WiringLandDetail wiringLandDetail, String appIdPrefix)
	//		throws Exception;

	Applicant findByIdNo(String idNo, String appName);

	String getNextApplicationNo(String applicationNoPrefix, String appName);

	Application createApplication(Application application,
			WiringLandDetail wiringLandDetail, String appIdPrefix, String appName);
			
	void createApplicationReference(ApplicationReference applicationReference, String appName);

}
