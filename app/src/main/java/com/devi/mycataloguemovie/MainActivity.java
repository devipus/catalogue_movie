package com.devi.mycataloguemovie;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<MovieItems>>{

    ListView listView;
    MovieAdapter adapter;
    EditText editMovie;
    Button buttonCari;

    static final String EXTRAS_MOVIE = "EXTRAS_MOVIE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new MovieAdapter(this);
        adapter.notifyDataSetChanged();

        listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);

        editMovie = (EditText)findViewById(R.id.edit_movie);
        buttonCari = (Button)findViewById(R.id.btn_cari);
        buttonCari.setOnClickListener(myListener);

        String movie = editMovie.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRAS_MOVIE, movie);

        getSupportLoaderManager().initLoader(0, bundle, this);
    }


    @Override
    public Loader<ArrayList<MovieItems>> onCreateLoader(int id, Bundle args){
        String kumpulanMovie = "";
        if (args != null){
            kumpulanMovie = args.getString(EXTRAS_MOVIE);
        }

        return new MyAsyncTaskLoader(this, kumpulanMovie);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<MovieItems>> loader, ArrayList<MovieItems> data){
        adapter.setData(data);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<MovieItems>> loader){
        adapter.setData(null);
    }

    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String movie = editMovie.getText().toString();

            if (TextUtils.isEmpty(movie))return;

            Bundle bundle = new Bundle();
            bundle.putString(EXTRAS_MOVIE, movie);
            getSupportLoaderManager().restartLoader(0, bundle, MainActivity.this);
        }
    };
}
