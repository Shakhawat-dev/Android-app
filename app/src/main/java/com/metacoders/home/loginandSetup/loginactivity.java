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

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.metacoders.home.Home_Activity;
import com.metacoders.home.R;
import com.shobhitpuri.custombuttons.GoogleSignInButton;

public class loginactivity extends AppCompatActivity {

    String  mail , pass  ;
    EditText mail_inPut , paas_input ;
    Button signiNBtn  , recoverPass , RegisterBtn ;
    FirebaseAuth mauth ;
    FirebaseUser muser ;
    ProgressBar progressBar ;
    private String verificationid;

    private final static  int RC_SIGN_IN =2 ;
    FirebaseAuth.AuthStateListener mAuthListener ;
    //    SignInButton google_btn ;
    GoogleSignInButton google_btn;
    GoogleApiClient mGoogleApiClient ;

    FirebaseUser mUser ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_page);
        mauth = FirebaseAuth.getInstance();

        try{
            getSupportActionBar().hide();
        }
        catch (NullPointerException e ){
            Toast.makeText(getApplicationContext() , "", Toast.LENGTH_SHORT)
                    .show();


        }



        mail_inPut = findViewById(R.id.mail_input) ;
        paas_input = findViewById(R.id.password_input);
        signiNBtn =  findViewById(R.id.signin_btn);
        //recoverPass = findViewById(R.id.SignUPBTN);
        RegisterBtn = findViewById(R.id.SignUPBTN);
        progressBar = findViewById(R.id.progressBarLoginPage);
        progressBar.setVisibility(View.INVISIBLE);
        google_btn = findViewById(R.id.google_btn);

        RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
                finish();


            }
        });





        signiNBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                mail = mail_inPut.getText().toString();
                pass = paas_input.getText().toString();

                if(!TextUtils.isEmpty(mail)&& !TextUtils.isEmpty(pass)){

                       signINUser();




                }
                else{
                    progressBar.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext() , "Please Enter Proper Value ", Toast.LENGTH_SHORT)
                            .show();

                }



            }
        });





        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("484233152873-52o6hu5i8uthvo9bti0d5qh0b04plf4m.apps.googleusercontent.com")
                .requestEmail()
                .build();



        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {


                        //error
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();





        google_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                signGoogle();

            }
        });





    }
    private  void firebaseAuthGoogle(GoogleSignInAccount account){

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken() , null);
        mauth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            Intent i = new Intent(getApplicationContext(), ProfileSetupPage.class);
                            //i.putExtra("GOOGLE" , "GOOGLE");
                            startActivity(i);
                            finish();
                        }

                        else{
                            Toast.makeText(getApplicationContext(), "Authentication Went Wrong WIth Google ",Toast.LENGTH_LONG).show();

                        }

                    }
                });



    }
    public void signGoogle(){
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent , RC_SIGN_IN);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {

                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthGoogle(account);
            } else {
                String e = result.getStatus().toString();
                Toast.makeText(getApplicationContext(), "Authentication WENT WRONG From Gmail" + e, Toast.LENGTH_LONG).show();


            }
        }
    }


    private void signINUser() {

        mauth.signInWithEmailAndPassword(mail , pass )
                .addOnCompleteListener(this , new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            Intent i = new Intent(getApplicationContext(), Home_Activity.class);
                            startActivity(i);
                            finish();



                        }
                        else {

                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(getApplicationContext() , "Error : UserName & PassWord Does Not Match " ,
                                    Toast.LENGTH_SHORT).show();

                        }



                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext() , "Error : "+ e.getMessage() ,
                        Toast.LENGTH_SHORT).show();


            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mauth = FirebaseAuth.getInstance();
        muser = mauth.getCurrentUser();
        if(muser!=null){

            Intent i = new Intent(getApplicationContext(), Home_Activity.class);
            startActivity(i);
            finish();



        }


    }
}
