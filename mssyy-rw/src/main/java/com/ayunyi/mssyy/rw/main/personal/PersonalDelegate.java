package com.ayunyi.mssyy.rw.main.personal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.ayunyi.mssyy.rw.R;
import com.ayunyi.mssyy.rw.R2;
import com.ayunyi.mssyy.rw.main.personal.list.ListAdapter;
import com.ayunyi.mssyy.rw.main.personal.list.ListBean;
import com.ayunyi.mssyy.rw.main.personal.list.ListItemType;
import com.ayunyi.mssyy.rw.main.personal.order.OrderListDelegate;
import com.yy.core.fragments.bottom.BottomItemDelegate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ft on 2018/8/22.
 */
public class PersonalDelegate extends BottomItemDelegate {

    @BindView(R2.id.rl_harder_bg)
    RelativeLayout relativeLayout = null;

    private Bundle mArgs = null;
    public static final String ORDER_TYPE = "ORDER_TYPE";


    @BindView(R2.id.rv_personal_setting)
    RecyclerView mRvSettings = null;

    private void startOrderListByType() {
        final OrderListDelegate orderListDelegate = new OrderListDelegate();
        orderListDelegate.setArguments(mArgs);
        getParentDelegate().getSupportDelegate().start(orderListDelegate);
    }

    @OnClick(R2.id.tv_all_order)
    void onClickAllOrder() {
        mArgs.putString(ORDER_TYPE, "all");
        startOrderListByType();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mArgs = new Bundle();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_personal;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

        final ListBean address = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(1)
                //.setDelegate()
                .setText("收货地址")
                .build();

        final ListBean system = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(2)
                .setText("系统设置")
                .build();
        final ListBean collection = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(3)
                .setText("我的收藏")
                .build();
        final ListBean points = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(4)
                .setText("积分")
                .build();
        final ListBean Coupon = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(5)
                .setText("优惠券")
                .build();

        List<ListBean> dataBeans = new ArrayList<>();
        dataBeans.add(address);
        dataBeans.add(system);
        dataBeans.add(collection);
        dataBeans.add(points);
        dataBeans.add(Coupon);

        //设置recyclerView
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRvSettings.setLayoutManager(layoutManager);
        ListAdapter listAdapter = new ListAdapter(dataBeans);
        mRvSettings.setAdapter(listAdapter);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        relativeLayout.setBackgroundColor(this.getResources().getColor(R.color.app_main_color));
    }
}
