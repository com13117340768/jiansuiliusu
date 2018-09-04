package com.ayunyi.mssyy.rw.main.sort.content;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by ft on 2018/8/20.
 */
public class SectionBean extends SectionEntity<SectionContentItemEntity>{

    private boolean mIsMore = false;
    private int mId = -1;

    public boolean isIsMore() {
        return mIsMore;
    }

    public void setIsMore(boolean isMore) {
        this.mIsMore = isMore;
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    SectionBean(boolean isHeader, String header) {
        super(isHeader, header);
    }

    SectionBean(SectionContentItemEntity sectionContentItemEntity) {
        super(sectionContentItemEntity);
    }
}
