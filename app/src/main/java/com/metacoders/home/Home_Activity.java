package com.metacoders.home;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.onesignal.OneSignal;

import io.fabric.sdk.android.Fabric;

public class Home_Activity extends AppCompatActivity implements View.OnClickListener {
    private CardView jobsButton, notificationButton, quizButton, vocaButton ,
            articleButton, current_Button, contactButton, settingsButton, aboutButton , Qus_Bank_Button
            , bisesButton  , feature_Button , edotrial_btn;
    //private DrawerLayout mDrawerlayout;
    //private ActionBarDrawerToggle mToggle;

    FirebaseAuth mAuth;
    AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_home);

        adView=(AdView)findViewById(R.id.adView_homepage);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);


// notification
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();

startService(new Intent(this, FireBase_notification.class));


//0ne time Dialogue Box
        SharedPreferences  prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean fstart = prefs.getBoolean("firstStart", true);
        if(fstart){

            show_Dialog_after_Install();
        }



            //selecting customs fonts
            TextView cakri = (TextView) findViewById(R.id.cakribakri);
            Typeface ace = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi_29-05-06.ttf");
            cakri.setTypeface(ace);

            TextView cakri_p = (TextView) findViewById(R.id.cakri_prostoti);
            Typeface face = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi_29-05-06.ttf");
            cakri_p.setTypeface(face);


            TextView not = (TextView) findViewById(R.id.notice_text);
            Typeface face2 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi_29-05-06.ttf");
            not.setTypeface(face2);

            TextView amader_tit = (TextView) findViewById(R.id.amader_title);
            Typeface amader = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi_29-05-06.ttf");
            amader_tit.setTypeface(amader);

            TextView settings = (TextView) findViewById(R.id.settings_ttile);
            Typeface set = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi_29-05-06.ttf");
            settings.setTypeface(set);

            TextView jog = (TextView) findViewById(R.id.jog);
            Typeface con = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi_29-05-06.ttf");
            jog.setTypeface(con);

            TextView vid = (TextView) findViewById(R.id.Editorial_title);
            Typeface vidTIt = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi_29-05-06.ttf");
            vid.setTypeface(vidTIt);

            TextView nott = (TextView) findViewById(R.id.voca_title);
            Typeface face2a = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi_29-05-06.ttf");
            nott.setTypeface(face2a);

            TextView bTitle = (TextView) findViewById(R.id.bank_title);
            Typeface face3 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi_29-05-06.ttf");
            bTitle.setTypeface(face3);

            TextView notFeture = (TextView) findViewById(R.id.feature_title);
            Typeface face2featre = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi_29-05-06.ttf");
            notFeture.setTypeface(face2featre);


            TextView notCurrent = (TextView) findViewById(R.id.current_title);
            Typeface face2cu = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi_29-05-06.ttf");
            notCurrent.setTypeface(face2cu);

            TextView notQuiz = (TextView) findViewById(R.id.quiz_title);
            Typeface face2Quiz = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi_29-05-06.ttf");
            notQuiz.setTypeface(face2Quiz);

            TextView notBises = (TextView) findViewById(R.id.bises);
            Typeface face2b = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi_29-05-06.ttf");
            notBises.setTypeface(face2b);


            //defining cardButtons
            edotrial_btn=(CardView)findViewById(R.id.video_Button);
            jobsButton = (CardView) findViewById(R.id.jobs_Button);
            notificationButton = (CardView) findViewById(R.id.notification_Button);
            quizButton = (CardView) findViewById(R.id.quiz_Button);
            articleButton = (CardView) findViewById(R.id.article_Button);
            current_Button = (CardView) findViewById(R.id.current_Button);
            contactButton = (CardView) findViewById(R.id.contact_Button);
            settingsButton = (CardView) findViewById(R.id.settings_Button);
            aboutButton = (CardView) findViewById(R.id.about_Button);
            vocaButton = (CardView)findViewById(R.id.vocabulari_Button);
            Qus_Bank_Button = (CardView)findViewById(R.id.questionBank_Button);
            bisesButton = (CardView)findViewById(R.id.Bises_Btn) ;
            feature_Button = (CardView)findViewById(R.id.feature_Button);
            //Click Listener to CardButton
            edotrial_btn.setOnClickListener(this);

            jobsButton.setOnClickListener(this);
            notificationButton.setOnClickListener(this);
            quizButton.setOnClickListener(this);
            articleButton.setOnClickListener(this);
            current_Button.setOnClickListener(this);
            contactButton.setOnClickListener(this);
            settingsButton.setOnClickListener(this);
            aboutButton.setOnClickListener(this);
            vocaButton.setOnClickListener(this);
            Qus_Bank_Button.setOnClickListener(this);
            bisesButton.setOnClickListener(this);
            feature_Button.setOnClickListener(this);


            // checking  net acces
            if (!isConnected(Home_Activity.this)) buildDialog(Home_Activity.this).show();
            else {

                //without interent what u wnat future devlopment

            }



            //For drawer
           /* mDrawerlayout = (DrawerLayout) findViewById(R.id.home_Drawer);
            //mDrawerlayout = (DrawerLayout) findViewById(R.id.contact_activity);
            mToggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
            mDrawerlayout.addDrawerListener(mToggle);
            mToggle.syncState();
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/

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

        @Override
        public void onClick (View view){
            Intent i;

            switch (view.getId()) {
                case R.id.questionBank_Button:
                    i = new Intent(this, Qustion_Bank.class);
                    startActivity(i);
                    break;

                case R.id.Bises_Btn:
                    i = new Intent(this, Bises.class);
                    startActivity(i);
                    break;

                case R.id.feature_Button:
                    i = new Intent(this, Feature_Activity.class);
                    startActivity(i);
                    break;


                case R.id.vocabulari_Button:
                    i = new Intent(this, Voca_activity.class);
                    startActivity(i);
                    break;
                case R.id.jobs_Button:
                    i = new Intent(this, PostsListActivity.class);
                    startActivity(i);
                    break;
                case R.id.notification_Button:
                    i = new Intent(this, NotificationActivity.class);
                    startActivity(i);
                    break;
                case R.id.quiz_Button:
                    i = new Intent(this, QuizActivity.class);
                    startActivity(i);
                    break;
                case R.id.article_Button:
                    i = new Intent(this, ArticleActivity.class);
                    startActivity(i);
                    break;
                case R.id.current_Button:
                    i = new Intent(this, Current_Activity.class);
                    startActivity(i);
                    break;
                case R.id.contact_Button:
                    i = new Intent(this, ContactActivity.class);
                    startActivity(i);
                    break;
                case R.id.settings_Button:
                    i = new Intent(this, SettingsActivity.class);
                    startActivity(i);
                    break;
                case R.id.about_Button:
                    i = new Intent(this, AboutActivity.class);
                    startActivity(i);
                    break;
                case R.id.video_Button:
                        i = new Intent(this, Editorial.class);
                        startActivity(i);
                        break;
                default:
                    break;
            }

        }
        public boolean isConnected (Context context){

            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netinfo = cm.getActiveNetworkInfo();

            if (netinfo != null && netinfo.isConnectedOrConnecting()) {
                android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

                if ((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting()))
                    return true;
                else return false;
            } else
                return false;
        }

        public AlertDialog.Builder buildDialog (Context c){

            AlertDialog.Builder builder = new AlertDialog.Builder(c);
            builder.setTitle("No Internet Connection");
            builder.setMessage("You need to have Mobile Data or wifi to access this App. Press ok to Continue Offline");

            builder.setPositiveButton("Use Offline", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {


                }
            });

            return builder;
           }
private  void show_Dialog_after_Install(){


        new AlertDialog.Builder(this)
                .setTitle("Hey User !!")
                .setMessage("Thanks For Installing The App . This is A Beta Version OF the Full App . So You May face some Problem")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create().show();
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart" , false ) ;
        editor.apply();
}


    }
