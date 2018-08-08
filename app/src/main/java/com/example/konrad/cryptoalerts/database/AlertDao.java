package com.example.konrad.cryptoalerts.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by Kayser Sose on 2018-05-23.
 */

@Dao
public interface AlertDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addAlert (Alert alert);

    @Delete
    void removeFAlert(Alert alert);

    @Query("select * from alert")
    Flowable<List<Alert>> getAllAlerts();

    @Update
    public void updateAlert(Alert alert);

    @Query("select * from alert where cryptoId=:id")
    Flowable<List<Alert>> getCryptoAlerts(String id);

}