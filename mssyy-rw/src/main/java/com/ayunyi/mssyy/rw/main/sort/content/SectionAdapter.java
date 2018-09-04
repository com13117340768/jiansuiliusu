package com.ayunyi.mssyy.rw.main.sort.content;

import android.support.v7.widget.AppCompatImageView;

import com.ayunyi.mssyy.rw.R;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by ft on 2018/8/20.
 */
public class SectionAdapter extends BaseSectionQuickAdapter<SectionBean, BaseViewHolder> {
    SectionAdapter(int layoutResId, int sectionHeadResId, List<SectionBean> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper,SectionBean item) {
        helper.setText(R.id.header, item.header);
        helper.setVisible(R.id.more, item.isIsMore());
        helper.addOnClickListener(R.id.more);
    }

    @Override
    protected void convert(BaseViewHolder helper, SectionBean item){
        final String thumb=item.t.getGoodsThumb();
        final String name = item.t.getGoodsName();
        final int goodsId = item.t.getGoodsId();
        final SectionContentItemEntity entity = item.t;
        helper.setText(R.id.tv, name);
        final AppCompatImageView goodsImageView = helper.getView(R.id.iv);
        Glide.with(mContext).load(thumb).into(goodsImageView);
    }
}
