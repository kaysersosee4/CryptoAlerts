package com.example.konrad.cryptoalerts.activities;

/**
 * Created by Kayser Sose on 2018-05-23.
 */

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

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.konrad.cryptoalerts.R;
import com.example.konrad.cryptoalerts.coinmarketcapApi.CoinmarketcapApiUtils;
import com.example.konrad.cryptoalerts.coinmarketcapApi.model.Cryptocurrency;
import com.example.konrad.cryptoalerts.coinmarketcapApi.service.CoinmarketcapService;
import com.example.konrad.cryptoalerts.database.AppDatabase;
import com.example.konrad.cryptoalerts.database.Wallet;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class WalletFromHomeFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_wallet_from_home,
                container, false);

        TextView mTest = (TextView) view.findViewById(R.id.testtext);
        Button addButton = (Button) view.findViewById(R.id.button_add);
        EditText mAmount = (EditText) view.findViewById(R.id.et_amount);
        String id="";

        Bundle bundle = this.getArguments();

        if(bundle != null){
           id = bundle.getString("id");
           mTest.append(id);
        }

        String finalId = id;
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double amount = Double.parseDouble(mAmount.getText().toString());
                AppDatabase db = AppDatabase.getDatabase(getContext());
                db.walletDao().addWallet(new Wallet(finalId,amount));
            }
        });




        return view;
    }

}