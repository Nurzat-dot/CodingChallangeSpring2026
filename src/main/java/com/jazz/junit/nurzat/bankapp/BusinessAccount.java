package com.jazz.junit.nurzat.bankapp;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BusinessAccount extends Account {
    private static final BigDecimal FEE_RATE = new BigDecimal("0.001");
    private static final BigDecimal MIN_FEE = new BigDecimal("10.00");
    public BusinessAccount(String owner, BigDecimal initialBalance) {
        super(owner, initialBalance);
    }

    @Override
    public AccountType getType() {
       return AccountType.BUSINESS;
    }

    @Override
    public BigDecimal calculateMonthlyFee() {
        BigDecimal percentFee = getBalance().multiply(FEE_RATE).setScale(2, RoundingMode.HALF_UP);
        return percentFee.max(MIN_FEE);
    }
}
