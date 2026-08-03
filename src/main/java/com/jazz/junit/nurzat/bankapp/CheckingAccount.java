package com.jazz.junit.nurzat.bankapp;

import java.math.BigDecimal;

public class CheckingAccount extends Account{
    private static final BigDecimal MONTHLY_FEE = new BigDecimal("5.00");

    public CheckingAccount(String owner){
        super(owner,BigDecimal.ZERO);
    }
    public CheckingAccount(String owner, BigDecimal initialBalance) {
        super(owner, initialBalance);
    }

    @Override
    public AccountType getType() {
        return AccountType.CHECKIN;
    }

    @Override
    public BigDecimal calculateMonthlyFee() {
        return MONTHLY_FEE;
    }
}
