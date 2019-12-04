package com.intuit.stockservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface UserStockNotifService {
	
	void addStocks(int userId, List<Integer> stkIds);
	void subscribeToNotification(int userId, int notificationId,
			char notificationSubscrStatus,
			int notificationFactor);

}
