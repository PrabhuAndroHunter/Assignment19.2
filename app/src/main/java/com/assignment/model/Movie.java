package com.assignment.model;

/**
 * Created by prabhu on 13/3/18.
 */

public class Movie {
    int id, vote_count;
    String name;

    public Movie(int id, int vote_count, String name) {
        this.id = id;
        this.vote_count = vote_count;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getVote_count() {
        return vote_count;
    }

    public String getName() {
        return name;
    }
}
