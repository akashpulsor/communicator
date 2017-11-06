package com.communicate.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;


public class Image extends AbstractEntity implements Media  {
	
	
	int like;
	
	
	String type;
	
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
}
