package com.metacoders.home.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.metacoders.home.Home_Activity;
import com.metacoders.home.R;
import com.metacoders.home.bises_post_detail;
import com.metacoders.home.loginandSetup.loginactivity;
import com.metacoders.home.packagePage.packageList;

import java.text.SimpleDateFormat;
import java.util.Date;

import libs.mjn.prettydialog.PrettyDialog;
import libs.mjn.prettydialog.PrettyDialogCallback;

public class utilities {

    public int calculateDayCount(String QueryDate , String todayDate) {
        //Setting dates
        String dayDifference = "0.00" ;
        try {
            //Dates to compare
            String CurrentDate=  todayDate;
            String FinalDate=  QueryDate;

            Date date1;
            Date date2;

            SimpleDateFormat dates = new SimpleDateFormat("dd-MM-yyyy");

            //Setting dates
            date1 = dates.parse(CurrentDate);
            date2 = dates.parse(FinalDate);

            //Comparing dates
            long difference = Math.abs(date1.getTime() - date2.getTime());
            long differenceDates = difference / (24 * 60 * 60 * 1000);

            //Convert long to String
            dayDifference = Long.toString(differenceDates);

            Log.e("HERE","HERE: " + dayDifference);
        }

        catch (Exception exception) {
            Log.e("DIDN'T WORK", "exception " + exception);
            dayDifference = "0.00" ;
        }



        return  Integer.parseInt(dayDifference) ;
    }

    //  8o8Rnfu2GxQ68bubfTKLugxnIqo2 test uid


    public  void TriggerAlertDialougeForPurchage(final Context context)
    {
        new PrettyDialog(context)
                .setIcon(R.drawable.logo)
                .setTitle("Please Subscribe to\n" +
                        "Read the Content" )
                .setMessage("বিস্তারিত দেখতে সাবস্ক্রাইব করুন")
                .addButton(
                        "SUBSCRIBE",     // button text
                        R.color.pdlg_color_white,  // button text color
                        R.color.pdlg_color_green,  // button background color
                        new PrettyDialogCallback() {  // button OnClick listener
                            @Override
                            public void onClick() {

                                if(FirebaseAuth.getInstance().getCurrentUser() != null)
                                {

                                    Intent  o = new Intent(context , packageList.class );
                                    context.startActivity(o);

                                }
                                else {

                                    Intent  o = new Intent(context , loginactivity.class );
                                    context.startActivity(o);
                                }
                            }
                        }
                )
                .show();
    }


    public RewardedAd loadRewardAd(final Context context )
    {
        RewardedAd    rewardedAd = new RewardedAd(context,
                "ca-app-pub-1385980455216480/4831863278"); //ca-app-pub-3940256099942544/5224354917
        RewardedAdLoadCallback adLoadCallback = new RewardedAdLoadCallback() {
            @Override
            public void onRewardedAdLoaded() {
                // Ad successfully loaded.
//                Toast.makeText(context , "The rewarded ad  loaded "
//                        , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRewardedAdFailedToLoad(int errorCode) {
                // Ad failed to load.

//                Toast.makeText(context , "The rewarded ad failed to  load."
//                        , Toast.LENGTH_SHORT).show();
            }
        };
        rewardedAd.loadAd(new AdRequest.Builder().build(), adLoadCallback);

    return  rewardedAd  ;

    }


    public  void sendToDesiredActivity(Class test  , Context context , String mTitle  , String mDesc) {

        Intent intent = new Intent(context, test);
        intent.putExtra("title", mTitle); // put title
        intent.putExtra("description", mDesc); //put description
       context.startActivity(intent); //start activity

    }

    public  InterstitialAd  loadIntersitalAd( Context c )
    {
        MobileAds.initialize(c,
                "ca-app-pub-1385980455216480/4528610941");
        InterstitialAd  mInterstitialAd = new InterstitialAd(c);
        mInterstitialAd.setAdUnitId("ca-app-pub-1385980455216480/4528610941");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        return  mInterstitialAd;
    }



}

