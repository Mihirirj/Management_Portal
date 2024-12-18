package com.it.dashboard.smc.controller;
import com.it.dashboard.smc.dao.EstimateDao;


import com.it.dashboard.util.common.Path;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.NamingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import com.it.dashboard.master.domain.Applicant;
import com.it.dashboard.master.domain.TableRowValue;
import com.it.dashboard.master.repo.MasterDao;
import com.it.dashboard.payment.domain.PivPayment;
import com.it.dashboard.payment.repo.PivPaymentDao;
import com.it.dashboard.service.domain.CAAuthResponse;
import com.it.dashboard.smc.dao.EstimateDao;
import com.it.dashboard.smc.model.SearchModel;
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

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import com.it.dashboard.util.common.Path;




@RequestMapping(value = "/smc")
@Controller
public class SmcController {
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
	@Autowired
	private EstimateDao estimateDao;
	
	@ExceptionHandler(NullPointerException.class)
	public String handleNullException(NullPointerException ex) {

		ModelAndView model = new ModelAndView("admin/index");
		model.addObject("errMsg", "this is null Exception.class");
		Map<String,String> divisionList = new HashMap<String,String>();
	    divisionList.put("DISCO1","DISTRIBUTION DIVISION 1");
	    divisionList.put("DISCO2","DISTRIBUTION DIVISION 2");
	    divisionList.put("DISCO3","DISTRIBUTION DIVISION 3");
	    divisionList.put("DISCO4","DISTRIBUTION DIVISION 4");
	    model.addObject("divisionList",divisionList);
	    model.addObject("division","-1");
		return "redirect:../login/signout";
	}  
	
	
	//-----------------------Pending Estimates----------------------------------------//
	
	@RequestMapping("/pendingEstimate")
	public ModelAndView pendingEstimate() {
		System.out.println("pending estimate");
	    ModelAndView model = new ModelAndView("admin/pendingEstimates");
	    List<TableRowValue> list = estimateDao.pendingEstimateDetailByDivision("DISCO1", "MGT");
	    NumberFormat nf = NumberFormat.getInstance();
	    nf.setGroupingUsed(true);
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		
	    model.addObject("tableList",list);
	    model.addObject("menu","2");
	    model.addObject("heading","Pending Estimates");
	    return model;
	}
	
	@RequestMapping(value = "/viewBottomDetailList", method = RequestMethod.GET)
	public ModelAndView viewBottomDetailList(@RequestParam("id") String id) {
		System.out.println("viewBottomDetailList"+id+"@@");
		ModelAndView model = new ModelAndView("admin/areaTable");
		List<TableRowValue> list = estimateDao.pendingEstimateDetailByProvince(id, "MGT");
		model.addObject("bottomList",list);
		model.addObject("menu","2");
		return model;
	}
	
	
	//-----------------------New Clients(account not opened)----------------------------------------//
	
		@RequestMapping("/newClients")
		public ModelAndView newClients() {
			System.out.println("new clients");
		    ModelAndView model = new ModelAndView("admin/newClients");
		    List<TableRowValue> list = estimateDao.pendingEstimateDetailByDivision("DISCO1", "MGT");
		    NumberFormat nf = NumberFormat.getInstance();
		    nf.setGroupingUsed(true);
			nf.setMaximumFractionDigits(2);
			nf.setMinimumFractionDigits(2);
			
		    model.addObject("clientTableList",list);
		    model.addObject("clientHeading","New Clients");
		    return model;
		}
		
		@RequestMapping(value = "/viewBottomDetailList", method = RequestMethod.POST)
		public ModelAndView viewBottomDetailList() {
			System.out.println("viewBottomDetailList");
			ModelAndView model = new ModelAndView("admin/clientTable");
			List<TableRowValue> list = estimateDao.pendingEstimateDetailByProvince("CC", "MGT");
			model.addObject("clientBottomList",list);
			return model;
		}
		
		
		//-------------------newClients END---------------//
		
		
		
		//-----New Connection Reports------//
		@RequestMapping("/newConnection")
		public ModelAndView newConnection() {
			System.out.println("newConnection");
		    ModelAndView model = new ModelAndView("view/newConn");
		    List<TableRowValue> list = estimateDao.pendingEstimateDetailByDivision("DISCO1", "MGT");
		    NumberFormat nf = NumberFormat.getInstance();
		    nf.setGroupingUsed(true);
			nf.setMaximumFractionDigits(2);
			nf.setMinimumFractionDigits(2);
			
		    model.addObject("tableList",list);
		    model.addObject("menu","2");
		    model.addObject("heading","New Connection Reports");
		    
		    return model;
		}
		
		
	
		
		 //-----Search------//
		
		@RequestMapping(value = "/searchMenu", method = {RequestMethod.GET, RequestMethod.POST})
		public ModelAndView searchMenu(
		        @RequestParam(value = "idNo", required = false) String idNo,
		        @RequestParam(value = "accountNo", required = false) String accountNo,
		        @RequestParam(value = "applicationNo", required = false) String applicationNo,
		        @RequestParam(value = "jobNo", required = false) String jobNo,
		        @RequestParam(value = "estimationNo", required = false) String estimationNo) {

		    ModelAndView model = new ModelAndView("view/searchMenu");
		    List<SearchModel> rowList = null;
		    Map<String, String> errors = new HashMap<String, String>();


		    // Check if a request is submitted (at least one parameter is provided)
		    boolean isSubmitted = (idNo != null && !idNo.trim().isEmpty()) ||
		                          (accountNo != null && !accountNo.trim().isEmpty()) ||
		                          (applicationNo != null && !applicationNo.trim().isEmpty()) ||
		                          (jobNo != null && !jobNo.trim().isEmpty()) ||
		                          (estimationNo != null && !estimationNo.trim().isEmpty());

		    if (isSubmitted) {
		        // Check if exactly one field is filled
		        int filledCount = 0;
		        if (idNo != null && !idNo.trim().isEmpty()) filledCount++;
		        if (accountNo != null && !accountNo.trim().isEmpty()) filledCount++;
		        if (applicationNo != null && !applicationNo.trim().isEmpty()) filledCount++;
		        if (jobNo != null && !jobNo.trim().isEmpty()) filledCount++;
		        if (estimationNo != null && !estimationNo.trim().isEmpty()) filledCount++;

		        if (filledCount != 1) {
		            errors.put("fieldError", "Please fill exactly one field.");
		        } else {
		            // Validate and fetch data based on search criteria
		            if (idNo != null && !idNo.trim().isEmpty()) {
		                if (!isValidIdNumber(idNo)) {
		                    errors.put("idNo", "Invalid ID Number format.");
		                } else {
		                    rowList = estimateDao.getSearchDataByIdNo(idNo);
		                }
		            } else if (accountNo != null && !accountNo.trim().isEmpty()) {
		                if (!isValidAccountNumber(accountNo)) {
		                    errors.put("accountNo", "Invalid Account Number format.");
		                } else {
		                    rowList = estimateDao.getSearchDataByAccountNo(accountNo);
		                }
		            } else if (applicationNo != null && !applicationNo.trim().isEmpty()) {
		                if (!isValidApplicationNumber(applicationNo)) {
		                    errors.put("applicationNo", "Invalid Application Number format.");
		                } else {
		                    rowList = estimateDao.getSearchDataByApplicationNo(applicationNo);
		                }
		            } else if (jobNo != null && !jobNo.trim().isEmpty()) {
		                if (!isValidJobNumber(jobNo)) {
		                    errors.put("jobNo", "Invalid Job Number format.");
		                } else {
		                    rowList = estimateDao.getSearchDataByJobNo(jobNo);
		                }
		            } else if (estimationNo != null && !estimationNo.trim().isEmpty()) {
		                if (!isValidEstimationNumber(estimationNo)) {
		                    errors.put("estimationNo", "Invalid Estimation Number format.");
		                } else {
		                    rowList = estimateDao.getSearchDataByEstimationNo(estimationNo);
		                }
		            }
		        }

		        // Check if there are errors, if so, return to the form with error messages
		        if (!errors.isEmpty()) {
		            model.addObject("errors", errors);
		            model.addObject("Formheading", "Search Menu");
		            model.addObject("Tableheading", "Search Data");
		            return model;
		        }

		        // If no errors and data found, display the search results
		        if (rowList == null || rowList.isEmpty()) {
		            model.addObject("errorMessage", "No results found.");
		        } else {
		            model.addObject("rowList", rowList);
		        }
		    }

		    model.addObject("Formheading", "Search Menu");
		    model.addObject("Tableheading", "Search Data");
		    return model;
		}

		private boolean isValidIdNumber(String idNo) {
		    // Validate ID Number format (10 or 12 characters alphanumeric)
		    return idNo.matches("[a-zA-Z0-9]+") && (idNo.length() == 10 || idNo.length() == 12);
		}

		private boolean isValidAccountNumber(String accountNo) {
		    // Validate Account Number format (exactly 18 characters alphanumeric)
		    return accountNo.matches("[a-zA-Z0-9]+") && accountNo.length() == 10;
		}

		private boolean isValidApplicationNumber(String applicationNo) {
		    // Validate Application Number format (exactly 18 characters)
		    return applicationNo.matches("\\d{3}\\.\\d{2}/[A-Z]+/\\d{2}/\\d{4}");
		}

		private boolean isValidJobNumber(String jobNo) {
		    // Validate Job Number format (exactly 18 characters)
		    return jobNo.matches("\\d{3}\\.\\d{2}/[A-Z]+/\\d{2}/\\d{4}");
		}

		private boolean isValidEstimationNumber(String estimationNo) {
		    // Validate Estimation Number format (exactly 18 characters)
		    return estimationNo.matches("\\d{3}\\.\\d{2}/[A-Z]+/\\d{2}/\\d{4}");
		}


		/*------------------chart controller-------------//
		
		@Controller
		@RequestMapping("/dashboard")
		public class ChartController {

		    private final EstimateDao estimateDao;

		    @Autowired
		    public ChartController(EstimateDao estimateDao) {
		        this.estimateDao = estimateDao;
		    }

		    @RequestMapping(value = "/chartData", method = RequestMethod.GET, produces = "application/json")
		    @ResponseBody
		    public List<Integer> getChartDataJson() {
		        String appName = "DashboardApp"; // Adjust this as necessary
		        String division = "yourDivision"; // Adjust this as necessary
		        List<Integer> chartData = estimateDao.getWeeklyApplicationCounts(division, appName);
		        return chartData;
		    }
		}
		
		//----------------End of the chart controller------*/
	
		
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
	 			
	 			
	 			//****************
	 			
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

	@RequestMapping(value = "/report", method = RequestMethod.GET)
	public String printInvoice(Model model, HttpServletRequest request,HttpServletResponse response) {
		
		Connection conn = null;
		//boolean canPrint = false;
		//String deptId = request.getSession().getAttribute("deptId").toString();
		try 
		{
			conn = getReportDbConnection("PIV");
			System.out.println("create conn printInvoice");			
			HashMap<String, Object> hmParams = new HashMap<String, Object>();
			String reportCode = request.getParameter("reportCode");
			//String reportName = "";
			//if(reportCode.equals("NC-001"))
			hmParams.put("@compId","DISCO1" );
			
			Path path = new Path();
			String REPORT_DIRECTORY = path.getReportPath();
			
			List<String> activityList = (List<String>)request.getSession().getAttribute("userActivity");
			
			
			String jrxmlFile = REPORT_DIRECTORY+reportCode+".jrxml";
			String jasperFile = REPORT_DIRECTORY+reportCode+".jasper";
			
			/*String jrxmlFile = REPORT_DIRECTORY+"Invoice.jrxml";
			String jasperFile = REPORT_DIRECTORY+"Invoice.jasper";*/
					
			System.out.println("jrxmlFile"+jrxmlFile);
			JasperReport jasperReport = getCompiledFile(jrxmlFile,jasperFile,request);
			generateReportPDF(response, hmParams, jasperReport, conn); 
			conn.close();
			System.out.println("close conn printInvoice");		
			
			
		}
		catch (Exception sqlExp) 
		{
			sqlExp.printStackTrace();
		} 
		finally 
		{
			if (conn != null) 
			{
				try 
				{
					conn.close();
					conn = null;
					System.out.println("close conn printInvoice finally");		
				} 
				catch (SQLException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}
		return null;
	}
	
	
	private JasperReport getCompiledFile(String jrxmlFile,String jasperFile, HttpServletRequest request) throws JRException {
		File reportFile = new File(	jasperFile);	
		//JasperCompileManager.compileReportToFile(jrxmlFile,jasperFile);
		JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportFile.getPath());
		return jasperReport;
		//return null;
	} 
	
	public void generateReportPDF (HttpServletResponse resp, Map parameters, JasperReport jasperReport, Connection conn)throws JRException, NamingException, SQLException, IOException {
		byte[] bytes = null;
		bytes = JasperRunManager.runReportToPdf(jasperReport,parameters,conn);
		resp.reset();
		resp.resetBuffer();
		resp.setContentType("application/pdf");
		resp.setContentLength(bytes.length);
		ServletOutputStream ouputStream = resp.getOutputStream();
		ouputStream.write(bytes, 0, bytes.length);
		ouputStream.flush();
		ouputStream.close();
} 
	
	
	private Connection getReportDbConnection(String appName) 
	{
		Connection conn = null;
		/*String connStr = configDao.getConfigValue("REPORT_DB_CON_STR", appName);
		String user = configDao.getConfigValue("REPORT_DB_USER", appName);
		String pwd = configDao.getConfigValue("REPORT_DB_PWD", appName);*/
		
		String connStr ="jdbc:oracle:thin:@10.128.0.163:1521:hqorap1";
		String user = "prodmis";
		String pwd = "prodmisn";
		/*
		String connStr ="jdbc:oracle:thin:@10.128.0.56:1521:hqorad1";
		String user = "dacons12";
		String pwd = "dacons12";*/
		try 
		{
		 	Class.forName("oracle.jdbc.driver.OracleDriver");
		 	conn = DriverManager.getConnection(connStr,user,pwd);
			 
		} catch (Exception e) {
	 		e.printStackTrace(); 
	 	}  
		return conn;	
	}

	
	
	
			

}