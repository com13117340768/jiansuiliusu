package com.ayunyi.mssyy.rw.main.discover;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ayunyi.mssyy.rw.R;
import com.yy.core.fragments.RedWineFragment;

/**
 * Created by ft on 2018/10/11.
 */
public class HttpUrlJump extends RedWineFragment {
    @Override
    public Object setLayout() {
        return R.layout.fragment_httpurl;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        WebView webView = rootView.findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.setDrawingCacheEnabled(true);
        webView.loadUrl("http://www.baidu.com");
    }
}
