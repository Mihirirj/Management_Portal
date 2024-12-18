package com.it.dashboard.master.repo;

import java.util.List;
import java.util.Map;

import com.it.dashboard.master.domain.Gldeptm;







public interface GldeptmDao {

	Gldeptm findByDeptId(String deptId, String appName);

	List<String> getDeptIdToCompId(String compId, String appName);

	Map<String, String> getDeptIdMap(String compId, String appName) ;

	Map<String, String> getAuthDeptIdMap(String userName, String appName);

	Map<String, String> getAuthDeptIdMap(String userName, String defDeptId, String appName);

	List<String> getAuthDeptIdList(String userName, String defDeptId, String appName);

	
    
}
