package com.pichincha.account.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.pichincha.account.dto.TransactionReportDTO;
import com.pichincha.account.model.Transaction;

public interface TransactionService {
	Transaction saveTransaction(Transaction transaction);

	Transaction updateTransaction(Long transactionId, Transaction transactionDetails);

	Transaction getTransactionById(Long transactionId);

	List<Transaction> getAllTransactions();

	void deleteTransactionById(Long transactionId);

	List<TransactionReportDTO> getTransactionsByClientIdAndDateRange(Long clientId, LocalDate startDate,
			LocalDate endDate);
}
