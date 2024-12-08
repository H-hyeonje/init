package com.spring.controller;


import java.util.Calendar;


public class zerotime {
		
	public long zerotime() {
		Calendar calendar=Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		long zero=calendar.getTimeInMillis();
	
	  return zero;
	}
	
	
}
