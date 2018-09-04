package com.ayunyi.mssyy.rw.icon;

import com.joanzapata.iconify.Icon;

public enum RWIcons implements Icon{
    icon_share('\ue60a')
    ;

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
