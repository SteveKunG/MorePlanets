package stevekung.mods.moreplanets.util.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;
import stevekung.mods.moreplanets.core.MorePlanetsCore;

public abstract class ItemFoodMP extends ItemFood implements ISortableItem, ISingleItemRender
{
    public ItemFoodMP()
    {
        super(0, false);
    }

    @Override
    public CreativeTabs getCreativeTab()
    {
        return MorePlanetsCore.ITEM_TAB;
    }

    @Override
    public EnumSortCategoryItem getItemCategory(int meta)
    {
        return EnumSortCategoryItem.FOOD;
    }
}