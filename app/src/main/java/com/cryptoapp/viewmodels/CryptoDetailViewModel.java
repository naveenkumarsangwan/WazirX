package com.cryptoapp.viewmodels;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cryptoapp.apis.APIServices;
import com.cryptoapp.apis.RetroInstance;
import com.cryptoapp.models.CryptoDetailModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CryptoDetailViewModel extends ViewModel {
    private final MutableLiveData<List<CryptoDetailModel>> cryptoList;

    public CryptoDetailViewModel() {
        cryptoList = new MutableLiveData<>();
    }

    public MutableLiveData<List<CryptoDetailModel>> getCryptoListObserver() {
        return cryptoList;
    }

    public void makeApiCall(String symbols) {
        APIServices apiServices = RetroInstance.getRetroClient().create(APIServices.class);
        Call<List<CryptoDetailModel>> call = apiServices.getCryptoDetailList(symbols);

        call.enqueue(new Callback<List<CryptoDetailModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<CryptoDetailModel>> call, @NonNull Response<List<CryptoDetailModel>> response) {
                cryptoList.postValue(response.body());
                assert response.body() != null;
                //Log.e("response :", response.body().toString());
            }

            @Override
            public void onFailure(@NonNull Call<List<CryptoDetailModel>> call, @NonNull Throwable t) {
                cryptoList.postValue(null);
               // Log.e("Error :", t.getMessage());
            }
        });
    }


}
