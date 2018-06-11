package stevekung.mods.moreplanets.utils.itemblocks;

import stevekung.mods.stevekunglib.utils.ColorUtils;

public interface IItemRarity
{
    public static final String DEFAULT = "85, 85, 255, 255";
    public static final String SPECIAL = "0, 255, 207, 255";
    public static final String ALIEN = "178, 109, 237, 255";
    public static final String COMMON = "117, 151, 255, 255";
    public static final String MACHINE = "124, 124, 124, 255";

    ColorUtils.RGB getRarity();
}