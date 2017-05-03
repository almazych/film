package com.almaz.myapp1;

import android.app.Application;

import com.almaz.myapp1.api.TheMovieApi;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//инициализация интерфейса и ретрофита
public class App extends Application {

    private static TheMovieApi themovieApi;
    private Retrofit retrofit;
    private OkHttpClient okHttpClient;

    @Override
    public void onCreate() {
        super.onCreate();

        Interceptor loggingInterceptor = new HttpLoggingInterceptor()
                .setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/") //Базовая часть адреса
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create()) //Конвертер, необходимый для преобразования JSON'а в объекты
                .build();//Создаем объект, при помощи которого будем выполнять запросы

        themovieApi = retrofit.create(TheMovieApi.class);
    }

    public static TheMovieApi getApi() {
        return themovieApi;
    }
}
