package com.it.dashboard.issue.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.it.dashboard.admin.repo.SecurityDaoImpl;
import com.it.dashboard.issue.domain.PivApplicant;
import com.it.dashboard.issue.domain.PivApplicantPK;
import com.it.dashboard.util.common.EmSelector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Repository
@Transactional
public class PivApplicantDaoImpl extends EmSelector implements PivApplicantDao{
	//@PersistenceContext(unitName = "persistenceUnitPIV")
   // private EntityManager getEntityManager(appName);
	private static final Log log = LogFactory.getLog(PivApplicantDaoImpl.class);
	

	@Override
	public PivApplicant findByPivNo(String pivNo, String appName) {
		log.error("$$$$$$$$$ findByPivNo return findByPivNo start");
		PivApplicant pivApplicant = null;
		try
		{
		/*CriteriaBuilder builder = getEntityManager(appName).getCriteriaBuilder();
        CriteriaQuery<PivApplicant> criteria = builder.createQuery(PivApplicant.class);
        Root<PivApplicant> pivApplicant = criteria.from(PivApplicant.class);

        criteria.select(pivApplicant).where(builder.equal(pivApplicant.get("id").get("pivNo"), pivNo));
        return getEntityManager(appName).createQuery(criteria).getSingleResult();*/
			String qryStr = "SELECT p FROM PivApplicant p WHERE p.id.pivNo = :pivNo ";
			Query query = getEntityManager(appName).createQuery(qryStr);
			query.setParameter("pivNo", pivNo);
			List<PivApplicant> list = query.getResultList();
			System.out.println("@@@@@@ pivapp size list "+list.size());
			if (list==null || list.size() == 0 || list.size() > 1)
				pivApplicant = null;
			else if (list.size() == 1)
				pivApplicant = list.get(0);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		log.error("$$$$$$$$$ findByPivNo return findByPivNo end");
		return pivApplicant;

	}
	
	/*@Override
	public PivApplicant findById(PivApplicantPK id) {
		try
		{
			return getEntityManager(appName).find(PivApplicant.class, id);
		}
		catch(NoResultException e)
		{
			return null;
		}
    

	}*/
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PivApplicant> findAllOrderedPivNo(String appName) {
		log.error("$$$$$$$$$ findAllOrderedPivNo start");
		List<PivApplicant> list = null;
		try {
			String qryStr = "SELECT p FROM PivApplicant p ";
			Query query = getEntityManager(appName).createQuery(qryStr);
			 list = query.getResultList();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			
		}
		log.error("$$$$$$$$$ findAllOrderedPivNo end");
		return list;
	}

	@Override
	public void register(PivApplicant pivApplicant, String appName) {
		log.error("$$$$$$$$$ register pivapplicant 1 start");
		getEntityManager(appName).persist(pivApplicant);
		log.error("$$$$$$$$$ register pivapplicant 1 end");
	}
	
	@Override
	public void register(PivApplicant pivApplicant, String nextPivNo, String appName) {
		log.error("$$$$$$$$$ register pivapplicant 2 start");
		PivApplicantPK pk=new PivApplicantPK();
		pk.setDeptId(pivApplicant.getId().getDeptId());
		pk.setPivNo(nextPivNo);
		pivApplicant.setId(pk);
		getEntityManager(appName).persist(pivApplicant);
		log.error("$$$$$$$$$ register pivapplicant 2 end");
	}

	@Override
	public void update(PivApplicant pivApplicant, String appName) {
		log.error("$$$$$$$$$ update pivapplicant start");
		getEntityManager(appName).merge(pivApplicant);
		log.error("$$$$$$$$$ update pivapplicant end");
	}

	@Override
	public void remove(String pivNo, String appName) {
		log.error("$$$$$$$$$ remove pivapplicant start");
		PivApplicant pivApplicant=findByPivNo(pivNo, appName);
		getEntityManager(appName).remove(pivApplicant);
		log.error("$$$$$$$$$ remove pivapplicant end");
	}

	

	



}