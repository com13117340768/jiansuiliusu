package com.yy.core.ui.recycler;

import android.support.annotation.ColorInt;

import com.choices.divider.DividerItemDecoration;

/**
 * Created by ft on 2018/8/16.
 */
public class BaseDecoration extends DividerItemDecoration {
    public BaseDecoration() {
    }

    private BaseDecoration(@ColorInt int color, int size) {
        setDividerLookup(new DividerLookupImpl(color, size));
    }

    public static BaseDecoration create(@ColorInt int color, int size) {
        return new BaseDecoration(color, size);
    }

}
