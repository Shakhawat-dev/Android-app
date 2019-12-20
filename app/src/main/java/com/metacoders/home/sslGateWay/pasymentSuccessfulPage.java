package com.metacoders.home.sslGateWay;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.metacoders.home.R;
import com.metacoders.home.profileActivity;

public class pasymentSuccessfulPage extends AppCompatActivity {

    String CoinValue ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasyment_successful_page);



        final  TextView textView = findViewById(R.id.coinView);
        final  Button okBtn = findViewById( R.id.okBtn);

        Intent i = getIntent();
        CoinValue = i.getStringExtra("coins") ;

        textView.setText(CoinValue + " Coins");


        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent o = new Intent(getApplicationContext()  , profileActivity.class);
                startActivity(o);
                finish();



            }
        });

    }


}
