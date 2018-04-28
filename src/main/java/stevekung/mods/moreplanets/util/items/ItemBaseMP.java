package stevekung.mods.moreplanets.util.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.module.planets.chalos.items.ChalosItems;

public class ItemBaseMP extends Item implements ISortableItem, ISingleItemRender
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
    public float getSmeltingExperience(ItemStack itemStack)
    {
        return itemStack.getItem() == ChalosItems.TIER_5_ROCKET_PART ? 2.0F : -1.0F;
    }

    @Override
    public CreativeTabs getCreativeTab()
    {
        return MorePlanetsMod.ITEM_TAB;
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
}