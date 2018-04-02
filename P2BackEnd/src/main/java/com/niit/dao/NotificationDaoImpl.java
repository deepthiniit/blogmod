package com.niit.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.Notification;
 
@Repository
@Transactional
public class NotificationDaoImpl implements NotificationDao {
   @Autowired
   private SessionFactory sessionFactory;
	public List<Notification> getNotificationsNotViewed(String email) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Notification where email=? and viewed=0");
		List<Notification> notificationsNotViewed=query.list();
		return notificationsNotViewed;
	}
	public List<Notification> getNotifications(String email) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Notification where email=? and viewed=0");
		List<Notification> notification=query.list();
		return notification;
	}
	
	 
	 
	

}
