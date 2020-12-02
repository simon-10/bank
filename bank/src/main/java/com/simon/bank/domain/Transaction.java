package com.simon.bank.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Transaction {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private int points;
    private LocalDate transactionDate;


    public Transaction(String name, int points, LocalDate transactionDate) {

        this.name = name;
        this.points = points;
        this.transactionDate = transactionDate;
    }

    public Transaction() {

    }

    public Integer getId() {
        return id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }
}
