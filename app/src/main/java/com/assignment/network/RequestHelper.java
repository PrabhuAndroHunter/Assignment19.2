package com.assignment.network;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.assignment.application.Application;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prabhu on 13/03/2018.
 */

public class RequestHelper {
    private final static String TAG = RequestHelper.class.toString();

    public static void getMovieInfo(final ResponseListener listner) {
        final List <JSONObject> movieObj = new ArrayList <JSONObject>();
        final String URL = Url.GET_MOVIE;
        Log.d(TAG, "get weather: " + URL);
        JsonObjectRequest movieReq = new JsonObjectRequest(Request.Method.GET,
                URL, null, new Response.Listener <JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
                movieObj.add(response);

                // Call ResponseParser to parse the response
                Log.d(TAG, "onResponse: ");
                ResponseParser.parseMovieInfo(movieObj, listner);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: ");
                listner.searchFail(error.getMessage());
            }
        });
        Application.getInstance().addToRequestQueue(movieReq);
    }
}
