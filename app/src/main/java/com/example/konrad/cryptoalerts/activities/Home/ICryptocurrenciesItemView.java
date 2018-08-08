package com.example.konrad.cryptoalerts.activities.Home;

public interface ICryptocurrenciesItemView {
    void setFavouriteIcon(boolean favourite);
    void setWalletIcon(boolean wallet);
    void setAlertIcon(boolean alert);
    void setCryptoIcon(String iconName);

    void setRank(int rank);
    void setName(String name);
    void setPrice(double price);
    void setChange(double price);

}
