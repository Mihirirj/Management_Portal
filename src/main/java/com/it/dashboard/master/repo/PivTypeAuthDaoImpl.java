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
import com.it.dashboard.master.domain.PivTypeAuth;
import com.it.dashboard.util.common.EmSelector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Repository
@Transactional
public class PivTypeAuthDaoImpl extends EmSelector implements PivTypeAuthDao{
	//@PersistenceContext(unitName = "persistenceUnitPIV")
   // private EntityManager getEntityManager(appName);
	private static final Log log = LogFactory.getLog(PivTypeAuthDaoImpl.class);
	
	/**
	 * register with auto No
	*/
	@Override
	public void register(PivTypeAuth pivTypeAuth, String appName) {
		log.error("$$$$$$$$$ register");
		getEntityManager(appName).persist(pivTypeAuth);
		
		
	}
	
	@Override
	public void update(PivTypeAuth pivTypeAuth, String appName) {
		log.error("$$$$$$$$$ update");
		getEntityManager(appName).merge(pivTypeAuth);
		
	}

	@Override
	public void remove(String deptId,String titleCd, String appName) {
		log.error("$$$$$$$$$ remove");
		PivTypeAuth pivTypeAuth=findByReferenceNo(deptId, titleCd, appName);
		getEntityManager(appName).remove(pivTypeAuth);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public PivTypeAuth findByReferenceNo(String deptId,String titleCd, String appName) {
		log.error("$$$$$$$$$ findByReferenceNo");
		try {
			String qryStr = "SELECT p FROM PivTypeAuth p WHERE p.id.deptId = :deptId and p.id.titleCd like :titleCd ";
			Query query = getEntityManager(appName).createQuery(qryStr);
			query.setParameter("deptId", deptId);
			query.setParameter("titleCd", titleCd+"%");
			List<PivTypeAuth> list = query.getResultList();
			if (list.isEmpty())
				return null;
			else if (list.size() == 1)
				return list.get(0);
			throw new NonUniqueResultException();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<PivTypeAuth> getAuthPivTypesToDeptId(String deptId, String appName) {
		log.error("$$$$$$$$$ getAuthPivTypesToDeptId");
		try {
			/**
			 * select substr(title_cd,5,7) , title_nm from gltitlm where
			 * status=2 and title_cd like 'PIV%' order by title_cd ;
			 */

			String qryStr = "SELECT p FROM  PivTypeAuth p "+
							" WHERE p.id.deptId = :deptId";
			Query query = getEntityManager(appName).createQuery(qryStr);
			//query.setParameter("status", new BigDecimal(2));
			//query.setParameter("like", "PIV" + "%");
			query.setParameter("deptId", deptId);
			List<PivTypeAuth> list = query.getResultList();
			
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}
	
	
	
	
	
}
