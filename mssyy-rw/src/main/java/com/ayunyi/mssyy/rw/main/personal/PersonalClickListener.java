package com.ayunyi.mssyy.rw.main.personal;

import android.view.View;

import com.ayunyi.mssyy.rw.main.personal.setup.SystemSetupFragment;
import com.ayunyi.mssyy.rw.main.personal.user.ListBean;
import com.ayunyi.mssyy.rw.main.personal.user.collection.CollectionFragment;
import com.ayunyi.mssyy.rw.main.personal.user.coupon.CouponFragment;
import com.ayunyi.mssyy.rw.main.personal.user.integral.IntegralFragment;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.yy.core.fragments.RedWineFragment;

import me.yokeyword.fragmentation.ISupportFragment;


public class PersonalClickListener extends SimpleClickListener {

    private final RedWineFragment DELEGATE;

    public PersonalClickListener(RedWineFragment delegate) {
        this.DELEGATE = delegate;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final ListBean bean = (ListBean) baseQuickAdapter.getData().get(position);
        int id = bean.getId();
        switch (id) {
            case 1:
                DELEGATE.getParentDelegate().getSupportDelegate().start(bean.getDelegate());
                break;
            case 2:
                DELEGATE.getParentDelegate().getSupportDelegate().start(SystemSetupFragment.getInstance("系统设置"));
                break;
            case 3:
                DELEGATE.getParentDelegate().getSupportDelegate().start(CollectionFragment.getInstance("我的收藏"));
                break;
            case 4:
                DELEGATE.getParentDelegate().getSupportDelegate().start(IntegralFragment.getInstance("我的会员积分"));
                break;
            case 5:
                DELEGATE.getParentDelegate().getSupportDelegate().start(CouponFragment.getInstance("喝了么商家优惠券"));
                break;
            default:
                break;
        }
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

}
