package com.almaz.myapp1;

import android.app.Application;

import com.almaz.myapp1.api.TheMovieApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class App extends Application {

    private static TheMovieApi themovieApi;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        themovieApi = retrofit.create(TheMovieApi.class);
    }

    public static TheMovieApi getApi() {
        return themovieApi;
    }
}
