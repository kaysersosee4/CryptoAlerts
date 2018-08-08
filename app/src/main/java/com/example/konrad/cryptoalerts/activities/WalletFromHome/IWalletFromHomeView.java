package com.example.konrad.cryptoalerts.activities.WalletFromHome;

public interface IWalletFromHomeView {
    void setIcon(String symbol);
    void setName(String name);
    void setSymbol(String symbol);
    void setUnitPrice(double price);
    void setValue(double value);
    void setAmount(double amount);
}
