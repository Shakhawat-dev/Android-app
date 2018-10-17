package com.metacoders.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

public class Wp_Quiz_details extends AppCompatActivity {


    private ProgressBar spinner;

    TextView title ;
    WebView webview ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this,new Crashlytics());
        setContentView(R.layout.viewing_quiz_in_web);
        webview = (WebView ) findViewById(R.id.Quizwebview) ;
        spinner = (ProgressBar) findViewById(R.id.progressBar_quiz) ;
        spinner.setVisibility(View.VISIBLE);
        Intent o = getIntent() ;
        final int position = getIntent().getExtras().getInt("itemPosition") ;

        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebChromeClient(new WebChromeClient());

        webview.loadUrl(QuizActivity.mListPost_quiz.get(position).getLink());
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

    }}

