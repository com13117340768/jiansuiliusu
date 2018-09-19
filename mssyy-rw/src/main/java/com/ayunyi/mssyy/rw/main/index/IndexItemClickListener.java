package com.ayunyi.mssyy.rw.main.index;

import android.view.View;

import com.ayunyi.mssyy.rw.detail.GoodsDetailFragment;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.yy.core.fragments.RedWineFragment;

/**
 * Created by ft on 2018/8/16.
 */
public class IndexItemClickListener extends SimpleClickListener{

    private RedWineFragment mLatteFragment;

    private  IndexItemClickListener(RedWineFragment latteFragment) {
        this.mLatteFragment = latteFragment;
    }

    public static SimpleClickListener create(RedWineFragment latteFragment){
        return new IndexItemClickListener(latteFragment);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        GoodsDetailFragment delegate = GoodsDetailFragment.create();
        mLatteFragment.start(delegate);
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
