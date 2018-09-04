package com.yy.core.ui.refresh;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yy.core.app.Latte;
import com.yy.core.net.RestClient;
import com.yy.core.net.callback.ISuccess;
import com.yy.core.ui.recycler.DataConverter;
import com.yy.core.ui.recycler.MultipleRecyclerAdapter;

import java.io.UnsupportedEncodingException;

/**
 * Created by ft on 2018/8/14.
 */
@SuppressWarnings("UnusedAssignment")
public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener
        , BaseQuickAdapter.RequestLoadMoreListener {

    private SwipeRefreshLayout mSwipeRefreshLayout = null;
    private PagingBean mPagingBean = null;
    private RecyclerView mRecyclerView = null;
    private MultipleRecyclerAdapter mRecyclerAdapter = null;
    private DataConverter mConverter = null;

    private RefreshHandler(SwipeRefreshLayout swipeRefreshLayout,
                           RecyclerView recyclerView,
                           DataConverter dataConverter,
                           PagingBean pagingBean) {
        this.mSwipeRefreshLayout = swipeRefreshLayout;
        this.mConverter = dataConverter;
        this.mPagingBean = pagingBean;
        this.mRecyclerView = recyclerView;
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }


    public static RefreshHandler create(SwipeRefreshLayout swipeRefreshLayout,
                                        RecyclerView recyclerView,
                                        DataConverter dataConverter) {
        return new RefreshHandler(swipeRefreshLayout, recyclerView, dataConverter, new PagingBean());
    }


    @Override
    public void onRefresh() {
       //     refresh();
    }


    private void refresh() {
        //noinspection Convert2Lambda
        Latte.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);
    }

    public void firstPage(Context context, String url) {
        RestClient.builder()
                .url(url)
                .loader(context)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        //         Log.d("hahaha", ""+response);
                        String info = null;
                        try {
                            info = new String(response.getBytes(), "utf-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        final JSONObject jsonObject = JSON.parseObject(info);
                        mPagingBean.setTotal(jsonObject.getInteger("total"))
                                .setPageSize(jsonObject.getInteger("page_size"));
                        mRecyclerAdapter = MultipleRecyclerAdapter.create(mConverter.setJsonData(info));
                        mRecyclerAdapter.setOnLoadMoreListener(RefreshHandler.this, mRecyclerView);
                        mRecyclerView.setAdapter(mRecyclerAdapter);
                        mPagingBean.addIndex();
                    }
                })
                .build()
                .get();
    }

    @SuppressWarnings("Convert2Lambda")
    private void paging(final String url) {
        final int pageSize = mPagingBean.getPageSize();
        final int currentCount = mPagingBean.getCurrentCount();
        final int total = mPagingBean.getTotal();
        final int index = mPagingBean.getPageIndex();

        if (mRecyclerAdapter.getData().size() < pageSize || currentCount >= total) {
            mRecyclerAdapter.loadMoreEnd(true);
        } else {
            Latte.getHandler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    RestClient.builder()
                            .url(url + index)
                            .success(new ISuccess() {
                                @Override
                                public void onSuccess(String response) {
                                    Log.d("paging", response);
                                    mConverter.clearData();
                                    mRecyclerAdapter.addData(mConverter.setJsonData(response).convert());
                                    //累加数量
                                    mPagingBean.setCurrentCount(mRecyclerAdapter.getData().size());
                                    mRecyclerAdapter.loadMoreComplete();
                                    mPagingBean.addIndex();
                                }
                            })
                            .build()
                            .get();
                }
            }, 1000);
        }
    }


    @Override
    public void onLoadMoreRequested() {
        paging("refresh.php?index=");
    }
}
