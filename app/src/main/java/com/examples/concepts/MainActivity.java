package com.examples.concepts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button tmdbButton, githubButton, enrichButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tmdbButton = findViewById(R.id.btn_TMDB);
        githubButton = findViewById(R.id.btn_Github);
//        enrichButton = findViewById(R.id.btn_enrich);

        tmdbButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, TmdbActivity.class)));
        githubButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, GithubActivity.class)));
//        enrichButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, EnrichActivity.class)));
    }

}