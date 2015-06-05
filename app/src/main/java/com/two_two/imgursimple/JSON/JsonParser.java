package com.two_two.imgursimple.JSON;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.two_two.imgursimple.ImgurSimple;
import com.two_two.imgursimple.volley.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by DmitryBorodin on 04.06.2015.
 */
public class JsonParser {
    private static ArrayList<String> listUrlPictures = new ArrayList<>();
    private static VolleySingleton volleySingleton = VolleySingleton.getInstance();
    private static RequestQueue requestQueue = volleySingleton.getRequestQueue();

    {
    }
    public JsonParser() {
        requestQueue = volleySingleton.getRequestQueue();
        volleySingleton = VolleySingleton.getInstance();
    }

    public static ArrayList<String> sendJsonRequest() {
        JSONObject response = null;

        RequestFuture<JSONObject> requestFuture = RequestFuture.newFuture();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                ImgurSimple.urlJson,
                (String)null, requestFuture, requestFuture);

        requestQueue.add(request);
        Log.e(ImgurSimple.TAG, "request added");  //TODO
        try {
            response = requestFuture.get(30000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            Log.d(ImgurSimple.TAG, e.getMessage());
            e.printStackTrace();
        } catch (ExecutionException e) {
            Log.d(ImgurSimple.TAG, e.getMessage());
        } catch (TimeoutException e) {
            Log.d(ImgurSimple.TAG, e.getMessage());
            e.printStackTrace();
        }
        Log.e(ImgurSimple.TAG, "before parse");  //TODO
        listUrlPictures = parseJsonResponse(response);
        return listUrlPictures;
    }

    private static ArrayList<String> parseJsonResponse(JSONObject response) {

        if(response == null || response.length() > 0){
        }
        try {
/*            Here we will sawe what we need from JSON. Just links to pictures in our case.*/
            JSONArray arrayPictures = null;
            if (response != null) {
                arrayPictures = response.getJSONArray("images");
                for (int i = 0; i < arrayPictures.length(); i++) {
                    JSONObject currentArticle = arrayPictures.getJSONObject(i);
                    String pictureLink = currentArticle.getString("link");
                    Log.e(ImgurSimple.TAG, pictureLink);  //TODO
                    listUrlPictures.add(pictureLink);
                }
            }

        } catch (JSONException e) {
            //this is test application so i don't handle unstable behaviour. DB
        }
        return listUrlPictures;
    }
}
