package com.ayunyi.mssyy.rw.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.ayunyi.mssyy.rw.R;
import com.yy.core.fragments.RedWineFragment;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by ft on 2018/9/22.
 */
public class CheckSignFragment extends RedWineFragment {


    private String mPhone = null;

    @Override
    public Object setLayout() {
        return R.layout.fragment_check_sign;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        String phone = getArguments().getString("PHONE");
        if (phone != null)
            mPhone = phone;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {













    }

    @Override
    public void onSupportVisible() {
        Toast.makeText(getContext(), mPhone, Toast.LENGTH_SHORT).show();
        super.onSupportVisible();
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
