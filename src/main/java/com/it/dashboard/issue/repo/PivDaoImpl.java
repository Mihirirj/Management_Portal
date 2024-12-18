package com.it.dashboard.issue.repo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.it.dashboard.admin.domain.PivDashboardGrid;
import com.it.dashboard.admin.domain.PivHistory;
import com.it.dashboard.admin.repo.PivHistoryDao;
import com.it.dashboard.admin.repo.SecurityDaoImpl;
import com.it.dashboard.issue.domain.PivAmount;
import com.it.dashboard.issue.domain.PivAmountGrid;
import com.it.dashboard.issue.domain.PivApplicant;
import com.it.dashboard.issue.domain.PivDetail;
import com.it.dashboard.master.domain.Applicant;
import com.it.dashboard.master.domain.Application;
import com.it.dashboard.master.domain.ApplicationReference;
import com.it.dashboard.master.domain.ApplicationReferencePK;
import com.it.dashboard.master.domain.WiringLandDetail;
import com.it.dashboard.master.repo.ApplicationDao;
import com.it.dashboard.master.repo.MasterDao;
import com.it.dashboard.payment.domain.PivPayment;
import com.it.dashboard.payment.repo.PivPaymentDao;
import com.it.dashboard.service.domain.PivSearchParamObject;
import com.it.dashboard.service.domain.PivSearchResultObject;
import com.it.dashboard.service.domain.PivServiceRequest;
import com.it.dashboard.service.domain.StampDutyObject;
import com.it.dashboard.util.common.CheckDigit;
import com.it.dashboard.util.common.EmSelector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Repository
@Transactional
public class PivDaoImpl extends EmSelector implements PivDao {
	//@PersistenceContext(unitName = "persistenceUnitPIV")
	//private EntityManager getEntityManager(appName);
	@Autowired
	private PivDetailDao pivDetailDao;
	@Autowired
	private PivApplicantDao pivApplicantDao;
	@Autowired
	private PivAmountDao pivAmountDao;
	@Autowired
	private MasterDao masterDao;
	@Autowired
	private PivHistoryDao pivHistoryDao;
	@Autowired
	private PivPaymentDao pivPaymentDao;
	@Autowired
	private ApplicationDao applicationDao;
	
	//@PersistenceContext(unitName="persistenceUnitPIV")
	//private EntityManager getEntityManager(appName);

	private static final Log log = LogFactory.getLog(PivDaoImpl.class);
	
	@Override
	public String[] generatePivThruService(String appIdPrefix, Applicant applicant, Application application, WiringLandDetail wiringLandDetail, PivDetail pivDetail, PivApplicant pivApplicant,
			ArrayList<PivAmount> pivAmountList, String appName) throws Exception{
		
		
		String[] strArr = new String[4];
		System.out.println("generatePivThruService----- 1"+appName);
		Applicant savedApp = applicationDao.findByIdNo(applicant.getIdNo(),appName);
		
		if(savedApp==null)
		{
			System.out.println("generatePivThruService----- 2");
			applicationDao.registerApplicant(applicant,appName);
		}
		
		System.out.println("generatePivThruService----- 3");
		application = applicationDao.createApplication(application, wiringLandDetail,  appIdPrefix, appName);
		strArr[2] = application.getApplicationNo();
		
		ApplicationReferencePK applicationReferencePK = new ApplicationReferencePK();
		applicationReferencePK.setApplicationId(application.getId().getApplicationId());
		applicationReferencePK.setDeptId(application.getId().getDeptId());
		ApplicationReference applicationReference = new ApplicationReference();
		applicationReference.setId(applicationReferencePK);
		applicationReference.setIdNo(application.getIdNo());
		applicationReference.setApplicationNo(application.getApplicationNo());
		System.out.println("generatePivThruService----- 4");
		applicationDao.createApplicationReference(applicationReference, appName);
		
		pivDetail.setReferenceNo(application.getApplicationNo());
		String pivNos = insertPivLocal(pivDetail, pivApplicant, pivAmountList, appName);
		
		
		//PivDetail pivDetailNew = pivDetailDao.findByPivNo(pivNo);
		StringTokenizer st = new StringTokenizer(pivNos,"###");
	 		String pivNo = st.nextToken();
	 		String bankRefNo = st.nextToken();
	 		
		strArr[0] = pivNo;
		strArr[1] = bankRefNo;
		strArr[2] = application.getApplicationNo();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		strArr[3] = dateFormat.format(pivDetail.getIssuedDate());
		
		System.out.println("generatePivThruService----- 5 end");
		return strArr;
	}
	
	@Override
	public String insertPiv(PivDetail pivDetail, PivApplicant pivApplicant,
			ArrayList<PivAmount> pivAmountList, String appName) {
		
		try {
			
			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);
			String pivType = pivDetail.getTitleCd();
			String pivTypeDigit=masterDao.getPivTypeDigit(pivType, appName);
			String yrStr = String.valueOf(year).substring(2);
			String pivNoPrefix = pivDetail.getId().getDeptId() + "/P"
					+ pivTypeDigit + "/"+yrStr+"/";
			String nextPivNo = pivDetailDao.getNextPivNo(pivNoPrefix, appName);
			
			CheckDigit checkDigit = new CheckDigit();
			int pivNoLen = nextPivNo.length();
			String bankCheckNo =checkDigit.getBankCheckNo(pivDetail.getId().getDeptId(), pivTypeDigit, nextPivNo.substring(pivNoLen-4, pivNoLen));
			pivDetail.setBankCheckNo(bankCheckNo);
			PivHistory pivHistory = new PivHistory();
			pivHistory.setDeptId(pivDetail.getId().getDeptId());
			pivHistory.setPivNo(nextPivNo);
			pivHistory.setStatus("N");
			pivHistory.setDescription("Generated");
			pivHistory.setUserId(pivDetail.getPreparedBy());
			Calendar calendar = Calendar.getInstance();
			Timestamp updateTime=new Timestamp(calendar.getTime().getTime());
			pivHistory.setUpdateTime(updateTime);
			pivDetailDao.register(pivDetail, nextPivNo,appName);
			pivApplicantDao.register(pivApplicant, nextPivNo,appName);
			pivAmountDao.registerList(pivAmountList, nextPivNo,appName);
			pivHistoryDao.register(pivHistory, appName);
			return nextPivNo+"###"+bankCheckNo;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
			
		}
		

	}
	
	
	private String insertPivLocal(PivDetail pivDetail, PivApplicant pivApplicant,
			ArrayList<PivAmount> pivAmountList, String appName) {
		
			log.error("$$$$$$$$$ insertPivLocal start");
			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);
			String pivType = pivDetail.getTitleCd();
			String pivTypeDigit=masterDao.getPivTypeDigit(pivType, appName);
			String yrStr = String.valueOf(year).substring(2);
			String pivNoPrefix = pivDetail.getId().getDeptId() + "/P"
					+ pivTypeDigit + "/"+yrStr+"/";
			String nextPivNo = pivDetailDao.getNextPivNo(pivNoPrefix, appName);
			CheckDigit checkDigit = new CheckDigit();
			int pivNoLen = nextPivNo.length();
			String bankCheckNo =checkDigit.getBankCheckNo(pivDetail.getId().getDeptId(), pivTypeDigit, nextPivNo.substring(pivNoLen-4, pivNoLen));
			pivDetail.setBankCheckNo(bankCheckNo);
			System.out.println("insertPiv@@@@@@@@@@@@@@@@@@@ 2");
			PivHistory pivHistory = new PivHistory();
			pivHistory.setDeptId(pivDetail.getId().getDeptId());
			pivHistory.setPivNo(nextPivNo);
			pivHistory.setStatus("N");
			pivHistory.setDescription("Generated");
			pivHistory.setUserId(pivDetail.getPreparedBy());
			Calendar calendar = Calendar.getInstance();
			Timestamp updateTime=new Timestamp(calendar.getTime().getTime());
			pivHistory.setUpdateTime(updateTime);
			System.out.println("insertPiv@@@@@@@@@@@@@@@@@@@ 3");
			pivDetailDao.register(pivDetail, nextPivNo, appName);
			System.out.println("insertPiv@@@@@@@@@@@@@@@@@@@ 4");
			pivApplicantDao.register(pivApplicant, nextPivNo, appName);
			System.out.println("insertPiv@@@@@@@@@@@@@@@@@@@ 5");
			pivAmountDao.registerList(pivAmountList, nextPivNo, appName);
			System.out.println("insertPiv@@@@@@@@@@@@@@@@@@@ 6");
			pivHistoryDao.register(pivHistory, appName);
			System.out.println("insertPiv@@@@@@@@@@@@@@@@@@@ 7");
			
			log.error("$$$$$$$$$ insertPivLocal end");
			return nextPivNo+"###"+bankCheckNo;
		

	}

	

	@Override
	public void updatePiv(PivDetail pivDetail, PivApplicant pivApplicant,
			ArrayList<PivAmount> pivAmountList, String appName) {
		log.error("$$$$$$$$$ updatePiv start");
		try {
			String pivNo = pivDetail.getId().getPivNo();
			String preStatus = pivDetail.getStatus();
			PivHistory pivHistory = new PivHistory();
			pivHistory.setDeptId(pivDetail.getId().getDeptId());
			pivHistory.setPivNo(pivNo);
			
			pivHistory.setUserId(pivDetail.getPreparedBy());
			if(preStatus.equals("V"))
			{
				pivHistory.setDescription("Sent for Approval.");
			}
			else if(preStatus.equals("R"))
			{
				pivDetail.setStatus("N");
				pivHistory.setDescription("Modified rejected PIV");
			}
			else
			{
				pivHistory.setDescription("Modified");
			}
			pivHistory.setStatus(pivDetail.getStatus());
			//pivHistory.setDe
			Calendar calendar = Calendar.getInstance();
			Timestamp updateTime=new Timestamp(calendar.getTime().getTime());
			pivHistory.setUpdateTime(updateTime);
			
			pivDetailDao.update(pivDetail, appName);
			pivApplicantDao.update(pivApplicant, appName);
			pivAmountDao.updateList(pivAmountList, appName);
			if(!preStatus.equals("N"))
				pivHistoryDao.register(pivHistory, appName);
			//return nextPivNo;
		} catch (Exception ex) {
			ex.printStackTrace();
			//return null;
			
		}
		log.error("$$$$$$$$$ updatePiv end");
	}
	
	@Override
	public void updatePivStatus(PivDetail pivDetail, PivHistory pivHistory, String appName) {
		log.error("$$$$$$$$$ updatePivStatus start");
		try {
			String pivNo = pivDetail.getId().getPivNo();
			//String nextHistoryCode = pivHistoryDao.getNextHistoryCode(pivNo);;
			//pivHistory.setHistoryCode(nextHistoryCode);
			Calendar calendar = Calendar.getInstance();
			Timestamp updateTime=new Timestamp(calendar.getTime().getTime());
			pivHistory.setUpdateTime(updateTime);
			
			pivDetailDao.update(pivDetail, appName);
			pivHistoryDao.register(pivHistory, appName);
			//return nextPivNo;
		} catch (Exception ex) {
			ex.printStackTrace();
			//return null;
			
		}
		log.error("$$$$$$$$$ updatePivStatus end");
	}
	
	@Override
	public void cancelPayment(PivDetail pivDetail, String appName) {
		log.error("$$$$$$$$$ cancelPayment start");
		try {
			String pivNo = pivDetail.getId().getPivNo();
			PivHistory pivHistory = new PivHistory();
			pivHistory.setDeptId(pivDetail.getId().getDeptId());
			pivHistory.setPivNo(pivDetail.getId().getPivNo());
			pivHistory.setStatus("A");
			pivHistory.setUserId(pivDetail.getUpdUser());
			Calendar calendar = Calendar.getInstance();
			Timestamp updateTime=new Timestamp(calendar.getTime().getTime());
			pivHistory.setUpdateTime(updateTime);
			pivHistory.setDescription("Cancel Payment");
			
			
			pivDetailDao.update(pivDetail, appName);
			pivHistoryDao.register(pivHistory, appName);
			pivPaymentDao.deletePaymentToPivNo(pivNo, appName);
			//return nextPivNo;
		} catch (Exception ex) {
			ex.printStackTrace();
			//return null;
			
		}
		log.error("$$$$$$$$$ cancelPayment end");
	}
	
	public void savePivHistory(String pivNo,String deptId,String status, String appName){
		log.error("$$$$$$$$$ savePivHistory start");
		try{
			pivHistoryDao.saveHistory(pivNo,deptId,status, appName);
		}catch(Exception ex){
			ex.printStackTrace();
			
		}
		log.error("$$$$$$$$$ savePivHistory end");
	}
	
	@Override
	public void updatePivPayment(PivDetail pivDetail,PivPayment pivPayment, String appName){
		log.error("$$$$$$$$$ updatePivPayment start");
		try{
			pivPaymentDao.register(pivPayment, appName);
			pivDetailDao.update(pivDetail, appName);
		}catch(Exception ex){
			ex.printStackTrace();
			
		}
		log.error("$$$$$$$$$ updatePivPayment end");
	}
	
	@Override
	public void removePivPayment(PivDetail pivDetail,PivPayment pivPayment, String appName){
		log.error("$$$$$$$$$ removePivPayment start");
		try{
			pivPaymentDao.remove(pivPayment, appName);
			pivDetailDao.update(pivDetail, appName);
		}catch(Exception ex){
			ex.printStackTrace();
			
		}
		log.error("$$$$$$$$$ removePivPayment end");
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PivAmountGrid> getAcctCodesByPivNo(String pivNo, String appName) {
		log.error("$$$$$$$$$ getAcctCodesByPivNo start");
		List<PivAmountGrid> list = null;
		try {
			String qryStr = " SELECT new com.it.dashboard.issue.domain.PivAmountGrid(a.id.accountCode, trim(c.acNm),  trim(to_char( a.amount, '999,999,999,999,999,999.99' ))   " +
							" , a.taxPercent, a.sortKey)  " +		
							" FROM PivAmount a , Glacctm c " +
							" WHERE a.id.pivNo = :pivNo " +
							" AND a.id.accountCode = c.id.acCd " +
							" ORDER BY a.sortKey";
			
			/*select a.ACCOUNT_CODE , c.ac_nm, a.amount
			from piv_amount a, Glacctm c  where piv_no = 'PIV/260.20/PIV-CQR/0001'
			and a.ACCOUNT_CODE = c.ac_cd*/
			Query query = getEntityManager(appName).createQuery(qryStr);
			//System.out.println("em obj PivDaoImpl getAcctCodesByPivNo "+getEntityManager(appName));
			query.setParameter("pivNo", pivNo);
			list = query.getResultList();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			
		}
		log.error("$$$$$$$$$ getAcctCodesByPivNo end");
		return list;
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PivAmountGrid> getAcctCodesByPivNoWithoutZero(String pivNo, String appName) {
		log.error("$$$$$$$$$ getAcctCodesByPivNoWithoutZero start");
		List<PivAmountGrid> list = null;
		try {
			String qryStr = " SELECT new com.it.dashboard.issue.domain.PivAmountGrid(a.id.accountCode, trim(c.acNm),  trim(to_char( a.amount, '999,999,999,999,999,999.99' ))"+
							" ,a.taxPercent, a.sortKey)  " +
							" FROM PivAmount a , Glacctm c " +
							" WHERE a.id.pivNo = :pivNo " +
							" AND a.id.accountCode = c.id.acCd " +
							" AND a.amount>0 "+
							" ORDER BY a.sortKey";
			
			/*select a.ACCOUNT_CODE , c.ac_nm, a.amount
			from piv_amount a, Glacctm c  where piv_no = 'PIV/260.20/PIV-CQR/0001'
			and a.ACCOUNT_CODE = c.ac_cd*/
			Query query = getEntityManager(appName).createQuery(qryStr);
			//System.out.println("em obj PivDaoImpl getAcctCodesByPivNoWithoutZero "+getEntityManager(appName));
			query.setParameter("pivNo", pivNo);
			list = query.getResultList();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			
		}
		log.error("$$$$$$$$$ getAcctCodesByPivNoWithoutZero end");
		return list;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	//This method finds details of new pivs (pr) 
	@SuppressWarnings({ "unused", "unchecked" })
	//This method finds details of new pivs according to the date (pr)!enter the date and remove state
	@Override
	public List<PivApplicant> findNewPivDetails(String deptId, String appName){
		log.error("$$$$$$$$$ findNewPivDetails start");
		List<PivApplicant> pivList = null;
		try{
			Date pivDate;
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			String d=dateFormat.format(cal.getTime());
			pivDate = dateFormat.parse(d);
			
			String qryStr = "select a from PivDetail p, PivApplicant a "+
							"where p.id.deptId = :deptId "+
							"and p.sysType = 'PIV' "+
							"and p.pivDate = :pivDate "+
							"and  p.id.deptId = a.id.deptId "+
							"and p.id.pivNo = a.id.pivNo "+
							"order by p.pivDate desc";
			Query query = getEntityManager(appName).createQuery(qryStr);
			//System.out.println("em obj PivDaoImpl findNewPivDetails "+getEntityManager(appName));
			query.setParameter("deptId", deptId);
			query.setParameter("pivDate", pivDate);
			
			pivList = query.getResultList();
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
			
		}
		log.error("$$$$$$$$$ findNewPivDetails end");
		return pivList;
	}
			
	@Override
	public List<PivDashboardGrid> getTodayPivList(String deptId, String appName){
		log.error("$$$$$$$$$ getTodayPivList start");
		List<PivDashboardGrid> pivList = null;
		try{
			Date pivDate;
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			String d=dateFormat.format(cal.getTime());
			pivDate = dateFormat.parse(d);
			
			String qryStr = "select new com.it.dashboard.admin.domain.PivDashboardGrid "+
							"(a.id.pivNo,a.name,a.idNo,p.status,p.pivDate,p.pivAmount ) "+
							"from PivDetail p, PivApplicant a "+
							"where p.id.deptId = :deptId "+
							"and p.sysType = 'PIV' "+
							"and p.pivDate = :pivDate "+
							"and  p.id.deptId = a.id.deptId "+
							"and p.id.pivNo = a.id.pivNo "+
							"order by p.enteredTime desc";
			Query query = getEntityManager(appName).createQuery(qryStr);
			//System.out.println("em obj PivDaoImpl getTodayPivList "+getEntityManager(appName));
			query.setParameter("deptId", deptId);
			query.setParameter("pivDate", pivDate);
			
			pivList = query.getResultList();
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
			
		}
		log.error("$$$$$$$$$ getTodayPivList end");
		return pivList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public int findNewPivCount(String deptId, String appName){
		log.error("$$$$$$$$$ findNewPivCount start");
		int count = 0;
		//EntityManager em = getEntityManager(appName);
		try {
			int i=0;
			Date pivDate;
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			String d=dateFormat.format(cal.getTime());
			pivDate = dateFormat.parse(d);
			
			String qryStr = "select a.id.pivNo from PivDetail p, PivApplicant a "+
							"where p.id.deptId = :deptId "+
							"and p.sysType = 'PIV' "+
							"and p.pivDate = :pivDate "+
							"and  p.id.deptId = a.id.deptId "+
							"and p.id.pivNo = a.id.pivNo";
			
			Query query = getEntityManager(appName).createQuery(qryStr);
			//System.out.println("em obj PivDaoImpl findNewPivCount "+getEntityManager(appName));
			query.setParameter("deptId", deptId);
			query.setParameter("pivDate", pivDate);
			
			List<String> list = query.getResultList();
			
			if (list.isEmpty()){
				count = 0;
			}
			else{
				i=list.size();
				count = i;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			
		}
		log.error("$$$$$$$$$ findNewPivCount end");
		return count;
	}
	
	@Override
	public List<PivDashboardGrid> findPaidPivDetails(String deptId, String appName){
		log.error("$$$$$$$$$ findPaidPivDetails start");	
		List<PivDashboardGrid> list = null;
		try 
		{
			Date pivDate;
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			String d=dateFormat.format(cal.getTime());
			pivDate = dateFormat.parse(d);
			
			String qryStr = "select new com.it.dashboard.admin.domain.PivDashboardGrid (a.id.pivNo,a.name,a.idNo,p.status,p.pivDate,p.pivAmount,p.paidTime ) "+
							"from PivDetail p, PivApplicant a "+
							"where p.id.deptId = :deptId "+
							//"and p.status = :status "+
							"and p.sysType = 'PIV' "+
							"and p.paidDate = :pivDate "+
							"and  p.id.deptId = a.id.deptId "+
							"and p.id.pivNo = a.id.pivNo "+
							"order by p.paidTime desc ";
			Query query = getEntityManager(appName).createQuery(qryStr);
			//System.out.println("em obj PivDaoImpl findPaidPivDetails "+getEntityManager(appName));
			query.setParameter("deptId", deptId);
			query.setParameter("pivDate", pivDate);
			list = query.getResultList();
			
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
			
		}
		log.error("$$$$$$$$$ findPaidPivDetails end");	
		return list;
	}
	
	
			
	@SuppressWarnings("unchecked")
	@Override
	public int findPaidPivCount(String deptId, String appName){
		log.error("$$$$$$$$$ findPaidPivCount start");
		int count = 0;
		//EntityManager em = getEntityManager(appName);
		try {
			int i=0;
			Date pivDate;
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			
			String d=dateFormat.format(cal.getTime());
			pivDate = dateFormat.parse(d);
			
			String qryStr = "select a from PivDetail p, PivApplicant a "+
							"where p.id.deptId = :deptId "+
							//"and p.status = :status "+
							"and p.sysType = 'PIV' "+
							"and p.paidDate = :pivDate "+
							"and  p.id.deptId = a.id.deptId "+
							"and p.id.pivNo = a.id.pivNo";
			
			Query query =getEntityManager(appName).createQuery(qryStr);
		//	System.out.println("em obj PivDaoImpl findPaidPivCount "+getEntityManager(appName));
			query.setParameter("deptId", deptId);
			//query.setParameter("status", status);
			query.setParameter("pivDate", pivDate);
			
			List<String> list = query.getResultList();
			
			if (list.isEmpty()){
				count = 0;
			}
			else{
				i=list.size();
				count = i;
			
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			
		}
		log.error("$$$$$$$$$ findPaidPivCount end");	
		return count = 0;
	}
			
	//method implementation to find the today total revenue 
	@SuppressWarnings("unchecked")
	@Override
	public BigDecimal findTotalRevenue(String deptId, String status, String appName){
		log.error("$$$$$$$$$ findTotalRevenue start");
		//EntityManager em = getEntityManager(appName);
		BigDecimal total =new BigDecimal(0);
		try{
			int i=0;
			BigDecimal amount=new BigDecimal(0) ;
			
			
			Date pivDate;
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			
			String d=dateFormat.format(cal.getTime());
			pivDate = dateFormat.parse(d);
			String qryStr = "select a from PivDetail p, PivApplicant a "+
							"where p.id.deptId = :deptId and "+
							"p.sysType = 'PIV' and "+
							"p.paidDate = :pivDate and "+
							//"p.status = :status and  "+
							"p.id.deptId = a.id.deptId and "+
							"p.id.pivNo = a.id.pivNo";
			
			Query query = getEntityManager(appName).createQuery(qryStr);
		//	System.out.println("em obj PivDaoImpl findTotalRevenue "+getEntityManager(appName));
			query.setParameter("deptId", deptId);
			//query.setParameter("status", status);
			query.setParameter("pivDate", pivDate);
			
			
			//execute query and do something with result        
			List<PivApplicant> paidPivList = query.getResultList();
			
			if(paidPivList.isEmpty()){
				total = new BigDecimal(0);
			}
			else{
				int j=0;
				i=paidPivList.size();
				for(j=0;j<i;j=j+1){
					amount=amount.add(paidPivList.get(j).getPivDetail().getGrandTotal());
			
				}
			
				total = amount;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			
		}
		log.error("$$$$$$$$$ findTotalRevenue end");
		return null;
	}
			
			
	@SuppressWarnings("unchecked")
	@Override
	public List<PivApplicant> getAuthorisedPivDetailList(String userRole,String deptId, String status, String appName) {
		log.error("$$$$$$$$$ getAuthorisedPivDetailList 1 start");
		List<PivApplicant> list = null;
		try 
		{
			
			String qryStr = "select p from PivApplicant p, PivApproval a, PivDetail d "
					+ "where d.id.deptId = :deptId and d.sysType = 'PIV' and p.pivDetail.id.deptId = :deptId and p.pivDetail.status = :status "
					+ "and p.pivDetail.id.deptId = a.id.deptId and p.pivDetail.status = a.id.activity and d.id.pivNo = p.id.pivNo and " 
					+ " a.id.userRole = :userRole order by d.enteredTime desc ";
			Query query = getEntityManager(appName).createQuery(qryStr);
			//System.out.println("em obj PivDaoImpl getAuthorisedPivDetailList "+getEntityManager(appName));
			query.setParameter("deptId", deptId);
			query.setParameter("userRole", userRole);
			query.setParameter("status", status);
			list = query.getResultList();
			//getEntityManager(appName).flush();
			
			
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
			
		}
		log.error("$$$$$$$$$ getAuthorisedPivDetailList 1 end");
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PivDashboardGrid> getAuthorisedPivList(String userRole,String deptId, String status, String appName) {
		log.error("$$$$$$$$$ getAuthorisedPivList 2 start");
		List<PivDashboardGrid> list = null;
		try 
		{
			
			String qryStr = "select new com.it.dashboard.admin.domain.PivDashboardGrid (p.id.pivNo,p.name,p.idNo,d.status,d.pivDate,d.pivAmount ) "+
							"from PivApplicant p, PivApproval a, PivDetail d "
							+ "where d.id.deptId = :deptId and d.sysType = 'PIV' "
							+ "and p.pivDetail.id.deptId = :deptId and "
							+ "p.pivDetail.status = :status "
							+ "and p.pivDetail.id.deptId = a.id.deptId "
							+ "and p.pivDetail.status = a.id.activity "
							+ "and d.id.pivNo = p.id.pivNo and " 
							+ " a.id.userRole = :userRole "
							+ "order by d.enteredTime desc ";
			
			
			Query query = getEntityManager(appName).createQuery(qryStr);
			//System.out.println("em obj PivDaoImpl getAuthorisedPivList "+getEntityManager(appName));
			query.setParameter("deptId", deptId);
			query.setParameter("userRole", userRole);
			query.setParameter("status", status);
			 list = query.getResultList();
			
			
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
			
		}
		log.error("$$$$$$$$$ getAuthorisedPivList 2 end");
		return list;
	}
			
	@SuppressWarnings("unchecked")
	@Override
	public int getAuthorisedPivDetailCount(String userRole,String deptId, String status, String appName) {
		log.error("$$$$$$$$$ getAuthorisedPivDetailCount start");
		int count = 0;
		//EntityManager em = getEntityManager(appName);
		int i=0;
		try {
			String qryStr = "select p from PivApplicant p, PivApproval a, PivDetail d "
					+ "where d.id.deptId = :deptId and d.sysType = 'PIV' and p.pivDetail.id.deptId = :deptId and p.pivDetail.status = :status "
					+ "and p.pivDetail.id.deptId = a.id.deptId and p.pivDetail.status = a.id.activity and d.id.pivNo = p.id.pivNo and " 
					+ " a.id.userRole = :userRole ";
					
			Query query = getEntityManager(appName).createQuery(qryStr);
		//	System.out.println("em obj PivDaoImpl getAuthorisedPivDetailCount "+getEntityManager(appName));
			query.setParameter("deptId", deptId);
			query.setParameter("userRole", userRole);
			query.setParameter("status", status);
			List<PivApplicant> list = query.getResultList();
			
			if (list.isEmpty()){
				count = 0;
			}
			else{
				i=list.size();
				count = i;
			
			}
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
			
		}
		log.error("$$$$$$$$$ getAuthorisedPivDetailCount end");
		return count;
	}
			
			/////////////////////////////////////////////////////////////////////////////////
/******** edit by pasindu ********/
	
	@Override
	public String insertPiv(PivDetail pivDetail, PivApplicant pivApplicant,
			ArrayList<PivAmount> pivAmountList, PivHistory pivHistory, String returnChequeNo, String appName) {
		log.error("$$$$$$$$$ insertPiv 2 start");
		String pdi = insertPiv(pivDetail, pivApplicant, pivAmountList, appName);
		String refPivNo = pivDetail.getOtherPivRef();
		String deptId = pivDetail.getId().getDeptId();
		String status = pivHistory.getStatus();
		
		PivPayment pivPayment = new PivPayment();
		pivPayment.setStatus("HN");
		String status1 = pivPayment.getStatus();
		//pivPaymentDao.chequeStatusUpdate(refPivNo, returnChequeNo, status1);
				
		pivDetailDao.updateStatus(refPivNo, deptId, status, appName);
		pivHistoryDao.register(pivHistory, appName);
		
		log.error("$$$$$$$$$ insertPiv 2 end");
		return pdi;
	}
	/***********************************/

	
	@SuppressWarnings("unchecked")
	@Override
	public List<PivSearchResultObject> serachPiv(PivSearchParamObject paramObj, String appName) {
		log.error("$$$$$$$$$ serachPiv start");
		List<PivSearchResultObject> objList = new ArrayList<PivSearchResultObject>();
		String deptIdParam = paramObj.getCostCenter();
		String nameParam = paramObj.getCustomerName();
		String idNoParam = paramObj.getIdNo();
		BigDecimal pivAmountParam = paramObj.getPivAmount();
		Date pivDateParam = paramObj.getPivDate();
		String referenceNoParam = paramObj.getReferenceNo();
		String condn = "";
		if(deptIdParam!=null && deptIdParam.trim().length()>0)
			condn = condn+" and a.id.deptId = :deptId ";
		if(nameParam!=null && nameParam.trim().length()>0)
			condn = condn+" and a.name like :name ";
		if(pivAmountParam!=null )
			condn = condn+" and a.pivDetail.pivAmount = :pivAmount ";
		if(pivDateParam!=null )
			condn = condn+" and a.pivDetail.pivDate = :pivDate ";
		if(referenceNoParam!=null && referenceNoParam.trim().length()>0)
			condn = condn+" and a.pivDetail.referenceNo = :referenceNo ";
		if(idNoParam!=null && idNoParam.trim().length()>0)
			condn = condn+" and a.idNo = :idNo ";
		try {
			String qryStr = " SELECT new com.it.dashboard.service.domain.PivSearchResultObject (a.id.deptId, a.pivDetail.bankCheckNo, a.idNo, a.name, a.address,  "+
							" a.pivDetail.referenceNo, trim(to_char( a.pivDetail.pivAmount, '999,999,999,999,999,999.99' )) , a.pivDetail.pivDate) " +
							" FROM PivApplicant a " +
							" WHERE (a.pivDetail.status = 'N' or a.pivDetail.status = 'A') " +
							 condn+
							" ORDER BY a.pivDetail.issuedDate";
			Query query = getEntityManager(appName).createQuery(qryStr);
			//System.out.println("em obj PivDaoImpl serachPiv "+getEntityManager(appName));
			if(deptIdParam!=null && deptIdParam.trim().length()>0)
				query.setParameter("deptId", deptIdParam);
			if(nameParam!=null && nameParam.trim().length()>0)
				query.setParameter("name", nameParam+"%");
			if(pivAmountParam!=null )
				query.setParameter("pivAmount", pivAmountParam);
			if(pivDateParam!=null )
				query.setParameter("pivDate", pivDateParam);
			if(referenceNoParam!=null && referenceNoParam.trim().length()>0)
				query.setParameter("referenceNo", referenceNoParam);
			if(idNoParam!=null && idNoParam.trim().length()>0)
				query.setParameter("idNo", idNoParam);
			//query.setParameter("pivNo", pivNo);
			objList = query.getResultList();
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
			
		}
		log.error("$$$$$$$$$ serachPiv end");
		return objList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<StampDutyObject> getStampDutyList(String compId, Date fromDate, Date toDate, String appName) {
		log.error("$$$$$$$$$ getStampDutyList start");
		List<StampDutyObject> list = null;
		try {
			String qryStr = " SELECT new com.it.dashboard.service.domain.StampDutyObject(p.paidDate, to_char(count(p.id.pivNo)),  " +
							" to_char(sum(p.pivAmount),'999,999,999,999,999,999.99'), to_char(count(p.id.pivNo)*25 ))"+
							" FROM PivDetail p  " +
							" WHERE p.paidDate >= :fromDate " +
							" AND p.paidDate <= :toDate " +
							" AND p.pivAmount > 25000 "+
							" AND (status = 'P' or status = 'FR'or status = 'FA'or status = 'F')"+
							" AND p.id.deptId in ( " +
							" select d.deptId  from Gldeptm d "+
							" where d.compId IN	"+
							" ( select g.compId  from Glcompm g "+
							" where  g.parentId =:compId  OR g.compId=:compId)) "+
							" group by p.paidDate "+
							" ORDER BY p.paidDate ";
			Query query = getEntityManager(appName).createQuery(qryStr);
			//System.out.println("em obj PivDaoImpl getStampDutyList "+getEntityManager(appName));
			query.setParameter("compId", compId);
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
			list = query.getResultList();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			
		}
		log.error("$$$$$$$$$ getStampDutyList end");
		return list;
	}
	
	
	
	@Override
	public void addPivServiceReq(PivServiceRequest pivSerReq, String appName) {
		log.error("$$$$$$$$$ addPivServiceReq start");
		getEntityManager(appName).persist(pivSerReq);
		//System.out.println("em obj PivDaoImpl addPivServiceReq "+getEntityManager(appName));
		log.error("$$$$$$$$$ addPivServiceReq end");
	}
	
	
}
