package com.intuit.stockservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.intuit.stockservice.dto.AppConstants;
import com.intuit.stockservice.dto.UserStkNotifRequestDto;
import com.intuit.stockservice.exceptionhandlers.CustomRuntimeException;
import com.intuit.stockservice.service.UserStockNotifService;

@RestController
public class UserStkNotifMappingController {
	
	@Autowired
	private UserStockNotifService userStockNotifService;
	
	//addUserStocks
	//subscribe_userIdNotifId	
	
	@PostMapping(value="/addstocks")
	public ResponseEntity<Object> addStocksToUser(@RequestBody UserStkNotifRequestDto usnDto) {
		if(usnDto.getUserId()==0) {
			throw new CustomRuntimeException("Invalid User Id:" + usnDto.getUserId());
		}
		if(usnDto.getStockIds().isEmpty()) {
			throw new CustomRuntimeException("No Stocks to add to the User account with id :" + usnDto.getUserId());
		}
		userStockNotifService.addStocks(usnDto.getUserId(), usnDto.getStockIds());
		return ResponseEntity.ok().build(); 
	}
	
	@PostMapping(value="/subscribe")
	public ResponseEntity<Object> subsNotification(@RequestBody UserStkNotifRequestDto usnDto) {
		if(usnDto.getUserId()==0) {
			throw new CustomRuntimeException("Invalid User Id");
		}
		if(usnDto.getNotifId()<=0) {
			throw new CustomRuntimeException("Invalid Notification Id");
		}
		if(!(usnDto.getNotifSubsStatus() == AppConstants.NOTIF_STAT_N 
				|| usnDto.getNotifSubsStatus() == AppConstants.NOTIF_STAT_Y)) {
			throw new CustomRuntimeException("Invalid Subscription status value(notifSubsStatus). It should be either 'Y' or 'N'");
		}
		if((usnDto.getNotifSubsStatus() == AppConstants.NOTIF_STAT_Y)
				&& (usnDto.getNotificationFactor()==0)) {
			throw new CustomRuntimeException("Invalid Notification factor value(notificationFactor) : " 
												+ usnDto.getNotificationFactor());
		}
		userStockNotifService.subscribeToNotification(usnDto.getUserId(), 
								usnDto.getNotifId(), usnDto.getNotifSubsStatus(),usnDto.getNotificationFactor());
		return ResponseEntity.ok().build();
	}

}
