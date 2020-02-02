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
import com.metacoders.home.sslGateWay.sslgateWayPage;

import java.util.Date;

public class profileActivity extends AppCompatActivity {

    Button payButton ;

    TextView amountTv  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_profile);

       amountTv = findViewById(R.id.point_bal_tv) ;
        payButton = findViewById(R.id.buttonPay);

        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext() , sslgateWayPage.class);


                startActivity(i);

            }
        });

        //TODO design the profile
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
        else{

            final ProgressDialog dialog = ProgressDialog.show(this, "",
                    "Loading...Please wait...", true);

            dialog.show();


            DatabaseReference    mref = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getUid()).child("coins");
            mref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                    if(dataSnapshot.exists())
                    {

                        amountTv.setText(dataSnapshot.getValue().toString() + " Coins");
                        dialog.dismiss();

                    }

                    else {
                        amountTv.setText("0 Coins");

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
