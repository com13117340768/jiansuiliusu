package com.ayunyi.mssyy.rw.login;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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

import java.text.MessageFormat;

import butterknife.BindView;
import butterknife.OnClick;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by ft on 2018/9/21.
 */
public class RetrievePassFragment extends RedWineFragment implements TextWatcher {

    private CountDownTimer countDownTimer = null;

    private boolean isClickSign = false;

    @BindView(R2.id.edit_phone)
    TextInputEditText inputEditText_phone = null;

    @BindView(R2.id.exit_clean_phone)
    IconTextView ic_phone = null;

    @BindView(R2.id.edit_clean_sign)
    TextInputEditText inputEditText_sign = null;

    @BindView(R2.id.ic_clean_sign)
    IconTextView ic_sign = null;


    @BindView(R2.id.bt_regit_sign)
    AppCompatButton bt_sign_code = null;


    @BindView(R2.id.btn_sign_in)
    AppCompatButton bt_submit = null;


    @OnClick(R2.id.exit_clean_phone)
    void CleanPhoneClick() {
        inputEditText_phone.setText("");

    }

    @OnClick(R2.id.ic_clean_sign)
    void CleanSignClick() {
        inputEditText_sign.setText("");
    }

    @OnClick(R2.id.bt_regit_sign)
    void SignCodeClick() {

        if (checkFrom()) {
            // 请求服务器验证码

            isClickSign = true;

            RestClient.builder()
                    .loader(getContext())
                    .url("baidu_image.php")
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            Logger.json(response);
                            AlertToast("验证码获取成功！");


                            inputEditText_phone.setEnabled(false);
                            inputEditText_phone.setFocusable(false);
                            ic_phone.setVisibility(View.GONE);

                            bt_sign_code.setClickable(false);
                            bt_sign_code.setTextColor(getContext().getResources().getColor(R.color.app_main_color));
                            countDownTimer = new CountDownTimer(60 * 1000, 1000) {
                                @Override
                                public void onTick(long millisUntilFinished) {
                                    if (getActivity() != null) {
                                        String text = String.valueOf(millisUntilFinished / 1000);
                                        bt_sign_code.setText(MessageFormat.format("{0}秒后重新获取", text));
                                    }
                                }

                                @Override
                                public void onFinish() {
                                    ExitTimer();
                                    bt_sign_code.setText("获取验证码");
                                    bt_sign_code.setClickable(true);
                                    bt_sign_code.setTextColor(getContext().getResources().getColor(R.color.app_gay));
                                }
                            };
                            countDownTimer.start();

                        }
                    })
                    .error(new IError() {
                        @Override
                        public void onError(int code, String msg) {
                            AlertToast("验证码获取失败！" + code + "\\n" + msg);
                        }

                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {
                            AlertToast("验证码获取失败！");
                        }
                    })
                    .build()
                    .get();


        }

    }


    @OnClick(R2.id.btn_sign_in)
    void SubmitClick() {

        //进行网络操作,提交账号信息,打开重新获取验证码的开关,设置倒计时

        RestClient.builder()
                .loader(getContext())
                .url("baidu_image.php")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Logger.json(response);
                        AlertToast("提交成功！");
                        ExitTimer();
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


    @Override
    public Object setLayout() {
        return R.layout.delegate_retrieve_pass;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        inputEditText_phone.addTextChangedListener(this);
        inputEditText_sign.addTextChangedListener(this);
        bt_sign_code.setClickable(false);
        bt_submit.setClickable(false);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

        if (!isClickSign) {

            if (inputEditText_phone.getText().toString().trim().length() > 0) {
                ic_phone.setVisibility(View.VISIBLE);
                bt_sign_code.setClickable(true);
                bt_sign_code.setTextColor(this.getResources().getColor(R.color.app_main_color));
            } else {
                bt_sign_code.setClickable(false);
                ic_phone.setVisibility(View.GONE);
                bt_sign_code.setTextColor(this.getResources().getColor(R.color.app_gay));
            }

        }


        if (inputEditText_sign.getText().toString().trim().length() > 0) {
            ic_sign.setVisibility(View.VISIBLE);
            if (inputEditText_sign.getText().toString().trim().length() == 6) {
                bt_submit.setClickable(true);
                bt_submit.setBackgroundColor(this.getResources().getColor(R.color.app_main_color));
            }
        } else {
            ic_sign.setVisibility(View.GONE);
            bt_submit.setClickable(false);
            bt_submit.setBackgroundColor(this.getResources().getColor(R.color.app_main_color_none));
        }

    }


    private boolean checkFrom() {
        boolean flag = true;
        String text = inputEditText_phone.getText().toString().trim();
        if (text.length() < 11) {
            inputEditText_phone.setError("手机号码格式有误!");
            flag = false;
        }
        return flag;
    }

    private void AlertToast(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }


    private void ExitTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
    }

    @Override
    public void onDestroy() {
        ExitTimer();
        super.onDestroy();
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
