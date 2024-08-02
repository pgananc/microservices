package com.pichincha.account.exception;

public class InsufficientBalanceException extends RuntimeException {

	private static final long serialVersionUID = -1127240120576802496L;

	public InsufficientBalanceException() {
        super("Saldo no disponible.");
    }
}
