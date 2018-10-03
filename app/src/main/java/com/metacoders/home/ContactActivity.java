package com.metacoders.home;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

public class ContactActivity extends AppCompatActivity {

    //private DrawerLayout mDrawerlayout;
    //private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this,new Crashlytics());
        setContentView(R.layout.activity_contact);

        /*
        mDrawerlayout = (DrawerLayout) findViewById(R.id.contact_activity);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        */
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        /*
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        */
        return super.onOptionsItemSelected(item);
    }
}
