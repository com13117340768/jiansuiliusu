package com.ayunyi.mssyy.rw.main.discover;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import com.ayunyi.mssyy.rw.R;
import com.ayunyi.mssyy.rw.R2;
import com.yy.core.fragments.bottom.BottomItemFragment;

import butterknife.BindView;

/**
 * Created by ft on 2018/8/21.
 */
public class DiscoverFragment extends BottomItemFragment {

    @BindView(R2.id.toolbar_discover)
    Toolbar mToolbar = null;
    @BindView(R2.id.dc_tv)
    AppCompatTextView dcTv;
    @BindView(R2.id.web_discover_container)
    FrameLayout webDiscoverContainer;

    @Override
    public Object setLayout() {
        return R.layout.delegate_discover;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
    }

}
