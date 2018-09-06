package com.ayunyi.mssyy.rw.main.index;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.ayunyi.mssyy.rw.R;
import com.yy.core.fragments.LatteFragment;

/**
 * Created by ft on 2018/9/6.
 */
public class RetryDelegate extends LatteFragment {

    public RetryDelegate() {
    }

    @Override
    public Object setLayout() {
        return R.layout.base_retry;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
     //   RetryDelegate retryDelegate = new RetryDelegate();
     //   getSupportDelegate().loadRootFragment(R.id.retry_rl, retryDelegate);
    }
}
