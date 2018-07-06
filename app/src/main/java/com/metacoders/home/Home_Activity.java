package com.metacoders.home;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

public class Home_Activity extends AppCompatActivity implements View.OnClickListener{
    private CardView jobsButton, notificationButton, quizButton,
            articleButton, newsButton, contactButton, settingsButton, aboutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_home);

        //defining cardButtons
        jobsButton= (CardView)findViewById(R.id.jobs_Button);
        notificationButton= (CardView)findViewById(R.id.notification_Button);
        quizButton= (CardView)findViewById(R.id.quiz_Button);
        articleButton= (CardView)findViewById(R.id.article_Button);
        newsButton= (CardView)findViewById(R.id.news_Button);
        contactButton= (CardView)findViewById(R.id.contact_Button);
        settingsButton= (CardView)findViewById(R.id.settings_Button);
        aboutButton= (CardView)findViewById(R.id.about_Button);

        //Click Listener to CardButton
        jobsButton.setOnClickListener(this);
        notificationButton.setOnClickListener(this);
        quizButton.setOnClickListener(this);
        articleButton.setOnClickListener(this);
        newsButton.setOnClickListener(this);
        contactButton.setOnClickListener(this);
        settingsButton.setOnClickListener(this);
        aboutButton.setOnClickListener(this);


        // checking  net acces
        if(!isConnected(Home_Activity.this)) buildDialog(Home_Activity.this).show() ;
        else {

            //without interent what u wnat future devlopment

        }

    }

    @Override
    public void onClick(View view) {
        Intent i;

        switch (view.getId()){
            case R.id.jobs_Button : i= new Intent(this,PostsListActivity.class);startActivity(i); break;
            case R.id.notification_Button : i= new Intent(this,NotificationActivity.class);startActivity(i); break;
            case R.id.quiz_Button : i= new Intent(this,QuizActivity.class);startActivity(i); break;
            case R.id.article_Button : i= new Intent(this,ArticleActivity.class);startActivity(i); break;
            case R.id.news_Button : i= new Intent(this,NewsActivity.class);startActivity(i); break;
            case R.id.contact_Button : i= new Intent(this,ContactActivity.class);startActivity(i); break;
            case R.id.settings_Button : i= new Intent(this,SettingsActivity.class);startActivity(i); break;
            case R.id.about_Button : i= new Intent(this,AboutActivity.class);startActivity(i); break;
            default: break;
        }

    }
    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile != null &&  mobile.isConnectedOrConnecting())||(wifi != null && wifi.isConnectedOrConnecting())) return true;
        else return false;
        } else
        return false;
    }

    public AlertDialog.Builder buildDialog(Context c) {

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
}
