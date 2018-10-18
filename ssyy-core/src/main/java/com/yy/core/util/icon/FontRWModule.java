package com.yy.core.util.icon;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

public class FontRWModule implements IconFontDescriptor {
    @Override
    public String ttfFileName() {
        return "mfont.ttf";
    }

    @Override
    public Icon[] characters() {
        return RWIcons.values();
    }
}
