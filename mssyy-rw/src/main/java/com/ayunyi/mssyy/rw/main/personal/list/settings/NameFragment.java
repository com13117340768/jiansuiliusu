package com.ayunyi.mssyy.rw.main.personal.list.settings;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.ayunyi.mssyy.rw.R;
import com.ayunyi.mssyy.rw.R2;
import com.yy.core.fragments.RedWineFragment;

import butterknife.BindView;
import butterknife.OnClick;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by ft on 2018/9/14.
 */
public class NameFragment extends RedWineFragment {

    @BindView(R2.id.btn_name_submit)
    AppCompatButton button = null;

    @BindView(R2.id.et_rename)
    AppCompatEditText editText = null;

    ISubmitReName iSubmitReName = null;

    @OnClick(R2.id.btn_name_submit)
    void OnClickSubmit() {
        String edText = editText.getText().toString();
        if (!TextUtils.isEmpty(edText) && !edText.trim().equals("")) {
            iSubmitReName.submitReName(edText);
            getSupportDelegate().pop();
        } else {
            Toast.makeText(getContext(), "输入格式不合法", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_name;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        button.setBackgroundColor(this.getResources().getColor(R.color.app_main_color));
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }


    public void setIReNameLister(ISubmitReName submitReName) {
        this.iSubmitReName = submitReName;
    }
}
