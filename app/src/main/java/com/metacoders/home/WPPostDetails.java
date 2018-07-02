package com.metacoders.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

public class WPPostDetails extends AppCompatActivity {

             TextView title ;
             WebView webview ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this,new Crashlytics());
        setContentView(R.layout.viewing_article_in_web);

        webview = (WebView ) findViewById(R.id.postwebview) ;
        Intent o = getIntent() ;
        int position = getIntent().getExtras().getInt("itemPosition") ;


        Log.e("WpPostDetails", "title is "+ ArticleActivity.mListPost.get(position).getTitle().getRendered()) ;

                webview.getSettings().setJavaScriptEnabled(true);
                webview.loadUrl(ArticleActivity.mListPost.get(position).getLink());
                webview.setWebViewClient(new WebViewClient());


    }
}
