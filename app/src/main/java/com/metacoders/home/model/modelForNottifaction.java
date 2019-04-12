package com.metacoders.home.model;

public class modelForNottifaction {

    String title , activity ;

    public modelForNottifaction() {
    }

    public modelForNottifaction(String title, String activity) {
        this.title = title;
        this.activity = activity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }
}
