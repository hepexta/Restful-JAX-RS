package com.hepexta.jaxrs.bank.model;

import com.hepexta.jaxrs.bank.ex.DepositException;
import com.hepexta.jaxrs.bank.repository.cache.AccountRepositoryCache;

import java.math.BigDecimal;

public class Account {
    private String number;
    private Client client;
    private BigDecimal balance;

    private Account() {
    }

    public Account(Client client, BigDecimal balance) {
        this.client = client;
        this.balance = balance;
    }

    public Account(String number, Client client, BigDecimal balance) {
        this.number = number;
        this.client = client;
        this.balance = balance;
    }

    public synchronized void withdraw(BigDecimal bal) {
        if (balance.compareTo(bal)>=0) {
            mockDataBase();
            balance = balance.subtract(bal);
        } else {
            throw new DepositException(String.format(DepositException.NOT_ENOUGH_MONEY_ERROR_WITHDRAWAL, client.getName()));
        }
    }

    public synchronized void deposit(BigDecimal bal) {
        if (bal.compareTo(BigDecimal.ZERO)>0) {
            mockDataBase();
            balance = balance.add(bal);
        } else {
            throw new DepositException(String.format(DepositException.NOT_ENOUGH_MONEY_ERROR_DEPOSIT, client.getName()));
        }
    }

    @Override
    public String toString() {
        return "Account{" +
                "number='" + number + '\'' +
                ", client=" + client +
                ", balance=" + balance +
                '}';
    }

    public BigDecimal getBalance() {
        return balance;
    }

    private void mockDataBase() {
        AccountRepositoryCache.getINSTANCE().modify(this);
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return this.number;
    }
}
