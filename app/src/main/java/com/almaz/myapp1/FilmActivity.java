package com.almaz.myapp1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.almaz.myapp1.MainActivity.API_KEY;

public class FilmActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "com.almaz.myapp1.id_film";

    @BindView(R.id.film_name)
    TextView mTitle;
    @BindView(R.id.film_image)
    ImageView mImage;
    @BindView(R.id.overview_film)
    TextView mOverView;

    Film film;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.film_item);
        ButterKnife.bind(this);

        App.getApi().getFilm(getIntent().getStringExtra(EXTRA_ID), API_KEY, "ru-US").enqueue(new Callback<Film>() {
            @Override
            public void onResponse(Call<Film> call, Response<Film> response) {
                if (response.isSuccessful()) {
                    Log.d("TAG", "Status Code = " + response.code());
                    film = response.body();

                    mTitle.setText(film.getTitle());
                    mOverView.setText(film.getOverview());

                    Picasso.with(FilmActivity.this)
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

    }
}
