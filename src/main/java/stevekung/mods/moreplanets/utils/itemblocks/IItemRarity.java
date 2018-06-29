package stevekung.mods.moreplanets.utils.itemblocks;

import stevekung.mods.stevekunglib.utils.ColorUtils;

public interface IItemRarity
{
    public static final String SPECIAL = "0, 255, 207";
    public static final String ALIEN = "178, 109, 237";
    public static final String COMMON = "117, 151, 255";
    public static final String MACHINE = "124, 124, 124";

    ColorUtils.RGB getRarity();
}