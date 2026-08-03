package com.jazz.junit.nurzat.bankapp.exceptions;

public abstract class BankException extends RuntimeException {
    protected BankException(String message) {
        super(message);
    }
    protected BankException (String massage,Throwable cause){
        super(massage,cause);
    }
}
