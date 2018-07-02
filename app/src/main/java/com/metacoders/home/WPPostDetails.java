package com.metacoders.home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

public class WPPostDetails extends AppCompatActivity {
    private   ProgressBar spinner;

             TextView title ;
             WebView webview ;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this,new Crashlytics());
        setContentView(R.layout.viewing_article_in_web);


        webview = (WebView ) findViewById(R.id.postwebview) ;
        spinner = (ProgressBar) findViewById(R.id.progressBar1) ;
        spinner.setVisibility(View.VISIBLE);
        Intent o = getIntent() ;
        int position = getIntent().getExtras().getInt("itemPosition") ;


        Log.e("WpPostDetails", "title is "+ ArticleActivity.mListPost.get(position).getTitle().getRendered()) ;

                webview.getSettings().setJavaScriptEnabled(true);

                webview.loadUrl(ArticleActivity.mListPost.get(position).getLink());

                webview.setWebViewClient(new WebViewClient());
        Handler handler  = new Handler() ;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                spinner.setVisibility(View.GONE);

            }
        },5000);


    }



}

