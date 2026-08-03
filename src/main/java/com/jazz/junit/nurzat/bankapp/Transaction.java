package com.jazz.junit.nurzat.bankapp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Transaction(
        String owner,
        TransactionType type,
        BigDecimal amount,
        LocalDateTime timestamp
) {
    public Transaction(String owner,TransactionType type,BigDecimal amount ){
        this(owner,type,amount,LocalDateTime.now());
    }

}
