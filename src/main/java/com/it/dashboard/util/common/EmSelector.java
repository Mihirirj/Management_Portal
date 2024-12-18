package com.it.dashboard.util.common;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;










public class EmSelector {
	@PersistenceContext(unitName = "entityManagerFactoryMgtPortal")
	private EntityManager emPIV1;
	
	@PersistenceContext(unitName="entityManagerFactoryMgtPortal")
	private EntityManager emPIV2;
	
	@PersistenceContext(unitName="entityManagerFactoryMgtPortal")
	private EntityManager emPIV3;
	
	
	
	
	
	public EntityManager getEntityManager(String appName){
		if (appName.equals("MGT") )
				return emPIV1;	
		else if (appName.equals("MGT") )
			return emPIV2;
		else 
			return emPIV3;
		
	}
	
	
	
	
	
	
	
	

}
