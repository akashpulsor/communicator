package com.communicate.service;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Service;

import com.communicate.model.Address;
import com.communicate.model.Gender;
import com.communicate.model.User;
import com.communicate.utils.DateConveter;
import com.communicate.utils.GenderConverter;



public class RegistrationForm  {

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
	
	
	@Convert(converter = GenderConverter.class)
	private Gender gender;
	
	
	private String country;
	
	
	private Long birthDate;
	
	private Gender sexualInterest;
	
	private String day ="28";
	private String month = "01";
	private String year = "1989";
	
	// TODO date drop down  in UI
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
	
}
