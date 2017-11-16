package com.communicate.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Image  implements Media  {
	
	
	
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
