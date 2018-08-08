package com.example.konrad.cryptoalerts.activities.Wallet;

public interface IWalletPresenter {
    void onBindWalletItemView(IWalletItemView view, int position);
    int getItemCount();
    void setupData();
}
