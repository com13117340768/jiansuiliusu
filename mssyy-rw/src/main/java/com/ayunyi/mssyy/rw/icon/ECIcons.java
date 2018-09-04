package com.ayunyi.mssyy.rw.icon;

import com.joanzapata.iconify.Icon;

public enum  ECIcons implements Icon{
    icon_scan('\ue606'),
    icon_ali_pay('\ue606'),
    qr_code('\ue602'),
    qr_co('\ue600')
    ;

    private char aChar;

    ECIcons(char aChar) {
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
