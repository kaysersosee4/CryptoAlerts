package com.example.konrad.cryptoalerts.activities.Home;


import android.content.Context;

public interface IHomePresenter {
    void setupData();
    void setupFavouriteData();

    void changeFavourite(ICryptocurrenciesItemView view, int position, CryptoListAdapter adapter);
    int getItemCount();
    void onBindCryptocurrencyItemView(ICryptocurrenciesItemView view, int position);

    void openWalletFromHomeFragment(Context context, int position);

}
