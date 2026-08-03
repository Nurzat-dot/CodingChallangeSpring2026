package com.jazz.junit.nurzat.bankapp.exceptions;

import java.math.BigDecimal;

public class InsuffcientFundsException extends BankException {
    private final BigDecimal available;
    private final BigDecimal requested;

    public InsuffcientFundsException(BigDecimal available, BigDecimal requested) {
        super("Not enough funds: available = " + available + ", requested = " + requested);
        this.available = available;
        this.requested = requested;
    }

    public BigDecimal getAvailable() {
        return available;
    }

    public BigDecimal getRequested() {
        return requested;
    }
}
