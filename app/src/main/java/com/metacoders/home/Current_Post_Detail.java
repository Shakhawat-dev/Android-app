package com.metacoders.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeErrorDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.metacoders.home.loginandSetup.loginactivity;
import com.metacoders.home.model.modelForBookMark;

public class Current_Post_Detail extends AppCompatActivity {
    TextView mTitleTv, mDetailTv;
    AdView mAdView ;
    DatabaseReference  mref  ;
    FirebaseAuth mauth ;
    String  uid ;
    Boolean ispressed = false ;
    AlertDialog alertDialog ;
    String image = "null" , title , desc ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        setContentView(R.layout.activity_current__post__detail);
        mauth = FirebaseAuth.getInstance();




        //advirtisement
        mAdView = findViewById(R.id.adView_current);

        AdRequest adRequest = new AdRequest.Builder().build();

        mAdView.loadAd(adRequest);




        //Action bar
        ActionBar actionBar = getSupportActionBar();
        //Actionbar title
        actionBar.setTitle("Post Detail");
        //set back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        //initialize views
        mTitleTv = findViewById(R.id.detail_tile_current);
        mDetailTv = findViewById(R.id.detail_description_current);
        //get data from intent
         title = getIntent().getStringExtra("title");
         desc = getIntent().getStringExtra("description");
        //set data to views
        mTitleTv.setText(title);
        mDetailTv.setText(desc);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.bookmarkmenu, menu);
        MenuItem item = menu.findItem(R.id.bookmark_btn);
        MenuItem item1 = menu.findItem(R.id.font_btn) ;

        item1.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {


                resizeTheFont() ;


                return false;
            }
        }) ;

        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if ( isUserSignedIn())
                {
                    if (ispressed){
                        Toast.makeText(getApplicationContext() , "You All Ready Added This", Toast.LENGTH_SHORT).show();
                    }
                    else  {
                        uploadPostToServer();

                    }

                }


                else {

                    triggerWarningDialouge();
                }


                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }
    private void resizeTheFont() {


        CharSequence[] textSize = {"Normal","Large","Extra Large"};
        // Toast.makeText(getApplicationContext() , "CLOCKED" , Toast.LENGTH_SHORT).show();


        AlertDialog.Builder builder = new AlertDialog.Builder(Current_Post_Detail.this,R.style.DialogTheme);
        builder.setTitle("Select Text Size");
        builder.setSingleChoiceItems(textSize, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                switch (item){
                    case 0:
                        mDetailTv.setTextSize(TypedValue.COMPLEX_UNIT_SP,17);
                        break;
                    case 1:
                        //  dView.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
                        mDetailTv.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
                        break;
                    case 2:
                        mDetailTv.setTextSize(TypedValue.COMPLEX_UNIT_SP,23);
                        break;
                }
                alertDialog.dismiss();
            }
        });
        alertDialog=builder.create();
        alertDialog.show();

    }

    private void uploadPostToServer() {
        uid = mauth.getUid();
        mref = FirebaseDatabase.getInstance().getReference("Users").child(uid).child("bookmarks");

        String ts = mref.push().getKey();

        modelForBookMark model = new modelForBookMark(title , image , desc, ts);
        mref.child(ts).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                ispressed= true ;


                Toast.makeText(getApplicationContext() , "Added To The Bookmark ", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                ispressed= false;

                Toast.makeText(getApplicationContext() , "Network Error!! Could Not Save The Data  ", Toast.LENGTH_SHORT).show();
            }
        });


    }


    public    boolean  isUserSignedIn()
    {
        FirebaseAuth mauth  = FirebaseAuth.getInstance();
        FirebaseUser user = mauth.getCurrentUser() ;

        return user != null;



    }
    public  void triggerWarningDialouge()
    {
        new AwesomeErrorDialog(Current_Post_Detail.this)
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
}
