package com.almaz.myapp1;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "com.almaz.myapp1.id_film";
    public static final String API_KEY = "1546eddf24e069a6848cd0c34766935f";
    public static final String TAG = "LogTag";

    RecyclerView recyclerView;
    PostsAdapter adapter;
    PostModel post;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar  = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        recyclerView = (RecyclerView) findViewById(R.id.posts_recycle_view);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new PostsAdapter(new ArrayList<Result>(), new PostsAdapter.OnItemClickListener() {
            @Override public void onItemClick(final Result item) {
                            Intent i = new Intent(MainActivity.this, FilmActivity.class);
                            i.putExtra(EXTRA_ID,item.getId());
                            startActivity(i);
            }
        });
        recyclerView.setAdapter(adapter);

        App.getApi().getData(API_KEY, "ru-US", 1).enqueue(new Callback<PostModel>() {
            public void onResponse(Call<PostModel> call, Response<PostModel> response) {
                progressBar.setVisibility(View.GONE);

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
            public void onFailure(Call<PostModel> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }

        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("LMKey", recyclerView.getLayoutManager().onSaveInstanceState());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Parcelable recyclerViewLayoutState = savedInstanceState.getParcelable("LMKey");
        recyclerView.getLayoutManager().onRestoreInstanceState(recyclerViewLayoutState);
    }

}