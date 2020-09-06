package com.metacoders.home;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import androidx.annotation.NonNull;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeErrorDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;
import com.crashlytics.android.Crashlytics;
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
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Locale;

import io.fabric.sdk.android.Fabric;

public class PostDetailActivity extends AppCompatActivity {

    TextView mTitleTv, mDetailTv;
    ImageView mImageIv;

    Bitmap bitmap;

    Button mSaveBtn, mShareBtn, mWallBtn;
    AdView mAdView , downAdview  , DownAdview_down ;

    DrawerLayout drawerLayout ;
    ActionBarDrawerToggle toggle ;
    NavigationView navigationView ;
    DatabaseReference mref ;
    FirebaseAuth mauth ;
    String  uid ;
    Boolean ispressed = false ;
    AlertDialog alertDialog;
String image , title , desc ;



    private static final int WRITE_EXTERNAL_STORAGE_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this,new  Crashlytics());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_post_detail);

        mauth = FirebaseAuth.getInstance();
        try{
            uid = mauth.getUid();

        }
        catch (NullPointerException e ){


        }




        //aDD

        drawerLayout = findViewById(R.id.drawerId_postDetail);
        navigationView=findViewById(R.id.NAVVIew_ID_postDetail);
        mAdView = findViewById(R.id.adView_cakriBakri);
        downAdview = findViewById(R.id.adView_cakriBakriDOwn);
        DownAdview_down = findViewById(R.id.adView_postdestail_down);
        AdRequest adRequest = new AdRequest.Builder().build();
        DownAdview_down.loadAd(adRequest);
        mAdView.loadAd(adRequest);
        downAdview.loadAd(adRequest);

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

        //Action bar
        //ActionBar actionBar = getSupportActionBar();
        //Actionbar title
        //actionBar.setTitle("Post Detail");
        //set back button in action bar
       // actionBar.setDisplayHomeAsUpEnabled(true);
        //actionBar.setDisplayShowHomeEnabled(true);

        //initialize views
        mTitleTv = findViewById(R.id.titleTv);
        mDetailTv = findViewById(R.id.descriptionTv);
        mImageIv = findViewById(R.id.imageView);
        mSaveBtn = findViewById(R.id.saveBtn);
        mShareBtn = findViewById(R.id.shareBtn);
     //   mWallBtn = findViewById(R.id.wallBtn);

        //get data from intent
        image = getIntent().getStringExtra("image");
         title = getIntent().getStringExtra("title");
         desc = getIntent().getStringExtra("description");
    //    Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

        //set data to views
        mTitleTv.setText(title);
        mDetailTv.setText(desc);
        Picasso.get().load(image).into(mImageIv);
     //   mImageIv.setImageBitmap(bmp);

        //get image from imageview as bitmap
      //  bitmap = ((BitmapDrawable) mImageIv.getDrawable()).getBitmap();

        //save btn click handle
        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if os is >= marshmallow we need runtime permission to save image
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                            PackageManager.PERMISSION_DENIED) {
                        String[] permission = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        //show popup to grant permission
                        requestPermissions(permission, WRITE_EXTERNAL_STORAGE_CODE);
                    } else {
                        //permission already granted, save image
                        saveImage();
                    }
                } else {
                    //System os is < marshmallow, save image
                    saveImage();
                }
            }
        });
        //share btn click handle
        mShareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareImage();
            }
        });
        //set wallpaper btn click handle


    }
    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        if(toggle.onOptionsItemSelected(item)){
            return  true ;
        }
        return super.onOptionsItemSelected(item);

    }


    private void shareImage() {
        //get image from
        bitmap = ((BitmapDrawable)mImageIv.getDrawable()).getBitmap();

        try {
            //get title and description and save in string s
            String s = mTitleTv.getText().toString() + "\n \n" + mDetailTv.getText().toString();
             Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain") ;
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT,"My App");
            shareIntent.putExtra(Intent.EXTRA_TEXT,s);
            startActivity(Intent.createChooser(shareIntent ,"Share Via"));




/*
            File file = new File(getExternalCacheDir(), "sample.png");
            FileOutputStream fOut = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
            fOut.close();
            file.setReadable(true, false);
            //intent to share image and text
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(Intent.EXTRA_TEXT, s); // put the text
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
            intent.setType("image/png");
            startActivity(Intent.createChooser(intent, "Share via"));
            */
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void saveImage() {

        //get image from
        bitmap = ((BitmapDrawable)mImageIv.getDrawable()).getBitmap();
        //time stamp, for image name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(System.currentTimeMillis());
        //path to external storage
        File path = Environment.getExternalStorageDirectory();
        //create folder named "Firebase"
        File dir = new File(path + "/JobApp/");
        dir.mkdirs();
        //image name
        String imageName = timeStamp + ".PNG";
        File file = new File(dir, imageName);
        OutputStream out;
        try {
            out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 95, out);
            out.flush();
            out.close();
            Toast.makeText(this, imageName + " saved to" + dir, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            //failed saving image
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    //handle onBackPressed(go to previous activity)
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case WRITE_EXTERNAL_STORAGE_CODE: {
                //if request code is cancelled the result arrays are empty
                if (grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    //permission is granted, save image
                    saveImage();
                } else {
                    //permission denied
                    Toast.makeText(this, "enable permission to save image", Toast.LENGTH_SHORT).show();
                }
            }
        }

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


            AlertDialog.Builder builder = new AlertDialog.Builder(PostDetailActivity.this,R.style.DialogTheme);
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

        mref = FirebaseDatabase.getInstance().getReference("Users").child(uid).child("bookmarks");
        String ts = mref.push().getKey();

        modelForBookMark  model = new modelForBookMark(title , image , desc, ts);
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
    public    boolean  isUserSignedIn() {
        FirebaseAuth mauth  = FirebaseAuth.getInstance();
        FirebaseUser user = mauth.getCurrentUser() ;

        return user != null;



    }
    public  void triggerWarningDialouge() {
        new AwesomeErrorDialog(PostDetailActivity.this)
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