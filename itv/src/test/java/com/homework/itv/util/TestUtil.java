package com.homework.itv.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.homework.itv.bean.SpecialPrice;
import com.homework.itv.bean.StockKeepingUnit;
import com.homework.itv.bean.Transaction;

@Component
public class TestUtil {
	private final static String TRANSACTION_ID = "123456789";

	public Set<StockKeepingUnit> setupStockKeepingUnits() {
		Set<StockKeepingUnit> stockKeepingUnits = new HashSet<>();
		stockKeepingUnits.add(new StockKeepingUnit("A", 50, new SpecialPrice(3, 130)));
		stockKeepingUnits.add(new StockKeepingUnit("B", 30, new SpecialPrice(2, 45)));
		stockKeepingUnits.add(new StockKeepingUnit("C", 20, new SpecialPrice()));
		stockKeepingUnits.add(new StockKeepingUnit("D", 15, null));
		return stockKeepingUnits;
	}
	
	public Transaction setupTransaction(Set<StockKeepingUnit> stockKeepingUnits) {
		Transaction transaction = new Transaction(TRANSACTION_ID);
		stockKeepingUnits.forEach(transaction::addStockKeepingUnit);
		Arrays.asList("A", "A", "B", "A", "C", "B", "D").forEach(transaction::addItem);
		return transaction;
	}
}
