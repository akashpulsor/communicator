package com.communicate.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


public class DateConveter {

	
	public Long convertToDatabaseColumn(String arg0) {
		// TODO Auto-generated method stub
		DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
		DateTime dt = formatter.parseDateTime(arg0);
		long temp = dt.getMillis();
		return temp;
	}

	//TODO ADD TIMEZONE
	
	public String convertToEntityAttribute(Long arg0) {
		// TODO Auto-generated method stub
		DateTime dt = new DateTime(arg0);
		return dt.toString();
	}

}
