package stevekung.mods.moreplanets.utils.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.utils.helper.CommonRegisterHelper;

public abstract class ItemBaseVariantsMP extends Item implements ISortableItem
{
    public ItemBaseVariantsMP()
    {
        super();
        this.setHasSubtypes(true);
    }

    @Override
    public CreativeTabs getCreativeTab()
    {
        return MorePlanetsMod.ITEM_TAB;
    }

    @Override
    public int getMetadata(int meta)
    {
        return meta;
    }

    @Override
    public void getSubItems(CreativeTabs creativeTabs, NonNullList<ItemStack> list)
    {
        if (CommonRegisterHelper.isItemTab(creativeTabs))
        {
            for (int i = 0; i < this.getItemVariantsName().length; i++)
            {
                list.add(new ItemStack(this, 1, i));
            }
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        int meta = itemStack.getItemDamage();
        return meta >= this.getItemVariantsName().length ? super.getUnlocalizedName(itemStack) + "." + this.getItemVariantsName()[0] : super.getUnlocalizedName(itemStack) + "." + this.getItemVariantsName()[meta];
    }

    @Override
    public EnumSortCategoryItem getItemCategory(int meta)
    {
        return EnumSortCategoryItem.GENERAL;
    }

    protected abstract String[] getItemVariantsName();
}