package com.ssyy.example;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ayunyi.mssyy.rw.login.SignInFragment;
import com.ayunyi.mssyy.rw.main.EcBottomFragment;
import com.yy.core.activities.ProxyActivity;
import com.yy.core.fragments.RedWineFragment;


public class MainActivity extends ProxyActivity {


    @Override
    public RedWineFragment setRootDelegate() {
        return new SignInFragment();
    }

    @Override
    public void post(Runnable runnable) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
