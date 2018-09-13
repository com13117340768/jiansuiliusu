package com.yy.core.ui.recycler;

import java.util.ArrayList;

/**
 * Created by ft on 2018/8/15.
 */
public abstract class DataConverter {

    protected final ArrayList<MultipleItemEntity> multipleItemEntities = new ArrayList<>();
    private String mDataJson = null;

    public abstract ArrayList<MultipleItemEntity> convert();

    public final DataConverter setJsonData(String json) {
        this.mDataJson = json;
        return this;
    }

    protected String getJsonData() {
        if (mDataJson == null || mDataJson.isEmpty()) {
            throw new NullPointerException("DataJson is null.");
        }
        return mDataJson;
    }

    public void clearData() {
        multipleItemEntities.clear();
    }
}
