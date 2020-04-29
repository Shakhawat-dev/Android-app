package com.metacoders.home;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import android.net.Uri;
import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeErrorDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;
import com.crashlytics.android.Crashlytics;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import  com.metacoders.home.utils.utilities ;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.metacoders.home.NottifiactionModule.NottificationPage;
import com.metacoders.home.bookMarkController.bookmarkActivity;
import com.metacoders.home.loginandSetup.loginactivity;
import com.metacoders.home.model.modelForPayment;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.fabric.sdk.android.Fabric;


public class Home_Activity extends AppCompatActivity implements View.OnClickListener {
    private CardView jobsButton, notificationButton, quizButton, vocaButton,
            articleButton, current_Button, contactButton, settingsButton, aboutButton,
            Qus_Bank_Button, bisesButton, feature_Button, edotrial_btn;
    //private DrawerLayout mDrawerlayout;
    //private ActionBarDrawerToggle mToggle;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
public static  Boolean isAuthorized  ;
     utilities  util;
     String TODAY ;


  //  FirebaseAuth mAuth;
 //   FirebaseUser muser;

    AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_home);


        // check user  has  payment or not

        if(isUserSignedIn())
        {
            checkUserHasPayment() ;
        }

        else {

            isAuthorized = false ;
        }


        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .setNotificationOpenedHandler(new notificationOpenHandler())
                .autoPromptLocation(true)
                .init();

        //mAuth = FirebaseAuth.getInstance();
     //   muser = mAuth.getCurrentUser();

        util =  new  utilities() ;
        TODAY = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

        drawerLayout = findViewById(R.id.drawerId);
        navigationView = findViewById(R.id.NAVVIew_ID);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);


        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.cakRi_menu:
                        Intent i = new Intent(getApplicationContext(), PostsListActivity.class);
                        startActivity(i);
                        break;
                    case R.id.Bcs_prep_menu:
                        Intent bcs = new Intent(getApplicationContext(), BcsButtonActivity.class);
                        startActivity(bcs);
                        break;
                    case R.id.job_prep_menu:
                        Intent jobprep = new Intent(getApplicationContext(), CareerPrepActivity.class);
                        startActivity(jobprep);
                        break;
                    case R.id.Bank_prep_menu:
                        Intent Bank = new Intent(getApplicationContext(), Bank_Prep.class);
                        startActivity(Bank);
                        break;
                    case R.id.Govt_job_prep_menu:
                        Intent gob = new Intent(getApplicationContext(), Govt_job_prep.class);
                        startActivity(gob);
                        break;
                    case R.id.Others_prep_menu:
                        Intent others = new Intent(getApplicationContext(), career_prep_Others.class);
                        startActivity(others);
                        break;
                    case R.id.noticeBoard_prep_menu:
                        Intent notiice = new Intent(getApplicationContext(), ArticleActivity.class);
                        startActivity(notiice);
                        break;
                    case R.id.bises_prep_menu:
                        Intent bises = new Intent(getApplicationContext(), Bises.class);
                        startActivity(bises);
                        break;
                    case R.id.Quiz_prep_menu:
                        Intent quiz = new Intent(getApplicationContext(), QuizActivity.class);
                        startActivity(quiz);
                        break;
                    case R.id.Samprotik_prep_menu:
                        Intent sam = new Intent(getApplicationContext(), Current_Activity.class);
                        startActivity(sam);
                        break;
                    case R.id.bookmark_menu:
                        i = new Intent(getApplicationContext(), bookmarkActivity.class);
                        startActivity(i);
                        break;

                    case R.id.profile_menu:
                        i = new Intent(getApplicationContext(), profileActivity.class);
                        startActivity(i);
                        break;
                    case R.id.Voca_prep_menu:
                        Intent voca = new Intent(getApplicationContext(), Voca_activity.class);
                        startActivity(voca);
                        break;
                    case R.id.Edotorial_prep_menu:
                        Intent edo = new Intent(getApplicationContext(), Editorial.class);
                        startActivity(edo);
                        break;
                    case R.id.Shop_prep_menu:
                        Intent shop = new Intent(getApplicationContext(), Shop.class);
                        startActivity(shop);
                        break;
                    case R.id.contact_us_menu:
                        Intent con = new Intent(getApplicationContext(), ContactActivity.class);
                        startActivity(con);
                        break;
                    case R.id.Rate_menu:

                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("market://details?id=" + getPackageName())));

                        } catch (ActivityNotFoundException e) {

                            startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));

                        }
                        break;


                }
                return false;
            }
        });


        adView = findViewById(R.id.adView_homepage);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);


// notification
        /*
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
*/
        startService(new Intent(this, FireBase_notification.class));


//0ne time Dialogue Box
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean fstart = prefs.getBoolean("firstStart", true);
        if (fstart) {

            show_Dialog_after_Install();
        }


        //selecting customs fonts
        TextView cakri = findViewById(R.id.cakribakri);
        Typeface ace = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi_29-05-06.ttf");
        cakri.setTypeface(ace);

        TextView cakri_p = findViewById(R.id.cakri_prostoti);
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi_29-05-06.ttf");
        cakri_p.setTypeface(face);


        TextView not = findViewById(R.id.notice_text);
        Typeface face2 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi_29-05-06.ttf");
        not.setTypeface(face2);

        TextView amader_tit = findViewById(R.id.amader_title);
        Typeface amader = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi_29-05-06.ttf");
        amader_tit.setTypeface(amader);

        TextView settings = findViewById(R.id.settings_ttile);
        Typeface set = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi_29-05-06.ttf");
        settings.setTypeface(set);

        TextView jog = findViewById(R.id.jog);
        Typeface con = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi_29-05-06.ttf");
        jog.setTypeface(con);

        TextView vid = findViewById(R.id.Editorial_title);
        Typeface vidTIt = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi_29-05-06.ttf");
        vid.setTypeface(vidTIt);

        TextView nott = findViewById(R.id.voca_title);
        Typeface face2a = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi_29-05-06.ttf");
        nott.setTypeface(face2a);

        TextView bTitle = findViewById(R.id.bank_title);
        Typeface face3 = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi_29-05-06.ttf");
        bTitle.setTypeface(face3);

        TextView notFeture = findViewById(R.id.feature_title);
        Typeface face2featre = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi_29-05-06.ttf");
        notFeture.setTypeface(face2featre);


        TextView notCurrent = findViewById(R.id.current_title);
        Typeface face2cu = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi_29-05-06.ttf");
        notCurrent.setTypeface(face2cu);

        TextView notQuiz = findViewById(R.id.quiz_title);
        Typeface face2Quiz = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi_29-05-06.ttf");
        notQuiz.setTypeface(face2Quiz);

        TextView notBises = findViewById(R.id.bises);
        Typeface face2b = Typeface.createFromAsset(getAssets(), "fonts/SolaimanLipi_29-05-06.ttf");
        notBises.setTypeface(face2b);


        //defining cardButtons
        edotrial_btn = findViewById(R.id.video_Button);
        jobsButton = findViewById(R.id.jobs_Button);
        notificationButton = findViewById(R.id.notification_Button);
        quizButton = findViewById(R.id.quiz_Button);
        articleButton = findViewById(R.id.article_Button);
        current_Button = findViewById(R.id.current_Button);
        contactButton = findViewById(R.id.contact_Button);
        settingsButton = findViewById(R.id.settings_Button);
        aboutButton = findViewById(R.id.about_Button);
        vocaButton = findViewById(R.id.vocabulari_Button);
        Qus_Bank_Button = findViewById(R.id.questionBank_Button);
        bisesButton = findViewById(R.id.Bises_Btn);
        feature_Button = findViewById(R.id.feature_Button);
        //Click Listener to CardButton
        edotrial_btn.setOnClickListener(this);

        jobsButton.setOnClickListener(this);
        notificationButton.setOnClickListener(this);
        quizButton.setOnClickListener(this);
        articleButton.setOnClickListener(this);
        current_Button.setOnClickListener(this);
        contactButton.setOnClickListener(this);
        settingsButton.setOnClickListener(this);
        aboutButton.setOnClickListener(this);
        vocaButton.setOnClickListener(this);
        Qus_Bank_Button.setOnClickListener(this);
        bisesButton.setOnClickListener(this);
        feature_Button.setOnClickListener(this);


        // checking  net acces
        if (!isConnected(Home_Activity.this)) buildDialog(Home_Activity.this).show();

        else {

            //without interent what u wnat future devlopment

        }


        //For drawer
           /* mDrawerlayout = (DrawerLayout) findViewById(R.id.home_Drawer);
            //mDrawerlayout = (DrawerLayout) findViewById(R.id.contact_activity);
            mToggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
            mDrawerlayout.addDrawerListener(mToggle);
            mToggle.syncState();
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.signout, menu);
        MenuItem item = menu.findItem(R.id.sign_out_btn);
        MenuItem bellicon = menu.findItem(R.id.nottfication_btn);


        bellicon.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent io = new Intent(getApplicationContext(), NottificationPage.class);
                startActivity(io);
                return false;
            }
        });


        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

             //   mAuth.signOut();

             // util.TriggerAlertDialougeForPurchage(Home_Activity.this);

                if(isUserSignedIn()){
                    FirebaseAuth mAuth = FirebaseAuth.getInstance();
                    mAuth.signOut();
                    Intent io = new Intent(getApplicationContext(), loginactivity.class);
                    startActivity(io);
                    finish();
                }
                else {

                    // trigger  a diaglue that user is not signed in  .
                       triggerWarningDialouge();
                }


                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }


        return super.onOptionsItemSelected(item);

    }


    @Override
    public void onClick(View view) {
        Intent i;

        switch (view.getId()) {
            case R.id.questionBank_Button:
                i = new Intent(this, Qustion_Bank.class);
                startActivity(i);
                break;

            case R.id.Bises_Btn:
                i = new Intent(this, Bises.class);
                startActivity(i);
                break;

            case R.id.feature_Button:
                i = new Intent(this, Feature_Activity.class);
                startActivity(i);
                break;


            case R.id.vocabulari_Button:
                i = new Intent(this, Voca_activity.class);
                startActivity(i);
                break;
            case R.id.jobs_Button:
                i = new Intent(this, PostsListActivity.class);
                startActivity(i);
                break;
            case R.id.notification_Button:
                i = new Intent(this, CareerPrepActivity.class);
                startActivity(i);
                break;
            case R.id.quiz_Button:
                i = new Intent(this, QuizActivity.class);
                startActivity(i);
                break;
            case R.id.article_Button:
                i = new Intent(this, ArticleActivity.class);
                startActivity(i);
                break;


            case R.id.current_Button:
                i = new Intent(this, Current_Activity.class);
                startActivity(i);
                break;
            case R.id.contact_Button:
//                i = new Intent(this, ContactActivity.class);
//                startActivity(i);
                break;
            case R.id.settings_Button:
                i = new Intent(this, Shop.class);
                startActivity(i);
                break;
            case R.id.about_Button:
                i = new Intent(this, ContactActivity.class);
                startActivity(i);
                break;
            case R.id.video_Button:
                i = new Intent(this, Editorial.class);
                startActivity(i);
                break;
            default:
                break;
        }

    }

    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            return (mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting());
        } else
            return false;
    }

    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet Connection");
        builder.setMessage("You need to have Mobile Data or wifi to access this App. Press ok to Continue Offline");

        builder.setPositiveButton("Use Offline", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {


            }
        });

        return builder;
    }

    private void show_Dialog_after_Install() {


        new AlertDialog.Builder(this)
                .setTitle("Hey Job Seeker !!")
                .setMessage("Thanks For Installing The App.")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create().show();
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();
    }

    public class notificationOpenHandler implements OneSignal.NotificationOpenedHandler {
        @Override
        public void notificationOpened(OSNotificationOpenResult result) {
         //   String title = result.notification.payload.title;
           String desc = result.notification.payload.body;
          //  String f = result.notification.payload.groupKey

            Intent intent = new Intent(getApplicationContext(), NottificationPage.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            /*
           if(desc.equals("Voca_activity")){
               Intent intent = new Intent(getApplicationContext(), Voca_activity.class);
               intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
               startActivity(intent);
           }
           else {
               Intent intent = new Intent(getApplicationContext(), NottificationPage.class);
               intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
               startActivity(intent);
           }
*/

        }

    }

    public    boolean  isUserSignedIn()
    {
        FirebaseAuth mauth  = FirebaseAuth.getInstance();
        FirebaseUser user = mauth.getCurrentUser() ;

        return user != null;



    }
    public  void triggerWarningDialouge()
    {
        new AwesomeErrorDialog(Home_Activity.this)
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

    @Override
    protected void onStart() {
        super.onStart();




    }

    private void checkUserHasPayment() {
      final  ProgressDialog dialog = new ProgressDialog(Home_Activity.this );
        dialog.setMessage("Checking For Payment");
        final DatabaseReference mref = FirebaseDatabase.getInstance().getReference("Users")
                .child(FirebaseAuth.getInstance().getUid()).child("transaction");

        dialog.show();

        mref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists())
                {
                    // calculate  his time is available or not

                    modelForPayment model =dataSnapshot.getValue(modelForPayment.class) ;

                    int  limit  = Integer.parseInt(model.getDuration())  ;

                  String qDate = model.getPurchaged_date() ;

                  int checkLimit = util.calculateDayCount(qDate ,TODAY ) ;
                  if(limit <= checkLimit)
                  {
                      // time has over

                      mref.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                          @Override
                          public void onComplete(@NonNull Task<Void> task) {

                              Toast.makeText(getApplicationContext() , "Your Subscription Ended !! " +isAuthorized + "  ", Toast.LENGTH_SHORT)
                                      .show();
                              isAuthorized = false ;

                              dialog.dismiss();
                          }
                      }) ;
                  }
                  else {

                      isAuthorized = true ;

//
                      Toast.makeText(getApplicationContext() , "You are Authed : " +isAuthorized + "  " + checkLimit + " duration "+ limit  +" "+ model.getPurchaged_date() + " today "+ TODAY, Toast.LENGTH_SHORT)
                              .show();

                      dialog.dismiss();
                  }

                }
                else if ( !dataSnapshot.exists()) {

                    isAuthorized = false ;
                    Toast.makeText(getApplicationContext() , "You Have No  Subscription !! " +isAuthorized + "  ", Toast.LENGTH_SHORT)
                            .show();
                    dialog.dismiss();
                }
                else {
                    Toast.makeText(getApplicationContext() , "You Have No  Subscription !! " +isAuthorized + "  ", Toast.LENGTH_SHORT)
                            .show();
                    isAuthorized = false ;
                    dialog.dismiss();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {



            }
        });

    }

    public boolean getAuthra()
    {
            Boolean daa = isAuthorized ;


        return daa ;
    }



}

