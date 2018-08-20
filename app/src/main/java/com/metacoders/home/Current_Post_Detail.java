package com.metacoders.home;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Current_Post_Detail extends AppCompatActivity {
    TextView mTitleTv, mDetailTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current__post__detail);



        //Action bar
        ActionBar actionBar = getSupportActionBar();
        //Actionbar title
        actionBar.setTitle("Post Detail");
        //set back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        //initialize views
        mTitleTv = findViewById(R.id.detail_tile_current);
        mDetailTv = findViewById(R.id.detail_description_current);
        //get data from intent
        String title = getIntent().getStringExtra("title");
        String desc = getIntent().getStringExtra("description");
        //set data to views
        mTitleTv.setText(title);
        mDetailTv.setText(desc);
    }
}
