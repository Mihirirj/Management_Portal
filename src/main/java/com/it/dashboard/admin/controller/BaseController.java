package com.it.dashboard.admin.controller;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.it.dashboard.smc.dao.EstimateDao;

@RequestMapping("/welcome")
@Controller
public class BaseController {
	
	

	@Autowired
	private EstimateDao estimateDao;
	
	@RequestMapping(value = "/ww", method = RequestMethod.GET)
	public String welcome11() {
	   /* Map<String,String> divisionList = new LinkedHashMap<String,String>();
	    divisionList.put("DISCO1","DD1");
	    divisionList.put("DISCO2","DD2");
	    divisionList.put("DISCO3","DD3");
	    divisionList.put("DISCO4","DD4");
	    model.addAttribute("divisionList",divisionList);
	    model.addAttribute("selectDivision","Select Division");*/
	    return "admin/index";
	    
	}
	
	@RequestMapping("/")
	public ModelAndView welcome() throws JsonProcessingException {
	    ModelAndView model = new ModelAndView("admin/index");

	    // Populate divisionList
	    Map<String,String> divisionList = new HashMap<String,String>();
	    divisionList.put("DISCO1", "DISTRIBUTION DIVISION 1");
	    divisionList.put("DISCO2", "DISTRIBUTION DIVISION 2");
	    divisionList.put("DISCO3", "DISTRIBUTION DIVISION 3");
	    divisionList.put("DISCO4", "DISTRIBUTION DIVISION 4");

	    // Add divisionList and other attributes to the model
	    model.addObject("divisionList", divisionList);
	    model.addObject("menu", "1");
	    model.addObject("division", "-1");

	    // Your existing logic for populating other model attributes goes here

	    // Account not opened
	    int accountNotOpened = estimateDao.getAccountNotOpened("DISCO1", "MGT");

	    // PIV Paid Not Energised
	    int countUsers = estimateDao.pivPaidNotEnergisedcount("DISCO1", "MGT");

	    // Pending Solar Estimates
	    int pendingSolarEstimatesCount = estimateDao.PendingSolarEatimatesCountByDivision("DISCO1", "MGT");

	    // Pending estimate count
	    int count = estimateDao.pendingEstimateCountByDivision("DISCO1", "MGT");

	    // Number formatting
	    NumberFormat nf = NumberFormat.getInstance();
	    nf.setGroupingUsed(true);

	    // Retrieve data for the chart
	    LocalDate currentDate = LocalDate.now();
	    LocalDate startDate = currentDate.minusMonths(9);
	    LocalDate endDate = currentDate.minusMonths(1);

	    LocalDate loanEndDate = LocalDate.now().minusDays(1); // Yesterday
	    LocalDate loanStartDate = loanEndDate.minusDays(6); // 6 days ago

	    // Loan data for chart
	    List<Object[]> loanApplicationsChartData = estimateDao.getDataForLoanApplications("DISCO1",
	            loanStartDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
	            loanEndDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
	    List<String> daysOfWeek = getLast7Days();

	    // Solar data for chart
	    List<Object[]> solarAccountsChartData = estimateDao.NewSolarAccounts("DISCO1",
	            startDate.toString(), endDate.toString());

	    // New Account data for chart
	    List<Object[]> newAccountsChartData = estimateDao.getDataForNewAccounts("DISCO1",
	            startDate.toString(), endDate.toString());
	    List<String> newAccountsMonths = getMonthsBetween(startDate.toString(), endDate.toString());

	    // Convert data to JSON
	    ObjectMapper objectMapper = new ObjectMapper();
	    String jsonData = objectMapper.writeValueAsString(loanApplicationsChartData);
	    String jsonDaysOfWeek = objectMapper.writeValueAsString(daysOfWeek);
	    String jsonMonths = objectMapper.writeValueAsString(newAccountsMonths);
	    String solarJsonData = objectMapper.writeValueAsString(solarAccountsChartData);
	    String newAccountJsonData = objectMapper.writeValueAsString(newAccountsChartData);

	    // Add JSON data to the model
	    model.addObject("loanData", jsonData);
	    model.addObject("daysOfWeek", jsonDaysOfWeek);
	    model.addObject("solarData", solarJsonData);
	    model.addObject("NewAccountsData", newAccountJsonData);
	    model.addObject("newAccountsMonths", jsonMonths);

	    // Add other model attributes
	    model.addObject("pendEstCount", nf.format(count));
	    model.addObject("accountNotOpened", nf.format(accountNotOpened));
	    model.addObject("PendingSolarEatimatesCount", nf.format(pendingSolarEstimatesCount));
	    model.addObject("pivpaidNotEEnergised", nf.format(countUsers));

	    return model;
	}

	// Method to get dates for the last 7 days excluding today
	private List<String> getLast7Days() {
		List<String> days = new ArrayList<String>();

		// Get the current date
		LocalDate currentDate = LocalDate.now();

		// Calculate the start and end of the 7-day period
		LocalDate loanstartDate = currentDate.minusDays(7); // Subtract 7 days
															// update this
															// to go back 6
															// days, excluding
															// today
		LocalDate loanendDate = currentDate.minusDays(1); // Subtract 1 day for
															// yesterday

		// Iterate through the days of the 7-day period
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate currentDay = loanstartDate;
		while (!currentDay.isAfter(loanendDate)) {
			days.add(currentDay.format(formatter));
			currentDay = currentDay.plusDays(1);
		}

		return days;
	}

	private List<String> getMonthsBetween(String startDate, String endDate) {
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

	}

}
