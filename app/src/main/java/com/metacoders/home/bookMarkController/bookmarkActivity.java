package com.metacoders.home.bookMarkController;

import android.content.Intent;

import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeErrorDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.metacoders.home.R;
import com.metacoders.home.ViewHolder;
import com.metacoders.home.loginandSetup.loginactivity;
import com.metacoders.home.model.modelForBookMark;

public class bookmarkActivity extends AppCompatActivity {

    AdView mAdView ;
    LinearLayoutManager mLayoutManager; //for sorting
    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;
    FirebaseAuth mauth ;
    String uid ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);




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

 public  void    loadData()
 {
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



                             //get data from views
                             String mTitle = getItem(position).getTitle() ;                         //mTitleTv.getText().toString();
                             String mDesc = getItem(position).getDescription();                                  //mDescTv.getText().toString();
                             String   mImage =  getItem(position).getImage();
                             String postid = getItem( position).getPostuid() ;

                              /*  Drawable mDrawable = mImageView.getDrawable();
                                Bitmap mBitmap = null;
                                 mBitmap = ((BitmapDrawable) mDrawable).getBitmap();
                                        */

                             // sending to new activity

                             Intent intent = new Intent(view.getContext(), BookMarkDetails.class);
                                                               /*  ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                                              mBitmap.compress(Bitmap.CompressFormat.PNG, 70, stream);
                                                         byte[] bytes = stream.toByteArray(); */
                             //put bitmap image as array of bytes
                             intent.putExtra("title", mTitle); // put title
                             intent.putExtra("description", mDesc); //put description
                             intent.putExtra("image",mImage); //put image
                             intent.putExtra("POSTID", postid );



                             startActivity(intent); //start activity




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

    public    boolean  isUserSignedIn()
    {
        FirebaseAuth mauth  = FirebaseAuth.getInstance();
        FirebaseUser user = mauth.getCurrentUser() ;

        return user != null;



    }
    public  void triggerWarningDialouge()
    {
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


}