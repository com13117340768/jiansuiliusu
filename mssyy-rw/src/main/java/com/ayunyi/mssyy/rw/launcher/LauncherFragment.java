package com.ayunyi.mssyy.rw.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.ayunyi.mssyy.rw.R;
import com.ayunyi.mssyy.rw.R2;
import com.yy.core.app.AccountManager;
import com.yy.core.app.IUserChecker;
import com.yy.core.fragments.RedWineFragment;

import java.text.MessageFormat;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ft on 2018/9/21.
 */
public class LauncherFragment extends RedWineFragment {


    @BindView(R2.id.launcher_timer)
    AppCompatTextView textView = null;


    @OnClick(R2.id.launcher_timer)
    void SkipFragment() {
        ExitTimer();
        checkIsShowLogin();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    private ILauncherListener listener = null;
    private CountDownTimer countDownTimer = null;

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) listener = (ILauncherListener) activity;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

        countDownTimer = new CountDownTimer(5 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (getActivity() != null) {
                    String text = String.valueOf(millisUntilFinished / 1000);
                    textView.setText(MessageFormat.format("{0}秒\b|\b跳过", text));
                }
            }

            @Override
            public void onFinish() {
                ExitTimer();
                checkIsShowLogin();
            }
        };
        countDownTimer.start();
    }

    @Override
    public void onDestroy() {
        ExitTimer();
        super.onDestroy();
    }


    private void ExitTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
    }

    private void checkIsShowLogin() {
        AccountManager.checkAccount(new IUserChecker() {
            @Override
            public void onSignIn() {
                if (listener != null)
                    listener.OnLauncherFinish(OnLauncherFinishTag.LOGINED);
            }

            @Override
            public void onNotSignIn() {
                if (listener != null)
                    listener.OnLauncherFinish(OnLauncherFinishTag.NOT_LOGNED);
            }
        });

    }


}
