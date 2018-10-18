package com.ayunyi.mssyy.rw.main.personal.user.integral;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ayunyi.mssyy.rw.R;
import com.ayunyi.mssyy.rw.R2;
import com.ayunyi.mssyy.rw.main.personal.order.OrderListAdapter;
import com.ayunyi.mssyy.rw.main.personal.order.OrderListClickListener;
import com.ayunyi.mssyy.rw.main.personal.order.OrderListDataConverter;
import com.ayunyi.mssyy.rw.main.personal.order.OrderListFragment;
import com.yy.core.fragments.RedWineFragment;
import com.yy.core.net.RestClient;
import com.yy.core.net.callback.ISuccess;
import com.yy.core.ui.recycler.MultipleItemEntity;

import java.util.List;

import butterknife.BindView;

/**
 * Created by ft on 2018/10/16.
 */
public class IntegralFragment extends RedWineFragment implements ISuccess {

    @BindView(R2.id.toolbar)
    Toolbar toolbar = null;
    private String mTitle = "";
    public static final String TITLE = "TITLE";

    public static IntegralFragment getInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        IntegralFragment fragment = new IntegralFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mTitle = bundle.getString(TITLE);
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.fragment_integral;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        toolbar.setTitle(mTitle);
        initToolbarNav(toolbar);
    }

    @Override
    public void onSuccess(String response) {


    }
}
