package com.communicate.service;

import javax.persistence.Convert;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.communicate.model.Gender;
import com.communicate.utils.DateConveter;
import com.communicate.utils.GenderConverter;



public class RegistrationForm  {

	/**
	 * 
	 */
	@NotNull(message = "Name cannot be null and smaller than 6 characters")
    @Size(min=6, max=30)
	private String password;
	
	@NotNull(message = "Please confirm password")
    @Size(min=6, max=30)
	private String confirmPassword;
	
	@NotNull
    @Size(min=5, max=30)
	private String firstName;

	@NotNull
    @Size(min=5, max=30)
	private String lastName;

	@Email(message = "Email should be valid")
	private String email;
	
	
	@NotNull
    @Size(min=10, max=10)
	private String mobileNumber;
	
	
	@Convert(converter = GenderConverter.class)
	private Gender gender;
	
	
	private String country;
	
	
	private Long birthDate;
	
	private Gender sexualInterest;
	
	private String day ="28";
	private String month = "01";
	private String year = "1989";
	
	private String roles; 
	
	// TODO date drop down  in UI
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Long getBirthDate() {
		String date = this.day +"/"+ this.month +"/"+ this.year; 
		this.birthDate = new DateConveter().convertToDatabaseColumn(date);
		return this.birthDate;
	}

	public Gender getSexualInterest() {
		return sexualInterest;
	}

	public void setSexualInterest(Gender sexualInterest) {
		this.sexualInterest = sexualInterest;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * @return the roles
	 */
	public String getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(String roles) {
		this.roles = roles;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the confirmPassword
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}

	/**
	 * @param confirmPassword the confirmPassword to set
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
}
