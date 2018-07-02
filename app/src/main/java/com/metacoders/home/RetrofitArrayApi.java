package com.metacoders.home;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitArrayApi {

      @GET("wp-json/wp/v2/posts")
      Call<List<WpPost>>getPostInfo();

}
