package com.homework.itv.controller;

import java.util.Collections;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.homework.itv.bean.StockKeepingUnit;
import com.homework.itv.bean.Transaction;
import com.homework.itv.controller.TransactionController;
import com.homework.itv.service.TransactionService;
import com.homework.itv.util.TestUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionControllerTests {
	private final static String TRANSACTION_ID = "123456789";
	private final static int TOTAL_PRICE = 210;
	private Set<StockKeepingUnit> stockKeepingUnits;
	private Transaction transaction;
	
	@Autowired
	private TestUtil testUtil;
	
	@InjectMocks
	private TransactionController transactionController;
	
	@Mock
	private TransactionService transactionServiceMock;
	
	@Before
	public void setup() {
		stockKeepingUnits = testUtil.setupStockKeepingUnits();
		transaction = testUtil.setupTransaction(stockKeepingUnits);
		Mockito.reset(transactionServiceMock);
	}

	@Test
	public void testStartTransaction() {
		Mockito.when(transactionServiceMock.create(Mockito.any())).thenReturn(TRANSACTION_ID);
		ResponseEntity<?> transactionId = transactionController.startTransaction(stockKeepingUnits);
		ResponseEntity<?> expected = new ResponseEntity<String>(TRANSACTION_ID + " transaction started.", HttpStatus.OK);
		
		Assert.assertEquals(expected, transactionId);
	}
	
	@Test
	public void testStartTransactionWithNullStockKeepingUnits() {
		ResponseEntity<?> transactionId = transactionController.startTransaction(null);
		ResponseEntity<?> expected = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		
		Assert.assertEquals(expected, transactionId);
	}
	
	@Test
	public void testStartTransactionWithEmptyStockKeepingUnits() {
		ResponseEntity<?> transactionId = transactionController.startTransaction(Collections.emptySet());
		ResponseEntity<?> expected = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		
		Assert.assertEquals(expected, transactionId);
	}
	
	@Test
	public void testEndTransaction() {
		Mockito.when(transactionServiceMock.transactionExists(Mockito.anyString())).thenReturn(true);
		Mockito.when(transactionServiceMock.get(Mockito.anyString())).thenReturn(transaction);
		ResponseEntity<?> expected = new ResponseEntity<String>(TRANSACTION_ID + " transaction ended, total price is: " + TOTAL_PRICE, HttpStatus.OK);

		ResponseEntity<?> endTransaction = transactionController.endTransaction(TRANSACTION_ID);
		
		Mockito.verify(transactionServiceMock, Mockito.times(1)).transactionExists(Mockito.anyString());
		Mockito.verify(transactionServiceMock, Mockito.times(1)).delete(Mockito.anyString());
		Assert.assertEquals(expected, endTransaction);
	}
	
	@Test
	public void testEndTransactionWithTransactionNotFound() {
		Mockito.when(transactionServiceMock.transactionExists(Mockito.anyString())).thenReturn(false);
		ResponseEntity<?> expected = new ResponseEntity<String>(HttpStatus.NOT_FOUND);

		ResponseEntity<?> endTransaction = transactionController.endTransaction(TRANSACTION_ID);
		
		Assert.assertEquals(expected, endTransaction);
	}
}
