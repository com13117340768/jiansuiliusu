package com.ssyy.example;

import android.app.Application;

import com.ayunyi.mssyy.rw.database.DatabaseManager;
import com.ayunyi.mssyy.rw.icon.FontECModule;
import com.ayunyi.mssyy.rw.icon.FontRWModule;
import com.facebook.stetho.Stetho;
import com.ft.example.R;
import com.hss01248.dialog.StyledDialog;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.yy.core.app.RedWine;
import com.yy.core.net.interceptors.DebugInterceptor;

public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RedWine.init(this)
                //       .withApiHost("http://mock.fulingjie.com/mock/api/")
                .withApiHost("http://192.168.2.102:8080/RestServer/api/")
                //     .withApiHost("http://jiu.ayunyi.com/api/")
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontECModule())
                .withIcon(new FontRWModule())
                .withLoaderDelayed(500)
                .withInterceptor(new DebugInterceptor("test", R.raw.fengtao))
                .configure();
        StyledDialog.init(this);//长按选择删除弹框
    //    initStetho();
        DatabaseManager.getInstance().init(this);

    }

    @SuppressWarnings("SpellCheckingInspection")
    private void initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }
}
