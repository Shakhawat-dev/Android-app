package com.metacoders.home;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.metacoders.home.bookMarkController.bookmarkActivity;
import com.metacoders.home.loginandSetup.loginactivity;
import com.metacoders.home.packagePage.packageList;
import com.metacoders.home.utils.utilities;

import libs.mjn.prettydialog.PrettyDialog;
import libs.mjn.prettydialog.PrettyDialogCallback;

public class Qustion_Bank extends AppCompatActivity {
// qustion bank this java file works as a list on

    LinearLayoutManager mLayoutManager; //for sorting
    SharedPreferences mSharedPref; //for saving sort settings
    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;
AdView ad ;
    DrawerLayout drawerLayout ;
    ActionBarDrawerToggle toggle ;
    NavigationView navigationView ;
    Home_Activity home ;
    int rewardd = 0 ;
    RewardedAd rewardedAd;
    PrettyDialog dialog ;
    InterstitialAd interstitialAd ;
    utilities utilities;
    String mTitle , mDesc ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qustion__bank);


        ad=findViewById(R.id.adView_QustionBank_List);
        //loading ad
        home = new Home_Activity() ;
        utilities = new utilities() ;
        rewardedAd =  utilities.loadRewardAd(Qustion_Bank.this);
        interstitialAd = utilities.loadIntersitalAd(Qustion_Bank.this) ;


        AdRequest adRequest = new AdRequest.Builder().build();
        ad.loadAd(adRequest);

        drawerLayout = findViewById(R.id.drawerId_qus_bank);
        navigationView=findViewById(R.id.NAVVIew_ID_qusBank);

        toggle = new ActionBarDrawerToggle(this,drawerLayout ,R.string.nav_open ,R.string.nav_close );


        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.cakRi_menu:
                        Intent i = new Intent(getApplicationContext() ,PostsListActivity.class);
                        startActivity(i);
                        break;
                    case R.id.Bcs_prep_menu:
                        Intent bcs = new Intent(getApplicationContext() ,BcsButtonActivity.class);
                        startActivity(bcs);
                        break;
                    case R.id.job_prep_menu:
                        Intent jobprep = new Intent(getApplicationContext() , CareerPrepActivity.class);
                        startActivity(jobprep);
                        break;
                    case R.id.Bank_prep_menu:
                        Intent Bank = new Intent(getApplicationContext() ,Bank_Prep.class);
                        startActivity(Bank);
                        break;
                    case R.id.Govt_job_prep_menu:
                        Intent gob = new Intent(getApplicationContext() ,Govt_job_prep.class);
                        startActivity(gob);
                        break;
                    case R.id.bookmark_menu:
                        i = new Intent(getApplicationContext(), bookmarkActivity.class);
                        startActivity(i);
                        break;

                    case R.id.profile_menu:
                        i = new Intent(getApplicationContext(), profileActivity.class);
                        startActivity(i);
                        break;
                    case R.id.Others_prep_menu:
                        Intent others = new Intent(getApplicationContext() ,career_prep_Others.class);
                        startActivity(others);
                        break;
                    case R.id.noticeBoard_prep_menu:
                        Intent notiice = new Intent(getApplicationContext() ,ArticleActivity.class);
                        startActivity(notiice);
                        break;
                    case R.id.bises_prep_menu:
                        Intent bises = new Intent(getApplicationContext() ,Bises.class);
                        startActivity(bises);
                        break;
                    case R.id.Quiz_prep_menu:
                        Intent quiz = new Intent(getApplicationContext() ,QuizActivity.class);
                        startActivity(quiz);
                        break;
                    case R.id.Samprotik_prep_menu:
                        Intent sam = new Intent(getApplicationContext() ,Current_Activity.class);
                        startActivity(sam);
                        break;
                    case R.id.Voca_prep_menu:
                        Intent voca = new Intent(getApplicationContext() ,Voca_activity.class);
                        startActivity(voca);
                        break;
                    case R.id.Edotorial_prep_menu:
                        Intent edo = new Intent(getApplicationContext() ,Editorial.class);
                        startActivity(edo);
                        break;
                    case R.id.Shop_prep_menu:
                        Intent shop = new Intent(getApplicationContext() ,Shop.class);
                        startActivity(shop);
                        break;
                    case R.id.contact_us_menu:
                        Intent con = new Intent(getApplicationContext() ,ContactActivity.class);
                        startActivity(con);
                        break;
                    case R.id.Rate_menu:
                        try{
                            startActivity(new Intent(Intent.ACTION_VIEW ,
                                    Uri.parse("market://details?id=" + getPackageName())));

                        } catch (ActivityNotFoundException e){

                            startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName() ) ));

                        }
                        break;

                }
                return false;
            }
        });



        //Actionbar
        ActionBar actionBar = getSupportActionBar();
        //set title
        mSharedPref = getSharedPreferences("SortSettings", MODE_PRIVATE);
        String mSorting = mSharedPref.getString("Sort", "newest"); //where if no settings is selected newest will be default

        if (mSorting.equals("newest")) {
            mLayoutManager = new LinearLayoutManager(this);
            //this will load the items from bottom means newest first
            mLayoutManager.setReverseLayout(true);
            mLayoutManager.setStackFromEnd(true);
        } else if (mSorting.equals("oldest")) {
            mLayoutManager = new LinearLayoutManager(this);
            //this will load the items from bottom means oldest first
            mLayoutManager.setReverseLayout(false);
            mLayoutManager.setStackFromEnd(false);
        }

        //RecyclerView
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        //set layout as LinearLayout
        mRecyclerView.setLayoutManager(mLayoutManager);

        //send Query to FirebaseDatabase
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference("Qus_Bank");
    }
    //search data
    private void firebaseSearch(String searchText) {

        //convert string entered in SearchView to lowercase
        String query = searchText;

        Query firebaseSearchQuery = mRef.orderByChild("title").startAt(query).endAt(query + "\uf8ff");

        FirebaseRecyclerAdapter<model_for_qusBank,viewHolder_for_qusBank> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<model_for_qusBank, viewHolder_for_qusBank>(
                        model_for_qusBank.class,
                        R.layout.row_for_qusbank,
                        viewHolder_for_qusBank.class,
                        firebaseSearchQuery
                ) {
                    @Override
                    protected void populateViewHolder(viewHolder_for_qusBank viewHolder, model_for_qusBank model, int position) {
                        viewHolder.setDetails(getApplicationContext(), model.getTitle(), model.getDescription());
                    }

                    @Override
                    public viewHolder_for_qusBank onCreateViewHolder(ViewGroup parent, int viewType) {

                        viewHolder_for_qusBank viewHolder = super.onCreateViewHolder(parent, viewType);

                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View view, final int position) {
                                //Views
                                mTitle = getItem(position).getTitle();
                                mDesc = getItem(position).getDescription();

                                //Views
                                if(home.getAuthra())
                                {
                                    // TextView mTitleTv = view.findViewById(R.id.rTitleTv_feature);
                                    // TextView mDescTv = view.findViewById(R.id.rDescriptionTv_feature);

                                    //get data from views

                                    utilities.sendToDesiredActivity(qus_bank_detail.class , Qustion_Bank.this , mTitle , mDesc);
                                    //pass this data to new activity

                                }
                                else {
                                    mTitle = getItem(position).getTitle();
                                    mDesc = getItem(position).getDescription();

                                    // utilities utilities = new utilities() ;
                                    //  utilities.TriggerAlertDialougeForPurchage(Feature_Activity.this);

                                    showPaymentDialog() ;
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


    //load data into recycler view onStart
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<model_for_qusBank, viewHolder_for_qusBank>
                firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<model_for_qusBank, viewHolder_for_qusBank>(
                       model_for_qusBank.class,
                        R.layout.row_for_qusbank,
                        viewHolder_for_qusBank.class,
                        mRef
                ) {
                    @Override
                    protected void populateViewHolder(viewHolder_for_qusBank viewHolder, model_for_qusBank model, int position) {
                        viewHolder.setDetails(getApplicationContext(), model.getTitle(), model.getDescription());
                    }

                    @Override
                    public viewHolder_for_qusBank onCreateViewHolder(ViewGroup parent, int viewType) {

                        viewHolder_for_qusBank viewHolder = super.onCreateViewHolder(parent, viewType);

                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                //Views
                                //Views
                                mTitle = getItem(position).getTitle();
                                mDesc = getItem(position).getDescription();

                                //Views
                                if(home.getAuthra())
                                {
                                    // TextView mTitleTv = view.findViewById(R.id.rTitleTv_feature);
                                    // TextView mDescTv = view.findViewById(R.id.rDescriptionTv_feature);

                                    //get data from views

                                    utilities.sendToDesiredActivity(qus_bank_detail.class , Qustion_Bank.this , mTitle , mDesc);
                                    //pass this data to new activity

                                }
                                else {
                                    mTitle = getItem(position).getTitle();
                                    mDesc = getItem(position).getDescription();

                                    // utilities utilities = new utilities() ;
                                    //  utilities.TriggerAlertDialougeForPurchage(Feature_Activity.this);

                                    showPaymentDialog() ;
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate the menu; this adds items to the action bar if it present
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                firebaseSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Filter as you type
                firebaseSearch(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(toggle.onOptionsItemSelected(item)){
            return  true ;
        }

        //handle other action bar item clicks here
        if (id == R.id.action_sort) {
            //display alert dialog to choose sorting
            showSortDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showPaymentDialog() {
        dialog =  new PrettyDialog(Qustion_Bank.this) ;
        dialog.setIcon(R.drawable.logo)
                .setTitle(getString(R.string.dialogue_title) +
                        "Read the Content" )
                .setMessage(getString(R.string.dialogue_dubtitle))
                .addButton(
                        "SUBSCRIBE",     // button text
                        R.color.pdlg_color_white,  // button text color
                        R.color.pdlg_color_green,  // button background color
                        new PrettyDialogCallback() {  // button OnClick listener
                            @Override
                            public void onClick() {

                                if(FirebaseAuth.getInstance().getCurrentUser() != null)
                                {

                                    Intent  o = new Intent(Qustion_Bank.this , packageList.class );
                                    startActivity(o);

                                }
                                else {

                                    Intent  o = new Intent(Qustion_Bank.this , loginactivity.class );
                                    startActivity(o);
                                }
                            }
                        }
                )
                .addButton(getString(R.string.watch_ad), R.color.white, R.color.pdlg_color_red, new PrettyDialogCallback() {
                    @Override
                    public void onClick() {




                        if (rewardedAd.isLoaded()) {

                            RewardedAdCallback adCallback = new RewardedAdCallback() {
                                @Override
                                public void onRewardedAdOpened() {
                                    // Ad opened.

                                    Toast.makeText(getApplicationContext() , "AD OPenend"
                                            , Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onRewardedAdClosed() {
                                    // Ad closed.
                                    Toast.makeText(getApplicationContext() , "AD Closed"
                                            , Toast.LENGTH_SHORT).show();



                                    if (rewardd == 0 ) {

                                        rewardedAd =   utilities.loadRewardAd(Qustion_Bank.this);
                                    }
                                    else {
                                        rewardd = 0 ;
                                        dialog.dismiss();

                                        utilities.sendToDesiredActivity(Voc_post_detail.class , Qustion_Bank.this , mTitle , mDesc);
                                    }
                                }

                                @Override
                                public void onUserEarnedReward(@NonNull RewardItem reward) {
                                    // User earned reward.
//                                    Toast.makeText(getApplicationContext() , "AD Earned "
//                                            , Toast.LENGTH_SHORT).show();


                                    rewardd = reward.getAmount() ;
                                    Toast.makeText(getApplicationContext() , "Close The Ad To Open the content "
                                            , Toast.LENGTH_SHORT).show();



                                    // utilities.ess(bises_post_detail.class , Feature_Activity.this , mTitle , mDesc);

                                }

                                @Override
                                public void onRewardedAdFailedToShow(int errorCode) {
                                    // Ad failed to display.
                                    Toast.makeText(getApplicationContext() , "AD Failed "
                                            , Toast.LENGTH_SHORT).show();
                                    rewardd = 0 ;

                                    rewardedAd =   utilities.loadRewardAd(Qustion_Bank.this);


                                }
                            };
                            rewardedAd.show(Qustion_Bank.this, adCallback);
                        }
                        else {
                            Log.d("TAG", "The rewarded ad wasn't loaded yet.");
                            Toast.makeText(getApplicationContext() , "The rewarded ad wasn't loaded yet. Try Again Later !!"
                                    , Toast.LENGTH_SHORT).show();

                            //rewardedAd =  utilities.loadRewardAd(Feature_Activity.this);

                            if(interstitialAd.isLoaded())
                            {
                                interstitialAd.show();

                                interstitialAd.setAdListener(new AdListener(){
                                    @Override
                                    public void onAdLoaded() {
                                        // Code to be executed when an ad finishes loading.


                                    }

                                    @Override
                                    public void onAdFailedToLoad(int errorCode) {
                                        // Code to be executed when an ad request fails.
                                    }

                                    @Override
                                    public void onAdOpened() {
                                        // Code to be executed when the ad is displayed.
                                    }

                                    @Override
                                    public void onAdClicked() {
                                        // Code to be executed when the user clicks on an ad.
                                    }

                                    @Override
                                    public void onAdLeftApplication() {
                                        // Code to be executed when the user has left the app.
                                    }

                                    @Override
                                    public void onAdClosed() {
                                        // Code to be executed when the interstitial ad is closed.

                                        utilities.sendToDesiredActivity(qus_bank_detail.class , Qustion_Bank.this , mTitle , mDesc);
                                    }
                                });

                            }
                            else {
                                utilities.loadRewardAd(Qustion_Bank.this);

                                Toast.makeText(getApplicationContext() , "The interstitial  ad wasn't loaded yet. Try Again Later"
                                        , Toast.LENGTH_SHORT).show();
                            }



                        }

                    }
                })
                .show();
    }
    private void showSortDialog() {
        //options to display in dialog
        String[] sortOptions = {" Newest", " Oldest"};
        //create alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sort by") //set title
                //.setIcon(R.drawable.ic_action_sort) //set icon
                .setItems(sortOptions, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position of the selected item
                        // 0 means "Newest" and 1 means "oldest"
                        if (which == 0) {
                            //sort by newest
                            //Edit our shared preferences
                            SharedPreferences.Editor editor = mSharedPref.edit();
                            editor.putString("Sort", "newest"); //where 'Sort' is key & 'newest' is value
                            editor.apply(); // apply/save the value in our shared preferences
                            recreate(); //restart activity to take effect
                        } else if (which == 1) {
                            {
                                //sort by oldest
                                //Edit our shared preferences
                                SharedPreferences.Editor editor = mSharedPref.edit();
                                editor.putString("Sort", "oldest"); //where 'Sort' is key & 'oldest' is value
                                editor.apply(); // apply/save the value in our shared preferences
                                recreate(); //restart activity to take effect
                            }
                        }
                    }
                });
        builder.show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        rewardedAd =  utilities.loadRewardAd(Qustion_Bank.this);
        interstitialAd = utilities.loadIntersitalAd(Qustion_Bank.this) ;
    }


}

