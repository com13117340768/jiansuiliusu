package com.ayunyi.mssyy.rw.main.cart;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.ayunyi.mssyy.rw.R;
import com.ayunyi.mssyy.rw.R2;
import com.ayunyi.mssyy.rw.main.EcBottomDelegate;
import com.hss01248.dialog.ActivityStackManager;
import com.yy.core.fragments.LatteFragment;
import com.yy.core.fragments.bottom.BottomItemDelegate;
import com.yy.core.net.RestClient;
import com.yy.core.net.callback.ISuccess;
import com.yy.core.ui.recycler.MultipleItemEntity;

import java.lang.reflect.InvocationHandler;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ft on 2018/8/22.
 */
public class ShopCartDelegate extends BottomItemDelegate implements ISuccess {


    @BindView(R2.id.shop_toolbar)
    Toolbar mToolbar = null;
    @BindView(R2.id.tv_shop_cart_pay)
    AppCompatTextView mTextView = null;

    @BindView(R2.id.rv_shop_cart)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_shop_cart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mToolbar.setBackgroundColor(this.getResources().getColor(R.color.app_main_color));
        mTextView.setBackgroundColor(this.getResources().getColor(R.color.app_main_color));
        RestClient.builder()
                .url("shop_cart.php")
                .loader(getContext())
                .success(this)
                .build()
                .get();
    }

    @Override
    public void onSuccess(String response) {
        List<MultipleItemEntity> data = new ShopCartDataConverter().setJsonData(response).convert();
        ShopCartAdapter adapter = new ShopCartAdapter(data);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(adapter);
        EcBottomDelegate ecBottomDelegate = getParentDelegate();
        mRecyclerView.addOnItemTouchListener(ShopItemClickListener.create(ecBottomDelegate));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
