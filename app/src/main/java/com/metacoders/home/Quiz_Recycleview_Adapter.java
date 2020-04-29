package com.metacoders.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.metacoders.home.loginandSetup.loginactivity;
import com.metacoders.home.packagePage.packageList;
import com.metacoders.home.utils.utilities;

import java.util.ArrayList;

import libs.mjn.prettydialog.PrettyDialog;
import libs.mjn.prettydialog.PrettyDialogCallback;

public class Quiz_Recycleview_Adapter extends RecyclerView.Adapter {
    private ArrayList<Model_for_Quiz_row> dataset ;

    private Context mContext ;
    int rewardd = 0 ;
    RewardedAd rewardedAd;
    PrettyDialog dialog ;
    InterstitialAd interstitialAd ;
    utilities utilities;
    String mTitle , mDesc ;
    int positions = 0 ;

    public Quiz_Recycleview_Adapter(ArrayList<Model_for_Quiz_row>mlist , Context context ){

        this.dataset = mlist ;
        this.mContext = context ;

    }
    public static class  ImageTypeViewHolder extends  RecyclerView.ViewHolder{
        TextView title , subtitle ;
        ImageView imageView ;

        public ImageTypeViewHolder(View itemView) {
            super(itemView);
            this.title = (TextView) itemView.findViewById(R.id.title_quiz) ;
            this.subtitle=(TextView) itemView.findViewById(R.id.subtitle_quiz) ;
            this.imageView = (ImageView) itemView.findViewById(R.id.Icon_quiz) ;

        }
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_quiz , parent ,  false );

        return new Quiz_Recycleview_Adapter.ImageTypeViewHolder(view);
    }
//dinbe

    @Override
    public void onBindViewHolder( final RecyclerView.ViewHolder holder,final int position) {
        final Model_for_Quiz_row object = dataset.get(position);
        (  (Quiz_Recycleview_Adapter.ImageTypeViewHolder) holder).title.setText(object.title);
        (  (Quiz_Recycleview_Adapter.ImageTypeViewHolder) holder).subtitle.setText(object.subtitle);

        ( (Quiz_Recycleview_Adapter.ImageTypeViewHolder) holder).title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Home_Activity home = new Home_Activity() ;
                utilities = new utilities() ;
                rewardedAd =  utilities.loadRewardAd(mContext);
                interstitialAd = utilities.loadIntersitalAd(mContext);
                positions  = position ;
                if(home.getAuthra())
                {
                    // TextView mTitleTv = view.findViewById(R.id.rTitleTv_feature);
                    Intent intent = new Intent(mContext, Wp_Quiz_details.class);
                  intent.putExtra("itemPosition", position);
                  mContext.startActivity(intent);
                }
                else {

                    // utilities utilities = new utilities() ;
                    //  utilities.TriggerAlertDialougeForPurchage(Feature_Activity.this);

                    showPaymentDialog() ;
                }


//                if(home.getAuthra())
//                {
//                    Intent intent = new Intent(mContext, Wp_Quiz_details.class);
//                    intent.putExtra("itemPosition", position);
//                    mContext.startActivity(intent);
//                }
//                else {
//
//                    utilities utilities = new utilities() ;
//                    utilities.TriggerAlertDialougeForPurchage(mContext);
//
//                }




            }
        });
        ( (Quiz_Recycleview_Adapter.ImageTypeViewHolder) holder).subtitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Home_Activity home = new Home_Activity() ;
                utilities = new utilities() ;
                rewardedAd =  utilities.loadRewardAd(mContext);
                interstitialAd = utilities.loadIntersitalAd(mContext);
                positions  = position ;
                if(home.getAuthra())
                {
                    // TextView mTitleTv = view.findViewById(R.id.rTitleTv_feature);
                    Intent intent = new Intent(mContext, Wp_Quiz_details.class);
                    intent.putExtra("itemPosition", position);
                    mContext.startActivity(intent);
                }
                else {

                    // utilities utilities = new utilities() ;
                    //  utilities.TriggerAlertDialougeForPurchage(Feature_Activity.this);

                    showPaymentDialog() ;
                }

            }
        });
        ( (Quiz_Recycleview_Adapter.ImageTypeViewHolder) holder).imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Home_Activity home = new Home_Activity() ;
                utilities = new utilities() ;
                rewardedAd =  utilities.loadRewardAd(mContext);
                interstitialAd = utilities.loadIntersitalAd(mContext);
                positions  = position ;
                if(home.getAuthra())
                {
                    // TextView mTitleTv = view.findViewById(R.id.rTitleTv_feature);
                    Intent intent = new Intent(mContext, Wp_Quiz_details.class);
                    intent.putExtra("itemPosition", position);
                    mContext.startActivity(intent);
                }
                else {

                    // utilities utilities = new utilities() ;
                    //  utilities.TriggerAlertDialougeForPurchage(Feature_Activity.this);

                    showPaymentDialog() ;
                }


            }
        });





    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    private void showPaymentDialog() {
        dialog =  new PrettyDialog(mContext) ;
        dialog.setIcon(R.drawable.logo)
                .setTitle(mContext.getString(R.string.dialogue_title) +
                        "Read the Content" )
                .setMessage(mContext.getString(R.string.dialogue_dubtitle))
                .addButton(
                        "SUBSCRIBE",     // button text
                        R.color.pdlg_color_white,  // button text color
                        R.color.pdlg_color_green,  // button background color
                        new PrettyDialogCallback() {  // button OnClick listener
                            @Override
                            public void onClick() {

                                if(FirebaseAuth.getInstance().getCurrentUser() != null)
                                {

                                    Intent  o = new Intent(mContext, packageList.class );
                                    mContext.startActivity(o);

                                }
                                else {

                                    Intent  o = new Intent(mContext , loginactivity.class );
                                    mContext.startActivity(o);
                                }
                            }
                        }
                )
                .addButton(mContext.getString(R.string.watch_ad), R.color.white, R.color.pdlg_color_red, new PrettyDialogCallback() {
                    @Override
                    public void onClick() {




                        if (rewardedAd.isLoaded()) {

                            RewardedAdCallback adCallback = new RewardedAdCallback() {
                                @Override
                                public void onRewardedAdOpened() {
                                    // Ad opened.

                                    Toast.makeText(mContext , "AD OPenend"
                                            , Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onRewardedAdClosed() {
                                    // Ad closed.
                                    Toast.makeText(mContext , "AD Closed"
                                            , Toast.LENGTH_SHORT).show();



                                    if (rewardd == 0 ) {

                                        rewardedAd =   utilities.loadRewardAd(mContext);
                                    }
                                    else {
                                        rewardd = 0 ;
                                        dialog.dismiss();
                                        Intent intent = new Intent(mContext, Wp_Quiz_details.class);
                                        intent.putExtra("itemPosition", positions);
                                        mContext.startActivity(intent);
                                    }
                                }

                                @Override
                                public void onUserEarnedReward(@NonNull RewardItem reward) {
                                    // User earned reward.
//                                    Toast.makeText(getApplicationContext() , "AD Earned "
//                                            , Toast.LENGTH_SHORT).show();


                                    rewardd = reward.getAmount() ;
                                    Toast.makeText(mContext , "Close The Ad To Open the content "
                                            , Toast.LENGTH_SHORT).show();



                                    // utilities.ess(bises_post_detail.class , Feature_Activity.this , mTitle , mDesc);

                                }

                                @Override
                                public void onRewardedAdFailedToShow(int errorCode) {
                                    // Ad failed to display.
                                    Toast.makeText(mContext , "AD Failed "
                                            , Toast.LENGTH_SHORT).show();
                                    rewardd = 0 ;

                                    rewardedAd =utilities.loadRewardAd(mContext);


                                }
                            };
                            rewardedAd.show((Activity) mContext , adCallback);
                        }
                        else {
                            Log.d("TAG", "The rewarded ad wasn't loaded yet.");
                            Toast.makeText(mContext , "The rewarded ad wasn't loaded yet. Try Again Later !!"
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

                                        dialog.dismiss();
                                    Intent intent = new Intent(mContext, Wp_Quiz_details.class);
                                        intent.putExtra("itemPosition", positions);
                                        mContext.startActivity(intent);
                                }
                                });

                            }
                            else {

                                utilities.loadRewardAd(mContext);

                                Toast.makeText(mContext, "The interstitial  ad wasn't loaded yet. Try Again Later"
                                        , Toast.LENGTH_SHORT).show();
                            }



                        }

                    }
                })
                .show();
    }


}
