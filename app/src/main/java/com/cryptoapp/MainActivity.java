package com.cryptoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.cryptoapp.adapters.CryptoListAdapter;
import com.cryptoapp.models.CryptoListModel;
import com.cryptoapp.viewmodels.CryptoListViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recView;
    List<CryptoListModel> cryptoList;
    CryptoListViewModel listViewModel;
    CryptoListAdapter adapter;
    ProgressBar progressBar;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        recView = findViewById(R.id.cryptoRecycler);
        progressBar = findViewById(R.id.progressBar);
        SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            listViewModel.makeApiCall();
            swipeRefreshLayout.setRefreshing(false);
            progressBar.setVisibility(View.VISIBLE);
        });

        recView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CryptoListAdapter(this, cryptoList);
        recView.setAdapter(adapter);

        //noinspection deprecation
        listViewModel = ViewModelProviders.of(this).get(CryptoListViewModel.class);
        listViewModel.getCryptoListObserver().observe(this, cryptoListModels -> {
            if (cryptoListModels != null) {
                cryptoList = cryptoListModels;
                adapter.updateCryptoList(cryptoListModels);
                progressBar.setVisibility(View.GONE);
            }
            if (cryptoListModels == null) {
                recView.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);

            }
        });
        listViewModel.makeApiCall();

    }
}