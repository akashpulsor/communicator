package com.communicate.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.communicate.model.Gender;

@Converter(autoApply = true)
public class GenderConverter implements AttributeConverter<Gender, String> {

	@Override
	public String convertToDatabaseColumn( Gender gender ) {
		// TODO Auto-generated method stub
		return gender.getGender();
	}

	@Override
	public Gender convertToEntityAttribute( String str ) {
		// TODO Auto-generated method stub
		return Gender.valueOf(str);
	}

}
