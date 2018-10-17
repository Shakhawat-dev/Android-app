package com.metacoders.home;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class Shop extends AppCompatActivity {
        WebView mwebview ;
        ProgressBar pbar ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop2);

        pbar = (ProgressBar)findViewById(R.id.progressBar_shop);
        mwebview = (WebView) findViewById(R.id.Shopview);

        pbar.setVisibility(View.VISIBLE);
        mwebview.setWebViewClient(new WebViewClient());
        mwebview.getSettings().setJavaScriptEnabled(true);mwebview.clearCache(true);
       mwebview.getSettings().setDomStorageEnabled(true);

        mwebview.loadUrl("https://shopup.com.bd/shop/942/products");

        Handler handler  = new Handler() ;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                pbar.setVisibility(View.GONE);

            }
        },10000);









    }

    @Override
    public void onBackPressed() {

        if (mwebview.canGoBack()) {
            mwebview.goBack();
        }
        else {

        super.onBackPressed();

    }
}
}
