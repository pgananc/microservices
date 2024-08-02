package com.pichincha.account.exception;

public class AccountException extends RuntimeException {

	private static final long serialVersionUID = -1127240120576802496L;

	public AccountException() {
        super("Cuenta no existe.");
    }
}
