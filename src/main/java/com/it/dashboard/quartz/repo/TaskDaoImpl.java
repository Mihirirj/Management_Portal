package com.it.dashboard.quartz.repo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.it.dashboard.util.common.EmSelector;

@Repository
@Transactional

public class TaskDaoImpl extends EmSelector implements TaskDao{
	//@PersistenceContext(unitName = "persistenceUnitPIV")
//private EntityManager getEntityManager(appName);
@Override
public void updateExpiredpivs(String appName){
	try{
		Date expiryDate;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		String d=dateFormat.format(cal.getTime());
		expiryDate = dateFormat.parse(d);
		
		String qryStr = "UPDATE PivDetail p SET p.status='E' WHERE p.expiryDate<=:expiryDate and p.status='N' ";
		Query query = getEntityManager(appName).createQuery(qryStr);
		
		query.setParameter("expiryDate", expiryDate);
		
	}
	catch (Exception ex) {
		ex.printStackTrace();
		
	}
}
}