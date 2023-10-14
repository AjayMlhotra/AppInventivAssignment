package com.appinventiv_assignment.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

public class AppUtils {
    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
    public static String isEmpty(String value){
        return !TextUtils.isEmpty(value) ? value : "";
    }
}
