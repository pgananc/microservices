package com.pichincha.account.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pichincha.account.exception.AccountException;
import com.pichincha.account.messaging.TransactionMessageProducer;
import com.pichincha.account.model.Account;
import com.pichincha.account.repository.AccountRepository;
import com.pichincha.account.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	private final AccountRepository accountRepository;
	private final TransactionMessageProducer transactionMessageProducer;

	public AccountServiceImpl(AccountRepository accountRepository,
			TransactionMessageProducer transactionMessageProducer) {
		this.accountRepository = accountRepository;
		this.transactionMessageProducer = transactionMessageProducer;
	}

	@Override
	public Account saveAccount(Account account) {
		Account accountSave = accountRepository.save(account);
		transactionMessageProducer.sendMessage(account);
		return accountSave;

	}

	@Override
	public Account getAccountById(String accountNumber) {
		Optional<Account> optionalAccount = accountRepository.findById(accountNumber);
		return optionalAccount.orElse(null);
	}

	@Override
	public List<Account> getAllAccounts() {
		return accountRepository.findAll();
	}

	@Override
	public void deleteAccountById(String accountNumber) {
		accountRepository.deleteById(accountNumber);
	}

	@Override
	public Account updateAccount(Account accountUpdate, String accountNumber) {
		Account existingAccount = getAccountById(accountNumber);
		if (existingAccount != null) {
			existingAccount.setAccountType(accountUpdate.getAccountType());
			existingAccount.setOpeningBalance(accountUpdate.getOpeningBalance());
			existingAccount.setStatus(accountUpdate.getStatus());
			existingAccount.setClientId(accountUpdate.getClientId());
			return accountRepository.save(existingAccount);
		} else {
			throw new AccountException();
		}
	}
}
