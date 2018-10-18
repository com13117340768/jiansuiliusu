package com.ayunyi.mssyy.rw.main.personal.user.integral;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yy.core.ui.recycler.DataConverter;
import com.yy.core.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * Created by ft on 2018/10/17.
 */
public class IntegralDateConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {

        JSONArray array = JSON.parseObject(getJsonData()).getJSONArray("data");

        int size = array.size();

        for (int i = 0; i < size; i++) {
           JSONObject object =array.getJSONObject(i);
            String count = object.getString("count");
        }

        return null;
    }
}
