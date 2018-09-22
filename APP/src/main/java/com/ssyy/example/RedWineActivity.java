package com.ssyy.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.ayunyi.mssyy.rw.launcher.ILauncherListener;
import com.ayunyi.mssyy.rw.launcher.LauncherFragment;
import com.ayunyi.mssyy.rw.launcher.OnLauncherFinishTag;
import com.ayunyi.mssyy.rw.login.FromWhereOnLoginTag;
import com.ayunyi.mssyy.rw.login.ISignListener;
import com.ayunyi.mssyy.rw.login.LoginFragment;
import com.ayunyi.mssyy.rw.main.EcBottomFragment;
import com.yy.core.activities.ProxyActivity;
import com.yy.core.app.IUserChecker;
import com.yy.core.fragments.RedWineFragment;


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
                getSupportDelegate().startWithPop(new EcBottomFragment());
                break;
            case USER:
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
                getSupportDelegate().startWithPop(new EcBottomFragment());
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
