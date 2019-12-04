package com.intuit.stockservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intuit.stockservice.model.StockServiceUser;

@Repository
public interface UserRepository extends JpaRepository<StockServiceUser, Long> {
	
}