package com.homework.itv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homework.itv.bean.Transaction;

/**
 * 
 * @author Eva Balazsfalvi
 * 
 * Handles item operations.
 *
 */
@Service
public class ItemService {

	@Autowired
	private TransactionService transactionService;
	
	/**
	 * Adds an item to an existing {@link Transaction}
	 * @param transactionId Id of an already existing {@link Transaction}
	 * @param item item to be added.
	 */
	public void addItem(String transactionId, String item) {
		transactionService.get(transactionId).addItem(item);
	}
}
