package com.ayunyi.mssyy.rw.main;

import android.graphics.Color;

import com.ayunyi.mssyy.rw.main.cart.ShopCartDelegate;
import com.ayunyi.mssyy.rw.main.discover.DiscoverDelegate;
import com.ayunyi.mssyy.rw.main.index.IndexDelegate;
import com.ayunyi.mssyy.rw.main.personal.PersonalDelegate;
import com.ayunyi.mssyy.rw.main.sort.SortDelegate;
import com.yy.core.fragments.bottom.BaseBottomDelegate;
import com.yy.core.fragments.bottom.BottomItemDelegate;
import com.yy.core.fragments.bottom.BottomTabBean;

import java.util.LinkedHashMap;

/**
 * Created by ft on 2018/8/13.
 */
public class EcBottomDelegate extends BaseBottomDelegate {
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems() {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "主页"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}", "分类"), new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}", "发现"), new DiscoverDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new ShopCartDelegate());
        items.put(new BottomTabBean("{fa-user}", "我的"), new PersonalDelegate());
        return items;
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickColor() {
        return Color.parseColor("#ffcc0000");
    }

}
