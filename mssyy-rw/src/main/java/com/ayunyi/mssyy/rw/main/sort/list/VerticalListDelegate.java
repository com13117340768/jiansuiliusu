package com.ayunyi.mssyy.rw.main.sort.list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.ayunyi.mssyy.rw.R;
import com.ayunyi.mssyy.rw.R2;
import com.ayunyi.mssyy.rw.main.sort.SortDelegate;
import com.yy.core.fragments.LatteFragment;
import com.yy.core.net.RestClient;
import com.yy.core.net.callback.IError;
import com.yy.core.net.callback.IFailure;
import com.yy.core.ui.recycler.MultipleItemEntity;

import java.util.List;

import butterknife.BindView;

/**
 * Created by ft on 2018/8/19.
 */
public class VerticalListDelegate extends LatteFragment {

    @BindView(R2.id.rv_vertical_menu_list)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_vertical_list;
    }

    protected void initRecyclerView() {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        //屏蔽动画效果
        mRecyclerView.setItemAnimator(null);
    }


    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        initRecyclerView();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .url("sort_list.php")
                .loader(getContext())
                .success(response -> {
                    final List<MultipleItemEntity> data = new VerticalListDataConverter().setJsonData(response).convert();
                    final SortDelegate delegate = getParentDelegate();
                    final SortRecyclerAdapter adapter = new SortRecyclerAdapter(data, delegate);
                    mRecyclerView.setAdapter(adapter);
                })
                .failure(() -> {
                })
                .error((code, msg) -> {
                })
                .build()
                .get();

    }
}
