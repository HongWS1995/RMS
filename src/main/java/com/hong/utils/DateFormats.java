package com.hong.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormats {
	public static String format(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		
	} 
}
