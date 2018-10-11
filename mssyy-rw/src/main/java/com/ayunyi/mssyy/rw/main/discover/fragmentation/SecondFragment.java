package com.ayunyi.mssyy.rw.main.discover.fragmentation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.ayunyi.mssyy.rw.R;
import com.yy.core.fragments.RedWineFragment;
import com.yy.core.fragments.bottom.BottomItemFragment;

/**
 * Created by ft on 2018/10/11.
 */
public class SecondFragment extends RedWineFragment {
    @Override
    public Object setLayout() {
        return R.layout.zhihu_fragment_second;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getSupportDelegate().loadRootFragment(R.id.fl_second_container, ViewPagerFragment.newInstance());
    }

    @Override
    public boolean onBackPressedSupport() {
        getSupportDelegate().pop();
        return true;
    }
}
