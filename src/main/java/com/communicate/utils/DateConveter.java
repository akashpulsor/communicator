package com.communicate.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


public class DateConveter {

	


	//TODO ADD TIMEZONE
	
	public String convertToEntityAttribute(Long arg0) {
		// TODO Auto-generated method stub
		DateTime dt = new DateTime(arg0);
		return dt.toString();
	}

}
