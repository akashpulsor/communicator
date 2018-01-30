package com.communicate.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.communicate.Exception.StorageException;

public class Utils {
	
	public static Long getEpochMillis() {
		
		return new DateTime().getMillis();
	}
	
	public static Boolean  createDirectory (  Path directoryPath  ) {
		
		if ( ! existsDirectory( directoryPath ) ) {
			try {
	            Files.createDirectories(directoryPath);
	          
	         }
	        catch (IOException e) {
	            return false;
	        }
		}
		
		return true;
		
	}
	
	public static Boolean existsDirectory( Path directoryPath ) {
		return Files.exists(directoryPath);
	}
	
	public static boolean isValidEmailAddress(String email) {
		   boolean result = true;
		   try {
		      InternetAddress emailAddr = new InternetAddress(email);
		      emailAddr.validate();
		   } catch (AddressException ex) {
		      result = false;
		   }
		   return result;
	}
	
	public static   PasswordEncoder getPasswordEncoder() {
		
		return new PasswordEncoder() {

			@Override
			public String encode(CharSequence rawPassword) {
				// TODO Auto-generated method stub
				return rawPassword.toString();
			}

			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				// TODO Auto-generated method stub
				if( rawPassword.equals(encodedPassword) ) {
					return true;
				}
				return false;
			}
			
		};
	}
	
	public static Long convertDateToEpochMillis(String arg0) {
		// TODO Auto-generated method stub
		DateTimeFormatter formatter = DateTimeFormat.forPattern("mm/dd/yyyy");
		DateTime dt = formatter.parseDateTime(arg0);
		long temp = dt.getMillis();
		return temp;
	}

}
