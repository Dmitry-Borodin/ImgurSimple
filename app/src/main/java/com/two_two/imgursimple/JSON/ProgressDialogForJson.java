package com.two_two.imgursimple.JSON;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by DmitryBorodin on 04.06.2015.
 * This will show LOADING bar while getting JSON.
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