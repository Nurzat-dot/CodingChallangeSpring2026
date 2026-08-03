package com.jazz.junit.nurzat.bankapp;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SavingsAccount extends Account {
    private final BigDecimal INTEREST_RATE;
    public SavingsAccount(String owner, BigDecimal initialBalance, BigDecimal interestRate) {
        super(owner, initialBalance);
        INTEREST_RATE = interestRate;
    }

    @Override
    public AccountType getType() {
        return AccountType.SAVINGS;
    }

    @Override
    public BigDecimal calculateMonthlyFee() {
        return BigDecimal.ZERO;
    }
    public void applyMonthlyInterest(){
        BigDecimal monthlyRate = INTEREST_RATE.divide(new BigDecimal("12"),6, RoundingMode.HALF_UP);
        BigDecimal interest = getBalance().multiply(monthlyRate).setScale(2,RoundingMode.HALF_UP);
        if (interest.compareTo(BigDecimal.ZERO) > 0){
            addBalance(interest,TransactionType.Interest);
        }

    }

    public BigDecimal getINTEREST_RATE() {
        return INTEREST_RATE;
    }
}
