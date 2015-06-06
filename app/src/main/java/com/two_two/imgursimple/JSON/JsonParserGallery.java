package com.two_two.imgursimple.JSON;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.two_two.imgursimple.ImgurSimple;
import com.two_two.imgursimple.R;
import com.two_two.imgursimple.UI.AdapterForPictures;
import com.two_two.imgursimple.volley.MyApplication;
import com.two_two.imgursimple.volley.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by DmitryBorodin on 04.06.2015.
 * This parse default api for recent pictures. Sometimes i get error 400, so may be try register app sometimes.
 */
public class JsonParserGallery {
    private static VolleySingleton volleySingleton = VolleySingleton.getInstance();
    private static RequestQueue requestQueue = volleySingleton.getRequestQueue();

    public JsonParserGallery() {
        requestQueue = volleySingleton.getRequestQueue();
        volleySingleton = VolleySingleton.getInstance();
    }

    public static void sendJsonRequest(final AdapterForPictures adapterForPictures, final ArrayList<String> listUrlPictures) {

        Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (response == null || response.length() <= 0) {
                    Log.e(ImgurSimple.TAG, "Response is NULL!!!");
                }
                try {
            /*Here we will save what we need from JSON. Just links to pictures in our case.*/
                    if (response != null) {
                        JSONArray arrayPictures = response.getJSONArray("data");
                        String pictureLink = null;
                        for (int i = 0; i < arrayPictures.length(); i++) {
                            JSONObject currentjsonObject = arrayPictures.getJSONObject(i);
                            if (currentjsonObject.has("link")) pictureLink = currentjsonObject.getString("link");

                            //if current record exist and is picture, pictures has type field
                            if (pictureLink != null && currentjsonObject.has("type")) {
                                listUrlPictures.add(pictureLink);
                            }
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MyApplication.getAppContext(),
                            MyApplication.getAppContext().getString(R.string.error_no_images),
                            Toast.LENGTH_LONG).show();
                }
                Log.e(ImgurSimple.TAG, "notified");

                //After getting URL list, biting adapter to refresh pictures
                adapterForPictures.notifyDataSetChanged();
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(ImgurSimple.TAG, "Error: " + error.getMessage());
                Toast.makeText(MyApplication.getAppContext(),
                        MyApplication.getAppContext().getString(R.string.error_not_respond), Toast.LENGTH_SHORT).show();
            }
        };

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, ImgurSimple.urlJson, listener, errorListener);

        requestQueue.add(jsonObjReq);

    }
}
