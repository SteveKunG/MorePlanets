package stevekung.mods.moreplanets.module.planets.nibiru.items;

import stevekung.mods.moreplanets.utils.items.EnumSortCategoryItem;
import stevekung.mods.moreplanets.utils.items.ItemBaseMP;

public class ItemWasteRodPicker extends ItemBaseMP
{
    public ItemWasteRodPicker(String name)
    {
        super();
        this.setMaxStackSize(1);
        this.setMaxDamage(15);
        this.setUnlocalizedName(name);
    }

    @Override
    public EnumSortCategoryItem getItemCategory()
    {
        return EnumSortCategoryItem.OTHER_TOOL;
    }
}