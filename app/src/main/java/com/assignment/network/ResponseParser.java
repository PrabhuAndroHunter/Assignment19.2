package com.assignment.network;

import android.util.Log;

import com.assignment.model.Movie;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prabhu on 13/03/2018.
 */

public class ResponseParser {
    private final static String TAG = ResponseParser.class.toString();

    // This method is used for parse stations data
    protected static void parseMovieInfo(List <JSONObject> response, ResponseListener listener) {
        List <Movie> moviesList = new ArrayList <Movie>();
        try {
            for (JSONObject resp : response) {
                JSONArray arr = resp.getJSONArray("results");
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject jobj = arr.getJSONObject(i);
                    Movie movie = new Movie(jobj.getInt("id"), jobj.getInt("vote_count"), jobj.getString("name"));
                    moviesList.add(movie);
                }
            }
            listener.searchDone(moviesList);
            Log.d(TAG, "Total parsed : " + moviesList.size());
        } catch (Exception e) {
            Log.e(TAG, "parseWeatherInfo: " + e.getMessage());
            listener.searchFail(e.getMessage());
        }
    }
}
