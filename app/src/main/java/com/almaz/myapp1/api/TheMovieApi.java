package com.almaz.myapp1.api;

import com.almaz.myapp1.PostModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface TheMovieApi {

    @GET("/movie/popular")
    Call<List<PostModel>> getData(@Query("key") String apiKey, @Query("language") String lang, @Query("page") int numPage);
}