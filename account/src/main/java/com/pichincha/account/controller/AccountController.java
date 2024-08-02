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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pichincha.account.model.Account;
import com.pichincha.account.service.AccountService;

@RestController
@RequestMapping("/api/cuentas")
public class AccountController {

	private final AccountService accountService;
	

	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	@PostMapping
	public ResponseEntity<Account> createAccount(@RequestBody Account account) {
		Account savedAccount = accountService.saveAccount(account);

		return ResponseEntity.status(HttpStatus.CREATED).body(savedAccount);
	}

	@PutMapping("/{accountNumber}")
	public ResponseEntity<?> updateAccount(@PathVariable String accountNumber, @RequestBody Account accountUpdate) {
		Account existingAccount = accountService.updateAccount(accountUpdate, accountNumber);
		if (existingAccount != null) {
			return ResponseEntity.ok(existingAccount);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Cuenta no encontrada: " + accountNumber);
		}
	}

	@GetMapping("/{accountNumber}")
	public ResponseEntity<?> getAccountById(@PathVariable String accountNumber) {
		Account account = accountService.getAccountById(accountNumber);
		if (account != null) {
			return ResponseEntity.ok(account);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Cuenta no encontrada: " + accountNumber);
		}
	}

	@GetMapping
	public ResponseEntity<List<Account>> getAllAccounts() {
		List<Account> accounts = accountService.getAllAccounts();
		return ResponseEntity.ok(accounts);
	}

	@DeleteMapping("/{accountNumber}")
	public ResponseEntity<Void> deleteAccount(@PathVariable String accountNumber) {
		accountService.deleteAccountById(accountNumber);
		return ResponseEntity.noContent().build();
	}

	@PutMapping()
	public ResponseEntity<?> updateClientName(@RequestParam String accountNumber, @RequestParam String clientName) {
		Account existingAccount = accountService.getAccountById(accountNumber);
		if (existingAccount != null) {
			existingAccount.setClientName(clientName);
			Account updatedAccount = accountService.saveAccount(existingAccount);
			return ResponseEntity.ok(updatedAccount);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Cuenta no encontrada: " + accountNumber);
		}
	}
}
