package me.wavever.ganklock.util;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import me.wavever.ganklock.R;

/**
 * Created by WAVE on 2016/2/22.
 */
public class DialogUtil {

    public static void showSingleDialog(Context context, String msg) {
        new AlertDialog.Builder(context)
                .setCancelable(true)
                .setMessage(msg)
                .setTitle(R.string.dialog_tip)
                .setPositiveButton(R.string.dialog_confirm,null)
                .show();
    }

    public static void showSingleDialog(Context context, int msgId) {
        new AlertDialog.Builder(context)
                .setCancelable(true)
                .setMessage(msgId)
                .setTitle(R.string.dialog_tip)
                .setPositiveButton(R.string.dialog_confirm,null)
                .show();
    }

}
