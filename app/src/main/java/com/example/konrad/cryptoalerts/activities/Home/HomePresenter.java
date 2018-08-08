package com.example.konrad.cryptoalerts.activities.Home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.konrad.cryptoalerts.R;
import com.example.konrad.cryptoalerts.activities.WalletFromHome.WalletFromHomeFragment;
import com.example.konrad.cryptoalerts.coinmarketcapApi.CoinmarketcapApiUtils;
import com.example.konrad.cryptoalerts.coinmarketcapApi.model.Cryptocurrency;
import com.example.konrad.cryptoalerts.coinmarketcapApi.service.CoinmarketcapService;
import com.example.konrad.cryptoalerts.database.Alert;
import com.example.konrad.cryptoalerts.database.AppDatabase;
import com.example.konrad.cryptoalerts.database.Favourite;
import com.example.konrad.cryptoalerts.database.Wallet;

import java.util.LinkedList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter implements IHomePresenter {
    private IHomeView homeView;
    private List<Cryptocurrency> cryptocurrencies;
    private boolean favourite;
    private AppDatabase db;


    HomePresenter(IHomeView homeView, Context context, boolean favourite) {
        this.homeView = homeView;
        this.db = AppDatabase.getDatabase(context);
        this.favourite = favourite;
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
                        .subscribe(cryptocurrencies->{
                            db.favouriteDao()
                                    .getAllFavourites()
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(favourites->{
                                        db.walletDao()
                                                .getAllWallets()
                                                .subscribeOn(Schedulers.io())
                                                .observeOn(AndroidSchedulers.mainThread())
                                                .subscribe(wallets->{
                                                    db.alertDao()
                                                            .getAllAlerts()
                                                            .subscribeOn(Schedulers.io())
                                                            .observeOn(AndroidSchedulers.mainThread())
                                                            .subscribe(alerts->{
                                                                loadInfo(cryptocurrencies,favourites,wallets,alerts);
                                                                homeView.setupAdapter();
                                                            });
                                                });
                                    });
                        });
    }

    private void loadInfo(List<Cryptocurrency> cryptos, List<Favourite> favourites, List<Wallet> wallets, List<Alert> alerts){
        List<Cryptocurrency> customCryptos = new LinkedList<>();

        if(favourite){
            for(Favourite f: favourites){
                for(Cryptocurrency c: cryptos){
                    if(f.id.equals(c.getId())){
                        c.setFavourite(true);
                        customCryptos.add(c);
                        break;
                    }
                }
            }

        }else{
            for(Favourite f: favourites){
                for(Cryptocurrency c: cryptos){
                    if(f.id.equals(c.getId())){
                        c.setFavourite(true);
                        break;
                    }
                }
            }
            customCryptos = cryptos;
        }

        for(Wallet w: wallets){
            for(Cryptocurrency c: customCryptos){
                if(w.getId().equals(c.getId())){
                    c.setWallet(true);
                    break;
                }
            }
        }

        for(Alert a: alerts){
            for(Cryptocurrency c: customCryptos){
                if(a.cryptoId.equals(c.getId())){
                    c.setAlert(true);
                    break;
                }
            }
        }




            setCryptocurrencies(customCryptos);


    }

    @Override
    public void setupFavouriteData() {
        //extractFavouriteData() :)
    }


    @SuppressLint("CheckResult")
    @Override
    public void changeFavourite(ICryptocurrenciesItemView view, int position, CryptoListAdapter adapter) {
        Cryptocurrency crypto = cryptocurrencies.get(position);
        crypto.setFavourite(!crypto.isFavourite());

        Completable.fromAction(() -> {
            if (crypto.isFavourite()) {
                db.favouriteDao().addFavourite(new Favourite(crypto.getId()));
            }else{
                db.favouriteDao().removeFavourite(new Favourite(crypto.getId()));
            }
        })      .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(()-> adapter.notifyItemChanged(position));


    }

    @Override
    public int getItemCount() {
        return cryptocurrencies.size();
    }

    @Override
    public void onBindCryptocurrencyItemView(ICryptocurrenciesItemView view, int position) {
        Cryptocurrency crypto = cryptocurrencies.get(position);
        String iconName = crypto.getSymbol().toLowerCase();

        view.setRank(crypto.getRank());
        view.setName(crypto.getName());
        view.setPrice(crypto.getPriceUSD());
        view.setChange(crypto.getDayPercentChange());

        view.setCryptoIcon(iconName);
        view.setFavouriteIcon(crypto.isFavourite());
        view.setAlertIcon(crypto.isAlert());
        view.setWalletIcon(crypto.isWallet());
    }

    @Override
    public void openWalletFromHomeFragment(Context context, int position) {
        Cryptocurrency crypto = cryptocurrencies.get(position);

        AppCompatActivity parentActivity = (AppCompatActivity) context;
                WalletFromHomeFragment myFragment = new WalletFromHomeFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("CRYPTOCURRENCY", crypto);
                myFragment.setArguments(bundle);

                parentActivity.getSupportFragmentManager().beginTransaction().replace(R.id.flContent, myFragment).addToBackStack(null).commit();
    }

    public void setCryptocurrencies(List<Cryptocurrency> cryptocurrencies) {
        this.cryptocurrencies = cryptocurrencies;
    }
}
