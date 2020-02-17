package com.metacoders.home.packagePage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.metacoders.home.Home_Activity;
import com.metacoders.home.R;
import com.metacoders.home.sslGateWay.sslgateWayPage;

import java.text.SimpleDateFormat;
import java.util.Date;

public class packageList extends AppCompatActivity {

    Button week , mon ;

//TODO REDESIGN  THIS PAGE  SMS VAi


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_list);

        week = findViewById(R.id.weeklyBtn) ;
        mon = findViewById(R.id.monthlyBtn );

        week.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext() , sslgateWayPage.class);
                i.putExtra("TIME" , "7") ;
                i.putExtra("VALUE", "20") ;
                startActivity(i);




            }
        });

        mon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(getApplicationContext() , sslgateWayPage.class);
                i.putExtra("TIME" , "30") ;
                i.putExtra("VALUE", "100") ;
                startActivity(i);


            }
        });





    }

}
