package com.example.konrad.cryptoalerts.activities.WalletFromHome;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.example.konrad.cryptoalerts.coinmarketcapApi.model.Cryptocurrency;
import com.example.konrad.cryptoalerts.database.AppDatabase;
import com.example.konrad.cryptoalerts.database.Wallet;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class WalletFromHomePresenter implements IWalletFromHomePresenter {
    private IWalletFromHomeView walletView;
    private Cryptocurrency crypto;
    private Wallet wallet;
    private AppDatabase db;

    public WalletFromHomePresenter(Context context, IWalletFromHomeView walletView, Cryptocurrency crypto) {
        this.walletView = walletView;
        this.crypto = crypto;
        this.db = AppDatabase.getDatabase(context);


    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    @SuppressLint("CheckResult")
    @Override
    public void setData() {
        db.walletDao()
                .getWallet(crypto.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .defaultIfEmpty(new Wallet("fake", "fake", "fake", 0.0, 0.0))
                .subscribe(wallet->{
                    setWallet(wallet);
                    sendDataToView();
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void updateWallet(String amount) {
        double dAmount = Double.parseDouble(amount);
        if(wallet.getId().equals("fake")){
            wallet = new Wallet(crypto.getId(), crypto.getName(), crypto.getSymbol(), dAmount, dAmount*crypto.getPriceUSD());
            Completable.fromAction(() ->
                    db.walletDao()
                    .addWallet(wallet))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(()-> sendDataToView());
        }
    }

    @Override
    public void goPreviousFragment(Fragment presentFragment) {
        presentFragment.getFragmentManager().popBackStack();
    }



    private void sendDataToView(){
        double amount = (wallet.equals("fake") ? 0.0 : wallet.getAmount());
        double value = amount * crypto.getPriceUSD();

        walletView.setIcon(crypto.getSymbol());
        walletView.setName(crypto.getName());
        walletView.setSymbol(crypto.getSymbol());
        walletView.setUnitPrice(crypto.getPriceUSD());
        walletView.setAmount(amount);
        walletView.setValue(value);

    }
}
