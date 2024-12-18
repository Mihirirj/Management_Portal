package com.it.dashboard.master.repo;

import java.util.List;

import com.it.dashboard.master.domain.Gltitlm;
import com.it.dashboard.master.domain.PivTypeAuth;






public interface PivTypeAuthDao {

	
    public void register(PivTypeAuth pivTypeAuth, String appName);
    
    public void update(PivTypeAuth pivTypeAuth, String appName);
    
    public void remove(String deptId,String titleCd, String appName);

    PivTypeAuth findByReferenceNo(String deptId, String titleCd, String appName);

    List<PivTypeAuth> getAuthPivTypesToDeptId(String deptId, String appName);
}
