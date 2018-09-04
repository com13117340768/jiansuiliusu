package com.yy.core.ui.Banner;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

public class HolderCreator implements CBViewHolderCreator<ImageHolder>{
    @Override
    public ImageHolder createHolder() {
        return new ImageHolder();
    }
}
