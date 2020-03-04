package com.metacoders.home.loginandSetup;

import android.content.Intent;
import androidx.annotation.NonNull;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.metacoders.home.R;
import com.metacoders.home.model.modelForPayment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RegisterActivity extends AppCompatActivity {


    EditText mailIn , passIn ;
    Button registerBtn ;
    String mail , pass ;
    FirebaseUser muser ;
    FirebaseAuth mauth ;
    ProgressBar progressBar ;
    DatabaseReference mref ;




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


                            giveHimCoin();

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

    public  void giveHimCoin()
    {
        mref = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getUid());
        String   DATE = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        modelForPayment modelForPayment  = new modelForPayment("3" , DATE,"5") ;



        mref.child("transaction").setValue(modelForPayment).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {


                Intent io = new Intent(getApplicationContext() , ProfileSetupPage.class);
                startActivity(io);
                finish();


            }
        }) ;
    }
}
