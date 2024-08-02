package com.pichincha.account.exception;

public class TransactionException extends RuntimeException {

	private static final long serialVersionUID = -1127240120576802496L;

	public TransactionException() {
        super("Transacion no existe.");
    }
}
