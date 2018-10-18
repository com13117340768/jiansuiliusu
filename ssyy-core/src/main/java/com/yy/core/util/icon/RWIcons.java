package com.yy.core.util.icon;

import com.joanzapata.iconify.Icon;

public enum RWIcons implements Icon{
    icon_delete('\ue8b6'),icon_share('\ue60a'),
    icon_return('\ue618'),icon_search('\ue651'),
    icon_address('\ue65a'),icon_integral('\ue629'),
    icon_collection('\ue663'),icon_coupon('\ue61f'),
    icon_setting('\ue796'),

//    icon_address('\ue619'),icon_integral('\ue616'),
//    icon_collection('\ue62b'),icon_coupon('\ue608'),
//    icon_setting('\ue60b')
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
