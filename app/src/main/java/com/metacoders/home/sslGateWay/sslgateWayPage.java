package com.metacoders.home.sslGateWay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.metacoders.home.R;
import com.metacoders.home.model.modelForPayment;
import com.metacoders.home.utils.utilities;
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCommerzInitialization;
import com.sslwireless.sslcommerzlibrary.model.response.TransactionInfo;
import com.sslwireless.sslcommerzlibrary.model.response.TransactionInfoModel;
import com.sslwireless.sslcommerzlibrary.model.util.CurrencyType;
import com.sslwireless.sslcommerzlibrary.model.util.SdkType;
import com.sslwireless.sslcommerzlibrary.view.singleton.IntegrateSSLCommerz;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.TransactionResponseListener;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class sslgateWayPage extends AppCompatActivity implements TransactionResponseListener {

    String TAG = "PAY";
    TextView tv;
    EditText et;
     double oldCoin  , newCoin  ;
     Boolean isCoinAvailable ;
    String  oldCOIN , time , point   ;
     DatabaseReference mref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sslgate_way_page);

        //  receive the data we sent
                Intent o  = getIntent();
                point =   o.getStringExtra("VALUE" ) ;
                time =  o.getStringExtra("TIME") ;





        mref = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getUid());
        tv = (TextView) findViewById(R.id.textView);

        et = (EditText) findViewById(R.id.editText);


        et.setText(point);
        et.setFocusable(false);

       findViewById(R.id.GoBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();


            }
        });


        ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    float amount = Float.parseFloat(et.getText().toString());
                    if (amount > 0.0) {
                       doPay(amount);
                       // uploadItToTheServer("10");
                       // sentToTheTransPage(String.valueOf(amount));
                    } else {
                        Toast.makeText(sslgateWayPage.this, "Enter Amount!", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }


    private void doPay(Float f) {
        int  rand  = new Random().nextInt() ;


        final SSLCommerzInitialization sslCommerzInitialization = new SSLCommerzInitialization("careercoachbdlive" , "5D301FC83F92990081"
        , f , CurrencyType.BDT , "Trans ID : Test" + rand, "food", SdkType.LIVE) ;


        IntegrateSSLCommerz.getInstance(sslgateWayPage.this)
                .addSSLCommerzInitialization(sslCommerzInitialization)
                .buildApiCall(sslgateWayPage.this);
    }

    @Override
    public void transactionSuccess(TransactionInfoModel transactionInfoModel) {

        // If payment is success and risk label is 0.
        if (transactionInfoModel.getRiskLevel().equals("0")) {



            Log.d(TAG, "Transaction Successfully completed");
            Log.d(TAG, transactionInfoModel.getTranId());
            Log.d(TAG, transactionInfoModel.getBankTranId());
            Log.d(TAG, transactionInfoModel.getAmount());
            Log.d(TAG, transactionInfoModel.getStoreAmount());
            Log.d(TAG, transactionInfoModel.getTranDate());
            Log.d(TAG, transactionInfoModel.getStatus());
        //
            //    tv.setText("Transaction Successfully completed");
            et.setText(null);


            uploadItToTheServer(transactionInfoModel.getAmount() ) ;

        }
        // Payment is success but payment is not complete yet. Card on hold now.
        else {
            Log.d(TAG, "Transaction in risk. Risk Title : " + transactionInfoModel.getRiskTitle().toString());
            tv.setText("Transaction in risk.");
            et.setText(null);
        }
    }

    private void uploadItToTheServer(final String amount) {


                if(isCoinAvailable)
                {

                    downLoadOldCoin(amount) ;


                }

                else {


                    String   DATE = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                    modelForPayment modelForPayment  = new modelForPayment(time , DATE,amount) ;


                    mref.child("transaction").setValue(modelForPayment).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {



                            sentToTheTransPage(amount) ;
                        }
                    }) ;

                }




    }

    private void downLoadOldCoin(final String amountt) {



        // download the coin data and add
      //  DatabaseReference mf = FirebaseDatabase.getInstance().getReference("Users").child("test").child("coins");
        mref.child("transaction").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              //   oldCoin = Double.parseDouble(dataSnapshot.getValue().toString()) ;
                modelForPayment modelFoPayment = dataSnapshot.getValue(modelForPayment.class) ;

                 oldCOIN = modelFoPayment.getCoins();
            //    Toast.makeText(getApplicationContext() , "oldCoin : " +  , Toast.LENGTH_SHORT).show() ;

                int newLimit  = Integer.parseInt(modelFoPayment.getDuration()) ;
                 newLimit = Integer.parseInt(time)+ newLimit ;

                newCoin = Double.valueOf(amountt);
                newCoin = Double.valueOf(oldCOIN) + newCoin ;

          //      Toast.makeText(getApplicationContext() , "oldCoin : " + oldCOIN  +  " newCoin  : " + newCoin , Toast.LENGTH_SHORT).show() ;
             //   String   DATE = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

                modelForPayment modelForPayment  = new modelForPayment(newLimit+"" , modelFoPayment.getPurchaged_date(),newCoin+"") ;
                mref.child("transaction").setValue(modelForPayment).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        sentToTheTransPage(amountt) ;

                    }
                }) ;



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });






    }

    private void sentToTheTransPage(String amount) {
        Intent nextPage = new Intent(getApplicationContext() , pasymentSuccessfulPage.class);
        nextPage.putExtra("coins", amount);
        startActivity(nextPage);
        finish();

    }

    @Override
    public void transactionFail(String s) {
        Log.e(TAG, "Transaction Fail");
        tv.setText("Transaction Fail " + s );
        et.setText(null);
    }

    @Override
    public void merchantValidationError(String s) {
        Toast .makeText( getApplicationContext() , "Error Msg : " + s , Toast.LENGTH_SHORT)
                .show();

    }

    @Override
    protected void onStart() {
        super.onStart();


        final ProgressDialog dialog = ProgressDialog.show(this, "",
                "Loading...Please wait...", true);

        dialog.show();



mref.child("transaction").child("coins").addListenerForSingleValueEvent(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


        if(dataSnapshot.exists())
        {
            isCoinAvailable = true  ;

            dialog.dismiss();

        }

        else {
            isCoinAvailable = false ;

            dialog.dismiss();

        }

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
});





    }
}
