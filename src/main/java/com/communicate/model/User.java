package com.communicate.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="USER")
public class User implements Serializable {

	@Id @GeneratedValue(strategy=GenerationType.AUTO
)		
	@Column
	private int id;
	
	
	
	@OneToOne
	@JoinColumn(name = "id")
	private Address address;
	
	@Column
	private String password;
	
	@Column
	private String name;
		
	@Column
	private String email;
	
	@Column
	private int mobileNumber;
	
	@Column
	private Gender gender;
	
	@Column
	private String country;
	
	@Column
	private Date birthDate;
	
	@Column
	private Date joinDate;
	
	
	/*@OneToOne(mappedBy = "USER")
	@MapKey(name = )
	private List<User> friends;
	*/
}
