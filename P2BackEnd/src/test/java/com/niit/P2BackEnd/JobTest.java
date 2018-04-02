package com.niit.P2BackEnd;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.JobDao;
import com.niit.model.Job;

public class JobTest {
	public static void main(String[] args){
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext config=new AnnotationConfigApplicationContext();
		config.scan("com.niit.*");
		config.refresh();
		Job job=(Job)config.getBean("JobDao");
		
		JobDao jobDao=(JobDao)config.getBean("JobDao");
		
		job.setCompanyName("abc");
		job.setJobTitle("software developer");
		job.setJobDescription("Java Developer");
		job.setSkillsRequired("Sql,Css,Jsp,Html");
		job.setLocation("Bangalore");
		job.setYrsofExp("2years");
		job.setSalary("45,000 per annum");

		jobDao.insertUser(job);
	}

}
