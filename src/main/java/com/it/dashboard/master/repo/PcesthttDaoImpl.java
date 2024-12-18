package com.it.dashboard.master.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.it.dashboard.admin.repo.SecurityDaoImpl;
import com.it.dashboard.master.domain.Pcesthtt;
import com.it.dashboard.util.common.EmSelector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Repository
@Transactional
public class PcesthttDaoImpl extends EmSelector implements PcesthttDao{
	//@PersistenceContext(unitName = "persistenceUnitPIV")
    //private EntityManager getEntityManager(appName);
	
	private static final Log log = LogFactory.getLog(PcesthttDaoImpl.class);
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Pcesthtt findByEstimateNo(String estimateNo, String appName) {
		log.error("$$$$$$$$$ findByEstimateNo");
		String qryStr = "SELECT s FROM Pcesthtt s WHERE TRIM(s.id.estimateNo)=:estimateNo";
		Query query = getEntityManager(appName).createQuery(qryStr);
		query.setParameter("estimateNo", estimateNo);
		List<Pcesthtt> list = query.getResultList();
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        else 
        	return null;
        
	}

	
	@Override
	public void update(Pcesthtt pcesthtt, String appName) {
		log.error("$$$$$$$$$ update");
		getEntityManager(appName).merge(pcesthtt);
		
	}
	
}
