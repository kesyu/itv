package com.homework.itv.service;

import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.homework.itv.bean.StockKeepingUnit;
import com.homework.itv.bean.Transaction;
import com.homework.itv.service.TransactionService;
import com.homework.itv.util.TestUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionServiceTests {
	private Set<StockKeepingUnit> stockKeepingUnits;

	@Autowired
	private TestUtil testUtil;
	
	@Autowired
	private TransactionService transactionService;
	
	@Before
	public void setup() {
		stockKeepingUnits = testUtil.setupStockKeepingUnits();
	}
	
	@Test
	public void testCreate() {
		String transactionId = transactionService.create(stockKeepingUnits);
		
		Transaction transaction = transactionService.get(transactionId);
		Assert.assertFalse("".equals(transactionId));
		Assert.assertNotNull(transaction);
		Assert.assertEquals(stockKeepingUnits, transaction.getStockKeepingUnits());
	}
}
