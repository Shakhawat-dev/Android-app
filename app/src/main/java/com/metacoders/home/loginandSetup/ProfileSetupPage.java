package com.metacoders.home.loginandSetup;

import android.app.ProgressDialog;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.metacoders.home.Home_Activity;
import com.metacoders.home.R;

import java.util.HashMap;

public class ProfileSetupPage extends AppCompatActivity {

    EditText nameInput , phoneINput ;
    String name , phone  ,uid ;
    FirebaseAuth mauth ;
    DatabaseReference mref ;
    Button nesBtn ;
    ProgressBar progressBar ;
    ProgressDialog dialog ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setup_page);
        dialog = new ProgressDialog(ProfileSetupPage.this) ;
        dialog.setTitle("Checking For Your Profile");
        dialog.setMessage("Please Wait......");
        mauth = FirebaseAuth.getInstance();
        uid = mauth.getUid();

        mref = FirebaseDatabase.getInstance().getReference("Users").child(uid);


        nameInput = (EditText)  findViewById(R.id.Name_input);
        phoneINput = (EditText)  findViewById(R.id.phnNumber_input);
        nesBtn = (Button) findViewById(R.id.Next_btn);
        progressBar = findViewById(R.id.progressBarACCOutnsetup);
        progressBar.setVisibility(View.INVISIBLE);
        nesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                name = nameInput.getText().toString();
                phone = phoneINput.getText().toString();
                int len = phone.length();


                if(len==11 && !TextUtils.isEmpty(name)){

                    HashMap addmap = new HashMap();
                    addmap.put("username", name);
                    addmap.put("ph", phone);

                    mref.setValue(addmap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            Intent i = new Intent(getApplicationContext() , Home_Activity.class);
                            startActivity(i);
                            finish();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(getApplicationContext() , "Error : Please Try Again Later " , Toast.LENGTH_SHORT).show();
                        }
                    });




                }
                else
                {
progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext() , "Error : Please Fill Up the Data Properly" , Toast.LENGTH_SHORT).show();
                }







            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        dialog.show();

        mref = FirebaseDatabase.getInstance().getReference("Users").child(uid);



        // first check the user  has  older installation
        mref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists())
                {
                    // skipp this
                    dialog.dismiss();

                    Intent oi = new Intent(ProfileSetupPage.this ,Home_Activity.class );
                    startActivity(oi);
                    finish();
                }
                else {

                    dialog.dismiss() ;

                   // Toast.makeText(getApplicationContext() ,  "New  User " , Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(getApplicationContext() ,  " Something went wrong !!" , Toast.LENGTH_LONG).show();
            }
        });





    }
}
