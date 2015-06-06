package com.two_two.imgursimple.UI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;

import com.two_two.imgursimple.ImgurSimple;
import com.two_two.imgursimple.JSON.JsonParserGallery;
import com.two_two.imgursimple.R;
import com.two_two.imgursimple.JSON.JsonParserGroup;
import com.two_two.imgursimple.JSON.ProgressDialogForJson;
import com.two_two.imgursimple.volley.MyApplication;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private AdapterForPictures adapterForPictures;
    private static ArrayList<String> listUrlPictures = new ArrayList<>();
    private EditText groupIdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView myGrid = (GridView)findViewById(R.id.pictureGridMain);
        adapterForPictures = new AdapterForPictures(this, listUrlPictures);
        myGrid.setAdapter(adapterForPictures);

        //will be needed in case on Albom Button
        groupIdView = (EditText)findViewById(R.id.editText);

    }


    //
    public void onButtonAlbom(View view) {
        ImgurSimple.urlJson = MyApplication.getAppContext().getString(com.two_two.imgursimple.R.string.url_json_group_base)
                + groupIdView.getText();

        listUrlPictures.clear();
        ProgressDialogForJson.pDialogProgress(this);
        JsonParserGroup.sendJsonRequest(adapterForPictures, listUrlPictures);
        ProgressDialogForJson.pDialogHide();
    }

    public void onButtonGallery(View view) {
        ImgurSimple.urlJson = MyApplication.getAppContext().getString(com.two_two.imgursimple.R.string.url_json_gallery);

        listUrlPictures.clear();
        ProgressDialogForJson.pDialogProgress(this);
        JsonParserGallery.sendJsonRequest(adapterForPictures, listUrlPictures);
        ProgressDialogForJson.pDialogHide();
    }
}

