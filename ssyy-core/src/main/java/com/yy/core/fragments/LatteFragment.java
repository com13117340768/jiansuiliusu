package com.yy.core.fragments;

@SuppressWarnings("ALL")
public abstract class LatteFragment extends PermissionFragment {

    public <T extends LatteFragment> T getParentDelegate() {
        return (T) getParentFragment();
    }

}
