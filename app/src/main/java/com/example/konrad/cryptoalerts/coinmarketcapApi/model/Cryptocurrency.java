package com.example.konrad.cryptoalerts.coinmarketcapApi.model;

import com.google.gson.annotations.SerializedName;
/**
 * Created by Konrad on 2018-04-29.
 */

public class Cryptocurrency {
    private String id;
    private String name;
    private String symbol;
    private int rank;
    @SerializedName("price_usd")
    private double priceUSD;
    @SerializedName("price_btc")
    private double priceBTC;
    @SerializedName("24h_volume_usd")
    private double dailyVolumeUSD;
    @SerializedName("market_cap_usd")
    private double marketCapUSD;
    @SerializedName("available_supply")
    private double availableSupply;
    @SerializedName("total_supply")
    private double totalSupply;
    @SerializedName("percent_change_1h")
    private double hourPercentChange;
    @SerializedName("percent_change_24h")
    private double dayPercentChange;
    @SerializedName("percent_change_7d")
    private double weekPercentChange;
    @SerializedName("last_updated")
    private int lastUpdated;



    private boolean favourite;

    //Only for tests

    public Cryptocurrency(String id, String name, String symbol, int rank, double priceUSD, double priceBTC, double dailyVolumeUSD, double marketCapUSD, double availableSupply, double totalSupply, double hourPercentChange, double dayPercentChange, double weekPercentChange, int lastUpdated) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.rank = rank;
        this.priceUSD = priceUSD;
        this.priceBTC = priceBTC;
        this.dailyVolumeUSD = dailyVolumeUSD;
        this.marketCapUSD = marketCapUSD;
        this.availableSupply = availableSupply;
        this.totalSupply = totalSupply;
        this.hourPercentChange = hourPercentChange;
        this.dayPercentChange = dayPercentChange;
        this.weekPercentChange = weekPercentChange;
        this.lastUpdated = lastUpdated;
        this.favourite = false;
    }

    @Override
    public String toString() {
        return name+" "+symbol+" "+priceUSD+" "+hourPercentChange;
    }

    public String getId() {return id;}

    public void setId(String id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public double getPriceUSD() {
        return priceUSD;
    }

    public void setPriceUSD(double priceUSD) {
        this.priceUSD = priceUSD;
    }

    public double getPriceBTC() {
        return priceBTC;
    }

    public void setPriceBTC(double priceBTC) {
        this.priceBTC = priceBTC;
    }

    public double getDailyVolumeUSD() {
        return dailyVolumeUSD;
    }

    public void setDailyVolumeUSD(double dailyVolumeUSD) {
        this.dailyVolumeUSD = dailyVolumeUSD;
    }

    public double getMarketCapUSD() {
        return marketCapUSD;
    }

    public void setMarketCapUSD(double marketCapUSD) {
        this.marketCapUSD = marketCapUSD;
    }

    public double getAvailableSupply() {
        return availableSupply;
    }

    public void setAvailableSupply(double availableSupply) {
        this.availableSupply = availableSupply;
    }

    public double getTotalSupply() {
        return totalSupply;
    }

    public void setTotalSupply(double totalSupply) {
        this.totalSupply = totalSupply;
    }

    public double getHourPercentChange() {
        return hourPercentChange;
    }

    public void setHourPercentChange(double hourPercentChange) {
        this.hourPercentChange = hourPercentChange;
    }

    public double getDayPercentChange() {
        return dayPercentChange;
    }

    public void setDayPercentChange(double dayPercentChange) {
        this.dayPercentChange = dayPercentChange;
    }

    public double getWeekPercentChange() {
        return weekPercentChange;
    }

    public void setWeekPercentChange(double weekPercentChange) {
        this.weekPercentChange = weekPercentChange;
    }

    public int getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(int lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }
}
