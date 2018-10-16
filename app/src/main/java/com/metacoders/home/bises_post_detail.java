package com.metacoders.home;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class bises_post_detail extends AppCompatActivity {
    TextView mTitleTv, mDetailTv;
    AdView mAdView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bises_post_detail);

        //add
        mAdView = findViewById(R.id.adView_Biases);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



        //Action bar
        ActionBar actionBar = getSupportActionBar();
        //Actionbar title
        actionBar.setTitle("Post Detail");
        //set back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        //initialize views
        mTitleTv = findViewById(R.id.detail_tile_bises);
        mDetailTv = findViewById(R.id.detail_description_bises);
        //get data from intent
        String title = getIntent().getStringExtra("title");
        String desc = getIntent().getStringExtra("description");
        //set data to views
        mTitleTv.setText(title);
        mDetailTv.setText(desc);
    }
}
