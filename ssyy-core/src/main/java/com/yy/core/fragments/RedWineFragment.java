package com.yy.core.fragments;

@SuppressWarnings("ALL")
public abstract class RedWineFragment extends PermissionFragment {

    public <T extends RedWineFragment> T getParentDelegate() {
        return (T) getParentFragment();
    }

}
