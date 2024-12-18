/**
 * 
 */
package com.it.dashboard.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.it.dashboard.admin.domain.PivActivityGrid;
import com.it.dashboard.issue.domain.PivApprovalPK;
import com.it.dashboard.issue.domain.PivModel;
import com.it.dashboard.issue.repo.PivApprovalDao;
import com.it.dashboard.master.domain.PivActivity;
import com.it.dashboard.master.repo.MasterDao;


/**
 * @author Anobiya
 *
 */

@Controller
@RequestMapping(value = "admin")
public class UserController {

	@Autowired
	private MasterDao masterDao;

	@Autowired
	private PivApprovalDao pivApprovalDao;
	

	// Method to go to userPrivilege.jsp page
	@RequestMapping(value = "/userPrivilege")
	public ModelAndView userPrivilege(HttpServletRequest request, PivModel pivModel) {
		List<PivActivity> activityList = masterDao.getActivity("PIV");
		ModelAndView model = new ModelAndView("admin/userPrivilege", "pivModel", pivModel);
		model.addObject("activityList", activityList);
		request.getSession().setAttribute("headerName", "Add User Privilege");
		return model;
	}

	@RequestMapping(value = "/AddUserPrivilege", method = RequestMethod.POST)
	public ModelAndView addUser(HttpServletRequest request, @ModelAttribute PivModel pivModel,
			@RequestParam String actionButton) {
		String deptId = pivModel.getPivApproval().getId().getDeptId();
		String userRole = pivModel.getPivApproval().getId().getUserRole();
		
		List<String> activity = pivApprovalDao.getActivity(deptId, userRole,"PIV");
		
		
		
		
		if (actionButton.equals("View")) {
			List<PivActivity> activityList = masterDao.getActivity("PIV");
			for(int i = 0; i <activityList.size(); i++){
				if(activity.contains(activityList.get(i).getActivityCode())){
					pivModel.getActivityGridList().get(i).setAction("Allow");
					
				}
				else
				{
					pivModel.getActivityGridList().get(i).setAction("Deny");
				}
			}
			ModelAndView model = new ModelAndView("admin/userPrivilege");
			model.addObject("activityList", activityList);
			request.getSession().setAttribute("headerName", "Add User Privilege");
			return model;
		}
		if (actionButton.equals("Save")) {
			List<PivActivity> activityList = masterDao.getActivity("PIV");
			int length = pivModel.getActivityGridList().size();
			for (int i = 0; i < length; i++) {
				pivModel.getActivityGridList().get(i).setActivity(activityList.get(i).getActivityCode());
				pivModel.getActivityGridList().get(i).setDescription(activityList.get(i).getDescription2());
				pivModel.getActivityGridList().get(i).setAction(pivModel.getActivityGridList().get(i).getAction());
			}
			List<PivActivityGrid> activityGridList = pivModel.getActivityGridList();
			for(int k = 0; k < activityGridList.size(); k++){
				if(activityGridList.get(k).getAction().equals("Deny") & activity.contains(activityGridList.get(k).getActivity())){
					PivApprovalPK pk = new PivApprovalPK();
					pk.setActivity(activityGridList.get(k).getActivity());
					pk.setDeptId(deptId);
					pk.setUserRole(userRole);
					pivApprovalDao.remove(pk,"PIV");
					
				}
			}
			String loggedUser = (String)request.getSession().getAttribute("loggedUser");
			pivApprovalDao.registerList(deptId, userRole, loggedUser, activityGridList,"PIV");
			
			pivModel.setMessage("User Privilege Added Succesfully !");
			pivModel.setMessageType("SUCCESS");
			ModelAndView model = new ModelAndView("admin/userPrivilege");
			model.addObject("activityList", activityList);
			request.getSession().setAttribute("headerName", "User Privilege Added");
			return model;
		}
		if (actionButton.equals("Clear")) {
			ModelAndView model = new ModelAndView("redirect:/admin/userPrivilege");
			return model;
		}
		return null;
	}	
}
