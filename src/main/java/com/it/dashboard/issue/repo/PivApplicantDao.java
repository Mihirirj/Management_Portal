package com.it.dashboard.issue.repo;

import java.util.List;

import com.it.dashboard.issue.domain.PivApplicant;
import com.it.dashboard.issue.domain.PivApplicantPK;

public interface PivApplicantDao {

	public PivApplicant findByPivNo(String pivNo, String appName) ;
	
	public List<PivApplicant> findAllOrderedPivNo(String appName);
    
    public void register(PivApplicant pivApplicant, String appName);
    
    public void register(PivApplicant pivApplicant, String nextPivNo, String appName);
    
    public void update(PivApplicant pivApplicant, String appName);
    
    public void remove(String pivNo, String appName);

	

	

	
}
