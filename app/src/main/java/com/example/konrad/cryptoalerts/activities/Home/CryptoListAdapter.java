package com.example.konrad.cryptoalerts.activities.Home;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.konrad.cryptoalerts.R;
import com.example.konrad.cryptoalerts.utils.stringUtils.ColorUtils;
import com.example.konrad.cryptoalerts.utils.stringUtils.FiatUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CryptoListAdapter extends RecyclerView.Adapter<CryptoListAdapter.MyViewHolder>  {

    private IHomePresenter presenter;

    public class MyViewHolder extends RecyclerView.ViewHolder implements ICryptocurrenciesItemView {
        @BindView(R.id.crypto_list_row)
        RelativeLayout row;

        @BindView(R.id.tv_rank)
        TextView rank;
        @BindView(R.id.tv_name)
        TextView name;
        @BindView(R.id.tv_price)
        TextView price;
        @BindView(R.id.tv_change)
        TextView change;
        @BindView(R.id.iv_icon)
        ImageView crypto_icon;
        @BindView(R.id.iv_alert)
        ImageView alert_icon;
        @BindView(R.id.iv_favourite)
        ImageView favourite_icon;
        @BindView(R.id.iv_wallet)
        ImageView wallet_icon;



        private MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            alert_icon.setOnClickListener(view1 -> Toast.makeText(view1.getContext(), "Alert_icon clicked!", Toast.LENGTH_LONG).show());

            favourite_icon.setOnClickListener(view12 -> presenter.changeFavourite(this, getAdapterPosition(), CryptoListAdapter.this));

            wallet_icon.setOnClickListener(view13 -> {
                presenter.openWalletFromHomeFragment(view.getContext(), getAdapterPosition());
            });
        }

        @Override
        public void setFavouriteIcon(boolean favourite) {
            Resources res = itemView.getContext().getResources();
            if(favourite){
                favourite_icon.setImageDrawable(res.getDrawable(R.drawable.ic_favorite_added));
            }else{
                favourite_icon.setImageDrawable(res.getDrawable(R.drawable.ic_favourite));
            }
        }

        @Override
        public void setWalletIcon(boolean wallet) {
            Resources res = itemView.getContext().getResources();
            if(wallet){
                wallet_icon.setImageDrawable(res.getDrawable(R.drawable.ic_full_wallet));
            }else{
                wallet_icon.setImageDrawable(res.getDrawable(R.drawable.ic_wallet));
            }
        }

        @Override
        public void setAlertIcon(boolean alert) {
            Resources res = itemView.getContext().getResources();
            if(alert){
                alert_icon.setImageDrawable(res.getDrawable(R.drawable.ic_active_alert));
            }else{
                alert_icon.setImageDrawable(res.getDrawable(R.drawable.ic_alert));
            }
        }

        @Override
        public void setCryptoIcon(String iconName) {
            Context context = itemView.getContext();
            Resources res = itemView.getResources();
            try {
                final int resourceId = res.getIdentifier(iconName, "drawable",
                        context.getPackageName());
                crypto_icon.setImageDrawable(res.getDrawable(resourceId));
            }catch (Resources.NotFoundException e){
                crypto_icon.setImageDrawable(res.getDrawable(R.drawable.question_mark));
            }
        }

        @Override
        public void setRank(int rank) {
            this.rank.setText(String.valueOf(rank));
        }

        @Override
        public void setName(String name) {
            this.name.setText(name);
        }

        @Override
        public void setPrice(double price) {
            String sPrice = FiatUtils.doubleToFiatValue(price, 4, FiatUtils.DOLAR);
            this.price.setText(sPrice);
        }

        @Override
        public void setChange(double change) {
            ColorUtils.setChangeText(this.change, change);
        }
    }


    CryptoListAdapter(IHomePresenter presenter) {
        this.presenter = presenter;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.crypto_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        presenter.onBindCryptocurrencyItemView(holder, position);
        ColorUtils.setRowBackgroundColor(holder.row, position);
    }

    @Override
    public int getItemCount() {
        return presenter.getItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}