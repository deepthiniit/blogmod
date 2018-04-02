 package com.niit.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.NotificationDao;
import com.niit.model.ErrorClass;
import com.niit.model.Notification;

@RestController
public class NotificationController {
@Autowired
    private NotificationDao notificationDao;

  public NotificationController(){
	System.out.println("NotificationController beanis created");
}
  
  public ResponseEntity<?>getNotificationsNotViewed(HttpSession session){
	  String email=(String)session.getAttribute("loginId");
	  if(email==null){
		  ErrorClass error=new ErrorClass(5,"Unauthorised access....");
		  return new ResponseEntity<ErrorClass>(error,HttpStatus.UNAUTHORIZED);
		  
	  }
	  List<Notification>notificationNotViewed=notificationDao.getNotificationsNotViewed(email);
	  return new ResponseEntity<List<Notification>>(notificationNotViewed,HttpStatus.OK);
	  }
  @RequestMapping(value="/getnotification/:id",method=RequestMethod.GET)
  public ResponseEntity<?> getNotifications(HttpSession session){
	  String email=(String)session.getAttribute("loginId");
	  if(email==null){
		  ErrorClass error=new ErrorClass(5,"Unauthorised access....");
		  return new ResponseEntity<ErrorClass>(error,HttpStatus.UNAUTHORIZED);
		  
	  }
	  List<Notification> notification=notificationDao.getNotifications(email);
	  return new ResponseEntity<List<Notification>>(notification,HttpStatus.OK);
  }
}
