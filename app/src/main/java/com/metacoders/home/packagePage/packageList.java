package com.metacoders.home.packagePage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.metacoders.home.Home_Activity;
import com.metacoders.home.R;
import com.metacoders.home.sslGateWay.sslgateWayPage;

import java.text.SimpleDateFormat;
import java.util.Date;

public class packageList extends AppCompatActivity {

    CardView weeklyCard, monthlyCard;
    Button week , mon;
    TextView weeklyTitle, monhtlyTitle;

//TODO REDESIGN  THIS PAGE  SMS VAi

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_list);

        weeklyCard = (CardView) findViewById(R.id.weekly_card);
        monthlyCard = (CardView) findViewById(R.id.monthly_card);
        weeklyTitle = (TextView) findViewById(R.id.weekly_title_text);
        monhtlyTitle = (TextView) findViewById(R.id.monthly_title_text);
        week = findViewById(R.id.weeklyBtn);
        mon = findViewById(R.id.monthlyBtn );

        weeklyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext() , sslgateWayPage.class);
                i.putExtra("TIME" , "7") ;
                i.putExtra("VALUE", "20") ;
                startActivity(i);
            }
        });

        monthlyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext() , sslgateWayPage.class);
                i.putExtra("TIME" , "30") ;
                i.putExtra("VALUE", "100") ;
                startActivity(i);
            }
        });

//        week.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

//        mon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

    }

}
