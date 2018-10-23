package com.ayunyi.mssyy.rw.main.cart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.ViewStubCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ayunyi.mssyy.rw.R;
import com.ayunyi.mssyy.rw.R2;
import com.ayunyi.mssyy.rw.main.RedWineBottomFragment;
import com.ayunyi.mssyy.rw.pay.IAlPayResultListener;
import com.ayunyi.mssyy.rw.pay.MPay;
import com.joanzapata.iconify.widget.IconTextView;
import com.yy.core.fragments.bottom.BottomItemFragment;
import com.yy.core.net.RestClient;
import com.yy.core.net.callback.ISuccess;
import com.yy.core.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ft on 2018/8/22.
 */
public class ShopCartFragment extends BottomItemFragment implements ISuccess, ICartItemListener ,IAlPayResultListener{

    private ShopCartAdapter mAdapter = null;

    @BindView(R2.id.rv_shop_cart)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.icon_shop_cart_select_all)
    IconTextView mIconSelectAll = null;
    @BindView(R2.id.stub_no_item)
    ViewStubCompat mStubNoItem = null;
    @BindView(R2.id.tv_shop_cart_total_price)
    AppCompatTextView mTvTotalPrice = null;
    @BindView(R2.id.shop_toolbar)
    Toolbar mToolbar = null;
    @BindView(R2.id.tv_shop_cart_pay)
    AppCompatTextView textView = null;


    @OnClick(R2.id.icon_shop_cart_select_all)
    void onClickSelectAll() {
        final int tag = (int) mIconSelectAll.getTag();
        if (tag == 0) {
            //noinspection ConstantConditions
            mIconSelectAll.setTextColor
                    (ContextCompat.getColor(getContext(), R.color.app_main));
            mIconSelectAll.setTag(1);
            mAdapter.setIsSelectedAll(true);
            //更新RecyclerView的显示状态
            mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
        } else {
            mIconSelectAll.setTextColor(Color.GRAY);
            mIconSelectAll.setTag(0);
            mAdapter.setIsSelectedAll(false);
            mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
        }
    }

    @OnClick(R2.id.tv_top_shop_cart_remove_selected)
    void onClickRemoveSelectedItem() {
        if (mAdapter.getItemCount() <= 0) {
            resetData();
            Toast.makeText(getContext(), "您的酒柜空啦", Toast.LENGTH_SHORT).show();
            return;
        }
        final List<MultipleItemEntity> data = mAdapter.getData();
        final List<MultipleItemEntity> deleteEntities = new ArrayList<>();
        for (MultipleItemEntity entity : data) {
            final boolean isSelected = entity.getField(ShopCartItemFields.IS_SELECTED);
            if (isSelected) {
                deleteEntities.add(entity);
            }
        }
        final int deleteCount = deleteEntities.size();
        final int dataCount = data.size();
        //    double addPrice = mAdapter.getTotalPrice();
        int currentCount;
        for (int i = 0; i < deleteCount; i++) {
            currentCount = deleteEntities.get(i).getField(ShopCartItemFields.POSITION);
//            double price = deleteEntities.get(i).getField(ShopCartItemFields.PRICE);
//            int count = deleteEntities.get(i).getField(ShopCartItemFields.COUNT);
//            double p = addPrice - (price * count);
//            mTvTotalPrice.setText(String.valueOf(p));

            if (currentCount < dataCount) {
                mAdapter.remove(currentCount);
                for (; currentCount < dataCount - 1; currentCount++) {
                    int rePosition = data.get(currentCount).getField(ShopCartItemFields.POSITION);
                    data.get(currentCount).setField(ShopCartItemFields.POSITION, rePosition - 1);
                }
            }
        }
        checkItemCount();
    }

    @OnClick(R2.id.tv_top_shop_cart_clear)
    void onClickClear() {
        final int count = mAdapter.getItemCount();
        if (count > 0) {
            resetData();
        } else {
            Toast.makeText(getContext(), "您的酒柜空啦!", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R2.id.tv_shop_cart_pay)
    void onClickPay() {
        createOrder();
    }

    //创建订单
    private void createOrder() {
        final String orderUrl = "shop_cart_count.php";
        final WeakHashMap<String, Object> orderParams = new WeakHashMap<>();
        //加入你的参数
        RestClient.builder()
                .url(orderUrl)
                .loader(getContext())
                .params(orderParams)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        //进行具体的支付
                        Log.d("ORDER", response);
                  //      final int orderId = JSON.parseObject(response).getInteger("result");
                        MPay.create(ShopCartFragment.this)
                                .setPayResultListener(ShopCartFragment.this)
                                .setOrderId(123456)
                                .beginPayDialog(ShopCartFragment.this);
                    }
                })
                .build()
                .post();
    }

    @SuppressWarnings("RestrictedApi")
    private void checkItemCount() {
        final int count = mAdapter.getItemCount();
        if (count == 0) {
            final View stubView = mStubNoItem.inflate();
            final AppCompatTextView tvToBuy = stubView.findViewById(R.id.tv_stub_to_buy);
            //noinspection Convert2Lambda
            tvToBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "您的酒柜空啦", Toast.LENGTH_SHORT).show();
                }
            });
            mRecyclerView.setVisibility(View.GONE);
        } else {
            mRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    private void resetData() {
        mAdapter.getData().clear();
        mAdapter.notifyDataSetChanged();
        checkItemCount();
        mIconSelectAll.setTextColor(Color.GRAY);
        mIconSelectAll.setClickable(false);
        textView.setClickable(false);
        mTvTotalPrice.setText("￥0.00");
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_shop_cart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        mIconSelectAll.setTag(0);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .url("shop_cart.php")
                .loader(getContext())
                .success(this)
                .build()
                .get();
    }

    @Override
    public void onSuccess(String response) {
        final ArrayList<MultipleItemEntity> data =
                new ShopCartDataConverter()
                        .setJsonData(response)
                        .convert();
        mAdapter = new ShopCartAdapter(data);
        mAdapter.setCartItemListener(this);
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
        RedWineBottomFragment ecBottomDelegate = getParentDelegate();
        mRecyclerView.addOnItemTouchListener(ShopItemClickListener.create(ecBottomDelegate));
        double mTotalPrice = mAdapter.getTotalPrice();
        mTvTotalPrice.setText(String.valueOf(mTotalPrice));
        checkItemCount();
    }

    @Override
    public void onItemClick(double itemTotalPrice) {
        final double price = mAdapter.getTotalPrice();
        mTvTotalPrice.setText(String.valueOf(price));
    }

    @Override
    public void onPaySuccess() {

    }

    @Override
    public void onPaying() {

    }

    @Override
    public void onPayFail() {

    }

    @Override
    public void onPayCancel() {

    }

    @Override
    public void onPayConnectError() {

    }
}
