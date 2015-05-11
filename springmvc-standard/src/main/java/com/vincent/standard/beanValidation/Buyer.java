package com.vincent.standard.beanValidation;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.SafeHtml;

import com.vincent.standard.beanValidation.custom.Phone;

public class Buyer {
	@Email
	private String name;
	
	@Phone
	private String phone;
	
	@SafeHtml
	private String richText;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRichText() {
		return richText;
	}

	public void setRichText(String richText) {
		this.richText = richText;
	}

	public Buyer(String name, String phone, String richText) {
		super();
		this.name = name;
		this.phone = phone;
		this.richText = richText;
	}
	

}
