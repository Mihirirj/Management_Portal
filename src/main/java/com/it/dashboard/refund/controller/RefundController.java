package com.it.dashboard.refund.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.it.dashboard.admin.domain.PivHistory;
import com.it.dashboard.admin.repo.SecurityDao;
import com.it.dashboard.issue.domain.PivAmountGrid;
import com.it.dashboard.issue.domain.PivApplicant;
import com.it.dashboard.issue.domain.PivDetail;
import com.it.dashboard.issue.domain.PivDetailPK;
import com.it.dashboard.issue.domain.PivModel;
import com.it.dashboard.issue.repo.PivApplicantDao;
import com.it.dashboard.issue.repo.PivDao;
import com.it.dashboard.issue.repo.PivDetailDao;
import com.it.dashboard.master.repo.MasterDao;
import com.it.dashboard.refund.domain.PivRefund;
import com.it.dashboard.refund.domain.PivRefundPK;
import com.it.dashboard.refund.repo.RefundDao;




@Controller
@RequestMapping(value = "refund")
public class RefundController {

	@Autowired
	private PivDetailDao pivDetailDao;

	@Autowired
	private PivApplicantDao pivApplicantDao;

	@Autowired
	private PivDao pivDao;

	@Autowired
	private RefundDao refundDao;

	@Autowired
	private MasterDao masterDao;
	
	@Autowired
	SecurityDao securityDao;
	
	@ExceptionHandler(NullPointerException.class)
	public String handleNullException(NullPointerException ex) {

		ModelAndView model = new ModelAndView("admin/index");
		model.addObject("errMsg", "this is null Exception.class");
		
		//return model;
		return "redirect:../admin/index";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/request")
	public ModelAndView refundRequestFunc(HttpServletRequest request) {
		
		
		List<String> activityList=(List<String>) request.getSession().getAttribute("userActivity");
		
		PivModel pivModel = new PivModel();
		pivModel.setMode("FR");
		if(activityList.contains("FR")){
			request.getSession().setAttribute("headerName", "Refund Request PIV");
			return new ModelAndView("refund/refundForm", "pivModel", pivModel);
		}
		else{
			request.getSession().setAttribute("headerName", "No Access");
			return new ModelAndView("refund/noAccess");
		}
		
		
		
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/cancel")
	public ModelAndView refundRequestCancel(HttpServletRequest request) {
		
		
		List<String> activityList=(List<String>) request.getSession().getAttribute("userActivity");
		
		PivModel pivModel = new PivModel();
		pivModel.setMode("FC");
		if(activityList.contains("FR")){
			request.getSession().setAttribute("headerName", "Refund Request Cancellation");
			return new ModelAndView("refund/refundForm", "pivModel", pivModel);
		}
		else{
			request.getSession().setAttribute("headerName", "No Access");
			return new ModelAndView("refund/noAccess");
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/approve")
	public ModelAndView refundApproveFunc(HttpServletRequest request){
		
		
		List<String> activityList=(List<String>) request.getSession().getAttribute("userActivity");
		
		PivModel pivModel = new PivModel();
		
		if(activityList.contains("FA")){
			request.getSession().setAttribute("headerName", "Refund Approve");
			
			String loggedUser = (String)request.getSession().getAttribute("loggedUser");
			List<String> deptIds = securityDao.getAuthorizedCostCenters(loggedUser,"PIV");
			
			
			List<PivRefund> finalFrList = refundDao.getRefundPivByStatus(deptIds, "FR","PIV");
			
			
			ModelAndView model=new ModelAndView("refund/refundApprovePivs", "pivModel", pivModel);
			model.addObject("finalFrList", finalFrList);
			return model;
		}
		else{
			request.getSession().setAttribute("headerName", "No Access");
			return new ModelAndView("refund/noAccess");
		}
		
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/issue")
	public ModelAndView refundIssueFunc(HttpServletRequest request){
		List<String> activityList=(List<String>) request.getSession().getAttribute("userActivity");
		PivModel pivModel=new PivModel();
		
		if(activityList.contains("F")){
			request.getSession().setAttribute("headerName", "Refund Issue");
			String loggedUser = (String)request.getSession().getAttribute("loggedUser");
			List<String> deptList = securityDao.getAuthorizedCostCenters(loggedUser,"PIV");
			List<PivRefund> FAPivList = refundDao.getRefundPivByStatus(deptList, "FA","PIV");
			ModelAndView model=new ModelAndView("refund/refundIssuePivs", "pivModel", pivModel);
			model.addObject("FAPivList", FAPivList);
			return model;	
		}
		else{
			request.getSession().setAttribute("headerName", "No Access");
			return new ModelAndView("refund/noAccess");
		}
		
	}
	
	// this is for page submit button action in refundForm //
	@RequestMapping(value = "/requestrefund", method = RequestMethod.POST)
	public ModelAndView onchangeFunc(@ModelAttribute("pivModel") PivModel pivModel, @RequestParam String actionButton,HttpServletRequest request) {
		String deptId = request.getSession().getAttribute("deptId").toString();
		String pivNo = pivModel.getPivDetail().getId().getPivNo();
		System.out.println("mode%%%"+pivModel.getMode());
		if (pivNo.isEmpty()) {
			pivModel.setMessage("enter a piv number to find");
			pivModel.setMessageType("INFO");
		} 
		else 
		{
			if (actionButton.equals("Find")) 
			{
				String loggedUser = (String)request.getSession().getAttribute("loggedUser");
				List<String> deptList = securityDao.getAuthorizedCostCenters(loggedUser,"PIV");
				PivDetail pivDetail = pivDetailDao.findByPivNo(pivNo,"PIV");
				
				if(pivDetail!=null)
				{
					
					String deptPIV=pivDetail.getId().getDeptId();
					
					if(deptList.contains(deptPIV))
					{
						setPivModel(pivModel,pivDetail);
						
							
						
					}
					else
					{
						pivModel.setMessage("Sorry, You have no authority to send refund request for this PIV");
						pivModel.setMessageType("ERROR");
					}
				}
					
			}
	
			else if (actionButton.equals("Send"))
			{
				PivRefund pivRefund = new PivRefund();
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				BigDecimal requestAmount = pivModel.getPivRefund().getRefundRequestAmount();
				
				
				BigDecimal seq_no = new BigDecimal("1");
			
				PivDetail pivDetail = pivDetailDao.findByPivNo(pivNo,"PIV");
				if(requestAmount.doubleValue()<=pivDetail.getPivAmount().doubleValue())
				{
					pivDetail.setStatus("FR");
					pivModel.setMode("FR");
					String stats = pivDetail.getStatus();
					BigDecimal refundable_amount = pivDetail.getPivAmount();
					String deptPIV=pivDetail.getId().getPivNo();
					
					PivDetailPK pk=new PivDetailPK();
					pk.setPivNo(pivNo);
					pk.setDeptId(pivDetail.getId().getDeptId());
					pivRefund.setId(pk);
					pivRefund.setRefundRequestAmount(requestAmount);
					pivRefund.setSeqNo(seq_no);
					pivRefund.setRequestUser((String) request.getSession().getAttribute("loggedUser"));		
					pivRefund.setRequestDate(timestamp);
					pivRefund.setRefundableAmount(refundable_amount);
					pivRefund.setStatus(stats);
				
					
					///pivDetail.getA
					//pivDetailDao.updateStatus(pivNo, deptPIV, stats);
					String loggedUser=(String)request.getSession().getAttribute("loggedUser");
					PivHistory pivHistory = new PivHistory();
					pivHistory.setDeptId(pivDetail.getId().getDeptId());
					pivHistory.setPivNo(pivNo);
					pivHistory.setStatus("FR");
					pivHistory.setUserId(loggedUser);
					pivHistory.setDescription("Request for refund.");
					Calendar calendar = Calendar.getInstance();
					Timestamp updateTime=new Timestamp(calendar.getTime().getTime());
					pivHistory.setUpdateTime(updateTime);
						
					refundDao.insertRefundDetails(pivRefund,  pivDetail,pivHistory,"PIV");
					pivModel.setMessage("PIV refund request sent successfully.");
					pivModel.setMessageType("SUCCESS");
					
					//String statsDescription = masterDao.getStatusDescription(stats);
					//pivDetail.setStatus(statsDescription);
				}
				else
				{
					setPivModel(pivModel,pivDetail);
					pivModel.setMessage("Request amount should be less than or equal to PIV amount.");
					pivModel.setMessageType("ERROR");
				}
	
			} 
			else if (actionButton.equals("Cancel"))
			{
				
			}
			return new ModelAndView("refund/refundForm", "pivModel", pivModel);
		}
		return new ModelAndView("refund/refundForm", "pivModel", pivModel);
	}
	
	@RequestMapping(value = "/refundApproved", method = RequestMethod.GET)
	public ModelAndView refundApprovedPivs(@ModelAttribute("pivModel") PivModel pivModel,@RequestParam("id") String id,HttpServletRequest request){
		String pivNo=id;
		ModelAndView model=null;
		System.out.println("customer name"+pivModel.getPivApplicant().getName());
		try
		{
			PivDetail pivDetail = pivDetailDao.findByPivNo(pivNo,"PIV");
			PivApplicant pivApplicant = pivModel.getPivApplicant();
			pivApplicant = pivApplicantDao.findByPivNo(pivNo,"PIV");
			List<PivAmountGrid> amountList = pivDao.getAcctCodesByPivNo(pivNo,"PIV");
			
			//String status=pivDetail.getStatus();
			//String pivtype =pivDetail.getGltitlm().getTitleCd().trim();
			//String statusDescription = masterDao.getStatusDescription(status);
			//String typeDescription = masterDao.getPivTypeDescription(pivtype);
			//pivDetail.setStatus(statusDescription);
			//pivDetail.getGltitlm().setTitleCd(typeDescription);
			
			pivModel.setPivDetail(pivDetail);
			pivModel.setPivApplicant(pivApplicant);
			pivModel.setAmountList(amountList);
			pivModel.setMode("approve");
			pivModel.setStatusDesc(masterDao.getStatusDescription(pivDetail.getStatus(),"PIV"));
			BigDecimal reqAmount=refundDao.getRequsetAmount(pivNo,"PIV");
			
			
			model=new ModelAndView("refund/refundDetailForm", "pivModel", pivModel);
			model.addObject("reqAmount", reqAmount);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return model;
		
	}
	
	@RequestMapping(value="/refundIssued",method=RequestMethod.GET)
	public ModelAndView refundIssuePivs(@ModelAttribute("pivModel") PivModel pivModel,@RequestParam("id") String id,HttpServletRequest request){
		String pivNo=id;
		ModelAndView model=null;
		String deptId = request.getSession().getAttribute("deptId").toString();
		
			PivDetail pivDetail=new PivDetail();
			PivRefund pivRefund=new PivRefund();
			pivDetail = pivDetailDao.findByPivNo(pivNo,"PIV");
			PivApplicant pivApplicant = pivModel.getPivApplicant();
			pivApplicant = pivApplicantDao.findByPivNo(pivNo,"PIV");
			List<PivAmountGrid> amountList = pivDao.getAcctCodesByPivNo(pivNo,"PIV");
			
			/*String status=pivDetail.getStatus();
			String pivtype =pivDetail.getGltitlm().getTitleCd().trim();
			String statusDescription = masterDao.getStatusDescription(status);
			String typeDescription = masterDao.getPivTypeDescription(pivtype);
			pivDetail.setStatus(statusDescription);
			pivDetail.getGltitlm().setTitleCd(typeDescription);*/
			pivModel.setMode("issue");
		
			Calendar calendar = Calendar.getInstance();
			pivRefund.setRefundDate(calendar.getTime());
			
			pivModel.setPivRefund(pivRefund);
			pivModel.setPivDetail(pivDetail);
			pivModel.setPivApplicant(pivApplicant);
			pivModel.setAmountList(amountList);
			pivModel.setStatusDesc(masterDao.getStatusDescription(pivDetail.getStatus(),"PIV"));
			model=new ModelAndView("refund/refundDetailForm", "pivModel", pivModel);
		
		return model;
	}
	
	@RequestMapping(value="refundApprove",method=RequestMethod.POST)
	public ModelAndView approveRefunding(@ModelAttribute("pivModel") PivModel pivModel, @RequestParam String actionButton,@RequestParam("pivno") String pivNo,HttpServletRequest request){
		String deptId = request.getSession().getAttribute("deptId").toString();
		System.out.println("deptId"+deptId);
		String deptPIV=refundDao.getDeptIdforPIV(pivNo,"PIV");
		if(actionButton.equals("Back To List")){
			return new ModelAndView("redirect:/refund/approve");
		}
		else if(actionButton.equals(" Back To List")){
			return new ModelAndView("redirect:/refund/issue");
		}
		else if(actionButton.equals("Confirm")){
			
			
				PivDetail pivDetail = pivDetailDao.findByPivNo(pivNo,"PIV");
				PivApplicant pivApplicant = pivModel.getPivApplicant();
				pivApplicant = pivApplicantDao.findByPivNo(pivNo,"PIV");
				List<PivAmountGrid> amountList = pivDao.getAcctCodesByPivNo(pivNo,"PIV");
				Timestamp approvalDate = new Timestamp(System.currentTimeMillis());
				String date=approvalDate.toString().substring(0, 9);
				String approvalUser=(String)request.getSession().getAttribute("loggedUser");
			
				PivRefundPK pivRefundPK=new PivRefundPK();
				pivRefundPK.setDeptId(pivDetail.getId().getDeptId());
				pivRefundPK.setPivNo(pivNo);
				
				PivRefund pivRefund= refundDao.findById(pivDetail.getId().getDeptId(),pivNo,"PIV");
				pivRefund.setApprovalDate(approvalDate);
				pivRefund.setApprovalUser(approvalUser);
				pivRefund.setStatus("FA");
				
				pivModel.setMode("FA");
				pivDetail.setStatus("FA");
				//String status = pivDetail.getStatus();
			
				//pivDetailDao.updateStatus(pivNo, deptPIV, status);
				//refundDao.updateRefundDetails(pivNo, deptPIV, status, approvalDate, approvalUser);
				String loggedUser=(String)request.getSession().getAttribute("loggedUser");
				PivHistory pivHistory = new PivHistory();
				pivHistory.setDeptId(pivDetail.getId().getDeptId());
				pivHistory.setPivNo(pivNo);
				pivHistory.setStatus("FA");
				pivHistory.setUserId(loggedUser);
				pivHistory.setDescription("Refund request approved.");
				Calendar calendar = Calendar.getInstance();
				Timestamp updateTime=new Timestamp(calendar.getTime().getTime());
				pivHistory.setUpdateTime(updateTime);
				
				refundDao.updateRefundDetails(pivRefund, pivDetail, pivHistory,"PIV");
				//String pivtype =pivDetail.getGltitlm().getTitleCd().trim();
				//String statusDescription = masterDao.getStatusDescription(status);
				///String typeDescription = masterDao.getPivTypeDescription(pivtype);
				//pivDetail.setStatus(statusDescription);
				//pivDetail.getGltitlm().setTitleCd(typeDescription);
				
				pivModel.setPivDetail(pivDetail);
				pivModel.setPivApplicant(pivApplicant);
				pivModel.setAmountList(amountList);
				pivModel.setMessage("PIV refund request approved.");
				pivModel.setMessageType("SUCCESS");
				pivModel.setStatusDesc(masterDao.getStatusDescription(pivDetail.getStatus(),"PIV"));
				
			
			return new ModelAndView("refund/refundDetailForm", "pivModel", pivModel);
		}
		else if(actionButton.equals("Issue")){
			
				PivDetail pivDetail = pivDetailDao.findByPivNo(pivNo,"PIV");
				PivApplicant pivApplicant = pivModel.getPivApplicant();
				pivApplicant = pivApplicantDao.findByPivNo(pivNo,"PIV");
				List<PivAmountGrid> amountList = pivDao.getAcctCodesByPivNo(pivNo,"PIV");
								
				String docNo=pivModel.getPivRefund().getDocNo();
				Date refundDate=pivModel.getPivRefund().getRefundDate();
				String loggedUser=(String)request.getSession().getAttribute("loggedUser");
				
			
				pivModel.setMode("F");
				pivDetail.setStatus("F");
				
				
				PivRefund pivRefund= refundDao.findById(pivDetail.getId().getDeptId(),pivNo,"PIV");
				pivRefund.setRefundUser(loggedUser);
				pivRefund.setDocNo(docNo);
				pivRefund.setRefundDate(refundDate);
				pivRefund.setStatus("F");
				
				
				//String status = pivDetail.getStatus();
			
				//pivDetailDao.updateStatus(pivNo, deptPIV, status);
				//refundDao.updateRefundDetails(pivNo, deptPIV, status, approvalDate, approvalUser);
				
				PivHistory pivHistory = new PivHistory();
				pivHistory.setDeptId(pivDetail.getId().getDeptId());
				pivHistory.setPivNo(pivNo);
				pivHistory.setStatus("F");
				pivHistory.setUserId(loggedUser);
				pivHistory.setDescription("Refund issued.");
				Calendar calendar = Calendar.getInstance();
				Timestamp updateTime=new Timestamp(calendar.getTime().getTime());
				pivHistory.setUpdateTime(updateTime);
				
				refundDao.updateRefundDetails(pivRefund, pivDetail, pivHistory,"PIV");
				
				//pivDetailDao.updateStatus(pivNo, deptPIV, status);
				//refundDao.updateRefundIssueDetails(pivNo, deptPIV, status, refundDate, refundUser, docNo);
				//refundDao.updateRefund(pivRefund);
				//String pivtype =pivDetail.getGltitlm().getTitleCd().trim();
				//String statusDescription = masterDao.getStatusDescription(status);
				//String typeDescription = masterDao.getPivTypeDescription(pivtype);
				//pivDetail.setStatus(statusDescription);
				//pivDetail.getGltitlm().setTitleCd(typeDescription);
				pivModel.setPivDetail(pivDetail);
				pivModel.setPivApplicant(pivApplicant);
				pivModel.setAmountList(amountList);
				pivModel.setStatusDesc(masterDao.getStatusDescription(pivDetail.getStatus(),"PIV"));
				pivModel.setMessage("PIV refund issued succesfully.");
				pivModel.setMessageType("SUCCESS");
			
			return new ModelAndView("refund/refundDetailForm", "pivModel", pivModel);
		}
		return null;
	}

	// private methods//
	private BigDecimal refundableAmount(String pivNo) {
		PivDetail pivDetail = pivDetailDao.findByPivNo(pivNo,"PIV");
		List<PivAmountGrid> amountList = pivDao.getAcctCodesByPivNo(pivNo,"PIV");
		BigDecimal total_amnt = new BigDecimal("0");

		List<String> refundableAccCodelist = refundDao.isRefundableAccCode("PIV");

		NumberFormat nf = NumberFormat.getInstance();
		nf.setGroupingUsed(true);
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		
		for (int i = 0; i < amountList.size(); i++) {
			PivAmountGrid amountGrid = amountList.get(i);
			String accd = amountGrid.getAcCd().trim();
			

			for (int j = 0; j < refundableAccCodelist.size(); j++) {
				Boolean x = refundableAccCodelist.get(j).trim().equals(accd);
				if (x) {
					double tmpAmt = 0;
					if(amountGrid.getAmount()!=null && amountGrid.getAmount().trim().length()>0)
					{
						//tmpAmt = amountGrid.getAmount().doubleValue();
						try{
						tmpAmt = nf.parse(amountGrid.getAmount()).doubleValue();
						}catch(ParseException e){}
					}
					/*if(amountGrid.getAmount()!=null)
					{
						tmpAmt = amountGrid.getAmount().doubleValue();
						
					}*/
					BigDecimal amnt = new BigDecimal(tmpAmt);
					
					total_amnt = amnt.add(total_amnt);
				} else {
					
				}
			}

		}
		
		return total_amnt;

	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}
	
	private void setPivModel(PivModel pivModel, PivDetail pivDetail)
	{
		String pivNo = pivDetail.getId().getPivNo();
		PivApplicant pivApplicant = pivModel.getPivApplicant();
		pivApplicant = pivApplicantDao.findByPivNo(pivNo,"PIV");
		List<PivAmountGrid> amountList = pivDao.getAcctCodesByPivNo(pivNo,"PIV");
		
		BigDecimal refundable_amount = pivDetail.getPivAmount();
		String mode = pivModel.getMode();
		String state=pivDetail.getStatus();
		
		String pivtype =pivDetail.getTitleCd().trim();
			
		pivModel.setPivDetail(pivDetail);		
		pivModel.setPivDetail(pivDetail);
		pivModel.setPivApplicant(pivApplicant);
		pivModel.setAmountList(amountList);
		//pivModel.setMode(state);
		pivModel.setStatusDesc(masterDao.getStatusDescription(pivDetail.getStatus(),"PIV"));
		System.out.println("mode%%%1"+pivModel.getMode());
		
		PivRefund pivRefund= refundDao.findById(pivDetail.getId().getDeptId(),pivNo,"PIV");
		if(pivRefund!=null)
		{
			pivModel.setPivRefund(pivRefund);
		}
		if(mode.equals("FR"))
		{
			if (!state.equals("P")) {
		
				if (state.equals("FR")) {	
					pivModel.setMessage("Refund request is already send for this PIV.Cannot request again.");
					pivModel.setMessageType("ERROR");
					
				} 
				else if(state.equals("F")){
					pivModel.setMessage("This PIV has already been refunded.");
					pivModel.setMessageType("ERROR");
				}
				
				else {
					pivModel.setMessage("This PIV cannot be refunded");
					pivModel.setMessageType("ERROR");
				}
			}
		}
		else if(mode.equals("FC"))
		{
			if(!state.equals("FR")){
				
				pivModel.setMessage("This PIV refund cannot be cancelled");
				pivModel.setMessageType("ERROR");
			}
		}
	}

}

