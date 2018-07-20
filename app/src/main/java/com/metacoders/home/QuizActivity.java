package com.metacoders.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class QuizActivity extends AppCompatActivity {
    private RecyclerView recyclerView ;
    private ProgressBar progressBar ;
    private LinearLayoutManager mLayoutManager ;
    private ArrayList<Model_for_Quiz_row> list ;
    private Quiz_Recycleview_Adapter madapter ;
    private  String baseUrl = "https://careercoachbd.net" ;
    public static List<Wp_post_quiz> mListPost_quiz;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this,new Crashlytics());
        setContentView(R.layout.activity_quiz);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_article_quiz) ;
        progressBar = (ProgressBar)findViewById(R.id.progressbar_article_quiz);
        mLayoutManager= new LinearLayoutManager(QuizActivity.this, LinearLayoutManager.VERTICAL , false );
        recyclerView.setLayoutManager(mLayoutManager);
        list=new ArrayList<Model_for_Quiz_row>() ;
        getRetrofit();
        madapter = new Quiz_Recycleview_Adapter( list , QuizActivity.this );
        recyclerView.setAdapter(madapter);


    }
    public  void getRetrofit(){


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        retrofitapi_quiz service = retrofit.create(retrofitapi_quiz.class);
        Call<List<Wp_post_quiz>> call = service.getPostInfo();

        call.enqueue(new Callback<List<Wp_post_quiz>>() {
            @Override
            public void onResponse(Call<List<Wp_post_quiz>> call, Response<List<Wp_post_quiz>> response) {
                Log.e("main" , "title"+ response.body());
                mListPost_quiz = response.body() ;
               progressBar.setVisibility(View.GONE);
                for(int i = 0; i<response.body().size();i++){
                    Log.e("main ", " title "+ response.body().get(i).getTitle().getRendered() + " "+
                            response.body().get(i).getId());

                    String tempdetails =response.body().get(i).getExcerpt().getRendered().toString();
                    tempdetails = tempdetails.replace("<p>","");
                    tempdetails = tempdetails.replace("</p>","");
                    tempdetails = tempdetails.replace("[&hellip;]","");

                    list.add( new Model_for_Quiz_row( Model_for_Quiz_row.IMAGE_TYPE,  response.body().get(i).getTitle().getRendered(),
                            tempdetails,
                            response.body().get(i).getLink())  );
                }
                madapter.notifyDataSetChanged();
}

            @Override
            public void onFailure(Call<List<Wp_post_quiz>> call, Throwable t) {

            }
        });

    }
    public static List<Wp_post_quiz> getList(){
        return  mListPost_quiz ;
    }
}
