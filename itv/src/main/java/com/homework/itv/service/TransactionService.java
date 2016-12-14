package com.homework.itv.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.homework.itv.bean.StockKeepingUnit;
import com.homework.itv.bean.Transaction;

/**
 * 
 * @author Eva
 * 
 * Handles transaction operations.
 *
 */
@Service
public class TransactionService {
	
	private final Map<String, Transaction> transactions = new HashMap<>();

	/**
	 * Creates a new {@link Transaction} with a {@link StockKeepingUnit} list.
	 * @param stockKeepingUnits set of {@link StockKeepingUnit}
	 * @return Id of newly created {@link Transaction}.
	 */
	public String create(Set<StockKeepingUnit> stockKeepingUnits) {
		String transactionId = UUID.randomUUID().toString();
		Transaction transaction = new Transaction(transactionId);
		stockKeepingUnits.forEach(transaction::addStockKeepingUnit);
		transactions.put(transactionId, transaction);
		return transactionId.toString();
	}
	
	public boolean transactionExists(String transactionId) {
		return transactions.containsKey(transactionId);
	}

	public Transaction get(String transactionId) {
		return transactions.get(transactionId);
	}
	
	public void delete(String transactionId) {
		transactions.remove(transactionId);
	}
}
