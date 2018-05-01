package stevekung.mods.moreplanets.utils.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.utils.client.renderer.IItemModelRender;

public class ItemBaseMP extends Item implements ISortableItem, IItemModelRender
{
    private EnumSortCategoryItem category;
    private String name;

    public ItemBaseMP()
    {
        super();
    }

    public ItemBaseMP(String name)
    {
        super();
        this.setUnlocalizedName(name);
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
        return this.category == null ? EnumSortCategoryItem.GENERAL : this.category;
    }

    public ItemBaseMP setSortCategory(EnumSortCategoryItem category)
    {
        this.category = category;
        return this;
    }

    @Override
    public String getName()
    {
        return this.name;
    }
}