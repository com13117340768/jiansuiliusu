package com.ayunyi.mssyy.rw.main.personal.user;

import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.SwitchCompat;
import android.widget.ImageView;

import com.ayunyi.mssyy.rw.R;
import com.ayunyi.mssyy.rw.main.personal.PersonalFragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconDrawable;
import com.yy.core.app.RedWine;
import com.yy.core.util.icon.RWIcons;
import com.yy.core.util.icon.RedWineIcons;
import com.yy.core.util.logger.FengLogger;

import java.util.List;

/**
 * Created by ft on 2018/9/13.
 */
public class ListAdapter extends BaseMultiItemQuickAdapter<ListBean, BaseViewHolder> {


    private PersonalFragment mFragment = null;


    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate()
            .centerCrop();

    public ListAdapter(List<ListBean> data, PersonalFragment fragment) {
        super(data);
        this.mFragment = fragment;
        addItemType(ListItemType.ITEM_NAME, R.layout.arrow_itemname_layout);
        addItemType(ListItemType.ITEM_NORMAL, R.layout.arrow_item_layout);
        addItemType(ListItemType.ITEM_AVATAR, R.layout.arrow_item_avatar);
        addItemType(ListItemType.ITEM_SWITCH, R.layout.arrow_switch_layout);
        addItemType(ListItemType.ITEM_USER_PROFILE, R.layout.arrow_item_user_profile);
        addItemType(ListItemType.ITEM_USER_HOME, R.layout.arrow_item_user_home);
    }

    @Override
    protected void convert(BaseViewHolder helper, ListBean item) {

        switch (helper.getItemViewType()) {
            case ListItemType.ITEM_USER_HOME:
                RWIcons icon = item.getIcon();
                int color = item.getColor();
                FengLogger.d("liwen",color);

                helper.setImageDrawable(R.id.imageView_head, new IconDrawable(mFragment.getContext(), icon).colorRes(color).actionBarSize());
                helper.setText(R.id.tv_arrow_text, item.getText());

                break;
            case ListItemType.ITEM_NAME:
                helper.setText(R.id.tv_arrow_text, item.getText());
                helper.setText(R.id.tv_arrow_value_one, item.getValue());
                break;

            case ListItemType.ITEM_NORMAL:
                helper.setText(R.id.tv_arrow_text, item.getText());
                helper.setText(R.id.tv_arrow_value, item.getValue());
                break;
            case ListItemType.ITEM_AVATAR:

                Glide.with(mContext)
                        .load(item.getImageUrl())
                        .apply(OPTIONS)
                        .into((ImageView) helper.getView(R.id.img_arrow_avatar));
                break;
            case ListItemType.ITEM_SWITCH:
                helper.setText(R.id.tv_arrow_switch_text, item.getText());
                final SwitchCompat switchCompat = helper.getView(R.id.list_item_switch);
                switchCompat.setChecked(true);
                switchCompat.setOnCheckedChangeListener(item.getOnCheckedChangeListener());
                break;
            case ListItemType.ITEM_USER_PROFILE:
                Glide.with(mContext)
                        .load(item.getImageUrl())
                        .apply(OPTIONS)
                        .into((ImageView) helper.getView(R.id.img_user_profile));
                break;
            default:
                break;
        }

    }
}
