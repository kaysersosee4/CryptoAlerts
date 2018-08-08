package com.example.konrad.cryptoalerts.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by Kayser Sose on 2018-05-18.
 */

@Dao
public interface FavouriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addFavourite (Favourite favourite);

    @Delete
    void removeFavourite(Favourite favourite);

    @Query("select * from favourite")
    Flowable<List<Favourite>> getAllFavourites();
}
