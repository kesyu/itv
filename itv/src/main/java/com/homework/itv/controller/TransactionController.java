package com.homework.itv.controller;

import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.homework.itv.bean.SpecialPrice;
import com.homework.itv.bean.StockKeepingUnit;
import com.homework.itv.bean.Transaction;
import com.homework.itv.service.TransactionService;

/**
 * 
 * @author Eva Balazsfalvi
 * 
 * Transaction endpoint controller.
 *
 */
@RestController
public class TransactionController {
	private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);
	
	@Autowired
	private TransactionService transactionService;

	/**
	 * Creates a checkout transaction.
	 * @param stockKeepingUnits Items with prices that can be bought.
	 * @return {@link ResponseEntity} with a status and a body with the id of created id.
	 */
	@RequestMapping(path="/startTransaction",
					method={RequestMethod.POST},
					consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,
					produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<?> startTransaction(@RequestBody Set<StockKeepingUnit> stockKeepingUnits) {
		logger.info("\"startTransaction endpoint was called.");
		if (stockKeepingUnits != null && !stockKeepingUnits.isEmpty()) {
			String transactionId = transactionService.create(stockKeepingUnits);
			return ResponseEntity.ok(transactionId + " transaction started.");
		} else {
			logger.error("Null or empty stock keeping unit list.");
			return ResponseEntity.badRequest().build();
		}
	}

	/**
	 * Closes {@link Transaction} and prints out total price of checked out items.
	 * @param transactionId Id of the existing {@link Transaction}
	 * @return {@link ResponseEntity} with a status and body with total price.
	 */
	@RequestMapping(path="/endTransaction",
					method={RequestMethod.DELETE},
					produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<?> endTransaction(@RequestHeader String transactionId) {
		logger.info("\"endTransaction endpoint was called.");
		if (transactionService.transactionExists(transactionId)) {
			int totalPrice = calculateTotalPrice(transactionId);
			transactionService.delete(transactionId);
			return ResponseEntity.ok(transactionId + " transaction ended, total price is: " + totalPrice);
		} else {
			logger.error("Transaction doesn't exist.");
			return ResponseEntity.notFound().build();
		}
	}
	
	/**
	 * Calculates total price of checked out items based on given {@link StockKeepingUnit} prices.
	 * @param transactionId Id of the existing {@link Transaction}
	 * @return total price of checked out items.
	 */
	private int calculateTotalPrice(String transactionId) {
		int totalPrice = 0;
		Transaction transaction = transactionService.get(transactionId);
		Map<String, Integer> items = transaction.getItems();
		Set<StockKeepingUnit> stockKeepingUnits = transaction.getStockKeepingUnits();
		
		for (StockKeepingUnit stockKeepingUnit : stockKeepingUnits) {
			int count = items.get(stockKeepingUnit.getItem());
			SpecialPrice specialPrice = stockKeepingUnit.getSpecialPrice();
			
			int specialPriceCount = 0;
			if (specialPrice != null 
					&& specialPrice.getCount() != null 
					&& specialPrice.getPrice() != null) {
				specialPriceCount = specialPrice.getCount();
				totalPrice += count / specialPriceCount * specialPrice.getPrice() + count % specialPriceCount * stockKeepingUnit.getPrice();
			} else {
				totalPrice += count * stockKeepingUnit.getPrice();
			}
		}
		
		return totalPrice;
	}
}
