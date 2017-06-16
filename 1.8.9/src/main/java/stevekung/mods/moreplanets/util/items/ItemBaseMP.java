package stevekung.mods.moreplanets.util.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsCore;

public class ItemBaseMP extends Item implements ISortableItem, ISingleItemRender
{
    private EnumSortCategoryItem category;
    protected String name;

    public ItemBaseMP()
    {
        super();
    }

    public ItemBaseMP(String name)
    {
        super();
        this.name = name;
        this.setUnlocalizedName(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public CreativeTabs getCreativeTab()
    {
        return MorePlanetsCore.ITEM_TAB;
    }

    @Override
    public EnumSortCategoryItem getItemCategory(int meta)
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

    @Override
    public Item getItem()
    {
        return this;
    }
}