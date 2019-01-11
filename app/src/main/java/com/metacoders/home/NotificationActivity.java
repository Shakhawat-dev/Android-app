package com.metacoders.home;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import io.fabric.sdk.android.Fabric;

public class NotificationActivity extends AppCompatActivity implements View.OnClickListener{

    private CardView bcsButton, bankButton, govtButton, teacherButton , banglaButton  , engbtn , ictButton, mathButton
           ,  BD_Button , GKButton ,Internation_btn , Others_btn ;

    //private DrawerLayout mDrawerlayout;
    //private ActionBarDrawerToggle mToggle;
    DrawerLayout drawerLayout ;
    ActionBarDrawerToggle toggle ;
    NavigationView navigationView ;
    AdView mAdView , adviewBottom , adviewMiddle ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this,new Crashlytics());
        setContentView(R.layout.activity_career_prep);

        //advertisement LOad
        mAdView = findViewById(R.id.adView_prep_carrer_up);
        adviewBottom =findViewById(R.id.adView_prep_carrer_Middle);
        adviewMiddle = findViewById(R.id.adView_prep_carrer_down);
        AdRequest adRequest = new AdRequest.Builder().build();
        adviewMiddle.loadAd(adRequest);
        adviewBottom.loadAd(adRequest);
        mAdView.loadAd(adRequest);


        drawerLayout = findViewById(R.id.drawerId_career_prep);
        navigationView=findViewById(R.id.NAVVIew_ID_career_prep);

        toggle = new ActionBarDrawerToggle(this,drawerLayout ,R.string.nav_open ,R.string.nav_close );


        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.cakRi_menu:
                        Intent i = new Intent(getApplicationContext() ,PostsListActivity.class);
                        startActivity(i);
                        break;
                    case R.id.Bcs_prep_menu:
                        Intent bcs = new Intent(getApplicationContext() ,BcsButtonActivity.class);
                        startActivity(bcs);
                        break;
                    case R.id.job_prep_menu:
                        Intent jobprep = new Intent(getApplicationContext() ,NotificationActivity.class);
                        startActivity(jobprep);
                        break;
                    case R.id.Bank_prep_menu:
                        Intent Bank = new Intent(getApplicationContext() ,Bank_Prep.class);
                        startActivity(Bank);
                        break;
                    case R.id.Govt_job_prep_menu:
                        Intent gob = new Intent(getApplicationContext() ,Govt_job_prep.class);
                        startActivity(gob);
                        break;
                    case R.id.Others_prep_menu:
                        Intent others = new Intent(getApplicationContext() ,career_prep_Others.class);
                        startActivity(others);
                        break;
                    case R.id.noticeBoard_prep_menu:
                        Intent notiice = new Intent(getApplicationContext() ,ArticleActivity.class);
                        startActivity(notiice);
                        break;
                    case R.id.bises_prep_menu:
                        Intent bises = new Intent(getApplicationContext() ,Bises.class);
                        startActivity(bises);
                        break;
                    case R.id.Quiz_prep_menu:
                        Intent quiz = new Intent(getApplicationContext() ,QuizActivity.class);
                        startActivity(quiz);
                        break;
                    case R.id.Samprotik_prep_menu:
                        Intent sam = new Intent(getApplicationContext() ,Current_Activity.class);
                        startActivity(sam);
                        break;
                    case R.id.Voca_prep_menu:
                        Intent voca = new Intent(getApplicationContext() ,Voca_activity.class);
                        startActivity(voca);
                        break;
                    case R.id.Edotorial_prep_menu:
                        Intent edo = new Intent(getApplicationContext() ,Editorial.class);
                        startActivity(edo);
                        break;
                    case R.id.Shop_prep_menu:
                        Intent shop = new Intent(getApplicationContext() ,Shop.class);
                        startActivity(shop);
                        break;
                    case R.id.contact_us_menu:
                        Intent con = new Intent(getApplicationContext() ,ContactActivity.class);
                        startActivity(con);
                        break;
                    case R.id.Rate_menu:
                        try{
                            startActivity(new Intent(Intent.ACTION_VIEW ,
                                    Uri.parse("market://details?id=" + getPackageName())));

                        } catch (ActivityNotFoundException e){

                            startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName() ) ));

                        }
                        break;


                }
                return false;
            }
        });


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
        if(toggle.onOptionsItemSelected(item)){
            return  true ;
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
