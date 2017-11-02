package com.communicate.service;

import java.util.Date;

import javax.persistence.Column;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.communicate.model.Gender;
import com.communicate.model.User;


@Service
public class RegistrationForm extends User {
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	

	@NotNull(message = "Name cannot be null and smaller than 6 characters")
    @Size(min=6, max=30)
	private String password;
	
    @NotNull
    @Size(min=5, max=30)
	private String name;
		
	
    @Email(message = "Email should be valid")
	private String email;
	
    
    @NotNull
    @Size(min=10, max=10)
	private String mobileNumber;
	
	
    
	private Gender gender;
	
	
	private String country;
	
	
	private Date birthDate;


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
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


	public Date getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}


	@Override
	public String toString() {
		return "RegistrationForm [password=" + password + ", name=" + name + ", email=" + email + ", mobileNumber="
				+ mobileNumber + ", gender=" + gender + ", country=" + country + ", birthDate=" + birthDate + "]";
	}


}
