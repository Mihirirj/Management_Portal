package com.it.dashboard.admin.controller;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.it.dashboard.issue.repo.PivApplicantDao;
import com.it.dashboard.issue.repo.PivDao;
import com.it.dashboard.issue.repo.PivDetailDao;
import com.it.dashboard.issue.domain.PivAmountGrid;
import com.it.dashboard.issue.domain.PivApplicant;
import com.it.dashboard.issue.domain.PivDetail;
import com.it.dashboard.issue.domain.PivModel;
import com.it.dashboard.master.domain.Agent;
import com.it.dashboard.master.domain.AgentBranch;
import com.it.dashboard.master.repo.MasterDao;
import com.it.dashboard.payment.domain.PivPayment;
import com.it.dashboard.payment.repo.PivPaymentDao;
import com.it.dashboard.service.domain.CAAuthResponse;
import com.it.dashboard.util.common.SMSDataProjectCosting;
import com.it.dashboard.util.common.smsDetailsResponse;
import com.it.dashboard.view.controller.CurrencyToWords;
import com.it.dashboard.admin.domain.PivDashboardGrid;
import com.it.dashboard.admin.domain.PivHistory;
import com.it.dashboard.admin.repo.PivHistoryDao;
import com.it.dashboard.admin.repo.SecurityDao;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Controller
public class DashboardController {
	@Autowired
	private PivDetailDao pivdetaildao;
	@Autowired
	private PivApplicantDao pivapplicantdao;
	@Autowired
	private PivDao pivdao;
	
	@Autowired
	private MasterDao masterDao;
	
	@Autowired
	private SecurityDao securityDao;
	@Autowired
	private PivHistoryDao PivHistoryDao;
	
	@Autowired
	private PivPaymentDao pivPaymentDao;
	
	@RequestMapping(value = "admin/newpivs", method = RequestMethod.GET)
	
	 public ModelAndView viewData(@RequestParam Map<String,String> requestParams,HttpServletRequest request){
		request.getSession().setAttribute("headerName", "Today Issued PIV");
		List<PivDashboardGrid> lst=new ArrayList<PivDashboardGrid>();
		System.out.println("admin/newpivs");
		String deptId=request.getSession().getAttribute("deptId").toString();
		
		lst=pivdao.getTodayPivList(deptId,"PIV");
		/*for(int i=0;i<lst.size();i++){
			String status = lst.get(i).getPivDetail().getStatus();
			
			String statusDescription = masterDao.getStatusDescription(status);
		
			lst.get(i).getPivDetail().setStatus(statusDescription);
			
		}*/
		ModelAndView mv= new ModelAndView("admin/newPiv");
		
			mv.addObject("newpivlist", lst);
			
			
			
			try
			{
				
				System.out.println("PIV payment update CA 26020522200779");
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
	 	 
	 			System.out.println("token "+response);
	 			System.out.println("token "+response.getBody().getAccess_token());
	 			String token = response.getBody().getAccess_token();
	 			
	 			
	 			//********************************************
	 			
	 			String updateUrl = "http://cebassist.ceb/Public/UpdatePivPayment";
	 			
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM- HH:mm:ss");
				Date today = Calendar.getInstance().getTime();        
				String paymentDate = df.format(today);
				
				HttpHeaders headers1 = new HttpHeaders();
				headers1.setContentType(MediaType.APPLICATION_JSON);
				headers1.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
				headers1.add("Authorization", "Bearer " + token);
				
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("PivNo", "43710522300667");
				jsonObj.put("PivPaymentDate", "2023-08-21 12:00:00");
				jsonObj.put("PaymentLocation", "2");
				
				//21-AUG-23 01.47.36.000000000 PM
				
		  	     HttpEntity requestEntity1 = new HttpEntity(jsonObj, headers1);
		 			
		  	     RestTemplate restTemplate1 = new RestTemplate();
		  	     ResponseEntity<String> responseEntity = restTemplate1.postForEntity(updateUrl, requestEntity1, String.class);
				
		  	   System.out.println("response ex 3 "+responseEntity.getBody());
		  	   
	 			
				
	 			
	 		}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			
			
			
			
			
			
		return mv;
	

	 }
	@RequestMapping(value = "admin/paidpivs", method = RequestMethod.GET)
	
	 public ModelAndView viewPaidData(HttpServletRequest request){
		request.getSession().setAttribute("headerName", "Today Paid PIV");
		List<PivDashboardGrid> lst=new ArrayList<PivDashboardGrid>();
		
		String deptId=request.getSession().getAttribute("deptId").toString();
		
		lst=pivdao.findPaidPivDetails(deptId,"PIV");
		
		ModelAndView mv= new ModelAndView("admin/paidPiv");
		  
			mv.addObject("paidPivList", lst);
			
		return mv;
	

	 }
	@RequestMapping(value ="/admin/popupView",method = RequestMethod.GET)
	public @ResponseBody
	ModelAndView viewPrint(@RequestParam("pivNo")String pivNo,HttpServletRequest request) {
		ModelAndView viewmodel=null;
		try
		{
			
		
			PivModel pivModel=new PivModel();
			
			PivDetail pivdetails=pivModel.getPivDetail();
			pivdetails=pivdetaildao.findByPivNo(pivNo,"PIV");
			
			
			String deptId=pivdetails.getId().getDeptId();
			
			
			PivApplicant pivapplicant=pivModel.getPivApplicant();
			pivapplicant=pivapplicantdao.findByPivNo(pivNo,"PIV");
			List<PivAmountGrid> amountList = pivdao.getAcctCodesByPivNoWithoutZero(pivNo,"PIV");
			
			
			List<PivHistory> historyList=PivHistoryDao.findHistory(pivNo, deptId,"PIV");
			
			for(int i=0;i<historyList.size();i++){
				String status1 = historyList.get(i).getStatus();
				
				String statusDescription1 = masterDao.getStatusDescription(status1,"PIV");
				
				historyList.get(i).setStatus(statusDescription1);
				
			}

			BigDecimal grandTotal=pivdetails.getGrandTotal();
			
			double currency = grandTotal.doubleValue();
			String printCurrency=CurrencyToWords.convert(currency);
			
			pivModel.setPivDetail(pivdetails);
			pivModel.setPivApplicant(pivapplicant);
			pivModel.setAmountList(amountList);
			pivModel.setHistoryList(historyList);
			pivModel.setPrintCurrency(printCurrency);
			pivModel.setStatusDesc(masterDao.getStatusDescription(pivdetails.getStatus(),"PIV"));
			
			
			if(pivdetails.getPaidAgent()!=null)
			{
				String agentCode = pivdetails.getPaidAgent();
				String branchCode = pivdetails.getPaidBranch();
				Agent agent = masterDao.getAgentById(agentCode,"PIV");
				AgentBranch agentBranch = masterDao.getAgentBranchById(agentCode,branchCode,"PIV");
				if(agent!=null)
					pivdetails.setPaidAgent(agent.getAgentName());
				else
					pivdetails.setPaidAgent(agentCode);
				if(agentBranch!=null)
					pivdetails.setPaidBranch(agentBranch.getAgentBranchName());
				else
					pivdetails.setPaidBranch(branchCode);
			}
		
			//List<PivPaymentGrid> paymentList = pivPaymentDao.getPivPaymentsToPIV(pivNo);
			List<PivPayment> paymentList = pivPaymentDao.getPivPaymentToPIV(pivNo,"PIV");
			pivModel.setPaymentList(paymentList);
			
			viewmodel=new ModelAndView("/view/viewResult","pivModel",pivModel);
			
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return viewmodel;

	}
	
	@RequestMapping(value = "admin/toBeApprovedpivs", method = RequestMethod.GET)
	
	 public ModelAndView viewToBeApprovedPivs(HttpServletRequest request){
		request.getSession().setAttribute("headerName", "To Be Approved PIV");
		//List<PivDashboardGrid> lst=new ArrayList<PivDashboardGrid>();
		List<PivApplicant> lst=new ArrayList<PivApplicant>();
		String deptId=request.getSession().getAttribute("deptId").toString();
		String userRole=request.getSession().getAttribute("loggedUserRole").toString();
		//lst=pivdao.getAuthorisedPivList(userRole,deptId,"V");
		lst=pivdao.getAuthorisedPivDetailList(userRole,deptId,"V","PIV");
		ModelAndView mv= new ModelAndView("admin/toBeApproved");
		  
			mv.addObject("approvedPivList", lst);
			
			
			
			
		return mv;
	 }
	
	@RequestMapping(value = "admin/toBeValidatedpivs", method = RequestMethod.GET)
	
	 public ModelAndView viewToBeValidatedPivs(HttpServletRequest request){
		request.getSession().setAttribute("headerName", "To Be Validated Pivs");
		
		List<PivApplicant> lst=new ArrayList<PivApplicant>();
		
		String deptId=request.getSession().getAttribute("deptId").toString();
		String userRole=request.getSession().getAttribute("loggedUserRole").toString();
		lst=pivdao.getAuthorisedPivDetailList(userRole,deptId,"S","PIV");
		ModelAndView mv= new ModelAndView("admin/toBeValidated");
		 
			mv.addObject("validatedPivList", lst);
			
		return mv;
	 }
	
	/*@RequestMapping(value = "admin/QRCodegeneration", method = RequestMethod.GET)
	public void QRCode() throws WriterException, IOException,NotFoundException {
				String qrCodeData = "Hello";
				String filePath = "QRCode.png";
				String charset = "UTF-8"; // or "ISO-8859-1"
				Map hintMap = new HashMap();
				hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

				createQRCode(qrCodeData, filePath, charset, hintMap, 100, 200);
				

			}

			public static void createQRCode(String qrCodeData, String filePath,
					String charset, Map hintMap, int qrCodeheight, int qrCodewidth)
					throws WriterException, IOException {
				BitMatrix matrix = new MultiFormatWriter().encode(
						new String(qrCodeData.getBytes(charset), charset),
						BarcodeFormat.QR_CODE, qrCodewidth, qrCodeheight, hintMap);
				MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
						.lastIndexOf('.') + 1), new File(filePath));
			}

			public static String readQRCode(String filePath, String charset, Map hintMap)
					throws FileNotFoundException, IOException, NotFoundException {
				BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
						new BufferedImageLuminanceSource(
								ImageIO.read(new FileInputStream(filePath)))));
				Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap,
						hintMap);
				return qrCodeResult.getText();
	

}
	**/		
			
			

}

