package com.example.konrad.cryptoalerts.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by Kayser Sose on 2018-05-11.
 */
@Dao
public interface WalletDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addWallet (Wallet wallet);

    @Query("select * from wallet")
    public List<Wallet> getAllWallets();

    @Delete
    public void deleteWallet(Wallet wallet);
}
