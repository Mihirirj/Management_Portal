package com.it.dashboard.master.repo;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.it.dashboard.admin.repo.SecurityDaoImpl;
import com.it.dashboard.master.domain.Gldeptm;
import com.it.dashboard.util.common.EmSelector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Repository
@Transactional
public class GldeptmDaoImpl extends EmSelector implements GldeptmDao{
	//@PersistenceContext(unitName = "persistenceUnitPIV")
    //private EntityManager getEntityManager(appName);
	
	private static final Log log = LogFactory.getLog(GldeptmDaoImpl.class);
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Gldeptm findByDeptId(String deptId, String appName) {
		log.error("$$$$$$$$$ findByDeptId");
		String qryStr = "SELECT s FROM Gldeptm s WHERE TRIM(s.deptId)=:deptId";
		Query query = getEntityManager(appName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		List<Gldeptm> list = query.getResultList();
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getDeptIdToCompId(String compId, String appName) {
		log.error("$$$$$$$$$ getDeptIdToCompId");
		//getEntityManager(appName);
		String qryStr = "SELECT g.deptId FROM Gldeptm g WHERE g.compId=:compId";
		Query query = getEntityManager(appName).createQuery(qryStr);
		query.setParameter("compId", compId);
		List<String> list = query.getResultList();
		return list;
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> getDeptIdMap(String compId, String appName) 
	{
		log.error("$$$$$$$$$ getDeptIdMap");
	   // create empty map to store results in. If we don't find results, an empty hashmap will be returned
	   Map<String, String> results = new LinkedHashMap<String, String>();

	   String jpaQuery = " Select g from  Gldeptm  g where g.compId in ( select c.compId from Glcompm c where c.compId=:compId or c.parentId=:compId)  and g.status=2 "+
						" order by  g.deptId ";
	   // Construct and run query
	  // String jpaQuery = "SELECT g FROM Gldeptm g WHERE g.compId=:compId order by g.deptId ";
	   Query query = getEntityManager(appName).createQuery(jpaQuery);
		query.setParameter("compId", compId);
		List<Gldeptm> list = query.getResultList();

	   // Place results in map
	   for (int i=0;i<list.size();i++) {
		   Gldeptm  gldeptm = list.get(i);
	      results.put(gldeptm.getDeptId(), gldeptm.getDeptId()+" - "+gldeptm.getDeptNm());
	   }

	   return results;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> getAuthDeptIdMap(String userName, String appName) 
	{
		log.error("$$$$$$$$$ getAuthDeptIdMap");
	   // create empty map to store results in. If we don't find results, an empty hashmap will be returned
	   Map<String, String> results = new LinkedHashMap<String, String>();

	   
	   
	   String jpaQuery = 	" select s.id.deptId, g.deptNm from Sausrdpm  s , Gldeptm g "+
			   				" where trim(s.id.userId) =  :userName "+
			   				" and s.id.deptId = g.deptId "+
			   				" and s.status = 2 "+
			   				" order by  g.deptId ";
	    
	   Query query = getEntityManager(appName).createQuery(jpaQuery);
		query.setParameter("userName", userName);
		
		List<Object[]> list = query.getResultList();
		
	   // Place results in map
		if (!list.isEmpty()) {
			for (Object[] agents: list) {
			      String deptId = (String)agents[0];
				  String deptNm = (String)agents[1];
				  results.put(deptId, deptId+" - "+deptNm);
			 }
		   
		}

	   return results;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> getAuthDeptIdMap(String userName, String defDeptId, String appName) 
	{
		log.error("$$$$$$$$$ getAuthDeptIdMap");
	   // create empty map to store results in. If we don't find results, an empty hashmap will be returned
	   Map<String, String> results = new LinkedHashMap<String, String>();
	   
	   
	   
	   String jpaQuery = 	" select s.id.deptId, g.deptNm from Sausrdpm  s , Gldeptm g "+
			   				" where trim(s.id.userId) =  :userName "+
			   				" and s.id.deptId = g.deptId "+
			   				" and s.id.deptId != :defDeptId "+
			   				" and s.status = 2 "+
			   				" order by  g.deptId ";
	    
	   Query query = getEntityManager(appName).createQuery(jpaQuery);
		query.setParameter("userName", userName);
		query.setParameter("defDeptId", defDeptId);
		List<Object[]> list = query.getResultList();
		
	   // Place results in map
		if (!list.isEmpty()) {
			for (Object[] agents: list) {
			      String deptId = (String)agents[0];
				  String deptNm = (String)agents[1];
				  results.put(deptId, deptId+" - "+deptNm);
			 }
		   
		}

	   return results;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAuthDeptIdList(String userName, String defDeptId, String appName) 
	{
		log.error("$$$$$$$$$ getAuthDeptIdList");
	   String jpaQuery = 	" select s.id.deptId from Sausrdpm  s  "+
			   				" where trim(s.id.userId) =  :userName "+
			   				" and s.id.deptId != :defDeptId "+
			   				" and s.status = 2 ";
			   				
	    
	   Query query = getEntityManager(appName).createQuery(jpaQuery);
		query.setParameter("userName", userName);
		query.setParameter("defDeptId", defDeptId);
		List<String> list = query.getResultList();
		

	   return list;
	}
}
