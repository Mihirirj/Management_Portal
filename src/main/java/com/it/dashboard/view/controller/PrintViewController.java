package com.it.dashboard.view.controller;
/**
 * @author Paramie
 *
 */


import javax.naming.NamingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.it.dashboard.util.common.Path;
import com.it.dashboard.view.controller.CurrencyToWords;
import com.it.dashboard.issue.repo.PivDetailDao;
import com.it.dashboard.admin.repo.ConfigDao;
import com.it.dashboard.admin.repo.PivHistoryDao;
import com.it.dashboard.master.repo.MasterDao;
import com.it.dashboard.issue.repo.PivApplicantDao;
import com.it.dashboard.issue.repo.PivDao;
import com.it.dashboard.issue.domain.PivAmountGrid;
import com.it.dashboard.issue.domain.PivApplicant;
import com.it.dashboard.issue.domain.PivDetail;
import com.it.dashboard.issue.domain.PivModel;
import com.it.dashboard.admin.domain.PivHistory;
import com.it.dashboard.master.domain.Agent;
import com.it.dashboard.master.domain.AgentBranch;
import com.it.dashboard.payment.domain.PivPayment;
import com.it.dashboard.payment.repo.PivPaymentDao;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




@Controller
public class PrintViewController {
	
	@Autowired
	private PivDetailDao PivDetailDao;

	@Autowired
	private PivApplicantDao PivApplicantDao;
	
	@Autowired
	private MasterDao masterDao;
	

	@Autowired
	private PivDao PivDao;
	
	@Autowired
	private PivHistoryDao PivHistoryDao;
	
	@Autowired
	private PivPaymentDao pivPaymentDao;
	
	@Autowired
	private ConfigDao configDao;
	
	@ExceptionHandler(NullPointerException.class)
	public String handleNullException(NullPointerException ex) {

		ModelAndView model = new ModelAndView("admin/index");
		model.addObject("errMsg", "this is null Exception.class");
		
		//return model;
		return "redirect:../admin/index";
	}
	
	@RequestMapping(value = "/viewprint/viewprint", method = RequestMethod.GET)
	public ModelAndView welcome(HttpServletRequest request) {
		String deptId = request.getSession().getAttribute("deptId").toString();
		request.getSession().setAttribute("headerName","PIV View");

		PivModel pivModel=new PivModel();
		return new ModelAndView("viewprint/viewprint" , "pivModel", pivModel);

	}

	@RequestMapping(value ="/viewprint/temp11")
	public ModelAndView viewPrint(@RequestParam("pivno") String pivno,HttpServletRequest request) {
		String deptId = request.getSession().getAttribute("deptId").toString();
		 		ModelAndView viewmodel=new ModelAndView("/viewprint/viewprint");
				if(pivno==null || pivno.equals("")){
					viewmodel.addObject("errorexp","piv no cannot be empty");
					return viewmodel;
				}
		       
		       // boolean error = false;
				else
				{
					try
					{
						request.getSession().setAttribute("headerName", "PIV View");
						PivModel pivmodel=new PivModel();
						
						PivDetail pivdetails=pivmodel.getPivDetail();
						PivApplicant pivapplicant=pivmodel.getPivApplicant();
						
					    pivdetails=PivDetailDao.findByPivNo(pivno,"PIV");
						pivapplicant=PivApplicantDao.findByPivNo(pivno,"PIV");
					
						List<PivAmountGrid> amountList = PivDao.getAcctCodesByPivNo(pivno,"PIV");
						
				
						pivmodel.setPivDetail(pivdetails);
						pivmodel.setPivApplicant(pivapplicant);
						pivmodel.setAmountList(amountList);
						
						viewmodel.addObject("pivdetails",pivdetails);
						viewmodel.addObject("pivapplicant",pivapplicant);
						viewmodel.addObject("pivmodel",pivmodel);
						viewmodel.addObject("pivno", pivno);
						viewmodel.addObject("amountList", amountList);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				
				return viewmodel;
				
				}
		
			
		}
	@RequestMapping(value ="/viewprint/findPiv")
	public ModelAndView viewPrint1(@ModelAttribute("pivModel") PivModel pivModel ,HttpServletRequest request, ModelMap model) {
		request.getSession().getAttribute("deptId");
			String pivNo = pivModel.getPivDetail().getId().getPivNo();
			if(pivNo==null || pivNo.equals("")){
				pivModel.setMessage("PIV no. cannot be empty.");
				
			}
			else
			{
				PivDetail pivDetail=pivModel.getPivDetail();
				pivDetail=PivDetailDao.findByPivNo(pivNo.trim(),"PIV");
				if(pivDetail==null)
				{
					
					pivModel.setMessage("PIV does not exist.");
				}
				else
				{
					List<String> activityList = (List<String>)request.getSession().getAttribute("userActivity");
					
					String displayMode = "";
					/*if(activityList.contains("PT") && activityList.contains("DT")){
						pivModel.setMode("PTDT");
						//model.addAttribute("pt", pivModel.getMode());
						//pivModel.setMode("DT");
						//model.addAttribute("dt", pivModel.getMode());
					}	*/
					if(activityList.contains("PT")){
						displayMode = displayMode+"PT";
						//pivModel.setMode("PT");
					}
					if(activityList.contains("DT")){
						displayMode = displayMode+"DT";
						//pivModel.setMode("DT");
					}
					//if(pivDetail.getPivActivity().getActivityCode().equals("P"))
					//{
						if(activityList.contains("CT")){
							displayMode = displayMode+"CT";
							//pivModel.setMode("DT");
						}
					//}
					pivModel.setMode(displayMode);
					
					PivApplicant pivApplicant=pivModel.getPivApplicant();
					pivApplicant=PivApplicantDao.findByPivNo(pivNo,"PIV");
					
					String status=pivDetail.getStatus();
					String pivtype =pivDetail.getTitleCd().trim();
					String deptId=pivDetail.getId().getDeptId();
					
			
					List<PivAmountGrid> amountList = PivDao.getAcctCodesByPivNoWithoutZero(pivNo,"PIV");
					
					List<PivHistory> historyList=PivHistoryDao.findHistory(pivNo, deptId,"PIV");
				
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
					pivModel.setStatusDesc(masterDao.getStatusDescription(status,"PIV"));
				
					if(pivDetail.getPaidAgent()!=null)
					{
						String agentCode = pivDetail.getPaidAgent();
						String branchCode = pivDetail.getPaidBranch();
						Agent agent = masterDao.getAgentById(agentCode,"PIV");
						AgentBranch agentBranch = masterDao.getAgentBranchById(agentCode,branchCode,"PIV");
						if(agent!=null)
							pivDetail.setPaidAgent(agent.getAgentName());
						else
							pivDetail.setPaidAgent(agentCode);
						if(agentBranch!=null)
							pivDetail.setPaidBranch(agentBranch.getAgentBranchName());
						else
							pivDetail.setPaidBranch(branchCode);
					}
				
					//List<PivPaymentGrid> paymentList = pivPaymentDao.getPivPaymentsToPIV(pivNo);
					List<PivPayment> paymentList = pivPaymentDao.getPivPaymentToPIV(pivNo,"PIV");
					pivModel.setPaymentList(paymentList);
				}
			}
		
		
		return new ModelAndView("/viewprint/viewprint" , "pivModel", pivModel);
		
	}
		
		
	
	
	
	@RequestMapping(value = "/viewprint/printInvoice", method = RequestMethod.GET)
	public String printInvoice(Model model, HttpServletRequest request,HttpServletResponse response) {
		
		Connection conn = null;
		boolean canPrint = false;
		String deptId = request.getSession().getAttribute("deptId").toString();
		try 
		{
			conn = getReportDbConnection("PIV");
			System.out.println("create conn printInvoice");			
			HashMap<String, Object> hmParams = new HashMap<String, Object>();
			String pivNo = request.getParameter("pivNo");
			hmParams.put("PIV_NO",pivNo );
			
			Path path = new Path();
			String REPORT_DIRECTORY = path.getReportPath();
			
			List<String> activityList = (List<String>)request.getSession().getAttribute("userActivity");
			
			
			String jrxmlFile = REPORT_DIRECTORY+"Invoice.jrxml";
			String jasperFile = REPORT_DIRECTORY+"Invoice.jasper";
					
			
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
	
	@RequestMapping(value = "/viewprint/printInvoicePaidCopy", method = RequestMethod.GET)
	public String printInvoicePaidCopy(Model model, HttpServletRequest request,HttpServletResponse response) {
		
		Connection conn = null;
		boolean canPrint = false;
		String deptId = request.getSession().getAttribute("deptId").toString();
		try 
		{
			conn = getReportDbConnection("PIV");
			System.out.println("create conn printInvoicePaidCopy");						
			HashMap<String, Object> hmParams = new HashMap<String, Object>();
			String pivNo = request.getParameter("pivNo");
			//String isCopy = request.getParameter("isCopy");
			hmParams.put("PIV_NO",pivNo );
			
			Path path = new Path();
			String REPORT_DIRECTORY = path.getReportPath();
			
			
			
			//gg
			String jrxmlFile = REPORT_DIRECTORY+"InvoicePaidCopy.jrxml";
			String jasperFile = REPORT_DIRECTORY+"InvoicePaidCopy.jasper";
			
			
			JasperReport jasperReport = getCompiledFile(jrxmlFile,jasperFile,request);
			generateReportPDF(response, hmParams, jasperReport, conn); 
			
			conn.close();
			System.out.println("close conn printInvoicePaidCopy");		
			
			
			
		}
		catch (Exception sqlExp) 
		{
			sqlExp.printStackTrace();
		} finally 
		{
			if (conn != null) 
			{
				try 
				{
					conn.close();
					conn = null;
					System.out.println("close conn printInvoicePaidCopy finally");		
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
	
	
	@RequestMapping(value = "/viewprint/printInvoiceCopy", method = RequestMethod.GET)
	public String printInvoiceCopy(Model model, HttpServletRequest request,HttpServletResponse response) {
		
		Connection conn = null;
		boolean canPrint = false;
		String deptId = request.getSession().getAttribute("deptId").toString();
		try 
		{
			conn = getReportDbConnection("PIV");
			System.out.println("create conn printInvoiceCopy");						
			HashMap<String, Object> hmParams = new HashMap<String, Object>();
			String pivNo = request.getParameter("pivNo");
			//String isCopy = request.getParameter("isCopy");
			hmParams.put("PIV_NO",pivNo );
			
			Path path = new Path();
			String REPORT_DIRECTORY = path.getReportPath();
			
			
			List<String> activityList = (List<String>)request.getSession().getAttribute("userActivity");
			
			String jrxmlFile = REPORT_DIRECTORY+"InvoiceCopy.jrxml";
			String jasperFile = REPORT_DIRECTORY+"InvoiceCopy.jasper";
				
			JasperReport jasperReport = getCompiledFile(jrxmlFile,jasperFile,request);
			generateReportPDF(response, hmParams, jasperReport, conn); 
			
			conn.close();
			System.out.println("close conn printInvoiceCopy");		
		}
		catch (Exception sqlExp) 
		{
			sqlExp.printStackTrace();
		} finally 
		{
			if (conn != null) 
			{
				try 
				{
					conn.close();
					conn = null;
					System.out.println("close conn printInvoiceCopy finally");		
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

	
	
	@RequestMapping(value = "/viewprint/printRefundInfo", method = RequestMethod.GET)
	public String printRefundInfo(Model model, HttpServletRequest request,HttpServletResponse response) {
		
		Connection conn = null;
		boolean canPrint = false;
		String deptId = request.getSession().getAttribute("deptId").toString();
		try 
		{
			conn = getReportDbConnection("PIV");
			System.out.println("create conn printRefundInfo");						
			HashMap<String, Object> hmParams = new HashMap<String, Object>();
			String pivNo = request.getParameter("pivNo");
			hmParams.put("PIV_NO",pivNo );
			
			Path path = new Path();
			String REPORT_DIRECTORY = path.getReportPath();
			
			List<String> activityList = (List<String>)request.getSession().getAttribute("userActivity");
			
			
			String jrxmlFile = REPORT_DIRECTORY+"InvoiceRefund.jrxml";
			String jasperFile = REPORT_DIRECTORY+"InvoiceRefund.jasper";
					
			
			JasperReport jasperReport = getCompiledFile(jrxmlFile,jasperFile,request);
			generateReportPDF(response, hmParams, jasperReport, conn); 
			
			conn.close();
			System.out.println("close conn printRefundInfo");		
			
		}
		catch (Exception sqlExp) 
		{
			sqlExp.printStackTrace();
		} finally 
		{
			if (conn != null) 
			{
				try 
				{
					conn.close();
					conn = null;
					System.out.println("close conn printRefundInfo finally");		
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
	
	
	private JasperReport getCompiledFile(String jrxmlFile,String jasperFile, HttpServletRequest request) throws JRException {
		File reportFile = new File(	jasperFile);	
		JasperCompileManager.compileReportToFile(jrxmlFile,jasperFile);
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
}
	
	
	
	
		
