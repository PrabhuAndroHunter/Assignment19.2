package com.assignment.network;

/**
 * Created by prabhu on 13/03/2018.
 */

public interface ResponseListener {
     void searchDone(Object object);
     void searchFail(String error);
}
