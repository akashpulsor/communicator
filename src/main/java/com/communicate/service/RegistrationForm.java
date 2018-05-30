package com.communicate.service;

import java.util.Date;

import javax.persistence.Convert;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.communicate.model.Gender;
import com.communicate.model.Role;
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
    @Size(min=1)
	private String firstName;

	@NotNull
    @Size(min=1)
	private String lastName;

	@Email(message = "Email should be valid")
	private String email;
	
	
	@NotNull
    @Size(min=10, max=10)
	private String mobileNumber;
	
	
	@Convert(converter = GenderConverter.class)
	private Gender gender;
	
	
	private String country;
	
	
	private String birthDate;
	
	private Gender sexualInterest;
	
	private Role roles; 
	
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

	

	public Gender getSexualInterest() {
		return sexualInterest;
	}

	public void setSexualInterest(Gender sexualInterest) {
		this.sexualInterest = sexualInterest;
	}

	/**
	 * @return the roles
	 */
	public Role getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(Role roles) {
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

	/**
	 * @return the birthDate
	 */
	public String getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	
	
}
