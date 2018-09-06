package com.ssyy.example;

import android.app.Application;

import com.ayunyi.mssyy.rw.icon.FontECModule;
import com.ayunyi.mssyy.rw.icon.FontRWModule;
import com.ft.example.R;
import com.hss01248.dialog.StyledDialog;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.yy.core.app.Latte;
import com.yy.core.net.interceptors.DebugInterceptor;

public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                //    .withApiHost("http://mock.fulingjie.com/mock/api/")
                .withApiHost("http://192.168.2.103:8080/RestServer/api/")
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontECModule())
                .withIcon(new FontRWModule())
                .withLoaderDelayed(500)
                .withInterceptor(new DebugInterceptor("test", R.raw.fengtao))
                .configure();
        StyledDialog.init(this);//长按选择删除弹框
    }
}
