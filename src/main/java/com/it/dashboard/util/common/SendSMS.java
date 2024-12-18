package com.it.dashboard.util.common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class SendSMS {
	 
	public SendSMS()
	{
		
	}
	
	public boolean sendSMSWithDateAsString(List<SMSDataProjectCosting> smsList)
	{
		boolean isSuccess = false;
		System.out.println("sendSMSWithDateAsString start");
		try
		{
			String smsUrl="http://10.128.1.126/SMSServiceJobCosting/api/SaveSMSDetails/SaveSMSDetailsProjectCosting";  
 			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			
			/*
			//Add the Jackson Message converter
	        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
	        // Note: here we are making this converter to process any kind of response, 
	        // not only application/*json, which is the default behaviour
	        jsonConverter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));   
	        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();        
	        messageConverters.add(jsonConverter);  
	        restTemplate.setMessageConverters(messageConverters); 
	        */
		    
	  	     JSONObject jsonObj = new JSONObject();
	  	     jsonObj.put("smsDetailsList", smsList);
 			
	  	     HttpEntity requestEntity = new HttpEntity(jsonObj, headers);
	 			
	  	     RestTemplate restTemplate = new RestTemplate();
	  	     ResponseEntity<smsDetailsResponse> responseEntity = restTemplate.postForEntity(smsUrl, requestEntity, smsDetailsResponse.class);
		
	  	     System.out.println("response sendSMSWithDateAsString ex "+responseEntity.getBody().getEx());
	  	     System.out.println("response sendSMSWithDateAsString status "+responseEntity.getBody().isSuccess());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("sendSMSWithDateAsString end");
		
		return isSuccess;
	}
	
	public boolean sendSMS(List<SMSData> smsList)
	{
		boolean isSuccess = false;
		
		try
		{
			String smsUrl="http://10.128.1.126/SMSAPI/api/SaveSMSDetails/SaveSMSDetails";
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			
			/*
			//Add the Jackson Message converter
	        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
	        // Note: here we are making this converter to process any kind of response, 
	        // not only application/*json, which is the default behaviour
	        jsonConverter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));   
	        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();        
	        messageConverters.add(jsonConverter);  
	        restTemplate.setMessageConverters(messageConverters); 
	        */
		    
	  	     JSONObject jsonObj = new JSONObject();
	  	     jsonObj.put("smsDetailsList", smsList);
 			
	  	     HttpEntity requestEntity = new HttpEntity(jsonObj, headers);
	 			
	  	     RestTemplate restTemplate = new RestTemplate();
	  	     ResponseEntity<smsDetailsResponse> responseEntity = restTemplate.postForEntity(smsUrl, requestEntity, smsDetailsResponse.class);
		
	  	     System.out.println("response ex 3 "+responseEntity.getBody().getEx());
	  	     System.out.println("response status 3 "+responseEntity.getBody().isSuccess());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("calling sms end method 2");
		
		return isSuccess;
	}
}