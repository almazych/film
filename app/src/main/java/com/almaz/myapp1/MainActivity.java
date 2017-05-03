package com.almaz.myapp1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    List<PostModel.Result> posts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        posts = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.posts_recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        PostsAdapter adapter = new PostsAdapter(posts);
        recyclerView.setAdapter(adapter);

        App.getApi().getData(API_KEY, "ru-US", 1).enqueue(new Callback<List<PostModel.Result>>() {
            @Override  //Данные успешно пришли, но надо проверить response.body() на null
            public void onResponse(Call<List<PostModel.Result>> call, Response<List<PostModel.Result>> response) {
                if(response.isSuccessful()) {
                    posts.addAll(response.body());
                } else{
                    try {
                        Log.d(TAG, response.errorBody().string());
                    } catch (IOException ioe){
                        Log.d(TAG, ioe.getLocalizedMessage());
                    }
                }
                recyclerView.getAdapter().notifyDataSetChanged();
            }
            @Override //Произошла ошибка
            public void onFailure(Call<List<PostModel.Result>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
