package com.two_two.imgursimple.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.two_two.imgursimple.R;


public class MainActivity extends AppCompatActivity {
private AdapterForPictures adapterForPictures;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void onButtonGoForImages(View view) {
        //url change
        Intent intent = new Intent(this,PictureActivity.class);
        startActivity(intent);


    }
}

