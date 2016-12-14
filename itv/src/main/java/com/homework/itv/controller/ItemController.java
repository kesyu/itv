package com.homework.itv.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.homework.itv.bean.Transaction;
import com.homework.itv.service.ItemService;
import com.homework.itv.service.TransactionService;

/**
 * 
 * @author Eva Balazsfalvi
 * 
 * Item endpoint controller.
 *
 */
@RestController
public class ItemController {
	private static final Logger logger = LoggerFactory.getLogger(ItemController.class);
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private TransactionService transactionService;
	
	/**
	 * Adds an item to an existing transaction.
	 * 
	 * @param item The item to be added.
	 * @param transactionId Id of the existing {@link Transaction}
	 * @return {@link ResponseEntity} with a status.
	 */
	@RequestMapping(path="/addItem",
			method={RequestMethod.POST},
			produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<?> addItem(@RequestParam String item, @RequestHeader String transactionId) {
		logger.info("\"addItem endpoint was called.");
		if (!item.isEmpty()) {
			if (transactionService.transactionExists(transactionId)) {
				itemService.addItem(transactionId, item);
				return ResponseEntity.ok().build();
			} else {
				logger.error("Transaction doesn't exist.");
				return ResponseEntity.notFound().build();
			}
		} else {
			logger.error("Item is empty.");
			return ResponseEntity.badRequest().build();
		}
	}

}
