package com.devi.mycataloguemovie;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MovieAdapter extends BaseAdapter {

    private ArrayList<MovieItems> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context context;



    public MovieAdapter(Context context){
        this.context = context;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(ArrayList<MovieItems> items){
        mData = items;
        notifyDataSetChanged();
    }

    public void addItem(final MovieItems item){
        mData.add(item);
        notifyDataSetChanged();
    }

    public void clearData(){
        mData.clear();
    }

    @Override
    public int getItemViewType(int position){
        return 0;
    }

    @Override
     public int getViewTypeCount(){
        return 1;
    }

    @Override
    public int getCount() {
        if (mData == null) return 0;
        return mData.size();
    }

    @Override
    public MovieItems getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.activity_movie_items, null);
            holder.textViewJudulMovie = (TextView)convertView.findViewById(R.id.textMovie);
            holder.textViewDescription = (TextView)convertView.findViewById(R.id.textDesc);
            holder.textViewTanggal = (TextView)convertView.findViewById(R.id.textTanggal);
            holder.imageViewPoster = (ImageView)convertView.findViewById(R.id.poster);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textViewJudulMovie.setText(mData.get(position).getJudul());
        holder.textViewDescription.setText(mData.get(position).getDescription());
        holder.textViewTanggal.setText(mData.get(position).getTanggal());
        Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500/"+mData.get(position).getPoster_path())
                .into(holder.imageViewPoster);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MovieDetailsActivity.class);
                intent.putExtra("poster_path", mData.get(position).getPoster_path());
                intent.putExtra("title", mData.get(position).getJudul());
                intent.putExtra("release_date", mData.get(position).getTanggal());
                intent.putExtra("overview", mData.get(position).getDescription());

                context.startActivity(intent);
            }
        });

        return convertView;
    }

    private static class ViewHolder {
        TextView textViewJudulMovie;
        TextView textViewDescription;
        TextView textViewTanggal;
        ImageView imageViewPoster;
    }
}

