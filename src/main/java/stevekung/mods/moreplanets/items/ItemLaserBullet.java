package stevekung.mods.moreplanets.items;

import net.minecraft.util.IStringSerializable;
import stevekung.mods.moreplanets.util.items.EnumSortCategoryItem;
import stevekung.mods.moreplanets.util.items.ItemBaseVariantsMP;

public class ItemLaserBullet extends ItemBaseVariantsMP
{
    public ItemLaserBullet(String name)
    {
        super();
        this.setUnlocalizedName(name);
    }

    @Override
    public EnumSortCategoryItem getItemCategory(int meta)
    {
        return EnumSortCategoryItem.PROJECTILE;
    }

    @Override
    public String[] getItemVariantsName()
    {
        return new String[] { "normal", "infected_crystallized" };
    }

    public static enum ItemType implements IStringSerializable
    {
        NORMAL_LASER_BULLET,
        INFECTED_CRYSTALLIZED_LASER_BULLET;

        @Override
        public String getName()
        {
            return this.name().toLowerCase();
        }
    }
}