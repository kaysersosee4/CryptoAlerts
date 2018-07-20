package com.example.konrad.cryptoalerts.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.konrad.cryptoalerts.R;
import com.example.konrad.cryptoalerts.coinmarketcapApi.CoinmarketcapApiUtils;
import com.example.konrad.cryptoalerts.coinmarketcapApi.model.Cryptocurrency;
import com.example.konrad.cryptoalerts.coinmarketcapApi.service.CoinmarketcapService;
import com.example.konrad.cryptoalerts.database.AppDatabase;
import com.example.konrad.cryptoalerts.database.Favourite;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Kayser Sose on 2018-05-19.
 */

public class FavouriteFragment extends Fragment {


        private List<Cryptocurrency> cryptoList = new ArrayList<Cryptocurrency>();
        private RecyclerView recyclerView;
        private CryptoListAdapter mAdapter;
        private List<Cryptocurrency> testList = new ArrayList<Cryptocurrency>();
        private AppDatabase database;


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fr_favourite,
                    container, false);

            testList.add(new Cryptocurrency("a","a", "a", 1, 2.0, 2.2,
                    2.2, 2.3, 2.3, 2.3, 2.3, 2.3, 2.3,
                    2));
            recyclerView = (RecyclerView) view.findViewById(R.id.favourite_recycler_view);

            mAdapter = new CryptoListAdapter(cryptoList);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());

            database = AppDatabase.getDatabase(getContext().getApplicationContext());

            List<Favourite> favouriteList = database.favouriteDao().getAllFavourites();


            CoinmarketcapApiUtils
                    .getService(CoinmarketcapService.class)
                    .getAllCryptoInfo()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .onErrorReturn(x-> testList)
                    .subscribe(x-> listsss(x, favouriteList));




                 recyclerView.setAdapter(new CryptoListAdapter(cryptoList));

            return view;
        }

        public void setCryptoList(List<Cryptocurrency> cryptoList) {
            this.cryptoList = cryptoList;
            mAdapter.notifyDataSetChanged();
        }

        public void listsss(List<Cryptocurrency> clist, List<Favourite> flist){
            List<Cryptocurrency> cryptos = new ArrayList<Cryptocurrency>();
            for(Cryptocurrency c: clist){
                for(Favourite f: flist){
                    if (c.getId().equals(f.id)) {
                        cryptos.add(c);
                        break;
                    }
                }
            }
            recyclerView.setAdapter(new CryptoListAdapter(cryptos));
        }
}
