package com.communicate.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.OneToOne;

@Embeddable
public class Address {
	
	
	@Column(length = 255, nullable = true)
	private String city;
	
	@Column(length = 255, nullable = true)
	private String state;
	
	@Column(length = 255, nullable = true)
	private String flat;

}
