package com.metacoders.home;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitArrayApi {

     // @GET("wp-json/wp/v2/posts?fields=id,title.rendered,link,excerpt.rendered")
     @GET("wp-json/wp/v2/posts?categories=5")
      Call<List<WpPost>>getPostInfo();

}
