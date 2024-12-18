package com.it.dashboard.admin.controller;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;














import java.util.Calendar;
import com.it.dashboard.admin.repo.SecurityDao;
import com.it.dashboard.smc.dao.EstimateDao;



@Controller
@RequestMapping(value = "/login")
public class LoginController {

	
	
	@Autowired
	private EstimateDao estimateDao;
	
	@Autowired
	private SecurityDao securityDao;

	
    
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
	
	//calling dashboard after sign in
	@RequestMapping(value = "/dashboard",  method = RequestMethod.POST)
	public ModelAndView viewDashboard(HttpServletRequest request) {
		System.out.println("redirect to dashboard"+request.getParameter("userName"));
		String userName = request.getParameter("userName");
		
		boolean isValidLogin = securityDao.validateLogin(request.getParameter("userName"), request.getParameter("password"),"MGT");
		ModelAndView model = new ModelAndView("admin/index");
		if(isValidLogin)
		{
			System.out.println("valid login!");
			model = new ModelAndView("admin/dashboard");
			
			NumberFormat nf = NumberFormat.getInstance();
		    nf.setGroupingUsed(true);
		    
			String division = request.getParameter("divisionSelect");
			String division_name = "";
			if(division.equals("DISCO1"))
				division_name = "DISTRIBUTION DIVISION 1";
			if(division.equals("DISCO2"))
				division_name = "DISTRIBUTION DIVISION 2";
			if(division.equals("DISCO3"))
				division_name = "DISTRIBUTION DIVISION 3";
			if(division.equals("DISCO4"))
				division_name = "DISTRIBUTION DIVISION 4";
			request.getSession().setAttribute("DIVISION", division);
			request.getSession().setAttribute("DIVISION_NAME", division_name);
			request.getSession().setAttribute("USER_NAME", userName);
			
			getDashboardComponents(division,model);
	
			model.addObject("menu","1");
		}
		else
		{
			 Map<String,String> divisionList = new HashMap<String,String>();
			    divisionList.put("DISCO1","DISTRIBUTION DIVISION 1");
			    divisionList.put("DISCO2","DISTRIBUTION DIVISION 2");
			    divisionList.put("DISCO3","DISTRIBUTION DIVISION 3");
			    divisionList.put("DISCO4","DISTRIBUTION DIVISION 4");
			    model.addObject("divisionList",divisionList);
			    model.addObject("division","-1");
			System.out.println("invalid login!");
		}
	    return model;
	}
	
	//calling dashboard after sign in
		@RequestMapping(value = "/dashboardmenu",  method = RequestMethod.GET)
		public ModelAndView viewDashboardMenu(HttpServletRequest request) {
			System.out.println("calling to dashboard from mainmenu");
			ModelAndView model = new ModelAndView("admin/dashboard");
			
			request.getSession().getAttribute("USER_NAME").toString();
		    
			String division = request.getSession().getAttribute("DIVISION").toString();
						
			getDashboardComponents(division,model);

			model.addObject("menu","1");
		    return model;
		}
	
	//Signout method
	@RequestMapping(value = "/signout",  method = RequestMethod.GET)
	public ModelAndView signout(HttpServletRequest request) 
	{
		request.getSession().removeAttribute("DIVISION");
		request.getSession().removeAttribute("DIVISION_NAME");
		request.getSession().removeAttribute("USER_NAME");
		ModelAndView model = new ModelAndView("admin/index");
		Map<String,String> divisionList = new HashMap<String,String>();
	    divisionList.put("DISCO1","DISTRIBUTION DIVISION 1");
	    divisionList.put("DISCO2","DISTRIBUTION DIVISION 2");
	    divisionList.put("DISCO3","DISTRIBUTION DIVISION 3");
	    divisionList.put("DISCO4","DISTRIBUTION DIVISION 4");
	    model.addObject("divisionList",divisionList);
	    model.addObject("menu","1");
	    model.addObject("division","-1");
		return model;
	}
	 
	 
/*	private List<String> getLast7Days(Date loanStartDate, Date loanEndDate) {
		List<String> days = new ArrayList<String>();

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM- HH:mm:ss");
		while (loanStartDate==loanEndDate) {
			Calendar calendar2 = Calendar.getInstance();
			calendar2.setTime(loanStartDate);
			calendar2.add(Calendar.DATE, 1);
			loanStartDate = calendar2.getTime();
			days.add(df.format(loanStartDate));
			//currentDay = currentDay.plusDays(1);
		}

		return days;
	}*/
	
	
	
	
	private List<String> getLast7Days(Date loanStartDate, Date loanEndDate) {
		List<String> days = new ArrayList<String>();
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    while (loanStartDate.before(loanEndDate) || loanStartDate.equals(loanEndDate)) {
	        days.add(df.format(loanStartDate));
	        Calendar calendar2 = Calendar.getInstance();
	        calendar2.setTime(loanStartDate);
	        calendar2.add(Calendar.DATE, 1);
	        loanStartDate = calendar2.getTime();
	    }
	    return days;
	}
	
	
	
	
	

	/*private List<String> getMonthsBetween(String startDate, String endDate) {
		List<String> months = new ArrayList<String>();
		LocalDate start = LocalDate.parse(startDate);
		LocalDate end = LocalDate.parse(endDate);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM",
				Locale.ENGLISH); // Format to include only the month
		LocalDate current = start;
		while (!current.isAfter(end)) {
			months.add(current.format(formatter));
			current = current.plusMonths(1);
		}
		return months;

	}*/
	
	
	private void getDashboardComponents(String division, ModelAndView model)
	{
		NumberFormat nf = NumberFormat.getInstance();
	    nf.setGroupingUsed(true);
	    
		// Pending estimate count
		int pendEstCount = estimateDao.pendingEstimateCountByDivision(division, "MGT");
		model.addObject("pendEstCount",nf.format(pendEstCount));
		
		// PIV Paid Not Energised
		int pivpaidNotEnergised = estimateDao.pivPaidNotEnergisedcount(division, "MGT");
		model.addObject("pivpaidNotEnergised", nf.format(pivpaidNotEnergised));
		
		// Account not opened
		int accountNotOpened = estimateDao.getAccountNotOpened(division, "MGT");
		model.addObject("accountNotOpened", nf.format(accountNotOpened));

		// Pending Solar Estimates
		int pendingSolarEatimatesCount = estimateDao.PendingSolarEatimatesCountByDivision(division, "MGT");
		model.addObject("pendingSolarEatimatesCount",nf.format(pendingSolarEatimatesCount));
					
					
		// Retrieve data for the chart
		Date endDate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -8);
		Date startDate = calendar.getTime();
		
		Calendar calendar2 = Calendar.getInstance();
		calendar2.add(Calendar.DATE, -1);
		Date loanendDate = calendar2.getTime();
		
		Calendar calendar3 = Calendar.getInstance();
		calendar3.add(Calendar.DATE, -8);
		Date loanstartDate = calendar3.getTime();
		
		//LocalDate loanendDate = LocalDate.now().minusDays(1); // Yesterday
		//LocalDate loanstartDate = loanendDate.minusDays(6); // 6 days ago

		//Loan
	/*	List<Object[]> loanApplicationsChartData = estimateDao.getDataForLoanApplications(division, loanstartDate,	loanendDate);
		List<String> daysOfWeek = getLast7Days(loanstartDate,	loanendDate);
		
        // Solar
		List<Object[]> solarAccountschartData = estimateDao.NewSolarAccounts(division, startDate, endDate);	
	*/	
		
		// New Account
		//List<Object[]> NewAccountschartData = estimateDao.getDataForNewAccounts(division, startDate.toString(),endDate);
		// Month
		//List<String> NewAccountsMonths = getMonthsBetween(startDate.toString(),	endDate.toString());

	/*	ObjectMapper objectMapper = new ObjectMapper();
		String jsonData = "[]";
		String jsonDaysOfWeek = "[]";
		String solarjsonData = "[]";
		String newAccountJsonData = "[]";
		String jsonMonths = "[]";
					
		try {
			jsonData = objectMapper.writeValueAsString(loanApplicationsChartData);
			jsonDaysOfWeek = objectMapper.writeValueAsString(daysOfWeek);

			//jsonMonths = objectMapper.writeValueAsString(NewAccountsMonths);
			//newAccountJsonData = objectMapper.writeValueAsString(NewAccountschartData);
			//solarjsonData = objectMapper.writeValueAsString(solarAccountschartData);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		// Add JSON data to the model
		model.addObject("loanData", jsonData);
		model.addObject("daysOfWeek", jsonDaysOfWeek);

		//model.addObject("solarData", solarjsonData);
		//model.addObject("NewAccountsData", newAccountJsonData);
		//model.addObject("newAccountsMonths", jsonMonths);
	}
*/
	/****************** Private Methods *********************************/

}
}
