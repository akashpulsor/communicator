package com.communicate.utils;

public abstract class CreateId {
	
	public static String getId() {
		
		return Utils.getEpochMillis().toString();
	}

}
