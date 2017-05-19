package com.almaz.myapp1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class FilmActivity extends AppCompatActivity {


    public static final String EXTRA_TITLE = "com.almaz.myapp1.title_film";
    public static final String EXTRA_IMAGE = "com.almaz.myapp1.image_film";

    TextView mTitle;
    ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.film_item);

        mTitle = (TextView)findViewById(R.id.film_name);
        mImage = (ImageView)findViewById(R.id.film_image);

        mTitle.setText(getIntent().getStringExtra(EXTRA_TITLE));
        Picasso.with(FilmActivity.this)
                .load(String.format("https://image.tmdb.org/t/p/w185_and_h278_bestv2%s",getIntent().getStringExtra(EXTRA_IMAGE)))
                .placeholder(R.drawable.poster_fon)
                .into(mImage);


    }

}
