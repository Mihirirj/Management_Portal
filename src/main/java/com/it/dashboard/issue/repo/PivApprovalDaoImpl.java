/**
 * 
 */
package com.it.dashboard.issue.repo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.it.dashboard.admin.domain.PivActivityGrid;
import com.it.dashboard.admin.repo.SecurityDaoImpl;
import com.it.dashboard.issue.domain.PivApproval;
import com.it.dashboard.issue.domain.PivApprovalPK;
import com.it.dashboard.util.common.EmSelector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Anobiya
 *
 */
@Repository
@Transactional
public class PivApprovalDaoImpl extends EmSelector implements PivApprovalDao {

	//@PersistenceContext(unitName = "persistenceUnitPIV")
	//private EntityManager getEntityManager(appName);
	private static final Log log = LogFactory.getLog(PivApprovalDaoImpl.class);
	/* (non-Javadoc)
	 * @see com.it.dashboard.issue.repo.PivApprovalDao#registerList1(java.lang.String, java.lang.String, java.util.List)
	 */
	@Override
	public void registerList(String deptId, String userRole, List<PivActivityGrid> activityList, String appName) {
		log.error("$$$$$$$$$ registerList activityList 1 start");
		try{
			
			for(int i = 0; i < activityList.size(); i++){
				if (activityList.get(i).getAction().equals("Allow")){
					PivApprovalPK pk = new PivApprovalPK();
					PivApproval pivApproval = new PivApproval();
					pk.setDeptId(deptId);
					pk.setUserRole(userRole);
					pk.setActivity(activityList.get(i).getActivity());
					pivApproval.setId(pk);
					pivApproval.setDescription(activityList.get(i).getDescription());
					getEntityManager(appName).merge(pivApproval);
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		log.error("$$$$$$$$$ registerList activityList 1 end");
	}
	
	@Override
	public void registerList(String deptId, String userRole, String loggedUser,List<PivActivityGrid> activityList, String appName) {
		log.error("$$$$$$$$$ registerList activityList 2 start");
		try{
			
			for(int i = 0; i < activityList.size(); i++){
				if (activityList.get(i).getAction().equals("Allow")){
					PivApprovalPK pk = new PivApprovalPK();
					PivApproval pivApproval = new PivApproval();
					pk.setDeptId(deptId);
					pk.setUserRole(userRole);
					pk.setActivity(activityList.get(i).getActivity());
					pivApproval.setId(pk);
					pivApproval.setDescription(activityList.get(i).getDescription());
					pivApproval.setAddUser(loggedUser);
					Timestamp updateTime=new Timestamp(new Date().getTime());
					pivApproval.setAddDate(updateTime);
					getEntityManager(appName).merge(pivApproval);
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		log.error("$$$$$$$$$ registerList activityList 2 end");
	}

	/* (non-Javadoc)
	 * @see com.it.dashboard.issue.repo.PivApprovalDao#getActivity(java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getActivity(String deptId, String userRole, String appName) {
		log.error("$$$$$$$$$ getActivity start");
		List<String> list = null;
		try{
			String qryStr = "SELECT p.id.activity FROM PivApproval p WHERE p.id.deptId = :deptId and p.id.userRole = :userRole";
			Query query = getEntityManager(appName).createQuery(qryStr);
			query.setParameter("deptId", deptId);
			query.setParameter("userRole", userRole);
			list = query.getResultList();
			
		}catch(Exception ex){
			ex.printStackTrace();
			
		}
		log.error("$$$$$$$$$ getActivity end");
		return list;
	}

	/* (non-Javadoc)
	 * @see com.it.dashboard.issue.repo.PivApprovalDao#findById(com.it.dashboard.issue.domain.PivApprovalPK)
	 */
	@Override
	public PivApproval findById(PivApprovalPK id, String appName) {
		log.error("$$$$$$$$$ findById PivApprovalPK start");
		 PivApproval pivApproval = getEntityManager(appName).find(PivApproval.class, id);
		log.error("$$$$$$$$$ findById PivApprovalPK end");
		return pivApproval;
	}

	/* (non-Javadoc)
	 * @see com.it.dashboard.issue.repo.PivApprovalDao#remove(com.it.dashboard.issue.domain.PivApprovalPK)
	 */
	@Override
	public void remove(PivApprovalPK id, String appName) {
		log.error("$$$$$$$$$ remove PivApprovalPK start");
		PivApproval pivApproval = findById(id, appName);
		getEntityManager(appName).remove(pivApproval);
		log.error("$$$$$$$$$ remove PivApprovalPK end");
	}
}