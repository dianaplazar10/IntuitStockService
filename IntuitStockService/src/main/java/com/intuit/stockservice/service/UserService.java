package com.intuit.stockservice.service;

import org.springframework.stereotype.Service;

import com.intuit.stockservice.model.StockServiceUser;

@Service
public interface UserService {
	
	StockServiceUser createUser(StockServiceUser user);
	StockServiceUser getUser(long userId);
	void removeUser(long parseLong);

}