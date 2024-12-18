package com.it.dashboard.refund.repo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.it.dashboard.admin.domain.PivHistory;
import com.it.dashboard.admin.repo.PivHistoryDao;
import com.it.dashboard.admin.repo.SecurityDaoImpl;
import com.it.dashboard.issue.domain.PivAmount;
import com.it.dashboard.issue.domain.PivApproval;
import com.it.dashboard.issue.domain.PivApprovalPK;
import com.it.dashboard.issue.domain.PivDetail;
import com.it.dashboard.issue.domain.PivDetailPK;
import com.it.dashboard.issue.repo.PivDetailDao;
import com.it.dashboard.refund.domain.PivRefund;
import com.it.dashboard.refund.domain.PivRefundPK;
import com.it.dashboard.util.common.EmSelector;

import org.apache.commons.logging.Log;

@Repository
@Transactional
public class RefundDaoImpl extends EmSelector implements RefundDao {

	private static final Log log = LogFactory.getLog(RefundDaoImpl.class);
	//@PersistenceContext(unitName = "persistenceUnitPIV")
	//private EntityManager em;
	@Autowired
	private PivDetailDao pivDetailDao;
	@Autowired
	private PivHistoryDao pivHistoryDao;

	@SuppressWarnings("unchecked")
	@Override
	public List<String> isRefundableAccCode(String appName) {			//get all the refundable account codes to a list//
		List<String> accdList;
		log.error("$$$$$$$$$ isRefundableAccCode");
		try {
			String qrystr = "select g.acCd from Glacctm g where g.isRefund='Y' ";
			Query query = getEntityManager(appName).createQuery(qrystr);

			List<String> list = query.getResultList();

			if (!(list.isEmpty())) {
				accdList = query.getResultList();
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return accdList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int isRefundablePiv(String deptId, String pivNo, String appName) {	//returns 0 or 1 after checking the given pivno is refundable or not by status//
		List<String> pivlist;
		log.error("$$$$$$$$$ isRefundablePiv");
		int value = 0;
		try {
			String qrystr = "select p.id.pivNo from PivDetail p where p.id.deptId=:deptId and p.status='P' ";
			Query query = getEntityManager(appName).createQuery(qrystr);
			query.setParameter("deptId", deptId);

			List<String> list = query.getResultList();

			if (!(list.isEmpty())) {
				pivlist = query.getResultList();
			} else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
		
		for (int j = 0; j < pivlist.size(); j++) {
			Boolean x = pivlist.get(j).trim().equals(pivNo);
			if (x) {
				value = 1;
				break;
			} else
				value = 0;
		}
		return value;
	}


	@Override
	public void insertRefundDetails(PivRefund pivRefund, String pivNo,PivDetail pivDetail, String appName) {	//insert details into refund table//
		log.error("$$$$$$$$$ insertRefundDetails");
		getEntityManager(appName).persist(pivRefund);	
		pivDetailDao.update(pivDetail, appName);
		
		
		
	}
	
	@Override
	public void insertRefundDetails(PivRefund pivRefund,  PivDetail pivDetail, PivHistory pivHistory, String appName) {	//insert details into refund table//
		log.error("$$$$$$$$$ insertRefundDetails");
		getEntityManager(appName).persist(pivRefund);	
		pivDetailDao.update(pivDetail, appName);
		pivHistoryDao.register(pivHistory, appName);
		
		
	}
	
	/*@Override
	public void updateRefund(PivRefund pivRefund){
		getEntityManager(appName).merge(pivRefund);
	}*/
	
	@Override
	public void updateRefundDetails(String pivNo,String deptId,String status,Timestamp approvalDate,String approvalUser, String appName) {
		log.error("$$$$$$$$$ updateRefundDetails");
		String qrystr="UPDATE PivRefund r SET r.status=:status , r.approvalDate=:approvalDate , r.approvalUser=:approvalUser WHERE r.id.pivNo=:pivNo and r.id.deptId=:deptId";
		Query qry=getEntityManager(appName).createQuery(qrystr);
		qry.setParameter("pivNo", pivNo);
		qry.setParameter("deptId", deptId);
		qry.setParameter("status", status);
		qry.setParameter("approvalDate", approvalDate);
		qry.setParameter("approvalUser", approvalUser);
		qry.executeUpdate();
	}
	
	@Override
	public void updateRefundDetails(PivRefund pivRefund, PivDetail pivDetail, PivHistory pivHistory, String appName) {
		log.error("$$$$$$$$$ updateRefundDetails");
		update(pivRefund, appName);	
		pivDetailDao.update(pivDetail,  appName);
		pivHistoryDao.register(pivHistory,  appName);
	}
	
	@Override
	public void updateRefundIssueDetails(String pivNo,String deptId,String status,Date refundDate,String refundUser,String docNo, String appName){
		log.error("$$$$$$$$$ updateRefundIssueDetails");
		String qrtstr="UPDATE PivRefund r SET r.status=:status , r.refundDate =:refundDate , r.refundUser=:refundUser,r.docNo=:docNo WHERE r.id.pivNo=:pivNo and r.id.deptId=:deptId";
		Query qry=getEntityManager(appName).createQuery(qrtstr);
		qry.setParameter("pivNo", pivNo);
		qry.setParameter("deptId", deptId);
		qry.setParameter("status", status);
		qry.setParameter("refundDate", refundDate);
		qry.setParameter("refundUser",refundUser);
		qry.setParameter("docNo",docNo);
		qry.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PivRefund> getRefundPivByStatus(String deptId, String status, String appName) {	
		log.error("$$$$$$$$$ getRefundPivByStatus");
		List<PivRefund> FRlist;
		String query="select r from PivRefund r where r.id.deptId = :deptId and r.status = :status order by requestDate desc";
		Query qry=getEntityManager(appName).createQuery(query);
		qry.setParameter("deptId", deptId);
		qry.setParameter("status", status);
		
			FRlist=qry.getResultList();
			
		
		return FRlist;
	}
	
	@Override
	public List<PivRefund> getRefundPivByStatus(List<String> deptList, String status, String appName) {	
		log.error("$$$$$$$$$ getRefundPivByStatus");
		List<PivRefund> FRlist;
		String query="select r from PivRefund r where r.id.deptId in (:deptList) and r.status = :status order by requestDate desc";
		Query qry=getEntityManager(appName).createQuery(query);
		qry.setParameter("deptList", deptList);
		qry.setParameter("status", status);
		
			FRlist=qry.getResultList();
			
		
		return FRlist;
	}

	@Override
	public BigDecimal getRequsetAmount(String pivNo, String appName) {
		log.error("$$$$$$$$$ getRequsetAmount");
		BigDecimal requestAmount;
		String query="select r.refundRequestAmount from PivRefund r where r.id.pivNo = :pivNo";
		Query qry=getEntityManager(appName).createQuery(query);
		qry.setParameter("pivNo", pivNo);
		requestAmount=(BigDecimal) qry.getSingleResult();
		return requestAmount;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAuthorizedActivity(String userRole, String deptId, String appName) {
		log.error("$$$$$$$$$ getAuthorizedActivity");
		//EntityManager em = getEntityManager(appName);
		List<String> activityList;
		String qrystr="select a.id.activity from PivApproval a where a.id.deptId=:deptId and a.id.userRole=:userRole";
		Query query=getEntityManager(appName).createQuery(qrystr);
		query.setParameter("deptId", deptId);
		query.setParameter("userRole", userRole);
		activityList=query.getResultList();
		
		return activityList;
	}


	

	//get refund requested pivs (FR) according to logged users' relevant cost centers//
	@SuppressWarnings({ "unchecked"})
	@Override
	public List<String> getCostCentersForLoggedUser(String loggedUserDeptId, String appName) {
		log.error("$$$$$$$$$ getCostCentersForLoggedUser");
		List<String> list = null;
		try{
			
			String querystr="select g.id.deptId from Gldeptm g where g.status='2' and g.compId IN (select a.compId from Gldeptm a where a.id.deptId=:loggedUserDeptId) "
					+ "UNION "
					+ "(select g.id.deptId from Gldeptm g where g.status='2' and g.compId IN (select c.id.compId from Glcompm c where c.status='2' and c.parentId IN (select a.compId from Gldeptm a where a.id.deptId=:loggedUserDeptId)))";
							
			Query qry=getEntityManager(appName).createQuery(querystr);
			qry.setParameter("loggedUserDeptId", loggedUserDeptId);
			
			list=qry.getResultList();
			
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		return list;
	}

	@Override
	public String getDeptIdforPIV(String pivNo, String appName) {
		log.error("$$$$$$$$$ getDeptIdforPIV");
		String dept;
		String queryStr="select p.id.deptId from PivDetail p where p.id.pivNo=:pivNo";
		Query qry=getEntityManager(appName).createQuery(queryStr);
		qry.setParameter("pivNo", pivNo);
		dept=(String) qry.getSingleResult();
		
		return dept;
	}
	
	@Override
	public void update(PivRefund pivRefund, String appName) {
		log.error("$$$$$$$$$ update");
		getEntityManager(appName).merge(pivRefund);

	}
	
	@Override
	public PivRefund findById(PivRefundPK id, String appName) {
		log.error("$$$$$$$$$ findById 1");
		return getEntityManager(appName).find(PivRefund.class, id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public PivRefund findById(String deptId, String pivNo, String appName) {
		log.error("$$$$$$$$$ findById 2");
		try {
			String qryStr = "SELECT p FROM PivRefund p "+
							" where p.id.deptId = :deptId "+
							" and p.id.pivNo = :pivNo ";
							
			Query query = getEntityManager(appName).createQuery(qryStr);
			query.setParameter("deptId", deptId);
			query.setParameter("pivNo", pivNo);
			
			PivRefund pivPayment = (PivRefund)query.getSingleResult();
			return pivPayment;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	
			
}
