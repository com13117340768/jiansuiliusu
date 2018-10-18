package com.ayunyi.mssyy.rw.main.discover.fragmentation;

import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.joanzapata.iconify.IconDrawable;
import com.yy.core.util.icon.RWIcons;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by YoKeyword on 16/2/7.
 */
public class BaseBackFragment extends SupportFragment {

    protected void initToolbarNav(Toolbar toolbar) {
        toolbar.setNavigationIcon(new IconDrawable(getContext(), RWIcons.icon_return).color(Color.WHITE).actionBarSize());
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportDelegate().pop();
            }
        });
    }
}
