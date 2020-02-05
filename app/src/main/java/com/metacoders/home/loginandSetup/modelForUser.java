package com.metacoders.home.loginandSetup;

public class modelForUser {

    String ph , username ;

    public modelForUser() {
    }

    public modelForUser(String ph, String username) {
        this.ph = ph;
        this.username = username;
    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
