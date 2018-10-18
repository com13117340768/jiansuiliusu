package com.ayunyi.mssyy.rw.main.personal;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ayunyi.mssyy.rw.R;
import com.ayunyi.mssyy.rw.R2;
import com.ayunyi.mssyy.rw.login.LoginFragment;
import com.ayunyi.mssyy.rw.main.UserPerpesKeys;
import com.ayunyi.mssyy.rw.main.personal.address.AddressFragment;
import com.ayunyi.mssyy.rw.main.personal.order.OrderListFragment;
import com.ayunyi.mssyy.rw.main.personal.user.ListAdapter;
import com.ayunyi.mssyy.rw.main.personal.user.ListBean;
import com.ayunyi.mssyy.rw.main.personal.user.ListItemType;
import com.ayunyi.mssyy.rw.main.personal.user.coupon.CouponFragment;
import com.ayunyi.mssyy.rw.main.personal.user.profile.UserProFileFragment;
import com.ayunyi.mssyy.rw.main.personal.setup.SystemSetupFragment;
import com.bumptech.glide.Glide;
import com.yy.core.app.AccountManager;
import com.yy.core.fragments.bottom.BottomItemFragment;
import com.yy.core.util.icon.FontRedWineModule;
import com.yy.core.util.icon.RWIcons;
import com.yy.core.util.icon.RedWineIcons;
import com.yy.core.util.sharepreference.RedWinePreference;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ft on 2018/8/22.
 */
public class PersonalFragment extends BottomItemFragment {

    private long mExitTime = 0;
    private final static long EXIT_TIME = 2000L;

    @BindView(R2.id.rl_harder_bg)
    RelativeLayout relativeLayout = null;

    @BindView(R2.id.img_user_avatar)
    CircleImageView uPortImageView = null;


    @BindView(R2.id.tv_user_name)
    AppCompatTextView uNameTextView = null;

    private Bundle mArgs = null;
    public static final String ORDER_TYPE = "ORDER_TYPE";


    @BindView(R2.id.rv_personal_setting)
    RecyclerView mRvSettings = null;

    private void startOrderListByType() {
        final OrderListFragment orderListDelegate = new OrderListFragment();
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
        if (CheckLoginState()) {
            getParentDelegate().getSupportDelegate().start(new UserProFileFragment());
        } else {
            getParentDelegate().getSupportDelegate().start(LoginFragment.getInstence(4));
        }
    }


    @OnClick(R2.id.tv_user_name)
    void OnClickUserText() {
        if (CheckLoginState()) {
            getParentDelegate().getSupportDelegate().start(new UserProFileFragment());
        } else {
            getParentDelegate().getSupportDelegate().start(LoginFragment.getInstence(4));
        }
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
                .setItemType(ListItemType.ITEM_USER_HOME)
                .setId(1)
                .setDelegate(new AddressFragment())
                .setText("收货地址")
                .setIcon(RWIcons.icon_address)
                .setColor(R.color.app_main)
                .build();

        final ListBean system = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_USER_HOME)
                .setId(2)
                .setText("系统设置")
                .setIcon(RWIcons.icon_setting)
                .setColor(R.color.dialogutil_text_title_11)
                .build();
        final ListBean collection = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_USER_HOME)
                .setId(3)
                .setText("我的收藏")
                .setIcon(RWIcons.icon_collection)
                .setColor(R.color.lite_blue)
                .build();
        final ListBean points = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_USER_HOME)
                .setId(4)
                .setText("积分")
                .setIcon(RWIcons.icon_integral)
                .setColor(R.color.colorPrimary)
                .build();
        final ListBean coupon = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_USER_HOME)
                .setId(5)
                .setText("优惠券")
                .setIcon(RWIcons.icon_coupon)
                .setColor(R.color.colorPrimaryDark)
                .build();

        List<ListBean> dataBeans = new ArrayList<>();
        dataBeans.add(address);
        dataBeans.add(system);
        dataBeans.add(collection);
        dataBeans.add(points);
        dataBeans.add(coupon);

        //设置recyclerView
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRvSettings.setLayoutManager(layoutManager);
        ListAdapter listAdapter = new ListAdapter(dataBeans,this);
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


    private boolean CheckLoginState() {
        return AccountManager.checkAccount();
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();

        if (CheckLoginState()) {
            String uriPah = RedWinePreference.getCustomAppProfile(UserPerpesKeys.URI_PATH);
            if (!uriPah.isEmpty()) {
                Glide.with(this)
                        .load(uriPah)
                        .into(uPortImageView);
            } else {
                uPortImageView.setImageResource(R.drawable.not_logged_in);
            }
            String uName = RedWinePreference.getCustomAppProfile(UserPerpesKeys.USER_NAME);
            if (!uName.isEmpty()) {
                uNameTextView.setText(uName);
            } else {
                uNameTextView.setText("点击登录");
            }

        } else {
            uPortImageView.setImageResource(R.drawable.not_logged_in);
            uNameTextView.setText("点击登录");
        }
    }
}
