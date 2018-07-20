package com.example.konrad.cryptoalerts.activities;

/**
 * Created by Kayser Sose on 2018-05-23.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.konrad.cryptoalerts.R;
import com.example.konrad.cryptoalerts.coinmarketcapApi.CoinmarketcapApiUtils;
import com.example.konrad.cryptoalerts.coinmarketcapApi.model.Cryptocurrency;
import com.example.konrad.cryptoalerts.coinmarketcapApi.service.CoinmarketcapService;
import com.example.konrad.cryptoalerts.database.AppDatabase;
import com.example.konrad.cryptoalerts.database.Wallet;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Kayser Sose on 2018-05-23.
 */

public class WalletFragment extends Fragment {
    List<Cryptocurrency> cryptocurrencies;
    List<Wallet> wallets;
    TextView test;
    double total;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_wallet,
                container, false);

        test = (TextView) view.findViewById(R.id.testWallet);

        AppDatabase db = AppDatabase.getDatabase(getContext());
        wallets = db.walletDao().getAllWallets();


        CoinmarketcapApiUtils
                .getService(CoinmarketcapService.class)
                .getAllCryptoInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn(x-> cryptocurrencies)
                .subscribe(x->testingMethod(x, wallets));








        return view;
    }

    public void testingMethod(List<Cryptocurrency> cryptocurrencies, List<Wallet> wallets){
        for(Cryptocurrency c: cryptocurrencies){
            for(Wallet w: wallets){
                if(w.id.equals(c.getId())){
                    total += w.amount*c.getPriceUSD();
                    test.append(c.getName()+ " amount: "+ w.amount+" value: "+(w.amount*c.getPriceUSD()+"\n"));
                }
            }
        }
        test.append("Total value: " + total);
    }

}