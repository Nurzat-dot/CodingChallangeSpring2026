package com.jazz.junit.nurzat.bankapp.exceptions;

public class AccountNotFoundException extends BankException {
    public AccountNotFoundException(String owner) {
        super("Account not found for owner: " + owner);
    }
}
