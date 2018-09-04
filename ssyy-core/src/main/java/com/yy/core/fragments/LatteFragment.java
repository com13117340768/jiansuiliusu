package com.yy.core.fragments;

public abstract class LatteFragment extends PermissionFragment {

    public <T extends LatteFragment> T getParentDelegate() {
        //noinspection unchecked
        return (T) getParentFragment();
    }

}
