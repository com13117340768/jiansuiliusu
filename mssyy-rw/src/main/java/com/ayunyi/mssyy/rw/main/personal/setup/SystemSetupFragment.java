package com.ayunyi.mssyy.rw.main.personal.setup;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.ayunyi.mssyy.rw.R;
import com.ayunyi.mssyy.rw.R2;
import com.ayunyi.mssyy.rw.main.personal.user.ListAdapter;
import com.ayunyi.mssyy.rw.main.personal.user.ListBean;
import com.ayunyi.mssyy.rw.main.personal.user.ListItemType;
import com.ayunyi.mssyy.rw.main.personal.setup.about.AboutFragment;
import com.ayunyi.mssyy.rw.main.personal.user.coupon.CouponFragment;
import com.yy.core.fragments.RedWineFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ft on 2018/9/23.
 */
public class SystemSetupFragment extends RedWineFragment {


    @BindView(R2.id.rv_system_setup)
    RecyclerView mRecyclerView = null;

    @BindView(R2.id.toolbar)
    Toolbar toolbar = null;
    private String title = null;
    private static final String COUPON = "COUPON";

    public static SystemSetupFragment getInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(COUPON, title);
        SystemSetupFragment couponFragment = new SystemSetupFragment();
        couponFragment.setArguments(bundle);
        return couponFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            title = bundle.getString(COUPON);
        }

    }

    @Override
    public Object setLayout() {
        return R.layout.fragment_system;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        toolbar.setTitle(title);
        initToolbarNav(toolbar);

        ListBean push = new ListBean.Builder()
                .setDelegate(null)
                .setItemType(ListItemType.ITEM_SWITCH)
                .setId(1)
                .setDelegate(new AboutFragment())
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            Toast.makeText(getContext(), "打开推送", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "关闭推送", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setText("消息推送")
                .build();

        ListBean about = new ListBean.Builder()
                .setId(2)
                .setItemType(ListItemType.ITEM_NORMAL)
                .setDelegate(new AboutFragment())
                .setText("关于")
                .build();

        ListBean upDataApp = new ListBean.Builder()
                .setId(3)
                .setItemType(ListItemType.ITEM_NORMAL)
                .setDelegate(new AboutFragment())
                .setText("检查更新")
                .build();


        List<ListBean> listBeans = new ArrayList<>();
        listBeans.add(push);
        listBeans.add(about);
        listBeans.add(upDataApp);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        ListAdapter listAdapter = new ListAdapter(listBeans,null);
        mRecyclerView.setAdapter(listAdapter);
        mRecyclerView.addOnItemTouchListener(new SystemClickListener(this));
    }
}
