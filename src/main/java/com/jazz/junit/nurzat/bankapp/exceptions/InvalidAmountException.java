package com.jazz.junit.nurzat.bankapp.exceptions;

import java.math.BigDecimal;

public class InvalidAmountException extends BankException {
    public InvalidAmountException(BigDecimal amount) {
        super("Invalid amount: " + amount + "must be positive");
    }
    public InvalidAmountException(String reason){
        super("Invalid amount: " + reason);
    }
}
