    package com.almaz.myapp1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity implements MainFragment.FilmClicked {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_activity_toolbar);
        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);

        ProgressBar progressBar  = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
    }


    @Override
    public void sendIdFilm(String filmId) {

        Boolean tabletMode = findViewById(R.id.fragment_container) != null;

        FilmFragment fragment = new FilmFragment();

        if (tabletMode){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,fragment)
                    .commit();
        } else {
            Intent i = new Intent(this,FilmActivity.class);
            startActivity(i);
        }
            fragment.updateText(filmId);
    }

}