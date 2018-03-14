package com.assignment.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.assignment.R;
import com.assignment.model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prabhu on 13/3/18.
 */

public class MovieAdapter extends RecyclerView.Adapter <MovieAdapter.MyViewHolder> {
    private List <Movie> movieList = new ArrayList <Movie>();

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.mName.setText(movie.getName());
        holder.mId.setText(""+movie.getId());
        holder.mVote.setText(""+movie.getVote_count());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mName, mId, mVote;
        ;


        public MyViewHolder(View view) {
            super(view);
            mName = (TextView) view.findViewById(R.id.textview_name);
            mId = (TextView) view.findViewById(R.id.textview_id);
            mVote = (TextView) view.findViewById(R.id.textview_vote);

        }
    }

    public void refreshUI(ArrayList <Movie> movieList) {
        this.movieList = movieList;
        this.notifyDataSetChanged();
    }
}
