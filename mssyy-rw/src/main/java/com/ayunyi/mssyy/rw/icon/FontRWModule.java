package com.ayunyi.mssyy.rw.icon;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

public class FontRWModule implements IconFontDescriptor {
    @Override
    public String ttfFileName() {
        //noinspection SpellCheckingInspection
        return "eciconfont.ttf";
    }

    @Override
    public Icon[] characters() {
        return RWIcons.values();
    }
}
