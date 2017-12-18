package stevekung.mods.moreplanets.module.planets.nibiru.items;

import micdoodle8.mods.galacticraft.api.item.IKeyItem;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.util.items.EnumSortCategoryItem;
import stevekung.mods.moreplanets.util.items.ItemBaseVariantsMP;

public class ItemNibiruDungeonKey extends ItemBaseVariantsMP implements IKeyItem
{
    public ItemNibiruDungeonKey(String name)
    {
        super();
        this.setMaxStackSize(1);
        this.setUnlocalizedName(name);
    }

    @Override
    public int getTier(ItemStack itemStack)
    {
        return itemStack.getItemDamage() == 0 ? 6 : 0;
    }

    @Override
    public EnumSortCategoryItem getItemCategory(int meta)
    {
        return EnumSortCategoryItem.DUNGEON_KEY;
    }

    @Override
    public String[] getItemVariantsName()
    {
        return new String[] { "nibiru_dungeon_key", "nibiru_dungeon_key_part_1", "nibiru_dungeon_key_part_2" };
    }

    public static enum ItemType
    {
        NIBIRU_DUNGEON_KEY,
        NIBIRU_DUNGEON_KEY_PART_1,
        NIBIRU_DUNGEON_KEY_PART_2;
    }
}