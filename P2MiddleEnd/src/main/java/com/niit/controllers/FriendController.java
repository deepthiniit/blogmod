package com.niit.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.FriendDao;
import com.niit.dao.JobDao;
import com.niit.dao.UserDao;
import com.niit.model.ErrorClass;
import com.niit.model.Friend;
import com.niit.model.User;

@RestController
public class FriendController {
	@Autowired
	private FriendDao friendDao;
	@Autowired
	private UserDao userDao;
@RequestMapping(value="/suggestedUsers",method=RequestMethod.GET)
public ResponseEntity<?> suggestedUsers(HttpSession session){
	String email=(String) session.getAttribute("loginId");
	if(email==null){
		ErrorClass error=new ErrorClass(5,"Unauthorized access...");
		return new ResponseEntity<ErrorClass>(error,HttpStatus.UNAUTHORIZED);
	}
	/*String email="john@abc.com";*/
	List<User> suggestedUsers=friendDao.suggestedUsers(email);
	return new ResponseEntity<List<User>>(suggestedUsers,HttpStatus.OK);
		
}
@RequestMapping(value="/addfriend",method=RequestMethod.POST)
public ResponseEntity<?> addFriend(@RequestBody User toId,HttpSession session){
	String email=(String) session.getAttribute("loginId");
	if(email==null){
		ErrorClass error=new ErrorClass(5,"Unauthorized access...");
		return new ResponseEntity<ErrorClass>(error,HttpStatus.UNAUTHORIZED);
	}
	User fromId=userDao.getUser(email);
	Friend friend=new Friend();
	friend.setFromId(fromId);
	friend.setToId(toId);
	friend.setStatus('P');
	friendDao.addFriend(friend);
	return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
@RequestMapping(value="/pendingrequests",method=RequestMethod.GET)
public ResponseEntity<?> pendingRequests(HttpSession session){
	String email=(String) session.getAttribute("loginId");
	if(email==null){
		ErrorClass error=new ErrorClass(5,"Unauthorized access...");
		return new ResponseEntity<ErrorClass>(error,HttpStatus.UNAUTHORIZED);
	}
	//String email="john@abc.com";
	List<Friend> pendingRequests=friendDao.pendingRequests(email);
	return new ResponseEntity<List<Friend>>(pendingRequests,HttpStatus.OK);
	
}
}

