package com.almaz.myapp1;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainFragment extends Fragment {

    public static final String API_KEY = "1546eddf24e069a6848cd0c34766935f";
    public static final String TAG = "LogTag";

    RecyclerView recyclerView;
    PostsAdapter adapter;
    PostModel post;


    FilmClicked mCallBack;

    public interface FilmClicked {
         void sendIdFilm(String filmId);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallBack = (FilmClicked) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement FilmClicked");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.posts_recycle_view);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new PostsAdapter(new ArrayList<Result>(), new PostsAdapter.OnItemClickListener() {
            @Override public void onItemClick(final Result item) {
                mCallBack.sendIdFilm(item.getId());
            }
        });
        recyclerView.setAdapter(adapter);

        App.getApi().getData(API_KEY, "ru-US", 1).enqueue(new Callback<PostModel>() {
            @Override
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

        return view;
    }


    @Override
    public void onDetach() {
        mCallBack = null;
        super.onDetach();
    }
}
