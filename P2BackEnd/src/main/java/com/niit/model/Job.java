package com.niit.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="job_s180250")


public class Job {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;
	@Column(nullable=false)
	private String jobTittle;
	private String jobDescription;
	@Column(nullable=false)
	private String skillsRequired;
	private String location;
	private String yrsofExp;
	private String companyName;
	private String salary;
	private Date postedOn;
	public Date getPostedOn() {
		return postedOn;
	}
	public void setPostedOn(Date postedOn) {
		this.postedOn = postedOn;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getJobTitle() {
		return jobTittle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTittle = jobTitle;
	}
	public String getJobDescription() {
		return jobDescription;
	}
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	public String getSkillsRequired() {
		return skillsRequired;
	}
	public void setSkillsRequired(String b) {
		this.skillsRequired = b;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getYrsofExp() {
		return yrsofExp;
	}
	public void setYrsofExp(String yrsofExp) {
		this.yrsofExp = yrsofExp;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	
	
}
