package com.example.konrad.cryptoalerts.database;

/**
 * Created by Kayser Sose on 2018-05-11.
 */

import android.content.Context;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Wallet.class, Favourite.class, Alert.class},
        version = 20, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract WalletDao walletDao();
    public abstract FavouriteDao favouriteDao();
    public abstract AlertDao alertDao();


    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context, AppDatabase.class, "userdatabase")
                            .fallbackToDestructiveMigration()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}