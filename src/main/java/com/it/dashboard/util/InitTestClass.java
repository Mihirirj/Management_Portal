package com.it.dashboard.util;

import org.springframework.beans.factory.annotation.Autowired;

import com.it.dashboard.quartz.repo.TaskDao;


public class InitTestClass {
	//public static boolean isOnDeployRunOnce = false;
	
	@Autowired
	private TaskDao taskdao;
	

	public void init(){
		taskdao.updateExpiredpivs("PIV");
		
		
	}

	

}


