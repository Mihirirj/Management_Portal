package com.it.dashboard.issue.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.it.dashboard.admin.repo.SecurityDaoImpl;
import com.it.dashboard.issue.domain.PivAmount;
import com.it.dashboard.issue.domain.PivAmountPK;
import com.it.dashboard.util.common.EmSelector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Repository
@Transactional
public class PivAmountDaoImpl extends EmSelector implements PivAmountDao {
	//@PersistenceContext(unitName = "persistenceUnitPIV")
	//private EntityManager getEntityManager(appName);
	private static final Log log = LogFactory.getLog(PivAmountDaoImpl.class);
	@Override
	public PivAmount findById(PivAmountPK id, String appName) {
		log.error("$$$$$$$$$ findById PivAmountPK start");
		PivAmount pivAmount = getEntityManager(appName).find(PivAmount.class, id);
		log.error("$$$$$$$$$ findById PivAmountPK end");
		return pivAmount;
	}
	
	@Override
	public List <PivAmount> findByPivNo(String pivNo, String appName) {
		/*CriteriaBuilder builder = getEntityManager(appName).getCriteriaBuilder();
		CriteriaQuery<PivAmount> criteria = builder
				.createQuery(PivAmount.class);
		Root<PivAmount> pivAmount = criteria.from(PivAmount.class);

		/*
		 * Swap criteria statements if you would like to try out type-safe
		 * criteria queries, a new feature in JPA 2.0
		 * criteria.select(member).orderBy(cb.asc(member.get(Member_.name)));
		 */

		/*criteria.select(pivAmount).where(
				builder.equal(pivAmount.get("id").get("pivNo"), pivNo));
		return getEntityManager(appName).createQuery(criteria).getResultList();*/
		log.error("$$$$$$$$$ findByPivNo return pivamount list start");
		String qryStr = "SELECT p FROM PivAmount p WHERE p.id.pivNo = :pivNo ";
		Query query = getEntityManager(appName).createQuery(qryStr);
		query.setParameter("pivNo", pivNo);
		List<PivAmount> list = query.getResultList();
		log.error("$$$$$$$$$ findByPivNo return pivamount list end");
		return list;
		

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PivAmount> findAllOrderedPivNo(String appName) {
		log.error("$$$$$$$$$ findAllOrderedPivNo start");
		List<PivAmount> list = null;
		try {
			String qryStr = "SELECT p FROM PivAmount p ";
			Query query = getEntityManager(appName).createQuery(qryStr);
			list = query.getResultList();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			
		}
		log.error("$$$$$$$$$ findAllOrderedPivNo end");
		return list;
		
	}

	@Override
	public void register(PivAmount pivAmount, String appName) {
		log.error("$$$$$$$$$ register pivAmount 1 start");
		getEntityManager(appName).persist(pivAmount);
		log.error("$$$$$$$$$ register pivAmount 1 end");

	}

	@Override
	public void register(PivAmount pivAmount, String nextPivNo, String appName) {
		log.error("$$$$$$$$$ register pivAmount 2 start");
		PivAmountPK pk = new PivAmountPK();
		pk.setAccountCode(pivAmount.getId().getAccountCode());
		pk.setDeptId(pivAmount.getId().getDeptId());
		pk.setPivNo(nextPivNo);
		pivAmount.setId(pk);
		getEntityManager(appName).persist(pivAmount);
		log.error("$$$$$$$$$ register pivAmount 2 end");

	}

	@Override
	public void registerList(List<PivAmount> pivAmountList, String appName) {
		log.error("$$$$$$$$$ registerList pivAmount 1 start");
		for (int i = 0; i < pivAmountList.size(); i++) {
			getEntityManager(appName).persist(pivAmountList.get(i));

		}
		log.error("$$$$$$$$$ registerList pivAmount 1 end");

	}
	
	@Override
	public void updateList(List<PivAmount> pivAmountList, String appName) {
		log.error("$$$$$$$$$ updateList pivamount start");
		for (int i = 0; i < pivAmountList.size(); i++) {
			getEntityManager(appName).merge(pivAmountList.get(i));

		}
		log.error("$$$$$$$$$ updateList pivamount end");

	}

	@Override
	public void update(PivAmount pivAmount, String appName) {
		log.error("$$$$$$$$$ update pivamount start");
		getEntityManager(appName).merge(pivAmount);
		log.error("$$$$$$$$$ update pivamount end");
	}

	@Override
	public void registerList(List<PivAmount> pivAmountList, String nextPivNo, String appName) {
		log.error("$$$$$$$$$ registerList pivamount 2 start");
		//try {
			for (int i = 0; i < pivAmountList.size(); i++) {
				register(pivAmountList.get(i), nextPivNo, appName);
	
			}
		/*} catch (Exception ex) {
			ex.printStackTrace();
	
		}*/
			log.error("$$$$$$$$$ registerList pivamount 2 end");
	
	}

	@Override
	public void remove(PivAmountPK id, String appName) {
		log.error("$$$$$$$$$ remove pivamount start");
		PivAmount pivAmount = findById(id,appName);
		getEntityManager(appName).remove(pivAmount);
		log.error("$$$$$$$$$ remove pivamount end");

	}

	
}
