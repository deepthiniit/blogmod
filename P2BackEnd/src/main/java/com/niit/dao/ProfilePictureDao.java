package com.niit.dao;

import org.springframework.stereotype.Repository;

import com.niit.model.ProfilePicture;

@Repository("profilepictureDao")
public interface ProfilePictureDao {
	 void uploadProfilePicture(ProfilePicture profilePicture);
	 ProfilePicture getProfilePicture(String email);

}
