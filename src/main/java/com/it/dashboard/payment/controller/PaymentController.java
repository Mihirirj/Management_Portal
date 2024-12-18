package com.it.dashboard.payment.controller;


import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.it.dashboard.admin.domain.PivHistory;
import com.it.dashboard.admin.repo.PivHistoryDao;
import com.it.dashboard.admin.repo.SecurityDao;
import com.it.dashboard.clientService.domain.PIVOfflineUpdateResponse;
import com.it.dashboard.clientService.domain.PIVOfflineUpdateSummery;
import com.it.dashboard.clientService.domain.PIVPayment;
import com.it.dashboard.clientService.domain.PivResponse;
import com.it.dashboard.clientService.domain.PymntOfflineMthdObject;
import com.it.dashboard.issue.domain.PivAmountGrid;
import com.it.dashboard.issue.domain.PivApplicant;
import com.it.dashboard.issue.domain.PivDetail;
import com.it.dashboard.issue.domain.PivModel;
import com.it.dashboard.issue.repo.PivApplicantDao;
import com.it.dashboard.issue.repo.PivDao;
import com.it.dashboard.issue.repo.PivDetailDao;
import com.it.dashboard.master.repo.MasterDao;
import com.it.dashboard.payment.domain.PivBankTran;
import com.it.dashboard.payment.domain.PivPayment;
import com.it.dashboard.payment.repo.PaymentDetail;
import com.it.dashboard.payment.repo.PivPaymentDao;
import com.it.dashboard.view.controller.CurrencyToWords;


@Controller
@RequestMapping(value = "/payment")
public class PaymentController {
	
	
	@Autowired
	private PivDao pivDao;
	@Autowired
	private PivDetailDao pivDetailDao;
	@Autowired
	private PivApplicantDao pivApplicantDao;
	@Autowired
	MasterDao masterDao;
	@Autowired
	SecurityDao securityDao;
	@Autowired
	private PivHistoryDao pivHistoryDao;
	@Autowired
	private PivApplicantDao PivApplicantDao;
	@Autowired
	private PivPaymentDao pivPaymentDao;
	@Autowired
	private PivDao pivdao;
	@Autowired
	private PaymentDetail paymentDetail;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    sdf.setLenient(true);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}
	
	@ExceptionHandler(NullPointerException.class)
	public String handleNullException(NullPointerException ex) {

		ModelAndView model = new ModelAndView("admin/index");
		model.addObject("errMsg", "this is null Exception.class");
		
		//return model;
		return "redirect:../admin/index";
	}
	
	@RequestMapping(value = "/chequePayment", method = RequestMethod.GET)
	public ModelAndView check(HttpServletRequest request , Model model) {
			PivModel pivModel = new PivModel();
			String deptId = request.getSession().getAttribute("deptId").toString();
			List<String> activityList = (List<String>)request.getSession().getAttribute("userActivity");
			if(activityList.contains("Q"))
			{
				request.getSession().setAttribute("headerName", "Cheque Payment PIV");
				//String deptId = (String)request.getSession().getAttribute("deptId");
				
				//List<PivPayment> pivList = pivPaymentDao.getChequePaymentPived(deptId, "Q", null);
				String loggedUser = (String)request.getSession().getAttribute("loggedUser");
				List<String> deptList = securityDao.getAuthorizedCostCenters(loggedUser,"PIV");
				List<PivPayment> pivList = pivPaymentDao.getPivToStatusAndPayMode(deptList, "Q","Q",null,"PIV");
				model.addAttribute("pivList", pivList);
				return new ModelAndView("payment/chequePayment" , "pivModel", pivModel);
			}
			else
			{
				return new ModelAndView("admin/noAccess");
			}
	 }
	
	
	
	@RequestMapping(value = "/realisedCheque", method = RequestMethod.GET)
	 public ModelAndView realisedCheque(HttpServletRequest request,Model model,@RequestParam("pivNo") String pivNo,@RequestParam("chequeNo") String chequeNo) {
			PivModel pivModel = new PivModel();
			System.out.println("realisedCheque-------------"+chequeNo);
			String deptId = request.getSession().getAttribute("deptId").toString();
			//pivDetailDao.updateStatus(pivNo, deptId, "P");
			String loggedUser = (String)request.getSession().getAttribute("loggedUser");
			List<String> deptList = securityDao.getAuthorizedCostCenters(loggedUser,"PIV");
		    try
		    {
		    	paymentDetail.updateChequePivStatus(chequeNo, "P", loggedUser, deptList,"PIV");
				
		    }
		    catch(Exception e)
		    {  
		    	e.printStackTrace();
		    }
		   // String loggedUser = (String)request.getSession().getAttribute("loggedUser");
			
			List<PivPayment> pivList = pivPaymentDao.getPivToStatusAndPayMode(deptList, "Q","Q",null,"PIV");
			model.addAttribute("pivList", pivList);
		    return new ModelAndView("payment/chequePayment" , "pivModel", pivModel);
			
	 }
	
	@RequestMapping(value = "/returnedCheque", method = RequestMethod.GET)
	public ModelAndView returnedCheque(HttpServletRequest request,Model model,@RequestParam("pivNo") String pivNo,@RequestParam("chequeNo") String chequeNo) {
		
			
			
			//String searchedChequeNo = model.getChequeNo();
			PivModel pivModel = new PivModel();
			String deptId = request.getSession().getAttribute("deptId").toString();
			pivDetailDao.updateStatus(pivNo, deptId, "H","PIV");
			String loggedUser = (String)request.getSession().getAttribute("loggedUser");
			List<String> deptList = securityDao.getAuthorizedCostCenters(loggedUser,"PIV");
			try{
				paymentDetail.chequeStatusUpdate(pivNo, deptId, chequeNo, "H",loggedUser,"PIV");
				paymentDetail.updateChequePivStatus(chequeNo, "H", loggedUser, deptList,"PIV");
				//pivPaymentDao.updateStatus1(pivNo, deptId, chequeNo, "P");
				
			    }
			    catch(Exception e)
			    {   
			    }
			//pivPaymentDao.chequeStatusUpdate(pivNo, chequeNo, "H");
			request.getSession().setAttribute("headerName", "Cheque Payment PIV");
			
			
			//String loggedUser = (String)request.getSession().getAttribute("loggedUser");
			
			List<PivPayment> pivList = pivPaymentDao.getPivToStatusAndPayMode(deptList, "Q","Q",null,"PIV");
			model.addAttribute("pivList", pivList);
			return new ModelAndView("payment/chequePayment" , "pivModel", pivModel);
	 }
	
	
	
	@RequestMapping(value = "/findChequePiv", method = RequestMethod.POST)
	public ModelAndView submitFormAjax(@ModelAttribute("pivModel") PivModel pivModel, HttpServletRequest request, BindingResult result) {
		String deptId = request.getSession().getAttribute("deptId").toString();
		if(result.hasErrors()){
			ModelAndView model = new ModelAndView("payment/chequePayment");
			return model;
		}
		String chequeNo = pivModel.getChequeNo();
		if (chequeNo.trim().length()==0 )
		{
			pivModel.setMessage("Please Enter Some Search Criteria!!");
			pivModel.setMessageType("ERROR");
			ModelAndView model = new ModelAndView("payment/chequePayment");
			request.getSession().setAttribute("headerName", "Search PIV");
			return model;
		} 
		else {
			request.getSession().setAttribute("headerName", "Search PIV");
			String loggedUser = (String)request.getSession().getAttribute("loggedUser");
			List<String> deptList = securityDao.getAuthorizedCostCenters(loggedUser,"PIV");
			
			List<PivPayment> pivList  = pivPaymentDao.getPivToStatusAndPayMode(deptList, "Q","Q",chequeNo,"PIV");
			double pivTotal = pivPaymentDao.getFreshChequeTotal(chequeNo, deptList,"PIV");
			//double pivRealisedTotal = pivPaymentDao.getChequeTotal(chequeNo, deptList,"P");
			//double pivReturnedTotal = pivPaymentDao.getChequeTotal(chequeNo, deptList, "H");
			NumberFormat nf = NumberFormat.getInstance();
			nf.setGroupingUsed(true);
			nf.setMaximumFractionDigits(2);
			nf.setMinimumFractionDigits(2);
			
			pivModel.setPivTotal("Cheque Total = "+nf.format(pivTotal));
			
			
			
			
			
			ModelAndView model = new ModelAndView("payment/chequePayment", "pivModel", pivModel);
			model.addObject("pivList", pivList);
			
			return model;
		}
	}
	
	
	@RequestMapping(value = "/bankPayment", method = RequestMethod.GET)
	public ModelAndView bankPaymetView(HttpServletRequest request , Model model) {
			PivModel pivModel = new PivModel();
			String deptId = request.getSession().getAttribute("deptId").toString();
			List<String> activityList = (List<String>)request.getSession().getAttribute("userActivity");
			request.getSession().setAttribute("headerName", "Payments Query");
			if(activityList.contains("PQ"))
			{
				
				//String deptId = (String)request.getSession().getAttribute("deptId");
				
				//List<PivPayment> pivList = pivPaymentDao.getChequePaymentPived(deptId, "Q", null);
				//String loggedUser = (String)request.getSession().getAttribute("loggedUser");
				//List<String> deptList = securityDao.getAuthorizedCostCenters(loggedUser);
				//List<PivPayment> pivList = pivPaymentDao.getPivToStatusAndPayMode(deptList, "Q","Q",null);
				//model.addAttribute("pivList", pivList);
				return new ModelAndView("payment/bankPayment" , "pivModel", pivModel);
			}
			else
			{
				return new ModelAndView("admin/noAccess");
			}
	 }
	
	@RequestMapping(value = "/findBankPayment", method = RequestMethod.POST)
	public ModelAndView findBankPayments(@ModelAttribute("pivModel") PivModel pivModel, HttpServletRequest request, BindingResult result) {
		String deptId1 = (String)request.getSession().getAttribute("deptId");
		if(result.hasErrors()){
			ModelAndView model = new ModelAndView("payment/bankPayment");
			return model;
		}
		String chequeNo = pivModel.getChequeNo();
		if (chequeNo.trim().length()==0 )
		{
			
			
			pivModel.setMessage("Please Enter Some Search Criteria!!");
			pivModel.setMessageType("ERROR");
			ModelAndView model = new ModelAndView("payment/bankPayment");
			request.getSession().setAttribute("headerName", "Search PIV");
			return model;
		} 
		else {
			
			String bankRefNo = chequeNo;
			request.getSession().setAttribute("headerName", "Bank Payments");
			ModelAndView model = new ModelAndView("payment/bankPayment", "pivModel", pivModel);
			PivDetail pivDetail = pivDetailDao.findByBankCheckNo(bankRefNo,"PIV");
			if(pivDetail!=null)
			{
				String deptId = pivDetail.getId().getDeptId();
				String loggedUser = (String)request.getSession().getAttribute("loggedUser");
				List<String> deptList = securityDao.getAuthorizedCostCenters(loggedUser,"PIV");
				if(deptList.contains(deptId))
				{
					pivModel.setPivDetail(pivDetail);
					
					List<PivBankTran> pivBankPayList  = paymentDetail.getBankPaymentList(bankRefNo,"PIV");
					//pivModel.setPivBankPayList(pivBankPayList);
					if(pivBankPayList!=null && pivBankPayList.size()>0)
					{
						model.addObject("pivList", pivBankPayList);
					}
					else
					{
						pivModel.setMessage("No payments for this PIV.");
						pivModel.setMessageType("ERROR");
					}
				}
				else
				{
					pivModel.setMessage("Sorry! You don't have authority to view payments of this PIV");
					pivModel.setMessageType("ERROR");
				}
			}
			else
			{
				pivModel.setMessage("PIV does not exist.");
				pivModel.setMessageType("ERROR");
			}
			
			return model;
		}
	}
	
	/******** edit by pasindu ********/
	
	//method to retrieve new form to generate a new PIV
	@RequestMapping(value = "/unconfirmPayment", method = RequestMethod.GET)
	private String viewPaymentDetails(HttpServletRequest request, Model model) {
		request.getSession().setAttribute("headerName", "Payment Details");               
		
		String deptId = request.getSession().getAttribute("deptId").toString();
		String costCeneter = deptId.substring(0,5);
		
		
		final String url = "http://pcds.ceb/PCDSServices/api/PIV/GetUnpostedPIVPayments?costCeneter={costCeneter}";
		RestTemplate restTemplate = new RestTemplate();
		PIVOfflineUpdateResponse support = restTemplate.getForObject(url, PIVOfflineUpdateResponse.class, costCeneter);
		model.addAttribute("support", support);
				
		for (int i=0; i<support.getOlnUpdt().size(); i++){
			
			PIVOfflineUpdateSummery var1 = support.getOlnUpdt().get(i);
			String pivNo = var1.getPivNo();
			String agentCode = var1.getAgentCode();
			String agentBranchCode = var1.getAgentBranchCode();
			String paidDeptId = var1.getPaidDeptId();
			double paidTotalAmount = var1.getPaidTotalAmount();
			String paidDate = var1.getPaidDate();
			
			for (int j=0; j<support.getOlnUpdt().get(i).getPaymentMethodObjArray().size(); j++){
				
				PymntOfflineMthdObject var2 = support.getOlnUpdt().get(i).getPaymentMethodObjArray().get(j);
				String pivNo1 = var2.getPivNo();
				double paidAmount = var2.getPaidAmount();
				String paymentMethod = var2.getPaymentMethod();
				String chequeNo = var2.getChequeNo();
				String creditCardNum = var2.getCreditCardNum();
				String chequeBank = var2.getChequeBank();
				String chequeBankBranch = var2.getChequeBankBranch();
				String tranID = var2.getTranID();

			}
		}
		return "payment/confirmDetails";
		
	  }
	/**********************************************/

	
	/******** edit by pasindu ********/
	
	@RequestMapping(value = "/confirmPiv", method = RequestMethod.GET)
	 public ModelAndView confirmPiv(HttpServletRequest request, Model model) {
				
		List<String> activityList = (List<String>)request.getSession().getAttribute("userActivity");
		PivModel pivModel = new PivModel();
		String deptId = request.getSession().getAttribute("deptId").toString();
		String loggedUser = (String)request.getSession().getAttribute("loggedUser");
		String pivNoFromJson = request.getParameter("pivNo");
		PivDetail pivDetail = pivDetailDao.findReferenceNoOrBankCheckNoInPiv(pivNoFromJson, "A","PIV");

		if(pivDetail != null){
			String bankCheckNo = pivDetail.getBankCheckNo();
			String referenceNo = pivDetail.getReferenceNo();
			String pivNo = pivDetail.getId().getPivNo();

			final String url = "http://10.128.1.126/PCDSServices/api/PIV/GetPaymentDetailsByPIV?pivNum={pivNo}";
			
			RestTemplate restTemplate = new RestTemplate();
			PivResponse response = restTemplate.getForObject(url, PivResponse.class, pivNoFromJson);
			model.addAttribute("response", response);
			model.addAttribute("pivModel", pivModel);
		
		
			double total = 0;
			int seqNum = 0;
			for(int i=0; i<response.getPayment().size(); i++){
				seqNum++;
				PIVPayment var = response.getPayment().get(i);
				PivPayment pivPayment = new PivPayment();
				String seqNo = String.valueOf(seqNum);
				String paidAgent = var.getAgent();
				String paidBranch = var.getCenter();
				String[] paidDate = var.getTranDt().split(" ");
				
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				try {
					Date dateNew = format.parse(paidDate[0]);
					DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
					String reportDate = df.format(dateNew);
					
					pivDetail.setPaidDate(dateNew);
					
					

				} catch (ParseException e) {
					e.printStackTrace();
				}
									
				total = total + Double.parseDouble(var.getTranAmt());
				pivModel.setPivTotal(String.valueOf(total));
				pivDetail.setPaidAgent(paidAgent);
				pivDetail.setPaidBranch(paidBranch);
				
			}
			pivDetail.setPaidAmount(BigDecimal.valueOf(total));
	
				request.getSession().setAttribute("headerName", "Confirm Payment");
				PivApplicant pivApplicant = pivApplicantDao.findByPivNo(pivNo,"PIV");
				List<PivAmountGrid> amountList = pivDao.getAcctCodesByPivNo(pivNo,"PIV");
				List<String> pivNoList = pivDetailDao.getPivNoListByStatus(deptId,"A", "PIV","PIV");
				boolean newPivNoList = pivNoList.add(pivNo);
				
				pivModel.setMode("CONFIRM");
				pivModel.setPivNoList(pivNoList);
				pivModel.setPivDetail(pivDetail);
				//pivModel.setPivTypeList(getPivTypeList(deptId));
				pivModel.setPivApplicant(pivApplicant);
				pivModel.setAmountList(amountList);
				pivModel.setStatusDesc(masterDao.getStatusDescription(pivDetail.getStatus(),"PIV"));
					
				return new ModelAndView("payment/confirmPivForm" , "pivModel", pivModel);
				
			}
		
			else{
				request.getSession().setAttribute("headerName", "Generate New PIV");
				pivModel.setMode("CONFIRM");
				return new ModelAndView("payment/confirmPivForm" , "pivModel", pivModel);
			}
			
		}
	
	
	@RequestMapping(value = "/setOff", method = RequestMethod.GET)
	public ModelAndView setOff(HttpServletRequest request , Model model) {
			PivModel pivModel = new PivModel();
			String deptId = request.getSession().getAttribute("deptId").toString();
			List<String> activityList = (List<String>)request.getSession().getAttribute("userActivity");
			request.getSession().setAttribute("headerName", "PIV Set-Off");
			if(activityList.contains("T"))
			{
				return new ModelAndView("payment/setoff" , "pivModel", pivModel);
			}
			else
			{
				return new ModelAndView("admin/noAccess");
				
			}
	 }
	
	@RequestMapping(value = "/setOffHandWritten", method = RequestMethod.GET)
	public ModelAndView setOffHandWritten(HttpServletRequest request , Model model) {
			PivModel pivModel = new PivModel();
			String deptId = request.getSession().getAttribute("deptId").toString();
			List<String> activityList = (List<String>)request.getSession().getAttribute("userActivity");
			request.getSession().setAttribute("headerName", "Hand Written PIV Set-Off");
			if(activityList.contains("M"))
			{
				return new ModelAndView("payment/setoffManual" , "pivModel", pivModel);
			}
			else
			{
				return new ModelAndView("admin/noAccess");
				
			}
	 }
	
	@RequestMapping(value = "/viewPiv", method = RequestMethod.GET)
	 public ModelAndView viewPiv(HttpServletRequest request, Model model) {
		PivModel pivModel = new PivModel();
		String deptId = request.getSession().getAttribute("deptId").toString();
		return new ModelAndView("payment/pivSetOff" , "pivModel", pivModel);
	}
	
	
	
	
	@RequestMapping(value = "/formSubmit", method = RequestMethod.GET,params="findManualButton")
	 public ModelAndView findManualButton(@ModelAttribute("pivModel") PivModel pivModel   , HttpServletRequest request) {
		String pivNoFind = pivModel.getPivNoFind();
		String deptId = request.getSession().getAttribute("deptId").toString();
		if(pivNoFind==null || pivNoFind.equals(""))
		{
			pivModel.setMessage("PIV no. cannot be empty.");
			pivModel.setMessageType("ERROR");
		}
		else
		{
			//String pivNo = pivModel.getPivDetail().getId().getPivNo();
			PivDetail pivDetail=pivModel.getPivDetail();
			pivDetail=pivDetailDao.findByPivNo(pivNoFind.trim(),"PIV");
			if(pivDetail==null)
			{
				pivModel.setMessage("PIV does not exist.");
				pivModel.setMessageType("ERROR");
			}
			else
			{
				String status=pivDetail.getStatus();
				setPivData(pivModel,pivDetail);
				
				if(!status.equals("A"))
				{
					pivModel.setMode(null);
					pivModel.setMessage("Sorry, This PIV cannot be Set-Off");
					pivModel.setMessageType("ERROR");
				}
				else
				{
					pivModel.setMode("SO");
				}
			}
		}
		return new ModelAndView("payment/setoffManual" , "pivModel", pivModel);
		}
	
	
		@RequestMapping(value = "/formSubmit", method = RequestMethod.GET,params="findButton")
		public ModelAndView findButton(@ModelAttribute("pivModel") PivModel pivModel , HttpServletRequest request) {
			String pivNoFind = pivModel.getPivNoFind();
			String deptId = request.getSession().getAttribute("deptId").toString();
			if(pivNoFind==null || pivNoFind.equals(""))
			{
				pivModel.setMessage("PIV no. cannot be empty.");
				pivModel.setMessageType("ERROR");
			}
			else
			{
				//String pivNo = pivModel.getPivDetail().getId().getPivNo();
				PivDetail pivDetail=pivModel.getPivDetail();
				pivDetail=pivDetailDao.findByPivNo(pivNoFind.trim(),"PIV");
				if(pivDetail==null)
				{
					pivModel.setMessage("PIV does not exist.");
					pivModel.setMessageType("ERROR");
				}
				else
				{
					String status=pivDetail.getStatus();
					setPivData(pivModel,pivDetail);
					
					if(!status.equals("A"))
					{
						pivModel.setMode(null);
						pivModel.setMessage("Sorry, This PIV cannot be Set-Off");
						pivModel.setMessageType("ERROR");
					}
					else
					{
						pivModel.setMode("SO");
					}
				}
			}
			return new ModelAndView("payment/setoff" , "pivModel", pivModel);
		}
	
		
		@RequestMapping(value = "/formSubmit", method = RequestMethod.GET,params="setoffButton")
		public ModelAndView setoffButton(@ModelAttribute("pivModel") PivModel pivModel , HttpServletRequest request) {
			String deptId = request.getSession().getAttribute("deptId").toString();
			String pivNo = pivModel.getPivDetail().getId().getPivNo();
			PivDetail pivDetail=pivDetailDao.findByPivNo(pivNo.trim(),"PIV");
			String setoffFromPivNo = pivModel.getPivDetail().getSetoffFrom();
			pivModel.setMode("SO");
			
			if(setoffFromPivNo==null || setoffFromPivNo.equals(""))
			{
				pivModel.setMessage("PIV No.(Setoff From) cannot be empty.");
				pivModel.setMessageType("ERROR");
			}
			else
			{
				PivDetail setoffFromPivDetail=pivDetailDao.findByPivNo(setoffFromPivNo.trim(),"PIV");
				if(setoffFromPivDetail==null)
				{
					pivModel.setMessage("PIV No.(Setoff From) does not exist.");
					pivModel.setMessageType("ERROR");
				}
				else
				{
					String status=setoffFromPivDetail.getStatus();
					
					if(status.equals("P"))
					{
						PivDetail preSetffFromPivDetail  = paymentDetail.getSetoffFromPIV(setoffFromPivNo,"PIV");
						if(preSetffFromPivDetail==null)
						{
							if(pivDetail.getPivAmount().doubleValue()<=setoffFromPivDetail.getPivAmount().doubleValue())
							{
								String refNo = setoffFromPivDetail.getReferenceNo();
								String jobNo = paymentDetail.isJobExist(refNo,"PIV");
								if(jobNo==null)
								{
									//PivActivity pivActivity = new PivActivity();
									//pivActivity.setActivityCode("T");
									pivDetail.setStatus("T");
									pivDetail.setSetoffFrom(setoffFromPivNo);
									pivDetail.setPaidDate(setoffFromPivDetail.getPaidDate());
									pivDetail.setConfirmedBy(setoffFromPivDetail.getConfirmedBy());
									pivDetail.setConfirmedDate(setoffFromPivDetail.getConfirmedDate());
									pivDetail.setConfirmedTime(setoffFromPivDetail.getConfirmedTime());
								
									setoffFromPivDetail.setSetoffTo(pivNo);
									
									String loggedUser = (String)request.getSession().getAttribute("loggedUser");
									paymentDetail.setoffPiv(pivDetail, setoffFromPivDetail, loggedUser,"PIV");
									pivModel.setMessage("PIV set-off done successfully.");
									pivModel.setMessageType("SUCCESS");
								}
								else
								{
									pivModel.setMessageType("ERROR");
									pivModel.setMessage("Sorry, PIV cannot be set-off with PIV "+setoffFromPivNo+". Job no ("+jobNo+") has been created related to this PIV.");
								}
							}
							else
							{
								pivModel.setMessageType("ERROR");
								pivModel.setMessage("Sorry, PIV cannot be set-off. Paid PIV amount is less than PIV to be set-off.");
							}
						}
						else
						{
							pivModel.setMessageType("ERROR");
							pivModel.setMessage("PIV No.(Setoff From) has already been set-off with "+preSetffFromPivDetail.getId().getPivNo());
						}
					}
					else
					{
						pivModel.setMessageType("ERROR");
						pivModel.setMessage("PIV No.(Setoff From) is not a paid PIV.");
					}
				}
			}
			setPivData(pivModel,pivDetail);
			pivDetail.setSetoffFrom(setoffFromPivNo);
			
		
			return new ModelAndView("payment/setoff" , "pivModel", pivModel);
		}
	
		
		@RequestMapping(value = "/formSubmit", method = RequestMethod.GET,params="setoffManualButton")
		public ModelAndView setoffManualButton(@ModelAttribute("pivModel") PivModel pivModel , HttpServletRequest request) {
			String deptId = request.getSession().getAttribute("deptId").toString();
			String pivNo = pivModel.getPivDetail().getId().getPivNo();
			Date paidDate=pivModel.getPivDetail().getPaidDate();
			PivDetail pivDetail=pivDetailDao.findByPivNo(pivNo.trim(),"PIV");
			String setoffFromPivNo = pivModel.getPivDetail().getSetoffFrom();
			pivModel.setMode("SO");
			
			if(setoffFromPivNo==null || setoffFromPivNo.equals(""))
			{
				pivModel.setMessage("PIV No.(Setoff From) cannot be empty.");
				pivModel.setMessageType("ERROR");
			}
			else if(paidDate==null || paidDate.equals(""))
			{
				pivModel.setMessage("Paid Date cannot be empty.");
				pivModel.setMessageType("ERROR");
			}
			else
			{
				PivDetail preSetffFromPivDetail  = paymentDetail.getSetoffFromPIV(setoffFromPivNo,"PIV");
				if(preSetffFromPivDetail==null)
				{
					String loggedUser = (String)request.getSession().getAttribute("loggedUser");
					//PivActivity pivActivity = new PivActivity();
					//pivActivity.setActivityCode("M");
					pivDetail.setStatus("M");
					pivDetail.setSetoffFrom(setoffFromPivNo);
					pivDetail.setPaidDate(paidDate);
					Calendar calendar = Calendar.getInstance();
					SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
					String confirmedTime = sdf.format(calendar.getTime()) ;
					pivDetail.setConfirmedDate(calendar.getTime());
					pivDetail.setConfirmedTime(confirmedTime);
					pivDetail.setConfirmedBy(loggedUser);
							
					paymentDetail.setoffPiv(pivDetail, null, loggedUser,"PIV");
					pivModel.setMessage("PIV set-off with manual PIV done successfully.");
					pivModel.setMessageType("SUCCESS");
							
				}
				else
				{
					pivModel.setMessageType("ERROR");
					pivModel.setMessage("PIV No.(Setoff From) has already been set-off with "+preSetffFromPivDetail.getId().getPivNo());
				}
			}
			setPivData(pivModel,pivDetail);
			pivDetail.setSetoffFrom(setoffFromPivNo);
					
			return new ModelAndView("payment/setoffManual" , "pivModel", pivModel);
		}
		
		/*********************************/
		private void setPivData(PivModel pivModel, PivDetail pivDetail)
		{
		
			String pivNo = pivDetail.getId().getPivNo();
			PivApplicant pivApplicant=pivModel.getPivApplicant();
			pivApplicant=PivApplicantDao.findByPivNo(pivNo,"PIV");
	
			String deptId=pivDetail.getId().getDeptId();
				
			List<PivAmountGrid> amountList = pivDao.getAcctCodesByPivNoWithoutZero(pivNo,"PIV");
		
			List<PivHistory> historyList=pivHistoryDao.findHistory(pivNo, deptId,"PIV");
	
			for(int i=0;i<historyList.size();i++){
				String status1 = historyList.get(i).getStatus();
				
				String statusDescription1 = masterDao.getStatusDescription(status1,"PIV");
				
				historyList.get(i).setStatus(statusDescription1);
				
			}
			BigDecimal grandTotal=pivDetail.getGrandTotal();
	
			double currency = grandTotal.doubleValue();
			String printCurrency=CurrencyToWords.convert(currency);
		
			pivModel.setPivDetail(pivDetail);
			pivModel.setPivApplicant(pivApplicant);
			pivModel.setAmountList(amountList);
			pivModel.setHistoryList(historyList);
			pivModel.setPrintCurrency(printCurrency);
			pivModel.setStatusDesc(masterDao.getStatusDescription(pivDetail.getStatus(),"PIV"));
			
	
			List<PivPayment> paymentList = pivPaymentDao.getPivPaymentToPIV(pivNo,"PIV");
			pivModel.setPaymentList(paymentList);
			
		}
	
}
