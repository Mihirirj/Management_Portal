package com.it.dashboard.admin.repo;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.it.dashboard.util.common.EmSelector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Repository
@Transactional
public class ConfigDaoImpl extends EmSelector implements ConfigDao {
	//@PersistenceContext(unitName = "persistenceUnitPIV")
   // private EntityManager getEntityManager(appName);
	
	private static final Log log = LogFactory.getLog(ConfigDaoImpl.class);
	
	
	@Override
	@SuppressWarnings("unchecked")
	public String getConfigValue(String key, String appName){
		//log.error("$$$$$$$$$ getConfigValue");
		String value = null;
		//try {
        	String qryStr = "select p.codeValue from PivConfig p where p.code = :key ";
			Query query = getEntityManager(appName).createQuery(qryStr);
			query.setParameter("key", key);
			//System.out.println("em obj getConfigValue "+getEntityManager(appName));
			
			List<String> list = query.getResultList();
			if (list.size() == 1)
	        	value = list.get(0).trim();
						
			
        /*} catch (Exception ex) {
			ex.printStackTrace();
			
		}*/
			//log.error("$$$$$$$$$ end getConfigValue");
		return value;
	}
	
	/*
	@Override
	public Connection getReportDbConnection(String appName) 
	{
		Connection conn = null;
		String connStr = getConfigValue("REPORT_DB_CON_STR", appName);
		String user = getConfigValue("REPORT_DB_USER", appName);
		String pwd = getConfigValue("REPORT_DB_PWD", appName);
		try 
		{
		 	Class.forName("oracle.jdbc.driver.OracleDriver");
	 	
			
			/*
			conn = DriverManager.getConnection(connStr,user,pwd);*/
			
			//String connStr = "jdbc:oracle:thin:@10.128.0.56:1521:hqorad1";
			//String user = "dacons12";
			//String pwd = "dacons12";
			/*conn = DriverManager.getConnection(connStr,user,pwd);
			 
		} catch (Exception e) {
	 		e.printStackTrace(); 
	 	}  
		return conn;	
	}*/
	
	/*
	@Override
	public void generateReportPDF (HttpServletResponse resp, Map parameters, JasperReport jasperReport, Connection conn)throws JRException, NamingException, SQLException, IOException {
			byte[] bytes = null;
			bytes = JasperRunManager.runReportToPdf(jasperReport,parameters,conn);
			resp.reset();
			resp.resetBuffer();
			resp.setContentType("application/pdf");
			resp.setContentLength(bytes.length);
			ServletOutputStream ouputStream = resp.getOutputStream();
			ouputStream.write(bytes, 0, bytes.length);
			ouputStream.flush();
			ouputStream.close();
	} 
	
	@Override
	public JasperReport getCompiledFile(String jrxmlFile,String jasperFile, HttpServletRequest request) throws JRException {
		File reportFile = new File(	jasperFile);	
		JasperCompileManager.compileReportToFile(jrxmlFile,jasperFile);
		JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportFile.getPath());
		return jasperReport;
	} 
*/
}
	