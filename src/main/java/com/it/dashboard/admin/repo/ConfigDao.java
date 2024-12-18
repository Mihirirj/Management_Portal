package com.it.dashboard.admin.repo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;

public interface ConfigDao {

	String getConfigValue(String key, String appName);

	//Connection getReportDbConnection(String appName) ;

	/*void generateReportPDF(HttpServletResponse resp, Map parameters,
			JasperReport jasperReport, Connection conn) throws JRException,
			NamingException, SQLException, IOException;

	JasperReport getCompiledFile(String jrxmlFile, String jasperFile,
			HttpServletRequest request) throws JRException;
	
	*/
	
}