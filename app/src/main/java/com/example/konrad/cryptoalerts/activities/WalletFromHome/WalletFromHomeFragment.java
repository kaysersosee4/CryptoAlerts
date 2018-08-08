package com.example.konrad.cryptoalerts.activities.WalletFromHome;


import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.konrad.cryptoalerts.R;
import com.example.konrad.cryptoalerts.coinmarketcapApi.model.Cryptocurrency;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WalletFromHomeFragment extends Fragment implements IWalletFromHomeView{
    IWalletFromHomePresenter presenter;

    @BindView(R.id.iv_crypto_icon)
    ImageView icon;

    @BindView(R.id.tv_crypto_name)
    TextView name;
    @BindView(R.id.tv_crypto_symbol)
    TextView symbol;
    @BindView(R.id.tv_unit_price)
    TextView unitPrice;
    @BindView(R.id.tv_value)
    TextView value;

    @BindView(R.id.et_amount)
    EditText amount;

    @BindView(R.id.b_back)
    Button back;
    @BindView(R.id.b_confirm)
    Button confirm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_wallet_from_home,
                container, false);

        ButterKnife.bind(this, view);


        Bundle bundle = getArguments();
        Cryptocurrency cryptocurrency = (Cryptocurrency) bundle.getSerializable("CRYPTOCURRENCY");
        presenter = new WalletFromHomePresenter(getContext(), this, cryptocurrency);
        presenter.setData();

        back.setOnClickListener(v -> presenter.goPreviousFragment(this));

        confirm.setOnClickListener(v -> presenter.updateWallet(amount.getText().toString()));



        return view;
    }


    @Override
    public void setIcon(String symbol) {
        Context context = getContext();
        Resources res = getResources();
        String lowerCaseSymbol = symbol.toLowerCase();

        try {
            final int resourceId = res.getIdentifier(lowerCaseSymbol, "drawable",
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
    public void setSymbol(String symbol) {
        this.symbol.setText(symbol.toUpperCase());
    }

    @Override
    public void setUnitPrice(double price) {
        String sPrice = String.valueOf(price);
        this.unitPrice.setText(sPrice);
    }

    @Override
    public void setValue(double value) {
        String sValue = String.valueOf(value);
        this.value.setText(sValue);
    }

    @Override
    public void setAmount(double amount) {
        String sAmount = String.valueOf(amount);
        this.amount.setText(sAmount);
    }
}