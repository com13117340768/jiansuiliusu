package com.yy.core.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.yy.core.app.RedWine;

public class DimenUtil {


    public static int getScreenWidth() {
        final Resources resources= RedWine.getApplicationContext().getResources();
        DisplayMetrics displayMetrics=resources.getDisplayMetrics();
        return displayMetrics.widthPixels;
    }
    public static int getScreenHeight() {
        final Resources resources= RedWine.getApplicationContext().getResources();
        DisplayMetrics displayMetrics=resources.getDisplayMetrics();
        return displayMetrics.heightPixels;
    }
}
