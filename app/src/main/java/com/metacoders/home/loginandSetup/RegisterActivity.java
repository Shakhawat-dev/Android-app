package com.metacoders.home.loginandSetup;

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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.metacoders.home.Home_Activity;
import com.metacoders.home.R;

public class RegisterActivity extends AppCompatActivity {


    EditText mailIn , passIn ;
    Button registerBtn ;
    String mail , pass ;
    FirebaseUser muser ;
    FirebaseAuth mauth ;
    ProgressBar progressBar ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        mailIn = findViewById(R.id.mail_inputregister );
        passIn = (EditText)  findViewById(R.id.password_input_register);
        registerBtn = findViewById(R.id.register_btn);
        mauth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBarRegiterPage);
        progressBar.setVisibility(View.INVISIBLE);

        try{
            getSupportActionBar().hide();
        }
        catch (NullPointerException e ){
            Toast.makeText(getApplicationContext() , "", Toast.LENGTH_SHORT)
                    .show();


        }


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                mail = mailIn.getText().toString();
                pass = passIn.getText().toString();

                if (!TextUtils.isEmpty(mail) || !TextUtils.isEmpty(pass)){

                   creteUSer();

                }
                else  {
                    progressBar.setVisibility(View.INVISIBLE);

                    Toast.makeText(getApplicationContext() , "Error : Enter The Feild CareFully ", Toast.LENGTH_SHORT)
                            .show();
                }



            }
        });



    }

    private void creteUSer() {
        mauth.createUserWithEmailAndPassword(mail ,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){


                            Intent io = new Intent(getApplicationContext() , Home_Activity.class);
                            startActivity(io);

                        }
                        else {

                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(getApplicationContext() , "Error :", Toast.LENGTH_SHORT).show();

                        }


                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext() , "Error : "+e.getMessage() , Toast.LENGTH_SHORT).show();

            }
        });


    }
}
