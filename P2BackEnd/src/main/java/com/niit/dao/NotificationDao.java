package com.niit.dao;

import java.util.List;

import com.niit.model.Notification;

public interface NotificationDao {
	List<Notification>getNotificationsNotViewed(String email);

	List<Notification> getNotifications(String email);

}
