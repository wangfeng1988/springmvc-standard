package com.vincent.standard.beanValidation.test;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.HibernateValidatorConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.vincent.standard.beanValidation.Author;
import com.vincent.standard.beanValidation.Blog;
import com.vincent.standard.beanValidation.Buyer;
import com.vincent.standard.beanValidation.User;

public class BeanValidationTest {

	Validator validator;
	Date date;
	Logger logger = Logger.getLogger(BeanValidationTest.class);
	@Before
	public void setUp() throws Exception {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
		date = new Date();
	}

	@After
	public void tearDown() throws Exception {
	}

	//普通验证
	@Test
	public void testUserValid() {
		User user1 = new User("user1", 10, DateUtils.addDays(date, 10));
		Set<ConstraintViolation<User>> constraintViolations1 = validator.validate(user1);
		assertEquals(2, constraintViolations1.size());
		logger.info("constraintViolations1 : " + constraintViolations1);
		for(ConstraintViolation<User> constraintViolation : constraintViolations1){
			//合并输出错误信息 
			logger.info(constraintViolation.getPropertyPath() + constraintViolation.getMessage());
		}
		User user2 = new User("user1", 18, DateUtils.addDays(date, -10));
		Set<ConstraintViolation<User>> constraintViolations2 = validator.validate(user2);
		assertEquals(0, constraintViolations2.size());
		logger.info("constraintViolations2 : " + constraintViolations2);
	}
	
	//fast fail mode
	@Test
	public void testUserValid2() {
		HibernateValidatorConfiguration configuration = Validation.byProvider(HibernateValidator.class).configure();
		ValidatorFactory factory2 = configuration.addProperty( "hibernate.validator.fail_fast", "true" ).buildValidatorFactory();
		Validator validator2 = factory2.getValidator();

		User user1 = new User("user1", 10, DateUtils.addDays(date, 10));
		Set<ConstraintViolation<User>> constraintViolations1 = validator2.validate(user1);
		assertEquals(1, constraintViolations1.size());
		logger.info("constraintViolations1 : " + constraintViolations1);
		for(ConstraintViolation<User> constraintViolation : constraintViolations1){
			//合并输出错误信息 
			logger.info(constraintViolation.getPropertyPath() + constraintViolation.getMessage());
		}
		
	}
	
	//继承验证
	@Test
	public void testAuthorValid(){
		Author author = new Author();
		author.setAge(18);
		author.setInitTime(DateUtils.addDays(date, -10));
		author.setAuthorName("authorName");
		Set<ConstraintViolation<Author>> constraintViolations1 = validator.validate(author);
		assertEquals(1, constraintViolations1.size());
		logger.info("constraintViolations1 : " + constraintViolations1);
		for(ConstraintViolation<Author> constraintViolation : constraintViolations1){
			//合并输出错误信息 
			logger.info(constraintViolation.getPropertyPath() + constraintViolation.getMessage());
		}
	}
	
	//@valid验证
	@Test
	public void testBlogValid(){
		User user1 = new User("user1", 10, DateUtils.addDays(date, 10));
		Blog blog1 = new Blog();
		blog1.setUser(user1);
		Set<ConstraintViolation<Blog>> constraintViolations1 = validator.validate(blog1);
		assertEquals(3, constraintViolations1.size());
		for(ConstraintViolation<Blog> constraintViolation : constraintViolations1){
			//合并输出错误信息 
			logger.info(constraintViolation.getPropertyPath() + constraintViolation.getMessage());
		}
	}
	
	//验证hibernate 扩展annotation
	@Test
	public void testHibernateBeanValid(){
		Buyer emailbean = new Buyer("aaa", null, null);
		Set<ConstraintViolation<Buyer>> constraintViolations1 = validator.validate(emailbean);
		assertEquals(1, constraintViolations1.size());
		for(ConstraintViolation<Buyer> constraintViolation : constraintViolations1){
			//合并输出错误信息 
			logger.info(constraintViolation.getPropertyPath() + constraintViolation.getMessage());
		}
	}
	
	
	//验证自定义约束
		@Test
		public void testCustomValid(){
			//@Email
			Buyer emailbean = new Buyer("abc@qq.com", "123", null);
			Set<ConstraintViolation<Buyer>> constraintViolations1 = validator.validate(emailbean);
			assertEquals(1, constraintViolations1.size());
			for(ConstraintViolation<Buyer> constraintViolation : constraintViolations1){
				//合并输出错误信息 
				logger.info(constraintViolation.getPropertyPath() + constraintViolation.getMessage());
			}
			
			//@SafeHtml XSS攻击
			Buyer emailbean2 = new Buyer("abc@qq.com", "12312341234", "<script>alert(\"xss\")</script>");
			Set<ConstraintViolation<Buyer>> constraintViolations2 = validator.validate(emailbean2);
			assertEquals(1, constraintViolations2.size());
			for(ConstraintViolation<Buyer> constraintViolation : constraintViolations2){
				//合并输出错误信息 
				logger.info(constraintViolation.getPropertyPath() + constraintViolation.getMessage());
			}
			
			//@SafeHtml 正常情况
			Buyer emailbean3 = new Buyer("abc@qq.com", "12312341234", "<html><p>abc</p></html>");
			Set<ConstraintViolation<Buyer>> constraintViolations3 = validator.validate(emailbean3);
			assertEquals(0, constraintViolations3.size());
			for(ConstraintViolation<Buyer> constraintViolation : constraintViolations3){
				//合并输出错误信息 
				logger.info(constraintViolation.getPropertyPath() + constraintViolation.getMessage());
			}
		}

}
