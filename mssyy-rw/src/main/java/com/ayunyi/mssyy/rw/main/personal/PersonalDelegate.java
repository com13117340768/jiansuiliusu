package com.ayunyi.mssyy.rw.main.personal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;

import com.ayunyi.mssyy.rw.R;
import com.ayunyi.mssyy.rw.R2;
import com.yy.core.fragments.bottom.BottomItemDelegate;

import butterknife.BindView;

/**
 * Created by ft on 2018/8/22.
 */
public class PersonalDelegate extends BottomItemDelegate {

    @BindView(R2.id.rl_harder_bg)
    RelativeLayout relativeLayout = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_personal;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        relativeLayout.setBackgroundColor(this.getResources().getColor(R.color.app_main_color));
    }
}
