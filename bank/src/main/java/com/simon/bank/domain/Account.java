package com.simon.bank.domain;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.*;

@Entity
public class Account {

    @Id
    @GeneratedValue
    private Integer accountId;
    private int balance;

    //private User user;


    @OneToMany
    @Cascade(CascadeType.ALL)
    private List<Transaction> transactions;


    public Account(int balance){
        this.balance=balance;
        this.transactions=new LinkedList<>();
    }

    public Account(){}

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction){
        transactions.add(transaction);
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
