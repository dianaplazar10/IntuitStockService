package com.intuit.stockservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.intuit.stockservice.exceptionhandlers.CustomRuntimeException;
import com.intuit.stockservice.model.Notification;
import com.intuit.stockservice.service.NotificationService;

@RestController
public class NotificationController {
	
	@Autowired
	private NotificationService notificationService;
	
	@GetMapping(value="/notifications/{notificationId}")
	public ResponseEntity<Notification> getNotification(@PathVariable("notificationId") String notificationId) {
		Notification notification = notificationService.getNotification(Long.parseLong(notificationId));
		if(notification==null) {
			return ResponseEntity.noContent().build(); 
		} 
		return new ResponseEntity<Notification>(notification,HttpStatus.OK);
	}
	
	@GetMapping(value="/notifications")
	public ResponseEntity<List<Notification>> getAllNotifications() {
		List<Notification> listOfNotifs = notificationService.getAll();
		if(listOfNotifs.isEmpty()) {
			return ResponseEntity.noContent().build(); 
		} 
		return new ResponseEntity<List<Notification>>(listOfNotifs,HttpStatus.OK);
	}
		
	@PostMapping(value="/notifications",consumes = {MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Notification> createNotification(@Valid @RequestBody Notification notification) {
		if(notification.getNotificationFactor() == 0) {
			throw new CustomRuntimeException("a valid notification factor attribute is mandatory to create notification");
		}
		Notification notiftn = notificationService.createNotif(notification);
		return new ResponseEntity<Notification>(notiftn,HttpStatus.CREATED);
	}

}
