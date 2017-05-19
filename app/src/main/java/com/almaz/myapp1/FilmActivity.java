package com.almaz.myapp1;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilmActivity extends AppCompatActivity {


    public static final String EXTRA_TITLE = "com.almaz.myapp1.title_film";
    public static final String EXTRA_IMAGE = "com.almaz.myapp1.image_film";
    public static final String EXTRA_OVERVIEW = "com.almaz.myapp1.overview_film";



    @BindView(R.id.film_name) TextView mTitle;
    @BindView(R.id.film_image) ImageView mImage;
    @BindView(R.id.overview_film) TextView mOverView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.film_item);
        ButterKnife.bind(this);

        mTitle.setText(getIntent().getStringExtra(EXTRA_TITLE));
        mOverView.setText(getIntent().getStringExtra(EXTRA_OVERVIEW));
        Picasso.with(FilmActivity.this)
                .load(String.format("https://image.tmdb.org/t/p/w185_and_h278_bestv2%s",getIntent().getStringExtra(EXTRA_IMAGE)))
                .placeholder(R.drawable.poster_fon)
                .into(mImage);

    }

}
