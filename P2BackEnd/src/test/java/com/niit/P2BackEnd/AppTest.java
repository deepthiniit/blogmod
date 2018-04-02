package com.niit.P2BackEnd;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.UserDao;
 
	import com.niit.model.User;

	public class AppTest {

		public static void main(String[] args) {
			
			//@SuppressWarnings("resource")
			AnnotationConfigApplicationContext config=new AnnotationConfigApplicationContext();
			config.scan("com.niit.*");
			config.refresh();
			//User user=(User)config.getBean("user");
			
//			UserDao userDao=(UserDao)config.getBean("userDao");
//			
//			user.setFirstname("HARISH");
//			user.setRole("ROLE_ADMIN"); 
//			user.setEmail("harish@gmail.com");			 
//	        user.setPassword("123");
//	        userDao.registerUser(user);
			
			
			
		}
	}
