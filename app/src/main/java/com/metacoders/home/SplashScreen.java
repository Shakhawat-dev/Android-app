package com.metacoders.home;

import android.content.Intent;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;


public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = new Intent(this , Home_Activity.class);
        startActivity(i);
        finish();


        /*
        getSupportActionBar().hide();
        EasySplashScreen config = new EasySplashScreen(SplashScreen.this)

                .withFullScreen()
                .withTargetActivity(Home_Activity.class)
                .withSplashTimeOut(2000)
                .withFooterText("\nBuild By MetaCoders")

                .withFullScreen()
                .withBackgroundResource(R.drawable.splash_background);


       // config.getHeaderTextView().setTextColor(android.graphics.Color.WHITE);
        config.getFooterTextView().setTextColor(android.graphics.Color.WHITE);
       // config.getAfterLogoTextView().setTextColor(android.graphics.Color.WHITE);
       // config.getBeforeLogoTextView().setTextColor(android.graphics.Color.WHITE);
        View view = config.create();


        setContentView(view);

*/

}}
