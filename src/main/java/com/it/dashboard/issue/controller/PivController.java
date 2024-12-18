package com.it.dashboard.issue.controller;

/*
 * author: Thanuksha Kaviratne
 */


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.it.dashboard.admin.domain.PivHistory;
import com.it.dashboard.admin.repo.PivHistoryDao;
import com.it.dashboard.admin.repo.SecurityDao;
import com.it.dashboard.issue.domain.PivAmount;
import com.it.dashboard.issue.domain.PivAmountGrid;
import com.it.dashboard.issue.domain.PivAmountPK;
import com.it.dashboard.issue.domain.PivApplicant;
import com.it.dashboard.issue.domain.PivApplicantPK;
import com.it.dashboard.issue.domain.PivDetail;
import com.it.dashboard.issue.domain.PivDetailPK;
import com.it.dashboard.issue.domain.PivModel;
import com.it.dashboard.issue.repo.PivAmountDao;
import com.it.dashboard.issue.repo.PivApplicantDao;
import com.it.dashboard.issue.repo.PivDao;
import com.it.dashboard.issue.repo.PivDetailDao;
import com.it.dashboard.master.repo.GldeptmDao;
import com.it.dashboard.master.repo.MasterDao;
import com.it.dashboard.master.domain.Bank;
import com.it.dashboard.master.domain.BankBranch;
import com.it.dashboard.master.domain.BankBranchPK;
import com.it.dashboard.master.domain.Gldeptm;
import com.it.dashboard.payment.domain.PivPayment;
import com.it.dashboard.payment.domain.PivPaymentPK;
import com.it.dashboard.payment.repo.PivPaymentDao;
import com.it.dashboard.util.common.CurrencyToWords;
import com.it.dashboard.util.common.SMSDataProjectCosting;
import com.it.dashboard.util.common.SendSMS;



@Controller
@SessionAttributes("loggedUser")
@RequestMapping(value = "/issue")
public class PivController {
	
	
	@Autowired
	private PivDao pivDao;
	@Autowired
	private PivDetailDao pivDetailDao;
	@Autowired
	private PivApplicantDao pivApplicantDao;
	@Autowired
	MasterDao masterDao;
	@Autowired
	private PivHistoryDao pivHistoryDao;
	@Autowired
	private PivPaymentDao pivPaymentDao;
	@Autowired
	private PivAmountDao pivAmountDao;
	@Autowired
	SecurityDao securityDao;
	@Autowired
	GldeptmDao gldeptmDao;
	
	
	
	
	@ExceptionHandler(NullPointerException.class)
	public String handleNullException(NullPointerException ex) {

		ModelAndView model = new ModelAndView("admin/index");
		model.addObject("errMsg", "this is null Exception.class");
		
		return "redirect:../admin/index";
	} 
	
	//method to retrieve new form to generate a new PIV
		@RequestMapping(value = "/testService", method = RequestMethod.GET)
		 public ModelAndView testService(HttpServletRequest request) {
				PivModel pivModel = new PivModel();
				
				return new ModelAndView("issue/pivForm_test" , "pivModel", pivModel);
		 }
		
	//method to retrieve new form to generate a new PIV
	@RequestMapping(value = "/generate", method = RequestMethod.GET)
	 public ModelAndView view(HttpServletRequest request) throws NullPointerException {
		request.getSession().setAttribute("headerName", "PIV Generate");
		String deptId = request.getSession().getAttribute("deptId").toString();
		//String activityList = (String)request.getSession().getAttribute("userActivity");
		List<String> activityList = (List<String>)request.getSession().getAttribute("userActivity");
		if(activityList.contains("N")){
			PivModel pivModel = new PivModel();
			//String deptId = (String)request.getSession().getAttribute("deptId");
			pivModel.setPivTypeList(getPivTypeList(deptId));
			//pivModel.setBankList(getBankList());
			pivModel.setBankList(masterDao.getBankListMap("PIV"));
			pivModel.setAgentList(masterDao.getAgentListMap("PIV"));
			pivModel.setMode("NEW");
			PivDetail pivDetail = new PivDetail();
			PivApplicant pivApplicant = new PivApplicant();
			Calendar calendar = Calendar.getInstance();
			pivDetail.setPivDate(calendar.getTime());
			calendar.add(Calendar.DATE,30);
			//pivDetail.setExpiryDate(calendar.getTime());
			pivDetail.setCurrencyCode("LKR");
			pivApplicant.setIdType("N");
			pivModel.setPivDetail(pivDetail);
			pivModel.setPivApplicant(pivApplicant);
			Gldeptm gldeptm = gldeptmDao.findByDeptId(deptId,"PIV");
			Map<String,String> deptIdMap = new LinkedHashMap<String,String>();
			deptIdMap.put(deptId, deptId+" - "+gldeptm.getDeptNm());
			pivModel.setDeptIdList(deptIdMap);
			
			return new ModelAndView("issue/pivForm" , "pivModel", pivModel);
		}
		else
		{
			return new ModelAndView("admin/noAccess");
		}	 
	}
	
	@RequestMapping(value = "/generateOthView", method = RequestMethod.GET)
	 public ModelAndView generateOthView(HttpServletRequest request) throws NullPointerException {
		request.getSession().setAttribute("headerName", "Other C/C PIV Generate");
		//String activityList = (String)request.getSession().getAttribute("userActivity");
		String deptId = request.getSession().getAttribute("deptId").toString();
		List<String> activityList = (List<String>)request.getSession().getAttribute("userActivity");
		if(activityList.contains("N")){
			PivModel pivModel = new PivModel();
			//String deptId = (String)request.getSession().getAttribute("deptId");
			pivModel.setPivTypeList(getPivTypeList(deptId));
			//pivModel.setBankList(getBankList());
			pivModel.setBankList(masterDao.getBankListMap("PIV"));
			pivModel.setAgentList(masterDao.getAgentListMap("PIV"));
			pivModel.setMode("NEW");
			PivDetail pivDetail = new PivDetail();
			PivApplicant pivApplicant = new PivApplicant();
			Calendar calendar = Calendar.getInstance();
			pivDetail.setPivDate(calendar.getTime());
			calendar.add(Calendar.DATE,30);
			//pivDetail.setExpiryDate(calendar.getTime());
			pivDetail.setCurrencyCode("LKR");
			pivApplicant.setIdType("N");
			pivModel.setPivDetail(pivDetail);
			pivModel.setPivApplicant(pivApplicant);
			pivModel.setIsMultipleDeptId("Y");
			String userName = (String)request.getSession().getAttribute("loggedUser");
			pivModel.setDeptIdList(gldeptmDao.getAuthDeptIdMap(userName,deptId));
			return new ModelAndView("issue/pivForm" , "pivModel", pivModel);
		}
		else
		{
			return new ModelAndView("admin/noAccess");
		}	 
	}
	
	//method to retrieve new form to generate a new PIV
		@RequestMapping(value = "/generateReturnCheque", method = RequestMethod.GET)
		 public ModelAndView generateReturnCheque(HttpServletRequest request) throws NullPointerException{
				PivModel pivModel = new PivModel();
				request.getSession().getAttribute("deptId").toString();
				@SuppressWarnings("rawtypes")
				List<String> chkList = pivPaymentDao.getChequeNoToStatus("H","PIV");
				//chkList.add("124145");
				//chkList.add("554714");
				pivModel.setChequeNoLst(chkList);
				//pivModel.setPivTypeList(getPivTypeList(deptId));
				//pivModel.setBankList(getBankList());
				pivModel.setBankList(masterDao.getBankListMap("PIV"));
				pivModel.setAgentList(masterDao.getAgentListMap("PIV"));
				pivModel.setMode("NEW");
				PivDetail pivDetail = new PivDetail();
				PivApplicant pivApplicant = new PivApplicant();
				Calendar calendar = Calendar.getInstance();
				pivDetail.setPivDate(calendar.getTime());
				calendar.add(Calendar.DATE,14);
				//pivDetail.setExpiryDate(calendar.getTime());
				pivDetail.setCurrencyCode("LKR");
				pivApplicant.setIdType("N");
				pivModel.setPivDetail(pivDetail);
				pivModel.setPivApplicant(pivApplicant);
				request.getSession().setAttribute("headerName", "PIV Generate");
				return new ModelAndView("issue/returnChequePivForm" , "pivModel", pivModel);
		 }
		
	
	
	
	
	
	
	//method to retrieve form to modify an existing PIV
	//reload PIV nos to be modified
	//Send for Validate button is also included in this form
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	 public ModelAndView modify(HttpServletRequest request) throws NullPointerException{
		request.getSession().setAttribute("headerName", "PIV Modify");
		List<String> activityList = (List<String>)request.getSession().getAttribute("userActivity");
		String deptId = request.getSession().getAttribute("deptId").toString();
		if(activityList.contains("N")){
			PivModel pivModel = new PivModel();
			//pivModel.setAmountList(getAcctList());
			//String deptId = (String)request.getSession().getAttribute("deptId");
			pivModel.setPivTypeList(getPivTypeList(deptId));
			pivModel.setMode("MODIFY");
			
			//String deptId = (String)request.getSession().getAttribute("deptId");
			List<String> pivNoList = pivDetailDao.getPivNoListByStatus(deptId,"N", "PIV","PIV");
			pivModel.setPivNoList(pivNoList);
			return new ModelAndView("issue/pivForm" , "pivModel", pivModel);
		}
		else
		{
			return new ModelAndView("admin/noAccess");
		}
	 }
	
	//method to retrieve form to modify an existing PIV
		//reload PIV nos to be modified
		//Send for Validate button is also included in this form
		@RequestMapping(value = "/modifyOthView", method = RequestMethod.GET)
		 public ModelAndView modifyOthView(HttpServletRequest request) {
			request.getSession().setAttribute("headerName", "Other C/C PIV Modify");
			String deptId = request.getSession().getAttribute("deptId").toString();
			List<String> activityList = (List<String>)request.getSession().getAttribute("userActivity");
			if(activityList.contains("N")){
				PivModel pivModel = new PivModel();
				//String deptId = (String)request.getSession().getAttribute("deptId");
				pivModel.setPivTypeList(getPivTypeList(deptId));
				pivModel.setMode("MODIFY");
				
				String loggedUser = (String)request.getSession().getAttribute("loggedUser");
				List<String> deptIdList = gldeptmDao.getAuthDeptIdList(loggedUser, deptId,"PIV");
				List<String> pivNoList = new ArrayList<String>();
				if(deptIdList!=null && deptIdList.size()>0)
				{
					pivNoList = pivDetailDao.getPivNoListByStatus(deptIdList,"N", "PIV","PIV");
				}
				pivModel.setPivNoList(pivNoList);
				pivModel.setIsMultipleDeptId("Y");
				return new ModelAndView("issue/pivForm" , "pivModel", pivModel);
			}
			else
			{
				return new ModelAndView("admin/noAccess");
			}
		 }
	
	@RequestMapping(value = "/discardPage", method = RequestMethod.GET)
	 public ModelAndView cancelPage(HttpServletRequest request) {
		request.getSession().setAttribute("headerName", "Discard New PIV");
		String deptId = request.getSession().getAttribute("deptId").toString();
		List<String> activityList = (List<String>)request.getSession().getAttribute("userActivity");
		if(activityList.contains("D")){
			PivModel pivModel = new PivModel();
			//pivModel.setAmountList(getAcctList());
			//String deptId = (String)request.getSession().getAttribute("deptId");
			pivModel.setPivTypeList(getPivTypeList(deptId));
			pivModel.setMode("CANCEL");
			
			//String deptId = (String)request.getSession().getAttribute("deptId");
			List<String> pivNoList = pivDetailDao.getPivNoListByStatus(deptId,"N", "PIV","PIV");
			pivModel.setPivNoList(pivNoList);
			return new ModelAndView("issue/pivForm" , "pivModel", pivModel);
		}
		else
		{
			return new ModelAndView("admin/noAccess");
		}
	 }
	
	@RequestMapping(value = "/discardOthView", method = RequestMethod.GET)
	 public ModelAndView discardOthView(HttpServletRequest request) {
		request.getSession().setAttribute("headerName", "Discard Other C/C New PIV");
		String deptId = request.getSession().getAttribute("deptId").toString();
		List<String> activityList = (List<String>)request.getSession().getAttribute("userActivity");
		if(activityList.contains("D")){
			PivModel pivModel = new PivModel();
			//pivModel.setAmountList(getAcctList());
			//String deptId = (String)request.getSession().getAttribute("deptId");
			pivModel.setPivTypeList(getPivTypeList(deptId));
			pivModel.setMode("CANCEL");
			pivModel.setIsMultipleDeptId("Y");
			//String deptId = (String)request.getSession().getAttribute("deptId");
			String loggedUser = (String)request.getSession().getAttribute("loggedUser");
			List<String> deptIdList = gldeptmDao.getAuthDeptIdList(loggedUser, deptId,"PIV");
			List<String> pivNoList = new ArrayList<String>();
			if(deptIdList!=null && deptIdList.size()>0)
			{
				pivNoList = pivDetailDao.getPivNoListByStatus(deptIdList,"N", "PIV","PIV");
			}
			pivModel.setPivNoList(pivNoList);
			return new ModelAndView("issue/pivForm" , "pivModel", pivModel);
		}
		else
		{
			return new ModelAndView("admin/noAccess");
		}
	 }
	
	@RequestMapping(value = "/cancelApprovalPage", method = RequestMethod.GET)
	 public ModelAndView cancelApprovalPage(HttpServletRequest request) {
		request.getSession().setAttribute("headerName", "Cancel PIV Approval");
		String deptId = request.getSession().getAttribute("deptId").toString();
		//List<String> activityList = (List<String>)request.getSession().getAttribute("userActivity");
		//if(activityList.contains("CA")){
			PivModel pivModel = new PivModel();
			//pivModel.setAmountList(getAcctList());
			//String deptId = (String)request.getSession().getAttribute("deptId");
			pivModel.setPivTypeList(getPivTypeList(deptId));
			pivModel.setMode("CANCEL_APPROVAL");
			
			String user = (String)request.getSession().getAttribute("loggedUser");
			List<String> pivNoList = pivDetailDao.getApprovedPivNoListByUser(user, deptId,"PIV");
			pivModel.setPivNoList(pivNoList);
			return new ModelAndView("issue/pivForm" , "pivModel", pivModel);
		/*}
		else
		{
			return new ModelAndView("admin/noAccess");
		}*/
	 }
	
	@RequestMapping(value = "/cancelApprovalOthView", method = RequestMethod.GET)
	 public ModelAndView cancelApprovalOthView(HttpServletRequest request) {
		request.getSession().setAttribute("headerName", "Cancel Other C/C PIV Approval");
		String deptId = request.getSession().getAttribute("deptId").toString();
		//List<String> activityList = (List<String>)request.getSession().getAttribute("userActivity");
		//if(activityList.contains("CA")){
			PivModel pivModel = new PivModel();
			//pivModel.setAmountList(getAcctList());
			//String deptId = (String)request.getSession().getAttribute("deptId");
			pivModel.setPivTypeList(getPivTypeList(deptId));
			pivModel.setMode("CANCEL_APPROVAL");
			pivModel.setIsMultipleDeptId("Y");
			String loggedUser = (String)request.getSession().getAttribute("loggedUser");
			List<String> deptIdList = gldeptmDao.getAuthDeptIdList(loggedUser, deptId,"PIV");
			if(deptIdList!=null && deptIdList.size()>0)
			{
				List<String> pivNoList = pivDetailDao.getApprovedPivNoListByUser(loggedUser, deptIdList,"PIV");
				pivModel.setPivNoList(pivNoList);
			}
			return new ModelAndView("issue/pivForm" , "pivModel", pivModel);
		/*}
		else
		{
			return new ModelAndView("admin/noAccess");
		}*/
	 }
	
	
	//method to retrieve form to validate existing PIV
	//reload PIV nos to be validated
	@RequestMapping(value = "/validate", method = RequestMethod.GET)
	public ModelAndView validate(HttpServletRequest request) {
		String deptId = request.getSession().getAttribute("deptId").toString();
		String userRole = (String)request.getSession().getAttribute("loggedUserRole");
		PivModel pivModel = new PivModel();
		pivModel.setPivTypeList(getPivTypeList(deptId));
		pivModel.setMode("VALIDATE");
		request.getSession().setAttribute("headerName", "PIV Validation");
		List<String> pivNoList = pivDetailDao.getAuthorisedPivNoListByStatus(userRole, deptId, "S","PIV");
		pivModel.setPivNoList(pivNoList);
		return new ModelAndView("issue/pivForm" , "pivModel", pivModel);
	 }
	
	//method to retrieve form to approve existing PIV
	//reload PIV nos to be approved
	@RequestMapping(value = "/approve", method = RequestMethod.GET)
	 public ModelAndView approve(HttpServletRequest request) {
		request.getSession().setAttribute("headerName", "PIV Approval");
		String deptId = request.getSession().getAttribute("deptId").toString();
		List<String> activityList = (List<String>)request.getSession().getAttribute("userActivity");
		if(activityList.contains("V")){
			//String deptId = (String)request.getSession().getAttribute("deptId");
			String userRole = (String)request.getSession().getAttribute("loggedUserRole");
			PivModel pivModel = new PivModel();
			pivModel.setPivTypeList(getPivTypeList(deptId));
			pivModel.setMode("APPROVE");
			
			List<String> pivNoList = pivDetailDao.getAuthorisedPivNoListByStatus(userRole, deptId, "V","PIV");
			pivModel.setPivNoList(pivNoList);
			return new ModelAndView("issue/pivForm" , "pivModel", pivModel);
		}
		else
		{
			return new ModelAndView("admin/noAccess");
		}
	 }
	
	//method to retrieve form to approve existing PIV
		//reload PIV nos to be approved
		@RequestMapping(value = "/approveOthView", method = RequestMethod.GET)
		 public ModelAndView approveOthView(HttpServletRequest request) {
			request.getSession().setAttribute("headerName", "Other C/C PIV Approval");
			String deptId = request.getSession().getAttribute("deptId").toString();
			List<String> activityList = (List<String>)request.getSession().getAttribute("userActivity");
			if(activityList.contains("V")){
				//String deptId = (String)request.getSession().getAttribute("deptId");
				String userRole = (String)request.getSession().getAttribute("loggedUserRole");
				PivModel pivModel = new PivModel();
				pivModel.setPivTypeList(getPivTypeList(deptId));
				pivModel.setMode("APPROVE");
				
				String loggedUser = (String)request.getSession().getAttribute("loggedUser");
				List<String> deptIdList = gldeptmDao.getAuthDeptIdList(loggedUser, deptId,"PIV");
				List<String> pivNoList = new ArrayList<String>();
				if (deptIdList!=null && deptIdList.size()>0)
					pivNoList = pivDetailDao.getAuthorisedPivNoListByStatus(userRole, deptIdList, "V","PIV");
				pivModel.setPivNoList(pivNoList);
				pivModel.setIsMultipleDeptId("Y");
				
				
				pivModel.setPivNoList(pivNoList);
				return new ModelAndView("issue/pivForm" , "pivModel", pivModel);
			}
			else
			{
				return new ModelAndView("admin/noAccess");
			}
		 }
		
	///////////////////edit by sandun///////////////
	@RequestMapping(value = "/reject", method = RequestMethod.GET)
	 public ModelAndView reject(HttpServletRequest request) {
		request.getSession().setAttribute("headerName", "Modify Rejected PIV ");
		String deptId = request.getSession().getAttribute("deptId").toString();
		List<String> activityList = (List<String>)request.getSession().getAttribute("userActivity");
		if(activityList.contains("N")){
			PivModel pivModel = new PivModel();
			//pivModel.setAmountList(getAcctList());
			//String deptId = (String)request.getSession().getAttribute("deptId");
			pivModel.setPivTypeList(getPivTypeList(deptId));
			pivModel.setMode("REJECT");
			
			//String deptId = (String)request.getSession().getAttribute("deptId");
			List<String> pivNoList = pivDetailDao.getPivNoListByStatus(deptId,"R", "PIV","PIV");
			
			pivModel.setPivNoList(pivNoList);
			return new ModelAndView("issue/pivForm" , "pivModel", pivModel);
		}
		else
		{
			return new ModelAndView("admin/noAccess");
		}
	 }
	
	
	@RequestMapping(value = "/rejectOthView", method = RequestMethod.GET)
	 public ModelAndView rejectOthView(HttpServletRequest request) {
		request.getSession().setAttribute("headerName", "Modify Rejected Other C/C PIV");
		String deptId = request.getSession().getAttribute("deptId").toString();
		List<String> activityList = (List<String>)request.getSession().getAttribute("userActivity");
		if(activityList.contains("N")){
			PivModel pivModel = new PivModel();
			//pivModel.setAmountList(getAcctList());
			//String deptId = (String)request.getSession().getAttribute("deptId");
			pivModel.setPivTypeList(getPivTypeList(deptId));
			pivModel.setMode("REJECT");
			pivModel.setIsMultipleDeptId("Y");
			String loggedUser = (String)request.getSession().getAttribute("loggedUser");
			List<String> deptIdList = gldeptmDao.getAuthDeptIdList(loggedUser, deptId,"PIV");
			List<String> pivNoList = new ArrayList<String>();
			if(deptIdList!=null && deptIdList.size()>0)
			{
				pivNoList = pivDetailDao.getPivNoListByStatus(deptIdList,"R", "PIV","PIV");
			}
			
			pivModel.setPivNoList(pivNoList);
			return new ModelAndView("issue/pivForm" , "pivModel", pivModel);
		}
		else
		{
			return new ModelAndView("admin/noAccess");
		}
	 }
	/////////////////////////
	
	
	//method to retrieve form to confirm existing PIV
	//reload PIV nos to be confirmed(paid)
	@SuppressWarnings("unused")
	@RequestMapping(value = "/confirm", method = RequestMethod.GET)
	 public ModelAndView confirm(HttpServletRequest request) {
		request.getSession().setAttribute("headerName", "PIV Confirmation");
		String deptId = request.getSession().getAttribute("deptId").toString();
		List<String> activityList = (List<String>)request.getSession().getAttribute("userActivity");
		if(activityList.contains("A")){
			//String deptId = (String)request.getSession().getAttribute("deptId");
			String userRole = (String)request.getSession().getAttribute("loggedUserRole");
			PivModel pivModel = new PivModel();
			//pivModel.setAmountList(getAcctList());
			pivModel.setPivTypeList(getPivTypeList(deptId));
			pivModel.setMode("CONFIRM");
			List<String> pivNoList = pivDetailDao.getPivNoListByStatus(deptId,"A", "PIV","PIV");
			pivModel.setPivNoList(pivNoList);
			
			
			//pivModel.setBankList(getBankList());
			pivModel.setBankList(masterDao.getBankListMap("PIV"));
			pivModel.setAgentList(masterDao.getAgentListMap("PIV"));
			return new ModelAndView("issue/pivConfirm" , "pivModel", pivModel);
		}
		else
		{
			return new ModelAndView("admin/noAccess");
		}
	 }
	
	
		
		
	// invoke when page submit buttons clicked
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/findPivForCheque",  method = RequestMethod.POST)
	public ModelAndView findPivForCheque(@ModelAttribute("pivModel") PivModel pivModel,@RequestParam String actionButton,HttpServletRequest request) 
	{
		String defDeptId = (String)request.getSession().getAttribute("deptId");
		String loggedUser = (String)request.getSession().getAttribute("loggedUser");
		try
		{
			String pivNo = pivModel.getPivDetail().getId().getPivNo();
			
			if(actionButton.equals("Add"))
			{
				String vatRegNo = pivModel.getPivApplicant().getVatRegNo();
				/*if(masterDao.isVatLiablePIV(pivModel.getPivDetail().getGltitlm().getTitleCd()) && (vatRegNo == null || vatRegNo.trim().length()==0))
				{
					pivModel.setMessage("Please enter VAT Reg. No.");
					pivModel.setMessageType("ERROR");
				}
				else
				{*/
					insertPiv(pivModel, defDeptId,loggedUser);
					request.getSession().setAttribute("headerName", "PIV Modify");
				//}
			}
			else if(pivNo.equals("-1"))
			{
				List<String> chkList = pivPaymentDao.getChequeNoToStatus("H","PIV");
				pivModel.setChequeNoLst(chkList);
				pivModel.setPivNoList(pivPaymentDao.getPivNoToChequeNo(pivModel.getChequeNo(),"H","PIV"));
				
			}
			else
			{
				NumberFormat nf = NumberFormat.getInstance();
				nf.setGroupingUsed(true);
				nf.setMaximumFractionDigits(2);
				nf.setMinimumFractionDigits(2);
				PivAmountGrid pag = new PivAmountGrid();
				//String pivNo = 
				List<String> chkList = pivPaymentDao.getChequeNoToStatus("H","PIV");
				pivModel.setChequeNoLst(chkList);
				pivModel.setPivNoList(pivPaymentDao.getPivNoToChequeNo(pivModel.getChequeNo(),"H","PIV"));
				String chequeNo = pivModel.getChequeNo();
				pag.setAcCd("L5910");
				pag.setAcNm("CANCELLED CHEQUES");
				PivPayment pivPayment = pivPaymentDao.findByPivCheque(pivNo, chequeNo,"PIV");
				pag.setAmount(nf.format(pivPayment.getPaidAmount()));
				List<PivAmountGrid> amountList = new ArrayList();
				amountList.add(pag);
				pivModel.setAmountList(amountList);
				pivModel.setPivTotal(nf.format(pivPayment.getPaidAmount()));
				PivApplicant pivApplicant = pivApplicantDao.findByPivNo(pivNo,"PIV");
				pivModel.setPivApplicant(pivApplicant);
			}
		
		}
		catch(NullPointerException e)
		{
			throw e;
		}
		catch(Exception e)
		{
			pivModel.setMessage("System Error.");
			pivModel.setMessageType("ERROR");
			e.printStackTrace();
		}
		return new ModelAndView("issue/returnChequePivForm" , "pivModel", pivModel);
	}
	
	
	
	
	// invoke when page submit buttons clicked
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/savePivDetail",  method = RequestMethod.POST)
	public ModelAndView savePiv(@ModelAttribute("pivModel") PivModel pivModel,@RequestParam String actionButton,HttpServletRequest request,Model model)   {
		
		String returnPage = "issue/pivForm";
		String defDeptId = request.getSession().getAttribute("deptId").toString();
		try
		{
			request.getSession().getAttribute("deptId").toString();
			String mode = pivModel.getMode();
			/*** edit by pasindu ***/
			String returnCheque = pivModel.getReturnCheque();
			/***********************/
			List<PivAmountGrid> amtList = pivModel.getAmountList();
			//PivDetailpivDetail = pivModel.getPivDetail();
			//ArrayList<PivAmount> pivAmtLst = new ArrayList<PivAmount>();
			
			
			
			String loggedUser = (String)request.getSession().getAttribute("loggedUser");
			
			NumberFormat nf = NumberFormat.getInstance();
			nf.setGroupingUsed(true);
			nf.setMaximumFractionDigits(2);
			nf.setMinimumFractionDigits(2);
			//Generate new piv
			
			
			
			if(mode.equals("NEW"))
			{
				String vatRegNo = pivModel.getPivApplicant().getVatRegNo();
				if(masterDao.isVatLiablePIV(pivModel.getPivDetail().getTitleCd(),"PIV") && (vatRegNo == null || vatRegNo.trim().length()==0))
				{
					String deptId = (String)request.getSession().getAttribute("deptId");
					pivModel.setMessage("Please enter VAT Reg. No.");
					pivModel.setMessageType("ERROR");
					Gldeptm gldeptm = gldeptmDao.findByDeptId(deptId,"PIV");
					Map<String,String> deptIdMap = new LinkedHashMap<String,String>();
					deptIdMap.put(deptId, deptId+" - "+gldeptm.getDeptNm());
					pivModel.setDeptIdList(deptIdMap);
					pivModel.setPivTypeList(getPivTypeList(deptId));
					model.addAttribute("IsVATLiable", "true");
					
				}
				else
				{
					insertPiv(pivModel, defDeptId,loggedUser);
					
					
					request.getSession().setAttribute("headerName", "PIV Modify");
				}
			}
			//modify form
			else if(mode.equals("MODIFY"))
			{
				
				String pivNo = pivModel.getPivDetail().getId().getPivNo();
				
				PivDetail pivDetail = pivDetailDao.findByPivNo(pivNo,"PIV");
				
				if(pivDetail!=null)
				{
					System.out.println("pivDetail actiyity "+pivDetail.getSysType());
					String preStatus = pivDetail.getStatus();
					
					PivApplicant pivApplicant = pivApplicantDao.findByPivNo(pivNo,"PIV");
					System.out.println("pivApplicant "+pivApplicant);
					List<PivAmountGrid> amountList = pivDao.getAcctCodesByPivNo(pivNo,"PIV");
					
					if(actionButton.contains("Modify"))
					{
						String vatRegNo = pivModel.getPivApplicant().getVatRegNo();
						if(masterDao.isVatLiablePIV(pivModel.getPivDetail().getTitleCd(),"PIV") && (vatRegNo == null || vatRegNo.trim().length()==0))
						{
							pivModel.setMessage("Please enter VAT Reg. No.");
							pivModel.setMessageType("ERROR");
							model.addAttribute("IsVATLiable", "true");
						}
						else
						{
							pivModel.setPivTypeList(getPivTypeList(defDeptId));
							updatePiv(pivDetail,pivApplicant,amtList,pivModel,loggedUser);
						
							List<String> pivNoList = new ArrayList();
							pivNoList.add(pivModel.getPivDetail().getId().getPivNo());
							pivModel.setPivNoList(pivNoList);
							pivModel.setMessage("PIV saved successfully.");
							pivModel.setMessageType("SUCCESS");
						}
						List<String> pivNoList = null;
						if(pivModel.getIsMultipleDeptId()!=null && pivModel.getIsMultipleDeptId().equals("Y"))
						{
							List<String> deptIdList = gldeptmDao.getAuthDeptIdList(loggedUser, defDeptId,"PIV");
							if(deptIdList!=null && deptIdList.size()>0)
								pivNoList = pivDetailDao.getPivNoListByStatus(deptIdList,"N", "PIV","PIV");
						}
						else
						{
							String deptId = (String)request.getSession().getAttribute("deptId");
							pivNoList = pivDetailDao.getPivNoListByStatus(deptId,"N", "PIV","PIV");
						}
						pivModel.setPivNoList(pivNoList);
					}
					//when "Send for Validate" button is clicked
					else if(actionButton.contains("Send for Approval"))
					{
						if(preStatus.equals("N"))
						{
							pivModel.setPivTypeList(getPivTypeList(defDeptId));
							pivDetail.setStatus("V");
							//??
							PivHistory pivHistory = new PivHistory();
							pivHistory.setDeptId(pivDetail.getId().getDeptId());
							pivHistory.setPivNo(pivNo);
							pivHistory.setStatus("V");
							pivHistory.setUserId(loggedUser);
							//updatePiv(pivDetail,pivApplicant,amtList,pivModel,loggedUser);
							pivDao.updatePivStatus(pivDetail,pivHistory,"PIV"); 
							
							//pivModel.setPivNoList(pivNoList);
							List<String> pivNoList = new ArrayList();
							pivNoList.add(pivDetail.getId().getPivNo());
							pivModel.setPivNoList(pivNoList);
							
							if(pivModel.getPivApplicant().getTelephoneNo()!=null && pivModel.getPivApplicant().getTelephoneNo().length()==10)
							{
								SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								Date today = Calendar.getInstance().getTime();        
								String reportDate = df.format(today);
							
								
								String amtStr = nf.format(pivDetail.getPivAmount());
								   
								String msg = "Your request (PIV No. "+pivDetail.getBankCheckNo()+") is in progress. Amount to be paid will be notified soon.";
								System.out.println("sms msg "+msg);
								System.out.println("piv amt "+pivDetail.getPivAmount());
								System.out.println("piv tele no "+pivApplicant.getTelephoneNo());
								
								SMSDataProjectCosting sms = new SMSDataProjectCosting();
								sms.setAcct_number(pivDetail.getBankCheckNo());
								sms.setAlias("CEB Info");
								sms.setApp_ref_key("PIV");
								//sms.setArea_code("514.00");
								sms.setAppkey("PIV");
								sms.setCost_cnter(defDeptId);
								//sms.setDivision("R4");
								sms.setExpr_date(reportDate);
								sms.setMessage(msg);
								sms.setPr_key(1);
								sms.setProc_date(reportDate);
								sms.setProc_flag("W");
								//sms.setProvince("WPS2");
								sms.setRecno(0);
								sms.setRef_id(pivNo);
								//sms.setSend_date(reportDate);
								sms.setShdl_date(reportDate);
								sms.setTele_no(pivApplicant.getTelephoneNo());
								sms.setAppkey("PIV");
					       
					       		List<SMSDataProjectCosting> smsList = new ArrayList<SMSDataProjectCosting>();
					       		smsList.add(sms);
				  	       	
					       		SendSMS smsObj = new SendSMS();
					       		smsObj.sendSMSWithDateAsString(smsList);
					       		
					       	}
							pivModel.setMessage("PIV sent for approval. Please use Print Button below to get print of the PIV");
							pivModel.setMessageType("SUCCESS");
							pivModel.setAmountList(null);
							pivModel.setPivDetail(null);
							pivModel.setPivApplicant(null);
							pivModel.setMode("PRINT");
							pivModel.setPivDetail(pivDetail);
							System.out.println("sending piv "+pivDetail.getId().getPivNo());
						}
					}
					//page submit for on change event when a PIV is selected from drop down
					else
					{
						
						pivModel.setPivDetail(pivDetail);
						pivModel.setPivApplicant(pivApplicant);
						pivModel.setAmountList(amountList);
						double pivTot = pivDetail.getGrandTotal().doubleValue();
						pivModel.setPivTotal(nf.format(pivTot));
						pivModel.setPivTypeList(getPivTypeList(defDeptId));
						
						if(masterDao.isVatLiablePIV(pivModel.getPivDetail().getTitleCd(),"PIV"))
								model.addAttribute("IsVATLiable", "true");
						
						List<String> pivNoList = null;
						if(pivModel.getIsMultipleDeptId()!=null && pivModel.getIsMultipleDeptId().equals("Y"))
						{
							List<String> deptIdList = gldeptmDao.getAuthDeptIdList(loggedUser, defDeptId,"PIV");
							if(deptIdList!=null && deptIdList.size()>0)
								pivNoList = pivDetailDao.getPivNoListByStatus(deptIdList,"N", "PIV","PIV");
						}
						else
						{
							String deptId = (String)request.getSession().getAttribute("deptId");
							pivNoList = pivDetailDao.getPivNoListByStatus(deptId,"N", "PIV","PIV");
						}
						pivModel.setPivNoList(pivNoList);
					}
					
				}
				
			}
			
			//////////EDIT BY SANDUN//////
			else if(mode.equals("REJECT"))
			{
				String pivNo = pivModel.getPivDetail().getId().getPivNo();
				PivDetail pivDetail = pivDetailDao.findByPivNo(pivNo,"PIV");
				PivApplicant pivApplicant = pivApplicantDao.findByPivNo(pivNo,"PIV");
				List<PivAmountGrid> amountList = pivDao.getAcctCodesByPivNo(pivNo,"PIV");
				if(actionButton.contains("Modify"))
				{
					List<String> activityList = (List<String>)request.getSession().getAttribute("userActivity");
					if(activityList.contains("N")){
						request.getSession().setAttribute("headerName", "PIV Modify");
						pivModel.setPivTypeList(getPivTypeList(defDeptId));
						updatePiv(pivDetail,pivApplicant,amtList,pivModel,loggedUser);
					
						List<String> pivNoList = new ArrayList();
						pivNoList.add(pivModel.getPivDetail().getId().getPivNo());
						pivModel.setPivNoList(pivNoList);
						pivModel.setMessage("Rejected PIV modified successfully.");
						pivModel.setMessageType("SUCCESS");
						pivModel.setMode("MODIFY");
						return new ModelAndView("issue/pivForm" , "pivModel", pivModel);
					}
					else
					{
						return new ModelAndView("admin/noAccess");
					}
				}
				
				else
				{  
					List<String> pivNoList = null;
					if(pivModel.getIsMultipleDeptId()!=null && pivModel.getIsMultipleDeptId().equals("Y"))
					{
						List<String> deptIdList = gldeptmDao.getAuthDeptIdList(loggedUser, defDeptId,"PIV");
						if(deptIdList!=null && deptIdList.size()>0)
							pivNoList = pivDetailDao.getPivNoListByStatus(deptIdList,"R", "PIV","PIV");
					}
					else
					{
						String deptId = (String)request.getSession().getAttribute("deptId");
						pivNoList = pivDetailDao.getPivNoListByStatus(deptId,"R", "PIV","PIV");
					}
					pivModel.setPivNoList(pivNoList);

					pivModel.setPivDetail(pivDetail);
					pivModel.setPivApplicant(pivApplicant);
					pivModel.setAmountList(amountList);
					double pivTot = pivDetail.getGrandTotal().doubleValue();
					pivModel.setPivTotal(nf.format(pivTot));
					pivModel.setPivTypeList(getPivTypeList(defDeptId));
					
				}
			}
			
			
			//validate form
			else if(mode.equals("VALIDATE"))
			{
				String pivNo = pivModel.getPivDetail().getId().getPivNo();
				PivDetail pivDetail = pivDetailDao.findByPivNo(pivNo,"PIV");
				PivApplicant pivApplicant = pivApplicantDao.findByPivNo(pivNo,"PIV");
				List<PivAmountGrid> amountList = pivDao.getAcctCodesByPivNo(pivNo,"PIV");
				//String loggedUser = (String)request.getSession().getAttribute("loggedUser");
				
				if(actionButton.contains("Validate"))
				{
					//pivDetailDao.updateStatus(pivNo, deptId, "V");	
					pivDetail.setStatus("V");
					
					PivHistory pivHistory = new PivHistory();
					pivHistory.setDeptId(pivDetail.getId().getDeptId());
					pivHistory.setPivNo(pivNo);
					pivHistory.setStatus("V");
					pivHistory.setUserId(loggedUser);
					
					pivDao.updatePivStatus(pivDetail,pivHistory,"PIV"); 
					pivModel.setMessage("PIV validated successfully.");
					pivModel.setMessageType("SUCCESS");
					pivModel.setAmountList(null);
					pivModel.setPivDetail(null);
					pivModel.setPivApplicant(null);
				}
				else if(actionButton.contains("Reject"))
				{
					
					Calendar calendar = Calendar.getInstance();
					
					pivDetail.setStatus("R");
					pivDetail.setRejectedBy(loggedUser);
					pivDetail.setRejectedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
					pivDetail.setRejectedReason(pivModel.getPivDetail().getRejectedReason());
					//pivDetailDao.update(pivDetail);	
					//pivDetail.setStatus("V");
					PivHistory pivHistory = new PivHistory();
					pivHistory.setDeptId(pivDetail.getId().getDeptId());
					pivHistory.setPivNo(pivNo);
					pivHistory.setStatus("R");
					pivHistory.setUserId(loggedUser);
					
					pivDao.updatePivStatus(pivDetail,pivHistory,"PIV");  
					pivModel.setMessage("PIV has been rejected.");
					pivModel.setMessageType("SUCCESS");
					pivModel.setAmountList(null);
					pivModel.setPivDetail(null);
					pivModel.setPivApplicant(null);
				}
				else
				{
					pivModel.setPivDetail(pivDetail);
					pivModel.setPivApplicant(pivApplicant);
					pivModel.setAmountList(amountList);
					pivModel.setPivTypeList(getPivTypeList(defDeptId));
					
				}
				String userRole = (String)request.getSession().getAttribute("loggedUserRole");
				String deptId = (String)request.getSession().getAttribute("deptId");
				List<String> pivNoList = pivDetailDao.getAuthorisedPivNoListByStatus(userRole, deptId, "S","PIV");
				pivModel.setPivNoList(pivNoList);
				
					
			}
			//validate form
			else if(mode.equals("APPROVE"))
			{
				String pivNo = pivModel.getPivDetail().getId().getPivNo();
				PivDetail pivDetail = pivDetailDao.findByPivNo(pivNo,"PIV");
				String preStatus = pivDetail.getStatus();
				PivApplicant pivApplicant = pivApplicantDao.findByPivNo(pivNo,"PIV");
				List<PivAmountGrid> amountList = pivDao.getAcctCodesByPivNo(pivNo,"PIV");
				//String loggedUser = (String)request.getSession().getAttribute("loggedUser");
				if(actionButton.contains("Approve"))
				{
					//pivDetailDao.updateStatus(pivNo, deptId, "A");	
					if(preStatus.equals("V"))
					{
						pivDetail.setStatus("A");
						PivHistory pivHistory = new PivHistory();
						pivHistory.setDeptId(pivDetail.getId().getDeptId());
						pivHistory.setPivNo(pivNo);
						pivHistory.setStatus("A");
						pivHistory.setUserId(loggedUser);
						pivHistory.setDescription("Approved");
						
						pivDetail.setApprovedBy(loggedUser);
						pivDetail.setApprovedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
						
						pivDao.updatePivStatus(pivDetail,pivHistory,"PIV"); 
						
						
						try
						{
							System.out.println("check for sending sms");
							if(pivModel.getPivApplicant().getTelephoneNo()!=null && pivModel.getPivApplicant().getTelephoneNo().length()==10)
							{
								System.out.println("start sending sms");
								SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								Date today = Calendar.getInstance().getTime();        
								String reportDate = df.format(today);
							
								
								String amtStr = nf.format(pivDetail.getPivAmount());
								   
								String msg = "Please pay PIV "+pivDetail.getBankCheckNo()+" amount Rs."+amtStr+" to any CEB POS counter or any Peoples Bank Branch. For online payments https://payment.ceb.lk/pivpay";
								System.out.println("sms msg "+msg);
								System.out.println("piv amt "+pivDetail.getPivAmount());
								System.out.println("piv tele no "+pivApplicant.getTelephoneNo());
								
								SMSDataProjectCosting sms = new SMSDataProjectCosting();
								sms.setAcct_number(pivDetail.getBankCheckNo());
								sms.setAlias("CEB Info");
								sms.setApp_ref_key("PIV");
								//sms.setArea_code("514.00");
								sms.setAppkey("PIV");
								sms.setCost_cnter(defDeptId);
								//sms.setDivision("R4");
								sms.setExpr_date(reportDate);
								sms.setMessage(msg);
								sms.setPr_key(1);
								sms.setProc_date(reportDate);
								sms.setProc_flag("W");
								//sms.setProvince("WPS2");
								sms.setRecno(0);
								sms.setRef_id(pivNo);
								//sms.setSend_date(reportDate);
								sms.setShdl_date(reportDate);
								sms.setTele_no(pivApplicant.getTelephoneNo());
								sms.setAppkey("PIV");
					       
					       		List<SMSDataProjectCosting> smsList = new ArrayList<SMSDataProjectCosting>();
					       		smsList.add(sms);
				  	       	
					       		SendSMS smsObj = new SendSMS();
					       		smsObj.sendSMSWithDateAsString(smsList);
					       		
					       		System.out.println("end sending sms");
					       		
					       	}
							pivModel.setMessage("PIV approved successfully.");
							pivModel.setMessageType("SUCCESS");
							pivModel.setAmountList(null);
							pivModel.setPivDetail(null);
							pivModel.setPivApplicant(null);
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
				}
				else if(actionButton.contains("Reject"))
				{
					Calendar calendar = Calendar.getInstance();
					pivDetail.setStatus("R");
					pivDetail.setRejectedBy(loggedUser);
					pivDetail.setRejectedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
					pivDetail.setRejectedReason(pivModel.getPivDetail().getRejectedReason());
					//pivDetailDao.update(pivDetail);	
					//pivDetail.setStatus("V");
					PivHistory pivHistory = new PivHistory();
					pivHistory.setDeptId(pivDetail.getId().getDeptId());
					pivHistory.setPivNo(pivNo);
					pivHistory.setStatus("R");
					pivHistory.setUserId(loggedUser);
					pivHistory.setDescription("Rejected.");
					
					pivDao.updatePivStatus(pivDetail,pivHistory,"PIV"); 
					
					pivModel.setMessage("PIV has been rejected.");
					pivModel.setMessageType("SUCCESS");
					pivModel.setAmountList(null);
					pivModel.setPivDetail(null);
					pivModel.setPivApplicant(null);
				}
				else
				{
					pivModel.setPivDetail(pivDetail);
					pivModel.setPivApplicant(pivApplicant);
					pivModel.setAmountList(amountList);
					pivModel.setPivTypeList(getPivTypeList(defDeptId));
					double pivTot = pivDetail.getGrandTotal().doubleValue();
					String pivTotStr = nf.format(pivTot);
					pivModel.setPivTotal(pivTotStr);
				}
				List<String> pivNoList = null;
				String userRole = (String)request.getSession().getAttribute("loggedUserRole");
				
				
				if(pivModel.getIsMultipleDeptId()!=null && pivModel.getIsMultipleDeptId().equals("Y"))
				{
					List<String> deptIdList = gldeptmDao.getAuthDeptIdList(loggedUser, defDeptId,"PIV");
				
					pivNoList = pivDetailDao.getAuthorisedPivNoListByStatus(userRole, deptIdList, "V","PIV");
				}
				else
				{
					String deptId = (String)request.getSession().getAttribute("deptId");
					pivNoList = pivDetailDao.getAuthorisedPivNoListByStatus(userRole, deptId, "V","PIV");
				}
				pivModel.setPivNoList(pivNoList);
			}
			//validate form
			else if(mode.equals("CONFIRM"))
			{
				returnPage = "issue/pivConfirm";
				
				String deptId = pivModel.getPivDetail().getId().getDeptId();
				
				//confirm button clicking
				if(actionButton.contains("Confirm"))
				{
					String pivNo = pivModel.getPivDetail().getId().getPivNo();
					Date paidDate=pivModel.getPivDetail().getPaidDate();
					PivDetail pivDetail = pivDetailDao.findByPivNo(pivNo,"PIV");
					String paidAgent=pivModel.getPivDetail().getPaidAgent();
					String paidBranch=pivModel.getPivDetail().getPaidBranch();
					pivModel.setAgentList(masterDao.getAgentListMap("PIV"));
					pivModel.setAgentBranchList(masterDao.getAgentBranchListMap(paidAgent,"PIV"));
					if(paidDate == null)
					{
						pivModel.setMessage("Please select Date Paid.");
						pivModel.setMessageType("ERROR");
						return new ModelAndView(returnPage, "pivModel", pivModel);
					}
					else if(paidAgent.equals("-1"))
					{
						pivModel.setMessage("Please select Paid Agent.");
						pivModel.setMessageType("ERROR");
						return new ModelAndView(returnPage, "pivModel", pivModel);
					}
					else if(paidBranch.equals("-1"))
					{
						pivModel.setMessage("Please select Paid Agent Branch.");
						pivModel.setMessageType("ERROR");
						return new ModelAndView(returnPage, "pivModel", pivModel);
					}
					pivModel.setPivTypeList(getPivTypeList(defDeptId));
					
					StringTokenizer st = new StringTokenizer(paidBranch,"###");
					paidBranch = st.nextToken();
					String paidDeptId = st.nextToken();
					pivDetail.setPaidAgent(paidAgent);
					pivDetail.setPaidBranch(paidBranch);
					if(paidAgent.equals("7135"))
						pivDetail.setPaidDeptId("000.00");
					else
						pivDetail.setPaidDeptId(paidDeptId);
					pivDetail.setPaidDate(paidDate);
					pivDetail.setPaidTime(new Timestamp(paidDate.getTime()));
					pivDetail.setConfirmedBy(loggedUser);
					pivDetail.setBankPaidDate(paidDate);
					pivDetail.setBankStatus("P");
					Calendar calendar = Calendar.getInstance();
					SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
					String confirmedTime = sdf.format(calendar.getTime()) ;
					pivDetail.setConfirmedDate(calendar.getTime());
					pivDetail.setConfirmedTime(confirmedTime);
					PivHistory pivHistory = new PivHistory();
					if(pivPaymentDao.isChequePayment(deptId, pivNo,"PIV"))
					{
						pivDetail.setStatus("Q");
						pivHistory.setStatus("Q");
					}
						
					else
					{
						pivDetail.setStatus("P");
						pivHistory.setStatus("P");
					}
					
					pivHistory.setDeptId(pivDetail.getId().getDeptId());
					pivHistory.setPivNo(pivNo);
					
					pivHistory.setUserId(loggedUser);
					pivHistory.setDescription("Payment Confirmed.");
					
					
					double paidAmt = pivPaymentDao.getPaidAmount(pivNo,"PIV");
					double pivAmt = pivDetail.getPivAmount().doubleValue();
					
						
						pivDao.updatePivStatus(pivDetail,pivHistory,"PIV"); 
						pivModel.setMessage("PIV confirmed successfully.");
						pivModel.setMessageType("SUCCESS");
						
						clearForm(pivModel, pivNo);
						
					
				}
				//Find button click
				else{
					String pivNo = pivModel.getPivDetail().getId().getPivNo();
					PivDetail pd = clearForm(pivModel, pivNo);
					pivModel.setPivDetail(pd);
					Date paidDate=pivModel.getPivDetail().getPaidDate();
					PivDetail pivDetail = pivDetailDao.findByPivNo(pivNo,"PIV");
					if (pivDetail==null)
					{
						pivModel.setMessage("Sorry, PIV does not exist.");
						pivModel.setMessageType("ERROR");
						//pivModel.setPivDetail(null);
						return new ModelAndView(returnPage, "pivModel", pivModel);
					}
					/*List<String> deptList = securityDao.getAuthorizedCostCenters(loggedUser);
					String issuedDeptId = pivDetail.getId().getDeptId();
					if(!deptList.contains(issuedDeptId))
					{
						pivModel.setMessage("Sorry, You dont't have authority to confirm PIVs issued by "+issuedDeptId);
						pivModel.setMessageType("ERROR");
						return new ModelAndView(returnPage, "pivModel", pivModel);
					}*/
					String sysType = pivDetail.getSysType();
					String pivStatus = pivDetail.getStatus();
					if(pivStatus.equals("P"))
					{
						pivModel.setMessage("Sorry,PIV is already confirmed.");
						pivModel.setMessageType("ERROR");
						return new ModelAndView(returnPage, "pivModel", pivModel);
						
					}
					if(sysType.equals("PIV") )
					{
						if(!pivStatus.equals("A"))
						{
							pivModel.setMessage("Sorry, You cannot confirm this PIV");
							pivModel.setMessageType("ERROR");
							return new ModelAndView(returnPage, "pivModel", pivModel);
						}
					}
					else
					{
						if(!pivStatus.equals("A") && !pivStatus.equals("N"))
						{
							pivModel.setMessage("Sorry, You cannot confirm this PIV");
							pivModel.setMessageType("ERROR");
							//pivModel.setPivDetail(null);
							return new ModelAndView(returnPage, "pivModel", pivModel);
						}
					}
					String paidAgent=pivModel.getPivDetail().getPaidAgent();
					String paidBranch=pivModel.getPivDetail().getPaidBranch();
					
					PivApplicant pivApplicant = pivApplicantDao.findByPivNo(pivNo,"PIV");
					List<PivAmountGrid> amountList = pivDao.getAcctCodesByPivNo(pivNo,"PIV");
					//Bank Bank=new Bank();
					//List<String> bblist=bankDao.findAllAgents();
					pivModel.setBankList(masterDao.getBankListMap("PIV"));
					pivModel.setAgentList(masterDao.getAgentListMap("PIV"));
					pivModel.setAgentBranchList(masterDao.getAgentBranchListMap(paidAgent,"PIV"));
					//pivModel.setPivDetail(pivDetail);
					pivModel.setPivApplicant(pivApplicant);
					pivModel.setAmountList(amountList);
					
					pivModel.setPivTypeList(getPivTypeList(defDeptId));
					pivModel.setPivDetail(pivDetail);
					pivModel.setPivApplicant(pivApplicant);
					pivModel.setAmountList(amountList);
					pivModel.setBank(null);
					double pivTot = pivDetail.getGrandTotal().doubleValue();
					String pivTotStr = nf.format(pivTot);
					pivModel.setPivTotal(pivTotStr);
					//pivModel.setBankList(getBankList());
					pivModel.setBankList(masterDao.getBankListMap("PIV"));
					pivModel.setAgentList(masterDao.getAgentListMap("PIV"));
					
					List<PivPayment> paymentList = pivPaymentDao.getPivPaymentToPIV(pivNo,"PIV");
					
					pivModel.setPaymentList(paymentList);
				}
				List<String> pivNoList = null;
				if(pivModel.getIsMultipleDeptId()!=null && pivModel.getIsMultipleDeptId().equals("Y"))
				{
					List<String> deptIdList = gldeptmDao.getAuthDeptIdList(loggedUser, defDeptId,"PIV");
					if(deptIdList!=null && deptIdList.size()>0)
						pivNoList = pivDetailDao.getPivNoListByStatus(deptIdList,"A", "PIV","PIV");
				}
				else
				{
					pivNoList = pivDetailDao.getPivNoListByStatus(deptId,"A", "PIV","PIV");
				}
				
				
				pivModel.setPivNoList(pivNoList);
				
			
			}
			else if(mode.equals("CANCEL"))
			{
				String pivNo = pivModel.getPivDetail().getId().getPivNo();
				
				PivDetail pivDetail = pivDetailDao.findByPivNo(pivNo,"PIV");
				PivApplicant pivApplicant = pivApplicantDao.findByPivNo(pivNo,"PIV");
				List<PivAmountGrid> amountList = pivDao.getAcctCodesByPivNo(pivNo,"PIV");
			
				if(actionButton.contains("Discard"))
				{
					pivDetail.setStatus("D");
					pivDetail.setRejectedBy(loggedUser);
					pivDetail.setRejectedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
					pivDetail.setRejectedReason(pivModel.getPivDetail().getRejectedReason());
					//pivDetailDao.update(pivDetail);	
					//pivDetail.setStatus("V");
					PivHistory pivHistory = new PivHistory();
					pivHistory.setDeptId(pivDetail.getId().getDeptId());
					pivHistory.setPivNo(pivNo);
					pivHistory.setStatus("D");
					pivHistory.setUserId(loggedUser);
					pivHistory.setDescription("Cancelled.");
					
					pivDao.updatePivStatus(pivDetail,pivHistory,"PIV"); 
					
					pivModel.setMessage("PIV has been discarded.");
					pivModel.setMessageType("SUCCESS");
					pivModel.setAmountList(null);
					pivModel.setPivDetail(null);
					pivModel.setPivApplicant(null);
				}
				else
				{
					pivModel.setPivTypeList(getPivTypeList(defDeptId));
					pivModel.setPivDetail(pivDetail);
					pivModel.setPivApplicant(pivApplicant);
					pivModel.setAmountList(amountList);
					double pivTot = pivDetail.getGrandTotal().doubleValue();
					pivModel.setPivTotal(nf.format(pivTot));
					
				}
				List<String> pivNoList = null;
				if(pivModel.getIsMultipleDeptId()!=null && pivModel.getIsMultipleDeptId().equals("Y"))
				{
					List<String> deptIdList = gldeptmDao.getAuthDeptIdList(loggedUser, defDeptId,"PIV");
					if(deptIdList!=null && deptIdList.size()>0)
						pivNoList = pivDetailDao.getPivNoListByStatus(deptIdList,"N", "PIV","PIV");
				}
				else
				{
					String deptId = (String)request.getSession().getAttribute("deptId");
					pivNoList = pivDetailDao.getPivNoListByStatus(deptId,"N", "PIV","PIV");
				}
				
				pivModel.setPivNoList(pivNoList);
			}
			else if(mode.equals("CANCEL_APPROVAL"))
			{
				String pivNo = pivModel.getPivDetail().getId().getPivNo();
				PivDetail pivDetail = pivDetailDao.findByPivNo(pivNo,"PIV");
				PivApplicant pivApplicant = pivApplicantDao.findByPivNo(pivNo,"PIV");
				List<PivAmountGrid> amountList = pivDao.getAcctCodesByPivNo(pivNo,"PIV");
			
				if(actionButton.contains("Cancel"))
				{
					pivDetail.setStatus("N");
					pivDetail.setRejectedBy(loggedUser);
					pivDetail.setRejectedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
					pivDetail.setRejectedReason(pivModel.getPivDetail().getRejectedReason());
					//pivDetailDao.update(pivDetail);	
					//pivDetail.setStatus("V");
					PivHistory pivHistory = new PivHistory();
					pivHistory.setDeptId(pivDetail.getId().getDeptId());
					pivHistory.setPivNo(pivNo);
					pivHistory.setStatus("N");
					pivHistory.setUserId(loggedUser);
					pivHistory.setDescription("Approval Cancelled.");
					
					pivDao.updatePivStatus(pivDetail,pivHistory,"PIV"); 
					
					pivModel.setMessage("PIV approval has been cancelled.");
					pivModel.setMessageType("SUCCESS");
					pivModel.setAmountList(null);
					pivModel.setPivDetail(null);
					pivModel.setPivApplicant(null);
				}
				else
				{
					pivModel.setPivTypeList(getPivTypeList(defDeptId));
					pivModel.setPivDetail(pivDetail);
					pivModel.setPivApplicant(pivApplicant);
					pivModel.setAmountList(amountList);
					double pivTot = pivDetail.getGrandTotal().doubleValue();
					pivModel.setPivTotal(nf.format(pivTot));
					
				}
				//String userRole = (String)request.getSession().getAttribute("loggedUserRole");
				
				//List<String> pivNoList = pivDetailDao.getAuthorisedPivNoListByStatus(userRole, deptId, "A");
				List<String> pivNoList = null;
				if(pivModel.getIsMultipleDeptId()!=null && pivModel.getIsMultipleDeptId().equals("Y"))
				{
					List<String> deptIdList = gldeptmDao.getAuthDeptIdList(loggedUser, defDeptId,"PIV");
					if(deptIdList!=null && deptIdList.size()>0)
						pivNoList = pivDetailDao.getApprovedPivNoListByUser(loggedUser, deptIdList,"PIV");
				}
				else
				{
					String deptId = (String)request.getSession().getAttribute("deptId");
					pivNoList = pivDetailDao.getApprovedPivNoListByUser(loggedUser, deptId,"PIV");
				}
				
				pivModel.setPivNoList(pivNoList);
			}
			
			
		}
		catch(NullPointerException e)
		{
			throw e;
		}
		catch(Exception e)
		{
			pivModel.setMessage("System Error.");
			pivModel.setMessageType("ERROR");
			e.printStackTrace();
		}
		return new ModelAndView(returnPage, "pivModel", pivModel);
		
	}
	
	
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    sdf.setLenient(true);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}
	
	//retrieve account codes for selected piv type; 
	//invoke onchange event of pivtype combo
	@RequestMapping(value = "/viewPivType", method = RequestMethod.POST)
	public String getPart(@ModelAttribute("pivModel") PivModel pivModel, Model model) {
		
		String pivType = pivModel.getPivDetail().getTitleCd();
		//pivModel.setAmountList(getAcctList(pivType));
		pivModel.setAmountList(masterDao.getAcctCodesByPivType(pivType,"PIV"));
		if(masterDao.isVatLiablePIV(pivType,"PIV"))
			model.addAttribute("IsVATLiable", "true");
		else
			model.addAttribute("IsVATLiable", "false");
		return "issue/pivAcctCodes";
	}
	////////////////////////////////////////////////////////////////////////////////////////////
	@RequestMapping(value = "/getBankBranch", method = RequestMethod.POST)
	public ModelAndView getBankBranch(@ModelAttribute("pivModel") PivModel pivModel) {
		String bankCode = pivModel.getChequeBank();
		Map<String,String> bankBranchList=masterDao.getBankBranchListMap(bankCode,"PIV");
		pivModel.setBankBranchList(bankBranchList);
		return new ModelAndView("issue/bankBranchCombo", "pivModel", pivModel);
	}
	
	@RequestMapping(value = "/viewGetAgentBranch", method = RequestMethod.POST)
	public ModelAndView viewGetAgentBranch(@ModelAttribute("pivModel") PivModel pivModel) {
		String paidAgent = pivModel.getPivDetail().getPaidAgent();
		Map<String, String> agentBranchList = masterDao.getAgentBranchListMap(paidAgent,"PIV");
		pivModel.setAgentBranchList(agentBranchList);
		return new ModelAndView("issue/agentBranchCombo", "pivModel", pivModel);
	}
	
	@RequestMapping(value = "/viewPaymentList", method = RequestMethod.POST)
	public ModelAndView viewPaymentList(@ModelAttribute("pivModel") PivModel pivModel,HttpServletRequest request) {
		
			String deptId = pivModel.getPivDetail().getId().getDeptId();
			String defDeptId = (String)request.getSession().getAttribute("loggedUser");
			String pivNo = pivModel.getPivDetail().getId().getPivNo();
			PivDetail pivDetail = pivDetailDao.findByPivNo(pivNo,"PIV");
			//add piv payment
			double paidAmtTmp = pivModel.getPaidAmount().doubleValue();
			double paidAmt = pivPaymentDao.getPaidAmount(pivNo,"PIV");
			double pivAmt = pivDetail.getPivAmount().doubleValue();
			if(paidAmtTmp+paidAmt<=pivAmt)
			{
				String loggedUser = (String)request.getSession().getAttribute("loggedUser");
				BankBranch bankBranch = null;
				if(pivModel.getChequeBank()!=null && pivModel.getChequeBank().trim().length()>0)
				{
					Bank bank = new Bank();
					bank.setBankCode(pivModel.getChequeBank());
				}
				if(pivModel.getChequeBranch()!=null && pivModel.getChequeBranch().trim().length()>0)
				{
					bankBranch = new BankBranch();
					BankBranchPK bankBranchPK = new BankBranchPK();
					bankBranchPK.setBankCode(pivModel.getChequeBank());
					bankBranchPK.setBranchCode(pivModel.getChequeBranch());
					bankBranch.setId(bankBranchPK);
				}
				
				PivPayment pivPayment =  new PivPayment();
				//pivPayment.setBankBranch(bankBranch);
				pivPayment.setChequeBankCode(pivModel.getChequeBank());
				pivPayment.setChequeBankBranch(pivModel.getChequeBranch());
				//pivPayment.setChequeBankBranch(pivModel.getChequeBranch());
				//pivPayment.setBank(bank);
				pivPayment.setChequeDate(pivModel.getChequeDate());
				pivPayment.setChequeNo(pivModel.getChequeNo());
				pivPayment.setPaidAmount(pivModel.getPaidAmount());
				pivPayment.setPaymentMode(pivModel.getPaymentMode());
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
				String addTime = sdf1.format(calendar.getTime()) ;
				pivPayment.setAddDate(calendar.getTime());
				pivPayment.setAddTime(addTime);
				pivPayment.setAddUser(loggedUser);
				
				PivPaymentPK pivPaymentPK =  new PivPaymentPK();
				pivPaymentPK.setDeptId(deptId);
				pivPaymentPK.setPivNo(pivNo);
				pivPayment.setId(pivPaymentPK);
				
				pivDetail.setPaidAmount(new BigDecimal(paidAmtTmp+paidAmt));
				pivModel.setPivDetail(pivDetail);
				pivDao.updatePivPayment(pivDetail, pivPayment,"PIV");
				
			}
			else
			{
				//error
			}
			List<PivPayment> paymentList = pivPaymentDao.getPivPaymentToPIV(pivNo,"PIV");
			pivModel.setPaymentList(paymentList);
			
		
		return new ModelAndView("issue/payments", "pivModel", pivModel);
	}
	
	@RequestMapping(value = "/deletePayment", method = RequestMethod.POST)
	public ModelAndView deletePayment(@ModelAttribute("pivModel") PivModel pivModel,HttpServletRequest request) {
		
			request.getSession().getAttribute("deptId").toString();
			String deptId = pivModel.getPivDetail().getId().getDeptId();
			String pivNo = pivModel.getPivDetail().getId().getPivNo();
			String serialNo = request.getParameter("SerialNo");
			Long sNo = new Long(serialNo);
			
			/*PivPaymentPK pivPaymentPK = new PivPaymentPK();
			pivPaymentPK.setDeptId(deptId);
			pivPaymentPK.setPivNo(pivNo);
			
			pivPaymentPK.setSeqNo(sNo.longValue());*/
			
			//PivPayment pivPayment = new PivPayment();
			//pivPayment.setId(pivPaymentPK);
			
			PivPayment pivPayment = pivPaymentDao.findById(deptId, pivNo, sNo.longValue(),"PIV");
			double deleteAmt = pivPayment.getPaidAmount().doubleValue();
			
			PivDetail pivDetail = pivDetailDao.findByPivNo(pivNo,"PIV");
			double paidAmt = 0;
			if(pivDetail.getPaidAmount()!=null)
				paidAmt = pivDetail.getPaidAmount().doubleValue();
			double newPaidAmt = paidAmt - deleteAmt;
			if(newPaidAmt<0)
				newPaidAmt = 0;
			pivDetail.setPaidAmount(new BigDecimal(newPaidAmt));
			
			pivDao.removePivPayment(pivDetail, pivPayment,"PIV");
			List<PivPayment> paymentList = pivPaymentDao.getPivPaymentToPIV(pivNo,"PIV");
			pivModel.setPaymentList(paymentList);
			pivModel.setPivDetail(pivDetail);
		
		return new ModelAndView("issue/payments", "pivModel", pivModel);
	}
	
	
	
	
	/*********** Private Methods ******************************/
	@SuppressWarnings("rawtypes")
	private Map<String,String> getPivTypeList(String deptId)
	{
		Map<String,String> pivTypeList = masterDao.getPivTypeKeyValue(deptId, "PIV");
		/*Map<String,String> pivTypeList = new LinkedHashMap<String,String>();
		List<Map> list = masterDao.getPivTypesToDeptId(deptId,"PIV");
		for(int i=0;i<list.size();i++)
		{
			Map m = list.get(i);
			Iterator s1 = m.values().iterator();
			int x=0;
			String key = "";
			String val = "";
			while(s1.hasNext())
			{
				Object obj = s1.next();
				
				if(x==0)
					val = obj.toString();
				else if (x==1)
					key = obj.toString();
				x++;
				
			}
			pivTypeList.put(key,val.trim());
			
		}*/
		return pivTypeList;
	}
	
	
	
	
	
	@SuppressWarnings("rawtypes")
	private Map<String,String> getBankBranchList(String bankCode)
	{
		Map<String,String> bankBranchList = new LinkedHashMap<String,String>();
		
		return bankBranchList;
	}
	
	
	
	
    private void updatePiv(PivDetail pivDetail,PivApplicant pivApplicant,List<PivAmountGrid> amtList, PivModel pivModel, String loggedUser)
    {
    	double total = 0;
    	String pivNo = pivDetail.getId().getPivNo();
    	String deptId = pivDetail.getId().getDeptId();
    	ArrayList<PivAmount> pivAmtLst = new ArrayList<PivAmount>();
		//PivDetail pivDetail = pivDetailDao.findByPivNo(pivModel.getPivDetail().getId().getPivNo());
		
		NumberFormat nf = NumberFormat.getInstance();
		nf.setGroupingUsed(true);
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		
		String pivTotStr = "0.00";
		
			if(null != amtList && amtList.size() > 0) {
				for (PivAmountGrid amountGrid : amtList) {
					double tmpAmt = 0;
					if(amountGrid.getAmount()!=null && amountGrid.getAmount().trim().length()>0)
					{
						//tmpAmt = amountGrid.getAmount().doubleValue();
						try
						{
						tmpAmt = nf.parse(amountGrid.getAmount()).doubleValue();
						}
						catch(ParseException e)
						{
							
						}
					}
					/*if(amountGrid.getAmount()!=null )
					{
					tmpAmt = amountGrid.getAmount().doubleValue();
					
					}*/
					total = total+tmpAmt;
											
					PivAmountPK pivPk = new  PivAmountPK();
					pivPk.setDeptId(deptId);
					pivPk.setAccountCode(amountGrid.getAcCd());
					pivPk.setPivNo(pivNo);
					
					PivAmount piv = pivAmountDao.findById(pivPk,"PIV");
					
					//PivAmount piv = new PivAmount();
					piv.setId(pivPk);
					piv.setAmount(new BigDecimal(tmpAmt));
					piv.setUpdUser(loggedUser);
					Timestamp updateTime=new Timestamp(new Date().getTime());
					piv.setUpdDate(updateTime);
					pivAmtLst.add(piv);
					
				}
				pivDetail.setGrandTotal(new BigDecimal(total).setScale(2, BigDecimal.ROUND_HALF_UP));
				pivDetail.setSubTotal(new BigDecimal(total).setScale(2, BigDecimal.ROUND_HALF_UP));
				pivDetail.setGrandTotal(new BigDecimal(total).setScale(2, BigDecimal.ROUND_HALF_UP));
				pivDetail.setPivAmount(new BigDecimal(total).setScale(2, BigDecimal.ROUND_HALF_UP));
				pivDetail.setSubTotal(new BigDecimal(total).setScale(2, BigDecimal.ROUND_HALF_UP));
				pivTotStr = nf.format(total);
				
			}
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			String preparedTime = sdf.format(calendar.getTime()) ;
			pivDetail.setUpdUser(loggedUser);
			pivDetail.setUpdDate(calendar.getTime());
			pivDetail.setUpdTime(preparedTime);
			pivDetail.setReferenceNo(pivModel.getPivDetail().getReferenceNo());
			pivDetail.setDescription(pivModel.getPivDetail().getDescription());
			BigDecimal pivAbt2Decimal = new BigDecimal(total).setScale(2, BigDecimal.ROUND_HALF_UP);
			String amtInWords = CurrencyToWords.convert(pivAbt2Decimal.doubleValue());
			pivDetail.setAmountInWords(amtInWords);
			
			pivApplicant.setIdType(pivModel.getPivApplicant().getIdType());
			pivApplicant.setIdNo(pivModel.getPivApplicant().getIdNo());
			pivApplicant.setName(pivModel.getPivApplicant().getName());
			pivApplicant.setAddress(pivModel.getPivApplicant().getAddress());
			pivApplicant.setCollectPersonId(pivModel.getPivApplicant().getCollectPersonId());
			pivApplicant.setCollectPersonName(pivModel.getPivApplicant().getCollectPersonName());
			pivApplicant.setEmail(pivModel.getPivApplicant().getEmail());
			pivApplicant.setUpdUser(loggedUser);
			pivApplicant.setVatRegNo(pivModel.getPivApplicant().getVatRegNo());
			Timestamp updateTime=new Timestamp(new Date().getTime());
			pivApplicant.setUpdDate(updateTime);
			pivApplicant.setTelephoneNo(pivModel.getPivApplicant().getTelephoneNo());
			
			pivModel.setPivDetail(pivDetail);
			pivModel.setPivApplicant(pivApplicant);
			pivModel.setPivTotal(pivTotStr);
			
		
			
		pivDao.updatePiv(pivDetail, pivApplicant, pivAmtLst,"PIV");
    }
    
  //controller method for the edit link in today pivs(pr)
  	@RequestMapping(value = "/modifyWhenClick", method = RequestMethod.GET)
  	 public ModelAndView modifyWhenClicked(HttpServletRequest request,@RequestParam("id") String id) {
  		ModelAndView mdl= null;
  			
  				NumberFormat nf = NumberFormat.getInstance();
  				nf.setGroupingUsed(true);
  				nf.setMaximumFractionDigits(2);
  				nf.setMinimumFractionDigits(2);
  				
	  			PivModel pivModel = new PivModel();
	  			
	  			pivModel.setMode("MODIFY");
	  			request.getSession().setAttribute("headerName", "PIV Modify");
	
	  			String deptId = request.getSession().getAttribute("deptId").toString();
	  			List<String> pivNoList = pivDetailDao.getPivNoListByStatus(deptId,"N", "PIV","PIV");
	  			pivModel.setPivNoList(pivNoList);
	  			
	  			String pivNo = id;
				PivDetail pivDetail = pivDetailDao.findByPivNo(pivNo,"PIV");
				PivApplicant pivApp = pivApplicantDao.findByPivNo(pivNo,"PIV");
				
				pivModel.setPivDetail(pivDetail);
				pivModel.setPivApplicant(pivApp);
				
				pivModel.setPivTypeList(getPivTypeList(deptId));
				String pivType = pivModel.getPivDetail().getTitleCd();
				List<PivAmountGrid> amountList = pivDao.getAcctCodesByPivNo(pivNo,"PIV");
				pivModel.setAmountList(amountList);
				
				double pivTot = pivDetail.getGrandTotal().doubleValue();
				pivModel.setPivTotal(nf.format(pivTot));
				
	  			mdl=new ModelAndView("issue/pivForm" , "pivModel", pivModel);
	  			
  			
  			return mdl ;
  	 }
  	
  	@RequestMapping(value = "/toBeApproved", method = RequestMethod.GET)
 	 public ModelAndView loadToBeApprovedPivs(HttpServletRequest request,@RequestParam("id") String id) {
  			ModelAndView mdl=null;
  			
  				NumberFormat nf = NumberFormat.getInstance();
  				nf.setGroupingUsed(true);
  				nf.setMaximumFractionDigits(2);
  				nf.setMinimumFractionDigits(2);
  				
	 			String deptId = request.getSession().getAttribute("deptId").toString();
				String userRole = (String)request.getSession().getAttribute("loggedUserRole");
				PivModel pivModel = new PivModel();
				pivModel.setPivTypeList(getPivTypeList(deptId));
				pivModel.setMode("APPROVE");
				request.getSession().setAttribute("headerName", "PIV Approval");
				List<String> pivNoList = pivDetailDao.getAuthorisedPivNoListByStatus(userRole, deptId, "V","PIV");
				pivModel.setPivNoList(pivNoList);
				
				String pivNo = id;
				PivDetail pivDetail = pivDetailDao.findByPivNo(pivNo,"PIV");
				PivApplicant pivApp = pivApplicantDao.findByPivNo(pivNo,"PIV");
				pivModel.setPivDetail(pivDetail);
				pivModel.setPivApplicant(pivApp);
				
				pivModel.setPivTypeList(getPivTypeList(deptId));
				String pivType = pivModel.getPivDetail().getTitleCd();
				List<PivAmountGrid> amountList = pivDao.getAcctCodesByPivNo(pivNo,"PIV");
				pivModel.setAmountList(amountList);
				
				double pivTot = pivDetail.getGrandTotal().doubleValue();
				pivModel.setPivTotal(nf.format(pivTot));
				
	 			mdl=new ModelAndView("issue/pivForm" , "pivModel", pivModel);
  			
 			return mdl ;
 	 }
  	@RequestMapping(value = "/toBeValidated", method = RequestMethod.GET)
	public ModelAndView loadToBeValidatedPivs(HttpServletRequest request,@RequestParam("id") String id) {
  		ModelAndView mdl=null;
  		
  			String deptId = request.getSession().getAttribute("deptId").toString();
			String userRole = (String)request.getSession().getAttribute("loggedUserRole");
			PivModel pivModel = new PivModel();
			pivModel.setPivTypeList(getPivTypeList(deptId));
			pivModel.setMode("VALIDATE");
			request.getSession().setAttribute("headerName", "PIV Validation");
			
			List<String> pivNoList = pivDetailDao.getAuthorisedPivNoListByStatus(userRole, deptId, "S","PIV");
		
			pivModel.setPivNoList(pivNoList);
			
			String pivNo = id;
			PivDetail pivDetail = pivDetailDao.findByPivNo(pivNo,"PIV");
			PivApplicant pivApp = pivApplicantDao.findByPivNo(pivNo,"PIV");
			pivModel.setPivDetail(pivDetail);
			pivModel.setPivApplicant(pivApp);
			
			pivModel.setPivTypeList(getPivTypeList(deptId));
			String pivType = pivModel.getPivDetail().getTitleCd();
			//pivModel.setAmountList(getAcctList(pivType));
			pivModel.setAmountList(masterDao.getAcctCodesByPivType(pivType,"PIV"));
			mdl=new ModelAndView("issue/pivForm" , "pivModel", pivModel);
		
			return mdl ;
	 }
  	
  	
  	/******** Edit by Pasindu ********/
	//method to retrieve new form to generate a new PIV
	
	@RequestMapping(value = "/returnChequeList", method = RequestMethod.GET)
	public String generateReturnCheque(HttpServletRequest request, Model model) {
		String deptId = request.getSession().getAttribute("deptId").toString();
		List<String> activityList = (List<String>)request.getSession().getAttribute("userActivity");
		if(activityList.contains("H"))
		{
			request.getSession().setAttribute("headerName", "Return Cheque Details");
			//String deptId = (String)request.getSession().getAttribute("deptId");
			String loggedUser = (String)request.getSession().getAttribute("loggedUser");
			List<String> deptList = securityDao.getAuthorizedCostCenters(loggedUser,"PIV");
			List<PivPayment> pivList = pivPaymentDao.getReturnPivList(deptList, "H","PIV");
			model.addAttribute("pivList", pivList);
			
			return "issue/returnChequePivForm";
		}
		else
		{
			return "admin/noAccess";
		}
	}
	/*********************************/
		
	
	/******** edit by pasindu ********/
	
	@RequestMapping(value = "/returnChequePiv", method = RequestMethod.GET)
	 public ModelAndView viewCheque(HttpServletRequest request) {
		PivModel pivModel = new PivModel();
		
			request.getSession().setAttribute("headerName", "Return Cheque PIV Generate");
					
			List<String> activityList = (List<String>)request.getSession().getAttribute("userActivity");
			
			String deptId = request.getSession().getAttribute("deptId").toString();
			String loggedUser = (String)request.getSession().getAttribute("loggedUser");
			String pivNo = request.getParameter("pivNo");
			String returnChequeNo = request.getParameter("chequeNo");
			
			PivDetail pivDetail = pivDetailDao.findByPivNo(pivNo,"PIV");
			pivDetail.setOtherPivRef(pivNo);
			PivApplicant pivApplicant = pivApplicantDao.findByPivNo(pivNo,"PIV");
			List<PivAmountGrid> amountList = pivDao.getAcctCodesByPivNo(pivNo,"PIV");
			//PivAmountGrid pag = (PivAmountGrid)amountList.get(1);
			
			pivModel.setMode("NEW");
			Map<String,String> deptIdMap = new LinkedHashMap<String,String>();
			Gldeptm gldeptm = gldeptmDao.findByDeptId(deptId,"PIV");
			deptIdMap.put(pivDetail.getId().getDeptId(), pivDetail.getId().getDeptId()+" - "+gldeptm.getDeptNm());
			pivModel.setDeptIdList(deptIdMap);
	 
			pivModel.setPivTypeList(getPivTypeList(deptId));
			pivModel.setPivDetail(pivDetail);
			pivModel.setPivApplicant(pivApplicant);
			pivModel.setAmountList(amountList);
			pivModel.setReturnChequeNo(returnChequeNo);
			pivModel.setReturnCheque("YES");
			
			NumberFormat nf = NumberFormat.getInstance();
			nf.setGroupingUsed(true);
			nf.setMaximumFractionDigits(2);
			nf.setMinimumFractionDigits(2);
			
			String pivTotal = nf.format(pivDetail.getPivAmount().doubleValue());
			pivModel.setPivTotal(pivTotal);
		

		return new ModelAndView("issue/pivForm" , "pivModel", pivModel);
		
	 }
	
	/***********************************/
	
  	private void insertPiv(PivModel pivModel, String defDeptId, String loggedUser) throws Exception
  	{
  		NumberFormat nf = NumberFormat.getInstance();
		nf.setGroupingUsed(true);
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		
		
		String deptId = pivModel.getPivDetail().getId().getDeptId();
  		List<PivAmountGrid> amtList = pivModel.getAmountList();
		//PivDetailpivDetail = pivModel.getPivDetail();
		ArrayList<PivAmount> pivAmtLst = new ArrayList<PivAmount>();
		
		String pivTotStr = "0.00";
  		double total = 0;
		if(null != amtList && amtList.size() > 0) {
			int i=0;
			PivAmount firstPivAmount = new PivAmount();
			double firstAmt = 0;
			for (PivAmountGrid amountGrid : amtList) {
				double tmpAmt = 0;
				PivAmountPK pivPk = new  PivAmountPK();
				pivPk.setDeptId(deptId);
				pivPk.setAccountCode(amountGrid.getAcCd().trim());
				PivAmount piv = new PivAmount();
				piv.setId(pivPk);
				piv.setAmount(new BigDecimal(0));
				piv.setAddUser(loggedUser);
				Timestamp updateTime=new Timestamp(new Date().getTime());
				piv.setAddDate(updateTime);
				piv.setTaxPercent(new BigDecimal(amountGrid.getTaxValue()));
				piv.setSortKey(amountGrid.getSortKey());
				
				if(amountGrid.getAmount()!=null)
				{
					if(amountGrid.getAmount()!=null && amountGrid.getAmount().trim().length()>0)
					{
						try{
						tmpAmt = nf.parse(amountGrid.getAmount()).doubleValue();
						}catch(ParseException e){}
					}
					piv.setAmount(new BigDecimal(tmpAmt));
					total = total+tmpAmt;
					
					if(i==0)
					{
						firstPivAmount = piv;
						firstAmt = tmpAmt;
					}
					else
					{
						pivAmtLst.add(piv);
					}
					if(amountGrid.getAcCd().trim().indexOf("L5221")!=-1)
					{
						piv.setDisplayAmount(new BigDecimal(0));
						firstAmt = firstAmt+tmpAmt;
					}
					else
					{
						piv.setDisplayAmount(new BigDecimal(tmpAmt));
					}
					i++;
				}
			
			}
			firstPivAmount.setDisplayAmount(new BigDecimal(firstAmt));
			pivAmtLst.add(firstPivAmount);
			BigDecimal pivAbt2Decimal = new BigDecimal(total).setScale(2, BigDecimal.ROUND_HALF_UP);
			String amtInWords = CurrencyToWords.convert(pivAbt2Decimal.doubleValue());
			pivModel.getPivDetail().setGrandTotal(pivAbt2Decimal);
			pivModel.getPivDetail().setSubTotal(pivAbt2Decimal);
			pivModel.getPivDetail().setGrandTotal(pivAbt2Decimal);
			pivModel.getPivDetail().setPivAmount(pivAbt2Decimal);
			pivModel.getPivDetail().setSubTotal(pivAbt2Decimal);
			pivModel.getPivDetail().setSysType("PIV");
			pivTotStr = nf.format(total);
			pivModel.getPivDetail().setAmountInWords(amtInWords);
			
			
		}
		Calendar calendar = Calendar.getInstance();
		//insert piv data
		PivDetailPK pivDetailPK = new PivDetailPK();
		pivDetailPK.setDeptId(deptId);
		
	
		pivModel.getPivDetail().setId(pivDetailPK);
		pivModel.getPivDetail().setIdNo("000000000y");
		pivModel.getPivDetail().setPivDate(new Date());
		pivModel.getPivDetail().setIssuedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		
		
		Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
		String preparedTime = sdf1.format(calendar.getTime()) ;
		pivModel.getPivDetail().setPreparedBy(loggedUser);
		pivModel.getPivDetail().setAddUser(loggedUser);
		pivModel.getPivDetail().setAddDate(calendar.getTime());
		pivModel.getPivDetail().setAddTime(preparedTime);
		pivModel.getPivDetail().setPreparedDate(timestamp);
		//PivActivity  pivActivity = new PivActivity();
		//pivActivity.setActivityCode("N");
		pivModel.getPivDetail().setStatus("N");
		pivModel.getPivDetail().setEnteredTime(timestamp);
		//pivModel.getPivDetail().setReferenceType(pivModel.getPivDetail().getTitleCd());
		pivModel.getPivDetail().setPivSeqNo(new BigDecimal(1));
		String pivType = pivModel.getPivDetail().getTitleCd();
		String refType = pivType.substring(pivType.length()-3);
		pivModel.getPivDetail().setReferenceType(refType);
		PivApplicantPK pivAppPk = new PivApplicantPK();
		pivAppPk.setDeptId(deptId);
	
	
		pivModel.getPivApplicant().setId(pivAppPk);
		pivModel.getPivApplicant().setAddUser(loggedUser);
		Timestamp updateTime=new Timestamp(new Date().getTime());
		pivModel.getPivApplicant().setAddDate(updateTime);
	
	
/******** edit by pasindu ********/
		
		String returnCheque = pivModel.getReturnCheque();
		String newNo = null;
		if(returnCheque.equals("YES")){
			
			PivHistory pivHistory = new PivHistory();
			pivHistory.setDeptId(deptId);
			String refPivNo = pivModel.getPivDetail().getOtherPivRef();

			pivHistory.setPivNo(refPivNo);
			pivHistory.setStatus("HN");
			pivHistory.setDescription("Return Cheque PIV Generated");
			pivHistory.setUserId(pivModel.getPivDetail().getPreparedBy());
			String status = pivHistory.getStatus();
			pivHistory.setUpdateTime(updateTime);
			
			String returnChequeNo = pivModel.getReturnChequeNo();
			
			newNo = pivDao.insertPiv(pivModel.getPivDetail(), pivModel.getPivApplicant(), pivAmtLst, pivHistory, returnChequeNo,"PIV");
		}
		else{
			newNo = pivDao.insertPiv(pivModel.getPivDetail(), pivModel.getPivApplicant(), pivAmtLst,"PIV");
		}
		/*******************************/
	
	
		pivModel.setMode("MODIFY");
		
		StringTokenizer st = new StringTokenizer(newNo,"###");
		String newPivNo = st.nextToken();
		String bankRefNo = st.nextToken();
		pivModel.getPivDetail().getId().setPivNo(newPivNo);
		pivModel.getPivDetail().setBankCheckNo(bankRefNo);
		
		List<String> pivNoList = new ArrayList<String>();
		pivNoList.add(newPivNo);
		pivModel.setPivNoList(pivNoList);
		pivModel.setMessage("PIV saved successfully.");
		pivModel.setMessageType("SUCCESS");
		pivModel.setPivTotal(pivTotStr);
		
		
		List<PivAmountGrid> amountList = pivDao.getAcctCodesByPivNo(newPivNo,"PIV");
		pivModel.setAmountList(amountList);
		pivModel.setPivTypeList(getPivTypeList(defDeptId));
  	}
	
  	private PivDetail clearForm(PivModel model, String pivNo)
  	{
  		model.setPivApplicant(null);
  		model.setPivDetail(null);
  		model.setPivPayment(null);
  		model.setAmountList(null);
  		model.setBank(null);
  		model.setBankBranch(null);
				
  		PivDetail pivDetail = new PivDetail();
  		PivDetailPK pivDetailPK = new PivDetailPK();
  		pivDetailPK.setPivNo(pivNo);
  		pivDetail.setId(pivDetailPK);
  		return pivDetail;
  	}
  	
  	
}