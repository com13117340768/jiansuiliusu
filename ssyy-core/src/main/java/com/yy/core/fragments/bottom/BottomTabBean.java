package com.yy.core.fragments.bottom;

public class BottomTabBean {

    private final String ICON;
    private final String TITLE;

    public BottomTabBean(String icon, String title) {
        this.ICON = icon;
        this.TITLE = title;
    }

    public String getIcon() {
        return ICON;
    }

    public String getTitle() {
        return TITLE;
    }


}
