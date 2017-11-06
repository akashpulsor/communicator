package com.communicate.model;

public enum Gender {
	
	M,F,O;
	
	private String gender;
	

	public Gender GenderToString( String gender ) {
		switch(gender) {
			case "M":
				return Gender.M; 
			case "F":
				return Gender.F;
			case "O":	
				return Gender.O;
			default:
				throw new IllegalArgumentException("Gender [" + gender + "] noy supported");
		}
		
	}


	public String getGender() {
		return gender;
	}

}
