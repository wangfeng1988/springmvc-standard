package com.vincent.standard.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class User {
	@NotNull(message="name不能为空")
	private String name;
	private int age;
	private Date initTime;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getInitTime() {
		return initTime;
	}
	public void setInitTime(Date initTime) {
		this.initTime = initTime;
	}

	
}
