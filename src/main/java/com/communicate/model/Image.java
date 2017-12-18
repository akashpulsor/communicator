package com.communicate.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;


public class Image   {
	
	
	
	Integer react ;
	
	
	String type;


	public Integer getReact() {
		return react;
	}


	public void setReact(Integer react) {
		this.react = react;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}
	
	
	
	}
