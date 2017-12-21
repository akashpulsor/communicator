package com.communicate.service;

import com.communicate.model.Image;
import com.communicate.model.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class DashBoard {
	
	
	
	private String profilename;
	
	protected User user;
	
	public DashBoard( User user ) {
		this.user = user;
	}

	/**
	 * @return the profilename
	 */
	public String getProfilename() {
		return profilename = this.user.getName();
	}

	/**
	 * @param profilename the profilename to set
	 */
	
}
