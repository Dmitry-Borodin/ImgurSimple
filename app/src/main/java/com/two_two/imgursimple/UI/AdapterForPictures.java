package com.two_two.imgursimple.UI;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.two_two.imgursimple.R;
import com.two_two.imgursimple.volley.VolleySingleton;

import java.util.ArrayList;

/**
 * Created by DmitryBorodin on 04.06.2015.
 * This adapter will fill pictures from imgur to pictures fragment.
 */
public class AdapterForPictures extends BaseAdapter{
    private Activity activity;
    private LayoutInflater inflater;
    private ArrayList<String> listUrlPictures = new ArrayList<>();
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
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_picture, null);
        }

        NetworkImageView picture = (NetworkImageView)convertView.findViewById(R.id.pictureView);
        urlOfPicture = listUrlPictures.get(position);
        picture.setImageUrl(urlOfPicture, imageLoader);
        convertView.setTag(urlOfPicture);
        return convertView;
    }
}
