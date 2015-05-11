package com.vincent.standard.beanValidation;

import javax.validation.constraints.NotNull;

public class Author extends User{
	@NotNull
	private String authorName;

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
	
}
