package com.vincent.standard.spring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.vincent.standard.entity.Blog;
import com.vincent.standard.entity.User;

public class SpringTest {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		//BeanFactory applicationContext = new ClassPathXmlApplicationContext("classpath*:/**/*.xml");
		BeanFactory applicationContext = new ClassPathXmlApplicationContext("classpath*:bean.xml");
		User user = applicationContext.getBean("user", User.class);
		System.out.println(user);
		
		Blog blog = applicationContext.getBean("blog", Blog.class);
		System.out.println(blog);
	}
}
