package com.homework.itv.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.homework.itv.controller.ErrorController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ErrorControllerTests {
	
	@Mock
	private Exception exception;
	
	@Mock
	private HttpMediaTypeNotSupportedException httpMediaTypeNotSupportedException;
	
	@Mock
	private BindException bindException;
	
	@Mock
	private MethodArgumentNotValidException methodArgumentNotValidException;
	
	@Mock
	private HttpMessageNotReadableException httpMessageNotReadableException;
	
	
	private ErrorController errorController = new ErrorController();
	
	@Test
	public void testHandleExceptionWithNull() {
		ResponseEntity<Void> handleException = this.errorController.handleException(null);
		
		Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, handleException.getStatusCode());
		Assert.assertEquals(null, handleException.getBody());
	}
	
	@Test
	public void testHandleExceptionr() {
		ResponseEntity<Void> handleException = this.errorController.handleException(exception);
	
		Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, handleException.getStatusCode());
		Assert.assertEquals(null, handleException.getBody());
	}
	
	@Test
	public void testHandleHttpMediaTypeNotSupportedExceptionWithNull() {
		ResponseEntity<Void> handleException = this.errorController.handleHttpMediaTypeNotSupportedException(null);
		
		Assert.assertEquals(HttpStatus.UNSUPPORTED_MEDIA_TYPE, handleException.getStatusCode());
		Assert.assertEquals(null, handleException.getBody());
	}
	
	@Test
	public void testHandleHttpMediaTypeNotSupportedException() {
		ResponseEntity<Void> handleException = this.errorController.handleHttpMediaTypeNotSupportedException(httpMediaTypeNotSupportedException);
	
		Assert.assertEquals(HttpStatus.UNSUPPORTED_MEDIA_TYPE, handleException.getStatusCode());
		Assert.assertEquals(null, handleException.getBody());
	}
	
	@Test
	public void testHandleBadRequestWithNull() {
		ResponseEntity<Void> handleException = this.errorController.handleBadRequest(null);
		
		Assert.assertEquals(HttpStatus.BAD_REQUEST, handleException.getStatusCode());
		Assert.assertEquals(null, handleException.getBody());
	}
	
	@Test
	public void testHandleBadRequest() {
		ResponseEntity<Void> handleException = this.errorController.handleBadRequest(methodArgumentNotValidException);
	
		Assert.assertEquals(HttpStatus.BAD_REQUEST, handleException.getStatusCode());
		Assert.assertEquals(null, handleException.getBody());
		
		handleException = this.errorController.handleBadRequest(bindException);
	
		Assert.assertEquals(HttpStatus.BAD_REQUEST, handleException.getStatusCode());
		Assert.assertEquals(null, handleException.getBody());
		
		handleException = this.errorController.handleBadRequest(httpMessageNotReadableException);
		
		Assert.assertEquals(HttpStatus.BAD_REQUEST, handleException.getStatusCode());
		Assert.assertEquals(null, handleException.getBody());
	}
}
