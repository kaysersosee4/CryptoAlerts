package com.example.konrad.cryptoalerts.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.konrad.cryptoalerts.R;
import com.example.konrad.cryptoalerts.coinmarketcapApi.CoinmarketcapApiUtils;
import com.example.konrad.cryptoalerts.coinmarketcapApi.model.Cryptocurrency;
import com.example.konrad.cryptoalerts.coinmarketcapApi.service.CoinmarketcapService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomeFragment extends Fragment {
    private List<Cryptocurrency> cryptoList = new ArrayList<Cryptocurrency>();
    private RecyclerView recyclerView;
    private CryptoListAdapter mAdapter;
    private List<Cryptocurrency> testList = new ArrayList<Cryptocurrency>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_home,
                container, false);

        testList.add(new Cryptocurrency("a","a", "a", 1, 2.0, 2.2,
                2.2, 2.3, 2.3, 2.3, 2.3, 2.3, 2.3,
                2));
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        mAdapter = new CryptoListAdapter(cryptoList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        CoinmarketcapApiUtils
                .getService(CoinmarketcapService.class)
                .getAllCryptoInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn(x-> testList)
                .subscribe(x-> recyclerView.setAdapter(new CryptoListAdapter(x)));




        return view;
    }

    public void setCryptoList(List<Cryptocurrency> cryptoList) {
        this.cryptoList = cryptoList;
        mAdapter.notifyDataSetChanged();
    }
}