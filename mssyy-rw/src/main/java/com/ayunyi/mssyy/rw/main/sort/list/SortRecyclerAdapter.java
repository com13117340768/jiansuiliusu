package com.ayunyi.mssyy.rw.main.sort.list;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.ayunyi.mssyy.rw.R;
import com.ayunyi.mssyy.rw.main.sort.SortFragment;
import com.ayunyi.mssyy.rw.main.sort.content.ContentFragment;
import com.yy.core.fragments.RedWineFragment;
import com.yy.core.ui.recycler.ItemType;
import com.yy.core.ui.recycler.MultipleFields;
import com.yy.core.ui.recycler.MultipleItemEntity;
import com.yy.core.ui.recycler.MultipleRecyclerAdapter;
import com.yy.core.ui.recycler.MultipleViewHolder;

import java.util.List;

import me.yokeyword.fragmentation.SupportHelper;

/**
 * Created by ft on 2018/8/19.
 */
public class SortRecyclerAdapter extends MultipleRecyclerAdapter {
    private final SortFragment mSortDelegate;
    private int mPrePosition = 0;

    SortRecyclerAdapter(List<MultipleItemEntity> data, SortFragment sortDelegate) {
        super(data);
        this.mSortDelegate = sortDelegate;
        addItemType(ItemType.VERTICAL_MENU_LIST, R.layout.item_vertical_menu_list);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case ItemType.VERTICAL_MENU_LIST:
                final String text = entity.getField(MultipleFields.TEXT);
                final boolean isClicked = entity.getField(MultipleFields.TAG);
                final AppCompatTextView name = holder.getView(R.id.tv_vertical_item_name);
                final View line = holder.getView(R.id.view_line);
                final View itemView = holder.itemView;
                itemView.setOnClickListener(v -> {
                    final int currentPosition = holder.getAdapterPosition();
                    if (mPrePosition != currentPosition) {
                        getData().get(mPrePosition).setField(MultipleFields.TAG, false);
                        notifyItemChanged(mPrePosition);
                        entity.setField(MultipleFields.TAG, true);
                        notifyItemChanged(currentPosition);
                        mPrePosition = currentPosition;
                        final int contentId = getData().get(currentPosition).getField(MultipleFields.ID);
                        showContent(contentId);
                    }
                });
                if (!isClicked) {
                    line.setVisibility(View.INVISIBLE);
                    name.setTextColor(ContextCompat.getColor(mContext, R.color.we_chat_black));
                    itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.item_background));
                } else {
                    line.setVisibility(View.VISIBLE);
                    name.setTextColor(ContextCompat.getColor(mContext, R.color.app_main));
                    line.setBackgroundColor(ContextCompat.getColor(mContext, R.color.app_main));
                    itemView.setBackgroundColor(Color.WHITE);
                }
                holder.setText(R.id.tv_vertical_item_name, text);
                break;
            default:
                break;
        }
    }

    private void showContent(int contentId) {
        final ContentFragment delegate = ContentFragment.newInstance(contentId);
        switchContent(delegate);
    }


    private void switchContent(ContentFragment delegate) {
        final RedWineFragment contentDelegate =
                SupportHelper.findFragment(mSortDelegate.getChildFragmentManager(), ContentFragment.class);
        if (contentDelegate != null) {
            contentDelegate.getSupportDelegate().replaceFragment(delegate, false);
        }
    }
}
