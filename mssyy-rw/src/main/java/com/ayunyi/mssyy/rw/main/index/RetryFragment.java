package com.ayunyi.mssyy.rw.main.index;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.ayunyi.mssyy.rw.R;
import com.ayunyi.mssyy.rw.R2;
import com.yy.core.fragments.RedWineFragment;

import butterknife.OnClick;

/**
 * Created by ft on 2018/9/6.
 */
public class RetryFragment extends RedWineFragment {

    RefreshListen mRefreshListen;

    @OnClick(R2.id.bt_retry)
    public void OnClick() {
        Toast.makeText(getProxyActivity(), "点击了刷新", Toast.LENGTH_SHORT).show();
        if (mRefreshListen !=null)
            mRefreshListen.refresh(null);
    }

    public RetryFragment() {
    }

    public void setChangeInterface(RefreshListen changeInterface){
        this.mRefreshListen = changeInterface;
    }


    @Override
    public Object setLayout() {
        return R.layout.base_retry;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
    }
}
