package com.almaz.myapp1;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainFragment extends Fragment {

    RecyclerView recyclerView;
    PostsAdapter adapter;
    PostModel post;
    FilmClicked mClicked;
    ProgressBar progressBar;


    public interface FilmClicked {
        void onSendId(String filmId);
        void onSendIdFirst(String firstId);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mClicked = (FilmClicked) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnArticleSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);

        progressBar  = (ProgressBar) view.findViewById(R.id.progressBar_main_fragment);
        progressBar.setVisibility(View.VISIBLE);

        recyclerView = (RecyclerView) view.findViewById(R.id.posts_recycle_view);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL)); //добавляет разделители в списке
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new PostsAdapter(new ArrayList<Result>(), new PostsAdapter.OnItemClickListener() {
            @Override public void onItemClick(final Result item) {

                String filmID = item.getId();

                mClicked.onSendId(filmID);

            }
        });
        recyclerView.setAdapter(adapter);

        App.getApi().getData("1546eddf24e069a6848cd0c34766935f", "ru-US", 1).enqueue(new Callback<PostModel>() {
            @Override
            public void onResponse(Call<PostModel> call, Response<PostModel> response) {
                if (response.isSuccessful()) {
                    Log.d("LogTag", "Status Code = " + response.code());
                    progressBar.setVisibility(View.GONE);
                    post = response.body();
                    adapter.changeDataSet(post.getResults());
                    mClicked.onSendIdFirst(post.getResults().get(0).getId());

                } else {
                    try {
                        Log.d("LogTag", response.errorBody().string());
                    } catch (IOException ioe) {
                        Log.d("LogTag", ioe.getLocalizedMessage());
                    }
                }
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<PostModel> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });

        return view;
    }

}
