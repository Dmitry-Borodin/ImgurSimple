package com.two_two.imgursimple.json;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.two_two.imgursimple.ImgurSimple;
import com.two_two.imgursimple.ui.AdapterForPictures;
import com.two_two.imgursimple.volley.MyApplication;
import com.two_two.imgursimple.volley.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by DmitryBorodin on 04.06.2015.
 */
public class JsonParser {
    private static VolleySingleton volleySingleton = VolleySingleton.getInstance();
    private static RequestQueue requestQueue = volleySingleton.getRequestQueue();

    {
    }
    public JsonParser() {
        requestQueue = volleySingleton.getRequestQueue();
        volleySingleton = VolleySingleton.getInstance();
    }

    public static void sendJsonRequest(final AdapterForPictures adapterForPictures, final ArrayList<String> listUrlPictures) {

        Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(ImgurSimple.TAG, "i get response");
                if (response == null || response.length() <= 0) {
                    Log.e(ImgurSimple.TAG, "Response is NULL!!!");
                }
                try {
            /*Here we will sawe what we need from JSON. Just links to pictures in our case.*/
                    if (response != null) {
                        JSONArray arrayPictures = response.getJSONObject("data").getJSONArray("images");
                        for (int i = 0; i < arrayPictures.length(); i++) {
                            JSONObject currentArticle = arrayPictures.getJSONObject(i);
                            String pictureLink = currentArticle.getString("link");
                            listUrlPictures.add(pictureLink);
                        }
                    }

                } catch (JSONException e) {
                    //this is test application so i don't handle unstable behaviour. DB
                    e.printStackTrace();
                    Toast.makeText(MyApplication.getAppContext(),
                            "Ошибка. Наверное в указанном альбоме нет картинок",    //TODO
                            Toast.LENGTH_LONG).show();
                }
                Log.e(ImgurSimple.TAG, "notified");
                adapterForPictures.notifyDataSetChanged();
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(ImgurSimple.TAG, "Error: " + error.getMessage());
                Toast.makeText(MyApplication.getAppContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, ImgurSimple.urlJson, listener, errorListener);


        requestQueue.add(jsonObjReq);

  /*JSONObject response = null;

        RequestFuture<JSONObject> requestFuture = RequestFuture.newFuture();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, ImgurSimple.urlJson, requestFuture, requestFuture);

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
        Log.e(ImgurSimple.TAG, "before return"+listUrlPictures.toString());
        return listUrlPictures;
    }

    private static ArrayList<String> parseJsonResponse(JSONObject response) {
        Log.d(ImgurSimple.TAG, "i get response");
        if (response == null || response.length() <= 0) {
            Log.e(ImgurSimple.TAG, "Response is NULL!!!");
        }
        try {
            *//*            Here we will sawe what we need from JSON. Just links to pictures in our case.*//*
            JSONArray arrayPictures = null;
            if (response != null) {
                arrayPictures = response.getJSONObject("data").getJSONArray("images");
                for (int i = 0; i < arrayPictures.length(); i++) {
                    JSONObject currentArticle = arrayPictures.getJSONObject(i);
                    String pictureLink = currentArticle.getString("link");
                    listUrlPictures.add(pictureLink);
                }
            }

        } catch (JSONException e) {
            //this is test application so i don't handle unstable behaviour. DB
            e.printStackTrace();
            Toast.makeText(MyApplication.getAppContext(),
                    "В указанном альбоме нет картинок",
                    Toast.LENGTH_LONG).show();
        }*/
    }
}
