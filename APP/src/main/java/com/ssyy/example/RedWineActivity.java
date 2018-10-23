package com.ssyy.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ayunyi.mssyy.rw.launcher.ILauncherListener;
import com.ayunyi.mssyy.rw.launcher.LauncherFragment;
import com.ayunyi.mssyy.rw.launcher.OnLauncherFinishTag;
import com.ayunyi.mssyy.rw.login.FromWhereOnLoginTag;
import com.ayunyi.mssyy.rw.login.ISignListener;
import com.ayunyi.mssyy.rw.login.LoginFragment;
import com.ayunyi.mssyy.rw.main.RedWineBottomFragment;
import com.ayunyi.mssyy.rw.main.personal.user.profile.PicturePreviewActivity;
import com.ft.example.R;
import com.yy.core.activities.ProxyActivity;
import com.yy.core.fragments.RedWineFragment;
import com.yy.core.util.toast.ToastUtils;


public class RedWineActivity extends ProxyActivity implements ISignListener, ILauncherListener {

    @Override
    public RedWineFragment setRootDelegate() {
        return new LauncherFragment();
    }

    @Override
    public void post(Runnable runnable) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSignInSuccess(FromWhereOnLoginTag tag) {
        switch (tag) {
            case INDEX:
                View textView = LayoutInflater.from(RedWineActivity.this).inflate(R.layout.toast_view, null);
                TextView textView1 = textView.findViewById(R.id.tv_toast);
                textView1.setText("登录成功...");

                ToastUtils toast = new ToastUtils(RedWineActivity.this, textView, 2);
                toast.show();
                getSupportDelegate().start(RedWineBottomFragment.getInstance(0));


                break;
            case USER:
                getSupportDelegate().start(RedWineBottomFragment.getInstance(4));
                break;
            default:
                break;
        }
    }

    @Override
    public void onSignUpSuccess() {
        AlertToast("注册成功");
    }

    @Override
    public void OnLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case LOGINED:
                getSupportDelegate().startWithPop(RedWineBottomFragment.getInstance(0));
                break;
            case NOT_LOGNED:
                getSupportDelegate().startWithPop(new LoginFragment());
                break;

            default:
                break;
        }
    }

    private void AlertToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
