package com.communicate.utils;

public class ImageUtils extends CreateId {

	
	public static String getId( Long id ) {
	
		String objectId = getId();
		objectId = objectId + id.toString(); 
		return objectId;
	}
}
