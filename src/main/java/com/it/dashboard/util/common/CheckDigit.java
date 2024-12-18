package com.it.dashboard.util.common;

import java.util.Calendar;
import java.util.StringTokenizer;

public class CheckDigit
{
	public CheckDigit()
	{
		
	}
	
	
	
	private int checkdigit(String idWithoutCheckdigit)
	{
		
		int c= -1;
		int len = idWithoutCheckdigit.length();
		int k = 2;
		int sum = 0;
		int oddSum = 0;
		int evenSum = 0;
		for (int i = 1;i<=len;i++)
		{
			
			String s = idWithoutCheckdigit.substring(i-1,i);
			int j = Integer.parseInt(s);
			if(i%2==0)
			{
				evenSum = evenSum+j;
			}
			else
			{
				oddSum = oddSum+j;
				}
		}
		
		oddSum = oddSum*3;
		sum = oddSum + evenSum;
		int dividedInt = 10;
		int rem = sum%dividedInt;
		
		c = dividedInt - rem;
		if(c==10)
			c = 0;
		
		return c;
			
		
	}
	
	public String getBankCheckNo(String dpetId,String pivTypeDigit, String serialNo)
	{
		String pivNo = null;
		StringTokenizer st = new StringTokenizer(dpetId,".");
		Calendar cal = Calendar.getInstance();
		String year = String.valueOf(cal.get(Calendar.YEAR)).substring(2, 4);
		String pivWithoutCheckdigit = st.nextToken()+st.nextToken()+pivTypeDigit+year+serialNo;
		int checkDigit = checkdigit(pivWithoutCheckdigit);
		pivNo = pivWithoutCheckdigit+Integer.toString(checkDigit);
		return pivNo;
		
	}
	
	public static void main(String[] args) {
		CheckDigit cd = new CheckDigit();
		
		int i = cd.checkdigit("4453052500001");
		
	}
}