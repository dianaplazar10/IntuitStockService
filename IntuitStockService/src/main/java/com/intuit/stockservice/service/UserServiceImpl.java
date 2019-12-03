package com.intuit.stockservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.intuit.stockservice.model.StockServiceUser;
import com.intuit.stockservice.repository.UserRepository;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Override
	public StockServiceUser createUser(StockServiceUser user) {
		StockServiceUser usr = userRepository.save(user);
		return usr;
	}

	@Override
	public StockServiceUser getUser(long userId) {
		StockServiceUser user = userRepository.findById(userId).get();
		return user;
	}

	@Override
	public void removeUser(long userId) {
		userRepository.deleteById(userId);		
	}

}
