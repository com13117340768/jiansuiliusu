package com.ayunyi.mssyy.rw.main.personal.address;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.yy.core.fragments.RedWineFragment;

/**
 * Created by ft on 2018/9/24.
 */
public class AddressItemClickListener extends SimpleClickListener {


    private final RedWineFragment redWineFragment;

    private AddressItemClickListener(RedWineFragment redWineFragment) {
        this.redWineFragment = redWineFragment;
    }

    public static SimpleClickListener create(RedWineFragment fragment) {
        return new AddressItemClickListener(fragment);
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//        MultipleItemEntity entity = (MultipleItemEntity) baseQuickAdapter.getData().get(position);
//        final String name = entity.getField(MultipleFields.NAME);
//        final String phone = entity.getField(AddressItemFields.PHONE);
//        final String address = entity.getField(AddressItemFields.ADDRESS);
//        final boolean isDefault = entity.getField(MultipleFields.TAG);
//        final int id = entity.getField(MultipleFields.ID);
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
