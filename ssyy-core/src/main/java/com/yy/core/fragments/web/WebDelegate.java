package com.yy.core.fragments.web;

import android.webkit.WebView;

import com.yy.core.fragments.LatteFragment;

import java.lang.ref.ReferenceQueue;

/**
 * Created by ft on 2018/8/21.
 */
public abstract class WebDelegate extends LatteFragment{

    private WebView webView = null;
    private final ReferenceQueue<WebView> webViewRequestOptions = new ReferenceQueue<>();

}
