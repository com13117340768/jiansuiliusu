package com.ayunyi.mssyy.rw.main.personal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ayunyi.mssyy.rw.R;
import com.ayunyi.mssyy.rw.R2;
import com.ayunyi.mssyy.rw.main.personal.address.AddersFragment;
import com.ayunyi.mssyy.rw.main.personal.user.ListAdapter;
import com.ayunyi.mssyy.rw.main.personal.user.ListBean;
import com.ayunyi.mssyy.rw.main.personal.user.ListItemType;
import com.ayunyi.mssyy.rw.main.personal.order.OrderListDelegate;
import com.ayunyi.mssyy.rw.main.personal.user.profile.UserProFileFragment;
import com.ayunyi.mssyy.rw.main.personal.setup.SystemSetupFragment;
import com.yy.core.fragments.bottom.BottomItemFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ft on 2018/8/22.
 */
public class PersonalFragment extends BottomItemFragment {

    private long mExitTime = 0;
    private final static long EXIT_TIME = 2000L;

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


    @OnClick(R2.id.img_user_avatar)
    void OnClickImage() {
        getParentDelegate().getSupportDelegate().start(new UserProFileFragment());
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
                .setDelegate(new AddersFragment())
                .setText("收货地址")
                .build();

        final ListBean system = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(2)
                .setDelegate(new SystemSetupFragment())
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
        mRvSettings.addOnItemTouchListener(new PersonalClickListener(this));
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
    }

    @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis() - mExitTime < EXIT_TIME) {
            if (mExitTime != 0) {
                mExitTime = 0;
            }
            if (_mActivity != null && !_mActivity.isFinishing()) {
                _mActivity.finish();
                System.exit(0);
            }
        } else {
            Toast.makeText(getContext(), "双击退出" + getString(com.yy.core.R.string.app_name), Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
            return true;
        }
        return false;
    }
}
