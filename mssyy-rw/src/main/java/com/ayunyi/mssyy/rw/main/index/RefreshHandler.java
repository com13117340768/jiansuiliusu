package com.ayunyi.mssyy.rw.main.index;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ayunyi.mssyy.rw.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yy.core.app.RedWine;
import com.yy.core.net.RestClient;
import com.yy.core.net.callback.IError;
import com.yy.core.net.callback.IFailure;
import com.yy.core.net.callback.ISuccess;
import com.yy.core.ui.Loader.LoaderStyle;
import com.yy.core.ui.recycler.DataConverter;
import com.yy.core.ui.recycler.MultipleRecyclerAdapter;
import com.yy.core.util.logger.FengLogger;

/**
 * Created by ft on 2018/8/14.
 */
@SuppressWarnings("UnusedAssignment")
public class RefreshHandler implements
        SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener {

    private final SwipeRefreshLayout REFRESH_LAYOUT;
    private final PagingBean BEAN;
    private final RecyclerView RECYCLERVIEW;
    private MultipleRecyclerAdapter mAdapter = null;
    private final DataConverter CONVERTER;
    IndexFragment mIndexFragment = null;

    private RefreshHandler(SwipeRefreshLayout swipeRefreshLayout,
                           RecyclerView recyclerView,
                           DataConverter converter, PagingBean bean, IndexFragment indexFragment
    ) {
        this.REFRESH_LAYOUT = swipeRefreshLayout;
        this.RECYCLERVIEW = recyclerView;
        this.CONVERTER = converter;
        this.BEAN = bean;
        mIndexFragment = indexFragment;
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }

    public static RefreshHandler create(SwipeRefreshLayout swipeRefreshLayout,
                                        RecyclerView recyclerView, DataConverter converter, IndexFragment indexFragment
    ) {
        return new RefreshHandler(swipeRefreshLayout, recyclerView, converter,
                new PagingBean(), indexFragment);
    }

    private void refresh() {
        REFRESH_LAYOUT.setRefreshing(true);
        RedWine.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                REFRESH_LAYOUT.setRefreshing(false);
            }
        }, 1500);
    }

    public void firstPage(String url) {
        BEAN.setDelayed(1000);
        RestClient.builder()
                .url(url)
                .loader(mIndexFragment.getContext(), LoaderStyle.LineScalePulseOutRapidIndicator)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final JSONObject object = JSON.parseObject(response);
                        BEAN.setTotal(object.getInteger("total"))
                                .setPageSize(object.getInteger("page_size"));
                        //设置Adapter
                        mAdapter = MultipleRecyclerAdapter.create(CONVERTER.setJsonData(response));
                        mAdapter.setOnLoadMoreListener(RefreshHandler.this, RECYCLERVIEW);
                        RECYCLERVIEW.setAdapter(mAdapter);
                        BEAN.addIndex();
                    }
                })
                .error(mIndexFragment)
                .failure(mIndexFragment)
                .build()
                .get();
    }

    private void paging(String url) {
        final int pageSize = BEAN.getPageSize();
        final int currentCount = BEAN.getCurrentCount();
        final int total = BEAN.getTotal();
        final int index = BEAN.getPageIndex();

        if (mAdapter.getData().size() < pageSize || currentCount >= total) {
            mAdapter.loadMoreEnd(true);
        } else {
            RedWine.getHandler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    RestClient.builder()
                            .url(url + index)
                            .success(new ISuccess() {
                                @Override
                                public void onSuccess(String response) {
                                    FengLogger.json("paging", response);
                                    //    CONVERTER.clearData();
                                    mAdapter.setNewData(CONVERTER.setJsonData(response).convert());
                                    //累加数量
                                    BEAN.setCurrentCount(mAdapter.getData().size());
                                    mAdapter.loadMoreComplete();
                                    BEAN.addIndex();
                                    mAdapter.loadMoreEnd();
                                }
                            })
                            .build()
                            .get();
                }
            }, 1000);
        }
    }

    @Override
    public void onRefresh() {
        refresh();
    }


    @Override
    public void onLoadMoreRequested() {
        paging("refresh.php?index=");
    }
}
