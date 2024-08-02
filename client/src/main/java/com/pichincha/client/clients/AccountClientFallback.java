package com.pichincha.client.clients;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AccountClientFallback implements AccountClient {

	@Override
	public ResponseEntity<?> updateClientName(String accountNumber, String clientName) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cuenta no encontrada: " + accountNumber);
	}

}
