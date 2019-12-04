package com.intuit.stockservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.intuit.stockservice.exceptionhandlers.CustomRuntimeException;
import com.intuit.stockservice.model.Notification;
import com.intuit.stockservice.repository.NotificationRepository;

@Component
public class NotificationServiceImpl implements NotificationService {
	
	@Autowired
	private NotificationRepository notifRepo;

	@Override
	public Notification createNotif(Notification notification) {
		Notification notif = notifRepo.save(notification);
		return notif;
	}

	@Override
	public Notification getNotification(long notificationId) {
		Notification notif = notifRepo.findById(notificationId).get();
		if(notif==null) {
			throw new CustomRuntimeException("No Notification with the given Notification Id: " 
							+ notificationId);
		}
		return notif;
	}

	public List<Notification> getAll() {
		List<Notification> listOfNotifs = notifRepo.findAll();
		return listOfNotifs;
	}
	
}