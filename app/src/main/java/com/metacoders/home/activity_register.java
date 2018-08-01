package com.metacoders.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class activity_register extends AppCompatActivity {
    private EditText mDisplayName , mPassword , mPhoneNum , mEmail ;
    private Button mBtn ;
    private FirebaseAuth mAuth ;
    private DatabaseReference mDatabase ;
    private ProgressDialog mProgressdialoge ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        mDisplayName = (EditText) findViewById(R.id.reg_display_name);
        mPassword = (EditText) findViewById(R.id.Reg_PassWord);
        mEmail = (EditText) findViewById(R.id.Reg_Email) ;
        mPhoneNum = (EditText) findViewById(R.id.reg_phone) ;
        mBtn = (Button)findViewById(R.id.button_reg) ;
       mProgressdialoge  = new ProgressDialog(this );
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mDisplayName.getText().toString();
                String pass = mPassword.getText().toString();
                String phone = mPhoneNum.getText().toString() ;
                String email = mEmail.getText().toString() ;


                if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(pass) && !TextUtils.isEmpty(phone)&&!TextUtils.isEmpty(email)){

                   // register(name , email , phone , pass ) ;
                    mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){

                                sendtoMain();
                            }
                            else {
                                String errorMessage = task.getException().getMessage();
                                Toast.makeText(activity_register.this, "error"+errorMessage , Toast.LENGTH_LONG).show();
                            }

                        }
                    })  ;

                }
            }

        });



    }

    public  void sendtoMain (){

        Intent main = new Intent(activity_register.this , Home_Activity.class);
        startActivity(main);


    }
}
