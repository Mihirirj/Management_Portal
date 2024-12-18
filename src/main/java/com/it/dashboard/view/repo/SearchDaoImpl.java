/**
 * 
 */
package com.it.dashboard.view.repo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.it.dashboard.admin.repo.SecurityDaoImpl;
import com.it.dashboard.issue.domain.PivApplicant;
import com.it.dashboard.util.common.EmSelector;

import org.apache.commons.logging.Log;


/**
 * @author Anobiya
 *
 */

@Repository
@Transactional
public class SearchDaoImpl extends EmSelector implements SearchDao {

	//@PersistenceContext(unitName = "persistenceUnitPIV")
    //private EntityManager em;
		
	private static final Log log = LogFactory.getLog(SearchDaoImpl.class);
	
	@SuppressWarnings({ })
	@Override
	public List<PivApplicant> searchPIV(String pivNo, String pivType, String name, String idNo, Date issuedFrom,
			Date issuedTo, Date paidFrom, Date paidTo, String bankRefNo, List<String> authCostCenterList, String referenceNo, String appName) {
		log.error("$$$$$$$$$ searchPIV");
		Calendar calendar = Calendar.getInstance();
		String qryStr = " SELECT p "+
						" FROM PivApplicant p " ;
						//" WHERE p.pivDetail.id.pivNo =  :pivNo " ;
				
		String whereCondition	 = "";
		if(pivNo.trim().length()>0)
			whereCondition = whereCondition+" and p.id.pivNo =  :pivNo";
		if(pivType.trim().length()>0)
			whereCondition = whereCondition+" and p.pivDetail.titleCd =  :pivType";
		if(name.trim().length()>0)
			whereCondition = whereCondition+" and p.name like  :name";
		if(idNo.trim().length()>0)
			whereCondition = whereCondition+" and p.idNo =  :idNo";
		if(issuedFrom!=null && issuedTo!=null)
			whereCondition = whereCondition+" and p.pivDetail.issuedDate >=  :issuedFrom and p.pivDetail.issuedDate < :issuedTo";
		else if(issuedFrom!=null )
			whereCondition = whereCondition+" and p.pivDetail.issuedDate >=  :issuedFrom ";
		else if(issuedTo!=null)
			whereCondition = whereCondition+" and p.pivDetail.issuedDate < :issuedTo ";
		if(paidFrom!=null && paidTo!=null)
			whereCondition = whereCondition+" and p.pivDetail.paidTime >=  :paidFrom and p.pivDetail.paidTime < :paidTo ";
		else if(paidFrom!=null )
			whereCondition = whereCondition+" and p.pivDetail.paidTime >=  :paidFrom ";
		else if(paidTo!=null)
			whereCondition = whereCondition+" and p.pivDetail.paidTime < :paidTo ";
		if(bankRefNo.trim().length()>0)
			whereCondition = whereCondition+" and p.pivDetail.bankCheckNo =  :bankRefNo";
		if(referenceNo.trim().length()>0)
			whereCondition = whereCondition+" and p.pivDetail.referenceNo =  :referenceNo";
		if(whereCondition.trim().length()>0)
			qryStr = qryStr+ " where "+whereCondition.substring(5);
		qryStr = qryStr+ " and p.pivDetail.id.deptId in (:authCostCenterList)";
		qryStr = qryStr+ " order by p.pivDetail.issuedDate desc";
		
		System.out.println(qryStr);
		Query query = getEntityManager(appName).createQuery(qryStr);
		if(pivNo.trim().length()>0)
			query.setParameter("pivNo", pivNo);
		if(pivType.trim().length()>0)
			query.setParameter("pivType", pivType);
		if(name.trim().length()>0)
			query.setParameter("name", "%"+name+"%");
		if(idNo.trim().length()>0)
			query.setParameter("idNo", idNo);
		if(issuedFrom!=null && issuedTo!=null)
		{
			calendar.setTime(issuedTo);
			calendar.add(Calendar.DATE, 1);
			query.setParameter("issuedFrom", issuedFrom);
			query.setParameter("issuedTo", calendar.getTime());
		}
		else if(issuedFrom!=null )
			query.setParameter("issuedFrom", issuedFrom);
		else if(issuedTo!=null)
		{
			calendar.setTime(issuedTo);
			calendar.add(Calendar.DATE, 1);
			query.setParameter("issuedTo", calendar.getTime());
		}
		if(paidFrom!=null && paidTo!=null)
		{
			calendar.setTime(paidTo);
			calendar.add(Calendar.DATE, 1);
			query.setParameter("paidFrom", paidFrom);
			query.setParameter("paidTo", calendar.getTime());
		}
		else if(paidFrom!=null )
			query.setParameter("paidFrom", paidFrom);
		else if(paidTo!=null)
		{
			calendar.setTime(paidTo);
			calendar.add(Calendar.DATE, 1);
			query.setParameter("paidTo", calendar.getTime());
		}
		if(bankRefNo.trim().length()>0)
			query.setParameter("bankRefNo", bankRefNo);
		if(referenceNo.trim().length()>0)
			query.setParameter("referenceNo", referenceNo);
		query.setParameter("authCostCenterList", authCostCenterList);
		List<PivApplicant> list = query.getResultList();
		
		return list;
		
		
	}

	
}