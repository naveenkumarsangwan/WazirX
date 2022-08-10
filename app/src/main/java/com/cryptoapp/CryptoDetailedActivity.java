package com.cryptoapp;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.cryptoapp.models.CryptoDetailModel;
import com.cryptoapp.viewmodels.CryptoDetailViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.widget.TextView;

import java.util.List;


public class CryptoDetailedActivity extends AppCompatActivity {
    TextView symbol, quoteAsset, volume, highPrice, lowPrice, lastPrice, baseAsset, openPrice;
    CryptoDetailViewModel cryptoDetailViewModel;
    List<CryptoDetailModel> cryptoList;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crypto_detailed);
        String symbols = getIntent().getStringExtra("symbol");
        Log.e("symbol", symbols);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        symbol = findViewById(R.id.view_symbol);
        quoteAsset = findViewById(R.id.view_quoteasset);
        volume = findViewById(R.id.view_volume);
        highPrice = findViewById(R.id.view_highprice);
        lowPrice = findViewById(R.id.view_lowprice);
        lastPrice = findViewById(R.id.view_lastprice);
        baseAsset = findViewById(R.id.view_baseasset);
        openPrice = findViewById(R.id.view_openprice);


        //noinspection deprecation
        cryptoDetailViewModel = ViewModelProviders.of(this).get(CryptoDetailViewModel.class);
        cryptoDetailViewModel.getCryptoListObserver().observe(this, cryptoListModels -> {
            if (cryptoListModels != null) {
                cryptoList = cryptoListModels;
                cryptoDetailViewModel = new ViewModelProvider(this).get(CryptoDetailViewModel.class);
            }
        });

        cryptoDetailViewModel.makeApiCall(symbols);

        symbol.setText(getIntent().getStringExtra("symbol"));
        baseAsset.setText(getIntent().getStringExtra("baseAsset"));
        quoteAsset.setText(getIntent().getStringExtra("quoteAsset"));
        openPrice.setText(getIntent().getStringExtra("openPrice"));
        lowPrice.setText(getIntent().getStringExtra("lowPrice"));
        highPrice.setText(getIntent().getStringExtra("highPrice"));
        lastPrice.setText(getIntent().getStringExtra("lastPrice"));
        volume.setText(getIntent().getStringExtra("volume"));

    }
}