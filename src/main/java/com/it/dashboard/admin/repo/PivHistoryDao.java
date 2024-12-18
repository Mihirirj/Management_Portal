package com.it.dashboard.admin.repo;

import java.sql.Timestamp;
import java.util.List;

import com.it.dashboard.admin.domain.PivHistory;

public interface PivHistoryDao {
	
	public List<PivHistory> findHistory(String pivNo, String deptId, String appName);
	
	public void saveHistory(String deptId,String pivNo,String status, String appName);

	String getNextHistoryCode(String pivNo, String appName);

	void register(PivHistory pivHistory, String appName);
	
}