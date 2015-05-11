package com.vincent.standard.entity;

import java.util.Date;

public class Blog{
	private String name;
	private User user;
	private Date initTime;
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
	
	public Date getInitTime() {
		return initTime;
	}
	public void setInitTime(Date initTime) {
		this.initTime = initTime;
	}
	
	@Override
	public String toString() {
		return "Blog [name=" + name + ", user=" + user + ", initTime="
				+ initTime + "]";
	}
}
