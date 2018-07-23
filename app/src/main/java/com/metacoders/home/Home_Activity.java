package com.metacoders.home;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

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
        //selecting customs fonts
        TextView cakri = (TextView)findViewById(R.id.cakribakri);
        Typeface ace = Typeface.createFromAsset(getAssets(),"fonts/SolaimanLipi_29-05-06.ttf");
        cakri.setTypeface(ace);

        TextView cakri_p = (TextView)findViewById(R.id.cakri_prostoti);
        Typeface face = Typeface.createFromAsset(getAssets(),"fonts/SolaimanLipi_29-05-06.ttf");
        cakri_p.setTypeface(face);


        TextView not=(TextView)findViewById(R.id.notice_text);
        Typeface face2 = Typeface.createFromAsset(getAssets(),"fonts/SolaimanLipi_29-05-06.ttf");
        not.setTypeface(face2);

        TextView amader_tit=(TextView)findViewById(R.id.amader_title);
        Typeface amader = Typeface.createFromAsset(getAssets(),"fonts/SolaimanLipi_29-05-06.ttf");
        amader_tit.setTypeface(amader);

        TextView settings=(TextView)findViewById(R.id.settings_ttile);
        Typeface set= Typeface.createFromAsset(getAssets(),"fonts/SolaimanLipi_29-05-06.ttf");
        settings.setTypeface(set);

        TextView jog=(TextView)findViewById(R.id.jog);
        Typeface con = Typeface.createFromAsset(getAssets(),"fonts/SolaimanLipi_29-05-06.ttf");
        jog.setTypeface(con);

        TextView vid=(TextView)findViewById(R.id.vid_title);
        Typeface vidTIt = Typeface.createFromAsset(getAssets(),"fonts/SolaimanLipi_29-05-06.ttf");
        vid.setTypeface(vidTIt);

        TextView nott=(TextView)findViewById(R.id.voca_title);
        Typeface face2a = Typeface.createFromAsset(getAssets(),"fonts/SolaimanLipi_29-05-06.ttf");
        nott.setTypeface(face2a);

        TextView bTitle=(TextView)findViewById(R.id.bank_title);
        Typeface face3 = Typeface.createFromAsset(getAssets(),"fonts/SolaimanLipi_29-05-06.ttf");
        bTitle.setTypeface(face3);

        TextView notFeture=(TextView)findViewById(R.id.feature_title);
        Typeface face2featre = Typeface.createFromAsset(getAssets(),"fonts/SolaimanLipi_29-05-06.ttf");
        notFeture.setTypeface(face2featre);


        TextView notCurrent=(TextView)findViewById(R.id.current_title);
        Typeface face2cu = Typeface.createFromAsset(getAssets(),"fonts/SolaimanLipi_29-05-06.ttf");
        notCurrent.setTypeface(face2cu);

        TextView notQuiz=(TextView)findViewById(R.id.quiz_title);
        Typeface face2Quiz = Typeface.createFromAsset(getAssets(),"fonts/SolaimanLipi_29-05-06.ttf");
        notQuiz.setTypeface(face2Quiz);

        TextView notBises=(TextView)findViewById(R.id.bises);
        Typeface face2b = Typeface.createFromAsset(getAssets(),"fonts/SolaimanLipi_29-05-06.ttf");
        notBises.setTypeface(face2b);








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
