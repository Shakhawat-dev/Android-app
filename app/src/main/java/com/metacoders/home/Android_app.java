package com.metacoders.home;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class Android_app extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
