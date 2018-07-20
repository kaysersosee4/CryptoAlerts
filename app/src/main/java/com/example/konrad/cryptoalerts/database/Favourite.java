package com.example.konrad.cryptoalerts.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Kayser Sose on 2018-05-18.
 */
@Entity
public class Favourite {
    @PrimaryKey @NonNull
    public final String id;

    public Favourite(String id) {
        this.id = id;
    }
}
