package stevekung.mods.moreplanets.utils.itemblocks;

import stevekung.mods.stevekunglib.utils.ColorUtils;

public interface IItemRarity
{
    String SPECIAL = "0, 255, 207";
    String ALIEN = "178, 109, 237";
    String COMMON = "117, 151, 255";
    String MACHINE = "124, 124, 124";

    ColorUtils.RGB getRarity();
}