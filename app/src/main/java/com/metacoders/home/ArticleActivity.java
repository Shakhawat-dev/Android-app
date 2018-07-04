package com.metacoders.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.crashlytics.android.Crashlytics;

import java.util.ArrayList;
import java.util.List;

import io.fabric.sdk.android.Fabric;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArticleActivity extends AppCompatActivity {
    private RecyclerView recyclerView ;
    private ProgressBar progressBar ;
    private LinearLayoutManager mLayoutManager ;
    private ArrayList<Model_for_Article_row> list ;
    private Article_Recycleview_Adapter adapter ;
        private  String baseUrl = "https://www.androbits.net" ;
    public static List<WpPost> mListPost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this,new Crashlytics());
        setContentView(R.layout.activity_article);



        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_article) ;
        progressBar = (ProgressBar) findViewById(R.id.progressbar_article) ;
        mLayoutManager = new LinearLayoutManager(ArticleActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        list = new ArrayList<Model_for_Article_row>();
        // retrofit api
        //retrofit fill

            getRetrofit();


        adapter = new Article_Recycleview_Adapter( list , ArticleActivity.this );
        recyclerView.setAdapter(adapter);


    }

    public  void getRetrofit(){


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        RetrofitArrayApi service = retrofit.create(RetrofitArrayApi.class);
       Call<List<WpPost>> call = service.getPostInfo();

       call.enqueue(new Callback<List<WpPost>>() {
           @Override
           public void onResponse(Call<List<WpPost>> call, Response<List<WpPost>> response) {
               Log.e("main" , "title"+ response.body());
               mListPost = response.body() ;
               progressBar.setVisibility(View.GONE);
               for(int i = 0; i<response.body().size();i++){
                   Log.e("main ", " title "+ response.body().get(i).getTitle().getRendered() + " "+
                           response.body().get(i).getId());

                   String tempdetails =response.body().get(i).getExcerpt().getRendered().toString();
                   tempdetails = tempdetails.replace("<p>","");
                   tempdetails = tempdetails.replace("</p>","");
                   tempdetails = tempdetails.replace("[&hellip;]","");

                   list.add( new Model_for_Article_row( Model_for_Article_row.IMAGE_TYPE,  response.body().get(i).getTitle().getRendered(),
                           tempdetails,
                           response.body().get(i).getLink())  );
               }
               adapter.notifyDataSetChanged();

           }

           @Override
           public void onFailure(Call<List<WpPost>> call, Throwable t) {

           }
       });

    }
    public static List<WpPost> getList(){
        return  mListPost ;
    }
}

