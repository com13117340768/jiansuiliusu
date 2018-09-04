package com.ayunyi.mssyy.rw.main.sort;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ayunyi.mssyy.rw.R;
import com.ayunyi.mssyy.rw.R2;
import com.ayunyi.mssyy.rw.main.sort.content.ContentDelegate;
import com.ayunyi.mssyy.rw.main.sort.list.VerticalListDelegate;
import com.yy.core.fragments.bottom.BottomItemDelegate;

import butterknife.BindView;

/**
 * Created by ft on 2018/8/13.
 */
public class SortDelegate extends BottomItemDelegate {

    @BindView(R2.id.sort_toolbar)
    Toolbar mToolbar = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        mToolbar.setBackgroundColor(this.getResources().getColor(com.yy.core.R.color.app_main_color));
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final VerticalListDelegate listDelegate = new VerticalListDelegate();
        getSupportDelegate().loadRootFragment(R.id.vertical_list_container, listDelegate);
        getSupportDelegate().loadRootFragment(R.id.sort_content_container, ContentDelegate.newInstance(1));
    }
}
