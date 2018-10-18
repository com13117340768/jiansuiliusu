package com.ayunyi.mssyy.rw.main.sort.content;

import android.view.View;

import com.ayunyi.mssyy.rw.main.index.detail.GoodsDetailFragment;
import com.ayunyi.mssyy.rw.main.sort.SortFragment;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.yy.core.fragments.RedWineFragment;

/**
 * Created by ft on 2018/10/18.
 */
public class ContentItemClickListener extends SimpleClickListener {

    private SortFragment redWineFragment = null;

    ContentItemClickListener(SortFragment redWineFragment) {
        this.redWineFragment = redWineFragment;
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        redWineFragment.getParentDelegate().start(new GoodsDetailFragment());
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
