package com.ayunyi.mssyy.rw.main.personal.user.coupon;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yy.core.ui.recycler.DataConverter;
import com.yy.core.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * Created by ft on 2018/10/16.
 */
public class CouponDateConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {

        JSONArray array = JSON.parseObject(getJsonData()).getJSONArray("data");
        int size = array.size();
        for (int i = 0; i < size; i++) {
            JSONObject data = array.getJSONObject(i);
            String title = data.getString("title");
            String money = data.getString("money");
            String date_limit = data.getString("date_limit");
            String money_limit = data.getString("money_limit");
            String remind = data.getString("remind");
            MultipleItemEntity itemEntity = MultipleItemEntity.builder()
                    .setItemType(CouponItemType.COUPON)
                    .setField(CouponItemFields.MONEY, money)
                    .setField(CouponItemFields.TITLE, title)
                    .setField(CouponItemFields.DATE_LIMIT, date_limit)
                    .setField(CouponItemFields.MONEY_LIMIT, money_limit)
                    .setField(CouponItemFields.REMIND, remind)
                    .build();
            multipleItemEntities.add(itemEntity);
        }
        return multipleItemEntities;
    }
}
