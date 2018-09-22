package com.ayunyi.mssyy.rw.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.View;

import com.ayunyi.mssyy.rw.R;
import com.ayunyi.mssyy.rw.R2;
import com.joanzapata.iconify.widget.IconTextView;
import com.yy.core.fragments.RedWineFragment;

import butterknife.BindView;
import butterknife.OnClick;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by ft on 2018/9/20.
 */
public class PhoneRegFragment extends RedWineFragment{

    @BindView(R2.id.icon_po_return)
    IconTextView iconTextView = null;

    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mEditTextphone = null;

    @BindView(R2.id.edit_msg_sign)
    TextInputEditText mEditTextMsgSign = null;

    @BindView(R2.id.edit_set_password)
    TextInputEditText mEditTextSetPass = null;


    @OnClick(R2.id.bt_submit_phone)
    void SubmitPhoneClick(){

    }



    @OnClick(R2.id.icon_po_return)
    void BackClick(){
        getSupportDelegate().start(new RegisterFragment());
    }




    @Override
    public Object setLayout() {
        return R.layout.delegate_login_phone_register;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
