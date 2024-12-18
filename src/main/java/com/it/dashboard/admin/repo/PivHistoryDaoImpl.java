package com.it.dashboard.admin.repo;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.it.dashboard.admin.domain.PivHistory;
import com.it.dashboard.util.common.EmSelector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Repository
@Transactional
public class PivHistoryDaoImpl extends EmSelector implements PivHistoryDao {
	//@PersistenceContext(unitName = "persistenceUnitPIV")
  //  private EntityManager getEntityManager(appName);
	private static final Log log = LogFactory.getLog(PivHistoryDaoImpl.class);

	/**
	 * register with auto No
	*/
	@Override
	public void register(PivHistory pivHistory, String appName) {
		log.error("$$$$$$$$$ register history");
		getEntityManager(appName).persist(pivHistory);
		log.error("$$$$$$$$$ end register history");
		
	}
	//@SuppressWarnings("unchecked")
	@SuppressWarnings("unchecked")
	@Override
	public List<PivHistory> findHistory(String pivNo, String deptId, String appName){
		log.error("$$$$$$$$$ findHistory");
		List<PivHistory> list = null;
		try {
        	String qryStr = "select p from PivHistory p where p.id.deptId = :deptId and p.id.pivNo=:pivNo order by updateTime ";
			Query query = getEntityManager(appName).createQuery(qryStr);
			query.setParameter("deptId", deptId);
			query.setParameter("pivNo", pivNo);
			
			list = query.getResultList();
			
			
			
			
        } catch (Exception ex) {
			ex.printStackTrace();
			
		}
		
		log.error("$$$$$$$$$ end findHistory");
		return list;
	}
	@Override
	public void saveHistory(String deptId,String pivNo,String status, String appName){
		log.error("$$$$$$$$$ saveHistory");
		try{
			Calendar calendar = Calendar.getInstance();
			Timestamp updateTime=new Timestamp(calendar.getTime().getTime());
			PivHistory pivHistory=new PivHistory();
			//String historyCode="11223";
			//pivHistory.setHistoryCode(historyCode);
			pivHistory.setDeptId(deptId);
			pivHistory.setPivNo(pivNo);
			pivHistory.setStatus(status);
			pivHistory.setUpdateTime(updateTime);
			//pivHistory.setUserId(userId);
			getEntityManager(appName).persist(pivHistory);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		log.error("$$$$$$$$$ end saveHistory");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String getNextHistoryCode(String pivNo, String appName) {
		log.error("$$$$$$$$$ getNextHistoryCode");
		String historyCode = null;
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		String yrStr = String.valueOf(year).substring(2);
		String historyCodePrefix = pivNo.substring(4)+"/";
		String sequence=null;
    	String like=historyCodePrefix+"%";
    	String strQuery="select p.id.historyCode from PivHistory p where p.id.historyCode like :like ORDER BY 1 DESC";
    	Query query=getEntityManager(appName).createQuery(strQuery);//SELECT MIS.TEST4_SEQ.NEXTVAL() FROM DUAL
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
    		historyCode = historyCodePrefix+"000"+sequence;
    	else if (sequence.length()==2)
    		historyCode = historyCodePrefix+"00"+sequence;
    	else if (sequence.length()==3)
    		historyCode = historyCodePrefix+"0"+sequence;
    	else historyCode = historyCodePrefix+sequence;
    	log.error("$$$$$$$$$ end getNextHistoryCode");
    	return historyCode;
    	
	}
}
	