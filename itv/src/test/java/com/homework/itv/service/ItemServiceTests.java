package com.homework.itv.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.homework.itv.bean.Transaction;
import com.homework.itv.service.ItemService;
import com.homework.itv.service.TransactionService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemServiceTests {
	private final static String TRANSACTION_ID = "123456789";
	private final static String ITEM = "A";
	
	@Mock
	private TransactionService transactionServiceMock;
	
	@Mock
	private Transaction transactionMock;
	
	@InjectMocks
	private ItemService itemService;
	
	@Before
	public void setup() {
		Mockito.reset(transactionServiceMock);
	}
	
	@Test
	public void testAddItem() {
		Transaction transaction = new Transaction(TRANSACTION_ID);
		Mockito.when(transactionServiceMock.get(Mockito.anyString())).thenReturn(transaction);

		Assert.assertTrue(transaction.getItems().isEmpty());
		itemService.addItem(TRANSACTION_ID, ITEM);
		
		Mockito.verify(transactionServiceMock, Mockito.times(1)).get(Mockito.anyString());
		Assert.assertFalse(transaction.getItems().isEmpty());
	}

}
