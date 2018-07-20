package com.example.konrad.cryptoalerts.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Kayser Sose on 2018-05-23.
 */
@Entity
public class Alert {
    @PrimaryKey(autoGenerate = true)
    public final long id;
    public final String cryptoId;
    public double above;
    public double below;
    public boolean isActive;

    public Alert(long id, String cryptoId, double above, double below, boolean isActive) {
        this.id = id;
        this.cryptoId = cryptoId;
        this.above = above;
        this.below = below;
        this.isActive = isActive;
    }
}
