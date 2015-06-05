package com.two_two.imgursimple;

/**
 * Created by DmitryBorodin on 04.06.2015.
 * This is basic class for project constants and so on.
 */
public class ImgurSimple {
    public static final String TAG = "DmitryTag";
    //This is URL for imgur that returns JSON for pictures within particular gallery. gallery ID needed at the end of URL.
    public volatile static String urlJson = "http://api.imgur.com/3/gallery/o0pr3"; //TODO delete groupID from here
}
