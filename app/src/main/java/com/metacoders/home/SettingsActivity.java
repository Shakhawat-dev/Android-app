package com.metacoders.home;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

public class SettingsActivity extends AppCompatActivity {
    Button btn , send_btn ;
    //private DrawerLayout mDrawerlayout;
    //private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this,new Crashlytics());
      //  setContentView(R.layout.activity_shop2);

       // btn = (Button)findViewById(R.id.button_register);




      //  btn.setOnClickListener(new View.OnClickListener() {
          //  @Override
          //  public void onClick(View view) {
             //   Intent intent = new Intent(SettingsActivity.this ,login.class);
               // startActivity(intent);
          //  }
    //    });
        /*
        mDrawerlayout = (DrawerLayout) findViewById(R.id.setting_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        */

    }




}
