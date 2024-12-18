package com.it.dashboard.master.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.it.dashboard.admin.repo.SecurityDaoImpl;
import com.it.dashboard.master.domain.Applicant;
import com.it.dashboard.master.domain.Application;
import com.it.dashboard.master.domain.ApplicationReference;
import com.it.dashboard.master.domain.WiringLandDetail;
import com.it.dashboard.util.common.EmSelector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Repository
@Transactional
public class ApplicationDaoImpl extends EmSelector implements ApplicationDao{
	//@PersistenceContext(unitName = "persistenceUnitPIV")
    //private EntityManager getEntityManager(appName);
	private static final Log log = LogFactory.getLog(ApplicationDaoImpl.class);
	
	
	@Override
	public void registerApplicant(Applicant applicant, String appName) {
		log.error("$$$$$$$$$ registerApplicant");
		getEntityManager(appName).persist(applicant);
		
		
	}
	
	@Override
	public void registerApplication(Application application, String appName) {
		log.error("$$$$$$$$$ registerApplication");
		getEntityManager(appName).persist(application);
		
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Application findByAppNo(String appNo, String appName) {
		log.error("$$$$$$$$$ findByAppNo");
		String qryStr = "SELECT s FROM Application s WHERE TRIM(s.applicationNo)=:appNo";
		Query query = getEntityManager(appName).createQuery(qryStr);
		query.setParameter("appNo", appNo);
		List<Application> list = query.getResultList();
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Applicant findByIdNo(String idNo, String appName) {
		log.error("$$$$$$$$$ findByIdNo Applicant");
		String qryStr = "SELECT s FROM Applicant s WHERE s.idNo=:idNo";
		Query query = getEntityManager(appName).createQuery(qryStr);
		query.setParameter("idNo", idNo);
		List<Applicant> list = query.getResultList();
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
	}

	
	@Override
	public void update(Application application, String appName) {
		log.error("$$$$$$$$$ update application");
		getEntityManager(appName).merge(application);
		
	}
	
	/*
	@Override
	public String createApplicationAutoId(Application application, WiringLandDetail wiringLandDetail, String appIdPrefix) throws Exception{
		
		String nextAppId=appIdPrefix+getNextAppId(appIdPrefix);
		
		application.getId().setApplicationId(nextAppId);
		wiringLandDetail.getId().setApplicationId(nextAppId);
		
		String nextAppNo = getNextApplicationNo(application.getId().getDeptId()+"/ECR/22/");
		application.setApplicationNo(nextAppNo);
		
		getEntityManager(appName).persist(application);
		getEntityManager(appName).persist(wiringLandDetail);
		
		return nextAppId;
		
	}
	*/
	
	@Override
	public void createApplicationReference(ApplicationReference applicationReference, String appName) {
		log.error("$$$$$$$$$ createApplicationReference");
		getEntityManager(appName).persist(applicationReference);
	}
	
	@Override
	public Application createApplication(Application application, WiringLandDetail wiringLandDetail, String appIdPrefix, String appName) {
		System.out.println("createApplication##################### 1");
		String nextAppId=appIdPrefix+getNextAppId(appIdPrefix, appName);
		System.out.println("createApplication##################### 2");
		application.getId().setApplicationId(nextAppId);
		wiringLandDetail.getId().setApplicationId(nextAppId);
		System.out.println("createApplication##################### 3");
		String nextAppNo = getNextApplicationNo(application.getId().getDeptId()+"/ECR/22/", appName);
		application.setApplicationNo(nextAppNo);
		System.out.println("createApplication##################### 4");
		getEntityManager(appName).persist(application);
		getEntityManager(appName).persist(wiringLandDetail);
		
		return application;
		
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public String getNextAppId(String appIdPrefix, String appName) {
		//getEntityManager(webAppName);
		log.error("$$$$$$$$$ getNextAppId");
    	String sequence=null;
    	String like=appIdPrefix+"%";
    	String strQuery="select a.id.applicationId from Application a " +
    			"where a.id.applicationId like :like ORDER BY 1 DESC";
    	Query query=getEntityManager(appName).createQuery(strQuery);//SELECT MIS.TEST4_SEQ.NEXTVAL() FROM DUAL
    	query.setParameter("like", like);
    	List<String> list=query.getResultList();
    	if (list.size()!=0){
    		sequence=query.getResultList().get(0).toString().trim();
    		sequence=sequence.substring(14);//total 18 chars  year 12 
    		Integer i=Integer.parseInt(sequence)+1;
    		sequence=i.toString();
    		
    	}else{
    		sequence="0001";
    		
    	}
    	if(sequence.length()==1)
    		return "000"+sequence;
    	else if (sequence.length()==2)
    		return "00"+sequence;
    	else if (sequence.length()==3)
    		return "0"+sequence;
    	else return sequence;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String getNextApplicationNo(String applicationNoPrefix, String appName) {
		log.error("$$$$$$$$$ getNextApplicationNo");
		//getEntityManager(webAppName);
		String sequence=null;
    	String like=applicationNoPrefix+"%";
    	//String strQuery="select APPLICATION_NO from APPLICATION_REFERENCE where APPLICATION_NO like '"+like+"' ORDER BY 1 DESC";
    	String strQuery="select APPLICATION_NO from APPLICATIONS where APPLICATION_NO like '"+like+"' ORDER BY 1 DESC";
    	
    	Query query=getEntityManager(appName).createNativeQuery(strQuery);//SELECT MIS.TEST4_SEQ.NEXTVAL() FROM DUAL
    	List<String> list=query.getResultList();
    	if (list.size()!=0){
    		sequence=query.getResultList().get(0).toString().trim();
    		sequence=sequence.substring(14);//total 18
    		Integer i=Integer.parseInt(sequence)+1;
    		sequence=i.toString();
    		
    	}else{
    		sequence="0001";
    		
    	}
    	if(sequence.length()==1)
    		sequence = "000"+sequence;
    	else if (sequence.length()==2)
    		sequence =  "00"+sequence;
    	else if (sequence.length()==3)
    		sequence =  "0"+sequence;
    	//else  
    		//sequence = sequence;
    	return applicationNoPrefix+sequence;
	}
	
}
