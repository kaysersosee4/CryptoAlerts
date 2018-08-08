package com.example.konrad.cryptoalerts.activities.Wallet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.example.konrad.cryptoalerts.coinmarketcapApi.CoinmarketcapApiUtils;
import com.example.konrad.cryptoalerts.coinmarketcapApi.model.Cryptocurrency;
import com.example.konrad.cryptoalerts.coinmarketcapApi.service.CoinmarketcapService;
import com.example.konrad.cryptoalerts.database.AppDatabase;
import com.example.konrad.cryptoalerts.database.Wallet;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class WalletPresenter implements IWalletPresenter {
    private IWalletView walletView;
    private List<Wallet> wallets;
    private AppDatabase db;

    public WalletPresenter(IWalletView walletView, Context context) {
        this.walletView = walletView;
        this.db = AppDatabase.getDatabase(context);
    }

    @Override
    public void onBindWalletItemView(IWalletItemView view, int position) {
        Wallet wallet = wallets.get(position);

        view.setIcon(wallet.getSymbol());
        view.setName(wallet.getName());
        view.setAmount(wallet.getAmount());
        view.setValue(wallet.getValue());

    }

    @Override
    public int getItemCount() {
        return wallets.size();
    }

    @SuppressLint("CheckResult")
    @Override
    public void setupData() {
        CoinmarketcapApiUtils
                .getService(CoinmarketcapService.class)
                .getAllCryptoInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable -> Log.d("NET","Failed loading data", throwable))
                .subscribe(cryptocurrencies -> {
                    db.walletDao()
                            .getAllWallets()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(wallets -> {
                                loadData(cryptocurrencies, wallets);
                                walletView.setTotalValue(getTotalValue());
                                walletView.setupAdapter();
                            } );
                });
    }

    private void loadData(List<Cryptocurrency> cryptocurrencies, List<Wallet> wallets){
        this.wallets = wallets;
        double value;
        for(Wallet w: wallets){
            for(Cryptocurrency c: cryptocurrencies){
                if(w.getId().equals(c.getId())){
                    value = w.getAmount() * c.getPriceUSD();
                    w.setValue(value);
                    break;
                }
            }
        }


    }

    private double getTotalValue(){
        double total = 0.0;
        for(Wallet w: wallets){
            total += w.getValue();
        }

        return total;
    }


}
