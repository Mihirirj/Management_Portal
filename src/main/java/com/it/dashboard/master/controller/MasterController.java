package com.it.dashboard.master.controller;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.it.dashboard.issue.domain.PivApplicant;
import com.it.dashboard.issue.domain.PivDetail;
import com.it.dashboard.issue.domain.PivModel;
import com.it.dashboard.master.domain.Gldeptm;
import com.it.dashboard.master.domain.Gltitlm;
import com.it.dashboard.master.domain.MasterModel;
import com.it.dashboard.master.domain.PivTypeAuth;
import com.it.dashboard.master.domain.PivTypeAuthPK;
import com.it.dashboard.master.repo.GldeptmDao;
import com.it.dashboard.master.repo.MasterDao;
import com.it.dashboard.master.repo.PivTypeAuthDao;



@Controller
@RequestMapping(value = "/master")
public class MasterController {

	@Autowired
	MasterDao masterDao;
	@Autowired
	PivTypeAuthDao pivTypeAuthDao;
	@Autowired
	GldeptmDao gledptmDao;
	
	@ExceptionHandler(NullPointerException.class)
	public String handleNullException(NullPointerException ex) {

		ModelAndView model = new ModelAndView("admin/index");
		model.addObject("errMsg", "this is null Exception.class");
		
		return "redirect:../admin/index";
	}
	
	@RequestMapping(value = "/pivTypes", method = RequestMethod.GET)
	 public ModelAndView view(HttpServletRequest request) {
		String deptId = request.getSession().getAttribute("deptId").toString();
		request.getSession().setAttribute("headerName", "PIV Types Authorization");
		String loggedUser = (String)request.getSession().getAttribute("loggedUser");
		List<String> activityList = (List<String>)request.getSession().getAttribute("userActivity");
		
		if(activityList.contains("AU")){
			MasterModel masterModel = new MasterModel();
			//String deptId = (String)request.getSession().getAttribute("deptId");
			Gldeptm  gldeptm = gledptmDao.findByDeptId(deptId,"PIV");
			Map<String, String> deptIdMap = gledptmDao.getAuthDeptIdMap(loggedUser,"PIV");
			List<PivTypeAuth> list = pivTypeAuthDao.getAuthPivTypesToDeptId(deptId,"PIV");
			masterModel.setDeptIdMap(deptIdMap);
			masterModel.setPivTypeAuthList(list);
			masterModel.setDeptId(deptId);
			
			ModelAndView mv= new ModelAndView("master/savePivTypes","masterModel",masterModel);
			  
			return mv;
		}
		else
		{
			return new ModelAndView("admin/noAccess");
		}
	 }
	
	@RequestMapping(value = "/viewDeptIdPivTypes", method = RequestMethod.POST)
	public ModelAndView viewDeptIdPivTypes(@ModelAttribute("masterModel") MasterModel masterModel,HttpServletRequest request) {
		String deptId1 = (String)request.getSession().getAttribute("deptId");
		try
		{
			String loggedUser = (String)request.getSession().getAttribute("loggedUser");
			String deptId = masterModel.getDeptId();
			List<PivTypeAuth> list = pivTypeAuthDao.getAuthPivTypesToDeptId(deptId,"PIV");
			
			String loggedDeptId = (String)request.getSession().getAttribute("deptId");
			Gldeptm  gldeptm = gledptmDao.findByDeptId(loggedDeptId,"PIV");
			
			Map<String, String>  deptIdMap = gledptmDao.getAuthDeptIdMap(loggedUser,"PIV");
			masterModel.setDeptIdMap(deptIdMap);
			
			
			masterModel.setPivTypeAuthList(list);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return new ModelAndView("master/savePivTypes", "masterModel", masterModel);
	}
	
	@RequestMapping(value = "/deletePivType", method = RequestMethod.POST)
	public ModelAndView deletePivType(@ModelAttribute("masterModel") MasterModel masterModel,HttpServletRequest request) {
		String deptId1 = (String)request.getSession().getAttribute("deptId");
		try
		{
			Map<String, String> deptIdMap   = masterModel.getDeptIdMap();
			String deptId = masterModel.getDeptId();
			String titleCd = request.getParameter("titleCd");
			pivTypeAuthDao.remove(deptId, titleCd,"PIV");
			List<PivTypeAuth> list = pivTypeAuthDao.getAuthPivTypesToDeptId(deptId,"PIV");
			masterModel.setPivTypeAuthList(list);
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return new ModelAndView("master/authPivTypeTbl", "masterModel", masterModel);
	}
	
	@RequestMapping(value = "/addPivType", method = RequestMethod.POST)
	public ModelAndView addPivType(@ModelAttribute("masterModel") MasterModel masterModel,HttpServletRequest request) {
		String deptId1 = (String)request.getSession().getAttribute("deptId");
		try
		{
			String deptId = masterModel.getDeptId();
			String titleCd = request.getParameter("titleCd");
			PivTypeAuth pivTypeAuth = new PivTypeAuth();
			PivTypeAuthPK pivTypeAuthPk = new PivTypeAuthPK();
			pivTypeAuthPk.setDeptId(deptId);
			pivTypeAuthPk.setTitleCd(titleCd);
			pivTypeAuth.setId(pivTypeAuthPk);
			
			String loggedUser = (String)request.getSession().getAttribute("loggedUser");
			pivTypeAuth.setAddUser(loggedUser);
			Timestamp updateTime=new Timestamp(new Date().getTime());
			pivTypeAuth.setAddDate(updateTime);
			
			pivTypeAuthDao.register(pivTypeAuth,"PIV");
			
			List<PivTypeAuth> list = pivTypeAuthDao.getAuthPivTypesToDeptId(deptId,"PIV");
			masterModel.setPivTypeAuthList(list);
			
			
		}
		catch(NullPointerException e)
		{
			throw e;
		}
		catch(Exception e)
		{
			//pivModel.setMessage("System Error.");
			//pivModel.setMessageType("ERROR");
			e.printStackTrace();
		}
		return new ModelAndView("master/authPivTypeTbl", "masterModel", masterModel);
	}
	
	@RequestMapping(value = "/viewAllPivTypes", method = RequestMethod.POST)
	public ModelAndView viewAllPivTypes(@ModelAttribute("masterModel") MasterModel masterModel,HttpServletRequest request) {
		String deptId1 = (String)request.getSession().getAttribute("deptId");
		try
		{
			String deptId = masterModel.getDeptId();
			List<Gltitlm> list = masterDao.getPivTypeObjList(deptId,"PIV");
			masterModel.setPivTypeList(list);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return new ModelAndView("master/pivTypesTbl", "masterModel", masterModel);
	}
		
	
}
