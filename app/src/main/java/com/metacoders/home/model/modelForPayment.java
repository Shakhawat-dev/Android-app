package com.metacoders.home.model;

public class modelForPayment {

    String duration , purchaged_date  , coins ;

    public modelForPayment() {
    }

    public modelForPayment(String duration, String purchaged_date, String coins) {
        this.duration = duration;
        this.purchaged_date = purchaged_date;
        this.coins = coins;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPurchaged_date() {
        return purchaged_date;
    }

    public void setPurchaged_date(String purchaged_date) {
        this.purchaged_date = purchaged_date;
    }

    public String getCoins() {
        return coins;
    }

    public void setCoins(String coins) {
        this.coins = coins;
    }
}
