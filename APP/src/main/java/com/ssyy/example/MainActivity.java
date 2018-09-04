package com.ssyy.example;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ayunyi.mssyy.rw.main.EcBottomDelegate;
import com.yy.core.activities.ProxyActivity;
import com.yy.core.fragments.LatteFragment;


public class MainActivity extends ProxyActivity {


    @Override
    public LatteFragment setRootDelegate() {
        return new EcBottomDelegate();
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
