package com.communicate.utils;

public class ImageUtils extends CreateId {

	
	public static String getId( String id ) {
	
		String objectId = getId();
		objectId = objectId + id; 
		return objectId;
	}
}
