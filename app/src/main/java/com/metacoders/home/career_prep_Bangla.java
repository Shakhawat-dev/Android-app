package com.metacoders.home;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class career_prep_Bangla extends AppCompatActivity {
    //this is use the model and the view holder of the all subject  and so on ....

    LinearLayoutManager mLayoutManager; //for sorting
    SharedPreferences mSharedPref; //for saving sort settings
    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career_prep__bangla);





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
        mRef = mFirebaseDatabase.getReference("Career_Prep_Bangla");
    }
    //search data
    private void firebaseSearch(String searchText) {

        //convert string entered in SearchView to lowercase
        String query = searchText;

        Query firebaseSearchQuery = mRef.orderByChild("title").startAt(query).endAt(query + "\uf8ff");

        FirebaseRecyclerAdapter<model_for__Career_prep_by_subject,viewHolder_for__Career_prep_by_subject> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<model_for__Career_prep_by_subject, viewHolder_for__Career_prep_by_subject>(
                       model_for__Career_prep_by_subject.class,
                        R.layout.row_for_subject_career_prep,
                        viewHolder_for__Career_prep_by_subject.class,
                        firebaseSearchQuery
                ) {
                    @Override
                    protected void populateViewHolder(viewHolder_for__Career_prep_by_subject viewHolder, model_for__Career_prep_by_subject model, int position) {
                        viewHolder.setDetails(getApplicationContext(), model.getTitle(), model.getDescription());
                        // this is for seacrh entry


                    }

                    @Override
                    public viewHolder_for__Career_prep_by_subject onCreateViewHolder(ViewGroup parent, int viewType) {

                        viewHolder_for__Career_prep_by_subject viewHolder = super.onCreateViewHolder(parent, viewType);

                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                //Views
                                TextView mTitleTv = view.findViewById(R.id.rTitleTv__Career_prep_by_subject);
                                TextView mDescTv = view.findViewById(R.id.rDescriptionTv_Career_prep_by_subject);

                                //get data from views
                                String mTitle = mTitleTv.getText().toString();
                                String mDesc = mDescTv.getText().toString();


                                //pass this data to new activity
                                Intent intent = new Intent(view.getContext(), postDetails_for_Career_prep_by_subject.class);
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
        FirebaseRecyclerAdapter<model_for__Career_prep_by_subject, viewHolder_for__Career_prep_by_subject>
                firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<model_for__Career_prep_by_subject, viewHolder_for__Career_prep_by_subject>(
                        model_for__Career_prep_by_subject.class,
                        R.layout.row_for_subject_career_prep,
                       viewHolder_for__Career_prep_by_subject.class,
                        mRef
                ) {
                    @Override
                    protected void populateViewHolder(viewHolder_for__Career_prep_by_subject viewHolder, model_for__Career_prep_by_subject model, int position) {
                        viewHolder.setDetails(getApplicationContext(), model.getTitle(), model.getDescription());
                    }

                    @Override
                    public viewHolder_for__Career_prep_by_subject onCreateViewHolder(ViewGroup parent, int viewType) {

                        viewHolder_for__Career_prep_by_subject viewHolder = super.onCreateViewHolder(parent, viewType);

                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                //Views
                                TextView mTitleTv = view.findViewById(R.id.rTitleTv__Career_prep_by_subject);
                                TextView mDescTv = view.findViewById(R.id.rDescriptionTv_Career_prep_by_subject);


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





}
