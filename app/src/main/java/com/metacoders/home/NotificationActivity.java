package com.metacoders.home;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

public class NotificationActivity extends AppCompatActivity implements View.OnClickListener{

    private CardView bcsButton, bankButton, govtButton, teacherButton;

    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this,new Crashlytics());
        setContentView(R.layout.activity_notification);

        //For Drawer
        mDrawerlayout = (DrawerLayout) findViewById(R.id.notification_activity);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //defining cardButtons
        bcsButton = (CardView) findViewById(R.id.bcs_Button);
        bankButton = (CardView) findViewById(R.id.bank_Button);
        govtButton = (CardView) findViewById(R.id.govt_Button);
        teacherButton = (CardView) findViewById(R.id.teacher_Button);

        //Click Listener to CardButton
        bcsButton.setOnClickListener(this);
        bankButton.setOnClickListener(this);
        govtButton.setOnClickListener(this);
        teacherButton.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onClick (View view){
        Intent i;

        switch (view.getId()) {
            case R.id.bcs_Button:
                i = new Intent(this, BcsButtonActivity.class);
                startActivity(i);
                break;
            default:
                break;

        }

    }
}
