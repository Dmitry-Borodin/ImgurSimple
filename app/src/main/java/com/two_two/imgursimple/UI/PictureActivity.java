package com.two_two.imgursimple.UI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.two_two.imgursimple.JSON.JsonParser;
import com.two_two.imgursimple.JSON.ProgressDialogForJson;
import com.two_two.imgursimple.R;

import java.util.ArrayList;

public class PictureActivity extends AppCompatActivity {
    private static ArrayList<String> listUrlPictures = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

    }

    @Override
    protected void onStart() {
        super.onStart();
        ProgressDialogForJson.pDialogProgress(this);
        listUrlPictures = JsonParser.sendJsonRequest();
        ProgressDialogForJson.hidePDialog();

        GridView myGrid = (GridView)findViewById(R.id.pictureGrid);
        myGrid.setAdapter(new AdapterForPictures(this, listUrlPictures));
    }
}
