package com.intuit.stockservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intuit.stockservice.model.Stock;

	@Repository
	public interface StockRepository extends JpaRepository<Stock, Long> {
		
		List<Stock> findByStockIdIn(List<Long> stkIds);
		
		Stock findByCompanyName(String companyName);

}
