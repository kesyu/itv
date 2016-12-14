package com.homework.itv.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.homework.itv.controller.ItemController;
import com.homework.itv.controller.TransactionController;
import com.homework.itv.service.ItemService;
import com.homework.itv.service.TransactionService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemControllerTests {
	private final static String TRANSACTION_ID = "123456789";
	
	@InjectMocks
	private TransactionController transactionController;
	
	@InjectMocks
	private ItemController itemController;
	
	@Mock
	private TransactionService transactionServiceMock;
	
	@Mock
	private ItemService itemServiceMock;
	
	@Before
	public void setup() {
		Mockito.reset(transactionServiceMock);
		Mockito.reset(itemServiceMock);
	}

	@Test
	public void testAddItem() {
		Mockito.when(transactionServiceMock.transactionExists(Mockito.anyString())).thenReturn(true);
		ResponseEntity<?> addItem = itemController.addItem("A", TRANSACTION_ID);
		Mockito.verify(itemServiceMock, Mockito.times(1)).addItem(Mockito.anyString(), Mockito.anyString());
		ResponseEntity<?> expected = new ResponseEntity<String>(HttpStatus.OK);
		
		Assert.assertEquals(expected, addItem);
	}
	
	@Test
	public void testAddEmptyItem() {
		Mockito.when(transactionServiceMock.transactionExists(Mockito.anyString())).thenReturn(true);
		ResponseEntity<?> addItem = itemController.addItem("", TRANSACTION_ID);
		ResponseEntity<?> expected = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		
		Assert.assertEquals(expected, addItem);
	}
	
	@Test
	public void testAddItemWithTransactionNotFound() {
		Mockito.when(transactionServiceMock.transactionExists(Mockito.anyString())).thenReturn(false);
		ResponseEntity<?> addItem = itemController.addItem("A", TRANSACTION_ID);
		ResponseEntity<?> expected = new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		
		Assert.assertEquals(expected, addItem);
	}
}
