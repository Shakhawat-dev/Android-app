package com.metacoders.home.model;

public class modelForProfile {
    String name , phone ;

    public modelForProfile(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public modelForProfile() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
