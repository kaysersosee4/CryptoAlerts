package com.example.konrad.cryptoalerts.activities.Home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.konrad.cryptoalerts.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment implements IHomeView {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private CryptoListAdapter mAdapter;
    private IHomePresenter presenter;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_home,
                container, false);

        ButterKnife.bind(this, view);

        setupRecyclerView();

        boolean favourite = getArguments().getBoolean("FAVOURITE");


        presenter = new HomePresenter(this, getContext(), favourite);
        presenter.setupData();


        return view;
    }


    @Override
    public void setupAdapter() {
        mAdapter = new CryptoListAdapter(presenter);
        recyclerView.setAdapter(mAdapter);


    }

    private void setupRecyclerView(){
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
    }
}