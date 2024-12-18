package com.it.dashboard.issue.repo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.it.dashboard.admin.repo.SecurityDaoImpl;
import com.it.dashboard.issue.domain.PivDetail;
import com.it.dashboard.issue.domain.PivDetailPK;
import com.it.dashboard.payment.domain.PivPaymentGrid;
import com.it.dashboard.util.common.EmSelector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Repository
@Transactional
public class PivDetailDaoImpl extends EmSelector implements PivDetailDao{
//	@PersistenceContext(unitName = "persistenceUnitPIV")
   // private EntityManager getEntityManager(appName);
	private static final Log log = LogFactory.getLog(PivDetailDaoImpl.class);
	@SuppressWarnings("unchecked")
	@Override
	public PivDetail findByReferenceNo(String referenceNo, String appName) {
		log.error("$$$$$$$$$ findByReferenceNo");
		PivDetail pivDetail = null;
		try {
			String qryStr = "SELECT p FROM PivDetail p WHERE trim(p.referenceNo) = :referenceNo ";
			//System.out.println("em obj findByReferenceNo "+getEntityManager(appName));
			Query query = getEntityManager(appName).createQuery(qryStr);
			query.setParameter("referenceNo", referenceNo);
			List<PivDetail> list = query.getResultList();
			if (list == null || list.size()==0)
				pivDetail = null;
			else if (list.size() == 1)
				pivDetail = list.get(0);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			
		}
		log.error("$$$$$$$$$ findByReferenceNo"); 
		return pivDetail;

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public PivDetail findByCAReferenceNo(BigDecimal referenceNo, String deptId, String appName) {
		log.error("$$$$$$$$$ findByCAReferenceNo");
		PivDetail pivDetail = null;
		try {
			String qryStr = "SELECT p FROM PivDetail p WHERE p.id.deptId = :deptId and p.caApplicationNo = :referenceNo ";
			Query query = getEntityManager(appName).createQuery(qryStr);
			//log.error("em obj findByCAReferenceNo "+getEntityManager(appName));
			query.setParameter("referenceNo", referenceNo);
			query.setParameter("deptId", deptId);
			List<PivDetail> list = query.getResultList();
			if (list == null || list.size()==0)
				pivDetail = null;
			else if (list.size() == 1)
				pivDetail = list.get(0);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			
		}
		log.error("$$$$$$$$$ findByCAReferenceNo");   
		return pivDetail;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Object[] findByCAApplicationNo(BigDecimal referenceNo, String referenceType, String appName) {
		log.error("$$$$$$$$$ findByCAApplicationNo");
		Object[] objArr = null;
		try {
			String qryStr = "SELECT p.id.pivNo, p.bankCheckNo, p.pivAmount, p.issuedDate FROM PivDetail p WHERE p.referenceType = :referenceType and p.caApplicationNo = :referenceNo ";
			Query query = getEntityManager(appName).createQuery(qryStr);
			//System.out.println("em obj findByCAApplicationNo "+getEntityManager(appName));
			query.setParameter("referenceNo", referenceNo);
			query.setParameter("referenceType", referenceType);
			
			List<Object[]> list = query.getResultList();
			
			if (list == null || list.size()==0)
				objArr = null;
			else 
			{
				objArr =  list.get(0);
				
				
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			
		}
		log.error("$$$$$$$$$ findByCAApplicationNo");   
		return objArr;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public PivDetail findByPivNo(String pivNo, String appName) {
		log.error("$$$$$$$$$ findByPivNo"+pivNo);
		PivDetail pivDetail = null;
		try {
			String qryStr = "SELECT p FROM PivDetail p WHERE p.id.pivNo = :pivNo ";
			Query query = getEntityManager(appName).createQuery(qryStr);
			//System.out.println("em obj findByPivNo "+getEntityManager(appName));
			query.setParameter("pivNo", pivNo);
			List<PivDetail> list = query.getResultList();
			
			if (list==null || list.isEmpty() || list.size()==0)
				pivDetail = null;
			else if (list.size() == 1)
				pivDetail = list.get(0);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			
		}
		log.error("$$$$$$$$$ findByPivNo"); 
		return pivDetail;

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public PivDetail findByBankCheckNo(String bankCheckNo, String appName) {
		log.error("$$$$$$$$$ findByBankCheckNo");
		PivDetail pivDetail = null;
		try {
			String qryStr = "SELECT p FROM PivDetail p WHERE p.bankCheckNo = :bankCheckNo and p.status != 'D' ";
			Query query = getEntityManager(appName).createQuery(qryStr);
			//System.out.println("em obj findByBankCheckNo "+getEntityManager(appName));
			query.setParameter("bankCheckNo", bankCheckNo);
			List<PivDetail> list = query.getResultList();
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
	
	@SuppressWarnings("unchecked")
	@Override
	public PivDetail findByBankCheckNo(String bankCheckNo, String status, String appName) {
		log.error("$$$$$$$$$ findByBankCheckNo");
		PivDetail pivDetail = null;
		try {
			String qryStr = "SELECT p FROM PivDetail p WHERE p.bankCheckNo = :bankCheckNo and p.status = :status";
			//System.out.println("em obj findByBankCheckNo "+getEntityManager(appName));
			Query query = getEntityManager(appName).createQuery(qryStr);
			query.setParameter("bankCheckNo", bankCheckNo);
			query.setParameter("status", status);
			List<PivDetail> list = query.getResultList();
			if (list.isEmpty())
				pivDetail = null;
			else if (list.size() == 1)
				pivDetail = list.get(0);
			throw new NonUniqueResultException();
		} catch (Exception ex) {
			ex.printStackTrace();
			
		}
		log.error("$$$$$$$$$ findByBankCheckNo");   
		return pivDetail;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean isPivExistByReferenceNo(String referenceNo, String appName) {
		log.error("$$$$$$$$$ isPivExistByReferenceNo");
		boolean isExist = false;
		try {
			String qryStr = "SELECT p FROM PivDetail p WHERE p.referenceNo = :referenceNo ";
			Query query = getEntityManager(appName).createQuery(qryStr);
			//System.out.println("em obj isPivExistByReferenceNo "+getEntityManager(appName));
			query.setParameter("referenceNo", referenceNo);
			List<PivDetail> list = query.getResultList();
			if (list.isEmpty())
				isExist = false;
			else 
				isExist = true;
			
		} catch (Exception ex) {
			ex.printStackTrace();
			
		}
		log.error("$$$$$$$$$ isPivExistByReferenceNo");
		 return isExist;   

	}
	
	/*@Override
	public PivDetail findByPivNo(String pivNo) {
		CriteriaBuilder builder = getEntityManager(appName).getCriteriaBuilder();
        CriteriaQuery<PivDetail> criteria = builder.createQuery(PivDetail.class);
        Root<PivDetail> pivDetail = criteria.from(PivDetail.class);

        /*
         * Swap criteria statements if you would like to try out type-safe criteria queries, a new
         * feature in JPA 2.0 criteria.select(member).orderBy(cb.asc(member.get(Member_.name)));
         */

        /*criteria.select(pivDetail).where(builder.equal(pivDetail.get("id").get("pivNo"), pivNo));
        return getEntityManager(appName).createQuery(criteria).getSingleResult();
    

	}*/

	@SuppressWarnings("unchecked")
	@Override
	public List<PivDetail> findAllOrderedReferenceNo(String appName) {
		log.error("$$$$$$$$$ findAllOrderedReferenceNo");
		try {
			String qryStr = "SELECT p FROM PivDetail p ";
			//System.out.println("em obj findAllOrderedReferenceNo "+getEntityManager(appName));
			Query query = getEntityManager(appName).createQuery(qryStr);
			List<PivDetail> list = query.getResultList();
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
	}
	/**
	 * register with auto No
	*/
	@Override
	public void register(PivDetail pivDetail, String appName) {
		//System.out.println("em obj register "+getEntityManager(appName));
		log.error("$$$$$$$$$ register pivDetail 1");
		getEntityManager(appName).persist(pivDetail);
		log.error("$$$$$$$$$ register pivDetail 1");
		
	}
	
	@Override
	public void register(PivDetail pivDetail, String nextPivNo, String appName) {
		log.error("$$$$$$$$$ register pivdetail");
		PivDetailPK pk=new PivDetailPK();
		pk.setPivNo(nextPivNo);
		pk.setDeptId(pivDetail.getId().getDeptId());
		pivDetail.setId(pk);
		getEntityManager(appName).persist(pivDetail);
		//System.out.println("em obj register "+getEntityManager(appName));
		log.error("$$$$$$$$$ register pivdetail");
	}
	

	@Override
	public void update(PivDetail pivDetail, String appName) {
		log.error("$$$$$$$$$ update pivdetail");
		getEntityManager(appName).merge(pivDetail);
		//System.out.println("em obj update "+getEntityManager(appName));
		log.error("$$$$$$$$$ update pivdetail");
	}

	@Override
	public void remove(String referenceNo, String appName) {
		log.error("$$$$$$$$$ remove pivdetail");
		PivDetail pivDetail=findByReferenceNo(referenceNo, appName);
		getEntityManager(appName).remove(pivDetail);
		//System.out.println("em obj remove "+getEntityManager(appName));
		log.error("$$$$$$$$$ remove pivdetail");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String getNextPivNo(String pivNoPrefix, String appName) {
		log.error("$$$$$$$$$ getNextPivNo");
		String sequence=null;
    	String like=pivNoPrefix+"%";
    	String strQuery="select p.id.pivNo from PivDetail p where p.id.pivNo like :like ORDER BY 1 DESC";
    	Query query=getEntityManager(appName).createQuery(strQuery);//SELECT MIS.TEST4_SEQ.NEXTVAL() FROM DUAL
    	//System.out.println("em obj getNextPivNo "+getEntityManager(appName));
    	query.setParameter("like", like);
    	List<String> list=query.getResultList();
    	if (list.size()!=0){
    		sequence=query.getResultList().get(0).toString().trim();
    		sequence=sequence.substring(sequence.length()-4);//total 22 chars
    		Integer i=Integer.parseInt(sequence)+1;
    		sequence=i.toString();
    	}else{
    		sequence="0001";
    	}
    	if(sequence.length()==1)
    		return pivNoPrefix+"000"+sequence;
    	else if (sequence.length()==2)
    		return pivNoPrefix+"00"+sequence;
    	else if (sequence.length()==3)
    		return pivNoPrefix+"0"+sequence;
    	else return pivNoPrefix+sequence;
    	
    		}


	@SuppressWarnings({ "unchecked" })
	@Override
	public List<PivDetail> getPivListByStatus(String deptId, String status, String appName) {
		log.error("$$$$$$$$$ getPivListByStatus");
		/**CriteriaBuilder builder = getEntityManager(appName).getCriteriaBuilder();
        CriteriaQuery<PivDetail> criteria = builder.createQuery(PivDetail.class);
        Root<PivDetail> pivDetail = criteria.from(PivDetail.class);

        criteria.select(pivDetail).where(builder.equal(pivDetail.get("id").get("deptId"), deptId)).where(builder.equal(pivDetail.get("status"), status));
        //criteria.select(pivDetail);
        //criteria.where(builder.equal(pivDetail.get("id").get("deptId"), deptId)); 
        //criteria.where(builder.equal(pivDetail.get("status"), status));
        List<Order> orderList = new ArrayList();
        orderList.add(builder.asc(pivDetail.get("id").get("pivNo")));
        criteria.orderBy(orderList);
        return getEntityManager(appName).createQuery(criteria).getResultList();  
        **/
        try {
        	String qryStr = "select p from PivDetail p where p.id.deptId = :deptId and p.status = :status ";
        	//System.out.println("em obj getPivListByStatus "+getEntityManager(appName));
        	Query query = getEntityManager(appName).createQuery(qryStr);
			query.setParameter("deptId", deptId);
			query.setParameter("status", status);
			List<PivDetail> list = query.getResultList();
			return list;
        } catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
        
		
	}
	
	@SuppressWarnings({ "unchecked" })
	@Override
	public List<String> getPivNoListByStatus(String deptId, String status,
			String sysName, String appName) {
		log.error("$$$$$$$$$ getPivNoListByStatus");
		try {
        	String qryStr = "select p.id.pivNo from PivDetail p "+
        					"where p.id.deptId = :deptId "+
        					"and p.status = :status "+
        					"and p.sysType = :sysName "+
        					" order by enteredTime desc";
        	//System.out.println("em obj getPivNoListByStatus "+getEntityManager(appName));
        	Query query = getEntityManager(appName).createQuery(qryStr);
			query.setParameter("deptId", deptId);
			query.setParameter("status", status);
			query.setParameter("sysName", sysName);
			List<String> list = query.getResultList();
			return list;
        } catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
		
	}
	
	@SuppressWarnings({ "unchecked" })
	@Override
	public List<String> getPivNoListByStatus(List deptIdList, String status,
			String sysName, String appName) {
		log.error("$$$$$$$$$ getPivNoListByStatus");
		try {
			
        	String qryStr = "select p.id.pivNo from PivDetail p "+
        					"where p.id.deptId in (:deptIdList) "+
        					"and p.status = :status "+
        					"and p.sysType = :sysName "+
        					" order by enteredTime desc";
        	//System.out.println("em obj getPivNoListByStatus "+getEntityManager(appName));
        	Query query = getEntityManager(appName).createQuery(qryStr);
			query.setParameter("deptIdList", deptIdList);
			query.setParameter("status", status);
			query.setParameter("sysName", sysName);
			List<String> list = query.getResultList();
			return list;
        } catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PivDetail> getAuthorisedPivDetailListByStatus(String userRole,
			String deptId, String status, String appName) {
		log.error("$$$$$$$$$ getAuthorisedPivDetailListByStatus");
		try {
			String qryStr = "select p from PivDetail p, PivApproval a where p.id.deptId = :deptId and p.status = :status and p.id.deptId = a.id.deptId and p.status = a.id.activity and a.id.userRole = :userRole ";
			//System.out.println("em obj getAuthorisedPivDetailListByStatus "+getEntityManager(appName));
			Query query = getEntityManager(appName).createQuery(qryStr);
			query.setParameter("deptId", deptId);
			query.setParameter("userRole", userRole);
			query.setParameter("status", status);
			List<PivDetail> list = query.getResultList();
			return list;
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAuthorisedPivNoListByStatus(String userRole,
			String deptId, String status, String appName) {
		log.error("$$$$$$$$$ getAuthorisedPivNoListByStatus 1");
		try {
			String qryStr = "select p.id.pivNo from PivDetail p, PivApproval a "+
							"where p.id.deptId = :deptId "+
							"and p.status = :status "+
							"and p.id.deptId = a.id.deptId "+
							"and p.status = a.id.activity "+
							"and a.id.userRole = :userRole "+
							"order by p.enteredTime desc";
			//System.out.println("em obj getAuthorisedPivNoListByStatus "+getEntityManager(appName));
			Query query = getEntityManager(appName).createQuery(qryStr);
			query.setParameter("deptId", deptId);
			query.setParameter("userRole", userRole);
			query.setParameter("status", status);
			List<String> list = query.getResultList();
			return list;
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAuthorisedPivNoListByStatus(String userRole,
			List deptIdList, String status, String appName) {
		log.error("$$$$$$$$$ getAuthorisedPivNoListByStatus 2");
		try {
			String qryStr = "select p.id.pivNo from PivDetail p "+
							"where p.id.deptId in (:deptIdList) "+
							"and p.status = :status "+
							"order by p.enteredTime desc";
			//System.out.println("em obj getAuthorisedPivNoListByStatus "+getEntityManager(appName));
			Query query = getEntityManager(appName).createQuery(qryStr);
			query.setParameter("deptIdList", deptIdList);
			//query.setParameter("userRole", userRole);
			query.setParameter("status", status);
			List<String> list = query.getResultList();
			return list;
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getApprovedPivNoListByUser(String user,	String deptId, String appName) {
		log.error("$$$$$$$$$ getApprovedPivNoListByUser");
		try {
			
			String qryStr = "select p.id.pivNo from PivDetail p "+
							"where p.id.deptId = :deptId "+
							"and p.status = 'A' "+
							"and p.approvedBy = :user "+
							"order by p.enteredTime desc";
			//System.out.println("em obj getApprovedPivNoListByUser "+getEntityManager(appName));
			Query query = getEntityManager(appName).createQuery(qryStr);
			query.setParameter("deptId", deptId);
			query.setParameter("user", user);
			List<String> list = query.getResultList();
			return list;
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getApprovedPivNoListByUser(String user,	List<String> deptIdList, String appName) {
		log.error("$$$$$$$$$ getApprovedPivNoListByUser");
		try {
			String qryStr = "select p.id.pivNo from PivDetail p "+
							"where p.id.deptId in :deptIdList "+
							"and p.status = 'A' "+
							"and p.approvedBy = :user "+
							"order by p.enteredTime desc";
			//System.out.println("em obj getApprovedPivNoListByUser "+getEntityManager(appName));
			Query query = getEntityManager(appName).createQuery(qryStr);
			query.setParameter("deptIdList", deptIdList);
			query.setParameter("user", user);
			List<String> list = query.getResultList();
			return list;
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
	}

	@Override
	public void updateStatus(String pivNo, String deptId,String status, String appName) {
		log.error("$$$$$$$$$ addPivServiceReq");
		try {
			
			String qryStr = "UPDATE PivDetail p SET p.status=:status WHERE p.id.pivNo=:pivNo and p.id.deptId=:deptId";
			//System.out.println("em obj updateStatus "+getEntityManager(appName));
			Query query = getEntityManager(appName).createQuery(qryStr);
			query.setParameter("pivNo", pivNo);
			query.setParameter("deptId", deptId);
			query.setParameter("status", status);
			query.executeUpdate();
        } catch (Exception ex) {
			ex.printStackTrace();
			
		}
		log.error("$$$$$$$$$ addPivServiceReq");

	}
	
	@Override
	public void updatePaiddate(String pivNo, String deptId,Date paidDate,String paidAgent,String paidBranch, String appName) {
		log.error("$$$$$$$$$ updatePaiddate");
		try {
			
			String qryStr = "UPDATE PivDetail p SET p.paidDate=:paidDate, p.paidAgent=:paidAgent, p.paidBranch=:paidBranch WHERE p.id.pivNo=:pivNo and p.id.deptId=:deptId";
			//System.out.println("em obj updatePaiddate "+getEntityManager(appName));
			Query query = getEntityManager(appName).createQuery(qryStr);
			query.setParameter("pivNo", pivNo);
			query.setParameter("deptId", deptId);
			
			query.setParameter("paidDate", paidDate);
			query.setParameter("paidAgent", paidAgent);
			query.setParameter("paidBranch", paidBranch);
			query.executeUpdate();
        } catch (Exception ex) {
			ex.printStackTrace();
			
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PivDetail> getChequePaymententPived(String deptId, String status, String appName) {
		try {
			log.error("$$$$$$$$$ getChequePaymententPived");
			String qryStr = "select p from PivDetail p where p.id.deptId = :deptId and "
					+ "p.status = :status order by p.id.pivNo";
			//System.out.println("em obj getChequePaymententPived "+getEntityManager(appName));
			Query query = getEntityManager(appName).createQuery(qryStr);
			query.setParameter("deptId", deptId);
			query.setParameter("status", status);
			List<PivDetail> list = query.getResultList();
			return list;
			
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
		
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<PivPaymentGrid> getPivPaymentsToChequeNo1(String chequeNo, String appName) {
		log.error("$$$$$$$$$ getPivPaymentsToChequeNo1");
		try {
			
			String qryStr = "SELECT p FROM PivPayment p "+
					" where p.chequeNo = :chequeNo";
					
			//System.out.println("em obj getPivPaymentsToChequeNo1 "+getEntityManager(appName));
					
							
			
			/*select a.ACCOUNT_CODE , c.ac_nm, a.amount
			from piv_amount a, Glacctm c  where piv_no = 'PIV/260.20/PIV-CQR/0001'
			and a.ACCOUNT_CODE = c.ac_cd*/
			Query query = getEntityManager(appName).createQuery(qryStr);
			query.setParameter("chequeNo",chequeNo);
			
	     //	query.setParameter("pivNo", pivNo);
//		query.setParameter("paidAmount",paidAmount );
			
			List<PivPaymentGrid> list = query.getResultList();
			return list;
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
	}
	/////////////////////////////
	
	

//	@Override
//	public List<String> getPivNoListToStatus(String deptId, String status) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	
	/******** edit by pasindu ********/
	@SuppressWarnings("unchecked")
	@Override
	public PivDetail findReferenceNoOrBankCheckNoInPiv (String pivNo, String status, String appName){
		log.error("$$$$$$$$$ findReferenceNoOrBankCheckNoInPiv");
			String qryStr = "SELECT p FROM PivDetail p, PivActivity s WHERE p.referenceNo = :pivNo or p.bankCheckNo = :pivNo And s.activityCode = :status";
			Query query = getEntityManager(appName).createQuery(qryStr);
			//System.out.println("em obj findReferenceNoOrBankCheckNoInPiv "+getEntityManager(appName));
			query.setParameter("pivNo", pivNo);
			query.setParameter("status", status);
			List<PivDetail> list = query.getResultList();
			if (list.isEmpty())
				return null;
			else if (list.size() == 1)
				return list.get(0);
			else
				return null;
		
		

	}
	
	
	
	/********************************/

}
