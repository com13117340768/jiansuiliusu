package com.ayunyi.mssyy.rw.main.personal.user.coupon;

import android.support.v7.widget.AppCompatTextView;

import com.ayunyi.mssyy.rw.R;
import com.yy.core.ui.recycler.MultipleItemEntity;
import com.yy.core.ui.recycler.MultipleRecyclerAdapter;
import com.yy.core.ui.recycler.MultipleViewHolder;

import java.util.List;

/**
 * Created by ft on 2018/10/16.
 */
public class CouponAdapter extends MultipleRecyclerAdapter {

    protected CouponAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(CouponItemType.COUPON, R.layout.item_coupon);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);

        switch (holder.getItemViewType()) {
            case CouponItemType.COUPON:

                String title = entity.getField(CouponItemFields.TITLE);
                String money = entity.getField(CouponItemFields.MONEY);
                String date_limit = entity.getField(CouponItemFields.DATE_LIMIT);
                String money_limit = entity.getField(CouponItemFields.MONEY_LIMIT);
                String remind = entity.getField(CouponItemFields.REMIND);

                AppCompatTextView tv_title = holder.getView(R.id.coupon_tv_money_title);
                AppCompatTextView tv_money = holder.getView(R.id.coupon_tv_money);
                AppCompatTextView tv_date_limit = holder.getView(R.id.coupon_tv_date_limit);
                AppCompatTextView tv_money_limit = holder.getView(R.id.coupon_tv_money_limit);
                AppCompatTextView tv_remind = holder.getView(R.id.coupon_tv_remind);

                tv_title.setText(title);
                tv_money.setText(money);
                tv_date_limit.setText(date_limit);
                tv_money_limit.setText(money_limit);
                tv_remind.setText(remind);
                break;
            default:
                break;


        }


    }
}
