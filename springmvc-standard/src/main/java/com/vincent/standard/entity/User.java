package com.vincent.standard.entity;

import java.util.Date;

import org.springframework.beans.factory.InitializingBean;

//public class User{
public class User implements InitializingBean{
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

	@Override
	public void afterPropertiesSet() throws Exception {
		this.initTime = new Date();
		
	}
	
//	@PostConstruct
//	public void init(){
//		this.initTime = new Date();
//	}
	
	
	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + ", initTime=" + initTime
				+ "]";
	}

	
}
