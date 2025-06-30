package com.stevekung.moreplanets.utils.itemblocks;

import com.stevekung.lib.utils.ColorUtils;

public interface IItemRarity
{
    String SPECIAL = "0, 255, 207";
    String ALIEN = "178, 109, 237";
    String COMMON = "117, 151, 255";
    String MACHINE = "124, 124, 124";

    ColorUtils.RGB getRarity();
}