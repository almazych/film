    package com.almaz.myapp1;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements MainFragment.FilmClicked {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_activity_toolbar);
        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);
    }


    @Override
    public void onSendId(String filmId) {
        Boolean tabletMode = findViewById(R.id.fragment_container) != null;

        if (tabletMode) {
            FilmFragment fragment = new FilmFragment();

            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,fragment)
                    .commit();
        } else {
            Intent i = new Intent(this, FilmActivity.class);
            i.putExtra("IDFILM", filmId);
            startActivity(i);
        }
    }
}