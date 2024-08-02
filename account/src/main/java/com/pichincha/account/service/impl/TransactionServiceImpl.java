package com.pichincha.account.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pichincha.account.dto.TransactionReportDTO;
import com.pichincha.account.exception.AccountException;
import com.pichincha.account.exception.InsufficientBalanceException;
import com.pichincha.account.exception.TransactionException;
import com.pichincha.account.model.Account;
import com.pichincha.account.model.Transaction;
import com.pichincha.account.model.TransactionType;
import com.pichincha.account.repository.AccountRepository;
import com.pichincha.account.repository.TransactionRepository;
import com.pichincha.account.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {
	private final TransactionRepository transactionRepository;

	private final AccountRepository accountRepository;

	public TransactionServiceImpl(TransactionRepository transactionRepository, AccountRepository accountRepository) {
		this.transactionRepository = transactionRepository;
		this.accountRepository = accountRepository;

	}

	@Override
	public Transaction saveTransaction(Transaction transaction) {

		Account account = accountRepository.findById(transaction.getAccountNumber())
				.orElseThrow(() -> new RuntimeException("Cuenta no encontrada."));

		if (transaction.getTransactionId() != null) {
			if (transaction.getBalance().add(transaction.getAmount()).compareTo(BigDecimal.ZERO) < 0) {
				throw new InsufficientBalanceException();
			}
			transaction.setBalance(transaction.getBalance().add(transaction.getAmount()));
		} else {
			Optional<Transaction> lastTransaction = transactionRepository
					.findTopByAccountNumberOrderByCreatedOnDesc(transaction.getAccountNumber());
			if (lastTransaction.isPresent()) {
				if (lastTransaction.get().getBalance().add(transaction.getAmount()).compareTo(BigDecimal.ZERO) < 0) {
					throw new InsufficientBalanceException();
				}
				transaction.setBalance(lastTransaction.get().getBalance().add(transaction.getAmount()));
			} else {
				if (account.getOpeningBalance().add(transaction.getAmount()).compareTo(BigDecimal.ZERO) < 0) {
					throw new InsufficientBalanceException();
				}
				transaction.setBalance(account.getOpeningBalance().add(transaction.getAmount()));
			}

		}
		transaction
				.setTransactionType(transaction.getAmount().compareTo(BigDecimal.ZERO) < 0 ? TransactionType.Retiro
						: TransactionType.Deposito);
		transaction.setCreatedOn(LocalDateTime.now());

		return transactionRepository.save(transaction);
	}

	@Override
	public Transaction getTransactionById(Long transactionId) {
		Optional<Transaction> optionalTransaction = transactionRepository.findById(transactionId);
		return optionalTransaction.orElse(null);
	}

	@Override
	public List<Transaction> getAllTransactions() {
		return transactionRepository.findAll();
	}

	@Override
	public void deleteTransactionById(Long transactionId) {
		transactionRepository.deleteById(transactionId);
	}

	@Override
	public List<TransactionReportDTO> getTransactionsByClientIdAndDateRange(Long clientId, LocalDate startDate,
			LocalDate endDate) {
		LocalDateTime startDateTime = startDate.atStartOfDay();
		LocalDateTime endDateTime = endDate.atTime(23, 59, 59);
		List<Transaction> transactions = transactionRepository.findByClientIdAndDateRange(clientId, startDateTime,
				endDateTime);

		return transactions.stream().map(transaction -> {
			TransactionReportDTO dto = new TransactionReportDTO();
			dto.setCreatedOn(LocalDate.from(transaction.getCreatedOn()));
			dto.setClientName(transaction.getAccount().getClientName());
			dto.setAccountNumber(transaction.getAccountNumber());
			dto.setAccountType(transaction.getAccount().getAccountType());
			dto.setOpeningBalance(transaction.getAccount().getOpeningBalance());
			dto.setAccountStatus(transaction.getAccount().getStatus());
			dto.setAmount(transaction.getAmount());
			dto.setBalance(transaction.getBalance());
			return dto;
		}).collect(Collectors.toList());
	}

	@Override
	public Transaction updateTransaction(Long transactionId, Transaction transactionDetails) {
		Transaction existingTransaction = getTransactionById(transactionId);
		if (existingTransaction != null) {
			existingTransaction.setAccountNumber(transactionDetails.getAccountNumber());
			existingTransaction.setCreatedOn(transactionDetails.getCreatedOn());
			existingTransaction.setAmount(transactionDetails.getAmount());
			existingTransaction.setTransactionType(transactionDetails.getTransactionType());
			existingTransaction.setBalance(transactionDetails.getBalance());

			return saveTransaction(existingTransaction);
		} else {
			throw new TransactionException();
		}
	}
}
