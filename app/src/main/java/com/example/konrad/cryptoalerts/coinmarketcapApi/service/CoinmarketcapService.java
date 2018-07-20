package com.example.konrad.cryptoalerts.coinmarketcapApi.service;

import com.example.konrad.cryptoalerts.coinmarketcapApi.model.Cryptocurrency;

import java.util.List;
import io.reactivex.Observable;

import retrofit2.http.GET;

/**
 * Created by Konrad on 2018-04-29.
 */

public interface CoinmarketcapService {
    @GET("/v1/ticker")
    Observable<List<Cryptocurrency>> getAllCryptoInfo();
}
