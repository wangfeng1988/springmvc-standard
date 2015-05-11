package com.vincent.standard.beanValidation;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

public class User{
	@NotNull
	private String name;
	
	@Min(12)
	private int age;
	
	@Past
	private Date initTime;
	
	
	public User() {
	}

	public User(String name, int age, Date initTime) {
		this.name = name;
		this.age = age;
		this.initTime = initTime;
	}

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

	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + ", initTime=" + initTime
				+ "]";
	}

	
}
