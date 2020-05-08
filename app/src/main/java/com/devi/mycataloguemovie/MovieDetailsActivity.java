package com.devi.mycataloguemovie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


public class MovieDetailsActivity extends AppCompatActivity {

    private ImageView imgPoster;
    private TextView textDate, textOverview, textJudul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        textDate = (TextView)findViewById(R.id.textDate);
        textDate.setText(getIntent().getStringExtra("release_date"));

        textJudul = (TextView)findViewById(R.id.textJudul);
        textJudul.setText(getIntent().getStringExtra("title"));

        textOverview = (TextView)findViewById(R.id.textOverview);
        textOverview.setText(getIntent().getStringExtra("overview"));

        imgPoster = (ImageView)findViewById(R.id.imgPoster);
        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500/"+ getIntent().getStringExtra("poster_path"))
                .into(imgPoster);



    }
}
