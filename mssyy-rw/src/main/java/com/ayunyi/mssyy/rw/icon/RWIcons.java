package com.ayunyi.mssyy.rw.icon;

import com.joanzapata.iconify.Icon;

public enum RWIcons implements Icon{
    icon_delete('\ue8b6'),icon_share('\ue60a'),icon_return('\ue618'),icon_search('\ue651');

    private char aChar;

    RWIcons(char aChar) {
        this.aChar = aChar;
    }

    @Override
    public String key() {
     return name().replace('_', '-');
    }

    @Override
    public char character() {
        return aChar;
    }
}
