/**
 * Controller class for view
 */
package com.it.dashboard.view.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.it.dashboard.admin.repo.SecurityDao;
import com.it.dashboard.issue.domain.PivAmountGrid;
import com.it.dashboard.issue.domain.PivApplicant;
import com.it.dashboard.issue.domain.PivDetail;
import com.it.dashboard.issue.domain.PivModel;
import com.it.dashboard.issue.repo.PivApplicantDao;
import com.it.dashboard.issue.repo.PivDao;
import com.it.dashboard.master.repo.MasterDao;
import com.it.dashboard.view.repo.SearchDao;
import com.it.dashboard.issue.repo.PivDetailDao;




/**
 * @author Anobiya
 *
 */

@Controller
@RequestMapping(value = "view")
public class ViewController {

	@Autowired
	private SearchDao searchDao;

	@Autowired
	private PivApplicantDao pivApplicantDao;
	
	@Autowired
	MasterDao masterDao;
	
	@Autowired
	SecurityDao securityDao;
	
	@Autowired
	private PivDao pivDao;
	
	@Autowired
	private PivDetailDao PivDetailDao;

	@ExceptionHandler(NullPointerException.class)
	public String handleNullException(NullPointerException ex) {

		ModelAndView model = new ModelAndView("admin/index");
		model.addObject("errMsg", "this is null Exception.class");
		
		//return model;
		return "redirect:../admin/index";
	}
	
	// Method to go to search.jsp page
	@RequestMapping(value = "/Search")
	public ModelAndView search(HttpServletRequest request) {
		String deptId = request.getSession().getAttribute("deptId").toString();
		PivModel pivModel = new PivModel();
		pivModel.setPivTypeList(getPivTypeList(deptId));
		ModelAndView model = new ModelAndView("view/searchForm", "pivModel", pivModel);
		request.getSession().setAttribute("headerName", "Search PIV");
		return model;
	}
	
	@RequestMapping(value = "/SubmitSearchFormAjax", method = RequestMethod.POST)
	public ModelAndView submitFormAjax(@ModelAttribute("pivModel") PivModel pivModel, HttpServletRequest request, BindingResult result) {
		String deptId = request.getSession().getAttribute("deptId").toString();
		if(result.hasErrors()){
			ModelAndView model = new ModelAndView("view/searchForm");
			return model;
		}
		
		pivModel.setPivTypeList(getPivTypeList(deptId));
		
		String pivNo = pivModel.getPivDetail().getId().getPivNo();
		String depositor = pivModel.getPivApplicant().getName();
		String pivType = pivModel.getPivDetail().getTitleCd();
		String idNo = pivModel.getPivApplicant().getIdNo();
		Date issuedFrom = pivModel.getIssuedFrom();
		Date issuedTo = pivModel.getIssuedTo();
		Date paidFrom = pivModel.getPaidFrom();
		Date paidTo = pivModel.getPaidTo();
		String bankRefNo = pivModel.getPivDetail().getBankCheckNo();
		String referenceNo = pivModel.getPivDetail().getReferenceNo();
		
		
		
		if (pivNo.trim().length()==0 && bankRefNo.trim().length()==0  && depositor.trim().length()==0 && 
				pivType.trim().length()==0 && idNo.trim().length()==0  && 
				paidFrom == null && issuedFrom == null && issuedTo == null && paidTo == null && referenceNo.trim().length()==0 )
		{
			
			pivModel.setMessage("Please Enter Search Criteria!!");
			pivModel.setMessageType("ERROR");
			ModelAndView model = new ModelAndView("view/searchResult");
			request.getSession().setAttribute("headerName", "Search PIV");
			return model;
		} else {
			String loggedUser = (String)request.getSession().getAttribute("loggedUser");
			List<String> deptList = securityDao.getAuthorizedCostCenters(loggedUser,"PIV");
			List<PivApplicant> pivList = searchDao.searchPIV(pivNo, pivType, depositor, idNo, issuedFrom, issuedTo, paidFrom, paidTo,bankRefNo,deptList,referenceNo,"PIV" );
			
			/*for(int i=0;i<pivList.size();i++){
				String status = pivList.get(i).getPivDetail().getStatus();
				String pivtype = pivList.get(i).getPivDetail().getTitleCd().trim();
				String statusDescription = masterDao.getStatusDescription(status);
				String typeDescription = masterDao.getPivTypeDescription(pivtype);
				pivList.get(i).getPivDetail().setStatus(statusDescription);
				pivList.get(i).getPivDetail().setTitleCd(typeDescription);
			}*/
			
			ModelAndView model = new ModelAndView("view/searchResult", "pivModel", pivModel);
			model.addObject("pivList", pivList);
			request.getSession().setAttribute("headerName", "Search PIV");
			return model;
		}
	}
		
	@RequestMapping(value = "/SubmitSearchForm", method = RequestMethod.POST)
	public ModelAndView submitForm(@RequestParam String actionButton) {
		if (actionButton.equals("Clear")) {
			ModelAndView model = new ModelAndView("redirect:/view/Search");
			return model;
		}
		return null;
	}
	
@RequestMapping(value = "/viewResult", method=RequestMethod.GET)
	public ModelAndView viewResult(@ModelAttribute("pivModel") PivModel pivModel,@RequestParam("pivNo") String pivNo, HttpServletRequest request){
	ModelAndView model = null;
	String deptId = request.getSession().getAttribute("deptId").toString();
	try
		{
			PivApplicant pivApplicant = pivModel.getPivApplicant();
			pivApplicant = pivApplicantDao.findByPivNo(pivNo,"PIV");
			
			
			PivDetail pivDetail = pivModel.getPivDetail();
			pivDetail = PivDetailDao.findByPivNo(pivNo,"PIV");
	
	
			List<PivAmountGrid> amountList = pivDao.getAcctCodesByPivNo(pivNo,"PIV");
			
	
	
			
			pivModel.setPivDetail(pivDetail);
			pivModel.setPivApplicant(pivApplicant);
			pivModel.setAmountList(amountList);
			System.out.println("piv status code "+pivDetail.getStatus());
			System.out.println("piv status code "+masterDao.getStatusDescription(pivDetail.getStatus(),"PIV"));
			pivModel.setStatusDesc(masterDao.getStatusDescription(pivDetail.getStatus(),"PIV"));
	
			model = new ModelAndView("/view/viewResult" , "pivModel", pivModel);
	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return model;		
	}

	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

	
	
	/*private Map<String, String> getPivTypeList() {
        Map<String, String> pivTypeList = new LinkedHashMap<String, String>();
        pivTypeList.put("ASD", "Asset Disposal");
        pivTypeList.put("CNC", "Customer Name Change");
        pivTypeList.put("CQR", "Cheque Return");
        // pivTypeList.put("PIV-TND", "Tender Deposit");
        return pivTypeList;
    }*/

	@SuppressWarnings("rawtypes")
	private Map<String,String> getPivTypeList(String deptId){
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
			pivTypeList.put(key,val);
			
			
		}*/
		return pivTypeList;
	}
}
