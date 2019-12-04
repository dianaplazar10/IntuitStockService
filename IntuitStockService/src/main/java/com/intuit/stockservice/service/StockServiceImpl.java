package com.intuit.stockservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.intuit.stockservice.exceptionhandlers.CustomRuntimeException;
import com.intuit.stockservice.model.Stock;
import com.intuit.stockservice.repository.StockRepository;

@Component
public class StockServiceImpl implements StockService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StockServiceImpl.class);
	
	@Autowired
	private StockRepository stockRepository;

	@Override
	public Stock createStock(Stock stock) {
		Stock existing = stockRepository.findByCompanyName(stock.getCompanyName());
		if(existing == null) {
			Stock stk = stockRepository.save(stock);
			return stk;
		} else {
			throw new CustomRuntimeException("This stock already exists. ");
		}
	}

	@Override
	public Stock getStock(long stockId) {
		Stock stk = stockRepository.findById(stockId).get();
		if(stk==null) {
			throw new CustomRuntimeException("No Stock with the given Stock Id: " 
					+ stockId);
		}
		return stk;
	}

	@Override
	public List<Stock> getAll() {
		List<Stock> listOfStocks = stockRepository.findAll();
		return listOfStocks;
	}

	@Override
	public List<Stock> getStocksIn(List<Integer> stockIds) {
		List<Long> stkIds = stockIds.stream()
		        .mapToLong(Integer::longValue)
		        .boxed().collect(Collectors.toList());
		List<Stock> listOfStocks = stockRepository.findByStockIdIn(stkIds);
		return listOfStocks;
	}
}
