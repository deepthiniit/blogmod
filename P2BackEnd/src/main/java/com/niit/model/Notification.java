package com.niit.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
     

    @Entity
	@Table(name="notification_s180250")
    
public class Notification {
	
    	@Id 
    	@GeneratedValue(strategy=GenerationType.AUTO)
	
    	private int id;
    	private String blogTittle;
	    private String email;  //author of the blogpost
	    private String approvalStatus;  //"rejected"   /   "Approved"
	    private String rejectionReason;
	    private boolean viewed;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBlogTittle() {
		return blogTittle;
	}
	public void setBlogTittle(String blogTittle) {
		this.blogTittle = blogTittle;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	public String getRejectionReason() {
		return rejectionReason;
	}
	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}
	public boolean isViewed() {
		return viewed;
	}
	public void setViewed(boolean viewed) {
		this.viewed = viewed;
	}
	
	

}
