package com.example.konrad.cryptoalerts.coinmarketcapApi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Konrad on 2018-04-29.
 */

public class CoinmarketcapApiUtils {
    private static Retrofit retrofit = null;
    private static final String URL = "https://api.coinmarketcap.com";

    public static <T> T getService(Class<T> service){
        if(retrofit == null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }

        return retrofit.create(service);
    }
}
