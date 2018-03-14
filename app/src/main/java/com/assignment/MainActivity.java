package com.assignment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.assignment.adapter.MovieAdapter;
import com.assignment.model.Movie;
import com.assignment.network.RequestHelper;
import com.assignment.network.ResponseListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ResponseListener {
    private RecyclerView mMovieList;
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // init layout
        setContentView(R.layout.activity_main);
        // create instance of adapter
        movieAdapter = new MovieAdapter();
        // init recycler view
        mMovieList = (RecyclerView) findViewById(R.id.recycler_View_movie_list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mMovieList.setLayoutManager(mLayoutManager);
        mMovieList.setItemAnimator(new DefaultItemAnimator());
        mMovieList.addItemDecoration(new RecyclerViewItemDecorator(this, 110));
        // set adapter
        mMovieList.setAdapter(movieAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // query for movie info
        if (getNetStatus()) {
            RequestHelper.getMovieInfo(this);
        } else {
            Toast.makeText(this, "Please check your Internet Connection!", Toast.LENGTH_SHORT).show();
        }

    }

    // this method will call when movie infpo found and parse info successfully
    @Override
    public void searchDone(Object object) {
        movieAdapter.refreshUI((ArrayList <Movie>) object);
    }

    @Override
    public void searchFail(String error) {
        Toast.makeText(this, "Movie Info Not Found!!", Toast.LENGTH_SHORT).show();
    }

    // This method will return true is device connected to internet otherwise return false
    public boolean getNetStatus() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        if (activeNetwork != null) { // connected to the internet
            return true;
        }
        return false;
    }
}
