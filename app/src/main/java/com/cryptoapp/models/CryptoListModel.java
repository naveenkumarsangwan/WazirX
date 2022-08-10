package com.cryptoapp.models;

import com.google.gson.annotations.SerializedName;

public class CryptoListModel {
    @SerializedName("symbol")
    private String symbol;

    @SerializedName("baseAsset")
    private String baseAsset;

    @SerializedName("quoteAsset")
    private String quoteAsset;

    @SerializedName("openPrice")
    private String openPrice;

    @SerializedName("lowPrice")
    private String lowPrice;

    @SerializedName("highPrice")
    private String highPrice;

    @SerializedName("lastPrice")
    private String lastPrice;

    @SerializedName("volume")
    private String volume;

    @SerializedName("bidPrice")
    private String bidPrice;

    @SerializedName("askPrice")
    private String askPrice;

    @SerializedName("at")
    private String at;


    public CryptoListModel(String symbol, String baseAsset, String quoteAsset, String openPrice, String lowPrice, String highPrice, String lastPrice, String volume, String bidPrice, String askPrice, String at) {
        this.symbol = symbol;
        this.baseAsset = baseAsset;
        this.quoteAsset = quoteAsset;
        this.openPrice = openPrice;
        this.lowPrice = lowPrice;
        this.highPrice = highPrice;
        this.lastPrice = lastPrice;
        this.volume = volume;
        this.bidPrice = bidPrice;
        this.askPrice = askPrice;
        this.at = at;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getBaseAsset() {
        return baseAsset;
    }

    public void setBaseAsset(String baseAsset) {
        this.baseAsset = baseAsset;
    }

    public String getQuoteAsset() {
        return quoteAsset;
    }

    public void setQuoteAsset(String quoteAsset) {
        this.quoteAsset = quoteAsset;
    }

    public String getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(String openPrice) {
        this.openPrice = openPrice;
    }

    public String getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(String lowPrice) {
        this.lowPrice = lowPrice;
    }

    public String getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(String highPrice) {
        this.highPrice = highPrice;
    }

    public String getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(String lastPrice) {
        this.lastPrice = lastPrice;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(String bidPrice) {
        this.bidPrice = bidPrice;
    }

    public String getAskPrice() {
        return askPrice;
    }

    public void setAskPrice(String askPrice) {
        this.askPrice = askPrice;
    }

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }


}
