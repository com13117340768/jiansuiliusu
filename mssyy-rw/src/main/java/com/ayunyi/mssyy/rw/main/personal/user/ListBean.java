package com.ayunyi.mssyy.rw.main.personal.user;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.widget.CompoundButton;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.joanzapata.iconify.Icon;
import com.yy.core.fragments.RedWineFragment;
import com.yy.core.util.icon.RWIcons;

/**
 * Created by ft on 2018/9/13.
 */
public class ListBean implements MultiItemEntity {
    private int mItemType = 0;
    private String mImageUrl = null;
    private String mText = null;
    private String mValue = null;
    private RWIcons icon = null;
    private int color = 0;
    private int mId = 0;
    private int tag = 0;
    private RedWineFragment mDelegate = null;

    private CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener = null;

    ListBean(int color,RWIcons icon, int mItemType, String mImageUrl, String mText, String mValue, int mId, RedWineFragment mDelegate, int mTag, CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener) {
        this.color = color;
        this.icon = icon;
        this.mItemType = mItemType;
        this.mImageUrl = mImageUrl;
        this.mText = mText;
        this.mValue = mValue;
        this.mId = mId;
        this.tag = mTag;
        this.mDelegate = mDelegate;
        this.mOnCheckedChangeListener = mOnCheckedChangeListener;
    }

    public int getTag() {
        return tag;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getText() {
        if (mText == null) {
            return "";
        }
        return mText;
    }

    public String getValue() {
        if (mValue == null) {
            return "";
        }
        return mValue;
    }

    public RWIcons getIcon() {
        return icon;
    }

    public int getColor() {
        return color;
    }

    public int getId() {
        return mId;
    }

    public RedWineFragment getDelegate() {
        return mDelegate;
    }

    public CompoundButton.OnCheckedChangeListener getOnCheckedChangeListener() {
        return mOnCheckedChangeListener;
    }

    @Override
    public int getItemType() {
        return mItemType;
    }

    public static final class Builder {
        private int color = 323232;
        private RWIcons icon = null;
        private int tag = 0;
        private int id = 0;
        private int itemType = 0;
        private String imageUrl = null;
        private String text = null;
        private String value = null;
        private CompoundButton.OnCheckedChangeListener onCheckedChangeListener = null;
        private RedWineFragment delegate = null;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }
        public Builder setIcon(RWIcons icon) {
            this.icon = icon;
            return this;
        }

        public Builder setColor(int color) {
            this.color = color;
            return this;
        }

        public Builder setItemType(int itemType) {
            this.itemType = itemType;
            return this;
        }

        public Builder setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder setTag(int tag) {
            this.tag = tag;
            return this;
        }

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Builder setValue(String value) {
            this.value = value;
            return this;
        }

        public Builder setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
            this.onCheckedChangeListener = onCheckedChangeListener;
            return this;
        }

        public Builder setDelegate(RedWineFragment delegate) {
            this.delegate = delegate;
            return this;
        }

        public ListBean build() {
            return new ListBean(color,icon,itemType, imageUrl, text, value, id, delegate, tag, onCheckedChangeListener);
        }
    }
}
