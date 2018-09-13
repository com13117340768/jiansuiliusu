package com.yy.core.ui.recycler;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by ft on 2018/8/15.
 */
public class MultipleViewHolder extends BaseViewHolder{

    private MultipleViewHolder(View view) {
        super(view);
    }
    public static MultipleViewHolder create(View view) {
        return new MultipleViewHolder(view);
    }
}
