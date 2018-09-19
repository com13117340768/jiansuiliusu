package com.ayunyi.mssyy.rw.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.ayunyi.mssyy.rw.R;
import com.yy.core.fragments.RedWineFragment;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by ft on 2018/8/18.
 */
public class GoodsDetailFragment extends RedWineFragment {


    public static GoodsDetailFragment create(){
        return new GoodsDetailFragment();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_goods_deteil;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
