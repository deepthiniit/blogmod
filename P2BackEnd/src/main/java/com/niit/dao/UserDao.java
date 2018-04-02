package com.niit.dao;

import org.springframework.stereotype.Repository;

import com.niit.model.User;

@Repository("userDao")
public interface UserDao {
	void registerUser(User user);
	boolean isEmailUnique(String email);
	User login(User user);//return either 1 user object or null value
	void update (User validUser);
    User getUser(String email);
    void updateUser(User user);
}
