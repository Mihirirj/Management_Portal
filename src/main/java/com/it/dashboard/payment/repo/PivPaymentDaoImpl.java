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
public class PivPaymentDaoImpl extends EmSelector implements PivPaymentDao {
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
	private static final Log log = LogFactory.getLog(PivPaymentDaoImpl.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public PivPayment findByPivCheque(String pivNo, String chequeNo, String appName) {
		log.error("$$$$$$$$$ findByPivCheque");
		try {
			String qryStr = "SELECT p FROM PivPayment p "+
							" where p.id.pivNo = :pivNo "+
							" and p.chequeNo = :chequeNo ";
			Query query = getEntityManager(appName).createQuery(qryStr);
			query.setParameter("pivNo", pivNo);
			query.setParameter("chequeNo", chequeNo);
			PivPayment pivPayment = (PivPayment)query.getSingleResult();
			return pivPayment;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public PivPayment findById(String deptId, String pivNo, long seqNo, String appName) {
		log.error("$$$$$$$$$ findById");
		try {
			String qryStr = "SELECT p FROM PivPayment p "+
							" where p.id.deptId = :deptId "+
							" and p.id.pivNo = :pivNo "+
							" and p.id.seqNo = :seqNo ";
			Query query = getEntityManager(appName).createQuery(qryStr);
			query.setParameter("deptId", deptId);
			query.setParameter("pivNo", pivNo);
			query.setParameter("seqNo", seqNo);
			PivPayment pivPayment = (PivPayment)query.getSingleResult();
			return pivPayment;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	/*
	@SuppressWarnings("unchecked")
	@Override
	public List<PivApplicant> getChequePayemtPiv(String deptId) {
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
	}*/
	
	///////////////////////////////
	////////////////////////sandun
	@SuppressWarnings("unchecked")
	@Override
	public List<PivPayment> getChequePaymentPived(String deptId, String paymentMode, String status, String appName) {
		log.error("$$$$$$$$$ getChequePaymentPived");
		try {
			String qryStr = "select p from PivPayment p "+
							" where p.id.deptId = :deptId "+
							" and p.status is null "+
							" and p.paymentMode = :paymentMode "+
							" and pivDetail.status = 'Q' "+
							" order by pivDetail.paidDate desc";

			Query query = getEntityManager(appName).createQuery(qryStr);
			query.setParameter("deptId", deptId);
			query.setParameter("paymentMode", paymentMode);
			List<PivPayment> list = query.getResultList();
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
	public List<PivPayment> getPivToStatusAndPayMode(List<String> deptList, String paymentMode, String status, String chequeNo, String appName) {
		
		log.error("$$$$$$$$$ getPivToStatusAndPayMode deptList "+deptList);
		log.error("$$$$$$$$$ getPivToStatusAndPayMode paymentMode "+paymentMode);
		log.error("$$$$$$$$$ getPivToStatusAndPayMode status "+status);
		log.error("$$$$$$$$$ getPivToStatusAndPayMode chequeNo "+chequeNo);
		try {
			String qryStr = "select p from PivPayment p "+
							"  where p.pivDetail.paidDeptId in (:deptList) "+
							" and p.status is null "+
							" and p.paymentMode = :paymentMode "+
							" and pivDetail.status = :status ";
			if(chequeNo != null)
				qryStr = qryStr+" and p.chequeNo = :chequeNo ";
			qryStr = qryStr+" order by p.chequeNo, pivDetail.paidDate desc";
			log.error("$$$$$$$$$ getPivToStatusAndPayMode qryStr"+qryStr);
			Query query = getEntityManager(appName).createQuery(qryStr);
			query.setParameter("deptList", deptList);
			query.setParameter("paymentMode", paymentMode);
			query.setParameter("status", status);
			if(chequeNo != null)
				query.setParameter("chequeNo", chequeNo);
			List<PivPayment> list = query.getResultList();
			log.error("$$$$$$$$$ getPivToStatusAndPayMode list"+list.size());
			return list;

		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
			return null;
		}
	}

//////////////////////////
///////////////////////////////sandun search function
@SuppressWarnings("unchecked")
@Override
public List<PivPaymentGrid> getPivPaymentsToChequeNo1(String chequeNo, String appName) {
	log.error("$$$$$$$$$ getPivPaymentsToChequeNo1");
try {
String qryStr = "SELECT p FROM PivPayment p "+
" where p.chequeNo = :chequeNo";





/*select a.ACCOUNT_CODE , c.ac_nm, a.amount
from piv_amount a, Glacctm c  where piv_no = 'PIV/260.20/PIV-CQR/0001'
and a.ACCOUNT_CODE = c.ac_cd*/
Query query = getEntityManager(appName).createQuery(qryStr);
query.setParameter("chequeNo", chequeNo);
//	query.setParameter("pivNo", pivNo);
//query.setParameter("paidAmount",paidAmount );

List<PivPaymentGrid> list = query.getResultList();
return list;
} catch (Exception ex) {
ex.printStackTrace();
return null;
}
}

/*
	@SuppressWarnings("unchecked")
	@Override
	public List<PivPaymentGrid> getPivPaymentsToChequeNo(String chequeNo) {
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
	}*/

	@SuppressWarnings("unchecked")
	@Override
	public List<PivPaymentGrid> getPivPaymentsToPIV(String pivNo, String appName) {
		log.error("$$$$$$$$$ getPivPaymentsToPIV");
		try {
			String qryStr = " SELECT new com.it.dashboard.payment.domain.PivPaymentGrid(pp.id.seqNo, trim(to_char( pp.paidAmount, '999,999,999,999,999,999.99' )) , "+
							" pp.paymentMode,  "+
							" pp.bankBranch.bank.bankName, pp.bankBranch.branchName, "+
							" pp.chequeDate, pp.chequeNo )  " +
							" FROM PivPayment pp " +
							" WHERE pp.id.pivNo =  :pivNo " ;
							
							
			
			Query query = getEntityManager(appName).createQuery(qryStr);
			query.setParameter("pivNo", pivNo);
			List<PivPaymentGrid> list = query.getResultList();
			
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PivPayment> getPivPaymentToPIV(String pivNo, String appName) {
		log.error("$$$$$$$$$ getPivPaymentToPIV");
		try {
			String qryStr = " SELECT pp "+
							" FROM PivPayment pp " +
							" WHERE pp.id.pivNo =  :pivNo " ;
							
							
			
			Query query = getEntityManager(appName).createQuery(qryStr);
			query.setParameter("pivNo", pivNo);
			List<PivPayment> list = query.getResultList();
			
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getPivNoToChequeNo(String chequeNo, String status, String appName) {
		log.error("$$$$$$$$$ getPivNoToChequeNo");
		try {
			String qryStr = " SELECT  pp.id.pivNo "+
							" FROM PivPayment pp " +
							" WHERE pp.chequeNo =  :chequeNo " +
							" and pp.status =  :status ";
					
							
			
			
			Query query = getEntityManager(appName).createQuery(qryStr);
			query.setParameter("chequeNo", chequeNo);
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
	public List<String> getChequeNoToStatus(String status, String appName) {
		log.error("$$$$$$$$$ getChequeNoToStatus");
		try {
			String qryStr = " SELECT  pp.chequeNo "+
							" FROM PivPayment pp " +
							" WHERE pp.status =  :status " ;
							
							
							
			
			/*select a.ACCOUNT_CODE , c.ac_nm, a.amount
			from piv_amount a, Glacctm c  where piv_no = 'PIV/260.20/PIV-CQR/0001'
			and a.ACCOUNT_CODE = c.ac_cd*/
			Query query = getEntityManager(appName).createQuery(qryStr);
			query.setParameter("status", status);
			List<String> list = query.getResultList();
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	
	
	
	
	
	@Override
	public void deletePaymentToPivNo(String pivNo, String appName) {
		log.error("$$$$$$$$$ deletePaymentToPivNo");
		try 
		{
			String qryStr = "Delete from PivPayment p where p.id.pivNo = :pivNo ";
			Query query = getEntityManager(appName).createQuery(qryStr);
			query.setParameter("pivNo", pivNo);
			int del = query.executeUpdate();
			
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		
		}
	
	
	}

	@Override
	public void update(PivPayment pivPayment, String appName) {
		log.error("$$$$$$$$$ update");
		getEntityManager(appName).merge(pivPayment);
		
	}
	
	@Override
	public void register(PivPayment pivPayment, String appName) {
		log.error("$$$$$$$$$ register");
		long seqNo = getNextPayemntSeqNo(pivPayment.getId().getPivNo(),pivPayment.getId().getDeptId(), appName);
		pivPayment.getId().setSeqNo(seqNo);
		getEntityManager(appName).persist(pivPayment);
		
		
	}
	
	@Override
	public void remove(PivPayment pivPayment, String appName) {
		log.error("$$$$$$$$$ remove");
		//getEntityManager(appName).remove(pivPayment);
		pivPayment = getEntityManager(appName).merge(pivPayment);  
		getEntityManager(appName).remove(pivPayment);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public long getNextPayemntSeqNo(String pivNo, String deptId, String appName) {
		log.error("$$$$$$$$$ getNextPayemntSeqNo");
		long nextSeq = 1;
	
		String strQuery="select max(p.id.seqNo) from PivPayment p "+
						"where p.id.deptId = :deptId "+
						"and p.id.pivNo = :pivNo ";
    	//String strQuery="select p.id.seqNo from PivPayment p where p.id.pivNo like :like ORDER BY 1 DESC";
    	Query query=getEntityManager(appName).createQuery(strQuery);//SELECT MIS.TEST4_SEQ.NEXTVAL() FROM DUAL
    	query.setParameter("pivNo", pivNo);
    	query.setParameter("deptId", deptId);
    	Object obj=query.getSingleResult();
    	if (obj!=null){
    		Long intObj = (Long)obj;
    		nextSeq = intObj.longValue()+1;
    	}
    	return nextSeq;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public double getPaidAmount(String pivNo, String appName) {
		log.error("$$$$$$$$$ getPaidAmount");
		double amount = 0;
	
		String strQuery="select sum(p.paidAmount) from PivPayment p "+
						"where p.id.pivNo = :pivNo ";
    	//String strQuery="select p.id.seqNo from PivPayment p where p.id.pivNo like :like ORDER BY 1 DESC";
    	Query query=getEntityManager(appName).createQuery(strQuery);//SELECT MIS.TEST4_SEQ.NEXTVAL() FROM DUAL
    	query.setParameter("pivNo", pivNo);
    	
    	Object obj=query.getSingleResult();
    	if (obj!=null){
    		BigDecimal intObj = (BigDecimal)obj;
    		amount = intObj.doubleValue();
    	}
    	return amount;
	}
	
	@Override
	public long insertBankTrans(PivBankTran pivBankTran, String appName)
	{
		log.error("$$$$$$$$$ insertBankTrans");
		getEntityManager(appName).persist(pivBankTran);
		return pivBankTran.getTransLogId();
	}
	
	@Override
	public void updateBankTrans(PivBankTran pivBankTran, String appName)
	{
		log.error("$$$$$$$$$ updateBankTrans");
		getEntityManager(appName).merge(pivBankTran);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public PivBankTran findBankTranById(String transId, String pivCheckNo, String activity, String appName) {
		try {
			log.error("$$$$$$$$$ findBankTranById");
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
	
	/*
	@Override
	public void confirmPayment( PivDetail pivDetail, List<PaymentMethodObject> pivPaymentList)
	{
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
				pivPaymentPK.setSeqNo(getNextPayemntSeqNo(pivNo, deptId));
				
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
					pivDetail.getPivActivity().setActivityCode("Q");
				}
				if(!obj.getPaymentMethod().equals("C"))
				{
					payMode = obj.getPaymentMethod();
				}
				register(pivPayment);
			}
			//update piv detail
			pivDetail.setPaymentMode(payMode);
			pivDetailDao.update(pivDetail);
					
			//insert piv history
			PivHistory pivHistory = new PivHistory();
			pivHistory.setDeptId(deptId);
			pivHistory.setPivNo(pivNo);
			pivHistory.setStatus(pivDetail.getPivActivity().getActivityCode());
			pivHistory.setDescription("Payment Confirmed.");
			pivHistory.setUserId("POS_USER");
			Calendar calendar = Calendar.getInstance();
			Timestamp updateTime=new Timestamp(calendar.getTime().getTime());
			pivHistory.setUpdateTime(updateTime);
			pivHistoryDao.register(pivHistory);
			
			if(!payMode.equals("Q"))
			{
				updateBulkPIVStatus(pivDetail.getReferenceNo());
			}
			//updateBankTrans(pivBankTran);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	*/
	@SuppressWarnings("unchecked")
	@Override
	public boolean isChequePayment(String deptId, String pivNo, String appName) {
		boolean isChequePayment = false;
		log.error("$$$$$$$$$ isChequePayment");
		try {
			
			String qryStr = "SELECT p FROM PivPayment p "+
							" where p.id.deptId = :deptId "+
							" and p.id.pivNo = :pivNo "+
							" and p.paymentMode = 'Q' ";
			Query query = getEntityManager(appName).createQuery(qryStr);
			query.setParameter("deptId", deptId);
			query.setParameter("pivNo", pivNo);
			List list = query.getResultList();
			if(list!=null && list.size()>0)
				isChequePayment  = true;
			
		} catch (Exception ex) {
			ex.printStackTrace();
			
		}
		return isChequePayment;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public double getChequeTotal(String chequeNo, List<String> deptList, String status, String appName) {
		double total = 0;
		log.error("$$$$$$$$$ getChequeTotal");
		try {
			
			String qryStr = "select sum(paidAmount) from PivPayment p "+
							"  where p.id.deptId in (:deptList) "+
							" and p.status = :status "+
							" and p.paymentMode = 'Q' "+
							" and p.chequeNo = :chequeNo ";
			Query query = getEntityManager(appName).createQuery(qryStr);
			query.setParameter("chequeNo", chequeNo);
			query.setParameter("deptList", deptList);
			query.setParameter("status", status);
			BigDecimal obj = (BigDecimal)query.getSingleResult();
			total = obj.doubleValue();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			
		}
		return total;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public double getFreshChequeTotal(String chequeNo, List<String> deptList, String appName) {
		double total = 0;
		log.error("$$$$$$$$$ getFreshChequeTotal");
		try {
			
			String qryStr = "select sum(paidAmount) from PivPayment p "+
							"  where p.id.deptId in (:deptList) "+
							" and p.status is null "+
							" and p.paymentMode = 'Q' "+
							" and p.chequeNo = :chequeNo ";
			Query query = getEntityManager(appName).createQuery(qryStr);
			query.setParameter("deptList", deptList);
			query.setParameter("chequeNo", chequeNo);
			if(query.getSingleResult()!=null)
			{
				BigDecimal obj = (BigDecimal)query.getSingleResult();
				total = obj.doubleValue();
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			
		}
		return total;
	}
	
/******** edit by pasindu ********/
	
	@SuppressWarnings({ "unchecked" })
	@Override
	public List<PivPayment> getReturnPivListByStatus(String deptId, String status, String appName){
		log.error("$$$$$$$$$ getReturnPivListByStatus");
		try{
			
			String qryStr = "select p from PivPayment p where p.id.deptId = :deptId and p.status = :status order by p.id.pivNo";
			Query query = getEntityManager(appName).createQuery(qryStr);
			query.setParameter("deptId", deptId);
			query.setParameter("status", status);
			List <PivPayment> list = query.getResultList();
			return list;
			
		}
		catch (Exception ex){
			ex.printStackTrace();
			return null;
		}
		
	}
	/********************************/

	
	@SuppressWarnings({ "unchecked" })
	@Override
	public List<PivPayment> getReturnPivList(List<String> deptList, String status, String appName){
		log.error("$$$$$$$$$ getReturnPivList");
		try{
			
			String qryStr = "select p from PivPayment p where p.id.deptId in (:deptList) and p.status = :status order by p.id.pivNo";
			Query query = getEntityManager(appName).createQuery(qryStr);
			query.setParameter("deptList", deptList);
			query.setParameter("status", status);
			List <PivPayment> list = query.getResultList();
			return list;
			
		}
		catch (Exception ex){
			ex.printStackTrace();
			return null;
		}
		
	}
	
	/*
	@SuppressWarnings({ "unchecked" })
	@Override
	public List<PivBankTran> getBankPaymentList(String bankRefNo){
		
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
	*/
	
	
	
	
	
	
	
/*
	
	@SuppressWarnings("unchecked")
	@Override
	public PivDetail getSetoffFromPIV( String pivNo) {
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
	*/
	/*
	@SuppressWarnings("unchecked")
	@Override
	public String isJobExist( String refNo) {
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
	*/
	/*
	@Override
	public void setoffPiv(PivDetail pivDetail, PivDetail pivDetailSetoffFrom, String loggedUser) {
		try 
		{
			
			//insert piv history
			PivHistory pivHistory = new PivHistory();
			pivHistory.setDeptId(pivDetail.getId().getDeptId());
			pivHistory.setPivNo(pivDetail.getId().getPivNo());
			pivHistory.setStatus(pivDetail.getPivActivity().getActivityCode());
			pivHistory.setDescription("PIV set-off with "+pivDetail.getSetoffFrom());
			pivHistory.setUserId(loggedUser);
			Calendar calendar = Calendar.getInstance();
			Timestamp updateTime=new Timestamp(calendar.getTime().getTime());
			pivHistory.setUpdateTime(updateTime);
			
			pivDetailDao.update(pivDetail);
			if(pivDetailSetoffFrom!=null)
				pivDetailDao.update(pivDetailSetoffFrom);
			pivHistoryDao.register(pivHistory);
			
			Application application = applicationDao.findByAppNo(pivDetailSetoffFrom.getReferenceNo());
			if(application!=null)
			{
				application.setStatus("D");
				String desc = application.getDescription();
				desc = desc + " Application is cancelled due to PIV set-off to another PIV "+pivDetail.getId().getPivNo();
				application.setDescription(desc);
				applicationDao.update(application);
			}
			
			Pcesthtt pcesthtt = null;
			try
			{
				pcesthtt =		pcesthttDao.findByEstimateNo(pivDetailSetoffFrom.getReferenceNo());
			}
			catch(Exception e){}
			if(pcesthtt!=null)
			{
				pcesthtt.setStatus(new BigDecimal(0));
				String desc = pcesthtt.getDescr();
				desc = desc + " Estimate is cancelled due to PIV set-off to another PIV "+pivDetail.getId().getPivNo();
				pcesthtt.setDescr(desc);
				pcesthttDao.update(pcesthtt);
			}
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}*/
}
