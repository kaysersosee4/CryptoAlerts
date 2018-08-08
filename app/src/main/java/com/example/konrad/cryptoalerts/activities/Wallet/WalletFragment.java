package com.example.konrad.cryptoalerts.activities.Wallet;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.konrad.cryptoalerts.R;
import com.example.konrad.cryptoalerts.utils.stringUtils.FiatUtils;

import butterknife.BindView;
import butterknife.ButterKnife;


public class WalletFragment extends Fragment implements IWalletView{
    @BindView(R.id.tv_total_value)
    TextView total_value;
    @BindView(R.id.rv_wallet)
    RecyclerView recyclerView;

    private WalletListAdapter mAdapter;
    private IWalletPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_wallet,
                container, false);

        ButterKnife.bind(this, view);

        setupRecyclerView();

        presenter = new WalletPresenter(this, getContext());
        presenter.setupData();

        return view;
    }


    @Override
    public void setupAdapter() {
        mAdapter = new WalletListAdapter(presenter);
        recyclerView.setAdapter(mAdapter);

    }

    @Override
    public void setTotalValue(double totalValue) {
        String sTotalValue = FiatUtils.doubleToFiatValue(totalValue, 2, FiatUtils.DOLAR);
        this.total_value.setText(sTotalValue);
    }

    private void setupRecyclerView(){
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
    }

}