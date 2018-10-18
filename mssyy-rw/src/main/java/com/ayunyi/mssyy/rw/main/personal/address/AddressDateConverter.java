package com.ayunyi.mssyy.rw.main.personal.address;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yy.core.ui.recycler.DataConverter;
import com.yy.core.ui.recycler.MultipleFields;
import com.yy.core.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * Created by ft on 2018/9/24.
 */
public class AddressDateConverter extends DataConverter {

    @Override
    public ArrayList<MultipleItemEntity> convert() {
        JSONArray array = JSON.parseObject(getJsonData()).getJSONArray("data");
        int size = array.size();
        for (int i = 0; i < size; i++) {
            JSONObject data = array.getJSONObject(i);
            int id = data.getInteger("id");
            String name = data.getString("name");
            String phone = data.getString("phone");
            String address = data.getString("address");
            boolean isDefault = data.getBoolean("default");
            MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setItemType(AddressItemType.ITEM_ADDRESS)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.NAME, name)
                    .setField(MultipleFields.TAG, isDefault)
                    .setField(AddressItemFields.ADDRESS, address)
                    .setField(AddressItemFields.PHONE, phone)
                    .build();
            multipleItemEntities.add(entity);
        }
        return multipleItemEntities;
    }
}
