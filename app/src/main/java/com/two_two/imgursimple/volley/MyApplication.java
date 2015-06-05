package com.two_two.imgursimple.volley;

import android.app.Application;
import android.content.Context;

/**
 * Created by DmitryBorodin on 04.06.2015.
 * This class needed for VolleySingleton
 */
public class MyApplication extends Application{
    private static MyApplication sInstance;

    @Override
    public void onCreate() {
        sInstance = this;
        super.onCreate();

    }
    public static MyApplication getInstance(){
        return sInstance;
    }
    public static Context getAppContext(){
        return sInstance.getApplicationContext();
    }
}
