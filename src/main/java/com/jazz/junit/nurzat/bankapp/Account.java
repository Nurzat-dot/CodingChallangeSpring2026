package com.jazz.junit.nurzat.bankapp;

import com.jazz.junit.nurzat.bankapp.exceptions.InsuffcientFundsException;
import com.jazz.junit.nurzat.bankapp.exceptions.InvalidAmountException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Account {

    private final String owner;
    private BigDecimal balance;
    private final List<Transaction> transactions = new ArrayList<>();

    protected Account(String owner,BigDecimal initialBalance){
        if (owner == null || owner.isBlank()){
            throw new IllegalArgumentException("Owner must not be blank");
        }
        if (initialBalance == null || initialBalance.compareTo(BigDecimal.ZERO) < 0){
            throw new InvalidAmountException("Initial balance must be non negative");
        }
        this.owner = owner;
        this.balance = initialBalance;
    }
    public abstract AccountType getType();
    public abstract BigDecimal calculateMonthlyFee();

    public void deposit(BigDecimal amount){
        validatePositive(amount);
        balance = balance.add(amount);
        transactions.add(new Transaction(owner,TransactionType.Deposit,amount));
    }

    public void withdraw(BigDecimal amount){
        validatePositive(amount);
        if (balance.compareTo(amount) < 0){
            throw new InsuffcientFundsException(balance,amount);
        }
        balance = balance.subtract(amount);
        transactions.add(new Transaction(owner,TransactionType.Withdrawal,amount));
    }
    public void chargeMonthlyFee(){
        BigDecimal fee = calculateMonthlyFee();
        if (fee.compareTo(BigDecimal.ZERO) > 0){
            balance = balance.subtract(fee);
            transactions.add(new Transaction(owner,TransactionType.Fee,fee));
        }
    }

    public String getOwner() {
        return owner;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }
    protected void addBalance(BigDecimal amount,TransactionType type){
        balance = balance.add(amount);
        transactions.add(new Transaction(owner,type,amount));
    }

    public void slowDeposit(BigDecimal amount, long millis)throws InterruptedException{
        Thread.sleep(millis);
        deposit(amount);


    }

    private void validatePositive(BigDecimal amount){
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0){
            throw new InvalidAmountException(amount);
        }
    }
    }

