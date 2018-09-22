package com.ayunyi.mssyy.rw.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Toast;

import com.ayunyi.mssyy.rw.R;
import com.ayunyi.mssyy.rw.R2;
import com.yy.core.fragments.RedWineFragment;
import com.yy.core.net.RestClient;
import com.yy.core.net.callback.IError;
import com.yy.core.net.callback.IFailure;
import com.yy.core.net.callback.ISuccess;

import butterknife.BindView;
import butterknife.OnClick;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by ft on 2018/9/22.
 */
public class PhoneLoginFragment extends RedWineFragment {


    private String phone = null;

    @BindView(R2.id.edit_phone)
    TextInputEditText inputEditText = null;

    @BindView(R2.id.btn_sign_in)
    AppCompatButton compatButton = null;


    @OnClick(R2.id.icon_po_return)
    void BackClick() {
        getSupportDelegate().start(new LoginFragment());
    }


    @OnClick(R2.id.btn_sign_in)
    void SubmitClick() {
        if (checkFrom()) {
            RestClient.builder()
                    .loader(getContext())
                    .url("baidu_image.php")
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            CheckSignFragment fragment = new CheckSignFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString("PHONE", getPhone());
                            fragment.setArguments(bundle);
                            getSupportDelegate().start(fragment);
                        }
                    })
                    .error(new IError() {
                        @Override
                        public void onError(int code, String msg) {
                            AlertToast("提交失败！" + code + "\\n" + msg);
                        }

                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {
                            AlertToast("登录失败！");
                        }
                    })
                    .build()
                    .get();
        }
    }

    public String getPhone() {
        return phone == null ? "" : phone;
    }


    @Override
    public Object setLayout() {
        return R.layout.fragment_phone_login;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }


    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }


    private boolean checkFrom() {
        boolean flag = true;
        String text = inputEditText.getText().toString().trim();
        if (text.length() < 11) {
            inputEditText.setError("手机号码格式有误!");
            return false;
        }
        phone = text;
        return true;
    }

    private void AlertToast(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }


}
