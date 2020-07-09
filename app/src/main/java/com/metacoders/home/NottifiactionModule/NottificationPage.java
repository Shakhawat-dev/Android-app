package com.metacoders.home.NottifiactionModule;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.metacoders.home.ArticleActivity;
import com.metacoders.home.Bank_Prep;
import com.metacoders.home.BcsButtonActivity;
import com.metacoders.home.Bises;
import com.metacoders.home.Current_Activity;
import com.metacoders.home.Editorial;
import com.metacoders.home.Feature_Activity;
import com.metacoders.home.Govt_job_prep;
import com.metacoders.home.PostsListActivity;
import com.metacoders.home.QuizActivity;
import com.metacoders.home.Qustion_Bank;
import com.metacoders.home.R;
import com.metacoders.home.Shikok_nivondon;
import com.metacoders.home.Voca_activity;
import com.metacoders.home.career_prep_Bangla;
import com.metacoders.home.career_prep_Bangladesh;
import com.metacoders.home.career_prep_Eng;
import com.metacoders.home.career_prep_GK;
import com.metacoders.home.career_prep_International;
import com.metacoders.home.career_prep_Math;
import com.metacoders.home.career_prep_Others;
import com.metacoders.home.career_prep_ict;
import com.metacoders.home.model.modelForNottifaction;

public class NottificationPage extends AppCompatActivity {

    LinearLayoutManager mLayoutManager; //for sorting
    SharedPreferences mSharedPref; //for saving sort settings
    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;
    private ProgressBar job_progressBar ;

    NavigationView navigationView ;
    DrawerLayout drawerLayout ;
    ActionBarDrawerToggle toggle ;
    AdView mAdView ;
    Class cls ;

    Intent  intent ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nottification_page);



        //loading Ad
        mAdView = findViewById(R.id.adView_NOTTIFICATION);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        // setting layout
        mLayoutManager = new LinearLayoutManager(this);
        //this will load the items from bottom means newest first
        mLayoutManager.setReverseLayout(true);
        //TODO Make Sure IT Works
        mLayoutManager.setStackFromEnd(true); // to reverse the layout to shpw the latest


        //RecyclerView
        mRecyclerView = findViewById(R.id.recyclerViewNOTTIFICATION);
        mRecyclerView.setHasFixedSize(true);

        //set layout as LinearLayout
        mRecyclerView.setLayoutManager(mLayoutManager);


        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = FirebaseDatabase.getInstance().getReference("notfication");
        mRef.keepSynced(true);

// DOwnloading Data From FIrebd
        FirebaseRecyclerAdapter<modelForNottifaction, viewholderForNottification> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<modelForNottifaction, viewholderForNottification>(
                        modelForNottifaction.class,
                        R.layout.row_for_notification,
                        viewholderForNottification.class,
                        mRef
                ) {


                    @Override
                    protected void populateViewHolder(viewholderForNottification viewHolder, modelForNottifaction model, int position) {


                        viewHolder.setDetails(getApplicationContext(), model.getTitle(), model.getActivity());


                        Handler handler  = new Handler() ;
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                              //  job_progressBar.setVisibility(View.GONE);

                            }
                        },3000);

                    }

                    @Override
                    public viewholderForNottification  onCreateViewHolder(ViewGroup parent, int viewType) {

                        viewholderForNottification viewHolder = super.onCreateViewHolder(parent, viewType);

                        viewHolder.setOnClickListener(new viewholderForNottification.ClickListener() {
                            @Override
                            public void onItemClick(View view,final int position) {

                                //Views
                                //   TextView mTitleTv = view.findViewById(R.id.rTitleTv);
                                //    TextView mDescTv = view.findViewById(R.id.rDescriptionTv);
                                //     ImageView mImageView = view.findViewById(R.id.rImageView);



                                //get data from views
                               // String mTitle = getItem(position).getTitle() ;                         //mTitleTv.getText().toString();
                                String macativity = getItem(position).getActivity();                                  //mDescTv.getText().toString();
                               // String   mImage =  getItem(position).getImage();
                                Intent i ;


                                if(macativity.contains("PostsListActivity")){


                                    i = new Intent(getApplicationContext()  , PostsListActivity.class);
                                    startActivity(i);

                                }
                                else if(macativity.contains("BcsButtonActivity")){

                                    i = new Intent(getApplicationContext()  , BcsButtonActivity.class);
                                    startActivity(i);

                                }
                                else if(macativity.contains("Current_Activity")){

                                    i = new Intent(getApplicationContext()  , Current_Activity.class);
                                    startActivity(i);

                                }
                                else if(macativity.contains("Bises")){

                                    i = new Intent(getApplicationContext()  , Bises.class);
                                    startActivity(i);

                                }
                                else if(macativity.contains("Voca_activity")){


                                    i = new Intent(getApplicationContext()  , Voca_activity.class);
                                    startActivity(i);

                                }
                                else if (macativity.contains("ENGLISH"))
                                {
                                    i = new Intent(getApplicationContext()  , career_prep_Eng.class);
                                    startActivity(i);
                                }
                                else if (macativity.contains("BANGLA"))
                                {
                                    i = new Intent(getApplicationContext()  , career_prep_Bangla.class);
                                    i.putExtra("DB" , "Career_Prep_Bangla") ;
                                    startActivity(i);
                                }
                                else if (macativity.contains("MATH"))
                                {
                                    i = new Intent(getApplicationContext()  , career_prep_Bangla.class);
                                    i.putExtra("DB" , "career_prep_MAth") ;
                                    startActivity(i);

                                }
                                else if (macativity.contains("ICT"))
                                {

                                    i = new Intent(getApplicationContext()  , career_prep_Bangla.class);
                                    i.putExtra("DB" , "career_prep_Ict") ;
                                    startActivity(i);
                                }
                                else if (macativity.contains("BANGLADESH"))
                                {
                                    i = new Intent(getApplicationContext()  , career_prep_Bangla.class);
                                    i.putExtra("DB" , "career_prep_Bangladesh") ;
                                    startActivity(i);

                                }else if (macativity.contains("INTERNATIONAL"))
                                {
                                    i = new Intent(getApplicationContext()  , career_prep_Bangla.class);
                                    i.putExtra("DB" , "career_prep_Inter") ;
                                    startActivity(i);
                                }
                                else if (macativity.contains("GENERAL_SCIENCE"))
                                {
                                    i = new Intent(getApplicationContext()  , career_prep_Bangla.class);
                                    i.putExtra("DB" , "career_prep_Gk") ;
                                    startActivity(i);
                                }
                                else if (macativity.contains("OTHERS"))
                                {
                                    i = new Intent(getApplicationContext()  , career_prep_Bangla.class);
                                    i.putExtra("DB" , "career_prep_Others") ;
                                    startActivity(i);

                                }
                                else if (macativity.contains("FEATURE"))
                                {

                                    i = new Intent(getApplicationContext()  , Feature_Activity.class);
                                    startActivity(i);
                                }
                                else if (macativity.contains("TEACHER_REG"))
                                {

                                    i = new Intent(getApplicationContext()  , career_prep_Bangla.class);
                                    i.putExtra("DB" , "Shikok_nivondon") ;
                                    startActivity(i);

                                }
                                else if (macativity.contains("GOVT_JOB"))
                                {
                                    i = new Intent(getApplicationContext()  , career_prep_Bangla.class);
                                    i.putExtra("DB" , "Govt_job") ;
                                    startActivity(i);
                                }
                                else if (macativity.contains("BANK"))
                                {
                                    i = new Intent(getApplicationContext()  , career_prep_Bangla.class);
                                    i.putExtra("DB" , "Bank_prep") ;
                                    startActivity(i);


                                }
                                else if (macativity.contains("EDOTIRIAL"))
                                {

                                    i = new Intent(getApplicationContext()  , Editorial.class);
                                    startActivity(i);
                                }
                                else if (macativity.contains("QUSTION_BANK"))
                                {
                                    i = new Intent(getApplicationContext()  , Qustion_Bank.class);
                                    startActivity(i);

                                }
                                else if (macativity.contains("QUIZ"))
                                {
                                    i = new Intent(getApplicationContext()  , QuizActivity.class);
                                    startActivity(i);

                                }
                               else if (macativity.contains("NOTICE_BOARD"))
                               {
                                   i = new Intent(getApplicationContext()  , ArticleActivity.class);
                                   startActivity(i);

                               }
//                                else if (macativity.contains(""))
//                                {
//
//                                }
//                                else if (macativity.contains(""))
//                                {
//
//                                }else if (macativity.contains(""))
//                                {
//
//                                }else if (macativity.contains(""))
//                                {
//
//                                }else if (macativity.contains(""))
//                                {
//
//                                }else if (macativity.contains(""))
//                                {
//
//                                }else if (macativity.contains(""))
//                                {
//
//                                }else if (macativity.contains(""))
//                                {
//
//                                }else if (macativity.contains(""))
//                                {
//
//                                }else if (macativity.contains(""))
//                                {
//
//                                }else if (macativity.contains(""))
//                                {
//
//                                }else if (macativity.contains(""))
//                                {
//
//                                }else if (macativity.contains(""))
//                                {
//
//                                }else if (macativity.contains(""))
//                                {
//
//                                }


                                else {

                                }




                            }

                            @Override
                            public void onItemLongClick(View view, int position) {
                                //TODO do your own implementaion on long item click
                            }
                        });

                        return viewHolder;
                    }

                };

        //set adapter to recyclerview
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);

    }
}





