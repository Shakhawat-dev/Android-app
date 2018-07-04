package com.metacoders.home;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitArrayApi {

      @GET("wp-json/wp/v2/posts?fields=id,title.rendered,link,excerpt.rendered")
      Call<List<WpPost>>getPostInfo();

}
