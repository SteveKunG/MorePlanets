package stevekung.mods.moreplanets.module.planets.fronos.items;

import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.util.items.EnumSortCategoryItem;
import stevekung.mods.moreplanets.util.items.ItemBaseVariantsMP;

public class ItemFronos extends ItemBaseVariantsMP
{
    public ItemFronos(String name)
    {
        super();
        this.setUnlocalizedName(name);
    }

    @Override
    public float getSmeltingExperience(ItemStack itemStack)
    {
        return itemStack.getItemDamage() == 1 ? 1.0F : -1.0F;
    }

    @Override
    public EnumSortCategoryItem getItemCategory(int meta)
    {
        switch (meta)
        {
        case 0:
            return EnumSortCategoryItem.INGOT;
        case 1:
            return EnumSortCategoryItem.PLATE;
        }
        return EnumSortCategoryItem.GENERAL;
    }

    @Override
    public String[] getItemVariantsName()
    {
        return new String[] { "extrailonite_ingot", "compressed_extrailonite" };
    }

    public static enum ItemType
    {
        EXTRAILONITE_INGOT,
        COMPRESSED_EXTRAILONITE;
    }
}