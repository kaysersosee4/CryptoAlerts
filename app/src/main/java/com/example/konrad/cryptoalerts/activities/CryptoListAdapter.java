package com.example.konrad.cryptoalerts.activities;

/**
 * Created by Kayser Sose on 2018-05-07.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.konrad.cryptoalerts.R;
import com.example.konrad.cryptoalerts.coinmarketcapApi.model.Cryptocurrency;
import com.example.konrad.cryptoalerts.database.AppDatabase;
import com.example.konrad.cryptoalerts.database.Favourite;

import java.util.List;

public class CryptoListAdapter extends RecyclerView.Adapter<CryptoListAdapter.MyViewHolder> {

    private List<Cryptocurrency> cryptoList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public Cryptocurrency cryptocurrency;
        public TextView rank, name, price, change;
        public ImageView crypto_icon, alert_icon, wallet_icon, favourite_icon;

        public MyViewHolder(View view) {
            super(view);
            rank = (TextView) view.findViewById(R.id.tv_rank);
            name = (TextView) view.findViewById(R.id.tv_name);
            price = (TextView) view.findViewById(R.id.tv_price);
            change = (TextView) view.findViewById(R.id.tv_change);
            crypto_icon = (ImageView) view.findViewById(R.id.iv_icon);
            alert_icon = (ImageView) view.findViewById(R.id.iv_alert);
            favourite_icon = (ImageView) view.findViewById(R.id.iv_favourite);
            wallet_icon = (ImageView) view.findViewById(R.id.iv_wallet);
            cryptocurrency = null;

            alert_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "Alert_icon clicked!", Toast.LENGTH_LONG).show();
                }
            });

            favourite_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AppDatabase database = AppDatabase.getDatabase(view.getContext().getApplicationContext());
                    if(cryptocurrency.isFavourite()){
                        database.favouriteDao().removeFavourite(new Favourite(cryptocurrency.getId()));
                        favourite_icon.setImageDrawable(view.getResources().getDrawable(R.drawable.ic_favourite));
                        cryptocurrency.setFavourite(false);
                    }else{
                        database.favouriteDao().addFavourite(new Favourite(cryptocurrency.getId()));
                        favourite_icon.setImageDrawable(view.getResources().getDrawable(R.drawable.ic_favorite_added));
                        cryptocurrency.setFavourite(true);
                    }
                    database.destroyInstance();
                }
            });

            wallet_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "Wallet_icon clicked!", Toast.LENGTH_LONG).show();
                    AppCompatActivity parentActivity = (AppCompatActivity) view.getContext();
                    WalletFromHomeFragment myFragment = new WalletFromHomeFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("id",cryptocurrency.getId());
                    myFragment.setArguments(bundle);

                    parentActivity.getSupportFragmentManager().beginTransaction().replace(R.id.flContent, myFragment).addToBackStack(null).commit();

                }
            });
        }
    }


    public CryptoListAdapter(List<Cryptocurrency> cryptoList) {
        this.cryptoList = cryptoList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.crypto_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Cryptocurrency crypto = cryptoList.get(position);
        Context context = holder.itemView.getContext();
        Resources res = context.getResources();
        String icon_name = crypto.getSymbol().toLowerCase();

        try {
            final int resourceId = res.getIdentifier(icon_name, "drawable",
                    context.getPackageName());
            holder.crypto_icon.setImageDrawable(res.getDrawable(resourceId));
        }catch (Resources.NotFoundException e){
            holder.crypto_icon.setImageDrawable(res.getDrawable(R.drawable.question_mark));
        }


        holder.rank.setText(String.valueOf(crypto.getRank()));
        holder.name.setText(crypto.getName());
        holder.price.setText(String.valueOf(crypto.getPriceUSD()));
        holder.change.setText(String.valueOf(crypto.getDayPercentChange()));
        holder.cryptocurrency = crypto;

        if(crypto.isFavourite()){
            holder.favourite_icon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_favorite_added));
        }else{
            holder.favourite_icon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_favourite));
        }


    }

    @Override
    public int getItemCount() {
        return cryptoList.size();
    }
}