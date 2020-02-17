package com.metacoders.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeErrorDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.metacoders.home.loginandSetup.loginactivity;
import com.metacoders.home.loginandSetup.modelForUser;
import com.metacoders.home.model.modelForPayment;
import com.metacoders.home.model.modelForProfile;
import com.metacoders.home.packagePage.packageList;
import com.metacoders.home.sslGateWay.sslgateWayPage;
import com.metacoders.home.utils.utilities;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class profileActivity extends AppCompatActivity {

    Button payButton ;
    utilities  utilities   ;
    TextView amountTv , nameTv  , phTv  , mailTv ,expireTv , pacakgeTv  , nametv ;
    String TODAY ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_profile);


        expireTv = findViewById(R.id.expires_tv) ;
        amountTv = findViewById(R.id.point_bal_tv) ;
        payButton = findViewById(R.id.buttonPay);
        nameTv = findViewById(R.id.nameTv) ;
        phTv = findViewById(R.id.phone_tv) ;
        nametv = findViewById(R.id.name_bal_tv) ;
        pacakgeTv = findViewById(R.id.package_info_tv) ;
        mailTv = findViewById(R.id.email_tv ) ;
           TODAY = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        utilities = new utilities()  ;

        try{
            FirebaseAuth mauth  = FirebaseAuth.getInstance();
            FirebaseUser user = mauth.getCurrentUser() ;
            mailTv.setText(user.getEmail());
        }
        catch (Exception er)
        {

            mailTv.setText("N/A");

        }


        loadProfileData() ;






        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext() , packageList.class);


                startActivity(i);

            }
        });

        //TODO design the profile
    }

    private void loadProfileData() {

        try{
            DatabaseReference    profile = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getUid());

            profile.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                    modelForUser model = dataSnapshot.getValue(modelForUser.class) ;

                    nameTv.setText(model.getUsername());
                    nametv.setText(model.getUsername());
                    phTv.setText(model.getPh());



                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        catch (Exception e )
        {
            nameTv.setText("Error Fetching Name");
            nametv.setText("Error Fetching Name");
            phTv.setText("Error Fetching Ph ");


        }
    }

    public  void triggerWarningDialouge()
    {
        new AwesomeErrorDialog(profileActivity.this)
                .setTitle("Error!!!")
                .setMessage("You Are Not Allowed To Do This Action.Please Login first . ")
                .setColoredCircle(R.color.dialogErrorBackgroundColor)
                .setDialogIconAndColor(R.drawable.ic_dialog_error, R.color.white)
                .setCancelable(true)
                .setButtonText(getString(R.string.dialog_ok_button))
                .setButtonBackgroundColor(R.color.dialogErrorBackgroundColor)
                .setButtonText("Proceed To Login")
                .setErrorButtonClick(new Closure() {
                    @Override
                    public void exec() {
                        // click

                        Intent io = new Intent(getApplicationContext(), loginactivity.class);
                        startActivity(io);



                    }
                })
                .show();

    }
    public    boolean  isUserSignedIn()
    {
        FirebaseAuth mauth  = FirebaseAuth.getInstance();
        FirebaseUser user = mauth.getCurrentUser() ;




        return user != null;



    }


    @Override
    protected void onStart() {
        super.onStart();

        if(!isUserSignedIn())
        {
            triggerWarningDialouge();

        }
    else {


            final ProgressDialog dialog = ProgressDialog.show(this, "",
                    "Loading...Please wait...", true);

            dialog.show();



            final DatabaseReference    mref = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getUid()).child("transaction");
            mref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                    if(dataSnapshot.exists())
                    {
                       // amountTv.setText(dataSnapshot.getValue().toString() + " Coins");

                        modelForPayment model  = dataSnapshot.getValue(modelForPayment.class) ;



                        String date = model.getPurchaged_date()  ;

                        amountTv.setText(model.getCoins().toString() + " Coins");

                        int  day = utilities.calculateDayCount(date , TODAY) ;

                        int limit = Integer.valueOf(model.getDuration())  ;

                        day =  limit - day ;

                        expireTv.setText( "in " + day + " Days") ;

                        if(limit <= 7)
                        {
                            pacakgeTv.setText("Weekly");
                        }
                        else if (limit <= 30 ) {
                            pacakgeTv.setText("Monthly");
                        }
                        else {
                            pacakgeTv.setText("Combined");
                        }

                        dialog.dismiss();
                    }

                    else {
                        amountTv.setText("0 Coins");
                        expireTv.setText("0 Days");
                        pacakgeTv.setText("N/A");

                        dialog.dismiss();

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });



        }







    }
}
