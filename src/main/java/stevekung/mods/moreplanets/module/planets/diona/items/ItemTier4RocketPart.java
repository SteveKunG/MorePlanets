package stevekung.mods.moreplanets.module.planets.diona.items;

import stevekung.mods.moreplanets.util.items.EnumSortCategoryItem;
import stevekung.mods.moreplanets.util.items.ItemBaseVariantsMP;

public class ItemTier4RocketPart extends ItemBaseVariantsMP
{
    public ItemTier4RocketPart(String name)
    {
        super();
        this.setUnlocalizedName(name);
    }

    @Override
    public String[] getItemVariantsName()
    {
        return new String[] { "compressed_tier_3_rocket_plate", "tier_4_rocket_engine", "tier_4_nose_cone", "tier_4_booster" };
    }

    @Override
    public EnumSortCategoryItem getItemCategory(int meta)
    {
        return meta == 0 ? EnumSortCategoryItem.HEAVY_PLATE : EnumSortCategoryItem.GENERAL;
    }

    public static enum ItemType
    {
        COMPRESSED_TIER_3_ROCKET_PLATE,
        TIER_4_ROCKET_ENGINE,
        TIER_4_NOSE_CONE,
        TIER_4_BOOSTER;
    }
}