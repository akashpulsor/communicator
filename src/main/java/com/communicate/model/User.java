package com.communicate.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.joda.time.DateTime;

import com.communicate.utils.DateConveter;
import com.communicate.utils.GenderConverter;

@Entity
@Table(name="USER")
public class User extends AbstractEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
   //TODO add asserts	
	@OneToOne
	@JoinColumn(name = "id")
	private Address address;
	
	@NotNull(message = "Name cannot be null and smaller than 6 characters")
    @Size(min=6, max=30)
	@Column
	private String password;
	
	@NotNull
    @Size(min=5, max=30)
	@Column
	private String name;
	
	@Email(message = "Email should be valid")
	@Column
	private String email;
	
	
	@NotNull
    @Size(min=10, max=10)
	@Column
	private String mobileNumber;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@Column
	private String country;
	
	@Column
	private Long birthDate;
	
	@Column
	private Long joinDate = new DateTime().getMillis();
	
	@Enumerated(EnumType.STRING)
	private Gender sexualInterest;


	//@OneToMany(mappedBy="User", cascade=CascadeType.ALL)
	//private List<Media> media;
	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the mobileNumber
	 */
	public String getMobileNumber() {
		return mobileNumber;
	}

	/**
	 * @param mobileNumber the mobileNumber to set
	 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	/**
	 * @return the gender
	 */
	public Gender getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the birthDate
	 */
	public Long getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(Long birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @return the joinDate
	 */
	public Long getJoinDate() {
		return joinDate;
	}

	/**
	 * @param joinDate the joinDate to set
	 */
	public void setJoinDate(Long joinDate) {
		this.joinDate = joinDate;
	}

	public Gender getSexualInterest() {
		return sexualInterest;
	}

	public void setSexualInterest(Gender sexualInterest) {
		this.sexualInterest = sexualInterest;
	}
	
	
	/*@OneToOne(mappedBy = "USER")
	@MapKey(name = )
	private List<User> friends;
	*/
}
