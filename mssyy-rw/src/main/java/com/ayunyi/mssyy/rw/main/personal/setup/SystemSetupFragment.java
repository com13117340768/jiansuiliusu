package com.ayunyi.mssyy.rw.main.personal.setup;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.ayunyi.mssyy.rw.R;
import com.ayunyi.mssyy.rw.R2;
import com.ayunyi.mssyy.rw.main.personal.list.ListAdapter;
import com.ayunyi.mssyy.rw.main.personal.list.ListBean;
import com.ayunyi.mssyy.rw.main.personal.list.ListItemType;
import com.ayunyi.mssyy.rw.main.personal.setup.about.AboutFragment;
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


    @Override
    public Object setLayout() {
        return R.layout.fragment_system;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

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

        List<ListBean> listBeans = new ArrayList<>();
        listBeans.add(push);
        listBeans.add(about);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        ListAdapter listAdapter = new ListAdapter(listBeans);
        mRecyclerView.setAdapter(listAdapter);
        mRecyclerView.addOnItemTouchListener(new SystemClickListener(this));
    }
}
