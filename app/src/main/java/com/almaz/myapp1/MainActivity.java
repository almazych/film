package com.almaz.myapp1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final String API_KEY = "1546eddf24e069a6848cd0c34766935f";
    public static final String TAG = "LogTag";

    RecyclerView recyclerView;
    PostModel post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.posts_recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        App.getApi().getData(API_KEY, "ru-US", 1).enqueue(new Callback<PostModel>() {
            public void onResponse(Call<PostModel> call, Response<PostModel> response)  {
                if(response.isSuccessful()) {
                    Log.d(TAG, "Status Code = " + response.code());
                    post = response.body();
                    //
                    //      Как из объекта вытащить список?
                    //.

                } else{
                    try {
                        Log.d(TAG, response.errorBody().string());
                    } catch (IOException ioe){
                    }
                }
                recyclerView.getAdapter().notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<PostModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
