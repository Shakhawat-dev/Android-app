package com.metacoders.home.bookMarkController;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.metacoders.home.R;
import com.squareup.picasso.Picasso;

public class BookMarkDetails extends AppCompatActivity {
    TextView mTitleTv, mDetailTv;
    ImageView mImageIv;

    Bitmap bitmap;

    Button mDElBtn, mShareBtn, mWallBtn;
    AdView mAdView , downAdview  , DownAdview_down ;

    DrawerLayout drawerLayout ;
    ActionBarDrawerToggle toggle ;
    NavigationView navigationView ;
    DatabaseReference mref ;
    FirebaseAuth mauth ;
    String  uid ;
    Boolean ispressed = false ;

    String image , title , desc , postUID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_mark_details);
        mauth = FirebaseAuth.getInstance();
        try{
            uid = mauth.getUid();

        }
        catch (NullPointerException e ){


        }


        mref = FirebaseDatabase.getInstance().getReference("Users").child(uid).child("bookmarks");

        mAdView = findViewById(R.id.adView_BOOOKMARKDETAILS1);
        downAdview = findViewById(R.id.adView_BOOOKMARKDETAILS2);
        DownAdview_down = findViewById(R.id.adView_BOOOKMARKDETAILS3);
        AdRequest adRequest = new AdRequest.Builder().build();
        DownAdview_down.loadAd(adRequest);
        mAdView.loadAd(adRequest);
        downAdview.loadAd(adRequest);
        //initialize views
        mTitleTv = findViewById(R.id.titleTvBOOOKMARKDETAILS);
        mDetailTv = findViewById(R.id.descriptionTvBOOOKMARKDETAILS);
        mImageIv = findViewById(R.id.imageViewBOOOKMARKDETAILS);
        mDElBtn = findViewById(R.id.DelBtnBOOOKMARKDETAILS);

        //get data from intent
        image = getIntent().getStringExtra("image");
        title = getIntent().getStringExtra("title");
        desc = getIntent().getStringExtra("description");
        postUID = getIntent().getStringExtra("POSTID");

        //set data to views
        mTitleTv.setText(title);
        mDetailTv.setText(desc);
        Picasso.get().load(image).into(mImageIv);

        mDElBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mref.child(postUID).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        finish();


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext() , "Error Deleting The Post From Your BookMark !", Toast.LENGTH_SHORT).show();
                    }
                });




            }
        });




    }
}
