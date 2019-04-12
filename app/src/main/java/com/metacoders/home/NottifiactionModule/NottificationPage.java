package com.metacoders.home.NottifiactionModule;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.metacoders.home.AboutActivity;
import com.metacoders.home.BcsButtonActivity;
import com.metacoders.home.Bises;
import com.metacoders.home.Current_Activity;
import com.metacoders.home.Model;
import com.metacoders.home.PostDetailActivity;
import com.metacoders.home.PostsListActivity;
import com.metacoders.home.R;
import com.metacoders.home.ViewHolder;
import com.metacoders.home.Voca_activity;
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
        mRef = mFirebaseDatabase.getReference("notfication");
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
                            public void onItemClick(View view, int position) {

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





