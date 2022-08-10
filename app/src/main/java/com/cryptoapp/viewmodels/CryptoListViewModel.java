package com.cryptoapp.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cryptoapp.apis.APIServices;
import com.cryptoapp.apis.RetroInstance;
import com.cryptoapp.models.CryptoListModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CryptoListViewModel extends ViewModel {
    private final MutableLiveData<List<CryptoListModel>> cryptoList;

    public CryptoListViewModel() {
        cryptoList = new MutableLiveData<>();
    }

    public MutableLiveData<List<CryptoListModel>> getCryptoListObserver() {
        return cryptoList;
    }

    public void makeApiCall() {
        APIServices apiServices = RetroInstance.getRetroClient().create(APIServices.class);
        Call<List<CryptoListModel>> call = apiServices.getCryptoList();
        call.enqueue(new Callback<List<CryptoListModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<CryptoListModel>> call, @NonNull Response<List<CryptoListModel>> response) {
                cryptoList.postValue(response.body());
                assert response.body() != null;
                //Log.e("Error :", response.body().toString());
            }

            @Override
            public void onFailure(@NonNull Call<List<CryptoListModel>> call, @NonNull Throwable t) {
                cryptoList.postValue(null);
                // Log.e("Error :",t.getMessage().toString());
            }
        });
    }
}
