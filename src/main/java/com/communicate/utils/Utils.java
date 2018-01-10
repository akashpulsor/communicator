package com.communicate.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.joda.time.DateTime;

import com.communicate.Exception.StorageException;

public class Utils {
	
	public static Long getEpochMillis() {
		
		return new DateTime().getMillis();
	}
	
	public static void createDirectory (  Path directoryPath  ) {
		
		if ( ! existsDirectory( directoryPath ) ) {
			try {
	            Files.createDirectories(directoryPath);
	           
	        }
	        catch (IOException e) {
	            throw new StorageException("Could not initialize storage", e);
	        }
		}
		
	}
	
	public static Boolean existsDirectory( Path directoryPath ) {
		return Files.exists(directoryPath);
	}
	
	
	

}
