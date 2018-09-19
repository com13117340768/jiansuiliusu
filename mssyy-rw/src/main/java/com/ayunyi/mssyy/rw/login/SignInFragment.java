package com.ayunyi.mssyy.rw.login;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.ayunyi.mssyy.rw.R;
import com.ayunyi.mssyy.rw.R2;
import com.ayunyi.mssyy.rw.main.EcBottomFragment;
import com.joanzapata.iconify.widget.IconTextView;
import com.orhanobut.logger.Logger;
import com.yy.core.fragments.RedWineFragment;
import com.yy.core.net.RestClient;
import com.yy.core.net.callback.IError;
import com.yy.core.net.callback.IFailure;
import com.yy.core.net.callback.ISuccess;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ft on 2018/9/18.
 */
public class SignInFragment extends RedWineFragment {

    //退出登录界面
    @BindView(R2.id.icon_exit)
    IconTextView iconTextView_exit = null;

    //客服
    @BindView(R2.id.icon_sign_kef)
    AppCompatTextView textView_kf = null;


    //输入账号
    @BindView(R2.id.edit_sign_in_email)
    AppCompatEditText editText_name = null;

    //输入密码
    @BindView(R2.id.edit_sign_in_password)
    AppCompatEditText editText_password = null;

    //登录button
    @BindView(R2.id.btn_sign_in)
    AppCompatButton loginButton = null;


    //短信注册登录
    @BindView(R2.id.tv_msg_sign)
    AppCompatTextView textView_msg = null;

    //普通注册
    @BindView(R2.id.tv_sing_reg)
    AppCompatTextView textView_reg = null;

    //微信登录
    @BindView(R2.id.icon_sign_in_wx)
    IconTextView iconTextView_wechat_login = null;

    //QQ登录
    @BindView(R2.id.icon_sign_in_qq)
    IconTextView iconTextView_qq_login = null;

    ISignListener signListener = null;


    //退出的点击事件
    @OnClick(R2.id.icon_exit)
    void ExitClick() {
        AlertToast("退出的点击事件");
    }

    //客服按钮的点击事件
    @OnClick(R2.id.icon_sign_kef)
    void KfClick() {
        AlertToast("客服按钮的点击事件");
    }

    Handler handler = new Handler();


    //用户点击登录事件
    @OnClick(R2.id.btn_sign_in)
    void LoginButtonClick() {
        if (checkFrom()) {
            RestClient.builder()
                    .loader(getContext())
                    .url("shop_cart_count.php")
                    .params("email", editText_name.getText().toString())
                    .params("password", editText_password.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            Logger.json(response);
                            AlertToast("登录成功！");
                        //    SignHandler.onSignIn(signListener);

                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    getSupportDelegate().start(new EcBottomFragment());
                                }
                            }) ;

                        }
                    })
                    .error(new IError() {
                        @Override
                        public void onError(int code, String msg) {
                            AlertToast("登录失败！" + code + "\\n" + msg);
                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {
                            AlertToast("登录失败！");
                        }
                    })
                    .build()
                    .post();


        }
    }

    //短信的普通注册按钮
    @OnClick(R2.id.tv_msg_sign)
    void MsgRegClick() {
        AlertToast("短信的普通注册按钮");

    }

    //普通注册-登录的点击事件
    @OnClick(R2.id.tv_sing_reg)
    void RegClick() {
        getSupportDelegate().start(new SignUpFragment());
    }

    //微信登录
    @OnClick(R2.id.icon_sign_in_wx)
    void WeChatLoginClick() {
        AlertToast("微信登录");
    }

    //QQ登录
    @OnClick(R2.id.icon_sign_in_qq)
    void QQLoginClick() {
        AlertToast("QQ登录");
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            signListener = (ISignListener) activity;
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
    }


    private void AlertToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }


    private boolean checkFrom() {
        String name = editText_name.getText().toString().trim();
        String password = editText_password.getText().toString().trim();
        boolean isValid = true;
        if (!TextUtils.isEmpty(name)) {
            if (name.length() < 2) {
                editText_name.setError("用户名不能小于2位数");
                isValid = false;
            } else if (name.contains("@") && name.endsWith(".com")) {
                if (!Patterns.EMAIL_ADDRESS.matcher(name).matches()) {
                    editText_name.setError("错误的邮箱格式");
                    isValid = false;
                }
            }
        } else {
            isValid = false;
            editText_name.setError("用户名不能为空");
        }

        if (!TextUtils.isEmpty(password)) {
            if (password.length() < 6) {
                isValid = false;
                editText_password.setError("密码不能小于6位数");
            }
        } else {
            isValid = false;
            editText_password.setError("密码不能为空");
        }
        return isValid;
    }


}
