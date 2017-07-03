package stevekung.mods.moreplanets.module.planets.chalos.items;

import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.util.items.EnumSortCategoryItem;
import stevekung.mods.moreplanets.util.items.ItemBaseVariantsMP;

public class ItemChalos extends ItemBaseVariantsMP
{
    public ItemChalos(String name)
    {
        super();
        this.setUnlocalizedName(name);
    }

    @Override
    public float getSmeltingExperience(ItemStack itemStack)
    {
        switch (itemStack.getItemDamage())
        {
        case 2:
        case 3:
            return 1.0F;
        }
        return -1.0F;
    }

    @Override
    public EnumSortCategoryItem getItemCategory(int meta)
    {
        switch (meta)
        {
        case 0:
        case 1:
            return EnumSortCategoryItem.INGOT;
        case 2:
        case 3:
            return EnumSortCategoryItem.PLATE;
        }
        return EnumSortCategoryItem.GENERAL;
    }

    @Override
    public String[] getItemVariantsName()
    {
        return new String[] { "diremsium_ingot", "zyptorium_ingot", "compressed_diremsium", "compressed_zyptorium" };
    }

    public static enum ItemType
    {
        DIREMSIUM_INGOT,
        ZYPTORIUM_INGOT,
        COMPRESSED_DIREMSIUM,
        COMPRESSED_ZYPTORIUM;
    }
}