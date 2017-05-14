package com.almaz.myapp1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_TITLE = "com.almaz.myapp1.title_film";
    public static final String EXTRA_IMAGE = "com.almaz.myapp1.image_film";
    public static final String API_KEY = "1546eddf24e069a6848cd0c34766935f";
    public static final String TAG = "LogTag";

    RecyclerView recyclerView;
    PostsAdapter adapter;
    PostModel post;
    Film film;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.posts_recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new PostsAdapter(new ArrayList<Result>(), new PostsAdapter.OnItemClickListener() {
            @Override public void onItemClick(final Result item) {
                App.getApi().getFilm(item.getId(), API_KEY, "ru-US").enqueue(new Callback<Film>() {
                    @Override
                    public void onResponse(Call<Film> call, Response<Film> response) {
                        if (response.isSuccessful()) {
                            Log.d("TAG", "Status Code = " + response.code());
                            film = response.body();

                            Intent i = new Intent(MainActivity.this, FilmActivity.class);
                            i.putExtra(EXTRA_TITLE,item.getTitle());
                            i.putExtra(EXTRA_IMAGE,item.getPosterPath());
                            startActivity(i);

                        } else {
                            try {
                                Log.d("TAG", response.errorBody().string());
                            } catch (IOException ioe) {
                                Log.d("TAG", ioe.getLocalizedMessage());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Film> call, Throwable t) {}
                });


            }
        });
        recyclerView.setAdapter(adapter);

        App.getApi().getData(API_KEY, "ru-US", 1).enqueue(new Callback<PostModel>() {
            public void onResponse(Call<PostModel> call, Response<PostModel> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "Status Code = " + response.code());
                    post = response.body();
                    adapter.changeDataSet(post.getResults());
                } else {
                    try {
                        Log.d(TAG, response.errorBody().string());
                    } catch (IOException ioe) {
                        Log.d(TAG, ioe.getLocalizedMessage());
                    }
                }
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<PostModel> call, Throwable t) {}
        });
    }
}