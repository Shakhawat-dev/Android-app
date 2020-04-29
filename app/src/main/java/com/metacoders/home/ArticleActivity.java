package com.metacoders.home;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;

import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.crashlytics.android.Crashlytics;
import com.metacoders.home.utils.utilities;

import java.util.ArrayList;
import java.util.List;

import io.fabric.sdk.android.Fabric;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArticleActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private LinearLayoutManager mLayoutManager;
    private ArrayList<Model_for_Article_row> list;
    private Article_Recycleview_Adapter adapter;
    private String baseUrl = "https://careercoachbd.net";
    public static List<WpPost> mListPost;
    InterstitialAd interstitialAd;
    utilities utilities;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    RewardedAd rewardedAd;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_article);
        utilities = new utilities();
        rewardedAd = utilities.loadRewardAd(ArticleActivity.this);
        interstitialAd = utilities.loadIntersitalAd(ArticleActivity.this);

        drawerLayout = findViewById(R.id.drawerId_artcile_noticeBoard);
        navigationView = findViewById(R.id.NAVVIew_ID_arctile_noticeboard);

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


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_article);
        progressBar = (ProgressBar) findViewById(R.id.progressbar_article);
        mLayoutManager = new LinearLayoutManager(ArticleActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        list = new ArrayList<Model_for_Article_row>();
        // retrofit api
        //retrofit fill

        getRetrofit();


        adapter = new Article_Recycleview_Adapter(list, ArticleActivity.this);
        recyclerView.setAdapter(adapter);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    public void getRetrofit() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        RetrofitArrayApi service = retrofit.create(RetrofitArrayApi.class);
        Call<List<WpPost>> call = service.getPostInfo();

        call.enqueue(new Callback<List<WpPost>>() {
            @Override
            public void onResponse(Call<List<WpPost>> call, Response<List<WpPost>> response) {
                Log.e("main", "title" + response.body());
                mListPost = response.body();
                progressBar.setVisibility(View.GONE);
                for (int i = 0; i < response.body().size(); i++) {
                    Log.e("main ", " title " + response.body().get(i).getTitle().getRendered() + " " +
                            response.body().get(i).getId());

                    String tempdetails = response.body().get(i).getExcerpt().getRendered().toString();
                    tempdetails = tempdetails.replace("<p>", "");
                    tempdetails = tempdetails.replace("</p>", "");
                    tempdetails = tempdetails.replace("[&hellip;]", "");

                    list.add(new Model_for_Article_row(Model_for_Article_row.IMAGE_TYPE, response.body().get(i).getTitle().getRendered(),
                            tempdetails,
                            response.body().get(i).getLink()));
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<WpPost>> call, Throwable t) {

            }
        });

    }

    public static List<WpPost> getList() {
        return mListPost;
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();


        rewardedAd = utilities.loadRewardAd(ArticleActivity.this);
        interstitialAd = utilities.loadIntersitalAd(ArticleActivity.this);


    }
}
