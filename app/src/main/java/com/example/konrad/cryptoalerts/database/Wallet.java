package com.example.konrad.cryptoalerts.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Kayser Sose on 2018-05-11.
 */

@Entity
public class Wallet {
    @PrimaryKey @NonNull
    public final String id;
    public double amount;

    public Wallet(String id, double amount) {
        this.id = id;
        this.amount = amount;
    }
}
