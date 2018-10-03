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

    private CardView bcsButton, bankButton, govtButton, teacherButton , banglaButton  , engbtn , ictButton, mathButton
           ,  BD_Button , GKButton ,Internation_btn , Others_btn ;

    //private DrawerLayout mDrawerlayout;
    //private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this,new Crashlytics());
        setContentView(R.layout.activity_career_prep);

        //For Drawer
        /*
        mDrawerlayout = (DrawerLayout) findViewById(R.id.notification_activity);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); */


        //defining cardButtons
        GKButton=(CardView)findViewById(R.id.general_btn);
        mathButton= (CardView)findViewById(R.id.math_btn) ;
        bcsButton = (CardView) findViewById(R.id.bcs_Button);
        bankButton = (CardView) findViewById(R.id.bank_Button);
        govtButton = (CardView) findViewById(R.id.govt_Button);
        teacherButton = (CardView) findViewById(R.id.teacher_Button);
        banglaButton= (CardView)findViewById(R.id.bangla_Button);
        engbtn = (CardView)findViewById(R.id.english_Button) ;
        ictButton =(CardView)findViewById(R.id.ict_btn);
        BD_Button = (CardView)findViewById(R.id.bdtopic_Button);
        Internation_btn= (CardView)findViewById(R.id.international_btn);
        Others_btn= (CardView)findViewById(R.id.others_btn);


        //Click Listener to CardButton
        bcsButton.setOnClickListener(this);
        bankButton.setOnClickListener(this);
        govtButton.setOnClickListener(this);
        teacherButton.setOnClickListener(this);
        banglaButton.setOnClickListener(this);
        engbtn.setOnClickListener(this);
        ictButton.setOnClickListener(this);
        mathButton.setOnClickListener(this);
        BD_Button.setOnClickListener(this);
        Internation_btn.setOnClickListener(this);
        GKButton.setOnClickListener(this);
        Others_btn.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        /*
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        } */
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

            case R.id.govt_Button:
                i = new Intent(this, Govt_job_prep.class);
                startActivity(i);
                break;

            case R.id.bank_Button:
                i = new Intent(this, Bank_Prep.class);
                startActivity(i);
                break;

            case R.id.teacher_Button:
             i=new Intent(this , Shikok_nivondon.class);
             startActivity(i);
             break;

            case R.id.english_Button:
                i=new Intent(this , career_prep_Eng.class);
                startActivity(i);
                break;


            case R.id.bangla_Button:
                i=new Intent(this , career_prep_Bangla.class);
                startActivity(i);
                break;

            case R.id.math_btn:
                i = new Intent(this, career_prep_Math.class);
                startActivity(i);
                break;

            case R.id.ict_btn:
                i = new Intent(this, career_prep_ict.class);
                startActivity(i);
                break;

            case R.id.bdtopic_Button:
                i = new Intent(this, career_prep_Bangladesh.class);
                startActivity(i);
                break;
            case R.id.international_btn:
                i = new Intent(this, career_prep_International.class);
                startActivity(i);
                break;

            case R.id.general_btn:
                i = new Intent(this, career_prep_GK.class);
                startActivity(i);
                break;


            case R.id.others_btn:
                i = new Intent(this, career_prep_Others.class);
                startActivity(i);
                break;


            default:
                break;

        }

    }
}
