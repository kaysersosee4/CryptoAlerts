package com.example.konrad.cryptoalerts.activities.Wallet;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.konrad.cryptoalerts.R;
import com.example.konrad.cryptoalerts.utils.stringUtils.ColorUtils;
import com.example.konrad.cryptoalerts.utils.stringUtils.FiatUtils;


import butterknife.BindView;
import butterknife.ButterKnife;

public class WalletListAdapter extends RecyclerView.Adapter<WalletListAdapter.WalletViewHolder> {
    private IWalletPresenter presenter;

    public WalletListAdapter(IWalletPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public WalletViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.wallet_list_item, parent, false);

        return new WalletListAdapter.WalletViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WalletViewHolder holder, int position) {
        presenter.onBindWalletItemView(holder, position);
        ColorUtils.setRowBackgroundColor(holder.row, position);
    }


    @Override
    public int getItemCount() {
        return presenter.getItemCount();
    }


    class WalletViewHolder extends RecyclerView.ViewHolder implements IWalletItemView{
        @BindView(R.id.wallet_list_row)
        RelativeLayout row;

        @BindView(R.id.iv_crypto_icon)
        ImageView icon;
        @BindView(R.id.tv_crypto_name)
        TextView name;
        @BindView(R.id.tv_amount)
        TextView amount;
        @BindView(R.id.tv_value)
        TextView value;

        WalletViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @Override
        public void setIcon(String symbol) {
            Context context = itemView.getContext();
            Resources res = itemView.getResources();
            try {
                final int resourceId = res.getIdentifier(symbol.toLowerCase(), "drawable",
                        context.getPackageName());
                icon.setImageDrawable(res.getDrawable(resourceId));
            }catch (Resources.NotFoundException e){
                icon.setImageDrawable(res.getDrawable(R.drawable.question_mark));
            }
        }

        @Override
        public void setName(String name) {
            this.name.setText(name);
        }

        @Override
        public void setAmount(double amount) {
            String sAmount = String.valueOf(amount);
            this.amount.setText(sAmount);
        }

        @Override
        public void setValue(double value) {
            String sValue = FiatUtils.doubleToFiatValue(value, 2, FiatUtils.DOLAR);
            this.value.setText(sValue);
        }
    }
}
