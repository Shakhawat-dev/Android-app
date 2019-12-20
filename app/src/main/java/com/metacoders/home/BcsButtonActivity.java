package com.metacoders.home;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import androidx.annotation.NonNull;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.metacoders.home.bookMarkController.bookmarkActivity;

public class BcsButtonActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayoutManager mLayoutManager; //for sorting
    SharedPreferences mSharedPref; //for saving sort settings
    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;
    AdView mAdView ;
// this activity shares the model of bises and post detail of bises (experimental )
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
private ProgressBar Bcs_Bar ;
public CardView QusBank_button;
    public CardView PrepButton ;

    DrawerLayout drawerLayout ;
    ActionBarDrawerToggle toggle ;
    NavigationView navigationView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bcs_button);

        drawerLayout = findViewById(R.id.drawerId_bcs_Activity);
        navigationView=findViewById(R.id.NAVVIew_ID_bcs_activty);

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
                    case R.id.bookmark_menu:
                        i = new Intent(getApplicationContext(), bookmarkActivity.class);
                        startActivity(i);
                        break;

                    case R.id.profile_menu:
                        i = new Intent(getApplicationContext(), profileActivity.class);
                        startActivity(i);
                        break;
                    case R.id.job_prep_menu:
                        Intent jobprep = new Intent(getApplicationContext() ,NotificationActivity.class);
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


        //advirtisement
        mAdView = findViewById(R.id.adView_Under_BCS_BUTTON);

        AdRequest adRequest = new AdRequest.Builder().build();

        mAdView.loadAd(adRequest);





        Bcs_Bar = (ProgressBar) findViewById(R.id.progressbar_Bcs_prep) ;
        Bcs_Bar.setVisibility(View.VISIBLE);
        //button linking with id
        QusBank_button =  (CardView)findViewById(R.id.qsnBank_Button_in_Bcs);
        PrepButton = (CardView)findViewById(R.id.prep_IN_Bcs_btn);

        ////Click Listener to CardButton


            QusBank_button.setOnClickListener(this);
            PrepButton.setOnClickListener(this);









        //For Drawer
     //   mDrawerlayout = (DrawerLayout) findViewById(R.id.bcs_activity);
      //  mToggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
     //   mDrawerlayout.addDrawerListener(mToggle);
    //    mToggle.syncState();
    //    getSupportActionBar().setDisplayHomeAsUpEnabled(true);



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
        mRef = mFirebaseDatabase.getReference("Bcs_Prep");
    }

    //search data
    private void firebaseSearch(String searchText) {

        //convert string entered in SearchView to lowercase
        String query = searchText;

        Query firebaseSearchQuery = mRef.orderByChild("title").startAt(query).endAt(query + "\uf8ff");

        FirebaseRecyclerAdapter<Model_for_Bises,viewHolder_for_bcs_prep> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Model_for_Bises, viewHolder_for_bcs_prep>(
                        Model_for_Bises.class,
                        R.layout.row_for_bcs_prep,
                        viewHolder_for_bcs_prep.class,
                        firebaseSearchQuery
                ) {
                    @Override
                    protected void populateViewHolder(viewHolder_for_bcs_prep viewHolder, Model_for_Bises model, int position) {
                        viewHolder.setDetails(getApplicationContext(), model.getTitle(), model.getDescription());
                    }

                    @Override
                    public viewHolder_for_bcs_prep onCreateViewHolder(ViewGroup parent, int viewType) {

                        viewHolder_for_bcs_prep viewHolder = super.onCreateViewHolder(parent, viewType);

                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                //Views
                                TextView mTitleTv = view.findViewById(R.id.rTitleTv_bcs_prep);
                                TextView mDescTv = view.findViewById(R.id.rDescriptionTv_bcs_prep);

                                //get data from views
                                String mTitle = mTitleTv.getText().toString();
                                String mDesc = mDescTv.getText().toString();


                                //pass this data to new activity
                                Intent intent = new Intent(view.getContext(), bises_post_detail.class);
                                intent.putExtra("title", mTitle); // put title
                                intent.putExtra("description", mDesc); //put description
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

    //load data into recycler view onStart
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Model_for_Bises, viewHolder_for_bcs_prep>
                firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Model_for_Bises, viewHolder_for_bcs_prep>(
                        Model_for_Bises.class,
                        R.layout.row_for_bcs_prep,
                        viewHolder_for_bcs_prep.class,
                        mRef
                ) {
                    @Override
                    protected void populateViewHolder(viewHolder_for_bcs_prep viewHolder, Model_for_Bises model, int position) {
                        viewHolder.setDetails(getApplicationContext(), model.getTitle(), model.getDescription());

                        Handler handler  = new Handler() ;

                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {


                               Bcs_Bar.setVisibility(View.GONE);

                            }
                        },950);
                    }

                    @Override
                    public viewHolder_for_bcs_prep onCreateViewHolder(ViewGroup parent, int viewType) {

                        viewHolder_for_bcs_prep viewHolder = super.onCreateViewHolder(parent, viewType);

                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                //Views
                                TextView mTitleTv = view.findViewById(R.id.rTitleTv_bcs_prep);
                                TextView mDescTv = view.findViewById(R.id.rDescriptionTv_bcs_prep);

                                //get data from views
                                String mTitle = mTitleTv.getText().toString();
                                String mDesc = mDescTv.getText().toString();

                                //pass this data to new activity
                                Intent intent = new Intent(view.getContext(), bises_post_detail.class);
                                intent.putExtra("title", mTitle); // put title
                                intent.putExtra("description", mDesc); //put description
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
    public void onClick(View view) {
        Intent i ;
        switch (view.getId()){
            case R.id.prep_IN_Bcs_btn :
            i=new Intent(this, Under_Bcs_prep_Button.class);
            startActivity(i);
            break;
            case R.id.qsnBank_Button_in_Bcs :
                i=new Intent(this, Under_Bcs_Button_QustionBank.class);
                startActivity(i);
                break;




            default:
                break;

        }
    }
}
