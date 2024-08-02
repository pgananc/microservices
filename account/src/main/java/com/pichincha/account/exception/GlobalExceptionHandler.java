package com.pichincha.account.exception;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
	Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponse> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage("Error validation: " + ex.getMessage());
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResponse.setDate(formatter.format(new Date()));
		return ResponseEntity.badRequest().body(errorResponse);
	}

	@ExceptionHandler(InsufficientBalanceException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponse> handleInsufficientBalance(InsufficientBalanceException ex) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResponse.setDate(formatter.format(new Date()));
		return ResponseEntity.badRequest().body(errorResponse);
	}
	
	@ExceptionHandler(AccountException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponse> handleAccountException(AccountException ex) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResponse.setDate(formatter.format(new Date()));
		return ResponseEntity.badRequest().body(errorResponse);
	}
	
	@ExceptionHandler(TransactionException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponse> handleTransactionException(TransactionException ex) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResponse.setDate(formatter.format(new Date()));
		return ResponseEntity.badRequest().body(errorResponse);
	}
}
