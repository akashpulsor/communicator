package com.communicate.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "ADDRESS")
public class Address {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO
	)
	private long id ;
	
	@OneToOne(mappedBy = "address")
	private User user;
	
	@Column(length = 255, nullable = true)
	private String city;
	
	@Column(length = 255, nullable = true)
	private String state;
	
	@Column(length = 255, nullable = true)
	private String flat;

}
