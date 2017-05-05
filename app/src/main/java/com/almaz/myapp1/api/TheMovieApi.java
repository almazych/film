package com.almaz.myapp1.api;

import com.almaz.myapp1.PostModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface TheMovieApi {

    @GET("movie/popular")
    Call<PostModel> getData(@Query("api_key") String apiKey, @Query("language") String lang, @Query("page") int numPage);
}