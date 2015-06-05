package com.two_two.imgursimple.json;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by DmitryBorodin on 04.06.2015.
 * This will show LOADING bar while pictureloading.
 * JSONParsing and picture loading is in Fragments.
 */
public class ProgressDialogForJson {
    private static ProgressDialog pDialog;

    public static void pDialogProgress(Context context){
        pDialog = new ProgressDialog(context);
        pDialog.setMessage(context.getString(com.two_two.imgursimple.R.string.loading_message));
        pDialog.show();
    }
    public static void pDialogHide() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }

    }
}