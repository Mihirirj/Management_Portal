package com.it.dashboard.util.common;


import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.StringTokenizer;

public class CurrencyToWords {
	
	private static final String[] tensNames = {
		"",
		" Ten",
		" Twenty",
		" Thirty",
		" Forty",
		" Fifty",
		" Sixty",
		" Seventy",
		" Eighty",
		" Ninety"
	};
	
	private static final String[] numNames = {
		"",
		" One",
		" Two",
		" Three",
		" Four",
		" Five",
		" Six",
		" Seven",
		" Eight",
		" Nine",
		" Ten",
		" Eleven",
		" Twelve",
		" Thirteen",
		" Fourteen",
		" Fifteen",
		" Sixteen",
		" Seventeen",
		" Eighteen",
		" Nineteen"
	};
	
	private static String convertLessThanOneThousand(int number) {
		String soFar;
		
		if (number % 100 < 20){
			soFar = numNames[number % 100];
			number /= 100;
		}
		else {
			soFar = numNames[number % 10];
			number /= 10;
			
			soFar = tensNames[number % 10] + soFar;
			number /= 10;
		}
		if (number == 0) return soFar;
		return numNames[number] + " Hundred" + soFar;
	}
	
	
	private static String convertNumber(long number) {
		// 0 to 999 999 999 999
		if (number == 0) { return "Zero"; }
		
		String snumber = Long.toString(number);
		
		// pad with "0"
		String mask = "000000000000";
		DecimalFormat df = new DecimalFormat(mask);
		snumber = df.format(number);
		
		// XXXnnnnnnnnn 
		int billions = Integer.parseInt(snumber.substring(0,3));
		// nnnXXXnnnnnn
		int millions  = Integer.parseInt(snumber.substring(3,6)); 
		// nnnnnnXXXnnn
		int hundredThousands = Integer.parseInt(snumber.substring(6,9)); 
		// nnnnnnnnnXXX
		int thousands = Integer.parseInt(snumber.substring(9,12));    
		
		String tradBillions;
		switch (billions) {
		case 0:
			tradBillions = "";
			break;
		case 1 :
			tradBillions = convertLessThanOneThousand(billions) 
				+ " Billion ";
			break;
		default :
			tradBillions = convertLessThanOneThousand(billions) 
				+ " Billion ";
		}
		String result =  tradBillions;
		
		String tradMillions;
		switch (millions) {
		case 0:
			tradMillions = "";
			break;
		case 1 :
			tradMillions = convertLessThanOneThousand(millions) 
				+ " Million ";
			break;
		default :
			tradMillions = convertLessThanOneThousand(millions) 
				+ " Million ";
		}
		result =  result + tradMillions;
		
		String tradHundredThousands;
		switch (hundredThousands) {
		case 0:
			tradHundredThousands = "";
			break;
		case 1 :
			tradHundredThousands = "One Thousand ";
			break;
		default :
			tradHundredThousands = convertLessThanOneThousand(hundredThousands) 
				+ " Thousand ";
		}
		result =  result + tradHundredThousands;
		
		String tradThousand;
		tradThousand = convertLessThanOneThousand(thousands);
		result =  result + tradThousand;
		
		// remove extra spaces!
		return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
	}
	
	
	public static String convert(double currency)
	{
		NumberFormat nf = NumberFormat.getInstance();
		nf.setGroupingUsed(false);
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		
		String currencyWords = "";
		String valStr = nf.format(currency);
		StringTokenizer st = new StringTokenizer(valStr,".");
		String intPart = st.nextToken();
		String decimalPart = st.nextToken();
		if (decimalPart.length() == 1)
        {
              decimalPart = decimalPart + "0";
        }
		
		long intPartLong = (new Long(intPart)).longValue();
		long decimalPartLong = (new Long(decimalPart)).longValue();
		String intPartStr = convertNumber(intPartLong);
		
		if(decimalPartLong==0)
		{
			currencyWords = intPartStr.trim()+" Rupees Only.";
		}
		else
		{
			String decimalPartStr = convertNumber(decimalPartLong);
			currencyWords = intPartStr.trim()+" Rupees and "+decimalPartStr.trim()+" Cents Only.";
		}
		return currencyWords;
	}
	
	
	
}
