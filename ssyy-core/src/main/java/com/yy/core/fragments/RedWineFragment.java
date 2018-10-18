package com.yy.core.fragments;

import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.joanzapata.iconify.IconDrawable;
import com.yy.core.util.icon.RWIcons;

@SuppressWarnings("ALL")
public abstract class RedWineFragment extends PermissionFragment {

    public <T extends RedWineFragment> T getParentDelegate() {
        return (T) getParentFragment();
    }

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
