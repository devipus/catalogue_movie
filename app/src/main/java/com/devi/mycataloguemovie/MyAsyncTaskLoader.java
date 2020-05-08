package com.devi.mycataloguemovie;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MyAsyncTaskLoader extends AsyncTaskLoader<ArrayList<MovieItems>> {
    private ArrayList<MovieItems> mData;
    private boolean mHasResult = false;

    private String mKumpulanMovie;

    public MyAsyncTaskLoader(final Context context, String kumpulanMovie){
        super(context);
        onContentChanged();
        this.mKumpulanMovie = kumpulanMovie;
    }

    @Override
    protected void onStartLoading(){
        if (takeContentChanged())
            forceLoad();
        else if (mHasResult)
            deliverResult(mData);
    }

    @Override
    public void deliverResult(final ArrayList<MovieItems> data){
        mData = data;
        mHasResult = true;
        super.deliverResult(data);
    }

    @Override
    protected void onReset(){
        super.onReset();
        onStartLoading();
        if (mHasResult){
            onReleaseResources(mData);
            mData = null;
            mHasResult = false;
        }
    }


    @Override
    public ArrayList<MovieItems> loadInBackground(){
        SyncHttpClient client = new SyncHttpClient();

        final ArrayList<MovieItems> movieItemses = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/search/movie?api_key=b929aa102cc3b1718f4a5f375a0b149b&language=en-US&query="+mKumpulanMovie;
        client.get(url, new AsyncHttpResponseHandler() {

            @Override
            public void onStart(){
                super.onStart();
                setUseSynchronousMode(true);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String hasil = new String(responseBody);
                    JSONObject responseObject = new JSONObject(hasil);
                    JSONArray results = responseObject.getJSONArray("results");

                    for (int i = 0; i < results.length(); i++){
                        JSONObject movie = results.getJSONObject(i);
                        MovieItems movieItems = new MovieItems(movie);
                        movieItemses.add(movieItems);
                    }

                } catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                //jiks response gagal maka, do nothing
            }
        });

        return movieItemses;
    }

    protected void onReleaseResources(ArrayList<MovieItems> data){
        //nothing to do
    }
}
