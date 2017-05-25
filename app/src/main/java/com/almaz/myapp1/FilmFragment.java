package com.almaz.myapp1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import static com.almaz.myapp1.MainFragment.API_KEY;

public class FilmFragment extends Fragment {

    @BindView(R.id.film_name) TextView mTitle;
    @BindView(R.id.film_image) ImageView mImage;
    @BindView(R.id.overview_film) TextView mOverView;

    Film film;
    String filmId;

    public void updateText(String text){
        filmId=text;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.film_fragment, container, false);
        ButterKnife.bind(getActivity());

        App.getApi().getFilm(filmId, API_KEY, "ru-US").enqueue(new Callback<Film>() {
            @Override
            public void onResponse(Call<Film> call, Response<Film> response) {
                if (response.isSuccessful()) {
                    Log.d("TAG", "Status Code = " + response.code());
                    film = response.body();

                    mTitle.setText(film.getTitle());
                    mOverView.setText(film.getOverview());

                    Picasso.with(getActivity())
                            .load(String.format("https://image.tmdb.org/t/p/w185_and_h278_bestv2%s",film.getPosterPath()))
                            .placeholder(R.drawable.poster_fon)
                            .resize(200,300)
                            .into(mImage);
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

        return view;
    }
}
