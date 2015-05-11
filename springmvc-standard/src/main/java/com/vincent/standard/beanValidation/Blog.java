package com.vincent.standard.beanValidation;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class Blog{
	@NotNull
	private String name;
	@Valid
	private User user;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
