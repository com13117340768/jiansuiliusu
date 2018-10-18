package com.ayunyi.mssyy.rw.main.personal.user.collection;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ayunyi.mssyy.rw.R;
import com.ayunyi.mssyy.rw.R2;
import com.ayunyi.mssyy.rw.main.index.detail.GoodsDetailFragment;
import com.ayunyi.mssyy.rw.main.personal.order.OrderListAdapter;
import com.ayunyi.mssyy.rw.main.personal.order.OrderListClickListener;
import com.ayunyi.mssyy.rw.main.personal.order.OrderListDataConverter;
import com.ayunyi.mssyy.rw.main.personal.order.OrderListFragment;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.yy.core.fragments.RedWineFragment;
import com.yy.core.net.RestClient;
import com.yy.core.net.callback.ISuccess;
import com.yy.core.ui.recycler.MultipleItemEntity;

import java.util.List;

import butterknife.BindView;

/**
 * Created by ft on 2018/10/16.
 */
public class CollectionFragment extends RedWineFragment{

    @BindView(R2.id.toolbar)
    Toolbar toolbar = null;
    private String mTitle = "";
    public static final String TITLE = "TITLE";
    private String mType = "";//表示为订单类型

    @BindView(R2.id.rv_collection_list)
    RecyclerView mRecyclerView = null;


    public static CollectionFragment getInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        CollectionFragment fragment = new CollectionFragment();
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
        return R.layout.fragment_collection;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        toolbar.setTitle(mTitle);
        initToolbarNav(toolbar);



        RestClient.builder()
                .loader(getContext())
                .url("order_list.php")
                .params("type", mType)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
                        mRecyclerView.setLayoutManager(manager);
                        final List<MultipleItemEntity> data =
                                new OrderListDataConverter().setJsonData(response).convert();
                        final OrderListAdapter adapter = new OrderListAdapter(data);
                        mRecyclerView.setAdapter(adapter);

                        mRecyclerView.addOnItemTouchListener(new SimpleClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                getSupportDelegate().start(GoodsDetailFragment.create(1));
                            }

                            @Override
                            public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

                            }

                            @Override
                            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                            }

                            @Override
                            public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

                            }
                        });
                    }
                })
                .build()
                .get();

    }
}
