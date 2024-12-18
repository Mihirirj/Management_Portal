package com.it.dashboard.admin.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.it.dashboard.issue.domain.PivDetail;
import com.it.dashboard.util.common.EmSelector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Repository
@Transactional
public class DashboardDaoImpl extends EmSelector implements DashboardDao {
	//@PersistenceContext(unitName = "persistenceUnitPIV")
	private static final Log log = LogFactory.getLog(DashboardDaoImpl.class);
	//private EntityManager getEntityManager(appName);
	/*@Override
	public PivDetail findNewPiv(String appName){
		log.error("$$$$$$$$$ findNewPiv");
		try {
			String qryStr = "SELECT p FROM PivDetail p WHERE p.idNo = '831350466v' ";
			Query query = getEntityManager(appName).createQuery(qryStr);
		
			@SuppressWarnings("unchecked")
			List<PivDetail> list = query.getResultList();
			if (list.isEmpty()){
			
				return null;
			}
			else {
				
				return list.get(0);
			}
			
			} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
	}*/
	
}
