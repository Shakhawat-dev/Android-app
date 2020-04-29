package com.metacoders.home;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;
import com.metacoders.home.bookMarkController.bookmarkActivity;

import io.fabric.sdk.android.Fabric;

public class WPPostDetails extends AppCompatActivity {
    private   ProgressBar spinner;

             TextView title ;
             WebView webview ;
    DrawerLayout drawerLayout ;
    ActionBarDrawerToggle toggle ;
    NavigationView navigationView ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this,new Crashlytics());
        setContentView(R.layout.viewing_article_in_web);




        drawerLayout = findViewById(R.id.drawerId_article);
        navigationView=findViewById(R.id.NAVVIew_ID_article);

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
                        Intent jobprep = new Intent(getApplicationContext() , CareerPrepActivity.class);
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
                    case R.id.bookmark_menu:
                        i = new Intent(getApplicationContext(), bookmarkActivity.class);
                        startActivity(i);
                        break;

                    case R.id.profile_menu:
                        i = new Intent(getApplicationContext(), profileActivity.class);
                        startActivity(i);
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



        webview = (WebView ) findViewById(R.id.postwebview) ;
        spinner = (ProgressBar) findViewById(R.id.progressBar1) ;
        spinner.setVisibility(View.VISIBLE);
        Intent o = getIntent() ;
        final int position = getIntent().getExtras().getInt("itemPosition") ;


        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebChromeClient(new WebChromeClient());

        webview.loadUrl(ArticleActivity.mListPost.get(position).getLink());
        webview.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
        webview.setWebViewClient(new WebViewClient(){


            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                String javaScript ="javascript:(function() { var a= document.getElementsByTagName('header');a[0].hidden='true';a=document.getElementsByClassName('page_body');a[0].style.padding='0px';})()";
                webview.loadUrl(javaScript);
            }

        });
        webview.loadUrl(getIntent().getStringExtra("url"));

    }
    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        if(toggle.onOptionsItemSelected(item)){
            return  true ;
        }
        return super.onOptionsItemSelected(item);

    }



     //   Log.e("WpPostDetails", "title is "+ ArticleActivity.mListPost.get(position).getTitle().getRendered()) ;

         /*       webview.getSettings().setJavaScriptEnabled(true);


                webview.loadUrl(ArticleActivity.mListPost.get(position).getLink());

                webview.setWebViewClient(new WebViewClient());
        Handler handler  = new Handler() ;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                spinner.setVisibility(View.GONE);

            }
        },5000);
webview.setOnLongClickListener(new View.OnLongClickListener() {
    @Override
    public boolean onLongClick(View v) {
        return true;
    }
});



    }


*/

}

