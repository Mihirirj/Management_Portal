/**
 * 
 */
package com.it.dashboard.view.repo;

import java.util.Date;
import java.util.List;

import com.it.dashboard.issue.domain.PivApplicant;
import com.it.dashboard.issue.domain.PivDetail;

/**
 * @author Anobiya
 *
 */
public interface SearchDao {
		

	List<PivApplicant> searchPIV(String pivNo, String pivType, String name,
			String idNo, Date issuedFrom, Date issuedTo, Date paidFrom,
			Date paidTo, String bankRefNo, List<String> authCostCenterList, String referenceNo, String appName);

	

}
