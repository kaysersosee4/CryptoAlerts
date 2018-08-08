package com.example.konrad.cryptoalerts.activities.WalletFromHome;


import android.support.v4.app.Fragment;

public interface IWalletFromHomePresenter {
    void setData();
    void updateWallet(String amount);
    void goPreviousFragment(Fragment presentFragment);
}
