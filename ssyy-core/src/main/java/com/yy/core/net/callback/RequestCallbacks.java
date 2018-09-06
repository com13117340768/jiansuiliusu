package com.yy.core.net.callback;

import android.os.Handler;
import android.support.annotation.NonNull;

import com.yy.core.app.ConfigKeys;
import com.yy.core.app.Latte;
import com.yy.core.ui.Loader.LatteLoader;
import com.yy.core.ui.Loader.LoaderStyle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public final class RequestCallbacks implements Callback<String> {

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final LoaderStyle LOADER_STYLE;
    private static final Handler HANDLER = Latte.getHandler();

    public RequestCallbacks(IRequest request, ISuccess success, IFailure failure, IError error, LoaderStyle style) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.LOADER_STYLE = style;
    }

    @Override
    public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (SUCCESS != null) {
                    SUCCESS.onSuccess(response.body());
                    onRequestFinish();
                }
            }
        } else {
            if (ERROR != null) {
                onRequestFinish();
                ERROR.onError(response.code(), response.message());
            }
        }

    }

    @Override
    public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
        if (FAILURE != null) {
            FAILURE.onFailure();
            onRequestFinish();
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }

    }


    private void onRequestFinish() {
        final long delayed = Latte.getConfiguration(ConfigKeys.LOADER_DELAYED);
        if (LOADER_STYLE != null) {
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LatteLoader.stopLoading();
                }
            }, delayed);
        }

    }
}
