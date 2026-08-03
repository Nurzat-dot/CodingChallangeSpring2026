package com.jazz.junit.nurzat.bankapp;

import com.jazz.junit.nurzat.bankapp.exceptions.AccountNotFoundException;
import com.jazz.junit.nurzat.bankapp.notification.NotificationService;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Bank {

    private final String name;
    private final Map<String,Account> accounts = new HashMap<>();
    private final List<Transaction> transactionlog = new ArrayList<>();
    private final Set<String> blackList = new HashSet<>();
    private final NotificationService notificationService;

    public Bank(String name, NotificationService notificationService) {
        this.name = name;
        this.notificationService = notificationService;
    }
    public Account openAccount(Account account){
        if (blackList.contains(account.getOwner())){
            throw new IllegalStateException("Owner is blacklisted: " + account.getOwner());
        }
        accounts.put(account.getOwner(),account);
        notificationService.notify(account.getOwner(),"Welcome to " + name + "!");
        return account;
    }
    public Account findByOwner(String owner){
        Account account = accounts.get(owner);
        if (account == null){
            throw new AccountNotFoundException(owner);
        }
        return account;
    }
    public void transfer(String fromOwner, String toOwner, BigDecimal amount){
        Account from = findByOwner(fromOwner);
        Account to = findByOwner(toOwner);
        from.withdraw(amount);
        to.deposit(amount);
        transactionlog.add(new Transaction(fromOwner,TransactionType.Transfer_Out,amount));
        transactionlog.add(new Transaction(toOwner,TransactionType.Transfer_In,amount));
        notificationService.notify(fromOwner,"You sent" + amount + "to" + toOwner);
        notificationService.notify(toOwner,"You received " + amount + "from" + fromOwner);
    }
    public List<String> firsNOwners(int n){
        List<String> allOwners = new ArrayList<>(accounts.keySet());
        List<String> result = new ArrayList<>();
        for (int i = 0; i < n && i < allOwners.size() ; i++) {
            result.add(allOwners.get(i));
            
        }
        return result;
    }
    public int chargeUntilBelow(BigDecimal threshold){
        int charges = 0;
        Account richest = findRichest();
        while (richest != null && richest.getBalance().compareTo(threshold) > 0){
            richest.chargeMonthlyFee();
            charges ++;
            richest = findRichest();
            if (charges > 100) break;
        }
        return charges;
    }
    public int chargeAllAtListOnes(){
        Iterator<Account> iterator = accounts.values().iterator();
        int charges = 0;
        if (!iterator.hasNext())return 0;
        do {
            Account account = iterator.next();
            account.chargeMonthlyFee();
            charges ++;
        }while (iterator.hasNext());
        return charges;
    }

    public Account findRichest(){
        return accounts.values().stream().
                max(Comparator.comparing(Account ::getBalance)).orElse(null);
    }
    public List<Account> filter(Predicate<Account> predicate){
        return accounts.values().stream()
                .filter(predicate)
                .toList();
    }
    public void applyAll(Consumer<Account> action){
        accounts.values().forEach(action);
    }
    public BigDecimal sumAllBalances(){
        BigDecimal total = BigDecimal.ZERO;
        for (Account account : accounts.values()){
            total = total.add(account.getBalance());
        }
        return total;
    }
    public void addToBlackList(String owner){
        blackList.add(owner);
    }
    public boolean isBlackList(String owner){
        return blackList.contains(owner);
    }

    public String getName() {
        return name;
    }


    public List<Transaction> getTransactionlog() {
        return Collections.unmodifiableList(transactionlog);
    }

    public Set<String> getBlackList() {
        return Collections.unmodifiableSet(blackList);
    }
public int accountsCount(){
        return accounts.size();
}
}

