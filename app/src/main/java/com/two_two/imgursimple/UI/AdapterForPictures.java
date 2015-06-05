package com.two_two.imgursimple.ui;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.two_two.imgursimple.volley.MyApplication;
import com.two_two.imgursimple.volley.VolleySingleton;

import java.util.ArrayList;

/**
 * Created by DmitryBorodin on 04.06.2015.
 * This adapter will fill pictures from imgur to pictures fragment.
 */
public class AdapterForPictures extends BaseAdapter{
    private Activity activity;
    private LayoutInflater inflater;
    private ArrayList<String> listUrlPictures;
    private VolleySingleton volleySingleton;
    private ImageLoader imageLoader;
    private String urlOfPicture;

    public AdapterForPictures(Activity activity, ArrayList<String> listUrlPictures) {
        this.activity = activity;
        this.listUrlPictures = listUrlPictures;
        volleySingleton = VolleySingleton.getInstance();
        imageLoader = volleySingleton.getImageLoader();
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return listUrlPictures.size();
    }

    @Override
    public Object getItem(int position) {
        return listUrlPictures.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null){
            inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

//        NetworkImageView picture = (NetworkImageView)convertView.findViewById(R.id.pictureView);
        NetworkImageView picture = new NetworkImageView(MyApplication.getAppContext());
        picture.setLayoutParams(new GridView.LayoutParams(200, 200));
        picture.setScaleType(NetworkImageView.ScaleType.CENTER_CROP);
        picture.setPadding(15,15,15,15);

        urlOfPicture = listUrlPictures.get(position);
        picture.setImageUrl(urlOfPicture, imageLoader);

        return picture;
    }
}
