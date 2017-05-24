package com.almaz.myapp1;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

    Toolbar toolbar;

    FilmFragment fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.film_item);

        toolbar = (Toolbar) findViewById(R.id.film_activity_toolbar);
        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragment = new FilmFragment();
        fragmentTransaction.add(R.id.film_container, fragment).commit();

        Bundle bundle = new Bundle();
        bundle.putString("filmId", getIntent().getStringExtra(EXTRA_ID));
        fragment.setArguments(bundle);

    }
}
