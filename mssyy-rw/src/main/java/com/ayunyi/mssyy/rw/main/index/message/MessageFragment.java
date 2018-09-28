package com.ayunyi.mssyy.rw.main.index.message;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.ayunyi.mssyy.rw.R;
import com.ayunyi.mssyy.rw.R2;
import com.yy.core.fragments.RedWineFragment;

import butterknife.OnClick;

/**
 * Created by ft on 2018/9/26.
 */
public class MessageFragment extends RedWineFragment{


    @OnClick(R2.id.icon_po_return)
    void BackClick(){
        this.getSupportDelegate().pop();
    }

    @Override
    public Object setLayout() {
        return R.layout.fragment_message;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }
}
