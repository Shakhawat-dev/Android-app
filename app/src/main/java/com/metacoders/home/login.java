package com.metacoders.home;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {
 private EditText sign_email , sign_pass ;
 private Button btn_sign , btn_register ;
 private ProgressBar bar  ;


 private FirebaseAuth mAuth ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance() ;

    sign_email = (EditText)findViewById(R.id.sign_email) ;
    sign_pass = (EditText)findViewById(R.id.sign_password) ;
    btn_register = (Button)findViewById(R.id.sign_resgister_button) ;
    btn_sign = (Button)findViewById(R.id.sign_in_btn) ;
    bar = (ProgressBar)findViewById(R.id.progressBar_login) ;


    btn_register.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(login.this , activity_register.class);
            startActivity(intent);
            finish();
        }
    });



    btn_sign.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        String email = sign_email.getText().toString() ;
            String pass = sign_pass.getText().toString();
            if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass)){

                bar.setVisibility(View.VISIBLE);
                mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            Intent mainintent = new Intent(login.this , Home_Activity.class);
                            startActivity(mainintent);
                            finish();

                        }
                        else {String e = task.getException().getMessage() ;
                            Toast.makeText(login.this , "Error :"+e , Toast.LENGTH_LONG ).show();

                        }
                        bar.setVisibility(View.INVISIBLE);

                    }
                });

            }
            else {
                Toast.makeText(login.this , "Eror PLs fill the data " , Toast.LENGTH_SHORT).show();

            }

        }
    });


    }
@Override
protected  void onStart() {


    super.onStart();
    // checking already logged in or not
 //   FirebaseUser currentUser =mAuth.getCurrentUser();
 //   if(currentUser!= null){
//sendtomain();

//    }
}


private  void sendtomain(){
    Intent mainintent = new Intent(login.this , Home_Activity.class);
    startActivity(mainintent);
    finish();
}

}
