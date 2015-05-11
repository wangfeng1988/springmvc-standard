package com.vincent.standard.beanValidation.custom;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone, String>{

	@Override
	public void initialize(Phone constraintAnnotation) {
		
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value == null)return true;
		Pattern pattern = Pattern.compile("^(\\d){11}$");
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	}

}
