package com.ayunyi.mssyy.rw.main.index;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yy.core.ui.recycler.MultipleItemEntity;
import com.yy.core.ui.recycler.MultipleViewHolder;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class QuickAdapter extends BaseQuickAdapter<MultipleItemEntity, MultipleViewHolder> {

    QuickAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(MultipleViewHolder helper, MultipleItemEntity item) {

    }
}
