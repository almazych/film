package com.almaz.myapp1;

import android.app.Fragment;
import android.os.Bundle;
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


public class FilmFragment extends Fragment {

    @BindView(R.id.film_name) TextView mTitle;
    @BindView(R.id.film_image) ImageView mImage;
    @BindView(R.id.overview_film) TextView mOverView;

    Film film;
    String filmId;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        filmId = bundle.getString("tag");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.film_fragment, container, false);
        ButterKnife.bind(this,view);

        App.getApi().getFilm(filmId, "1546eddf24e069a6848cd0c34766935f", "ru-US").enqueue(new Callback<Film>() {
            @Override
            public void onResponse(Call<Film> call, Response<Film> response) {
                if (response.isSuccessful()) {
                    Log.d("LogTag", "Status Code = " + response.code());
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
                        Log.d("LogTag", response.errorBody().string());
                    } catch (IOException ioe) {
                        Log.d("LogTag", ioe.getLocalizedMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<Film> call, Throwable t) {
                Log.d("ONFAILURE", "Произошел фейл");
            }
        });

        return view;
    }
}
