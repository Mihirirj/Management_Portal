package com.it.dashboard.payment.repo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.it.dashboard.admin.domain.PivHistory;
import com.it.dashboard.admin.repo.PivHistoryDao;
import com.it.dashboard.admin.repo.SecurityDaoImpl;
import com.it.dashboard.issue.domain.PivAmount;
import com.it.dashboard.issue.domain.PivAmountGrid;
import com.it.dashboard.issue.domain.PivApplicant;
import com.it.dashboard.issue.domain.PivDetail;
import com.it.dashboard.issue.repo.PivDetailDao;
import com.it.dashboard.master.domain.Application;
import com.it.dashboard.master.domain.Bank;
import com.it.dashboard.master.domain.BankBranch;
import com.it.dashboard.master.domain.BankBranchPK;
import com.it.dashboard.master.domain.Pcesthtt;
import com.it.dashboard.master.domain.PivActivity;
import com.it.dashboard.master.repo.ApplicationDao;
import com.it.dashboard.master.repo.PcesthttDao;
import com.it.dashboard.payment.domain.PaymentMethodObject;
import com.it.dashboard.payment.domain.PivBankTran;
import com.it.dashboard.payment.domain.PivPayment;
import com.it.dashboard.payment.domain.PivPaymentGrid;
import com.it.dashboard.payment.domain.PivPaymentPK;
import com.it.dashboard.util.common.EmSelector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Repository
@Transactional
public class PaymentDetailImpl extends EmSelector implements PaymentDetail {
	//@PersistenceContext(unitName = "persistenceUnitPIV")
	//private EntityManager getEntityManager(appName);
	
	@Autowired
	private PivDetailDao pivDetailDao;
	@Autowired
	private PivHistoryDao pivHistoryDao;
	@Autowired
	private ApplicationDao applicationDao;
	@Autowired
	private PcesthttDao pcesthttDao;
	@Autowired
	private PivPaymentDao pivPaymentDao;

	
	private static final Log log = LogFactory.getLog(PaymentDetailImpl.class);
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PivApplicant> getChequePayemtPiv(String deptId, String appName) {
		log.error("$$$$$$$$$ getChequePayemtPiv");
		try 
		{
			
			String qryStr = "select p from PivApplicant p, PivApproval a, PivDetail d "
					+ "where d.id.deptId = :deptId and d.sysType = 'PIV' and p.pivDetail.id.deptId = :deptId and p.pivDetail.status = :status "
					+ "and p.pivDetail.id.deptId = a.id.deptId and p.pivDetail.status = a.id.activity and d.id.pivNo = p.id.pivNo and " 
					+ " a.id.userRole = :userRole ";
			Query query = getEntityManager(appName).createQuery(qryStr);
			query.setParameter("deptId", deptId);
			List<PivApplicant> list = query.getResultList();
			return list;
			
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
			return null;
		}
	}
	
	
	
	




	@SuppressWarnings("unchecked")
	@Override
	public List<PivPaymentGrid> getPivPaymentsToChequeNo(String chequeNo, String appName) {
		log.error("$$$$$$$$$ getPivPaymentsToChequeNo");
		try {
			String qryStr = " SELECT new com.it.dashboard.payment.domain.PivPaymentGrid(pp.id.deptId, pp.id.pivNo, pd.pivAmount, "+
							" pp.paidAmount, b.bankName, bb.branchName, "+
							" pp.chequeDate, pp.chequeNo )  " +
							" FROM PivPayment pp, PivDetail pd, Bank b, BankBranch bb " +
							" WHERE pp.chequeNo =  :chequeNo " +
							" and pp.status = 'Q' "+
							" and pp.id.pivNo = pd.id.pivNo "+
							" and pp.chequeBankBranch = bb.id.branchCode "+
							" and pp.chequeBankCode = b.bankCode "+
							" and  pp.chequeBankCode = bb.id.bankCode ";
							
			
			Query query = getEntityManager(appName).createQuery(qryStr);
			query.setParameter("chequeNo", chequeNo);
			List<PivPaymentGrid> list = query.getResultList();
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	
	
	
	
	
	@Override
	public void chequeStatusUpdate(String pivNo, String deptId, String chequeNo, String status, String loggedUser, String appName) {
		log.error("$$$$$$$$$ chequeStatusUpdate");
		try 
		{
			PivDetail pivDetail = pivDetailDao.findByPivNo(pivNo, appName);
			//PivActivity pivActivity = new PivActivity();
			//pivActivity.setActivityCode(status);
			pivDetail.setStatus(status);
			
			//insert piv history
			PivHistory pivHistory = new PivHistory();
			pivHistory.setDeptId(deptId);
			pivHistory.setPivNo(pivNo);
			pivHistory.setStatus(status);
			
			
				
			pivHistory.setUserId(loggedUser);
			Calendar calendar = Calendar.getInstance();
			Timestamp updateTime=new Timestamp(calendar.getTime().getTime());
			pivHistory.setUpdateTime(updateTime);
			
			if(status.equals("H"))
			{
				pivHistory.setDescription("Cheque Returned.");
				pivDetailDao.update(pivDetail, appName);
				pivHistoryDao.register(pivHistory, appName);
			}
			else if(status.equals("P") )
			{
				pivHistory.setDescription("Cheque Realised.");
				pivDetailDao.update(pivDetail, appName);
				pivHistoryDao.register(pivHistory, appName);
			}
			
			
			
			
			PivPayment payment = pivPaymentDao.findByPivCheque(pivNo,chequeNo, appName);
			payment.setStatus(status);
			pivPaymentDao.update(payment, appName);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			//return null;
			
		}
	}
	
	@Override
	public void updateChequePivStatus(String chequeNo, String status, String loggedUser,List<String> deptList, String appName) {
		log.error("$$$$$$$$$ updateChequePivStatus");
		try 
		{
			
			List<PivPayment> pivList = pivPaymentDao.getPivToStatusAndPayMode(deptList, "Q", "Q", chequeNo, appName);
			log.error("$$$$$$$$$ updateChequePivStatus"+pivList.size());
			for(int i=0;i<pivList.size();i++)
			{
				PivPayment pivPayment = pivList.get(i);
				
				PivDetail pivDetail = pivPayment.getPivDetail();
				
				//PivActivity pivActivity = new PivActivity();
				//pivActivity.setActivityCode(status);
				pivDetail.setStatus(status);
				
				//insert piv history
				PivHistory pivHistory = new PivHistory();
				pivHistory.setDeptId(pivDetail.getId().getDeptId());
				pivHistory.setPivNo(pivDetail.getId().getPivNo());
				pivHistory.setStatus(status);
				pivHistory.setUserId(loggedUser);
				Calendar calendar = Calendar.getInstance();
				Timestamp updateTime=new Timestamp(calendar.getTime().getTime());
				pivHistory.setUpdateTime(updateTime);
				
				if(status.equals("H"))
				{
					pivHistory.setDescription("Cheque Returned.");
					
				}
				else if(status.equals("P") )
				{
					try
					{
						pivHistory.setDescription("Cheque Realised.");
						updateBulkPIVStatus(pivDetail.getReferenceNo(), appName);
						updateBulkAppStatus(pivDetail.getReferenceNo(), appName);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
				pivDetailDao.update(pivDetail, appName);
				pivHistoryDao.register(pivHistory, appName);
				
				
				
				pivPayment.setStatus(status);
				pivPaymentDao.update(pivPayment, appName);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			//return null;
			
		}
	}
	
	
	



	
	
	
	
	
	
	
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public PivBankTran findBankTranById(String transId, String pivCheckNo, String activity, String appName) {
		log.error("$$$$$$$$$ findBankTranById");
		try {
			String qryStr = "SELECT p FROM PivBankTran p "+
							" where p.id.transId = :transId "+
							" and p.id.pivCheckNo = :pivCheckNo "+
							" and p.id.activity = :activity ";
			Query query = getEntityManager(appName).createQuery(qryStr);
			query.setParameter("transId", transId);
			query.setParameter("pivCheckNo", pivCheckNo);
			query.setParameter("activity", activity);
			
			PivBankTran pivBankTrans = (PivBankTran)query.getSingleResult();
			return pivBankTrans;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	
	@Override
	public void confirmPayment( PivDetail pivDetail, List<PaymentMethodObject> pivPaymentList, String appName)
	{
		log.error("$$$$$$$$$ confirmPayment");
		try
		{
			String pivNo = pivDetail.getId().getPivNo();
			String deptId = pivDetail.getId().getDeptId();
			
			//PivBankTran pivBankTran = findBankTranById(transId, pivDetail.getBankCheckNo(),"PAYMENT");
			//pivBankTran.setStatus1("S");
			
			
			String payMode = "C";
			//insert piv payment
			for(int i=0;i<pivPaymentList.size();i++)
			{
				PivPaymentPK pivPaymentPK = new PivPaymentPK();
				pivPaymentPK.setDeptId(deptId);
				pivPaymentPK.setPivNo(pivNo);
				pivPaymentPK.setSeqNo(pivPaymentDao.getNextPayemntSeqNo(pivNo, deptId, appName));
				
				PaymentMethodObject obj = pivPaymentList.get(i);
				
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date checqueDate = null;
				if(obj.getChequeDate()!=null && obj.getChequeDate().trim().length()>0)
				{
					try
	 				{
						checqueDate = dateFormat.parse(obj.getChequeDate());
	 				}
					catch(ParseException e)
	 				{
	 					try
	 					{
		 					dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		 					checqueDate = dateFormat.parse(obj.getChequeDate());
	 					}
	 					catch(ParseException e1)
	 					{}
	 				}
				}
				
				PivPayment pivPayment = new PivPayment();
				pivPayment.setId(pivPaymentPK);
				pivPayment.setPaidAmount(new BigDecimal(obj.getPaidAmount()));
				
				pivPayment.setAddUser("POS_USER");
				//pivPayment.setBank(bank);
				
				//pivPayment.setChequeBankBranch(obj.getChequeBankBranch());
				//pivPayment.setChequeBankCode(obj.getChequeBank());
				pivPayment.setChequeDate(checqueDate);
				pivPayment.setChequeNo(obj.getChequeNo());
				pivPayment.setPaymentMode(obj.getPaymentMethod());
				//pivPayment.setStatus("A");
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
				String addTime = sdf1.format(calendar.getTime()) ;
				pivPayment.setAddTime(addTime);
				pivPayment.setAddDate(calendar.getTime());
				
				
				if(obj.getChequeBank()!=null && obj.getChequeBank().trim().length()>0
						&& obj.getChequeBankBranch()!=null && obj.getChequeBankBranch().trim().length()>0)
				{
					Bank bank = new Bank();
					bank.setBankCode(obj.getChequeBank());
					BankBranch bankBranch = new BankBranch();
					BankBranchPK bankBranchPK = new BankBranchPK();
					bankBranchPK.setBankCode(obj.getChequeBank());
					bankBranchPK.setBranchCode(obj.getChequeBankBranch());
					bankBranch.setId(bankBranchPK);
					//pivPayment.setBankBranch(bankBranch);
					pivPayment.setChequeBankCode(obj.getChequeBank());
					pivPayment.setChequeBankBranch(obj.getChequeBankBranch());
				}
				
				if(obj.getPaymentMethod().equals("Q"))
				{
					pivDetail.setStatus("Q");
				}
				if(!obj.getPaymentMethod().equals("C"))
				{
					payMode = obj.getPaymentMethod();
				}
				pivPaymentDao.register(pivPayment, appName);
			}
			//update piv detail
			pivDetail.setPaymentMode(payMode);
			pivDetailDao.update(pivDetail, appName);
					
			//insert piv history
			PivHistory pivHistory = new PivHistory();
			pivHistory.setDeptId(deptId);
			pivHistory.setPivNo(pivNo);
			pivHistory.setStatus(pivDetail.getStatus());
			pivHistory.setDescription("Payment Confirmed.");
			pivHistory.setUserId("POS_USER");
			Calendar calendar = Calendar.getInstance();
			Timestamp updateTime=new Timestamp(calendar.getTime().getTime());
			pivHistory.setUpdateTime(updateTime);
			pivHistoryDao.register(pivHistory, appName);
			
			if(!payMode.equals("Q"))
			{
				updateBulkPIVStatus(pivDetail.getReferenceNo(), appName);
			}
			//updateBankTrans(pivBankTran);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	


	
	
	
	@SuppressWarnings({ "unchecked" })
	@Override
	public List<PivBankTran> getBankPaymentList(String bankRefNo, String appName){
		log.error("$$$$$$$$$ getBankPaymentList");
		try{
			
			String qryStr = "select p from PivBankTran p where p.pivCheckNo = :bankRefNo order by p.addDate desc";
			Query query = getEntityManager(appName).createQuery(qryStr);
			query.setParameter("bankRefNo", bankRefNo);
			List <PivBankTran> list = query.getResultList();
			return list;
			
		}
		catch (Exception ex){
			ex.printStackTrace();
			return null;
		}
		
	}
	
	
	
	
	
	
	
	

	
	@SuppressWarnings("unchecked")
	@Override
	public PivDetail getSetoffFromPIV( String pivNo, String appName) {
		log.error("$$$$$$$$$ getSetoffFromPIV");
		try {
			String qryStr = "SELECT p FROM PivDetail p "+
							" where p.setoffFrom = :pivNo "+
							" and (p.status = 'T' or p.status = 'M')";
							
			Query query = getEntityManager(appName).createQuery(qryStr);
			query.setParameter("pivNo", pivNo);
			List results = query.getResultList();
			if (results.isEmpty()) {
			    return null; // handle no-results case
			} else {
			    return (PivDetail)results.get(0);
			}
		} catch (Exception  ex) {
			ex.printStackTrace();
			return null;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String isJobExist( String refNo, String appName) {
		log.error("$$$$$$$$$ isJobExist");
		try {
			String jobNo = null;
			String qryStr = "SELECT p.projectNo FROM Pcesthmt p "+
							" where p.projectNo = :refNo ";
							
							
			Query query = getEntityManager(appName).createQuery(qryStr);
			query.setParameter("refNo", refNo);
			List results = query.getResultList();
			if (results.isEmpty()) {
				return null;
			} else {
			    return (String)results.get(0);
			}
		} catch (Exception  ex) {
			ex.printStackTrace();
			return null;
		}
		
	}
	
	@Override
	public void setoffPiv(PivDetail pivDetail, PivDetail pivDetailSetoffFrom, String loggedUser, String appName) {
		log.error("$$$$$$$$$ setoffPiv");
		try 
		{
			
			//insert piv history
			PivHistory pivHistory = new PivHistory();
			pivHistory.setDeptId(pivDetail.getId().getDeptId());
			pivHistory.setPivNo(pivDetail.getId().getPivNo());
			pivHistory.setStatus(pivDetail.getStatus());
			pivHistory.setDescription("PIV set-off with "+pivDetail.getSetoffFrom());
			pivHistory.setUserId(loggedUser);
			Calendar calendar = Calendar.getInstance();
			Timestamp updateTime=new Timestamp(calendar.getTime().getTime());
			pivHistory.setUpdateTime(updateTime);
			
			pivDetailDao.update(pivDetail, appName);
			if(pivDetailSetoffFrom!=null)
				pivDetailDao.update(pivDetailSetoffFrom, appName);
			pivHistoryDao.register(pivHistory, appName);
			
			Application application = applicationDao.findByAppNo(pivDetailSetoffFrom.getReferenceNo(), appName);
			if(application!=null)
			{
				application.setStatus("D");
				String desc = application.getDescription();
				desc = desc + " Application is cancelled due to PIV set-off to another PIV "+pivDetail.getId().getPivNo();
				application.setDescription(desc);
				applicationDao.update(application, appName);
			}
			
			Pcesthtt pcesthtt = null;
			try
			{
				pcesthtt =		pcesthttDao.findByEstimateNo(pivDetailSetoffFrom.getReferenceNo(), appName);
			}
			catch(Exception e){}
			if(pcesthtt!=null)
			{
				pcesthtt.setStatus(new BigDecimal(0));
				String desc = pcesthtt.getDescr();
				desc = desc + " Estimate is cancelled due to PIV set-off to another PIV "+pivDetail.getId().getPivNo();
				pcesthtt.setDescr(desc);
				pcesthttDao.update(pcesthtt, appName);
			}
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void updateBulkPIVStatus(String refNo, String appName) {
		log.error("$$$$$$$$$ updateBulkPIVStatus");
		try 
		{
			String qryStr = "update Spstdesthmt set status = 60 where app_no = :refNo ";
			Query query = getEntityManager(appName).createQuery(qryStr);
			query.setParameter("refNo", refNo);
			int del = query.executeUpdate();
			
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		
		}
	
	
	}
	
	private void updateBulkAppStatus(String refNo, String appName) {
		log.error("$$$$$$$$$ updateBulkAppStatus");
		try 
		{
			String qryStr = " update Application a set a.status = 'P' "+
							" where a.id.applicationId = :refNo "+
							" and a.applicationType IN ('BS','LN','RE') ";
			Query query = getEntityManager(appName).createQuery(qryStr);
			query.setParameter("refNo", refNo);
			int del = query.executeUpdate();
			
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		
		}
	
	
	}
	
	
}
