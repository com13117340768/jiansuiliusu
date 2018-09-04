package com.yy.core.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.yy.core.app.Latte;

public class DimenUtil {


    public static int getScreenWidth() {
        final Resources resources=Latte.getApplicationContext().getResources();
        DisplayMetrics displayMetrics=resources.getDisplayMetrics();
        return displayMetrics.widthPixels;
    }
    public static int getScreenHeight() {
        final Resources resources=Latte.getApplicationContext().getResources();
        DisplayMetrics displayMetrics=resources.getDisplayMetrics();
        return displayMetrics.heightPixels;
    }
}
