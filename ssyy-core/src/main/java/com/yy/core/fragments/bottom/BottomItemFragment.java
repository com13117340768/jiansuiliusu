package com.yy.core.fragments.bottom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.yy.core.R;
import com.yy.core.fragments.RedWineFragment;

public abstract class BottomItemFragment extends RedWineFragment implements View.OnKeyListener {
    private long mExitTime = 0;
    private final static long EXIT_TIME = 2000L;

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View rootView = getView();
        if (rootView != null) {
            rootView.setFocusableInTouchMode(true);
            rootView.requestFocus();
            rootView.setOnKeyListener(this);
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - mExitTime < EXIT_TIME) {
                if (mExitTime != 0) {
                    mExitTime = 0;
                }
                if (_mActivity != null && !_mActivity.isFinishing()) {
                    _mActivity.finish();
                    System.exit(0);
                }
            } else {
                Toast.makeText(getContext(), "双击退出" + getString(R.string.app_name), Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            }
            return true;
        }
        return false;
    }

    public BottomItemFragment getSupoFragment(){
        return this;
    }
}
