/**
 * 
 */
package com.it.dashboard.issue.repo;

import java.util.List;

import com.it.dashboard.admin.domain.PivActivityGrid;
import com.it.dashboard.issue.domain.PivApproval;
import com.it.dashboard.issue.domain.PivApprovalPK;

/**
 * @author Anobiya
 *
 */
public interface PivApprovalDao {
	public PivApproval findById(PivApprovalPK id, String appName);
	
	public List<String> getActivity(String deptId, String userRole, String appName);
	
	public void registerList(String deptId, String userRole, List<PivActivityGrid> activityList, String appName);
	
	public void remove(PivApprovalPK id, String appName);

	void registerList(String deptId, String userRole, String loggedUser,
			List<PivActivityGrid> activityList, String appName);
}