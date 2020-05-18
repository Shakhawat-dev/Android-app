package com.metacoders.home.bookMarkController;

import android.content.DialogInterface;
import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeErrorDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.metacoders.home.Home_Activity;
import com.metacoders.home.Qustion_Bank;
import com.metacoders.home.R;
import com.metacoders.home.ViewHolder;
import com.metacoders.home.Voc_post_detail;
import com.metacoders.home.loginandSetup.loginactivity;
import com.metacoders.home.model.modelForBookMark;
import com.metacoders.home.packagePage.packageList;
import com.metacoders.home.qus_bank_detail;
import com.metacoders.home.utils.utilities;

import libs.mjn.prettydialog.PrettyDialog;
import libs.mjn.prettydialog.PrettyDialogCallback;

public class bookmarkActivity extends AppCompatActivity {

    AdView mAdView ;
    LinearLayoutManager mLayoutManager; //for sorting
    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;
    FirebaseAuth mauth ;
    String uid ;
    int rewardd = 0 ;
    RewardedAd rewardedAd;
    PrettyDialog dialog ;
    InterstitialAd interstitialAd ;
    com.metacoders.home.utils.utilities utilities;
    String mTitle , mDesc , mId  , imageLInk;
     Home_Activity home ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);
        home = new Home_Activity() ;
        utilities = new utilities() ;
        rewardedAd =  utilities.loadRewardAd(bookmarkActivity.this);
        interstitialAd = utilities.loadIntersitalAd(bookmarkActivity.this) ;



        //loading Ad
        mAdView = findViewById(R.id.adView_bookmark);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mLayoutManager = new LinearLayoutManager(this);
        //RecyclerView
        mRecyclerView = findViewById(R.id.recyclerViewForBookmark);
        mRecyclerView.setHasFixedSize(true);

        //set layout as LinearLayout
        mRecyclerView.setLayoutManager(mLayoutManager);

        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);


        if( isUserSignedIn())
        {
            loadData();
        }
        else {

            triggerWarningDialouge();
        }



    }

 public  void    loadData() {
     mauth = FirebaseAuth.getInstance() ;
     uid = mauth.getUid();

     mFirebaseDatabase = FirebaseDatabase.getInstance();
     mRef = mFirebaseDatabase.getReference("Users").child(uid).child("bookmarks");
     mRef.keepSynced(true);

     FirebaseRecyclerAdapter<modelForBookMark, viewHolderForBOOKMARK> firebaseRecyclerAdapter =
             new FirebaseRecyclerAdapter<modelForBookMark, viewHolderForBOOKMARK>(
                     modelForBookMark.class,
                     R.layout.row_for_bookmark,
                     viewHolderForBOOKMARK.class,
                     mRef
             ) {


                 @Override
                 protected void populateViewHolder(viewHolderForBOOKMARK viewHolder, modelForBookMark model, int position) {


                     viewHolder.setDetails(getApplicationContext(), model.getTitle(), model.getDescription(), model.getImage() , model.getPostuid());


                     Handler handler  = new Handler() ;
                     handler.postDelayed(new Runnable() {
                         @Override
                         public void run() {

                             //   job_progressBar.setVisibility(View.GONE);

                         }
                     },3000);

                 }






                 @Override
                 public viewHolderForBOOKMARK onCreateViewHolder(ViewGroup parent, int viewType) {

                     viewHolderForBOOKMARK viewHolder = super.onCreateViewHolder(parent, viewType);

                     viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                         @Override
                         public void onItemClick(View view, int position) {

                             //Views
                             //   TextView mTitleTv = view.findViewById(R.id.rTitleTv);
                             //    TextView mDescTv = view.findViewById(R.id.rDescriptionTv);
                             //     ImageView mImageView = view.findViewById(R.id.rImageView);

                             mTitle = getItem(position).getTitle();
                             mDesc = getItem(position).getDescription();
                             mId = getItem(position).getPostuid() ;
                             imageLInk = getItem(position).getImage() ;
                             //Views
                             if(home.getAuthra())
                             {
                                 // TextView mTitleTv = view.findViewById(R.id.rTitleTv_feature);
                                 // TextView mDescTv = view.findViewById(R.id.rDescriptionTv_feature);

                                 //get data from views


                                 Intent o = new Intent(getApplicationContext() , BookMarkDetails.class);
                                 o.putExtra("title" , mTitle) ;
                                 o.putExtra("desc" , mDesc) ;
                                 o.putExtra("id" , mId) ;
                                 o.putExtra("image" , imageLInk) ;
                                 startActivity(o);

                                 //pass this data to new activity

                             }
                             else {
                                 mTitle = getItem(position).getTitle();
                                 mDesc = getItem(position).getDescription();
                                 mId = getItem(position).getPostuid() ;
                                 imageLInk = getItem(position).getImage() ;


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

    public    boolean  isUserSignedIn() {
        FirebaseAuth mauth  = FirebaseAuth.getInstance();
        FirebaseUser user = mauth.getCurrentUser() ;

        return user != null;



    }
    public  void triggerWarningDialouge() {
        new AwesomeErrorDialog(bookmarkActivity.this)
                .setTitle("Error!!!")
                .setMessage("Do Use This Feature Please Login first ... ")
                .setColoredCircle(R.color.dialogErrorBackgroundColor)
                .setDialogIconAndColor(R.drawable.ic_dialog_error, R.color.white)
                .setCancelable(false)
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

    private void showPaymentDialog() {
        dialog =  new PrettyDialog(bookmarkActivity.this) ;
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

                                    Intent  o = new Intent(bookmarkActivity.this , packageList.class );
                                    startActivity(o);

                                }
                                else {

                                    Intent  o = new Intent(bookmarkActivity.this , loginactivity.class );
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

                                        rewardedAd =   utilities.loadRewardAd(bookmarkActivity.this);
                                    }
                                    else {
                                        rewardd = 0 ;
                                        dialog.dismiss();
                                        Intent o = new Intent(getApplicationContext() , BookMarkDetails.class);
                                        o.putExtra("title" , mTitle) ;
                                        o.putExtra("desc" , mDesc) ;
                                        o.putExtra("id" , mId) ;
                                        o.putExtra("image" , imageLInk) ;
                                        startActivity(o);
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

                                    rewardedAd =   utilities.loadRewardAd(bookmarkActivity.this);


                                }
                            };
                            rewardedAd.show(bookmarkActivity.this, adCallback);
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
                                        Intent o = new Intent(getApplicationContext() , BookMarkDetails.class);
                                        o.putExtra("title" , mTitle) ;
                                        o.putExtra("desc" , mDesc) ;
                                        o.putExtra("id" , mId) ;
                                        o.putExtra("image" , imageLInk) ;
                                        startActivity(o);
    }
                                });

                            }
                            else {
                                utilities.loadRewardAd(bookmarkActivity.this);

                                Toast.makeText(getApplicationContext() , "The interstitial  ad wasn't loaded yet. Try Again Later"
                                        , Toast.LENGTH_SHORT).show();
                            }



                        }

                    }
                })
                .show();
    }


    @Override
    protected void onResume() {
        super.onResume();

        rewardedAd =  utilities.loadRewardAd(bookmarkActivity.this);
        interstitialAd = utilities.loadIntersitalAd(bookmarkActivity.this) ;
    }



}
