package com.yy.core.ui.Loader;

import android.content.Context;
import android.text.TextUtils;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

public final class LoaderCreator {
    private static final WeakHashMap<String, Indicator> LOADING_MAP = new WeakHashMap<>();

    protected static AVLoadingIndicatorView create(Context context, String type) {
        final AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(context);
        if (LOADING_MAP.get(type) == null) {
            final Indicator indicator = getIndicator(type);
            LOADING_MAP.put(type, indicator);
        }
        avLoadingIndicatorView.setIndicator(LOADING_MAP.get(type));

        return avLoadingIndicatorView;
    }


    private static Indicator getIndicator(String name) {
        if (TextUtils.isEmpty(name)) {
            return null;
        }
        final StringBuilder drawableClassName = new StringBuilder();
        if (!name.contains(".")) {
            final String dealPackName = AVLoadingIndicatorView.class.getPackage().getName();
            drawableClassName.append(dealPackName)
                    .append(".indicators.");
        }
        drawableClassName.append(name);
        try {
            final Class<?> drawableClass = Class.forName(drawableClassName.toString());
            return (Indicator) drawableClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
