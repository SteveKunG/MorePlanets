package stevekung.mods.moreplanets.utils.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.utils.client.renderer.IItemModelRender;

public abstract class ItemFoodMP extends ItemFood implements ISortableItem, IItemModelRender
{
    private String name;

    public ItemFoodMP()
    {
        super(0, false);
    }

    @Override
    public Item setUnlocalizedName(String name)
    {
        this.name = name;
        return super.setUnlocalizedName(name);
    }

    @Override
    public CreativeTabs getCreativeTab()
    {
        return MorePlanetsMod.ITEM_TAB;
    }

    @Override
    public EnumSortCategoryItem getItemCategory()
    {
        return EnumSortCategoryItem.FOOD;
    }

    @Override
    public String getName()
    {
        return this.name;
    }
}