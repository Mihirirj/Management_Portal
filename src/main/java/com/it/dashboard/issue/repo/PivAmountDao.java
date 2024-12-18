package com.it.dashboard.issue.repo;

import java.util.List;

import com.it.dashboard.issue.domain.PivAmount;
import com.it.dashboard.issue.domain.PivAmountPK;

public interface PivAmountDao {

	public List<PivAmount> findByPivNo(String pivNo, String appName);
	
	public List<PivAmount> findAllOrderedPivNo(String appName);
    
    public void register(PivAmount pivAmount, String appName);
    
    public void register(PivAmount pivAmount, String nextPivNo, String appName);
    
    public void registerList(List<PivAmount> pivAmountList, String appName);
    
    public void updateList(List<PivAmount> pivAmountList, String appName);
    
    public void registerList(List<PivAmount> pivAmountList, String nextPivNo, String appName);
    
    public void update(PivAmount pivAmount, String appName);
    
    

    public void remove(PivAmountPK id, String appName);

    public PivAmount findById(PivAmountPK id, String appName);

    

}