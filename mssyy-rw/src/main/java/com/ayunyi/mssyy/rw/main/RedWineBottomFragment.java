package com.ayunyi.mssyy.rw.main;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;

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
public class RedWineBottomFragment extends BaseBottomFragment {


    private static final String MODE = "startFragmentMode";
    public int mode = 0;

    public static RedWineBottomFragment getInstance(int startMode) {
        Bundle bundle = new Bundle();
        bundle.putInt(MODE, startMode);
        RedWineBottomFragment fragment = new RedWineBottomFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mode = bundle.getInt(MODE);
        }
    }

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
        return mode;
    }

    @Override
    public int setClickColor() {
        return Color.parseColor("#ffcc0000");
    }

}
