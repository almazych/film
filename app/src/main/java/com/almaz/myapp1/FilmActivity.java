package com.almaz.myapp1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class FilmActivity extends AppCompatActivity {

    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.film_item);

        title = (TextView)findViewById(R.id.film_name);

        title.setText(getIntent().getStringExtra("EXTRA_ID"));
    }
}
