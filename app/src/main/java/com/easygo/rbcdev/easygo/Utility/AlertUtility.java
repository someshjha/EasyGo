package com.easygo.rbcdev.easygo.Utility;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Created by rbcdev on 15-10-07.
 */
public class AlertUtility {

    public static void displayAlert(String message, Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message);
        builder.setCancelable(true);

        AlertDialog alert = builder.create();
        alert.show();
    }
}
