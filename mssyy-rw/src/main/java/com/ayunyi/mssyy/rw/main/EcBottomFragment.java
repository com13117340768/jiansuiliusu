package com.ayunyi.mssyy.rw.main;

import android.graphics.Color;

import com.ayunyi.mssyy.rw.main.cart.ShopCartFragment;
import com.ayunyi.mssyy.rw.main.discover.DiscoverFragment;
import com.ayunyi.mssyy.rw.main.index.IndexFragment;
import com.ayunyi.mssyy.rw.main.personal.PersonalFragment;
import com.ayunyi.mssyy.rw.main.sort.SortFragment;
import com.yy.core.fragments.bottom.BaseBottomFragment;
import com.yy.core.fragments.bottom.BottomItemFragment;
import com.yy.core.fragments.bottom.BottomTabBean;

import java.util.LinkedHashMap;

/**
 * Created by ft on 2018/8/13.
 */
public class EcBottomFragment extends BaseBottomFragment {
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemFragment> setItems() {
        final LinkedHashMap<BottomTabBean, BottomItemFragment> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "主页"), new IndexFragment());
        items.put(new BottomTabBean("{fa-sort}", "分类"), new SortFragment());
        items.put(new BottomTabBean("{fa-compass}", "发现"), new DiscoverFragment());
        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new ShopCartFragment());
        items.put(new BottomTabBean("{fa-user}", "我的"), new PersonalFragment());
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
