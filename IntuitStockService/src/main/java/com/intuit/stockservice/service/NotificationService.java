package com.intuit.stockservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.intuit.stockservice.model.Notification;

@Service
public interface NotificationService {
	
	Notification createNotif(Notification notification);
	Notification getNotification(long notificationId);
	List<Notification> getAll();

}
