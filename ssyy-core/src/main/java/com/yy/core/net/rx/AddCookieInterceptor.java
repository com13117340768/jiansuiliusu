package com.yy.core.net.rx;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public final class AddCookieInterceptor implements Interceptor {

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
//        final Request.Builder builder = chain.request().newBuilder();
//        Observable
//                .just(LattePreference.getCustomAppProfile("cookie"))
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(@io.reactivex.annotations.NonNull String cookie) throws Exception {
//                        //给原生API请求附带上WebView拦截下来的Cookie
//                        builder.addHeader("Cookie", cookie);
//                    }
//                });
 //       return chain.proceed(builder.build());
        return null;
    }
}
