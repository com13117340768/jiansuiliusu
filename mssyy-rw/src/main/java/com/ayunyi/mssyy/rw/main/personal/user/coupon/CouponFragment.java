package com.ayunyi.mssyy.rw.main.personal.user.coupon;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ayunyi.mssyy.rw.R;
import com.ayunyi.mssyy.rw.R2;
import com.yy.core.fragments.RedWineFragment;
import com.yy.core.net.RestClient;
import com.yy.core.net.callback.ISuccess;
import com.yy.core.ui.recycler.MultipleItemEntity;
import com.yy.core.util.logger.FengLogger;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by ft on 2018/10/15.
 */
public class CouponFragment extends RedWineFragment implements ISuccess {

    private String title = null;
    private static final String COUPON = "COUPON";

    @BindView(R2.id.toolbar)
    Toolbar toolbar = null;

    @BindView(R2.id.coupon_recyclerView)
    RecyclerView recyclerView = null;


    public static CouponFragment getInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(COUPON, title);
        CouponFragment couponFragment = new CouponFragment();
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
        return R.layout.fragment_coupon;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        toolbar.setTitle(title);
        initToolbarNav(toolbar);

        RestClient.builder()
                .url("coupon.php")
                .loader(getContext())
                .success(this)
                .build()
                .get();
    }

    @Override
    public void onSuccess(String response) {

        FengLogger.d("chouchou",response);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<MultipleItemEntity> itemEntities = new CouponDateConverter().setJsonData(response).convert();

        CouponAdapter couponAdapter = new CouponAdapter(itemEntities);

        recyclerView.setAdapter(couponAdapter);

    }
}
