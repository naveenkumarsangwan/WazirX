package com.cryptoapp.apis;

import com.cryptoapp.models.CryptoDetailModel;
import com.cryptoapp.models.CryptoListModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIServices {
    @GET("24hr")
    Call<List<CryptoListModel>> getCryptoList();

    @GET("24hr")
    Call<List<CryptoDetailModel>> getCryptoDetailList(@Query("symbol") String symbol);
}
