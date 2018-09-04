package com.ayunyi.mssyy.rw.icon;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

public class FontECModule implements IconFontDescriptor {
    @Override
    public String ttfFileName() {
        //noinspection SpellCheckingInspection
        return "iconfont.ttf";
    }

    @Override
    public Icon[] characters() {
        return ECIcons.values();
    }
}
