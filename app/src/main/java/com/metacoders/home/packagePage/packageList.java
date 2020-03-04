package com.metacoders.home.packagePage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.metacoders.home.Home_Activity;
import com.metacoders.home.R;
import com.metacoders.home.sslGateWay.sslgateWayPage;

import java.text.SimpleDateFormat;
import java.util.Date;

public class packageList extends AppCompatActivity {

    CardView weeklyCard, monthlyCard;
    Button week , mon;
    String Mon_moneyValue  , Week_monValue  ;

    TextView weeklyTitle , monhtlyTitle , monview , weekview;

//TODO REDESIGN  THIS PAGE  SMS VAi

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_list);

        getSupportActionBar().hide();

        weeklyCard = (CardView) findViewById(R.id.weekly_card);
        monthlyCard = (CardView) findViewById(R.id.monthly_card);
        weeklyTitle = (TextView) findViewById(R.id.weekly_title_text);
        monhtlyTitle = (TextView) findViewById(R.id.monthly_title_text);
        week = findViewById(R.id.weeklyBtn);
        mon = findViewById(R.id.monthlyBtn );
        monview = findViewById(R.id.monthlyBdtView) ;
        weekview = findViewById(R.id.weekBdtView);

        weeklyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (Mon_moneyValue != null)
                {
                    Intent i = new Intent(getApplicationContext() , sslgateWayPage.class);
                    i.putExtra("TIME" , "7") ;
                    i.putExtra("VALUE", Mon_moneyValue) ;
                    startActivity(i);

                }
                else {
                    Toast.makeText(getApplicationContext()  , "Please Contact Admin !!" , Toast.LENGTH_LONG).show();

                }


            }
        });

        monthlyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(Week_monValue != null)
                {
                    Intent i = new Intent(getApplicationContext() , sslgateWayPage.class);
                    i.putExtra("TIME" , "30") ;
                    i.putExtra("VALUE", Week_monValue) ;
                    startActivity(i);

                }
                else {
                    Toast.makeText(getApplicationContext()  , "Please Contact Admin !! " , Toast.LENGTH_LONG).show();
                }





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

        loadDb();
    }

    public  void loadDb()
    {
        final DatabaseReference mref = FirebaseDatabase.getInstance().getReference("paid_packages").child("weekly");
        DatabaseReference mreff = FirebaseDatabase.getInstance().getReference("paid_packages").child("monthly");

        mref.child("point").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                weekview.setText(dataSnapshot.getValue().toString()+ " Taka");
                Mon_moneyValue = dataSnapshot.getValue().toString() ;
                week.setText(dataSnapshot.getValue().toString() + " Points");


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        mreff.child("point").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                mon.setText(dataSnapshot.getValue().toString() + " Points");
                Week_monValue = dataSnapshot.getValue().toString() ;
                monview.setText(dataSnapshot.getValue().toString() + " Taka");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }) ;


    }

}
