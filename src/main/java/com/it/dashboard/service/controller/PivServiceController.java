package com.it.dashboard.service.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.it.dashboard.issue.domain.PivAmount;
import com.it.dashboard.issue.domain.PivAmountGrid;
import com.it.dashboard.issue.domain.PivAmountPK;
import com.it.dashboard.issue.domain.PivApplicant;
import com.it.dashboard.issue.domain.PivApplicantPK;
import com.it.dashboard.issue.domain.PivDetail;
import com.it.dashboard.issue.domain.PivDetailPK;
import com.it.dashboard.issue.repo.PivAmountDao;
import com.it.dashboard.issue.repo.PivApplicantDao;
import com.it.dashboard.issue.repo.PivDao;
import com.it.dashboard.issue.repo.PivDetailDao;
import com.it.dashboard.master.domain.Applicant;
import com.it.dashboard.master.domain.Application;
import com.it.dashboard.master.domain.ApplicationPK;
import com.it.dashboard.master.domain.WiringLandDetail;
import com.it.dashboard.master.domain.WiringLandDetailPK;
import com.it.dashboard.master.repo.MasterDao;
import com.it.dashboard.payment.domain.PaymentMethodObject;
import com.it.dashboard.payment.domain.PaymentObject;
import com.it.dashboard.payment.domain.PivBankTran;
import com.it.dashboard.payment.repo.PaymentDetail;
import com.it.dashboard.payment.repo.PivPaymentDao;
import com.it.dashboard.service.domain.ApplicationObject;
import com.it.dashboard.service.domain.CAAuthResponse;
import com.it.dashboard.service.domain.PivApplicantObject;
import com.it.dashboard.service.domain.PivDetailObject;
import com.it.dashboard.service.domain.PivObject;
import com.it.dashboard.service.domain.PivRequest;
import com.it.dashboard.service.domain.PivResponse;
import com.it.dashboard.service.domain.PivSearchParamObject;
import com.it.dashboard.service.domain.PivSearchResultObject;
import com.it.dashboard.service.domain.PivServiceParamObject;
import com.it.dashboard.service.domain.PivServiceRequest;
import com.it.dashboard.service.domain.StampDutyObject;
//import com.it.dashboard.payment.repo.pivDetail;
import com.it.dashboard.util.common.BaseResponse;
import com.it.dashboard.util.common.CurrencyToWords;
import com.it.dashboard.util.common.KeyValueObject;
import com.it.dashboard.util.common.SMSDataProjectCosting;
import com.it.dashboard.util.common.SendSMS;

@Controller
@RequestMapping("/webservice")
public class PivServiceController 
{
	private static final String SUCCESS_STATUS = "success";
	private static final Log log = LogFactory.getLog(PivServiceController.class);
	
	@Autowired
	private PivDetailDao pivDetailDao;
 	@Autowired
	private PivAmountDao pivAmountDao;
 	@Autowired
	private PivDao pivDao;
 	@Autowired
	private PivPaymentDao pivPaymentDao;
 	@Autowired
	private MasterDao masterDao;
 	@Autowired
	private PivApplicantDao pivApplicantDao;
 	@Autowired
	private PaymentDetail paymentDetail;
 
 	@RequestMapping(value = "/pay", method = RequestMethod.GET)
 	public @ResponseBody BaseResponse pay(@RequestParam(value = "PIVNo") String pivNo) 
 	{
 		BaseResponse response = new BaseResponse();
  
 		response.setStatus(SUCCESS_STATUS);
 		response.setCode(Integer.parseInt(pivNo));
  
 		return response;
 	}
 
 	@RequestMapping(value = "/greeting/{name}",method = RequestMethod.GET, produces = "application/json")
 	public @ResponseBody String greeting(@PathVariable("name") String name) 
 	{
    
 		return "hi "+name;
 	}
 
 	@RequestMapping(value = "/getPivDetail0/{pivNo}",method = RequestMethod.GET, produces = "application/json")
 	public @ResponseBody PivDetail getPivDetail0(@PathVariable("pivNo") String pivNo) 
 	{
 		
 		PivDetail pivDetail = pivDetailDao.findByBankCheckNo(pivNo,"POS");
 		return pivDetail;
 	}
 
 	@RequestMapping(value = "/getPivDetail/{pivNo}",method = RequestMethod.GET, produces = "application/json")
 	public @ResponseBody List<PivDetailObject> getPivDetail(@PathVariable("pivNo") String pivNo) 
 	{
 		
 		
 		
 		List<PivDetailObject> pivObjLst = new ArrayList<PivDetailObject>();
 		
 		String returnCode = "5";
 		String returnValue = "PIV cannot be paid.";
 		PivDetailObject pivObj = new PivDetailObject();
 		try
 		{
 			
 			
	 		PivDetail pivDetail = pivDetailDao.findByBankCheckNo(pivNo,"POS");
	 		if(pivDetail!=null)
	 		{
		 		String status = pivDetail.getStatus();
				
				if(status.equals("N") || status.equals("A"))
				{
			 		pivObj.setAmountInWords(pivDetail.getAmountInWords());
			 		pivObj.setCostCenter(pivDetail.getId().getDeptId());
			 		pivObj.setCurrencyCode(pivDetail.getCurrencyCode());
			 		pivObj.setExpiryDate(pivDetail.getExpiryDate());
			 		pivObj.setPivDate(pivDetail.getPivDate());
			 		pivObj.setPivNo(pivDetail.getBankCheckNo());
			 		pivObj.setPivReceiptNo(pivDetail.getPivReceiptNo());
			 		pivObj.setPivTotal(pivDetail.getGrandTotal().doubleValue());
			 		pivObj.setReferenceNo(pivDetail.getReferenceNo());
		 		
		 			List<PivAmountGrid> amtList = pivDao.getAcctCodesByPivNo(pivDetail.getId().getPivNo(),"POS");
			 		PivApplicant pivApp = pivApplicantDao.findByPivNo(pivDetail.getId().getPivNo(),"POS");
			 		
			 		PivApplicantObject pivAppObj = new PivApplicantObject();
			 		pivAppObj.setAddress(pivApp.getAddress());
			 		pivAppObj.setEmail(pivApp.getEmail());
			 		pivAppObj.setEpfNo(pivApp.getEpfNo());
			 		pivAppObj.setIdNo(pivApp.getIdNo());
			 		pivAppObj.setName(pivApp.getName());
			 		pivAppObj.setTelephoneNo(pivApp.getTelephoneNo());
			 		pivAppObj.setVatRegNo(pivApp.getVatRegNo());
			 		
			 		pivObj.setPivApplicantObject(pivAppObj);
			 		pivObj.setPivAmountList(amtList);
			 		
			 		returnCode = "1";
					returnValue = "PIV can be paid.";
				}
				else if(status.equals("P"))
				{
					returnCode = "3";
					returnValue = "PIV already paid.";
					
	 	 	   	}
				else if(status.equals("D"))
				{
					returnCode = "4";
					returnValue = "PIV cancelled.Ask customer to get a new PIV.";
					
	 	 	   	}
				else if(status.equals("E"))
				{
					returnCode = "6";
					returnValue = "PIV Expired. Ask customer to get a new PIV.";
					
	 	 	   	}
				else
				{
					returnCode = "5";
					returnValue = "PIV cannot be paid.";
					
				}
	 		}
	 		else
	 		{
	 			returnCode = "0";
				returnValue = "PIV does not exist.";
	 			
	 		}
 		}
 		catch(Exception ex)
 		{
 			//e = new Exception("PIV cannot be paid.";
 			ex.printStackTrace();
 			returnCode = "5";
			returnValue = "PIV cannot be paid.";
 		}
 		pivObj.setReturnCode(returnCode);
 		pivObj.setReturnValue(returnValue);
 		pivObjLst.add(pivObj);
 		return pivObjLst;
 	}
 	
 	@RequestMapping(value = "/getPivDetail1/{pivNo}",method = RequestMethod.GET, produces = "application/json")
 	public @ResponseBody List<PivDetailObject> getPivDetail1(@PathVariable("pivNo") String pivNo) throws Exception
 	{
 		/*return new Greeting(counter.incrementAndGet(),
                         String.format(template, name));*/
 		Exception e = null;
 		System.out.println("@@getPivDetail service@@"+pivNo);
 		List<PivDetailObject> objLst = new ArrayList<PivDetailObject>();
 		try
 		{
 			//PivDetail pivDetail = new PivDetail();
 			//pivDetail.setAmountInWords("123");
 			PivDetailObject pivObj = new PivDetailObject();
	 		PivDetail pivDetail = pivDetailDao.findByBankCheckNo(pivNo,"POS");
	 		if(pivDetail!=null)
	 		{
		 		String status = pivDetail.getStatus();
				
				if(status.equals("N") || status.equals("A"))
				{
			 		pivObj.setAmountInWords(pivDetail.getAmountInWords());
			 		pivObj.setCostCenter(pivDetail.getId().getDeptId());
			 		pivObj.setCurrencyCode(pivDetail.getCurrencyCode());
			 		pivObj.setExpiryDate(pivDetail.getExpiryDate());
			 		pivObj.setPivDate(pivDetail.getPivDate());
			 		pivObj.setPivNo(pivDetail.getBankCheckNo());
			 		pivObj.setPivReceiptNo(pivDetail.getPivReceiptNo());
			 		pivObj.setPivTotal(pivDetail.getGrandTotal().doubleValue());
			 		pivObj.setReferenceNo(pivDetail.getReferenceNo());
		 		
		 			List<PivAmountGrid> amtList = pivDao.getAcctCodesByPivNo(pivDetail.getId().getPivNo(),"POS");
			 		PivApplicant pivApp = pivApplicantDao.findByPivNo(pivDetail.getId().getPivNo(),"POS");
			 		
			 		PivApplicantObject pivAppObj = new PivApplicantObject();
			 		pivAppObj.setAddress(pivApp.getAddress());
			 		pivAppObj.setEmail(pivApp.getEmail());
			 		pivAppObj.setEpfNo(pivApp.getEpfNo());
			 		pivAppObj.setIdNo(pivApp.getIdNo());
			 		pivAppObj.setName(pivApp.getName());
			 		pivAppObj.setTelephoneNo(pivApp.getTelephoneNo());
			 		pivAppObj.setVatRegNo(pivApp.getVatRegNo());
			 		
			 		pivObj.setPivApplicantObject(pivAppObj);
			 		pivObj.setPivAmountList(amtList);
			 		objLst.add(pivObj);
			 		//objLst.add(pivAppObj);
			 		//objLst.add(amtList);
				}
				else if(status.equals("P"))
				{
					
					e = new Exception("PIV already paid.");
					throw e;
	 	 	   	}
				else if(status.equals("D"))
				{
					e = new Exception("PIV cancelled.Ask customer to get a new PIV.");
					throw e;
	 	 	   	}
				else if(status.equals("E"))
				{
					e = new Exception("PIV Expired. Ask customer to get a new PIV.");
					throw e;
	 	 	   	}
				else
				{
					e = new Exception("PIV cannot be paid.");
					throw e;
				}
	 		}
	 		else
	 		{
	 			e = new Exception("PIV does not exist.");
	 			throw e;
	 		}
 		}
 		catch(Exception ex)
 		{
 			//e = new Exception("PIV cannot be paid.");
 			ex.printStackTrace();
 			throw ex;
 		}
 		
 		return objLst;
 	}
 	
 	
 	
 	
 	@RequestMapping(method= RequestMethod.POST,value="/confirmPayment",  produces = "application/json",  consumes = "application/json")
 	public @ResponseBody KeyValueObject confirmPayment(@RequestBody PaymentObject paymentObj){
 		KeyValueObject obj = new KeyValueObject();
 		String key = "5";
 		String value = "Unsuccessful Payment.";
 		
 		try
 		{
 			if(paymentObj!=null)
 			{
 				Date paidDate = null;
 				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
 				try
 				{
 					paidDate = dateFormat.parse(paymentObj.getTransDate());
 				}
 				catch(ParseException e)
 				{
 					try
 					{
	 					dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	 					paidDate = dateFormat.parse(paymentObj.getTransDate());
 					}
 					catch(ParseException e1)
 					{}
 				}
 				Date transDate = dateFormat.parse(paymentObj.getPaidDate());
 				//insert piv_bank_trans
 				String transId = paymentObj.getTransId();
 				String pivCheckNo = paymentObj.getPivNo();
 				//PivBankTranPK pivBankTranPK = new PivBankTranPK();
 				
 				
 				PivBankTran pivBankTran = new PivBankTran();
 				pivBankTran.setActivity("PAYMENT");
 				pivBankTran.setPivCheckNo(pivCheckNo);
 				pivBankTran.setTransId(transId);
 				pivBankTran.setAddDate(new Timestamp(paidDate.getTime()));
 				pivBankTran.setAddUser("POS_USER");
 				pivBankTran.setAmount(new BigDecimal(paymentObj.getPaidTotalAmount()));
 				pivBankTran.setBankCode(paymentObj.getAgentCode());
 				pivBankTran.setBranchCode(paymentObj.getAgentBranchCode());
 				pivBankTran.setPaymentDate(new Timestamp(paidDate.getTime()));
 				pivBankTran.setStatus1("N");
 				pivBankTran.setTransDate(new Timestamp(transDate.getTime()));
 				long transLogId = pivPaymentDao.insertBankTrans(pivBankTran,"POS");
 				pivBankTran.setTransLogId(transLogId);
 				
 						
 				String pivNo = paymentObj.getPivNo();
 				PivDetail pivDetail = pivDetailDao.findByBankCheckNo(pivNo,"POS");
 				if(pivDetail!=null)
 				{
 					
 					//DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
 					double pivAmt = pivDetail.getPivAmount().doubleValue();
 					System.out.println("piv no "+pivDetail.getBankCheckNo());
 					System.out.println("piv amt "+pivAmt);
 					System.out.println("paid amt "+paymentObj.getPaidTotalAmount());
 					if(pivAmt==paymentObj.getPaidTotalAmount())
 					{
 						String status = pivDetail.getStatus();
 						
 						if(status.equals("N") || status.equals("A"))
 						{
 							
			 				pivDetail.setPaidAgent(paymentObj.getAgentCode());
			 				pivDetail.setPaidBranch(paymentObj.getAgentBranchCode());
			 				pivDetail.setPaidAmount(new BigDecimal(paymentObj.getPaidTotalAmount()));
			 				pivDetail.setPaidDate(paidDate);
			 				pivDetail.setPaidTime(new Timestamp(paidDate.getTime()));
			 				//pivDetail.setBankStatus("P");
			 				pivDetail.setBankPaidDate(paidDate);
			 				//pivDetail.setPaidDate(paidDate);
			 				Calendar calendar = Calendar.getInstance();
			 				SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
							String addTime = sdf1.format(calendar.getTime()) ;
			 				pivDetail.setConfirmedBy("POS_USER");
			 				pivDetail.setConfirmedDate(calendar.getTime());
			 				pivDetail.setConfirmedTime(addTime);
			 				pivDetail.setStatus("P");
			 				if(paymentObj.getAgentCode().equals("7135"))
			 					pivDetail.setPaidDeptId("000.00");
			 				else
			 					pivDetail.setPaidDeptId(paymentObj.getPaidDeptId());
			 				
			 				List<PaymentMethodObject> pivPaymentList = paymentObj.getPaymentMethodObjArray();
			 				//List<PaymentMethodObject> pivPaymentList = new ArrayList();
			 				
			 				pivBankTran.setStatus1("S");
				 	 	   	pivBankTran.setReturnCode(key);
				 	 	  	pivBankTran.setReturnMsg(value);
				 	 	  	
				 	 	  paymentDetail.confirmPayment(pivDetail, pivPaymentList,"POS");
				 	 	   	
				 	 	   	key = "1";
				 	 	   	value = "Payment successfully saved";
				 	 	   	
				 	 	   	//update cebassist
				 	 	   	if(pivDetail.getCaApplicationNo()!=null)
				 			{
					 			try
					 			{
					 				updateCAwithPayment(paymentObj.getPivNo(), pivDetail.getPaidDate());
					 			}
					 			catch(Exception e)
					 			{
					 				e.printStackTrace();
					 			}
				 			}
				 	 	   	sendSms(pivDetail);
 						}
 						else if(status.equals("P"))
 						{
 							key = "3";
				 	 	   	value = "PIV already paid.";
 						}
 						else if(status.equals("D"))
 						{
 							key = "4";
				 	 	   	value = "PIV cancelled.";
 						}
 						else if(status.equals("E"))
 						{
 							key = "6";
				 	 	   	value = "PIV Expired.";
 						}
 					}
 					else
 					{
 						key = "2";
 	 				 	value = "PIV Amounts are not matched.";
 					}
 				}
 				else
 				{
 					key = "0";
 				 	value = "PIV does not exist.";
 				}
 				obj.setKey(key);
 			 	obj.setValue(value);
 			 	//if(!key.equals("1"))
 			 	//{
	 				pivBankTran.setReturnCode(key);
	 				pivBankTran.setReturnMsg(value);
	 				pivPaymentDao.updateBankTrans(pivBankTran,"POS");
 			 //	}
 			}
 		
 		}
 		catch(Exception e)
 		{
 			e.printStackTrace();
 		}
 		return obj;
 	}
 	
 	@RequestMapping(value = "/cancelPayment/{pivNo}",method = RequestMethod.GET, produces = "application/json")
 	public @ResponseBody boolean cancelPayment(@PathVariable("pivNo") String pivNo) 
 	{
 		/*return new Greeting(counter.incrementAndGet(),
                         String.format(template, name));*/
 		boolean isCancelSuccess = true;
 		System.out.println("@@cancel payment service@@"+pivNo);
 		
 		try
 		{
 			PivDetail pivDetail = pivDetailDao.findByBankCheckNo(pivNo,"POS");
	 		if(pivDetail!=null)
	 		{
		 		String status = pivDetail.getStatus();
				
				if( ( status.equals("P") ||status.equals("Q")||status.equals("D")) && !pivDetail.getPaidDeptId().equals("000.00"))
				{
					
					pivDetail.setStatus("A");
					pivDetail.setUpdUser("POS_USER");
					Calendar calendar = Calendar.getInstance();
					SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
					String preparedTime = sdf.format(calendar.getTime()) ;
					pivDetail.setUpdDate(calendar.getTime());
					pivDetail.setUpdTime(preparedTime);
					pivDao.cancelPayment(pivDetail,"POS");
					
			 		
				}
				
				else
				{
					
					isCancelSuccess = false;
				}
	 		}
	 		else
	 		{
	 			
	 			isCancelSuccess = false;
	 		}
 		}
 		catch(Exception ex)
 		{
 			isCancelSuccess = false;
 			ex.printStackTrace();
 		}
 		
 		return isCancelSuccess;
 	}
 	
 	@RequestMapping(method= RequestMethod.POST,value="/serachPIV",  produces = "application/json",  consumes = "application/json")
 	public @ResponseBody List<PivSearchResultObject> searchPIV(@RequestBody PivSearchParamObject paramObj){
 		List<PivSearchResultObject> pivObjLst = new ArrayList<PivSearchResultObject>();
 		System.out.println("service serachPiv.....");
 		try
 		{
 			if(paramObj!=null)
 			{
 				pivObjLst = pivDao.serachPiv(paramObj,"POS");
 				for (int i=0;i<pivObjLst.size();i++) {
 					PivSearchResultObject obj = pivObjLst.get(i);
 					
 				}
 			}
 		
 		}
 		catch(Exception e)
 		{
 			e.printStackTrace();
 		}
 		return pivObjLst;
 	}
 	
 	@RequestMapping(method= RequestMethod.POST,value="/getStampDuty",  produces = "application/json",  consumes = "application/json")
 	public @ResponseBody List<StampDutyObject> getStampDuty(@RequestBody PivServiceParamObject paramObj){
 		List<StampDutyObject> objLst = new ArrayList<StampDutyObject>();
 		System.out.println("getStampDuty.....");
 		System.out.println("get Ageny Code....."+paramObj.getAgentCode());
 		if(paramObj.getAgentCode()!=null)
 		{
	 		try
	 		{
	 			String agentCode = paramObj.getAgentCode();
	 			String compId = masterDao.getCompIdToAgent(agentCode,"POS");
	 			Date fromDate = paramObj.getFromDate();
	 			Date toDate = paramObj.getToDate();
	 			
	 			objLst = pivDao.getStampDutyList(compId, fromDate, toDate,"POS");
	 		}
	 		catch(Exception e)
	 		{
	 			e.printStackTrace();
	 		}
 		}
 		System.out.println("getStampDuty.....end");
 		return objLst;
 	}
 	
 	
 	
 	
 	@RequestMapping(method= RequestMethod.POST,value="/generateApplicationPIV",  produces = "application/json",  consumes = "application/json")
 	public @ResponseBody PivResponse generateApplicationPIV(@RequestBody PivRequest pivRequest){
 	
 		System.out.println("generateApplicationPIV start");
 		
 		String resCode = "202";
 		String resValue = "Error.";
 		
 		PivResponse pivResponse = new PivResponse();
 		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
 		NumberFormat nf = NumberFormat.getInstance();
 		nf.setGroupingUsed(true);
 		nf.setMaximumFractionDigits(2);
 		nf.setMinimumFractionDigits(2);
 		
 		PivServiceRequest pivSerReq = new PivServiceRequest();	
 		pivSerReq.setRequestTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
 		pivSerReq.setServiceType("APP_PIV_GEN");
		
 		try
 		{
 			if(pivRequest!=null)
 			{
 				ApplicationObject applicationObj = pivRequest.getApplicationObject();
 				System.out.println("generateApplicationPIV applicationObj "+applicationObj);
 				if(applicationObj!=null)
 				{
 					
 					System.out.println("generateApplicationPIV with "+applicationObj.getCaApplicationNo());
 					System.out.println("generateApplicationPIV address line 1 "+applicationObj.getAddressLine1());
 					System.out.println("generateApplicationPIV address line 2 "+applicationObj.getAddressLine2());
 					System.out.println("generateApplicationPIV address line 3 "+applicationObj.getAddressLine3());
 					System.out.println("generateApplicationPIV ampereage "+applicationObj.getAmperage());
 					System.out.println("generateApplicationPIV app date "+applicationObj.getApplicationDate());
 					System.out.println("generateApplicationPIV app type "+applicationObj.getApplicationType());
 					System.out.println("generateApplicationPIV area id "+applicationObj.getAreaId());
 					System.out.println("generateApplicationPIV conn type "+applicationObj.getConnectioType());
 					System.out.println("generateApplicationPIV csc id "+applicationObj.getCscId());
 					System.out.println("generateApplicationPIV cus vat no "+applicationObj.getCustomerVatNo());
 					System.out.println("generateApplicationPIV email "+applicationObj.getEmail());
 					System.out.println("generateApplicationPIV exist acct no "+applicationObj.getExistingAccountNo());
 					System.out.println("generateApplicationPIV f name "+applicationObj.getFirstName());
 					System.out.println("generateApplicationPIV nic "+applicationObj.getId());
 					System.out.println("generateApplicationPIV id type "+applicationObj.getIdType());
 					System.out.println("generateApplicationPIV l name "+applicationObj.getLastName());
 					System.out.println("generateApplicationPIV mobile "+applicationObj.getMobile());
 					System.out.println("generateApplicationPIV nei acct no "+applicationObj.getNeighborAccountNo());
 					System.out.println("generateApplicationPIV phase "+applicationObj.getPhase());
 					System.out.println("generateApplicationPIV ref type "+applicationObj.getPivReferenceType());
 					System.out.println("generateApplicationPIV serv add line 1 "+applicationObj.getServiceAddressLine1());
 					System.out.println("generateApplicationPIV serv add line 2 "+applicationObj.getServiceAddressLine2());
 					System.out.println("generateApplicationPIV serv add line 3 "+applicationObj.getServiceAddressLine3());
 					System.out.println("generateApplicationPIV telephone "+applicationObj.getTelephone());
 					
 				}
 			}
 				
 			String msg = validateRequest(pivRequest);
 			System.out.println("generateApplicationPIV msg "+msg);
	 		if(msg == null)
	 		{
	 			
	 			
 				ApplicationObject applicationObj = pivRequest.getApplicationObject();
 				
 				PivObject pivObject =  new PivObject();
	 			
	 			String sysType = "CA";
 		 		if(pivRequest.getCallingFrom().equals("0"))
 		 			sysType = "CAOF";
 		 		else if(pivRequest.getCallingFrom().equals("1"))
 		 			sysType = "CAON";;
 		 			
	 			pivSerReq.setThirdPartyRefNo(applicationObj.getCaApplicationNo());
	 			pivSerReq.setRequestId(pivRequest.getRequestId());
	 			System.out.println("generateApplicationPIV 0 before  findByCAApplicationNo ");
	 			
	 			Object[] objArr = pivDetailDao.findByCAApplicationNo(new BigDecimal(applicationObj.getCaApplicationNo()),applicationObj.getPivReferenceType(), "CA");
	 			
	 			if(objArr==null || objArr.length==0)
	 			{
	 				String areaId = applicationObj.getAreaId();
	 				String cscId = applicationObj.getCscId();
	 				System.out.println("generateApplicationPIV 1 before getCostCenterToDepotCode1");
	 				String deptId = masterDao.getCostCenterToDepotCode1(areaId, cscId,"CA");
	 				System.out.println("generateApplicationPIV 2 ");
	 				//String deptId = "514.20";
	 				
	 				Applicant applicant = new Applicant();
		 				
	 				applicant.setIdNo(applicationObj.getId());
	 				applicant.setIdType(applicationObj.getIdType());
	 				
	 				applicant.setFirstName(applicationObj.getFirstName());
	 				if(applicationObj.getLastName()==null || applicationObj.getLastName().length()==0)
	 					applicant.setLastName(" ");
	 				else
	 					applicant.setLastName(applicationObj.getLastName());
	 				
	 				
	 				applicant.setStreetAddress(applicationObj.getAddressLine1());
	 				if(applicationObj.getAddressLine2()==null || applicationObj.getAddressLine2().length()==0)
	 					applicant.setSuburb(" ");
	 				else
	 					applicant.setSuburb(applicationObj.getAddressLine2());
	 				if(applicationObj.getAddressLine3()==null || applicationObj.getAddressLine3().length()==0)
	 					applicant.setCity(" ");
	 				else
	 					applicant.setCity(applicationObj.getAddressLine3());
	 				
	 				applicant.setPreferredLanguage("SI");
	 				applicant.setIdNo(applicationObj.getId());
	 				applicant.setTelephoneNo(applicationObj.getTelephone());
	 				applicant.setMobileNo(applicationObj.getMobile());
	 				applicant.setEmail(applicationObj.getEmail());
 				
	 				ApplicationPK applicationPK = new ApplicationPK();
	 				applicationPK.setDeptId(deptId);
	 				Application application = new Application();
	 				application.setId(applicationPK);
	 				
	 				System.out.println("ca date "+applicationObj.getApplicationDate());
	 				System.out.println("convert date "+dateFormat.parse(applicationObj.getApplicationDate()));
	 				application.setSubmitDate(dateFormat.parse(applicationObj.getApplicationDate()));
	 				application.setIdNo(applicationObj.getId());
	 				application.setPreparedBy("CIS");
	 				application.setStatus("N");
	 				application.setApplicationType("CR");
	 				application.setApplicationSubType(applicationObj.getApplicationType());
	 				application.setIdNo(applicationObj.getId());
	 				application.setAddDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
	 				
	 				System.out.println("generateApplicationPIV 3 ");
	 				WiringLandDetail WiringLandDetail = new WiringLandDetail();
	 				WiringLandDetailPK wiringLandDetailPK = new WiringLandDetailPK();
	 				wiringLandDetailPK.setDeptId(deptId);
	 				
	 				WiringLandDetail.setId(wiringLandDetailPK);
	 				WiringLandDetail.setConnectionType(new BigDecimal(applicationObj.getAmperage()));
	 				WiringLandDetail.setPhase(new BigDecimal(applicationObj.getPhase()));
	 				WiringLandDetail.setExistingAccNo(applicationObj.getExistingAccountNo());
	 				WiringLandDetail.setNeighboursAccNo(applicationObj.getNeighborAccountNo());
	 				WiringLandDetail.setServiceStreetAddress(applicationObj.getServiceAddressLine1());
	 				if(applicationObj.getServiceAddressLine3()==null || applicationObj.getServiceAddressLine3().length()==0)
	 					WiringLandDetail.setServiceCity(" ");
	 				else 
	 					WiringLandDetail.setServiceCity(applicationObj.getServiceAddressLine3());
	 				if(applicationObj.getServiceAddressLine2()==null || applicationObj.getServiceAddressLine2().length()==0)
	 					WiringLandDetail.setServiceSuburb(" ");
	 				else
	 					WiringLandDetail.setServiceSuburb(applicationObj.getServiceAddressLine2());
	 				WiringLandDetail.setOwnership("O");
	 				WiringLandDetail.setOccupyOwnerCertified("Y");
	 				WiringLandDetail.setIsGovernmentPlace("Y");
	 				WiringLandDetail.setTariffCatCode("GP");
	 				WiringLandDetail.setTariffCode("11");
	 				WiringLandDetail.setCustomerCategory("PRIV");
	 				WiringLandDetail.setCustomerType("DOME");
	 					 	
	 				System.out.println("generateApplicationPIV 4 ");
	 		 		Calendar calendar = Calendar.getInstance();
	 		 		Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());
	 				SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
	 				String preparedTime = sdf1.format(calendar.getTime()) ;
	 				
	 				double pivAmt = 0;
	 				/*AppSubtypeChargePK appSubtypeChargePK = new AppSubtypeChargePK();
					appSubtypeChargePK.setApplicationSubType(applicationObj.getApplicationType());
					appSubtypeChargePK.setConnectionType(new Long(applicationObj.getAmperage()));
					appSubtypeChargePK.setPhase(new Long(applicationObj.getPhase()));
					
					/*Spstdrat spstdrat1 = masterDao.getSpstdrat("STD_YEAR_APPLICABLE", "0000");
					String stdConYear = spstdrat1.getRate().toString();
					appSubtypeChargePK.setYear(stdConYear);*/
					
					
					//AppSubtypeCharge appSubtypeCharge = masterDao.getAppSubtypeCharge(appSubtypeChargePK);
					//pivAmt = appSubtypeCharge.getApplicationFee().doubleValue();
					if(applicationObj.getConnectioType().equals("0"))
	 		 			pivAmt = 6500;
	 		 		else if(applicationObj.getConnectioType().equals("1"))
	 		 			pivAmt = 18000;
	 		 		
	 		 		BigDecimal pivAmtBD = new BigDecimal(pivAmt);
	 		 		System.out.println("generateApplicationPIV 5 ");
	 		 		PivDetailPK pivDetailPK = new PivDetailPK();
	 		 		pivDetailPK.setDeptId(deptId);
	 		 		
	 		 		PivDetail pivDetail = new PivDetail();
	 		 		pivDetail.setAddDate(calendar.getTime());
	 		 		pivDetail.setAddTime(preparedTime);
	 		 		pivDetail.setAddUser("SYSTEM");
	 		 		String amtInWords = CurrencyToWords.convert(pivAmtBD.doubleValue());
	 		 		pivDetail.setAmountInWords(amtInWords);
	 		 		pivDetail.setGrandTotal(pivAmtBD);
	 		 		pivDetail.setId(pivDetailPK);
	 		 		pivDetail.setIdNo(applicationObj.getId());
	 		 		pivDetail.setIssuedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
	 		 		pivDetail.setPivDate(calendar.getTime());
	 		 		pivDetail.setPivAmount(pivAmtBD);
	 		 		//Gltitlm gltitlm = new Gltitlm();
	 		 		//gltitlm.setTitleCd("PIV-APN                                 ");
	 		 		pivDetail.setTitleCd("PIV-APN                                 ");
	 		 		pivDetail.setPivSeqNo(new BigDecimal(1));
	 		 		pivDetail.setReferenceType(applicationObj.getPivReferenceType());
	 		 		pivDetail.setPreparedBy("SYSTEM");
	 		 		//PivActivity pivActivity = new PivActivity();
	 		 		//pivActivity.setActivityCode("A");
	 		 		pivDetail.setStatus("A");
	 		 		pivDetail.setSysType(sysType);
	 		 		pivDetail.setCaApplicationNo(new BigDecimal(applicationObj.getCaApplicationNo()));
	 		 		pivDetail.setGrandTotal(pivAmtBD);
	 		 		pivDetail.setDescription("Solar Application Fee (Online Application No : ("+applicationObj.getCaApplicationNo()+")");
	 		 		pivDetail.setEnteredTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
	 		 		System.out.println("generateApplicationPIV 6 ");
	 		 		PivApplicantPK pivApplicantPK = new PivApplicantPK();
	 		 		pivApplicantPK.setDeptId(deptId);
	 		 		
	 		 		PivApplicant pivApplicant = new PivApplicant();
	 		 		pivApplicant.setId(pivApplicantPK);
	 		 		pivApplicant.setAddDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
	 		 		String appAdrr = applicationObj.getAddressLine1();
	 		 		if(applicationObj.getAddressLine2()!=null && applicationObj.getAddressLine2().trim().length()>0)
	 		 			appAdrr = appAdrr+", "+applicationObj.getAddressLine2();
	 		 		if(applicationObj.getAddressLine3()!=null && applicationObj.getAddressLine3().trim().length()>0)
	 		 			appAdrr = appAdrr+", "+applicationObj.getAddressLine3();
	 		 		pivApplicant.setAddress(appAdrr);
	 		 		pivApplicant.setAddUser("WEB");
	 		 		pivApplicant.setCollectPersonId(applicationObj.getId());
	 		 		pivApplicant.setCollectPersonName(applicationObj.getFirstName()+", "+applicationObj.getLastName());
	 		 		pivApplicant.setEmail(applicationObj.getEmail());
	 		 		pivApplicant.setIdNo(applicationObj.getId());
	 		 		pivApplicant.setIdType(applicationObj.getIdType());
	 		 		pivApplicant.setName(applicationObj.getFirstName()+", "+applicationObj.getLastName());
	 		 		pivApplicant.setTelephoneNo(applicationObj.getTelephone());
	 		 		pivApplicant.setVatRegNo(applicationObj.getCustomerVatNo());
	 		 		
	 		 		ArrayList<PivAmount> amountList = new ArrayList<PivAmount>();
	 		 		System.out.println("generateApplicationPIV 7");
	 		 		PivAmount pivAmount = new PivAmount();
	 		 		PivAmountPK pivAmountPK = new PivAmountPK();
	 		 		pivAmountPK.setAccountCode("R1379               ");
	 		 		pivAmountPK.setDeptId(deptId);
	 		 		pivAmount.setId(pivAmountPK);
	 		 		pivAmount.setAmount(pivAmtBD);
	 		 		amountList.add(pivAmount);
	 		 		System.out.println("generateApplicationPIV 8 ");
	 		 		pivAmount.setAmount(pivAmtBD);
	 		 		String appPrefix = deptId+"/ACR/22/";
	 		 		String[] strArr = pivDao.generatePivThruService(appPrefix,applicant, application, WiringLandDetail,pivDetail, pivApplicant, amountList,"CA");
	 		 		String pivNo = strArr[0];
	 		 		String bankRefNo = strArr[1];
	 		 		String appId = strArr[2];
	 		 		String pivCreatedDate = strArr[3];
	 		 		System.out.println("generateApplicationPIV 9");
	 		 		resCode = "200";
	 		 		resValue = "Success";
	 		 		pivObject.setBankRefNo(bankRefNo);
	 		 		pivObject.setPivAmount(nf.format(pivAmt));
	 		 		pivObject.setPivCreatedDate(pivCreatedDate);
	 		 		pivObject.setPivNo(pivNo);
	 		 		pivObject.setPivType("Solar Application Fee");
	 		 		
	 		 		pivResponse.setApplicationNo(appId);
	 		 		pivResponse.setPivObject(pivObject);
	 		 		
	 		 		pivSerReq.setPivNo(pivNo);
	 		 		System.out.println("generateApplicationPIV 10 ");
	 			}
	 			else
	 			{
	 				resCode = "700";
 	 				resValue = "PIV already generated for this Application.";
 	 				
 	 				
 	 				pivObject.setBankRefNo((String)objArr[1]);
 	 				pivObject.setPivAmount(nf.format(objArr[2]));
 	 				pivObject.setPivCreatedDate(dateFormat.format(objArr[3]));
 	 				pivObject.setPivNo((String)objArr[0]);
 	 				pivObject.setPivType("Solar Application Fee");
 	 				pivResponse.setPivObject(pivObject);
 	 				
 	 				
 	 				
 	 				pivSerReq.setPivNo((String)objArr[0]);
	 			}
	 			System.out.println("generateApplicationPIV 11 ");
	 			pivSerReq.setThirdPartyRefNo(applicationObj.getCaApplicationNo());
	 			pivSerReq.setCallingFrom(sysType);
	 			
	 		}
 			else
 			{
 				resCode = "202";
 				resValue = msg;
 			}
	 		
	 		System.out.println("generateApplicationPIV 12 ");
 		
 		}
 		catch(Exception e)
 		{
 			
 			resCode = "202";
 	 		resValue = e.getMessage();
 			e.printStackTrace();
 		}
 		pivResponse.setResponseCode(resCode);
 		pivResponse.setResponseMsg(resValue);
 		
 		pivSerReq.setResponseCode(resCode);
 		pivSerReq.setResponseMsg(resValue);
 		pivSerReq.setResponseTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
 		try
 		{
 			pivDao.addPivServiceReq(pivSerReq,"CA");
 			
 		}catch(Exception e){
 			
 			//e.printStackTrace();
 		}
 		System.out.println("generateApplicationPIV end");
 		System.out.println("--------------------------");
 		return pivResponse;
 	}
 	
 	private String validateRequest(PivRequest pivRequest)
 	{
 		String msg = null;
 		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
 		if(pivRequest==null)
 			return "Request object cannot be null";
 		ApplicationObject applicationObject = pivRequest.getApplicationObject();
 		if(applicationObject==null)
 			return "Application object cannot be null";
 		
 		if(applicationObject.getFirstName()==null || applicationObject.getFirstName().trim().length()==0)
 			return "First Name cannot be blank.";
 		else 
 			if(applicationObject.getFirstName().trim().length()>50)
 				return "First Name length should be below 50";
 		
 		if(pivRequest.getRequestDate()==null || pivRequest.getRequestDate().trim().length()==0)
 			return "Request Date cannot be blank.";
 		else
 		{
 			try
			{
				dateFormat.parse(pivRequest.getRequestDate());
			}
			catch(ParseException e)
			{
				return "Request Date format should be dd/MM/yyyy HH:mm:ss";
				
			}
 		}
 		
 		if(pivRequest.getCallingFrom()==null || pivRequest.getCallingFrom().trim().length()==0)
 			return "Calling From parameter cannot be blank.";
 		
 		if(applicationObject.getCaApplicationNo()==null || applicationObject.getCaApplicationNo().trim().length()==0)
 			return "CA Application No is blank.";
 		
 		if(applicationObject.getApplicationDate()==null || applicationObject.getApplicationDate().trim().length()==0)
 			return "Application Date cannot be blank.";
 		else
 		{
 			try
			{
				dateFormat.parse(pivRequest.getRequestDate());
			}
			catch(ParseException e)
			{
				return "Application Date format should be dd/MM/yyyy HH:mm:ss";
			}
 		}
 		
 		if(applicationObject.getFirstName()==null || applicationObject.getFirstName().trim().length()==0)
 			return "First Name cannot be blank.";
 		else 
 			if(applicationObject.getFirstName().trim().length()>50)
 				return "First Name length should be less than or equal to 50";
 		
 		if(applicationObject.getLastName()!=null && applicationObject.getLastName().trim().length()>60)
 				return "Last Name length should be less than or equal to 60";
 		//contact address validation
 		if(applicationObject.getAddressLine1()==null || applicationObject.getAddressLine1().trim().length()==0)
 			return "Address Line 1 cannot be blank.";
 		else if(applicationObject.getAddressLine1().trim().length()>100)
 				return "Address Line 1 length should be less than or equal to 100";
 		
 		if(applicationObject.getAddressLine2()!=null && applicationObject.getAddressLine2().length()>100)
 				return "Address Line 2 length should be less than or equal to 100";
 		
 		if(applicationObject.getAddressLine3()!=null && applicationObject.getAddressLine3().trim().length()>100)
 				return "Address Line 3 should be less than or equal to 100";
 		//contact address validation end
 		
 		//service address validation
 		if(applicationObject.getServiceAddressLine1()==null || applicationObject.getServiceAddressLine1().trim().length()==0)
 			return "Service Address Line 1 cannot be blank.";
 		else if(applicationObject.getServiceAddressLine1().length()>100)
 				return "Service Address Line 1 length should be less than or equal to 100";
 		
 		if(applicationObject.getServiceAddressLine2()!=null && applicationObject.getServiceAddressLine2().length()>100)
 				return "Service Address Line 2 length should be less than or equal to 100";
 		
 		if(applicationObject.getServiceAddressLine3()!=null && applicationObject.getServiceAddressLine3().length()>100)
 				return "Service Address Line 3 should be less than or equal to 100";
 		//service address validation end
 		
 		if(applicationObject.getIdType()==null || applicationObject.getIdType().trim().length()==0)
 			return "ID Type cannot be blank.";
 		if(!applicationObject.getIdType().equals("NIC")&&!applicationObject.getIdType().equals("PAS")&&!applicationObject.getIdType().equals("BRN"))
 			return "ID Type should be NIC, PAS or BRN";
 		
 		if(applicationObject.getId()==null || applicationObject.getId().trim().length()==0)
 			return "ID No cannot be blank.";
 		else 
 			if(applicationObject.getId().trim().length()>12)
 				return "ID No length should be below 12";
 		
 		if(applicationObject.getTelephone()!=null && applicationObject.getTelephone().length()>10)
				return "Telephone No length should be less than or equal to 10";
 		
 		if(applicationObject.getMobile()!=null && applicationObject.getMobile().trim().length()>10)
			return "Mobile No length should be less than or equal to 10";
 		
 		if(applicationObject.getEmail()!=null && applicationObject.getEmail().trim().length()>50)
			return "Email length should be less than or equal to 50";
 		
 		if(applicationObject.getCscId()==null || applicationObject.getCscId().trim().length()==0)
 			return "CSC ID cannot be blank.";
 		else if(applicationObject.getCscId().trim().length()!=1)
			return "CSC ID No length should be 1";
 		
 		if(applicationObject.getAreaId()==null || applicationObject.getAreaId().trim().length()==0)
 			return "Area ID cannot be blank.";
 		else if(applicationObject.getAreaId().trim().length()!=2)
				return "Area ID No length should be 2";
 		
 		
 		if(applicationObject.getApplicationType()==null || applicationObject.getApplicationType().trim().length()==0)
 			return "Application Type No cannot be blank.";
 		if(!applicationObject.getApplicationType().equals("NM")
 				&&!applicationObject.getApplicationType().equals("NA")
 				&&!applicationObject.getApplicationType().equals("NP")
 				&&!applicationObject.getApplicationType().equals("BM")
 				&&!applicationObject.getApplicationType().equals("BA")
 				&&!applicationObject.getApplicationType().equals("BP"))
 			return "Invalid Application Type.";
 		
 		/*if(!applicationObject.getAmperage().equals("30")
 				&&!applicationObject.getApplicationType().equals("60")
 				&&!applicationObject.getApplicationType().equals("15"))
 		 	return "Invalid Amperage.";*/
 		
 		if(!applicationObject.getPhase().equals("1")
 				&&!applicationObject.getPhase().equals("3")
 				)
 		 	return "Invalid Phase.";
 		
 		if(applicationObject.getConnectioType()==null || applicationObject.getConnectioType().trim().length()==0)
 			return "Connection Type cannot be blank.";
 		if(!applicationObject.getConnectioType().equals("0")
 				&&!applicationObject.getConnectioType().equals("1")
 				)
 		 	return "Invalid Connection Type.";
 		
 		return msg;
 		
 	}
 	
 	private void sendSms(PivDetail pivDetail)
 	{
 		try
 		{
	 		PivApplicant pivApplicant = pivApplicantDao.findByPivNo(pivDetail.getId().getPivNo(),"POS");
	 		if(pivApplicant.getTelephoneNo()!=null && pivApplicant.getTelephoneNo().length()==10)
			{
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date today = Calendar.getInstance().getTime();        
				String reportDate = df.format(today);
				
				SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
				String paidDate = df1.format(pivDetail.getPaidDate());
			
				NumberFormat nf = NumberFormat.getInstance();
		 		nf.setGroupingUsed(true);
		 		nf.setMaximumFractionDigits(2);
		 		nf.setMinimumFractionDigits(2);
				String amtStr = nf.format(pivDetail.getPaidAmount());
				String agent = "CEB POS.";
								   
				String msg = "Payment of Rs."+amtStr+" received to CEB PIV No "+pivDetail.getBankCheckNo()+" on "+paidDate+" via "+agent;
				System.out.println("sms msg "+msg);
				System.out.println("piv amt "+pivDetail.getPivAmount());
				System.out.println("piv tele no "+pivApplicant.getTelephoneNo());
				
				SMSDataProjectCosting sms = new SMSDataProjectCosting();
				sms.setAcct_number(pivDetail.getBankCheckNo());
				sms.setAlias("CEB Info");
				sms.setApp_ref_key("PIV");
				//sms.setArea_code("514.00");
				sms.setAppkey("PIV");
				sms.setCost_cnter(pivDetail.getId().getDeptId());
				//sms.setDivision("R4");
				sms.setExpr_date(reportDate);
				sms.setMessage(msg);
				sms.setPr_key(1);
				sms.setProc_date(reportDate);
				sms.setProc_flag("W");
				//sms.setProvince("WPS2");
				sms.setRecno(0);
				sms.setRef_id(pivDetail.getBankCheckNo());
				//sms.setSend_date(reportDate);
				sms.setShdl_date(reportDate);
				sms.setTele_no(pivApplicant.getTelephoneNo());
				sms.setAppkey("PIV");
	       
	       		List<SMSDataProjectCosting> smsList = new ArrayList<SMSDataProjectCosting>();
	       		smsList.add(sms);
		       	
	       		SendSMS smsObj = new SendSMS();
	       		smsObj.sendSMSWithDateAsString(smsList);
	       		
	       	}
 		}
 		catch(Exception e)
 		{
 			e.printStackTrace();
 		}
 	}
 	private void updateCAwithPayment(String bankCheckNo, Date paymentDate) throws Exception
 	{
 		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		String paymentDateStr = sdf.format(paymentDate) ;
	   		
 		System.out.println("updateCAwithPayment piv no "+bankCheckNo);
 			System.out.println("updateCAwithPayment payment date no "+paymentDateStr);
			//update cebassist
 	 	   	final String url = "http://cebassist.ceb/AuthToken";
 	 	   	
 	 	   	RestTemplate restTemplate = new RestTemplate();
 			HttpHeaders headers = new HttpHeaders();
 			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
 					
 			MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
 			map.add("username", "pivpaymentsystem");
 			map.add("password", "s3k4ijYeQXS@Gns");

 			HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(map, headers);
 			
 			ResponseEntity<CAAuthResponse> response = restTemplate.postForEntity(url, requestEntity, CAAuthResponse.class);
 	 
 			System.out.println("body "+response.getBody().getAccess_token());
 			String token = response.getBody().getAccess_token();
 			
 			/********************************************/
 			
 			String updateUrl = "http://cebassist.ceb/Public/UpdatePivPayment";
 			RestTemplate restTemplate1 = new RestTemplate();
 			
 			HttpHeaders headers1 = new HttpHeaders();
 			headers1.setContentType(MediaType.APPLICATION_JSON);
 			//headers1.set("Authorization", response.getBody().getAccess_token());
 			//headers.add(HeaderParameters.AUTHORIZATION, HeaderParameters.BEARER + TokenGeneration.token);
 			headers1.add("Authorization", "Bearer " + token);
 			//headers1.
 			
 			/*CAPaymentUpdateRequest reqObject = new CAPaymentUpdateRequest();
 			reqObject.setPivNo("26020522200229");
 			reqObject.setPaymentLocation("2");
 			reqObject.setPivPaymentDate(new Date());*/
 			
 			JSONObject jsonObj = new JSONObject();
 			jsonObj.put("PivNo", bankCheckNo);
 			jsonObj.put("PaymentLocation", "2");
 			jsonObj.put("PivPaymentDate", paymentDateStr);
 			
 			//HttpEntity<MultiValueMap<String, String>> requestEntity1 = new HttpEntity<MultiValueMap<String, String>>(reqObject, headers1);
 			
 			HttpEntity<String> requestEntity1 = new HttpEntity<String>(jsonObj.toString(), headers1);
 			//HttpEntity<CAPaymentUpdateRequest> requestEntity1 = new HttpEntity<CAPaymentUpdateRequest>(reqObject, headers1);
 			
 			ResponseEntity<String> response1 = restTemplate.postForEntity(updateUrl, requestEntity1, String.class);
 			
 			//PIVOfflineUpdateResponse support = restTemplate.getForObject(url, PIVOfflineUpdateResponse.class, costCeneter);
 			System.out.println("response1 header "+response1.getHeaders());
 			System.out.println("response1 body "+response1.getBody());
 			
		
 	}
 	
}

