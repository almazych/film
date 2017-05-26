package com.almaz.myapp1;

import android.app.FragmentManager;
import android.os.Bundle;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class FilmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.film_item);

        Toolbar toolbar = (Toolbar) findViewById(R.id.film_activity_toolbar);
        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);
        FilmFragment fragment = new FilmFragment();

        String f = getIntent().getStringExtra("IDFILM");
        Bundle arg = new Bundle();
        arg.putString("tag", f);
        fragment.setArguments(arg);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        fragmentTransaction.add(R.id.film_container, fragment).commit();



    }
}