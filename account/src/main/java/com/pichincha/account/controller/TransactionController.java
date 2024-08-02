package com.pichincha.account.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pichincha.account.model.Transaction;
import com.pichincha.account.service.TransactionService;

@RestController
@RequestMapping("/api/movimientos")
public class TransactionController {

	private final TransactionService transactionService;

	public TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@PostMapping
	public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
		Transaction savedTransaction = transactionService.saveTransaction(transaction);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedTransaction);
	}

	@PutMapping("/{transactionId}")
	public ResponseEntity<Transaction> updateTransaction(@PathVariable Long transactionId,
			@RequestBody Transaction transactionDetails) {
		Transaction updatedTransaction = transactionService.updateTransaction(transactionId, transactionDetails);
		if (updatedTransaction != null) {
			return ResponseEntity.ok(updatedTransaction);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/{transactionId}")
	public ResponseEntity<Transaction> getTransactionById(@PathVariable Long transactionId) {
		Transaction transaction = transactionService.getTransactionById(transactionId);
		if (transaction != null) {
			return ResponseEntity.ok(transaction);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping
	public ResponseEntity<List<Transaction>> getAllTransactions() {
		List<Transaction> transactions = transactionService.getAllTransactions();
		return ResponseEntity.ok(transactions);
	}

	@DeleteMapping("/{transactionId}")
	public ResponseEntity<Void> deleteTransaction(@PathVariable Long transactionId) {
		transactionService.deleteTransactionById(transactionId);
		return ResponseEntity.noContent().build();
	}

}
