package com.jazz.junit.nurzat.bankapp;

import java.math.BigDecimal;

public class TransactionValidator {

    public static final BigDecimal MIN_AMOUNT = new BigDecimal("0.01");
    public static final BigDecimal MAX_AMOUNT = new BigDecimal("1000000");

    private TransactionValidator(){

    }
    public static boolean isValidAmount(BigDecimal amount){
        if (amount == null) return false;
        return amount.compareTo(MIN_AMOUNT) >= 0 && amount.compareTo(MAX_AMOUNT) <= 0;
    }
    public static boolean isValidOwner(String owner){
        return owner != null && !owner.isBlank()&& owner.length()>=2 && owner.length()<=50;
    }

}
