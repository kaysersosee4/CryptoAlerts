package com.example.konrad.cryptoalerts.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity
public class Wallet {
    @PrimaryKey @NonNull
    private final String id;
    private double amount;
    private double value;
    private String name;
    private String symbol;

    public Wallet(@NonNull String id, String name, String symbol, double amount, double value) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.value = value;
        this.symbol = symbol;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
